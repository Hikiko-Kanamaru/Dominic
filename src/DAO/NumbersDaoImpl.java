package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.sql.DataSource;

import domain.ActionListDomain;
import domain.NameListDomain;
import domain.NumberAndHuman;
import domain.NumbersDomain;

public class NumbersDaoImpl implements NumbersDao {

	//	フィールド
	private DataSource ds;

	//	ファクトリーからデータソースを受け取るのに必要
	public NumbersDaoImpl(DataSource ds) {
		this.ds = ds;
		System.out.println(ds);

	}



	@Override
	public NumbersDomain findByNumber(int userid, int number) throws Exception {

		NumbersDomain numbersDomain = new NumbersDomain();

//		int setUserID =  1 ;

		System.out.println("findByNumberコネクション獲得実施1");
		try (Connection con = ds.getConnection()) {
//			sqlを一発読み込みに変更するので、チェック系をやめる
//			System.out.println("findByNumberコネクション獲得後2");
//			boolean testFlag = hasNumber(userid, number,con);
//
//
//			int setUserID =  testFlag ? userid:NumbersDao.defalutID ;
//
//			System.out.println("findByNumberコネクション獲得後3");
//			String sql = "SELECT * FROM numbers where user_id = ? and list_number = ? ;";
			String sql = "select user_id,"
					+ " list_number, "
					+ "name_id, name_URL, action_id, action_URL,"
					+ " memory_Level, namelist.name as name_Name, actionlist.name as action_Name "
					+ "from numbers join namelist on numbers.name_id = namelist.id "
					+ "join actionlist on numbers.action_id = actionlist.id "
					+ "where list_number = ? and user_id in (?,1)  "
					+ "order by user_id desc;";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(2, userid);
			stmt.setInt(1, number);



			System.out.println("findByNumberコネクション獲得後４");
			ResultSet rs = stmt.executeQuery();
			System.out.println("検索中2");

			if (rs.next()) {

				System.out.println("検索結果が帰ってきています");

				int dbnameid = (Integer) rs.getObject("name_id");
				String dbnameURL = rs.getString("name_URL");
				int dbactionid = (Integer) rs.getObject("action_id");
				String dbactionURL = rs.getString("action_URL");
				int dbmemory = (Integer) rs.getObject("memory_Level");

//				var nameListDAO = DaoFactory.createNameList();
//				var nameList = nameListDAO.findById(dbnameid,con);
//				nameList.setURL(dbnameURL);

//				var actionListDAO = DaoFactory.createActionList();
//				var actionList = actionListDAO.findById(dbactionid,con);
//				actionList.setURL(dbactionURL);

//	データベースのカラム名
//				*user_id, *list_number, *name_id, *name_URL, *action_id,
//				*action_URL, *memory_Level, name_Name, action_Name

				String dbnameName = rs.getString("name_Name");
				String dbactionName = rs.getString("action_Name");

				NameListDomain nameid = new NameListDomain(dbnameid, dbnameName, dbnameURL, dbmemory);

				ActionListDomain actionid = new ActionListDomain(dbactionid, dbactionName, dbactionURL, dbmemory);
				int memory_Level = dbmemory;
				numbersDomain = new NumbersDomain(userid, number, nameid, actionid, memory_Level);

			}else {
				System.out.println("データが存在しませんでした。");
			}

		} catch (Exception e) {
			System.out.println("findByNumberサーバー接続に失敗しました");
			throw e;
		}

		return numbersDomain;

	}
	@Override
	public NumbersDomain findByNumber(int userid, int number,Connection con) throws Exception {

		NumbersDomain numbersDomain = new NumbersDomain();

//		int setUserID =  1 ;

		System.out.println("findByNumberコネクション獲得実施1");
		try  {
//			sqlを一発読み込みに変更するので、チェック系をやめる
//			System.out.println("findByNumberコネクション獲得後2");
//			boolean testFlag = hasNumber(userid, number,con);
//
//
//			int setUserID =  testFlag ? userid:NumbersDao.defalutID ;
//
//			System.out.println("findByNumberコネクション獲得後3");
//			String sql = "SELECT * FROM numbers where user_id = ? and list_number = ? ;";
			String sql = "select user_id,"
					+ " list_number, "
					+ "name_id, name_URL, action_id, action_URL,"
					+ " memory_Level, namelist.name as name_Name, actionlist.name as action_Name "
					+ "from numbers join namelist on numbers.name_id = namelist.id "
					+ "join actionlist on numbers.action_id = actionlist.id "
					+ "where list_number = ? and user_id in (?,1)  "
					+ "order by user_id desc;";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(2, userid);
			stmt.setInt(1, number);



			System.out.println("findByNumberコネクション獲得後４");
			ResultSet rs = stmt.executeQuery();
			System.out.println("検索中2");

			if (rs.next()) {

				System.out.println("検索結果が帰ってきています");

				int dbnameid = (Integer) rs.getObject("name_id");
				String dbnameURL = rs.getString("name_URL");
				int dbactionid = (Integer) rs.getObject("action_id");
				String dbactionURL = rs.getString("action_URL");
				int dbmemory = (Integer) rs.getObject("memory_Level");

//				var nameListDAO = DaoFactory.createNameList();
//				var nameList = nameListDAO.findById(dbnameid,con);
//				nameList.setURL(dbnameURL);

//				var actionListDAO = DaoFactory.createActionList();
//				var actionList = actionListDAO.findById(dbactionid,con);
//				actionList.setURL(dbactionURL);

//	データベースのカラム名
//				*user_id, *list_number, *name_id, *name_URL, *action_id,
//				*action_URL, *memory_Level, name_Name, action_Name

				String dbnameName = rs.getString("name_Name");
				String dbactionName = rs.getString("action_Name");

				NameListDomain nameid = new NameListDomain(dbnameid, dbnameName, dbnameURL, dbmemory);

				ActionListDomain actionid = new ActionListDomain(dbactionid, dbactionName, dbactionURL, dbmemory);
				int memory_Level = dbmemory;
				numbersDomain = new NumbersDomain(userid, number, nameid, actionid, memory_Level);

			}else {
				System.out.println("データが存在しませんでした。");
			}

		} catch (Exception e) {
			System.out.println("findByNumberサーバー接続に失敗しました");
			throw e;
		}

		return numbersDomain;

	}

	@Override
	public Map<Integer, NumbersDomain> findByNumberMap(int userid, int first, int end) throws Exception {

		Map<Integer, NumbersDomain> map = new HashMap<>();

		int i = first ,f = end;

		for (;i<=f;i++) {
			if(i>=100 || i<0)break;

			map.put(i, findByNumber(userid, i));

		}

		return map;
	}



	/**
	 * 値のチェックはここではやらない
	 * 正しい値が入っているものとして処理する
	 */
	@Override
	public NumbersDomain insert(NumbersDomain NumDom) throws Exception {

		//	データベースと連携し出たが登録できたか確認する 1になっていれば成功とみなそう
		int stmtEXECount = 0;

		//		dsにはデータソースが入っているので問題ない
		try (Connection con = ds.getConnection()) {
			System.out.println("insert１");

			String sql = "INSERT INTO "
					+ "`numbers` "
					+ "(`user_id`, `list_number`, "
					+ "`name_id`, `name_URL`, `action_id`, `action_URL`, "
					+ "`memory_Level`) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?);";

			PreparedStatement stmt = con.prepareStatement(sql);


			stmt.setInt(1, NumDom.getUser_id());
			stmt.setInt(2, NumDom.getList_number());
			stmt.setInt(3, NumDom.getName().getId());
			stmt.setString(4, NumDom.getName().getURL());
			stmt.setInt(5, NumDom.getAction().getId());
			stmt.setString(6,NumDom.getAction().getURL());
			stmt.setInt(7, NumDom.getMemory_Level());


			stmtEXECount = stmt.executeUpdate();

//			System.out.println("insert2");
		} catch (Exception e) {
//			System.out.println("insert3");
			throw new ServletException(e);
		}

		if (stmtEXECount != 1) {
			System.out.println("numbersの登録に失敗しました。");
		}else {
			System.out.println("numbersの登録に成功しました。");
		}

		return NumDom;

	}

	@Override
	public NumbersDomain update(NumbersDomain NumDom) throws Exception {

		//	データベースと連携し出たが登録できたか確認する 1になっていれば成功とみなそう
		int stmtEXECount = 0;

		//		dsにはデータソースが入っているので問題ない
		try (Connection con = ds.getConnection()) {
			System.out.println("insert１");

			String sql = "UPDATE `numbers` SET "
					+ "`name_id` = ?,"
					+ " `name_URL` = ?,"
					+ " `action_id` = ?,"
					+ " `action_URL` = ?,"
					+ " `memory_Level` = ? "
					+ "WHERE (`user_id` = ?) and (`list_number` = ?);";

			PreparedStatement stmt = con.prepareStatement(sql);


			stmt.setInt(6, NumDom.getUser_id());
			stmt.setInt(7, NumDom.getList_number());
			stmt.setInt(1, NumDom.getName().getId());
			stmt.setString(2, NumDom.getName().getURL());
			stmt.setInt(3, NumDom.getAction().getId());
			stmt.setString(4,NumDom.getAction().getURL());
			stmt.setInt(5, NumDom.getMemory_Level());


			stmtEXECount = stmt.executeUpdate();

			System.out.println("insert2");
		} catch (Exception e) {
			System.out.println("insert3");
			throw new ServletException(e);
		}

		if (stmtEXECount != 1) {
			System.out.println("numbersの更新に失敗しました。");
		}else {
			System.out.println("numbersの更新に成功しました。");
		}

		return NumDom;

	}

	@Override
	public boolean delete(int userid, int number) throws Exception {

		//	データベースと連携し出たが登録できたか確認する 1になっていれば成功とみなそう
		int stmtEXECount = 0;

		//		dsにはデータソースが入っているので問題ない
		try (Connection con = ds.getConnection()) {

			String sql = "DELETE FROM `numbers`"
					+ " WHERE (`user_id` = ?) and (`list_number` = ?);";

			PreparedStatement stmt = con.prepareStatement(sql);


			stmt.setInt(1,userid);
			stmt.setInt(2, number);

			stmtEXECount = stmt.executeUpdate();

		} catch (Exception e) {
			throw new ServletException(e);
		}

		if (stmtEXECount != 1) {
			System.out.println("numbersの削除に失敗しました。");
		}else {
			System.out.println("numbersの削除に成功しました。");
		}

		return stmtEXECount == 1;
	}

	@Override
	public boolean hasNumber(int userid, int number) throws Exception {


//		System.out.println("検索中");

		try (Connection con = ds.getConnection()) {

			String sql = "SELECT * FROM numbers where user_id = ? and list_number = ? ;";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, userid);
			stmt.setInt(2, number);


//			System.out.println("検索中1");
			ResultSet rs = stmt.executeQuery();
//			System.out.println("検索中2");

			return rs.next();

		} catch (Exception e) {
//			System.out.println("サーバー接続に失敗しました");
			throw e;
		}

	}

	@Override
	public boolean hasNumber(int userid, int number,Connection con) throws Exception {


		System.out.println("NunbersDaohasNumberCON検索中1");
//
//		try (Connection con = ds.getConnection()) {

			String sql = "SELECT * FROM numbers where user_id = ? and list_number = ? ;";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, userid);
			stmt.setInt(2, number);

			System.out.println("NunbersDaohasNumberCON検索中2");

//			System.out.println("検索中1");
			ResultSet rs = stmt.executeQuery();
//			System.out.println("検索中2");

			System.out.println("NunbersDaohasNumberCON検索中3");
			return rs.next();

//		} catch (Exception e) {
////			System.out.println("サーバー接続に失敗しました");
//			throw e;
//		}

	}





	@Override
	public NumbersDomain updateOrInsert(NumbersDomain NumDom) throws Exception {


		// TODO 自動生成されたメソッド・スタブ

		NumbersDomain ND = new NumbersDomain(NumDom.getUser_id(), NumDom.getList_number());


//		nameID ・ActionIDがある場合は、検索名前で行くことにした。
//		ない場合は、nameで検索する
//		ない場合は、そのままで行く
//		URLは、ID名前があったときに設定する。

//		アクションとネームで同様の処理をする


//		ネームデータの登録
		var nameDao = DaoFactory.createNameList();
		NameListDomain NLD = nameDao.findByName(NumDom.getName().getName());
		if(NLD.getStrength() == -1) {
//			データが存在しないので製造する
			NLD = nameDao.insertorUpdate(NumDom.getName().getName(), NumDom.getName().getURL());
		}


		NLD.setURL(NumDom.getName().getURL());
		ND.setName(NLD);



//		アクションデータの登録
		var actionDao = DaoFactory.createActionList();
		ActionListDomain ALD = actionDao.findByName(NumDom.getAction().getName());
		if(ALD.getStrength() == -1) {
//			データが存在しないので製造する
			ALD = actionDao.insertorUpdate(NumDom.getAction().getName(), NumDom.getAction().getURL());
		}
		ALD.setURL(NumDom.getAction().getURL());

		ND.setAction(ALD);



//		事故リストに存在するかをチェックして
//		var findMemory = findByNumber(NumDom.getUser_id(), NumDom.getList_number());

		var findMemory = hasNumber(NumDom.getUser_id(), NumDom.getList_number());

//		記憶強度を獲得する
//		insertかupdateを呼ぶ
		if(!findMemory){
			ND.setMemory_Level(0);

			ND = insert(ND);


		}else {
			ND.setMemory_Level(findByNumber(NumDom.getUser_id(), NumDom.getList_number()).getMemory_Level());
			ND = update(ND);
		}

		return ND;
	}

	@Override
	public NumbersDomain updateOrInsert(NumberAndHuman user,int userID) throws Exception {


		NameListDomain NLD = new NameListDomain(user.getNameId(), user.getName(), user.getNameURL(),1);
		ActionListDomain ALD = new ActionListDomain(user.getActionId(), user.getAction(), user.getActionURL(),1);


//		updateOrInsert(NumberAndHuman user)にデータをパースしてその結果を返す。
		NumbersDomain ND = new NumbersDomain(userID,user.getNumber() , NLD, ALD,-1);
//		これだと強度が上書きされてしまうので、項目があるかどうかの確認が必要。
//		対応済みから－１でOK


		return updateOrInsert(ND);
	}

}
