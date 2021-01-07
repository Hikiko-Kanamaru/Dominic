package DAO;

import java.sql.Connection;
import java.util.Map;

import domain.NumberAndHuman;
import domain.NumbersDomain;

public interface NumbersDao {
	static final int defalutID = 1;


//	必要なもの
//	ナンバーを指定してデータが得られるもの
//	自分に登録してあれば、自分のもの
//	登録していなければデフォルトのもの
//	これらを実行するために、ユーザーIDとナンバーを与えたら、値を返してくれるものがあると良い
//
//	それと、0～100までの範囲で、指定した範囲のものを獲得してくれるメソッドも必要
//
//	レコードを登録できる機能も必要。
//
//	レコードを削除する機能も必要
//
//	登録する前に、name_idとaction_idの検索が必要、存在しない場合登録が必要
//	まずは、name_idDAOを作る　その後にACTION＿IDにコピーして利用する制作済み



//	List<User> findAll() throws Exception;
//
	/**numberで検索します
	 * ユーザーデータがなかった場合デフォルトを検索します
	 * 	一行＝レコードだけ手に入れる
	 * @param name
	 * @return LoginData
	 * @throws Exception
	 */
	NumbersDomain findByNumber(int userid,int number) throws Exception;


	/**numberで検索します
	 * ユーザーデータがなかった場合デフォルトを検索します
	 * 	一行＝レコードだけ手に入れる
	 * @param name
	 * @return LoginData
	 * @throws Exception
	 */
	Map<Integer,NumbersDomain> findByNumberMap(int userid,int first,int end) throws Exception;






	/**
	 * データベースに対応するデータを入れるメソッド
	 * @param NumberAndHuman
	 * @return NumbersDomain
	 * @throws Exception
	 */
	NumbersDomain insert(NumbersDomain NumDom) throws Exception;



	//	アップデートするメソッド
	NumbersDomain update(NumbersDomain user) throws Exception;

	//	デリートするメソッド
	boolean delete(int userid,int number) throws Exception;

	/**
	 * 自身のリストに存在するかを返してくれる
	 */
	boolean hasNumber(int userid,int number)throws Exception;


	/**
	 * データが有ればアップデート　なければインサートしてくれる
	 */
	NumbersDomain  updateOrInsert(NumbersDomain NumDom)throws Exception;


	/**
	 * データが有ればアップデート　なければインサートしてくれる
	 */
	NumbersDomain  updateOrInsert(NumberAndHuman user,int userID)throws Exception;


	/**
	 * コネクションも一緒にもらうタイプ
	 * @param userid
	 * @param number
	 * @param con
	 * @return
	 * @throws Exception
	 */
	boolean hasNumber(int userid, int number, Connection con) throws Exception;


	NumbersDomain findByNumber(int userid, int number, Connection con) throws Exception;



}
