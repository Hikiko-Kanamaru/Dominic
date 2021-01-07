package domain;

public class WordJoinDomain {

	/**
	 * wordBookとwordProfのID
	 * 結合用に使っている
	 */
	private int id;
	/** wordBookの値*/
	private int memoryLevel;

	/** wordBookの値 関連付けられた文章*/
	private String text;

	/** wordProfの値 関連付ける単語*/
	private String word;

	/** wordProfの値 関連付ける数値*/
	private int value;

	/**
	 * @param id
	 * @param memoryLevel
	 * @param text
	 * @param word
	 * @param value
	 */
	public WordJoinDomain(int id, int memoryLevel, String text, String word, int value) {
		super();
		this.id = id;
		this.memoryLevel = memoryLevel;
		this.text = text;
		this.word = word;
		this.value = value;
	}

	/**
	 * @param id = 1-
	 * @param memoryLevel = -1
	 * @param text = "未登録"
	 * @param word = "未設定"
	 * @param value = -1
	 */
	public WordJoinDomain(boolean out) {
		super();
		this.id = -1;
		this.memoryLevel = -1;
		this.text = "未登録";
		this.word = "未設定";
		this.value = -1;
	}

	/**
	 * @param id = 1-
	 * @param memoryLevel = -1
	 * @param text = "未登録"
	 * @param word = "未設定"
	 * @param value = -1
	 */
	public WordJoinDomain() {
		super();
		this.id = -1;
		this.memoryLevel = -1;
		this.text = "未登録";
		this.word = "未設定";
		this.value = -1;
	}

	/**
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return memoryLevel
	 */
	public int getMemoryLevel() {
		return memoryLevel;
	}

	/**
	 * @return text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @return word
	 */
	public String getWord() {
		return word;
	}

	/**
	 * @return value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param id セットする id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param memoryLevel セットする memoryLevel
	 */
	public void setMemoryLevel(int memoryLevel) {
		this.memoryLevel = memoryLevel;
	}

	/**
	 * @param text セットする text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @param word セットする word
	 */
	public void setWord(String word) {
		this.word = word;
	}

	/**
	 * @param value セットする value
	 */
	public void setValue(int value) {
		this.value = value;
	}











}
