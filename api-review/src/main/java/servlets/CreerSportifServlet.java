package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class CreerSportifServlet
 */
@WebServlet(name = "CreerSportif", urlPatterns = { "/CreerSportif" })
public class CreerSportifServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreerSportifServlet() {
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

	    String nom = request.getParameter("nomSportif");
	    String rue = request.getParameter("rueSportif");
	    String ville = request.getParameter("villeSportif");
	    String cp = request.getParameter("codePostalSportif");
	    
	    if(nom !=null && rue!=null && ville !=null && cp!=null) {
	    	try {
	    		  sportsDAO.DAO_JPA<donnees.Sportif> dao = new sportsDAO.DAO_JPA<>();

		            donnees.Sportif nouveauSportif = new donnees.Sportif();
		            nouveauSportif.setNom(nom);
		            nouveauSportif.setRue(rue);
		            nouveauSportif.setVille(ville);
		            nouveauSportif.setCodePostal(cp);

		            dao.create(nouveauSportif);
		            
		            System.out.println("Sportif ajouté avec succès : " + nom);
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}
	    }
	    response.sendRedirect("index.html");
	}

}
