package com.kaoni.ch15;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * 
 * 
 *
 * desc : server 프로그램
 * 
 * @Company : KAONI
 * @author : 김영롱
 * @Date : 2020. 6. 22.
 * @Version : 1.0.0
 */
public class ServerEx_1 extends Application {
	private ExecutorService executorService; // Thread Pool
	private ServerSocket serverSocket;
	private List<ClientEx> connections = new Vector<ClientEx>();
	private TextArea txtDisplay; // UI
	private Button btnStartStop; // UI

	public void startServer() {
		executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		try {
			serverSocket = new ServerSocket(Cons.PORTNUM);
		} catch (IOException e) {
			if (!serverSocket.isClosed()) {
				stopServer();
			}
			return;
		}

		Runnable runnable = () -> {
			Platform.runLater(() -> {
				displayText("[서버 시작]");
				btnStartStop.setText("stop");
			});
			while (true) {
				Socket socket;
				try {
					socket = serverSocket.accept(); // 연결을 기다림
					String message = "[연결 수락: " + socket.getRemoteSocketAddress() + ": "
							+ Thread.currentThread().getName() + "]";
					Platform.runLater(() -> {
						displayText(message);
					});
					ClientEx client = new ClientEx(socket);
					connections.add(client);
					Platform.runLater(() -> {
						displayText("[연결 개수: " + connections.size() + "]");
					});
				} catch (IOException e) {
					if (!serverSocket.isClosed()) {
						stopServer();
						break;
					}
				}
			}
		};
		executorService.submit(runnable);
	}

	public void stopServer() {
		Iterator<ClientEx> iterator = connections.iterator();
		try {
			while (iterator.hasNext()) {
				ClientEx client = iterator.next();
				client.socket.close();
				iterator.remove();
			}
			if (serverSocket != null && !serverSocket.isClosed()) {
				serverSocket.close();
			}
			if (executorService != null && !executorService.isShutdown()) {
				executorService.shutdown();
			}
			Platform.runLater(() -> {
				displayText("[서버 멈춤]");
				btnStartStop.setText("start");
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 클라이언트 클래스
	class ClientEx {
		Socket socket;

		ClientEx(Socket socket) {
			this.socket = socket;
			receive();
		}

		public void receive() {
			Runnable runnable = () -> {
				try {
					while (true) {
						byte[] byteArr = new byte[Cons.READ_BY_COUNT];
						InputStream inputStream = socket.getInputStream();
						int readByteCount = inputStream.read(byteArr);
						if (readByteCount == -1) {
							throw new IOException();
						}

						String message = "[요청 처리: " + socket.getRemoteSocketAddress() + ": "
								+ Thread.currentThread().getName() + "]";
						Platform.runLater(() -> displayText(message));
						String data = new String(byteArr, 0, readByteCount, "UTF-8");
						for (ClientEx client : connections) {
							client.send(data);
						}
					}
				} catch (IOException e) {
					connections.remove(ClientEx.this);
					String message = "[클라이언트 통신 안됨: " + socket.getRemoteSocketAddress() + ": "
							+ Thread.currentThread().getName() + "]";
					Platform.runLater(() -> displayText(message));
					try {
						socket.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			};
			executorService.submit(runnable);

		}

		public void send(String data) {
			Runnable runnable = () -> {
				try {
					byte[] byteArr = data.getBytes("UTF-8");
					OutputStream os = socket.getOutputStream();
					os.write(byteArr);
					os.flush();
				} catch (IOException e) {
					String message = "[클라이언트 통신 안됨: " + socket.getRemoteSocketAddress() + ": "
							+ Thread.currentThread().getName() + "]";
					Platform.runLater(() -> displayText(message));
					connections.remove(ClientEx.this);
					try {
						socket.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			};
			executorService.submit(runnable);
		}

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

		btnStartStop = new Button("start");
		btnStartStop.setPrefHeight(30);
		btnStartStop.setMaxWidth(Double.MAX_VALUE);
		btnStartStop.setOnAction(e -> {
			if (btnStartStop.getText().equals("start")) {
				startServer();
			} else if (btnStartStop.getText().equals("stop")) {
				stopServer();
			}
		});
		root.setBottom(btnStartStop);

		Scene scene = new Scene(root);
//		scene.getStylesheets().add(getClass().getResource("app.css").toString());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Server");
		primaryStage.setOnCloseRequest(event -> stopServer());
		primaryStage.show();
	}

	public void displayText(String text) {
		txtDisplay.appendText(text + "\n");
	}

	public static void main(String[] args) {
		System.out.println("server시작");
		launch(args);
	}

}
