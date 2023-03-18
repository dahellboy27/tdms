package com.bezkoder.spring.files.csv.model;

public class Login {

	private String loginId;
	private String loginPassword;
	private String answer;

	public Login(String loginId, String loginPassword, String answer) {
		super();
		this.loginId = loginId;
		this.loginPassword = loginPassword;
		this.answer = answer;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "Login [loginId=" + loginId + ", loginPassword=" + loginPassword + ", answer=" + answer + "]";
	}

}
