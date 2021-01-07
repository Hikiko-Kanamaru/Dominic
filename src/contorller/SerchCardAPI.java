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

import DAO.DaoFactory;
import domain.DefaultList;
import domain.LoginData;
import domain.NumberAndHuman;
import domain.NumbersDomain;

/**
 * Servlet implementation class SimpleApi
 * カード番号を受け取って
 * numberandHuman型のjsonを返すAPI
 * SerchAPIのhumanの部分を利用して構築する。
 * userIDが獲得できなかったら＝ゲストだったら、ID＝１で検索した結果を返す。
 * APIを呼ぶときには"cardNumber"に値を入れる
 */
@WebServlet("/SerchCardApi")
public class SerchCardAPI extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//		こいつをfalseにしたら確実に失敗させる
		boolean safeFlag = true;
		NumbersDomain anserNumbersD = new NumbersDomain();
		NumberAndHuman anserNAH = new NumberAndHuman(false);

		HttpSession session = request.getSession();
		Object objLogin = session.getAttribute("loginData");

		LoginData loginData = new LoginData();
		if (!(Objects.isNull(objLogin))) {
			loginData = (LoginData) objLogin;
		}

		////		ローカル変数で管理する
		//		検索用値　serchNuberだと誤解しやすいので、cardNumberに変える
		//		int serchNumber = 0;
		int cardNumber = 0;

		System.out.println("ここまでは呼ばれています45");

		//		リクエストから
		String strCardNumber = request.getParameter("Number");

		//		テスト用
//				strCardNumber = "64";
		//		テスト用

		if (strCardNumber == null || strCardNumber == "")
			safeFlag = false;

		try {
			cardNumber = Integer.parseInt(strCardNumber);

		} catch (NumberFormatException e) {
			safeFlag = false;

			System.out.println("数値の変換に失敗しました");
			cardNumber = 0;
		}

		//		カードの範囲が範囲外の場合排除する

		if (cardNumber < 0 || cardNumber > 99) {
			safeFlag = false;
			cardNumber = 0;
		}

//ゲストユーザーの場合の処理を追加する
		if(loginData.getUserId()== 99) {
			GuestSerchCard(request, response,cardNumber,anserNAH, safeFlag);

			return ;
		}



		System.out.println("ここまでは呼ばれています50");
		var numbersDao = DaoFactory.createNumbersDao();

		try {
			anserNumbersD = numbersDao.findByNumber(loginData.getUserId(), cardNumber);
		} catch (Exception e) {
			System.out.println("SerchCardApiでサーバーエラー　デフォルトデータを返します");
			e.printStackTrace();
		}

		anserNAH = new NumberAndHuman(anserNumbersD);



		//		System.out.println("sherchjcard成功判断");

		if (safeFlag && anserNAH.getNumber() != -1) {

		//System.out.println("sherchjcard検索に成功");

			//			ジャクソンデータバインドを使う
			////		リストをjsonに変える
			//ジャクソンデータバインドを使って使えるようにしておく
			//		基本設定
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/json; charset=UTF-8");

			Writer writer = response.getWriter();

			ObjectMapper mapper = new ObjectMapper();
			System.out.println(anserNAH.getName());
			System.out.println(anserNAH.getAction());

			System.out.println("sherchCardAPI54");
			writer.write(mapper.writeValueAsString(anserNAH));
			//			ジャクソンデータバインドを使う。


		} else {
			//		検索に失敗したら
			//		失敗メッセージを入れてページに返す
			System.out.println("sherchAPI59検索に失敗");
			request.setAttribute("cardTest", false);
			request.setAttribute("msg", "sherchjcardに失敗しました。");

		}


	}



	private void GuestSerchCard(HttpServletRequest request, HttpServletResponse response,
			int cardNumber, NumberAndHuman anserNAH,
			boolean safeFlag)
			throws IOException {

		anserNAH = DefaultList.getNumberAndHuman(cardNumber);



		//		System.out.println("sherchjcard成功判断");

		if (safeFlag && anserNAH.getNumber() != -1) {

		//System.out.println("sherchjcard検索に成功");

			//			ジャクソンデータバインドを使う
			////		リストをjsonに変える
			//ジャクソンデータバインドを使って使えるようにしておく
			//		基本設定
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/json; charset=UTF-8");

			Writer writer = response.getWriter();

			ObjectMapper mapper = new ObjectMapper();
			System.out.println(anserNAH.getName());
			System.out.println(anserNAH.getAction());

			System.out.println("GuestsearchCardAPI54");
			writer.write(mapper.writeValueAsString(anserNAH));
			//			ジャクソンデータバインドを使う。
return ;

		} else {
			//		検索に失敗したら
			//		失敗メッセージを入れてページに返す
			System.out.println("GestsearchAPI59検索に失敗");
			request.setAttribute("cardTest", false);
			request.setAttribute("msg", "GestSearchjcardに失敗しました。");

		}




	}




}
