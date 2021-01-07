package test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DaoFactory;
import domain.DefaultList;

/**
 * Servlet implementation class DaoTest
 */
@WebServlet("/DaoTest")
public class DaoTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DaoTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		var kore = request.getSession();
		var are = kore.getAttribute("nani");
		System.out.println(are);


		var nameDao = DaoFactory.createNameList();
		var actionDao = DaoFactory.createActionList();
		var fullDao = DaoFactory.createNumbersDao();

		for (int i = 0; i < 100; i++) {

			System.out.println(i+":"+ DefaultList.NAME[i]+"="+ DefaultList.ACTION[i] );


		}


		try {
//			System.out.println("nameインサートの開始");
//			インサートの動作確認が住んだ
//			var nameuke = nameDao.insert("わたくしさまです", "");
//			System.out.println("actionインサートの開始");
//			var actionuke = actionDao.insert("アクション登録されています", "");
//			アクションの登録ができた


//			var fullUke = fullDao.updateOrInsert(new NumberAndHuman(122, "action", "dfaf", 15, "dfa", "アクションヒーロ", 888, "www"));


//  デフォルトテーブルの登録メソッド
//			for (int i=0;i<100;i++ ) {
//				var NumDom = new NumbersDomain(1,i);
//				NumDom.setName(new NameListDomain(i, "www"));
//				NumDom.setAction(new ActionListDomain(i, "www"));
//				NumDom.setMemory_Level(1);
//
//				System.out.println(NumDom.getAction().getURL());
//				var kore2 = fullDao.insert(NumDom);
//
//System.out.print(i + ":");
//				System.out.println(kore2);
//			}


//			要求したNumberが得られるかどうかのテスト

//			var ukeru = fullDao.findByNumber(799, 99);
//			System.out.println(ukeru.getName());
//			System.out.println(ukeru.getName().getName());
//			System.out.println(ukeru.getAction().getName());

//
//			var wdbs = new WordDBService();
//			WordBookDao  wbd = wdbs.getwBook();
//
//			List<WordJoinDomain> wList = wbd.findWordJoinByUserID(17);
//
//			for (WordJoinDomain wJ : wList) {
//				System.out.println(wJ.getText());
//				System.out.println(wJ);
//
//			}



System.out.println("完了");

		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}





		response.getWriter().append("Served at: ").append(request.getContextPath());



	}

}
