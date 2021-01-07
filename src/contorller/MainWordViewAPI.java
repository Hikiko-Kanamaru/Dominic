package contorller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet("/mainWordViewAPI")
public class MainWordViewAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;



	/**
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

		List<WordJoinDomain> wordJoinList = new ArrayList<>() ;
if(loginData.getUserId() != 99) {

	var wordDBS = new WordDBService(loginData.getUserId());

	 wordJoinList = wordDBS.getWordJoinList();
}

		String path = "/WEB-INF/view/wordView.jsp";
		request.setAttribute("wordJoinList", wordJoinList);


		request.getRequestDispatcher(path).forward(request, response);

	}

}
