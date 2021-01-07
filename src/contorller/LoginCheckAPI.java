package contorller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.LoginData;

/**
 * Servlet implementation class SimpleApi
 */
@WebServlet("/LoginCheckApi")
public class LoginCheckAPI extends HttpServlet {


//
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object objLogin = session.getAttribute("loginData");
		if(objLogin != null) {
			LoginData loginData  = (LoginData)objLogin;
			request.setAttribute("userName", loginData.getUserName());
			request.setAttribute("loginTest", loginData.isLoginState());
			request.setAttribute("msg", "成功しました");
		}else {
			request.setAttribute("userName","ゲスト");
			request.setAttribute("loginTest", false);
			request.setAttribute("msg", "ログインしていません。");
		}



		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	}





}
