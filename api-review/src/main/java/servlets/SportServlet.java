package servlets;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import donnees.*;

@WebServlet("/Sports")
public class SportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public List<Sportif> getListeSportifs() {
		// requï¿½te JPQL pour rï¿½cupï¿½rer les sportifs dans la BDD
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SportsPU");
		EntityManager em = emf.createEntityManager();
		Query requete = em.createQuery("SELECT s FROM Sportif s");
		return (List<Sportif>) requete.getResultList();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operation = request.getParameter("operation");
		if (operation.equals("listeSportif")) {
			// récupère la liste des sportifs et l'associe à la requête HTTP
			request.setAttribute("sportifs", this.getListeSportifs());
			// forwarde la requêt à la page JSP
			getServletConfig().getServletContext().getRequestDispatcher("/afficheSportifs.jsp")
				.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}