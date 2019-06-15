package com.epul.oeuvres.controle;


import com.epul.oeuvres.dao.UtiliseService;
import com.epul.oeuvres.dao.StationService;
import com.epul.oeuvres.dao.VehiculeService;
import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.UtiliseEntity;
import com.epul.oeuvres.metier.StationEntity;
import com.epul.oeuvres.metier.VehiculeEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

///
/// Les m?thode du contr?leur r?pondent ? des sollicitations
/// des pages JSP

@Controller
public class UtiliseControleur {

//	private static final Logger logger = LoggerFactory.getLogger(MultiControleur.class);

    @RequestMapping(value = "listerUtilise.htm")
    public ModelAndView afficherUtilises(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage;
        try {
            // HttpSession session = request.getSession();
            UtiliseService unService = new UtiliseService();
            request.setAttribute("mesUtilises", unService.consulterListeUtilises());
            destinationPage = "/vues/listerUtilise";
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";

        }
        return new ModelAndView(destinationPage);
    }



   /* @RequestMapping(value = "insererUtilise.htm")
    public ModelAndView insererUtilise(HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

        String destinationPage = "";
        StationService stationServ = new StationService();
        VehiculeService vehiculeServ=new VehiculeService();

        UtiliseService service = new UtiliseService();

        try {
            UtiliseEntity Utilise = new UtiliseEntity();
            
            Utilise.setEtatUtilise(Byte.valueOf("0"));

            StationEntity stat = stationServ.consulterStationById(Integer.parseInt(request.getParameter("station")));
            Utilise.setStation(stat);
            Utilise.setVehicule(null);

           /* VehiculeEntity vehiculeEn =vehiculeServ.consulterVehiculeById(Integer.parseInt(request.getParameter("Vehicule")));
            Utilise.setVehicule(vehiculeEn);

            service.insertUtilise(Utilise);

            request.setAttribute("mesUtilises", service.consulterListeUtilises());

            destinationPage = "/vues/listerUtilise";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }

        return new ModelAndView(destinationPage);
    }*/
/*
    @RequestMapping(value = "ajouterUtilise.htm")
    public ModelAndView ajouterUtilise(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String destinationPage = "";
        try {
            StationService stationService = new StationService();

            request.setAttribute("stations", stationService.consulterListeStations());

            destinationPage = "vues/ajouterUtilise";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }

        return new ModelAndView(destinationPage);
    }


    @RequestMapping(value = "modifierUtilise.htm")
    public ModelAndView modifierUtilise(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage = "";
        try {
            UtiliseService unService = new UtiliseService();
            StationService stationService = new StationService();
            VehiculeService vehiculeService = new VehiculeService();

            int numero = Integer.parseInt(request.getParameter("id"));

            request.setAttribute("Utilise", unService.consulterUtiliseById(numero));
            request.setAttribute("vehicules", vehiculeService.consulterListeVehicules());
            request.setAttribute("stations", stationService.consulterListeStations());


            destinationPage = "vues/modifierUtilise";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }

        return new ModelAndView(destinationPage);
    }


    @RequestMapping(value = "ajoutModificationUtilise.htm")
    public ModelAndView ajoutModifUtilise(HttpServletRequest request,
                                         HttpServletResponse response) throws Exception {

        UtiliseService service = new UtiliseService();
        StationService stationServ = new StationService();
        VehiculeService vehiculeService= new VehiculeService();

        String destinationPage = "/vues/listerUtilise";

        try {

            int numero= Integer.parseInt(request.getParameter("Utilise"));

            UtiliseEntity UtiliseEnt = service.consulterUtiliseById(numero);


            StationEntity stat = stationServ.consulterStationById(Integer.parseInt(request.getParameter("station")));
            UtiliseEnt.setStation(stat);


            if(request.getParameter("vehicule").equals("")){

                UtiliseEnt.setVehicule(null);
                UtiliseEnt.setEtatUtilise(Byte.valueOf("0"));
            }
            else {

                VehiculeEntity vehiculeEn =vehiculeService.consulterVehiculeById(Integer.parseInt(request.getParameter("vehicule")));
                UtiliseEnt.setVehicule(vehiculeEn);
                UtiliseEnt.setEtatUtilise(Byte.valueOf("1"));
            }


            service.modifUtilise(UtiliseEnt);

            request.setAttribute("mesUtilises", service.consulterListeUtilises());
            destinationPage = "/vues/listerUtilise";
        } catch (Exception e) {
            System.out.println("erreur ma gueule");
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }

        return new ModelAndView(destinationPage);
    }
*/

   /* @RequestMapping(value = "supprimerUtilise.htm")
    public ModelAndView supprimerUtilise(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage = "";
        try {
            UtiliseService unService = new UtiliseService();

            int numero = Integer.parseInt(request.getParameter("id"));
            UtiliseEntity Utilise = unService.consulterUtiliseById(numero);

            unService.supprimerUtilise(Utilise);

            request.setAttribute("mesUtilises", unService.consulterListeUtilises());

            destinationPage = "/vues/listerUtilise";

        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }

        return new ModelAndView(destinationPage);
    }*/


}
