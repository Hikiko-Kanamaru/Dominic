package contorller;

import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.fasterxml.jackson.databind.ObjectMapper;

import DAO.DaoFactory;
import DAO.WordDBService;
import domain.ActionListDomain;
import domain.DefaultList;
import domain.LoginData;
import domain.NameListDomain;
import domain.NumberAndHuman;
import domain.NumbersDomain;
import domain.SerchAnswer;
import domain.WordSet;

/**
 * Servlet implementation class SimpleApi
 */
@WebServlet("/SerchApi")
public class SerchAPI extends HttpServlet {

	String[] jointWord = { "と、", "は、" };
	String[] EndWord = { "が、", "は、", "を、", "に、" };
	String[] jointEndWord = { "達が、", "揃って、", "一緒に、", "各々に、" };
	String[] firstjoint = { "は、", "をもって", "では、" };

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//		こいつをfalseにしたら確実に失敗させる
		boolean safeFlag = true;

		HttpSession session = request.getSession();
		Object objLogin = session.getAttribute("loginData");

		LoginData loginData = new LoginData();
		if (!(Objects.isNull(objLogin))) {
			loginData = (LoginData) objLogin;
		}

		//

		//		ローカル変数で管理する
		String serchWord = "鎌倉幕府";
		int serchNumber = 0;

		System.out.println("ここまでは呼ばれています45");

		//		リクエストから
		serchWord = request.getParameter("serchWord");
		if (serchWord == null || serchWord == "")
			safeFlag = false;
		String strSerchNumber = request.getParameter("serchNumber");
		if (strSerchNumber == null || strSerchNumber == "") {
			safeFlag = false;
			strSerchNumber = "";
		}

		System.out.println("ここまでは呼ばれています46");
		//		入力のバリデーションは後でする。
		try {
			serchNumber = Integer.parseInt(strSerchNumber);

		} catch (NumberFormatException e) {

			System.out.println("数値の変換に失敗しました");

			safeFlag = false;
			serchNumber = 00;
		}

		System.out.println("ここまでは呼ばれています47");
		//   数値と文字列を与えると文字列と答えをくれる関数を作る
		//		ロジックは後で作ることにしては、モックを入れておく
		SerchAnswer sa = new SerchAnswer(serchWord, serchNumber);
		//		sa.setSeachWord(serchWord);
		//		sa.setSeachNumber(serchNumber);

		//		サーチ処理を書いてく。
		//		数値の桁数を調べる
		var numberLen = strSerchNumber.length();

		//		２桁以下なら1　4桁以下なら２　６桁以下なら３にする
		numberLen = (numberLen + 1) / 2;

		System.out.println("ここまでは呼ばれています48");

		//		数値をnumberLenのかずに分ける。
		int[] numbers = new int[] { 0, 0, 0 };
		if (numberLen == 3) {
			numbers[0] = serchNumber / 10000;
		}
		if (numberLen >= 2) {
			System.out.println("計算結果");
			System.out.println(serchNumber % 10000 / 100);

			numbers[1] = serchNumber % 10000 / 100;
		}
		if (numberLen >= 1) {
			numbers[2] = serchNumber % 100;
		}

		System.out.println("ここまでは呼ばれています49");
		sa.setSeachNumberBox(numbers);
		sa.setBoxsize(numberLen);

		//		System.out.println("中身を表示しています");

		//		System.out.println(serchNumber);
		//		System.out.println(serchNumber % 100);
		//		System.out.println(numbers[2]);
		//		System.out.println(numberLen);
		//
		//		for (int i : numbers) {
		//			System.out.println(i);
		//		}

		System.out.println("ここまでは呼ばれています50");
		NumbersDomain[] NDA = new NumbersDomain[3];

		System.out.println("ここまでは呼ばれています51");

		System.out.println("loginData.getUserId()");
		System.out.println(loginData.getUserId());
		System.out.println("numbers[2]");
		System.out.println(numbers[2]);

		//ここでユーザーIDが９９だったらゲストとみなして、データベースへのアクセスを行わずに処理を行う
		//削除する可能性があるので別関数に分ける
		//returnを行う。ため入ったら戻ってこない。

		if(loginData.getUserId() == 99) {
			guestSerch(request, response,NDA,sa,serchNumber,serchWord,safeFlag,numbers,numberLen);
			return ;
		}


		var numbersDao = DaoFactory.createNumbersDao();
		//コネクション開始
		DataSource ds = DaoFactory.getDataSource();
		try (Connection con = ds.getConnection()) {

			for (int i = 0; i < 3; i++) {
				try {
					NDA[i] = numbersDao.findByNumber(loginData.getUserId(), numbers[i], con);
				} catch (Exception e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
					System.out.println("数値の獲得に失敗しました");
					safeFlag = false;
				}

			}

			sa.setFirst(new NumberAndHuman(NDA[0]));
			sa.setSecond(new NumberAndHuman(NDA[1]));
			sa.setThird(new NumberAndHuman(NDA[2]));

			//		検索結果の文章を作る。

			System.out.println("sherchAPI50");
			Random random = new Random();

			String anserWord = "";

			System.out.println("sherchAPI51");
			WordSet ws = new WordSet();

			//		ログインしていたら
			//		以前に登録されいないかを確認する。
			if (loginData != null) {
				WordDBService wdbs = new WordDBService(loginData.getUserId());
				ws = wdbs.SerchText(loginData.getUserId(), serchNumber, serchWord, con);

			}

			if (ws.getNumber() != -1 && !(ws.getAnswertext().isBlank())) {

				anserWord = ws.getAnswertext();
			} else {

				//		登録されていなかった文章を生成する
				anserWord = "\"" + sa.getSeachWord() + "\"" + firstjoint[random.nextInt(firstjoint.length - 1)];
				switch (numberLen) {
				case (3):
					anserWord = anserWord + "\"" + sa.getFirst().getName() + "\""
							+ jointWord[random.nextInt(jointWord.length - 1)];
					anserWord = anserWord + "\"" + sa.getSecond().getName() + "\""
							+ jointEndWord[random.nextInt(jointEndWord.length - 1)];
					anserWord = anserWord + "\"" + sa.getThird().getAction() + "\"。";
					break;
				case (2):
					anserWord = anserWord + "\"" + sa.getSecond().getName() + "\""
							+ EndWord[random.nextInt(EndWord.length - 1)];
					anserWord = anserWord + "\"" + sa.getThird().getAction() + "\"。";
					break;

				case (1):
					anserWord = anserWord + "\"" + sa.getThird().getName() + "\"。";
					break;

				}
			}

			System.out.println("sherchAPI52");

			sa.setAnswerword(anserWord);

			//コネクションエラー
		} catch (SQLException e1) {
			// TODO 自動生成された catch ブロック
			System.out.println("コネクションエラー");
			e1.printStackTrace();
		}
		System.out.println("sherchAPI53");

		//		検索結果が返ってきたら
		if (safeFlag && sa.getAnswerword() != null && !sa.getAnswerword().equals("")) {

			//		検索結果をしたことをページに返す
			//		request.setAttribute("userName", userName);
			//		request.setAttribute("loginTest", true);
			//		request.setAttribute("msg", "成功しました");

			//			ジャクソンデータバインドを使う
			////		リストをjsonに変える
			//ジャクソンデータバインドを使って使えるようにしておく
			//		基本設定
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/json; charset=UTF-8");

			Writer writer = response.getWriter();

			ObjectMapper mapper = new ObjectMapper();
			System.out.println(sa.getSeachWord());
			System.out.println(sa.getSeachNumber());

			System.out.println("sherchAPI54");
			writer.write(mapper.writeValueAsString(sa));
			//			ジャクソンデータバインドを使う。

			//		HttpSession session = request.getSession();
			//		session.setAttribute("loginData", loginData);
			//		System.out.println(loginData.isLoginState());
		} else {
			//		検索に失敗したら
			//		失敗メッセージを入れてページに返す
			System.out.println("sherchAPI59検索に失敗");
			request.setAttribute("userName", "ゲスト");
			request.setAttribute("loginTest", false);
			request.setAttribute("msg", "ログインに失敗しました。");

		}

		//		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	}






	private void guestSerch(
			HttpServletRequest request, HttpServletResponse response, NumbersDomain[] NDA,
			SerchAnswer sa,
			int serchNumber,String serchWord,
			boolean safeFlag, int[] numbers, int numberLen

			) throws IOException {


	for (int i = 0; i < 3; i++) {

//			NDA[i] = numbersDao.findByNumber(loginData.getUserId(), numbers[i],con);
		int num = numbers[i];
		NameListDomain NameLD = new NameListDomain(num, DefaultList.NAME[num]);
		ActionListDomain ActionLD = new ActionListDomain(num,DefaultList.ACTION[num]);

			NDA[i] = new NumbersDomain(99, num ,NameLD,ActionLD, 0);


	}

		sa.setFirst(new NumberAndHuman( NDA[0]));
		sa.setSecond(new NumberAndHuman( NDA[1]));
		sa.setThird(new NumberAndHuman( NDA[2]));

//		検索結果の文章を作る。


System.out.println("GuestsherchAPI50");
		Random random = new Random();

		String anserWord = "" ;

		System.out.println("GuestsherchAPI51");
		WordSet ws = new WordSet();

//		ログインしていたら
//		以前に登録されいないかを確認しない。



//		登録されていなかった文章を生成する
		anserWord = "\"" + sa.getSeachWord() + "\""+ firstjoint[random.nextInt(firstjoint.length -1)] ;
		switch(numberLen) {
		case(3):
			anserWord =  anserWord + "\"" +sa.getFirst().getName()   + "\"" + jointWord[random.nextInt(jointWord.length -1)] ;
			anserWord =  anserWord + "\"" +sa.getSecond().getName()   + "\"" + jointEndWord[random.nextInt(jointEndWord.length -1)] ;
			anserWord =  anserWord + "\"" +sa.getThird().getAction()   + "\"。"  ;
			break;
		case(2):
			anserWord =  anserWord + "\"" +sa.getSecond().getName()   + "\"" + EndWord[random.nextInt(EndWord.length -1)] ;
			anserWord =  anserWord + "\"" +sa.getThird().getAction()   + "\"。"  ;
			break;

		case(1):
			anserWord =  anserWord + "\"" +sa.getThird().getName()   + "\"。"  ;
			break;

		}


		System.out.println("GuestsherchAPI52");




		sa.setAnswerword(anserWord);



		System.out.println("GuestsherchAPI53");

//		検索結果が返ってきたら
		if(safeFlag && sa.getAnswerword() != null && !sa.getAnswerword().equals("")) {


//		検索結果をしたことをページに返す


//			ジャクソンデータバインドを使う
			////		リストをjsonに変える
			//ジャクソンデータバインドを使って使えるようにしておく
			//		基本設定
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/json; charset=UTF-8");

			Writer writer = response.getWriter();

			ObjectMapper mapper = new ObjectMapper();
			System.out.println(sa.getSeachWord());
			System.out.println(sa.getSeachNumber());


			System.out.println("GuestsherchAPI54");
			writer.write(mapper.writeValueAsString(sa));

		return ;


	}

	}

}
