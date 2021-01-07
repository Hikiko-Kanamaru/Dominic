package contorller;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.WordDBService;
import domain.LoginData;
import domain.WordJoinDomain;

/**
 * Servlet implementation class CardServlet
 */
@WebServlet("/mainWordViewMonoAPI")
public class MainWordViewMonoAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;



	/** value:wordProfIdを渡す必要があります
	 *
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		こいつをfalseにしたら確実に失敗させる
		boolean safeFlag = true;

		HttpSession session = request.getSession();
		Object objLogin = session.getAttribute("loginData");
		LoginData loginData = new LoginData();
		if (!(Objects.isNull(objLogin))) {
			loginData = (LoginData) objLogin;
		}else {
			safeFlag = false;

		}




		var wordDBS = new WordDBService(loginData.getUserId());

		String strProfId = request.getParameter("value");
		int profId ;
		var wordjoindomain = new WordJoinDomain(false) ;


		try {
			profId = Integer.parseInt(strProfId);

		}catch (NumberFormatException e) {

			System.out.println("数値の変換に失敗しました");
			safeFlag  = false;
			profId = -1;
		}
		if(safeFlag ) {
			wordjoindomain = wordDBS.getWordJoinDomain(profId);
		}

		String path = "/WEB-INF/view/wordViewMono.jsp";
		request.setAttribute("wordJoinDomain", wordjoindomain);


		request.getRequestDispatcher(path).forward(request, response);

	}

}
