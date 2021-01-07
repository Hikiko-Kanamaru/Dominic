package contorller;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import domain.NumbersDomain;

/**
 * Servlet implementation class SimpleApi
 */
@WebServlet("/TestApi")
public class TestAPI extends HttpServlet {

//	フィールド
	private DataSource ds;

//
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		ds = getDataSource();


		String msg = "初期";

		NumbersDomain numbersDomain = new NumbersDomain();


		try (Connection con = ds.getConnection()) {

//			msg += "sql前";
//			String sql = "SELECT * FROM numbers where user_id = 17 and list_number = 1 ;";
//			PreparedStatement stmt = con.prepareStatement(sql);
//			msg += "sql中";
//			ResultSet rs = stmt.executeQuery();
//			msg += "sql後";
//			System.out.println("検索終了");

//			msg += "sqlU前";
//			String sql = "INSERT INTO `word_profile` (`word`, `value`, `text`, `meam`) VALUES (\"追加\", 9999, \"できたよ\", \"中身あり\");";
//			PreparedStatement stmt = con.prepareStatement(sql);
//			msg += "sqlU中";
//			int stmtEXECount = 0;
//			stmtEXECount = stmt.executeUpdate();
//			msg += "sqlU後";
//			System.out.println("検索終了");

//			msg += "4varsqlU前";
//			String sql = "INSERT INTO `word_profile` (`word`, `value`, `text`, `meam`) VALUES (?, 9999, \"できたよ\", \"中身あり\");";
//			msg += "sqlU3前2";
//			PreparedStatement stmt = con.prepareStatement(sql);
//			String kore= "これをついかします。";
//			msg += "sqlU4前3";
//			stmt.setString(1, "これをついかしました");
//			msg += "sqlU３中";
//			int stmtEXECount = 0;
//			stmtEXECount = stmt.executeUpdate();
//			msg += "sqlU3後";
//			System.out.println("検索終了");

//			msg += "5varsqlU前";
//			String sql = "INSERT INTO `word_profile` (`word`, `value`, `text`, `meam`) VALUES (?, 9999, \"できたよ\", \"中身あり\");";
//			msg += "sqlU3前2";
//			PreparedStatement stmt = con.prepareStatement(sql);
//			String kore= "変数で追加";
//			msg += "sqlU4前3";
//			stmt.setString(1, kore);
//			msg += "sqlU３中";
//			int stmtEXECount = 0;
//			stmtEXECount = stmt.executeUpdate();
//			msg += "sqlU3後";
//			System.out.println("検索終了");


//			msg += "６varsqlU前";
//			String sql = "SELECT * FROM numbers where user_id = ? and list_number = ? ;";
//			PreparedStatement stmt = con.prepareStatement(sql);
//			stmt.setInt(1, 17);
//			msg += "sqlU4前3";
//			stmt.setInt(2, 1);
//			msg += "sqlU３中";
//			ResultSet rs = stmt.executeQuery();
//			msg += "sqlU3後";
//			System.out.println("検索終了");

//			msg += "7varsqlU前";
//			String sql = "SELECT * FROM numbers where user_id = ? and list_number = ? ;";
//			PreparedStatement stmt = con.prepareStatement(sql);
//			msg += "sqlU4前3";
//			stmt.setInt(1, 17);
//			msg += "sqlU5中";
//			stmt.setInt(2, 17);
//			msg += "sqlU6中";
//			ResultSet rs = stmt.executeQuery();
//			msg += "sqlU3後";
//			System.out.println("検索終了");


//			msg += "8varsqlU前";
//				String sql = "SELECT * FROM actionlist where id = ?;";
//				msg += "qlU前";
//				PreparedStatement stmt = con.prepareStatement(sql);
//				stmt.setInt(1,1);
//				msg += "qlU前2";
//				ResultSet rs = stmt.executeQuery();
//				msg += "qlU前3";
//				//			rs.nextでレコードがあるかどうかを確認して、IDがあるかどうかを確認する
//				//			rs.next()は真偽を返すので、条件式にそのまま使える
//				msg += "RS4";
//				if (rs.next()) {
//					msg += "qlU中５";
//					System.out.println("検索結果が帰ってきています");
//					var dbname = rs.getString("name");
//					msg += "qlU中6";
//					int dbid = (Integer) rs.getObject("id");
//					msg += "qlU中後７";
//					var dbURL = rs.getString("URL");
//					msg += "qlU中後８";
//					var dbStrength = (Integer)rs.getObject("Strength");
//
//					msg += "qlU中9";
//					System.out.println(dbname+"id:"+dbid+"state:"+dbURL +"強度" + dbStrength);
//				var ActionListDomai = new ActionListDomain(dbid, dbname, dbURL, dbStrength) ;
//					System.out.println("検索結果のデータ化が完了");
//				}else {
//					System.out.println("データが存在しませんでした。");
//					msg += "ifエラー9";
//
//				}
//
//



		} catch (Exception e) {
			msg += "sqlUエラー";
			System.out.println("findByNumberサーバー接続に失敗しました");

		}

		msg += "sql終了";



			request.setAttribute("userName","ゲスト");
			request.setAttribute("loginTest", false);
			request.setAttribute("msg", msg);



		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	}




	//データソースメソッド
	/**
	 * データソース=接続を作るメソッド
	 * このクラスのみで使うので、外部に出さない。
	 * ただし.createUserDao内で使うので、staticにする
	 */
	private static DataSource getDataSource() {

System.out.println("testAPI01");
		var bundle = ResourceBundle.getBundle("environment");
		//c3p0の読み込み
		ComboPooledDataSource ds ;
		ds = new ComboPooledDataSource();

//		InitialContext ctx = null;
//		DataSource ds = null;


		System.out.println("testAPI02");

		try {
			System.out.println("testAPI03");

			ds.setDriverClass(bundle.getString("jdbc.driver"));
			System.out.println("testAPI04");
		}
		catch (PropertyVetoException e) {
			System.out.println("testAPI05");
			System.out.println("testAPI06データベース接続でエラー");
			throw new RuntimeException(e);
		}

		System.out.println("testAPI06");
		ds.setJdbcUrl(bundle.getString("jdbc.url"));
		ds.setUser(bundle.getString("jdbc.user"));
		ds.setPassword(bundle.getString("jdbc.password"));


		System.out.println("testAPI07");
		return ds;
	}



}
