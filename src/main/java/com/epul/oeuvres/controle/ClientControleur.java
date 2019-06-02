package com.epul.oeuvres.controle;


import com.epul.oeuvres.dao.ClientService;
import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.ClientEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

///
/// Les méthode du contrôleur répondent à des sollicitations
/// des pages JSP

@Controller
public class ClientControleur {

//	private static final Logger logger = LoggerFactory.getLogger(MultiControleur.class);

	@RequestMapping(value = "listerClient.htm")
	public ModelAndView afficherLesStages(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String destinationPage;
		try {
			// HttpSession session = request.getSession();
			ClientService unClientService = new ClientService();
			request.setAttribute("mesClients", unClientService.consulterListeClients());
			destinationPage = "vues/listerClient";
		} catch (MonException e) {
			request.setAttribute("MesErreurs", e.getMessage());
			destinationPage = "Erreur";

		}
		return new ModelAndView(destinationPage);
	}



	@RequestMapping(value = "insererClient.htm")
	public ModelAndView insererClient(HttpServletRequest request,
									  HttpServletResponse response) throws Exception {

		String destinationPage = "";
		try {
			ClientEntity unClient = new ClientEntity();
			unClient.setNom(request.getParameter("txtnom"));
			unClient.setPrenom(request.getParameter("txtprenom"));

			unClient.setDateNaissance(Date.valueOf(request.getParameter("dDateNaiss")));

			ClientService unClientService = new ClientService();
			unClientService.insertClient(unClient);

			request.setAttribute("mesClients", unClientService.consulterListeClients());
			destinationPage = "vues/listerClient";
		} catch (Exception e) {
			request.setAttribute("MesErreurs", e.getMessage());
			destinationPage = "vues/Erreur";
		}

		return new ModelAndView(destinationPage);
	}

	@RequestMapping(value = "ajouterClient.htm")
	public ModelAndView ajouterClient(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String destinationPage = "";
		try {
			destinationPage = "vues/ajouterClient";
		} catch (Exception e) {
			request.setAttribute("MesErreurs", e.getMessage());
			destinationPage = "vues/Erreur";
		}

		return new ModelAndView(destinationPage);
	}


	@RequestMapping(value = "modifierClient.htm")
	public ModelAndView modifierClient(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String destinationPage = "";
		try {
			ClientService unClientService = new ClientService();
			int numero = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("item", unClientService.consulterClientById(numero));

			destinationPage = "vues/modifierClient";
		} catch (Exception e) {
			request.setAttribute("MesErreurs", e.getMessage());
			destinationPage = "vues/Erreur";
		}

		return new ModelAndView(destinationPage);
	}


	@RequestMapping(value = "supprimerClient.htm")
	public ModelAndView supprimerClient(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String destinationPage = "";

		try {
			ClientService unClientService = new ClientService();
			int numero = Integer.parseInt(request.getParameter("id"));

			//ClientEntity unClient = unService.ClientById(numero);
			unClientService.supprimerClient(numero);


			request.setAttribute("mesClients", unClientService.consulterListeClients());
			destinationPage = "vues/listerClient";

		} catch (Exception e) {
			request.setAttribute("MesErreurs", e.getMessage());
			destinationPage = "vues/Erreur";
		}

		return new ModelAndView(destinationPage);
	}

	@RequestMapping(value = "insererClientModif.htm")
	public ModelAndView insererClientModif(HttpServletRequest request,
											 HttpServletResponse response) throws Exception {

		String destinationPage = "";
		try {
			int numero= Integer.parseInt(request.getParameter("idClient"));
			//Modification de l'Client
			ClientService unClientService = new ClientService();


			ClientEntity unClient = unClientService.consulterClientById(numero);
			unClient.setNom(request.getParameter("txtnom"));
			unClient.setPrenom(request.getParameter("txtprenom"));
			unClient.setDateNaissance(Date.valueOf(request.getParameter("dDateNaiss")));

			unClientService.majClient(unClient);


			request.setAttribute("mesClients", unClientService.consulterListeClients());
			destinationPage = "vues/listerClient";
		} catch (Exception e) {
			request.setAttribute("MesErreurs", e.getMessage());
			destinationPage = "vues/Erreur";
		}

		return new ModelAndView(destinationPage);
	}
/*
	// /
	// / Affichage de la page d'accueil
	// /
	@RequestMapping(value = "index.htm", method = RequestMethod.GET)
	public ModelAndView Afficheindex(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("index");
	}

	// /
	// / Affichage de la page d'accueil
	// /
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView Afficheindex2(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("index");
	}
	// /
	// / Affichage de la page d'accueil
	// /
	@RequestMapping(value = "erreur.htm", method = RequestMethod.GET)
	public ModelAndView AfficheErreur(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("vues/Erreur");
	}
*/
}
