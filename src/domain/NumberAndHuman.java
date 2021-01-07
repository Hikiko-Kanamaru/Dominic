package domain;

public class NumberAndHuman {

	final int number;
	final String type;
	final String name;
	final int nameId;
	final String nameURL;
	final String action;
	final int actionId;
	final String actionURL;
	/**
	 * @param number
	 * @param type
	 * @param name
	 * @param nameId
	 * @param nameURL
	 * @param action
	 * @param actionId
	 * @param actionURL
	 */
	public NumberAndHuman(int number, String type, String name, int nameId, String nameURL, String action,
			int actionId, String actionURL) {
		super();
		this.number = number;
		this.type = type;
		this.name = name;
		this.nameId = nameId;
		this.nameURL = nameURL;
		this.action = action;
		this.actionId = actionId;
		this.actionURL = actionURL;
	}


/**
 * 開発中用のデフォルトコード
 * 本番環境では使用しないこと
 * @param num
 */
	public NumberAndHuman(int num,String type) {
		this(num,
				type,
				"なまえ"+ num,
				1000+num,
				"なまえURL"+num,
				"アクション"+ num,
				2000+ num,
				"アクションURL"+ num);
	}



	public NumberAndHuman(NumbersDomain domain) {
		super();



		this.number = domain.getList_number() ;
		this.type = "human";
		this.name = domain.getName().getName();
		this.nameId = domain.getName().getId();
		this.nameURL = domain.getName().getURL();
		this.action = domain.getAction().getName();
		this.actionId = domain.getAction().getId();
		this.actionURL = domain.getAction().getURL();
	}





	/**
	 * 失敗用コンストラクタ
	 * @param number -1
	 * @param type
	 * @param name
	 * @param nameId
	 * @param nameURL
	 * @param action
	 * @param actionId
	 * @param actionURL
	 */
	public NumberAndHuman(boolean error) {
		super();
		this.number = -1;
		this.type = "action";
		this.name = "";
		this.nameId = -1;
		this.nameURL = "www";
		this.action = "";
		this.actionId = -1;
		this.actionURL = "www";
	}


	public final int getNumber() {
		return number;
	}
	public final String getType() {
		return type;
	}
	public final String getName() {
		return name;
	}
	public final int getNameId() {
		return nameId;
	}
	public final String getNameURL() {
		return nameURL;
	}
	public final String getAction() {
		return action;
	}
	public final int getActionId() {
		return actionId;
	}
	public final String getActionURL() {
		return actionURL;
	}







}
