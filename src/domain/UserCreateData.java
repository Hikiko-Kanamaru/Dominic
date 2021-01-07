package domain;

public class UserCreateData {

	private final String  name;
	private final String password;
	/**
	 * @param name
	 * @param password
	 */
	public UserCreateData(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public String getPassword() {
		return password;
	}




}
