package DAO;

import java.sql.Connection;
import java.util.Map;

import domain.WordProfileDomain;

public interface WordProfileDao {

	//	必要なもの

	//	名前が存在するかの検索機能。IDと名前とURLと強度を返してくれる　強度－１なら存在しない扱いで

	//	登録機能
	//
	//	削除機能：複数の人間から見られる可能性があるものなので、削除は今はやめとく
	//
	//	上書き機能：IDを指定して、中身を書き換えられるようにする機能　URLとStrengthのみ
	//
	//	通信用にdomainを用意する　NameListDomain

	;
	//

	WordProfileDomain findByid(int id) throws Exception;

	WordProfileDomain findByNotid(WordProfileDomain domain) throws Exception;

	Map<Integer, WordProfileDomain> findword(String word) throws Exception;

	WordProfileDomain insert(WordProfileDomain domain) throws Exception;

	WordProfileDomain update(WordProfileDomain domain) throws Exception;

	/**
	 * データがなかったらインサート
	 * データが有ったUpdateするメソッド
	 */
	WordProfileDomain insertorUpdate(WordProfileDomain domain) throws Exception;

	//
	//
	boolean delete(WordProfileDomain id) throws Exception;

	/**
	 * word とValueで検索
	 * 同一者が複数登録されることがありうるので、idがもっとも大きいものを返すようにする
	 * 同時に同じものを登録しない限り問題は起きない
	 * データが見つからなかったらIDが－1になります。
	 */
	WordProfileDomain findByNotid(WordProfileDomain domain, Connection con) throws Exception;

}
