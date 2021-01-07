package contorller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DaoFactory;
import domain.LoginData;
import domain.UserCreateData;

/**
 * Servlet implementation class SimpleApi
 */
@WebServlet("/LoginUserCreateApi")
public class LoginUserCreateApi extends HttpServlet {

	//開発中は、クロスサイトに引っかかるので、getにして利用する。
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//		ローカル変数で管理する
//		String userName = "ゲスト";
//		boolean login = false;
//		int id  = 99;
		LoginData loginData  = new LoginData("", 99, false);

		//		リクエストから
		String strId = request.getParameter("lang_id");
		//		IDのバリデーションは後でする。
		String password = request.getParameter("password");



		//		ここでユーザー名が利用できるかのチェックを行う
		System.out.println(strId);
		boolean namechack = (strId.length() >= 6);

		//		ユーザーパスワードのチェックを行う
		System.out.println(password);
		boolean passChack = (password.length() >= 6);

		//		どちらも成功したら登録する
		if (namechack && passChack) {
			//			ここでDaoから登録用のインタンスをもらってサーバーに登録する
//			System.out.println("クリエイトユーザー１");
			boolean touroku = false;
//登録に成功したかどうか
			var userDao = DaoFactory.createUserDao();
			var user = new UserCreateData(strId, password);
			try {
//				System.out.println("クリエイトユーザー２");
			 loginData =userDao.insert(user);
//			 System.out.println("クリエイトユーザー３");
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				System.out.println("サーバー系のエラーどうしようもない。");
				e.printStackTrace();
			}
			touroku = loginData.isLoginState();

			//		成功したことをページに返す
			if (touroku) {
				final String  msg = loginData.getUserName() + "さんの登録に成功しました。";
				request.setAttribute("userName", loginData.getUserName());
				request.setAttribute("loginTest", loginData.isLoginState());
				request.setAttribute("msg", msg);
				System.out.println(msg);
			} else {
				//		ひどい書き方だが、時短のために使用
				request.setAttribute("userName", "ゲスト");
				request.setAttribute("loginTest", false);
				request.setAttribute("msg", "サーバへの登録に失敗しました。");
				System.out.println("クリエイトユーザー失敗");
			}

		} else {
			//		ログインに失敗したら、
			//		ユーザーネームをゲストにして
			//		失敗したことを伝えるフラグを立てて
			//		失敗メッセージを入れてページに返す
			request.setAttribute("userName", "ゲスト");
			request.setAttribute("loginTest", false);
			request.setAttribute("msg", "サーバへの登録に失敗しました。");
			System.out.println("クリエイトユーザー失敗");

		}
//		System.out.println("クリエイトユーザー10");
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	}

}
