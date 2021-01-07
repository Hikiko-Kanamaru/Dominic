package contorller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.LoginLogic;

/**
 * Servlet implementation class SimpleApi
 */
@WebServlet("/loginApi")
public class LoginApi extends HttpServlet {


//開発中は、クロスサイトに引っかかるので、getにして利用する。
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		ローカル変数で管理する
		String userName = "ゲスト";
		boolean login = false;
		System.out.println("ここまでは呼ばれています");

//		リクエストから
		String strId = request.getParameter("lang_id");
//		IDのバリデーションは後でする。
		String password = request.getParameter("password");



//		ここでログインチェックを行う

		var loginData = new LoginLogic().execute(strId,password);
		login = loginData.isLoginState();
		userName = strId;

//		ログインに成功したら
		if(login) {

//		ユーザーネームと成功したことをページに返す
		request.setAttribute("userName", userName);
		request.setAttribute("loginTest", true);
		request.setAttribute("msg", "成功しました");

		HttpSession session = request.getSession();
		session.setAttribute("loginData", loginData);
		System.out.println(loginData.getUserName());
		}else {
//		ログインに失敗したら、
//		ユーザーネームをゲストにして
//		失敗したことを伝えるフラグを立てて
//		失敗メッセージを入れてページに返す
			request.setAttribute("userName","ゲスト");
			request.setAttribute("loginTest", false);
			request.setAttribute("msg", "ログインに失敗しました。");

		}

		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	}





}
