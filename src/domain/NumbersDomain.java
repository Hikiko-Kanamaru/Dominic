package domain;

public class NumbersDomain {

	private final int user_id;
	private final int list_number;

	NameListDomain name;
	ActionListDomain action;

	private int memory_Level;






	public int getUser_id() {
		return user_id;
	}






	/**
	 * @param user_id
	 * @param list_number
	 * @param name
	 * @param action
	 * @param memory_Level
	 */
	public NumbersDomain(int user_id, int list_number, NameListDomain name, ActionListDomain action, int memory_Level) {
		super();
		this.user_id = user_id;
		this.list_number = list_number;
		this.name = name;
		this.action = action;
		this.memory_Level = memory_Level;
	}






	/**
	 * @param user_id
	 * @param list_number
	 */
	public NumbersDomain(int user_id, int list_number) {
		super();
		this.user_id = user_id;
		this.list_number = list_number;
	}







	/**初期化失敗用コンストラクタ。
	 * 失敗の確認はuser_id = -1かmemoryLebelが-1を判定基準にする
	 * @param user_id = -1
	 * @param list_number = 200
	 * @param name = NameListDomain()
	 * @param action = ActionListDomain()
	 * @param memory_Level = -1
	 */
	public NumbersDomain() {
		super();
		this.user_id = -1;
		this.list_number = -1;
		this.name = new NameListDomain() ;
		this.action = new ActionListDomain();
		this.memory_Level = -1;
	}


	public int getList_number() {
		return list_number;
	}

	public NameListDomain getName() {
		return name;
	}

	public ActionListDomain getAction() {
		return action;
	}

	public int getMemory_Level() {
		return memory_Level;
	}

	public void setName(NameListDomain name) {
		this.name = name;
	}

	public void setAction(ActionListDomain action) {
		this.action = action;
	}

	public void setMemory_Level(int memory_Level) {
		this.memory_Level = memory_Level;
	}





}
