package contorller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DaoFactory;
import DAO.WordDBService;
import domain.WordSet;

/**
 * Servlet implementation class DBTestServlet
 */
@WebServlet("/dbTest")
public class DBTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		var dao = DaoFactory.createUserDao();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection( "jdbc:mysql://127.0.0.1:3306/dominic_db?useUnicode=true&characterEncoding=utf8&serverTimezone=JST", "root", "" );
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}




		WordSet ws = new WordSet(456, "OKOE", "kore");
		var wordDBS  =  new WordDBService(99);
//		try {
//			var kotae = wordDBS.dbDelete(ws);
//			System.out.println("データベース削除の動作確認"+ kotae);
//		} catch (Exception e) {
//			// TODO 自動生成された catch ブロック
//			e.printStackTrace();
//		}

//		var hantei =wordDBS.dbBookDelete(94);
//
//		System.out.println(hantei);

//		var kore = wordDBS.bookFindByid();
//		System.out.println(kore);
//		System.out.println(kore.size());
//		for (WordBookDomain i : kore.values()) {
//			System.out.println(i.getText());
//		}

		String path = "/WebContent/index.html";
		request.getRequestDispatcher(path).forward(request, response);

	}

}
