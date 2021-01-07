package domain;

public class SerchAnswer {

	String seachWord;
	int seachNumber;
//	２桁ごとまとめたもの
	int[] seachNumberBox;
	int boxsize;
///	画面に表示するように文字列化するもの
	String answerNumber;
///検索結果が入るもの
	String answerWord;

	NumberAndHuman first;
	NumberAndHuman second;
	NumberAndHuman third;






	/**
	 * @param seachword
	 * @param seachNumber
	 * @param answerNumber
	 * @param answerword
	 */
	public SerchAnswer(String seachword, int seachNumber, String answerNumber, String answerword) {
		this.seachWord = seachword;
		this.seachNumber = seachNumber;
		this.answerNumber = answerNumber;
		this.answerWord = answerword;
	}






	/**
	 * @param seachword
	 * @param seachNumber
	 * @param seachNumberBox
	 * @param boxsize
	 * @param answerNumber
	 * @param answerword
	 * @param first
	 * @param second
	 * @param third
	 */
	public SerchAnswer(String seachword, int seachNumber, int[] seachNumberBox, int boxsize, String answerNumber,
			String answerword, NumberAndHuman first, NumberAndHuman second, NumberAndHuman third) {
		this.seachWord = seachword;
		this.seachNumber = seachNumber;
		this.seachNumberBox = seachNumberBox;
		this.boxsize = boxsize;
		this.answerNumber = answerNumber;
		this.answerWord = answerword;
		this.first = first;
		this.second = second;
		this.third = third;
	}

	/**
	 *
	 * 開発用デフォルトメソッド　
	 * 開発中のみ利用すること
	 * @param serchword
	 * @param serchNumber
	 */
	public SerchAnswer(String serchword, int serchNumber) {

		System.out.println("インスタンス精製まで来ています");
		this.seachWord = serchword;
		this.seachNumber = serchNumber;
		this.seachNumberBox = new int[]{12,54,67};
		this.boxsize = 3;
		this.answerNumber = String.valueOf(serchNumber);
		this.answerWord = "これが答えだす"+serchNumber;
		System.out.println("ここまで来ました2");
//		var action = ACTION;
		System.out.println("ここまで来ました３");
		this.first = new NumberAndHuman(14, "ACTION");
		this.second = new NumberAndHuman(56, "ACTION");
		this.third = new NumberAndHuman(69, "ACTION");

	}






	public String getSeachWord() {
		return seachWord;
	}
	public void setSeachWord(String seachword) {
		this.seachWord = seachword;
	}
	public int getSeachNumber() {
		return seachNumber;
	}
	public void setSeachNumber(int seachNumber) {
		this.seachNumber = seachNumber;
	}
	public int[] getSeachNumberBox() {
		return seachNumberBox;
	}
	public void setSeachNumberBox(int[] seachNumberBox) {
		this.seachNumberBox = seachNumberBox;
	}
	public int getBoxsize() {
		return boxsize;
	}
	public void setBoxsize(int boxsize) {
		this.boxsize = boxsize;
	}
	public String getAnswerNumber() {
		return answerNumber;
	}
	public void setAnswerNumber(String answerNumber) {
		this.answerNumber = answerNumber;
	}
	public String getAnswerword() {
		return answerWord;
	}
	public void setAnswerword(String answerword) {
		this.answerWord = answerword;
	}
	public NumberAndHuman getFirst() {
		return first;
	}
	public void setFirst(NumberAndHuman first) {
		this.first = first;
	}
	public NumberAndHuman getSecond() {
		return second;
	}
	public void setSecond(NumberAndHuman second) {
		this.second = second;
	}
	public NumberAndHuman getThird() {
		return third;
	}
	public void setThird(NumberAndHuman third) {
		this.third = third;
	}






}
