package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.sql.DataSource;

import domain.ActionListDomain;

public class ActionListDaoImpl implements ActionListDao {

	//	フィールド
	private DataSource ds;

	//	ファクトリーからデータソースを受け取るのに必要
	public ActionListDaoImpl(DataSource ds) {
		this.ds = ds;
		System.out.println(ds);

	}

	/**
	 * ユーザーIDは、固定で99を返すので、修正するか、検索しなおすこと。
	 * まずはファインド系から作る　ここは後で作ります
	 * アップデートとインサートの切り替えを行うメソッドが別に必要な気がするので、
	 * InsertofSetを別に作ろう
	 * そうすること前提に、ここではインサートだけ作る
	 */
	@Override
	public ActionListDomain insert(String name,String URL) throws Exception {

		ActionListDomain ActionListDomai = new ActionListDomain();
		var nameOnly = findByName(name);
		if(nameOnly.getStrength()!= -1) {
			System.out.println("同一名が存在するのでここで制作しません。");
			return ActionListDomai;
		}

		//	データベースと連携し出たが登録できたか確認する 1になっていれば成功とみなそう
		int stmtEXECount = 0;

		//		dsにはデータソースが入っているので問題ない
		try (Connection con = ds.getConnection()) {
			System.out.println("insert１");

			String sql = "INSERT INTO `actionlist` (`name`, `URL`, `Strength`) VALUES ( ?, ?, '1');";

			PreparedStatement stmt = con.prepareStatement(sql);


			stmt.setString(1, name);
			stmt.setString(2, URL);


			stmtEXECount = stmt.executeUpdate();

			System.out.println("insert2");
		} catch (Exception e) {
			System.out.println("insert3");
			throw new ServletException(e);
		}

		ActionListDomai = findByName(name) ;
		if (ActionListDomai.getStrength() == -1) {
			System.out.println("ユーザー制作に失敗しました。");
		}else {
			System.out.println("ユーザー制作に成功しました。");
		}

		return ActionListDomai;
	}

	@Override
	public ActionListDomain findByName(String name) throws Exception {

		ActionListDomain ActionListDomai = new ActionListDomain();

		System.out.println("検索中");

		try (Connection con = ds.getConnection()) {

			String sql = "SELECT * FROM actionlist where name = ?;";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, name);
			System.out.println("検索中1");
			ResultSet rs = stmt.executeQuery();
			System.out.println("検索中2");
			//			rs.nextでレコードがあるかどうかを確認して、IDがあるかどうかを確認する
			//			rs.next()は真偽を返すので、条件式にそのまま使える
			if (rs.next()) {
				System.out.println("検索結果が帰ってきています");
				var dbname = rs.getString("name");
				int dbid = (Integer) rs.getObject("id");
				var dbURL = rs.getString("URL");
				var dbStrength = (Integer)rs.getObject("Strength");

				System.out.println(dbname+"id:"+dbid+"state:"+dbURL +"強度" + dbStrength);
				ActionListDomai = new ActionListDomain(dbid, dbname, dbURL, dbStrength) ;
				System.out.println("検索結果のデータ化が完了");
			}else {
				System.out.println("データが存在しませんでした。");
			}

		} catch (Exception e) {
			System.out.println("サーバー接続に失敗しました");
			throw e;
		}

		return ActionListDomai;
	}

	@Override
	public ActionListDomain findById(int id) throws Exception {

		ActionListDomain ActionListDomai = new ActionListDomain();

		System.out.println("検索中");

		try (Connection con = ds.getConnection()) {

			String sql = "SELECT * FROM actionlist where id = ?;";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			System.out.println("検索中1");
			ResultSet rs = stmt.executeQuery();
			System.out.println("検索中2");
			//			rs.nextでレコードがあるかどうかを確認して、IDがあるかどうかを確認する
			//			rs.next()は真偽を返すので、条件式にそのまま使える
			if (rs.next()) {
				System.out.println("検索結果が帰ってきています");
				var dbname = rs.getString("name");
				int dbid = (Integer) rs.getObject("id");
				var dbURL = rs.getString("URL");
				var dbStrength = (Integer)rs.getObject("Strength");

				System.out.println(dbname+"id:"+dbid+"state:"+dbURL +"強度" + dbStrength);
				ActionListDomai = new ActionListDomain(dbid, dbname, dbURL, dbStrength) ;
				System.out.println("検索結果のデータ化が完了");
			}else {
				System.out.println("データが存在しませんでした。");
			}

		} catch (Exception e) {
			System.out.println("サーバー接続に失敗しました");
			throw e;
		}

		return ActionListDomai;
	}


	@Override
	public ActionListDomain findById(int id,Connection con) throws Exception {

		ActionListDomain ActionListDomai = new ActionListDomain();

		System.out.println("検索中");

		try {

			String sql = "SELECT * FROM actionlist where id = ?;";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			System.out.println("検索中1");
			ResultSet rs = stmt.executeQuery();
			System.out.println("検索中2");
			//			rs.nextでレコードがあるかどうかを確認して、IDがあるかどうかを確認する
			//			rs.next()は真偽を返すので、条件式にそのまま使える
			if (rs.next()) {
				System.out.println("検索結果が帰ってきています");
				var dbname = rs.getString("name");
				int dbid = (Integer) rs.getObject("id");
				var dbURL = rs.getString("URL");
				var dbStrength = (Integer)rs.getObject("Strength");

				System.out.println(dbname+"id:"+dbid+"state:"+dbURL +"強度" + dbStrength);
				ActionListDomai = new ActionListDomain(dbid, dbname, dbURL, dbStrength) ;
				System.out.println("検索結果のデータ化が完了");
			}else {
				System.out.println("データが存在しませんでした。");
			}

		} catch (Exception e) {
			System.out.println("サーバー接続に失敗しました");
			throw e;
		}

		return ActionListDomai;
	}

	@Override
	public ActionListDomain update(ActionListDomain domain) throws Exception {


		//	データベースと連携し出たが登録できたか確認する 1になっていれば成功とみなそう
		int stmtEXECount = 0;

		//		dsにはデータソースが入っているので問題ない
		try (Connection con = ds.getConnection()) {
			System.out.println("update１");

			String sql = "UPDATE `actionlist` SET `URL` = ?, `Strength` = ? WHERE (`id` = ?);";

			PreparedStatement stmt = con.prepareStatement(sql);


			stmt.setString(1, domain.getURL());
			stmt.setInt(2, domain.getStrength());
			stmt.setInt(3, domain.getId());


			stmtEXECount = stmt.executeUpdate();

			System.out.println("update2");
		} catch (Exception e) {
			System.out.println("update3");
			throw new ServletException(e);
		}


		if (stmtEXECount != 1) {
			System.out.println("アップデートに失敗しました。");
			return new ActionListDomain();
		}else {
			System.out.println("アップデートに成功しました。");
		}


		return domain;
	}

	/**細々とした操作をせずに設定できるセットメソッド
	 *
	 */
	@Override
	public ActionListDomain insertorUpdate(String name, String URL) throws Exception {

		ActionListDomain ActionListDomain = findByName(name);

		if(ActionListDomain.getStrength() == -1) {
//			インサート処理
			ActionListDomain = insert(name, URL);

		}else {
//			アップデート処理
			ActionListDomain = update(ActionListDomain);
		}

		return ActionListDomain;
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
