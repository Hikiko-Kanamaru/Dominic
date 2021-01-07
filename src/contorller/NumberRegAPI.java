package contorller;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import DAO.DaoFactory;
import domain.LoginData;
import domain.NumberAndHuman;
import domain.NumbersDomain;

/**
 * Servlet implementation class NumberRegAPI
 */
@WebServlet("/numberRegAPI")
public class NumberRegAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NumberRegAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


//		ローカル変数で管理する
		int number;
		String strNumber;
	    String name;
	    String nameURL;
	    String action;
	    String actionURL;


		System.out.println("NumberRegが呼ばれています");

//		リクエストから
		strNumber = request.getParameter("number");
		try {
		number = Integer.parseInt(strNumber);
		}catch (Exception e) {
			System.out.println("数値の変換に失敗したので終了します");
			return ;
//			number = 999;
		}
	    name = request.getParameter("name");
	    nameURL = request.getParameter("nameURL");
	    action = request.getParameter("action");
	    actionURL = request.getParameter("actionURL");



//		データを受け取る


//		データをサーバーに登録する
//	    後で作る


//		サーバーからデータを呼び出す
//	    現在はモック
	    NumberAndHuman NAH = new NumberAndHuman(number, "ACTION", name, 0002, nameURL, action, 0006, actionURL);
//	    NumberAndHuman NAH = new NumberAndHuman(number, "ACTION");

//	    System.out.println("NumberReg");
//	    System.out.println(nameURL);
//	    System.out.println(actionURL);


//	    まず、アクションとnameを作る
	    var numbersDao = DaoFactory.createNumbersDao();
		HttpSession session = request.getSession();
		Object objLogin = session.getAttribute("loginData");

	    int userID = ((LoginData)objLogin).getUserId();

	    try {
			NumbersDomain ND = numbersDao.updateOrInsert(NAH,userID);

			NAH = new NumberAndHuman(ND.getList_number() , "ACTION", ND.getName().getName(),ND.getName().getId() , ND.getName().getURL(), ND.getAction().getName(),ND.getAction().getId() , ND.getAction().getURL());

		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			System.out.println("サーバの接続に失敗しまいた");
			e.printStackTrace();
			return ;
		}



//	    Numbersに与える。


//		最終的に更新されたデータを送り返す
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/json; charset=UTF-8");

		Writer writer = response.getWriter();

		ObjectMapper mapper = new ObjectMapper();

//
		System.out.println("NumberRegが処理を完了します。");


		writer.write(mapper.writeValueAsString(NAH));


	}

}
