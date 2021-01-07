package DAO;

import domain.LoginData;
import domain.UserCreateData;

public interface UserDao {

//	//	５つメソッドをつくる
//	//	Readメソッドが２つ
//	//	全部手に入れる
//	List<User> findAll() throws Exception;
//
	/**
	 * 	一行＝レコードだけ手に入れる
	 * @param name
	 * @return LoginData
	 * @throws Exception
	 */
	LoginData findByName(String name) throws Exception;

	/**
	 * 一行＝レコードだけ手に入れる IDでアクセス
	 * @param userId
	 * @return LoginData
	 * @throws Exception
	 */
	LoginData findById(int userId) throws Exception;

	/**
	 * データベースにユーザーをcreateするメソッド
	 * @param user:User名とパスワード
	 * @return LoginData
	 * @throws Exception
	 */
	LoginData insert(UserCreateData user) throws Exception;


//	//	アップデートするメソッド
//	void update(User user) throws Exception;
//
//	//	デリートするメソッド
//	void delete(Integer id) throws Exception;

	/**
	 * パスワードとIDでログイン
	 */
	LoginData findByLoginIdAndLoginPass(String loginName,String loginPass) throws Exception;

	/**
	 * 名前が存在するかどうかを調べるメソッド
	 * 名前が存在する場合:true
	 * @param name
	 * @return
	 * @throws Exception
	 */
	boolean findName(String name)throws Exception;

}
