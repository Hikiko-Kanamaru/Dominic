package DAO;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import domain.WordBookDomain;
import domain.WordJoinDomain;

public interface WordBookDao {

	//	必要なもの

	//	検索

	//	userIDを与えると、登録idの配列を返してくれるもの
//	idを与えると、配列でデータを返してくれるもの
	// idとuserIDを与えてデータを得るもの

	//	登録機能
	//
	//	削除機能：いらない単語帳とかあるので削除は必要
	//	削除の際には、idとuserIDが必須なので注意すること
	//
	//	上書き機能：IDを指定して、中身を書き換えられるようにする機能　textと強度変更との2つ
	//
	//登録か上書きかの自動選択機能。

	//

	WordBookDomain findByIdAndUserId(int id,int userId) throws Exception;
	Map<Integer,WordBookDomain> findByid(int id) throws Exception;

	Map<Integer,WordBookDomain> findByUserID(int userId) throws Exception;


	WordBookDomain insert(WordBookDomain domain) throws Exception;

	WordBookDomain update(WordBookDomain domain) throws Exception;

	/**
	 * データがなかったらインサート
	 * データが有ったUpdateするメソッド
	 */
	WordBookDomain insertorUpdate(WordBookDomain domain) throws Exception;

	//
	boolean delete(WordBookDomain domain) throws Exception;

	List<WordJoinDomain> findWordJoinByUserID(int userId) throws Exception;
	/**joinされたリストを返す
	 * wordBookだけではなくwordProfにもアクセスする
	 *Mono用一つのデータだけを返す
	 */
	WordJoinDomain findWordJoinByUserIDandProfID(int userId, int profId) throws Exception;
	WordBookDomain findByIdAndUserId(int id, int userId, Connection con) throws Exception;


}
