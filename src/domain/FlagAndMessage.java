package domain;

public class FlagAndMessage {

	private boolean flag;
	private String msg;
	private int count;
	/**
	 * @param flag
	 * @param msg
	 * @param count
	 */
	public FlagAndMessage(boolean flag, String msg, int count) {
		super();
		this.flag = flag;
		this.msg = msg;
		this.count = count;
	}



	/**失敗用イニシャライザ
	 * @param flag = flag
	 * @param msg = ""
	 * @param count = -1
	 */
	public FlagAndMessage(boolean flag) {
		super();
		this.flag = flag;
		this.msg = "";
		this.count = -1;
	}



	/**
	 * @param flag false
	 * @param msg ""
	 * @param count -1
	 */
	public FlagAndMessage() {
		super();
		this.flag = false;
		this.msg = "";
		this.count = -1;
	}



	/**
	 * @return flag
	 */
	public boolean isFlag() {
		return flag;
	}



	/**
	 * @return msg
	 */
	public String getMsg() {
		return msg;
	}



	/**
	 * @return count
	 */
	public int getCount() {
		return count;
	}



	/**
	 * @param flag セットする flag
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}



	/**
	 * @param msg セットする msg
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}



	/**
	 * @param count セットする count
	 */
	public void setCount(int count) {
		this.count = count;
	}







}
