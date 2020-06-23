package com.kaoni.ch15.client;

import com.kaoni.ch15.Cons;
import com.kaoni.ch15.dao.MemberDao;
import com.kaoni.ch15.stage.ChattingStage;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * 
 * 
 *
 * desc 	: 로그인화면 만들기
 * @Company : KAONI
 * @author  : 김영롱
 * @Date	: 2020. 6. 23.
 * @Version : 1.0.0
 */
public class DoClient extends Application {
	private TextField inputId; // UI
	private TextField inputPw; // UI
	private Button btnJoin; // UI
	private Button btnLogin; // UI
	private BorderPane root; // UI

	// JavaFX로 UI생성
	@Override
	public void start(Stage primaryStage) throws Exception {
		root = new BorderPane();
		root.setPrefSize(350, 220);

		Label labelId = new Label("\t\tI D");
		labelId.setPrefSize(Cons.SET_LABEL_WIDTH, Cons.SET_LABEL_HEIGHT);
		Label labelPw = new Label("\t\tP W");
		labelPw.setPrefSize(Cons.SET_LABEL_WIDTH, Cons.SET_LABEL_HEIGHT);
		BorderPane top = new BorderPane();
		BorderPane center = new BorderPane();
		BorderPane bottom = new BorderPane();
		inputId = new TextField();
		inputId.setPrefSize(Cons.SET_INPUT_ID_WIDTH, Cons.SET_INPUT_ID_HEIGHT);
		BorderPane.setMargin(inputId, new Insets(Cons.MARGIN_BASIC)); // 마진
		inputPw = new TextField();
		inputPw.setPrefSize(Cons.SET_INPUT_ID_WIDTH, Cons.SET_INPUT_ID_HEIGHT);
		BorderPane.setMargin(inputPw, new Insets(Cons.MARGIN_BASIC)); // 마진
		// 회원가입 버튼
		btnJoin = new Button("회원 가입");
		btnJoin.setPrefSize(Cons.SET_LOGIN_BUTTON_WIDTH, Cons.SET_LOGIN_BUTTON_HEIGHT);
		BorderPane.setMargin(btnJoin, new Insets(Cons.MARGIN_BASIC)); // 마진
		// 연결버튼 클릭 시 액션
		btnJoin.setOnAction(e -> {
		});
		// 로그인 버튼
		btnLogin = new Button("로그인");
		btnLogin.setPrefSize(Cons.SET_LOGIN_BUTTON_WIDTH, Cons.SET_LOGIN_BUTTON_HEIGHT);
		BorderPane.setMargin(btnLogin, new Insets(Cons.MARGIN_BASIC)); // 마진
		btnLogin.setOnAction(e -> {
			checkLogin(); // 로그인 체크
		}); // login 버튼 클릭 시 액션
		top.setLeft(labelId);
		top.setRight(inputId);
		center.setLeft(labelPw);
		center.setRight(inputPw);
		bottom.setLeft(btnJoin);
		bottom.setRight(btnLogin);
		root.setTop(top);
		root.setCenter(center);
		root.setBottom(bottom);

		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("app.css").toString());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Client");
		primaryStage.setOnCloseRequest(event -> {
		}); // 종료 이벤트
		primaryStage.show();
	}

	private void checkLogin() {
		String memberId = inputId.getText().trim();
		String memberPw = inputPw.getText().trim();
		MemberDao memberDao = MemberDao.getMemberDao();
		String memberName = memberDao.memberLogin(memberId, memberPw);
		if (memberName != null) {// 로그인 성공
			startChatting(memberName);
		} else {// 로그인 실패
			String message = "[로그인 실패] 아이디 / 비밀번호를 확인해주세요";
			errorMsg(message);
		}
	}
	
	/**
	 * 
	 * desc : 채팅 시작
	 * @Method startChatting
	 * @param String
	 */
	private void startChatting(String memberName) {
		ChattingStage chat = new ChattingStage();
		Stage chatStage = chat.getChatStage(memberName);
		Stage oldStage = (Stage) btnLogin.getScene().getWindow();
		chatStage.show();
		oldStage.close();
	}

	private void errorMsg(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText(message);
		alert.showAndWait();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
