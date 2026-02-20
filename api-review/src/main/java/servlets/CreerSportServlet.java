package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class CreerSportServlet
 */
@WebServlet(name = "CreerSport", urlPatterns = { "/CreerSport" })
public class CreerSportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreerSportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
	    
	    String nom = request.getParameter("nomSport");

	    if (nom != null) {
	        try {

	            sportsDAO.DAO_JPA<donnees.Sport> dao = new sportsDAO.DAO_JPA<>();

	            donnees.Sport nouveauSport = new donnees.Sport();
	            nouveauSport.setIntitule(nom);

	            dao.create(nouveauSport);
	            
	            System.out.println("Sport ajouté avec succès : " + nom);
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    response.sendRedirect("index.html");
	}

}
