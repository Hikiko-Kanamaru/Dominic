package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.sql.DataSource;

import domain.WordProfileDomain;

public class WordProfileDaoImpl implements WordProfileDao {

	//	フィールド
	private DataSource ds;

	//	ファクトリーからデータソースを受け取るのに必要
	public WordProfileDaoImpl(DataSource ds) {
		this.ds = ds;
		System.out.println(ds);

	}

	@Override
	public WordProfileDomain findByid(int id) throws Exception {
		var inDomain = new WordProfileDomain(false);

		try (Connection con = ds.getConnection()) {

			String sql = "SELECT * FROM word_profile where id = ?;";

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				System.out.println("検索結果が帰ってきています");
				int dbid = (Integer) rs.getObject("id");
				var dbword = rs.getString("word");
				int dbvalue = (Integer) rs.getObject("value");
				var dbtext = rs.getString("text");
				var dbmeam = rs.getString("meam");

				System.out.println("ワードプロファイル" + dbword + " id:" + dbid + " value:" + dbvalue + " text:" + dbtext + ""
						+ " meam:" + dbmeam);
				inDomain = new WordProfileDomain(dbid, dbword, dbvalue, dbtext, dbmeam);
				System.out.println("検索結果のデータ化が完了");
			} else {
				System.out.println("データが存在しませんでした。");
			}

		} catch (Exception e) {
			System.out.println("WordProfileでサーバーエラー");
			throw new ServletException(e);
		}

		return inDomain;
	}

	/**
	 * word とValueで検索
	 * 同一者が複数登録されることがありうるので、idがもっとも大きいものを返すようにする
	 * 同時に同じものを登録しない限り問題は起きない
	 * データが見つからなかったらIDが－1になります。
	 */
	@Override
	public WordProfileDomain findByNotid(WordProfileDomain domain) throws Exception {
		var inDomain = new WordProfileDomain(false);

		try (Connection con = ds.getConnection()) {

			String sql = "SELECT * FROM word_profile where word = ? and value = ?;";

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, domain.getWord());
			stmt.setInt(2, domain.getValue());
//			stmt.setString(3, domain.getText());
//			stmt.setString(4, domain.getMeam());

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				System.out.println("検索結果が帰ってきています");
				int dbid = (Integer) rs.getObject("id");
				var dbword = rs.getString("word");
				int dbvalue = (Integer) rs.getObject("value");
				var dbtext = rs.getString("text");
				var dbmeam = rs.getString("meam");

				System.out.println("ワードプロファイル" + dbword + " id:" + dbid + " value:" + dbvalue + " text:" + dbtext + ""
						+ " meam:" + dbmeam);
				if (inDomain.getId() < dbid) {
					inDomain = new WordProfileDomain(dbid, dbword, dbvalue, dbtext, dbmeam);
				}

				System.out.println("検索結果のデータ化が完了");
			}

		} catch (Exception e) {
			System.out.println("WordProfileでサーバーエラー");
			throw new ServletException(e);
		}

		return inDomain;
	}



	/**
	 * word とValueで検索
	 * 同一者が複数登録されることがありうるので、idがもっとも大きいものを返すようにする
	 * 同時に同じものを登録しない限り問題は起きない
	 * データが見つからなかったらIDが－1になります。
	 */
	@Override
	public WordProfileDomain findByNotid(WordProfileDomain domain,Connection con) throws Exception {
		var inDomain = new WordProfileDomain(false);

		try {

			String sql = "SELECT * FROM word_profile where word = ? and value = ?;";

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, domain.getWord());
			stmt.setInt(2, domain.getValue());
//			stmt.setString(3, domain.getText());
//			stmt.setString(4, domain.getMeam());

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				System.out.println("検索結果が帰ってきています");
				int dbid = (Integer) rs.getObject("id");
				var dbword = rs.getString("word");
				int dbvalue = (Integer) rs.getObject("value");
				var dbtext = rs.getString("text");
				var dbmeam = rs.getString("meam");

				System.out.println("ワードプロファイル" + dbword + " id:" + dbid + " value:" + dbvalue + " text:" + dbtext + ""
						+ " meam:" + dbmeam);
				if (inDomain.getId() < dbid) {
					inDomain = new WordProfileDomain(dbid, dbword, dbvalue, dbtext, dbmeam);
				}

				System.out.println("検索結果のデータ化が完了");
			}

		} catch (Exception e) {
			System.out.println("WordProfileでサーバーエラー");
			throw new ServletException(e);
		}

		return inDomain;
	}



	@Override
	public Map<Integer, WordProfileDomain> findword(String word) throws Exception {
		Map<Integer, WordProfileDomain> map = new HashMap<>();

		var inDomain = new WordProfileDomain(false);

		try (Connection con = ds.getConnection()) {

			String sql = "SELECT * FROM word_profile where word = ?;";

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, word);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				System.out.println("検索結果が帰ってきています");
				int dbid = (Integer) rs.getObject("id");
				var dbword = rs.getString("word");
				int dbvalue = (Integer) rs.getObject("value");
				var dbtext = rs.getString("text");
				var dbmeam = rs.getString("meam");

				System.out.println("ワードプロファイル" + dbword + " id:" + dbid + " value:" + dbvalue + " text:" + dbtext + ""
						+ " meam:" + dbmeam);
				inDomain = new WordProfileDomain(dbid, dbword, dbvalue, dbtext, dbmeam);

				map.put(inDomain.getId(), inDomain);
				System.out.println("検索結果のデータ化が完了");
			}
			if (map.size() == 0) {
				System.out.println("WordProfile検索結果は0です。");
			}

		} catch (Exception e) {
			System.out.println("WordProfileでサーバーエラー");
			throw new ServletException(e);
		}

		return map;
	}

	/**
	 * idは使用しない。それ以外を項目として利用し登録する。
	 * finedメソッドのidを利用せずnametextmeamから検索するものが必要-＞済
	 */
	@Override
	public WordProfileDomain insert(WordProfileDomain domain) throws Exception {

		var inDomain = new WordProfileDomain(false);

		//	データベースと連携し出たが登録できたか確認する 1になっていれば成功とみなそう
		int stmtEXECount = 0;

		//		dsにはデータソースが入っているので問題ない
		try (Connection con = ds.getConnection()) {
			System.out.println("insert１");

			String sql = "INSERT INTO `word_profile` (`word`, `value`, `text`, `meam`) VALUES (?, ?, ?, ?);";

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, domain.getWord());
			stmt.setInt(2, domain.getValue());
			stmt.setString(3, domain.getText());
			stmt.setString(4, domain.getMeam());

			stmtEXECount = stmt.executeUpdate();

			System.out.println("insert2");
		} catch (Exception e) {
			System.out.println("insert3");
			throw new ServletException(e);
		}

		inDomain = findByNotid(domain);
		if (inDomain.getId() == -1) {
			System.out.println("wordProfileに失敗しました。");
		} else {
			System.out.println("wordProfileに成功しました。");
		}

		return inDomain;
	}

	/**
	 * idを指定して、上書きする機能 textとvalueは変更不可。
	 * 安全安全性のチェックは、別のところでやる
	 * textとmeamの上書き、中身を確認してから与えること
	 */
	@Override
	public WordProfileDomain update(WordProfileDomain domain) throws Exception {

		var inDomain = new WordProfileDomain(false);


		try (Connection con = ds.getConnection()) {

			String sql = "UPDATE `word_profile` SET `text` = ?, `meam` = ? WHERE (`id` = ?);";
			PreparedStatement stmt = con.prepareStatement(sql);


			stmt.setString(1, domain.getText());
			stmt.setString(2, domain.getMeam());
			stmt.setInt(3, domain.getId());

			//	データベースと連携し出たが登録できたか確認する 1になっていれば成功とみなそう
			int stmtEXECount = 0;
			stmtEXECount = stmt.executeUpdate();


		} catch (Exception e) {
			System.out.println("サーバー接続に失敗しました");
			throw e;
		}

		inDomain = findByid(domain.getId());
		if (inDomain.getId() == -1) {
			System.out.println("wordProfileのUPDATEに失敗しました。");
		} else {
			System.out.println("wordProfileのUPDATEに成功しました。");
		}

		return inDomain;

	}

	/**細々とした操作をせずに設定できるセットメソッド
	 * idが－１ならインサート
	 *idがあればアップデートする
	 */
	@Override
	public WordProfileDomain insertorUpdate(WordProfileDomain domain) throws Exception {

		WordProfileDomain inDomain;

		if (domain.getId() == -1) {
			//			インサート処理
			inDomain = insert(domain);

		} else {
			//			アップデート処理
			inDomain = update(domain);
		}

		return inDomain;
	}

	@Override
	public boolean delete(WordProfileDomain id) throws Exception {
		//		コピペエリア上

		int stmtEXECount = 0;

		try (Connection con = ds.getConnection()) {
			//				削除する方法
			String sql1 = "DELETE FROM `word_profile` WHERE (`id` = ?);";

			PreparedStatement stmt = con.prepareStatement(sql1);

			stmt.setInt(1, id.getId());
			//			sql文を実行する
			 stmtEXECount =stmt.executeUpdate();


		} catch (Exception e) {
			throw e;
		}

		if(stmtEXECount == 0) {
			System.out.println("wordProfile削除に失敗したようです");
		}else {
			System.out.println("wordProfile削除に失敗したようです");
		}

		//		コピペエリア下

		return stmtEXECount != 0;
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
