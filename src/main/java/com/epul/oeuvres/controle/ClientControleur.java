package com.epul.oeuvres.controle;


import com.epul.oeuvres.dao.ClientService;
import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.ClientEntity;
import com.epul.oeuvres.utilitaires.FonctionsUtiles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

@Controller
public class ClientControleur {

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
	public ModelAndView insererClient(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String destinationPage = "";
		try {
			ClientEntity unClient = new ClientEntity();
			unClient.setNom(request.getParameter("txtnom"));
			unClient.setPrenom(request.getParameter("txtprenom"));
			unClient.setDateNaissance(Date.valueOf(request.getParameter("dDateNaiss")));
			unClient.setLogin(unClient.getPrenom()+"."+unClient.getNom());
			unClient.setMotdepasse(FonctionsUtiles.md5("secret"));
			unClient.setRole("USER");

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
	public ModelAndView insererClientModif(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String destinationPage = "";
		try {
			int numero= Integer.parseInt(request.getParameter("idClient"));

			ClientService unClientService = new ClientService();
			ClientEntity unClient = unClientService.consulterClientById(numero);

			unClient.setNom(request.getParameter("txtnom"));
			unClient.setPrenom(request.getParameter("txtprenom"));
			unClient.setDateNaissance(Date.valueOf(request.getParameter("dDateNaiss")));

			unClient.setRole(request.getParameter("txtrole").toUpperCase());
			unClient.setLogin(unClient.getPrenom()+"."+unClient.getNom());

			unClientService.majClient(unClient);

			request.setAttribute("mesClients", unClientService.consulterListeClients());

			destinationPage = "vues/listerClient";
		} catch (Exception e) {
			request.setAttribute("MesErreurs", e.getMessage());
			destinationPage = "vues/Erreur";
		}

		return new ModelAndView(destinationPage);
	}
}
