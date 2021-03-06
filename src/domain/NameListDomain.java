package domain;

public class NameListDomain {
	private final int id ;
	private final String name;
	private String URL ;
	private int Strength;



	/**
	 * @param id
	 * @param name
	 */
	public NameListDomain(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.URL = "";
		Strength = 0;
	}



	/**
	 * @param id
	 * @param name
	 * @param URL
	 * @param strength
	 */
	public NameListDomain(int id, String name, String URL, int strength) {
		super();
		this.id = id;
		this.name = name;
		this.URL = URL;
		Strength = strength;
	}



	/**
	 * 失敗データ用コンストラクタ
	 * @param id = 99
	 * @param name = 匿名希望
	 * @param uRL = www
	 * @param strength = -1
	 */
	public NameListDomain() {
		this.id = 99;
		this.name = "匿名希望";
		URL = "www";
		Strength = -1;
	}



	public int getId() {
		return id;
	}



	public String getName() {
		return name;
	}



	public String getURL() {
		return URL;
	}



	public int getStrength() {
		return Strength;
	}



	public void setURL(String uRL) {
		URL = uRL;
	}



	public void setStrength(int strength) {
		Strength = strength;
	}


}
