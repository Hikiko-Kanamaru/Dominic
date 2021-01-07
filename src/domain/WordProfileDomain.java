package domain;

public class WordProfileDomain {
	int id;
	String word = "";
	int value = 0;
	String text = "";
	String meam = "未設定";
	/**
	 * @param id
	 * @param word
	 * @param value
	 * @param text
	 * @param meam
	 */
	public WordProfileDomain(int id, String word, int value, String text, String meam) {
		this.id = id;
		this.word = word;
		this.value = value;
		this.text = text;
		this.meam = meam;
	}
	/**
	 * @param id
	 */
	public WordProfileDomain(int id) {
		this.id = id;
	}

	/**
	 * 失敗用コンストラクタ、何を入れようが失敗を返します
	 * @param id = -1
	 * @param word = ""
	 * @param value = -1
	 * @param text = ""
	 * @param meam = "未設定"
	 */
	public WordProfileDomain(boolean error) {
		this.id = -1;
		this.word = "";
		this.value = -1;
		this.text = "";
		this.meam = "未設定";
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getMeam() {
		return meam;
	}
	public void setMeam(String meam) {
		this.meam = meam;
	}







}
