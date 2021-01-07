package domain;


public class WordBookDomain {
	int ID;
	final int userID;
	int memoryLevel;
	String text ;

	/**
	 * @param iD
	 * @param userID
	 * @param memoryLevel
	 */
	public WordBookDomain(int iD, int userID, int memoryLevel,String text) {
		ID = iD;
		this.userID = userID;
		this.memoryLevel = memoryLevel;
		this.text = text;
	}

	/**\
	 * @param userID
	 */
	public WordBookDomain(int userID) {
		this.userID = userID;
	}

	/**失敗用イニシャライザ
	 * @param userID -1
	 */
	public WordBookDomain(boolean ari) {
		this.userID = -1;
		this.ID = -1;
	}



	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getMemoryLevel() {
		return memoryLevel;
	}

	public void setMemoryLevel(int memoryLevel) {
		this.memoryLevel = memoryLevel;
	}

	public int getUserID() {
		return userID;
	}

	/**
	 * @return text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text セットする text
	 */
	public void setText(String text) {
		this.text = text;
	}




}
