package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.sql.DataSource;

import org.mindrot.jbcrypt.BCrypt;

import domain.LoginData;
import domain.UserCreateData;

public class UserDaoImpl implements UserDao {

	//	フィールド
	private DataSource ds;

	//	ファクトリーからデータソースを受け取るのに必要
	public UserDaoImpl(DataSource ds) {
		this.ds = ds;
		System.out.println(ds);

	}

	/**
	 * ユーザーIDは、固定で99を返すので、修正するか、検索しなおすこと。
	 */
	@Override
	public LoginData insert(UserCreateData user) throws Exception {
		LoginData logindata = new LoginData("", 99, false);
		var nameOnly = findByName(user.getName());
		if(nameOnly.isLoginState()) {
			System.out.println("同一名が存在するのでここで制作しません。");
			return logindata;
		}



		//	データベースと連携し出たが登録できたか確認する 1になっていれば成功とみなそう
		int stmtEXECount = 0;

		//		dsにはデータソースが入っているので問題ない
		try (Connection con = ds.getConnection()) {
			System.out.println("insert１");
//			String sql = "INSERT INTO users "
//					+ "(name,password)"
//					+ " values"
//					+ " (?,?);";



			String sql = "INSERT INTO `users` (`name`, `pass`) VALUES (?, ?);";

			System.out.println("insert1.4");
			PreparedStatement stmt = con.prepareStatement(sql);

			//			ここで、パスワードをハッシュ化して保存する
			System.out.println("insert1.5");
			System.out.println(user.getName());
//			String kari = "12345685";
			stmt.setString(1, user.getName());
//			stmt.setString(1, kari);



			System.out.println("insert1.6");
			String hashedPass = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
			System.out.println("insert1.7");
			stmt.setString(2, hashedPass);
			System.out.println("insert1.8");

			stmtEXECount = stmt.executeUpdate();

			System.out.println("insert2");
		} catch (Exception e) {
			System.out.println("insert3");
			throw new ServletException(e);
		}

		System.out.println(user.getName());
		System.out.println(user.getPassword());

		if (stmtEXECount != 1) {
			System.out.println("ユーザー制作に失敗しました。");
		}else {

			logindata = new LoginData(user.getName(), 99, true);
			System.out.println("ユーザー制作に成功しました。");
		}

		return logindata;
	}

	@Override
	public LoginData findByName(String name) throws Exception {

		LoginData loginData = new LoginData("", 99, false);

		System.out.println("検索中");
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT * FROM users where name=?;";
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
				var dbstate = rs.getString("state");
				boolean state ="act".equals(dbstate);
				System.out.println(dbname+"id:"+dbid+"state:"+dbstate +state);
				loginData = new LoginData(dbname, dbid, state);
				System.out.println("検索結果のデータ化が完了");

			}

		} catch (Exception e) {
			System.out.println("サーバー接続に失敗しました");
			throw e;
		}

		return loginData;
	}

	@Override
	public LoginData findById(int userId) throws Exception {

		LoginData loginData = new LoginData("", 99, false);

		try (Connection con = ds.getConnection()) {
			String sql = "select * from users"
					+ " where id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();
			//			rs.nextでレコードがあるかどうかを確認して、IDがあるかどうかを確認する
			if (rs.next()) {
				var dbname = rs.getString("name");
				int dbid = (Integer) rs.getObject("id");
				var dbstate = rs.getString("state");
				boolean state = (dbstate == "ACT");
				loginData = new LoginData(dbname, dbid, state);

			}

		} catch (Exception e) {
			System.out.println("サーバー接続に失敗しました");
			throw e;
		}

		return loginData;
	}

	/**
	 * パスワードとIDでログイン
	 */
	@Override
	public LoginData findByLoginIdAndLoginPass(String loginName, String loginPass) throws Exception {

		//		loginNameから、IDを調べる
		var loginData = findByName(loginName);

		//		データの有効を確認する
		if (loginData.isLoginState()) {
			//コピペ上
			try (Connection con = ds.getConnection()) {
				String sql = "select * from users"
						+ " where id=?";
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, loginData.getUserId());
				ResultSet rs = stmt.executeQuery();

				if (rs.next()) {
					//				パスワードの確認をする
					//				ハッシュ化されているので特殊な確認をする
					/**
					 * データベースにあるハッシュ化されたパスワード
					 */
					String hashedPass = rs.getString("pass");

					if (BCrypt.checkpw(loginPass, hashedPass)) {
						//					パスワードが正しいのでloginDataをそのまま通す
						System.out.println("パスワードチェックに成功しました");

					} else {
						System.out.println("パスワードが正しくないです");
						loginData = new LoginData("", 99, false);
					}

				}
			} catch (Exception e) {
				throw e;
			}

		} else {
			System.out.println("ユーザーデータが存在しない");
		}

		return loginData;
	}

	@Override
	public boolean findName(String name) throws Exception {

		var loginData =  findByName(name);

		return loginData.isLoginState();
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
