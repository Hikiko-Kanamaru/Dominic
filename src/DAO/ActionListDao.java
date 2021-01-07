package DAO;

import java.sql.Connection;

import domain.ActionListDomain;

public interface ActionListDao {


//	必要なもの

//	名前が存在するかの検索機能。IDと名前とURLと強度を返してくれる　強度－１なら存在しない扱いで

//	登録機能
//
//	削除機能：複数の人間から見られる可能性があるものなので、削除は今はやめとく
//
//	上書き機能：IDを指定して、中身を書き換えられるようにする機能　URLとStrengthのみ
//
//	通信用にdomainを用意する　ActionListDomain


;
//
	/**
	 * 	一行＝レコードだけ手に入れる
	 * 名前から獲得
	 * @param name
	 * @return ActionListDomain
	 * @throws Exception
	 */
	ActionListDomain findByName(String name) throws Exception;

	/**
	 * 一行＝レコードだけ手に入れる IDでアクセス
	 * IDから獲得
	 * @param userId
	 * @return ActionListDomain
	 * @throws Exception
	 */
	ActionListDomain findById(int id) throws Exception;

	ActionListDomain findById(int id, Connection con) throws Exception;


	/**
	 * データベースに人名をcreateするメソッド
	 * 名前とURLがあればいいな
	 * @param name
	 * @param URL
	 * @return ActionListDomain
	 * @throws Exception
	 */
	ActionListDomain insert(String name,String URL) throws Exception;




	/**
	 * アップデートするメソッド
	 * 変更できるのはURLとstrengthだけなので注意すること
	 * @param domain
	 * @return ActionListDomain
	 * @throws Exception
	 */
	ActionListDomain update(ActionListDomain domain) throws Exception;


	/**
	 * データがなかったらインサート
	 * データが有ったUpdateするメソッド
	 * @param name
	 * @param URL
	 * @return
	 * @throws Exception
	 */
	ActionListDomain insertorUpdate(String name,String URL) throws Exception;





//
//	//	デリートするメソッド デリートはさせない、複数のユーザーから見られる可能性があるため
//	void delete(Integer id) throws Exception;




}
