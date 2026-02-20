package servlets;

import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sportsDAO.DAOException;
import sportsDAO.DAO_JPA;

import java.io.IOException;
import java.util.List;

import donnees.Sport;

import jakarta.persistence.Query;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Servlet implementation class CreerDisciplineServlet
 */
@WebServlet(name = "CreerDiscipline", urlPatterns = { "/CreerDiscipline" })
public class CreerDisciplineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * retourne la liste des sports de l'applicatin
	 */
	public List<Sport> getListeSports() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SportsPU");
		EntityManager em = emf.createEntityManager();
		Query requete = em.createQuery("SELECT s FROM Sport s");
		return (List<Sport>) requete.getResultList();

	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operation = request.getParameter("operation");
		if (operation.equals("listeSport")) {
			// récupère la liste des sportifs et l'associe à la requête HTTP
			request.setAttribute("sports", this.getListeSports());
			// forwarde la requêt à la page JSP
			getServletConfig().getServletContext().getRequestDispatcher("/creerDiscipline.jsp")
				.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
