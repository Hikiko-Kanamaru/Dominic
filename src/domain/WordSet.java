package domain;

public final class WordSet {
	//対応する値
	private int number;
//	対応する文字
	private String word;
//	結果のテキスト文章
	private String answertext;

	//bookプロファイルID
	private int value = -1;
	/**
	 * @param number
	 * @param word
	 * @param answertext
	 */
	public WordSet(int number, String word, String answertext) {
		this.number = number;
		this.word = word;
		this.answertext = answertext;
	}





	/**
	 * @param number
	 * @param word
	 * @param answertext
	 * @param value
	 */
	public WordSet(int number, String word, String answertext, int value) {
		super();
		this.number = number;
		this.word = word;
		this.answertext = answertext;
		this.value = value;
	}





	/**
	 *this(-1,"未設定","テキスト未入力");
	 */
	public WordSet() {
		this(-1,"未設定","テキスト未入力");

	}
	public int getNumber() {
		return number;
	}
	public String getWord() {
		return word;
	}
	public String getAnswertext() {
		return answertext;
	}
	/**
	 * @return value
	 */
	public int getValue() {
		return value;
	}
	/**
	 * @param value セットする value
	 */
	public void setValue(int value) {
		this.value = value;
	}






}
