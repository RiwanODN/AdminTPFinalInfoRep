package com.epul.oeuvres.controle;

import com.epul.oeuvres.dao.Service;
import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.ClientEntity;
import com.epul.oeuvres.metier.UtilisateurEntity;
import com.epul.oeuvres.utilitaires.FonctionsUtiles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;


@Controller
public class Authentification {

    @RequestMapping(value = "login.htm", method = RequestMethod.GET)
    public ModelAndView pageLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("vues/formLogin");
    }

    @RequestMapping(value = "controleLogin.htm")
    public ModelAndView controleLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage;
        {
            String login = request.getParameter("login");
            String pwd = request.getParameter("pwd");
            String message ="";
            try {

                Service unService = new Service();
                ClientEntity unUtilisateur = unService.getUtilisateur(login);

                if (unUtilisateur != null) {
                    try {
                        String pwdmd5 = FonctionsUtiles.md5(pwd);
                        if (unUtilisateur.getMotdepasse().equals(pwdmd5)) {
                            if(unUtilisateur.getRole().equalsIgnoreCase("ADMIN")){
                                HttpSession session = request.getSession();
                                session.setAttribute("id", unUtilisateur.getIdClient());
                                destinationPage = "/index";
                            } else {
                                message = "Utilisateur non autorisé";
                                request.setAttribute("message", message);
                                destinationPage = "/vues/formLogin";
                            }

                        } else {
                            message = "mot de passe erroné";
                            request.setAttribute("message", message);
                            destinationPage = "/vues/formLogin";
                        }
                    } catch (NoSuchAlgorithmException e) {
                        request.setAttribute("MesErreurs", e.getMessage());
                        destinationPage = "/vues/Erreur";
                    }
                } else {
                    message = "login erroné";
                    request.setAttribute("message", message);
                    destinationPage = "/vues/formLogin";
                }
            } catch (MonException e) {
                request.setAttribute("MesErreurs", e.getMessage());
                destinationPage = "/vues/Erreur";
            }
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "logout.htm", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        session.invalidate();

        return new ModelAndView("index");
    }

}
