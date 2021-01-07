package contorller;

import java.io.IOException;
import java.io.Writer;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import DAO.WordDBService;
import domain.LoginData;
import domain.WordBookDomain;
import domain.WordSet;

/**
 * Servlet implementation class WordRegAPI
 */
@WebServlet("/wordRegAPI")
public class WordRegAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public WordRegAPI() {
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
		String word;
		String answertext;

//		falseで失敗させる。
		boolean safeFlag = true;


		System.out.println("wordRegが呼ばれています");

//		リクエストから
		strNumber = request.getParameter("number");
		try {
		number = Integer.parseInt(strNumber);
		}catch (Exception e) {
//			System.out.println("変換に失敗したので終了します");
//			return ;
			number = -1;
			safeFlag=false;
		}
		word = request.getParameter("word");
		answertext = request.getParameter("answertext");

		//空の入力があったら失敗させる
		if(word.isBlank() || answertext.isBlank()) {
			safeFlag = false;
		}



//		データを受け取る
		WordSet ws = new WordSet(number, word, answertext);


		HttpSession session = request.getSession();
		Object objLogin = session.getAttribute("loginData");
		LoginData loginData  = (LoginData)objLogin;
		 int userID;
		 if(Objects.isNull(loginData)||Objects.isNull(loginData.getUserId())){
			 userID = -1;
			 safeFlag=false;
		 }else {
			 userID = loginData.getUserId();
		 }

		WordDBService wdbService = new WordDBService(userID);

//		現在のセルのvalueを確認する
		String strCellValue = request.getParameter("cellValue");
		int cellValue;
		boolean deleteFlag = true;
		try {
			cellValue = Integer.parseInt(strCellValue);
		}catch (Exception e) {
//			System.out.println("変換に失敗したので終了します");
//			return ;
			cellValue = -1;
		}

		if(cellValue < 0 ) {
			deleteFlag = false;
			}

//		昔のデータが存在するか確認する
		WordBookDomain oldWBD = wdbService.dbWordBookDomainGet(cellValue);
//
		if (oldWBD.getUserID()==-1 ) {
//			データが存在しないのでフラグを折る
			deleteFlag = false;
		}


		if(deleteFlag) {
			var oldws = wdbService.dbGet(cellValue);

			try {
				deleteFlag = wdbService.dbDelete(oldws);
				System.out.println("データがかぶるため削除実行："+ deleteFlag);
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				System.out.println("データがかぶるため削除実行：サーバーでエラー");
				e.printStackTrace();
			}
		}



//		実行していいか確認する
		if(safeFlag) {


//		データをサーバーに登録する
		try {
//	    後で作る wsを渡す
//		登録できたかか確認のためサーバーからデータを呼び出す
			ws = wdbService.dbSet(ws);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			System.out.println("wordRegAPIでトラブルが有りました。");
			e.printStackTrace();
		}

		}else {
//			DBに登録しないので失敗処理をさせる
			System.out.println("wordRegAPI失敗させます");
			ws = new WordSet();

		}



//		最終的に更新されたデータを送り返す
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/json; charset=UTF-8");

		Writer writer = response.getWriter();

		ObjectMapper mapper = new ObjectMapper();

//
		System.out.println("WordRegが処理を完了します。");
		System.out.println(answertext);

		writer.write(mapper.writeValueAsString(ws));


	}

}
