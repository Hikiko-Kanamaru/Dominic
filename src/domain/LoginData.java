package domain;

import java.io.Serializable;

public class LoginData implements Serializable {

	//後で、不変オブジェクト化するが、今は放置
//	シリアライザブルは、動作すると思うが、おかしくなったら確認すること

	private String userName = "ゲスト";
	private int userId = 99 ;
	private boolean loginState = false;
	/**
	 * @param userName
	 * @param userId
	 * @param loginState
	 */
	public LoginData(String userName, int userId, boolean loginState) {
		super();
		this.userName = userName;
		this.userId = userId;
		this.loginState = loginState;
	}
	/**
	 *
	 */
	public LoginData() {
		super();
	}
	public String getUserName() {
		return userName;
	}
	public int getUserId() {
		return userId;
	}
	public boolean isLoginState() {
		return loginState;
	}




}
