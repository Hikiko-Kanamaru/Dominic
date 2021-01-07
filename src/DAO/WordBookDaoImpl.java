package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.sql.DataSource;

import domain.WordBookDomain;
import domain.WordJoinDomain;

public class WordBookDaoImpl implements WordBookDao {

	//	フィールド
	private DataSource ds;

	//	ファクトリーからデータソースを受け取るのに必要
	public WordBookDaoImpl(DataSource ds) {
		this.ds = ds;
		System.out.println(ds);

	}

	@Override
	public WordBookDomain findByIdAndUserId(int id, int userId) throws Exception {
		var inDomain = new WordBookDomain(false);

		try (Connection con = ds.getConnection()) {

			String sql = "SELECT * FROM word_book where id = ? and user_id = ?;";

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, id);
			stmt.setInt(2, userId);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				System.out.println(" WordBook検索結果が帰ってきています");
				int dbid = (Integer) rs.getObject("id");
				var dbuserid = (Integer) rs.getObject("user_id");
				var dbmemoryLevel = (Integer) rs.getObject("memory_Level");
				var dbtext = rs.getString("text");

				System.out.println(
						" id:" + dbid + " userId:" + dbuserid + "dbmemoryLevel:" + dbmemoryLevel + " text:" + dbtext);
				inDomain = new WordBookDomain(dbid, dbuserid, dbmemoryLevel, dbtext);
				System.out.println("WordBook検索結果のデータ化が完了");
			} else {
				System.out.println("WordBookデータが存在しませんでした。");
			}

		} catch (Exception e) {
			System.out.println("WordBookでサーバーエラー");
			throw new ServletException(e);
		}

		return inDomain;
	}


	@Override
	public WordBookDomain findByIdAndUserId(int id, int userId,Connection con) throws Exception {
		var inDomain = new WordBookDomain(false);

		try {

			String sql = "SELECT * FROM word_book where id = ? and user_id = ?;";

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, id);
			stmt.setInt(2, userId);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				System.out.println(" WordBook検索結果が帰ってきています");
				int dbid = (Integer) rs.getObject("id");
				var dbuserid = (Integer) rs.getObject("user_id");
				var dbmemoryLevel = (Integer) rs.getObject("memory_Level");
				var dbtext = rs.getString("text");

				System.out.println(
						" id:" + dbid + " userId:" + dbuserid + "dbmemoryLevel:" + dbmemoryLevel + " text:" + dbtext);
				inDomain = new WordBookDomain(dbid, dbuserid, dbmemoryLevel, dbtext);
				System.out.println("WordBook検索結果のデータ化が完了");
			} else {
				System.out.println("WordBookデータが存在しませんでした。");
			}

		} catch (Exception e) {
			System.out.println("WordBookでサーバーエラー");
			throw new ServletException(e);
		}

		return inDomain;
	}

	/**
	 * ワードIDで検索し
	 * user_idがkeyのmapが得られる
	 * pram:int :id
	 * returns:
	 * key:user_id
	 * value:WordBookDomain
	 */
	@Override
	public Map<Integer, WordBookDomain> findByid(int id) throws Exception {
		Map<Integer, WordBookDomain> map = new HashMap<>();

		var inDomain = new WordBookDomain(false);

		try (Connection con = ds.getConnection()) {

			String sql = "SELECT * FROM word_book where id = ?;";

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				System.out.println(" WordBook検索結果が帰ってきています");
				int dbid = (Integer) rs.getObject("id");
				var dbuserid = (Integer) rs.getObject("user_id");
				var dbmemoryLevel = (Integer) rs.getObject("memory_Level");
				var dbtext = rs.getString("text");

				System.out.println(
						" id:" + dbid + " userId:" + dbuserid + "dbmemoryLevel:" + dbmemoryLevel + " text:" + dbtext);
				inDomain = new WordBookDomain(dbid, dbuserid, dbmemoryLevel, dbtext);

				map.put(inDomain.getUserID(), inDomain);
				System.out.println("検索結果のデータ化が完了");
			}
			if (map.size() == 0) {
				System.out.println("WordBook検索結果は0です。");
			}

		} catch (Exception e) {
			System.out.println("WordBookでサーバーエラー");
			throw new ServletException(e);
		}

		return map;
	}


	/**
	 * ユーザーIDで検索し
	 * idがkeyのmapが得られる
	 * pram:int :user_id
	 * returns:
	 * 	key:id
	 * 	value:WordBookDomain
	 */
	@Override
	public Map<Integer, WordBookDomain> findByUserID(int userId) throws Exception {
		Map<Integer, WordBookDomain> map = new HashMap<>();

		var inDomain = new WordBookDomain(false);

		try (Connection con = ds.getConnection()) {

			String sql = "SELECT * FROM word_book where user_id = ?;";

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, userId);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				System.out.println(" WordBook検索結果が帰ってきています");
				int dbid = (Integer) rs.getObject("id");
				var dbuserid = (Integer) rs.getObject("user_id");
				var dbmemoryLevel = (Integer) rs.getObject("memory_Level");
				var dbtext = rs.getString("text");

				System.out.println(
						" id:" + dbid + " userId:" + dbuserid + "dbmemoryLevel:" + dbmemoryLevel + " text:" + dbtext);
				inDomain = new WordBookDomain(dbid, dbuserid, dbmemoryLevel, dbtext);

				map.put(inDomain.getID(), inDomain);
				System.out.println("検索結果のデータ化が完了");
			}
			if (map.size() == 0) {
				System.out.println("WordBook検索結果は0です。");
			}

		} catch (Exception e) {
			System.out.println("WordBookでサーバーエラー");
			throw new ServletException(e);
		}

		return map;
	}



	/**
	 * idは使用しない。それ以外を項目として利用し登録する。
	 * finedメソッドのidを利用せずnametextmeamから検索するものが必要-＞済
	 */
	@Override
	public WordBookDomain insert(WordBookDomain domain) throws Exception {

		var inDomain = new WordBookDomain(false);

		//	データベースと連携し出たが登録できたか確認する 1になっていれば成功とみなそう
		int stmtEXECount = 0;

		//		dsにはデータソースが入っているので問題ない
		try (Connection con = ds.getConnection()) {
			System.out.println("insert１");

			String sql = "INSERT INTO `word_book` (`id`, `user_id`, `memory_Level`, `text`) VALUES (?, ?, ?, ?);";

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, domain.getID());
			stmt.setInt(2, domain.getUserID());
			stmt.setInt(3, domain.getMemoryLevel());
			System.out.println("WordbookDomain insert1.7 get.text");
			System.out.println(domain.getText());

			stmt.setString(4, domain.getText());

			stmtEXECount = stmt.executeUpdate();

			System.out.println("insert2");
		} catch (Exception e) {
			System.out.println("insert3");
			throw new ServletException(e);
		}

		inDomain = findByIdAndUserId(domain.getID(), domain.getUserID());
		if (inDomain.getUserID() == -1) {
			System.out.println("WordBookinsertに失敗しました。");
		} else {
			System.out.println("WordBookに成功しました。");
		}

		return inDomain;
	}

	/**
	 * idとuser_idを指定して、上書きする機能
	 * idとuser_idは変更不可。検索に利用するため。必要なら引数が2つのものを実装する
	 * 安全安全性のチェックは、別のところでやる
	 * imemory_Levelとtextの上書き、中身を確認してから与えること
	 */
	@Override
	public 	WordBookDomain update(WordBookDomain domain) throws Exception{

		var inDomain = new WordBookDomain(false);
		//	データベースと連携し出たが登録できたか確認する 1になっていれば成功とみなそう
		int stmtEXECount = 0;

		try (Connection con = ds.getConnection()) {

			String sql = "UPDATE `word_book` SET `memory_Level` = ?, `text` = ? WHERE (`id` = ? ) and (`user_id` = ? );";
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, domain.getMemoryLevel());
			stmt.setString(2, domain.getText());
			stmt.setInt(3, domain.getID());
			stmt.setInt(4, domain.getUserID());

			stmtEXECount = stmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("サーバー接続に失敗しました");
			throw e;
		}

		if (stmtEXECount == 0) {
			System.out.println("WordBookのUPDATEに失敗しました。");
		} else {
			inDomain = findByIdAndUserId(domain.getID(), domain.getUserID());
			System.out.println("WordBookのUPDATEに成功しました。");
		}

		return inDomain;

	}



	/**細々とした操作をせずに設定できるセットメソッド
	 * memoryLevelが－１ならインサート
	 *idがあればアップデートする
	 */
	@Override
	public WordBookDomain insertorUpdate(WordBookDomain domain) throws Exception{

		WordBookDomain inDomain;

		if (domain.getMemoryLevel() == -1) {
			//			インサート処理
			domain.setMemoryLevel(1);
			inDomain = insert(domain);

		} else {
			//			アップデート処理
			inDomain = update(domain);
		}

		return inDomain;
	}

	@Override
	public boolean delete(WordBookDomain id) throws Exception {
		int stmtEXECount = 0;

		try (Connection con = ds.getConnection()) {
			//				削除する方法
			String sql1 = "DELETE FROM `word_book` WHERE (`id` = ?) and (`user_id` = ?);";

			PreparedStatement stmt = con.prepareStatement(sql1);

			stmt.setInt(1, id.getID());
			stmt.setInt(2, id.getUserID());
			//			sql文を実行する
			stmtEXECount = stmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		}

		if (stmtEXECount == 0) {
			System.out.println("WordBook削除に失敗したようです");
		} else {
			System.out.println("WordBook削除に成功したようです");
		}


		return stmtEXECount != 0;
	}


	/**joinされたリストを返す
	 * wordBookだけではなくwordProfにもアクセスする
	 *
	 */
	@Override
	public List<WordJoinDomain> findWordJoinByUserID(int userId) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

		List<WordJoinDomain> list = new ArrayList<>();

		var inDomain = new WordJoinDomain(false);

		try (Connection con = ds.getConnection()) {

			String sql = "SELECT "
					+ " word_book.id,"
					+ " word_book.memory_Level,"
					+ " word_book.text,"
					+ " word_profile.word,"
					+ " word_profile.value"
					+ " FROM  word_book"
					+ " join word_profile"
					+ " on word_book.id = word_profile.id"
					+ " where word_book.user_id = ?"
					+ " order by word asc "
					+ " ; ";

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, userId);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				System.out.println(" WordBookJoin検索結果が帰ってきています");
				inDomain = mapToWJD(rs);


				System.out.println(
						" id:" + inDomain.getId()
						+ " userText:" + inDomain.getText()
						+ " word:" + inDomain.getWord()
						+ " Value:" + inDomain.getValue());
				list.add(inDomain);
				System.out.println("WordBookJoin検索結果のデータ化が完了");
			}
			if (list.size() == 0) {
				System.out.println("WordBookJoin検索結果は0です。");
			}

		} catch (Exception e) {
			System.out.println("WordBookJoinでサーバーエラー");
			throw new ServletException(e);
		}

		return list;

	}


	/**joinされたリストを返す
	 * wordBookだけではなくwordProfにもアクセスする
	 *Mono用一つのデータだけを返す
	 */
	@Override
	public WordJoinDomain findWordJoinByUserIDandProfID(int userId,int profId) throws Exception {
		// TODO 自動生成されたメソッド・スタブ



		var inDomain = new WordJoinDomain(false);

		try (Connection con = ds.getConnection()) {

			String sql = "SELECT "
					+ " word_book.id,"
					+ " word_book.memory_Level,"
					+ " word_book.text,"
					+ " word_profile.word,"
					+ " word_profile.value"
					+ " FROM word_book"
					+ " join word_profile"
					+ " on word_book.id = word_profile.id"
					+ " where word_book.user_id = ?"
					+ " and word_book.id = ? "
					+ " ; ";

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, userId);
			stmt.setInt(2, profId);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				System.out.println(" WordBookJoin検索結果が帰ってきています");
				inDomain = mapToWJD(rs);


				System.out.println(
						" id:" + inDomain.getId()
						+ " userText:" + inDomain.getText()
						+ " word:" + inDomain.getWord()
						+ " Value:" + inDomain.getValue());

				System.out.println("WordBookJoin検索結果のデータ化が完了");
			}
			if (inDomain.getId() == -1) {
				System.out.println("WordBookJoinMono検索結果は0です。");
			}

		} catch (Exception e) {
			System.out.println("WordBookJoinでサーバーエラー");
			throw new ServletException(e);
		}

		return inDomain;

	}





	private WordJoinDomain mapToWJD(ResultSet rs) throws SQLException {
				//		まずリザルトセットの各カラムからデータを取り出していく
		WordJoinDomain wordJoin = new WordJoinDomain();

		var tmpID = ((Integer) rs.getObject("id")) ;
		var tmpML = (Integer) rs.getObject("memory_Level");
		var tmpText =  rs.getString("text");
		var tmpword = rs.getString("word");
		var tmpvalue =  (Integer) rs.getObject("value");


		if (!Objects.isNull(tmpID))wordJoin.setId(tmpID);
		if (!Objects.isNull(tmpML))wordJoin.setMemoryLevel(tmpML);
		if (!Objects.isNull(tmpText))wordJoin.setText(tmpText);
		if (!Objects.isNull(tmpword))wordJoin.setWord(tmpword);
		if (!Objects.isNull(tmpvalue))wordJoin.setValue(tmpvalue);

				return wordJoin;
			}





	//	/***すべてのユーザーデータの取得
	//	 *
	//	 */
	//	@Override
	//	public List<User> findAll() throws Exception {
	//		//		全ユーザーデータの取得
	//		List<User> users = new ArrayList<>();
	//
	//		//		ファイルクローズ構文
	//		try (Connection con = ds.getConnection()) {
	//			//			sql文を用意
	//			//			String sql = "SELECT * FROM users";
	//			//			SQl文の内容を変更する
	//			String sql = "SELECT "
	//					+ " users.id,"
	//					+ " users.name,"
	//					+ " users.email,"
	//					+ " users.birthday,"
	//					+ " users.status,"
	//					+ " users.description,"
	//					+ " users.type_id,"
	//					+ " user_types.name as userType"
	//					+ " FROM users "
	//					+ " JOIN user_types"
	//					+ " ON users.type_id = user_types.id"
	//					+ " WHERE users.status = ?";
	//			//			実行準備段階にする
	//			PreparedStatement stmt = con.prepareStatement(sql);
	//			stmt.setString(1, "ACT");
	//			//			SQL文を実行する
	//			ResultSet rs = stmt.executeQuery();
	//
	//			//			UserDataにマッピングする
	//			//			マッピング処理はよく使われるので、別のメソッドに分離する。
	//			//			rs.next()で一つのレコードをもらえる
	//			while (rs.next()) {
	//				users.add(mapToUser(rs));
	//			}
	//
	//		} catch (Exception e) {
	//			throw e;
	//		}
	//
	//		/*仮データ
	//		User u1 = new User();		u1.setId(1);u1.setName("山田太郎");u1.setEmail("te@tea.com");User u2 = new User();u2.setId(12);u2.setName("太郎竹中");u2.setEmail("gfs@tfa.com");	User u3 = new User();u3.setId(187);u3.setName("ここ氏ら");u3.setEmail("mikoko@t756.com");users.add(u1);users.add(u2);users.add(u3);
	//		 */
	//
	//		return users;
	//	}
	//
	//	/**
	//	 * idをもとにレコードを取得する。
	//	 * whereを伴うセレクト文を使っていく
	//	 * 値が存在しない場合の対処もする
	//	 */
	//	@Override
	//	public User findById(Integer id) throws Exception {
	//		User user = null;
	//
	//		//		con接続をつくる
	//		try (Connection con = ds.getConnection()) {
	//			//			sqlを作る
	//			//			このやり方だとマッピング効率が悪いので変更する
	//			//			String sql = "SELECT * FROM users WHERE id = ?";
	//			String sql = "SELECT"
	//					+ " users.id,"
	//					+ " users.name,"
	//					+ " users.email,"
	//					+ " users.birthday,"
	//					+ " users.status,"
	//					+ " users.description,"
	//					+ " users.type_id,"
	//					+ " user_types.name as userType"
	//					+ " FROM users"
	//					+ " JOIN user_types "
	//					+ " ON users.type_id = user_types.id"
	//					+ " WHERE users.id = ?";
	//			//			プリぺーあどステートメントを作る
	//			PreparedStatement stmt = con.prepareStatement(sql);
	//			//			プレースホルダを埋める
	//			stmt.setObject(1, id, Types.INTEGER);
	//			ResultSet rs = stmt.executeQuery();
	//
	//			if (rs.next() == true) {
	//				//				該当データあり
	//				//				userにデータをまとめる　＝マッピング
	//				user = mapToUser(rs);
	//			}
	//
	//		} catch (Exception e) {
	//			throw e;
	//		}
	//
	//		return user;
	//	}
	//
	//	@Override
	//	public void insert(User user) throws Exception {
	//
	//		//		dsにはデータソースが入っているので問題ない
	//		try (Connection con = ds.getConnection()) {
	//			String sql = "INSERT INTO users "
	//					+ "(name, email, type_id, birthday,password)"
	//					+ " values"
	//					+ "(?,?,?,?,?);";
	//			PreparedStatement stmt = con.prepareStatement(sql);
	//			stmt.setString(1, user.getName());
	//			stmt.setString(2, user.getEmail());
	//			stmt.setObject(3, user.getTypeId(), Types.INTEGER);
	//			//			java.Util.dateからjava.sql.dateに変更する。
	//			java.sql.Date sqlDate = new java.sql.Date(user.getBirthday().getTime());
	//			stmt.setDate(4, sqlDate);
	//
	//			//			ここで、パスワードをハッシュ化して保存する
	//			String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
	//			System.out.println(hashed);
	//			stmt.setString(5, hashed);
	//
	//			stmt.executeUpdate();
	//
	//		} catch (Exception e) {
	//			throw new ServletException(e);
	//		}
	//
	//	}
	//
	//	@Override
	//	public void update(User user) throws Exception {
	//		// TODO 自動生成されたメソッド・スタブ
	//
	//		try	(Connection con =  ds.getConnection()){
	//			String sql = "UPDATE users SET"
	//					+ " name = ?,"
	//					+ " email = ?,"
	//					+ " type_id = ? ,"
	//					+ " birthday = ?"
	//					+ " where id = ? ";
	//
	////			insertコピペ
	//			PreparedStatement stmt = con.prepareStatement(sql);
	//			stmt.setString(1, user.getName());
	//			stmt.setString(2, user.getEmail());
	//			stmt.setObject(3, user.getTypeId(), Types.INTEGER);
	//			//			java.Util.dateからjava.sql.dateに変更する。
	//			java.sql.Date sqlDate = new java.sql.Date(user.getBirthday().getTime());
	//			stmt.setDate(4, sqlDate);
	//
	//			stmt.setObject(5, user.getId(), Types.INTEGER);
	//
	//			stmt.executeUpdate();
	////			insertコピペ
	//
	//
	//
	//		}catch (Exception e) {
	//			throw e;
	//		}
	//
	//
	//
	//
	//
	//	}
	//
	//	@Override
	//	public void delete(Integer id) throws Exception {
	//
	////接続を作る
	//			try(Connection con = ds.getConnection()){
	////				削除する方法
	//			String sql1  = "DELETE FROM users"
	//					+ " WHERE ID = ?";
	////			削除情報を書き込む方法
	//			String sql2 = "UPDATE users SET"
	//					+ " status = ?"
	//					+ " WHERE id = ?";
	//
	//			PreparedStatement stmt =
	//					con.prepareStatement(sql2);
	//
	//			stmt.setString(1, "DEL");
	//			stmt.setObject(2, id, Types.INTEGER);
	////			sql文を実行する
	//			stmt.executeUpdate();
	//
	//			}catch (Exception e) {
	//				throw e;
	//			}
	//
	//	}
	//
	//	/**ResultSetからUserDataへマッピング変換
	//	 * usersテーブルから取得したデータを入力して下さい。
	//	 * @throws SQLException
	//	 * 例外処理をResultSetを投げる方に責任を負わせる。
	//	 */
	//	private User mapToUser(ResultSet rs) throws SQLException {
	//		//		まずリザルトセットの各カラムからデータを取り出していく
	//
	//		//		System.out.println("ユーザーの生成をします。");
	//		User user = new User(
	//				(Integer) rs.getObject("id"),
	//				rs.getString("name"),
	//				rs.getString("email"),
	//				(Integer) rs.getObject("type_ID"),
	//				rs.getTimestamp("birthday"),
	//				rs.getString("status"),
	//				rs.getString("description"),
	//				rs.getString("userType")//userType
	//		);
	//
	//		/*このやり方をせずにSQL文を編集して、設定を変える
	//		if (user.getTypeId() == 2) {
	//			user.setUserType("課金ユーザー");
	//		} else {
	//			user.setUserType("一般ユーザー");
	//		}
	//		*/
	//
	//		/*
	//		 //		まずリザルトセットの各カラムからデータを取り出していく
	//		 		Integer id = (Integer) rs.getObject("id");
	//				String name = rs.getString("name");
	//				String email = rs.getString("email");
	//				Integer typeId = (Integer) rs.getObject("type_id");
	//				Date birthday = rs.getTimestamp("birthday");
	//		// 一つのユーザーにデータをまとめていく。
	//		 User user = new User();
	//		 user.setId(id);
	//		 user.setName(name);
	//		 user.setEmail(email);
	//		 user.setTypeId(typeId);
	//		 user.setBirthday(birthday)
	//		 */
	//
	//		return user;
	//	}

}
