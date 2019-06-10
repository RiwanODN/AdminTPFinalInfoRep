package com.epul.oeuvres.controle;


import com.epul.oeuvres.dao.BorneService;

import com.epul.oeuvres.dao.StationService;
import com.epul.oeuvres.dao.VehiculeService;
import com.epul.oeuvres.meserreurs.MonException;

import com.epul.oeuvres.metier.BorneEntity;
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
public class BorneControleur {

//	private static final Logger logger = LoggerFactory.getLogger(MultiControleur.class);

    @RequestMapping(value = "listerBorne.htm")
    public ModelAndView afficherBornes(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage;
        try {
            // HttpSession session = request.getSession();
            BorneService unService = new BorneService();
            request.setAttribute("mesBornes", unService.consulterListeBornes());
            destinationPage = "/vues/listerBorne";
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";

        }
        return new ModelAndView(destinationPage);
    }



    @RequestMapping(value = "insererBorne.htm")
    public ModelAndView insererBorne(HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

        String destinationPage = "";
        StationService stationServ = new StationService();
        VehiculeService vehiculeServ=new VehiculeService();

        BorneService service = new BorneService();

        try {
            BorneEntity Borne = new BorneEntity();
            
            Borne.setEtatBorne(Byte.valueOf("0"));

            StationEntity stat = stationServ.consulterStationById(Integer.parseInt(request.getParameter("station")));
            Borne.setStation(stat);
            Borne.setVehicule(null);

           /* VehiculeEntity vehiculeEn =vehiculeServ.consulterVehiculeById(Integer.parseInt(request.getParameter("Vehicule")));
            Borne.setVehicule(vehiculeEn);*/

            service.insertBorne(Borne);

            request.setAttribute("mesBornes", service.consulterListeBornes());

            destinationPage = "/vues/listerBorne";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }

        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "ajouterBorne.htm")
    public ModelAndView ajouterBorne(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String destinationPage = "";
        try {
            StationService stationService = new StationService();

            request.setAttribute("stations", stationService.consulterListeStations());

            destinationPage = "vues/ajouterBorne";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }

        return new ModelAndView(destinationPage);
    }


    @RequestMapping(value = "modifierBorne.htm")
    public ModelAndView modifierBorne(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage = "";
        try {
            BorneService unService = new BorneService();
            StationService stationService = new StationService();
            VehiculeService vehiculeService = new VehiculeService();

            int numero = Integer.parseInt(request.getParameter("id"));

            request.setAttribute("Borne", unService.consulterBorneById(numero));
            request.setAttribute("vehicules", vehiculeService.consulterListeVehicules());
            request.setAttribute("stations", stationService.consulterListeStations());


            destinationPage = "vues/modifierBorne";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }

        return new ModelAndView(destinationPage);
    }


    @RequestMapping(value = "ajoutModificationBorne.htm")
    public ModelAndView ajoutModifBorne(HttpServletRequest request,
                                         HttpServletResponse response) throws Exception {

        BorneService service = new BorneService();
        StationService stationServ = new StationService();
        VehiculeService vehiculeService= new VehiculeService();

        String destinationPage = "/vues/listerBorne";

        try {

            int numero= Integer.parseInt(request.getParameter("borne"));

            BorneEntity BorneEnt = service.consulterBorneById(numero);


            StationEntity stat = stationServ.consulterStationById(Integer.parseInt(request.getParameter("station")));
            BorneEnt.setStation(stat);


            if(request.getParameter("vehicule").equals("")){

                BorneEnt.setVehicule(null);
                BorneEnt.setEtatBorne(Byte.valueOf("0"));
            }
            else {

                VehiculeEntity vehiculeEn =vehiculeService.consulterVehiculeById(Integer.parseInt(request.getParameter("vehicule")));
                BorneEnt.setVehicule(vehiculeEn);
                BorneEnt.setEtatBorne(Byte.valueOf("1"));
            }


            service.modifBorne(BorneEnt);

            request.setAttribute("mesBornes", service.consulterListeBornes());
            destinationPage = "/vues/listerBorne";
        } catch (Exception e) {
            System.out.println("erreur ma gueule");
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }

        return new ModelAndView(destinationPage);
    }


    @RequestMapping(value = "supprimerBorne.htm")
    public ModelAndView supprimerBorne(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage = "";
        try {
            BorneService unService = new BorneService();

            int numero = Integer.parseInt(request.getParameter("id"));
            BorneEntity Borne = unService.consulterBorneById(numero);

            unService.supprimerBorne(Borne);

            request.setAttribute("mesBornes", unService.consulterListeBornes());

            destinationPage = "/vues/listerBorne";

        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }

        return new ModelAndView(destinationPage);
    }


}
