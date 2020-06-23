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
public class ClientDo extends Application {
	private String name;
	private Socket socket;
	private TextArea txtDisplay; // UI
	private TextField txtInput; // UI
	private Button btnConn; // UI
	private Button btnSend; // UI

	/**
	 * 
	 * desc : 연결 시작하는 메소드
	 * 
	 * @Method startClient
	 * @param String
	 */
	public void startClient(String name) {
		System.out.println("[" + name + "] startClient 메소드 실행");
		this.name = name;
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					socket = new Socket();
					socket.connect(new InetSocketAddress(Cons.PORTNUM));
					Platform.runLater(() -> {
						displayText("[" + name + "님 환영합니다]");
						btnConn.setText("stop");
						btnSend.setDisable(false);
					});
				} catch (IOException e) {
					Platform.runLater(() -> {
						displayText("[서버 통신 안됨]");
						stopClient();
						return;
					});
				}
				receive();
			}
		};
		thread.start();
	}

	/**
	 * 
	 * desc : 연결 끊을 때 메소드
	 * 
	 * @Method stopClient
	 */
	void stopClient() {
		System.out.println("[" + name + "] stopClient 메소드 실행");
		Platform.runLater(() -> {
			displayText("stopClient실행 [연결 끊음]");
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

	/**
	 * 
	 * desc : 내가 쓴 글인지 체크하는 메소드
	 * 
	 * @Method writeMe
	 * @param String
	 * @return boolean
	 */
	private boolean writeMe(String message) {
		System.out.println("[" + name + "] writeMe 메소드 실행");
		int nameSymbol = message.indexOf("]");
		String tempName = message.substring(1, nameSymbol);
		return name.equals(tempName);
	}

	/**
	 * 
	 * desc : 클라이언트의 메세지 받는 메소드
	 * 
	 * @Method receive
	 */
	public void receive() {
		System.out.println("[" + name + "] receive 메소드 실행");
		try {
			byte[] byteArr = new byte[Cons.READ_ONCE_NUM];
			InputStream is = socket.getInputStream();
			while (true) {
				int readByteCount = is.read(byteArr);
				if (readByteCount == -1) {
					break;
				}
				String data = new String(byteArr, 0, readByteCount, "UTF-8");
				if (writeMe(data)) {// 내가 쓴 글이면 띄어쓰기
					txtDisplay.appendText("\t\t\t\t\t\t");
				}
				Platform.runLater(() -> displayText(data));
			}
		} catch (IOException e) {
			System.out.println("Client의 receive에러: " + e.getMessage());
			Platform.runLater(() -> displayText("[서버 통신 안됨]"));
			stopClient();
		}
	}

	/**
	 * 
	 * desc : String을 보내는 메소드
	 * 
	 * @Method send
	 * @param String
	 */
	public void send(String data) {
		System.out.println("[" + name + "] send 메소드 실행");
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					byte[] byteArr = data.getBytes("UTF-8");
					OutputStream os = socket.getOutputStream();
					os.write(byteArr);
					os.flush();
					txtInput.setText("");
				} catch (IOException e) {
					Platform.runLater(() -> displayText("[서버 통신 안됨]"));
				}
			}
		};
		thread.start();
	}

	// JavaFX로 UI생성
	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println("[" + name + "] start 메소드 실행");
		BorderPane root = new BorderPane();
		root.setPrefSize(Cons.SET_UI_WIDTH, Cons.SET_UI_HEIGHT); // 가로 500, 세로 300

		txtDisplay = new TextArea("[이름 입력 후 start버튼 클릭해주세요]\n");
		txtDisplay.setEditable(false);
		BorderPane.setMargin(txtDisplay, new Insets(0, 0, 2, 0)); // 마진
		root.setCenter(txtDisplay);

		BorderPane bottom = new BorderPane();
		txtInput = new TextField();
		txtInput.setPrefSize(Cons.SET_BUTTON_WIDTH, Cons.SET_BUTTON_HEIGHT);
		BorderPane.setMargin(txtInput, new Insets(0, 1, 1, 1)); // 마진
		// 시작 버튼
		btnConn = new Button("start");
		btnConn.setPrefSize(Cons.SET_BUTTON_WIDTH, Cons.SET_BUTTON_HEIGHT);
		// 연결버튼 클릭 시 액션
		btnConn.setOnAction(e -> {
			if (btnConn.getText().equals("start")) { // 현재 연결 아님
				if (txtInput.getText().equals("")) { // 이름 먼저 입력하고 start
					txtDisplay.setText("[이름을 입력하고 start버튼을 클릭하세요]\n");
				} else {
					String name = txtInput.getText();
					txtInput.setText("");
					primaryStage.setTitle(name); // Title 이름으로 설정
					startClient(name); // 연결 실행
				}
			} else if (btnConn.getText().equals("stop")) { // 현재 연결 상태
				stopClient();
			}
		});
		// 보내는 버튼
		btnSend = new Button("send");
		btnSend.setPrefSize(Cons.SET_BUTTON_WIDTH, Cons.SET_BUTTON_HEIGHT);
		btnSend.setDisable(true);
		btnSend.setOnAction(e -> send("[" + name + "] " + txtInput.getText())); // send버튼 클릭 시 액션

		bottom.setCenter(txtInput);
		bottom.setLeft(btnConn);
		bottom.setRight(btnSend);
		root.setBottom(bottom);

		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("app.css").toString());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Client");
		primaryStage.setOnCloseRequest(event -> stopClient()); // 종료 이벤트
		primaryStage.show();
	}

	/**
	 * 
	 * desc : text 개행으로 보이게 해줌
	 * 
	 * @Method displayText
	 * @param String
	 */
	void displayText(String text) {
		txtDisplay.appendText(text + "\n");
	}

	public static void main(String[] args) {
		Application.launch(ClientDo.class, args);
	}
}
