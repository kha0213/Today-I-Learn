package com.kaoni.ch15;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * 
 * 
 *
 * desc : 클라이언트 프로그램
 * 
 * @Company : KAONI
 * @author : 김영롱
 * @Date : 2020. 6. 22.
 * @Version : 1.0.0
 */
public class ClientDo_1 extends Application {
	Socket socket;
	private TextArea txtDisplay; // UI
	private TextField txtInput; // UI
	private Button btnConn; // UI
	private Button btnSend; // UI
	
	void startClient() {
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					socket = new Socket();
					socket.connect(new InetSocketAddress(Cons.PORTNUM));
					
					Platform.runLater(() -> {
						displayText("[연결 완료: " + socket.getRemoteSocketAddress() + "]");
						btnConn.setText("stop");
						btnSend.setDisable(false);
					});
				} catch (IOException e) {
					Platform.runLater(() -> {
						displayText("[서버 통신 안됨]");
						if (!socket.isClosed()) {
							stopClient();
							return;
						}
					});
				}
				receive();
			}
		};
		thread.start();
	}

	void stopClient() {
		Platform.runLater(() -> {
			displayText("[연결 끊음]");
			btnConn.setText("start");
			btnSend.setDisable(true);
		});
		try {
			if (socket != null && !socket.isClosed()) {
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void receive() {
		while (true) {
			byte[] byteArr = new byte[Cons.READ_BY_COUNT];
			InputStream is = null;
			try {
				is = socket.getInputStream();
				while(true) {
					int readByteCount = is.read(byteArr);
					if (readByteCount == -1) {
						break;
					}
					String data = new String(byteArr, 0, readByteCount, "UTF-8");
					Platform.runLater(() -> displayText("[받기 완료] " + data));
					
				}
			} catch (IOException e) {
				Platform.runLater(() -> displayText("[서버 통신 안됨]"));
				stopClient();
				break;
			} finally {
				try {
					if (is != null) {
						is.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void send(String data) {
		Thread thread = new Thread() {
			@Override
			public void run() {
				OutputStream os = null;
				try {
					byte[] byteArr = data.getBytes("UTF-8");
					os = socket.getOutputStream();
					os.write(byteArr);
					os.flush();
					Platform.runLater(() -> displayText("[보내기 완료]"));
					txtInput.setText("");
				} catch (IOException e) {
					Platform.runLater(() -> displayText("[서버 통신 안됨]"));
				} finally {
					try {
						if (os != null) {
							os.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		};
		thread.start();
	}

	// 따라 친 부분 ( JavaFX로 UI생성)
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		root.setPrefSize(500, 300); // 가로 500, 세로 300

		txtDisplay = new TextArea();
		txtDisplay.setEditable(false);
		BorderPane.setMargin(txtDisplay, new Insets(0, 0, 2, 0));
		root.setCenter(txtDisplay);

		BorderPane bottom = new BorderPane();
		txtInput = new TextField();
		txtInput.setPrefSize(60, 30);
		BorderPane.setMargin(txtInput, new Insets(0, 1, 1, 1));
		// 시작 버튼
		btnConn = new Button("start");
		btnConn.setPrefSize(60, 30);
		// 연결버튼 클릭 시 액션
		btnConn.setOnAction(e -> {
			if (btnConn.getText().equals("start")) {
				startClient();
			} else if (btnConn.getText().equals("stop")) {
				stopClient();
			}
		});
		// 보내는 버튼
		btnSend = new Button("send");
		btnSend.setPrefSize(60, 30);
		btnSend.setDisable(true);
		btnSend.setOnAction(e -> send(txtInput.getText())); // send버튼 클릭 시 액션

		bottom.setCenter(txtInput);
		bottom.setLeft(btnConn);
		bottom.setRight(btnSend);
		root.setBottom(bottom);

		Scene scene = new Scene(root);
//		scene.getStylesheets().add(getClass().getResource("app.css").toString());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Client");
		primaryStage.setOnCloseRequest(event -> stopClient());
		primaryStage.show();
	}

	void displayText(String text) {
		txtDisplay.appendText(text + "\n");
	}

	public static void main(String[] args) {
		launch(args);
	}

}
