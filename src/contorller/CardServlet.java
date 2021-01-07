package contorller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CardServlet
 */
@WebServlet("/card")
public class CardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String[] numberWord  = {"00","10","20","30","40","50","60","70","80","90"} ;
		request.setAttribute("numberWord", numberWord);
		String[] numberMessage = {
				"濁音 または、ワ行",
				"ア行",
				"カ行",
				"サ行",
				"タ行",
				"ナ行",
				"ハ行",
				"マ行",
				"ヤ行",
				"ラ行"
				};
 		request.setAttribute("numberMessage", numberMessage);

		String path = "/WEB-INF/view/card.jsp";
		request.getRequestDispatcher(path).forward(request, response);

	}

}
