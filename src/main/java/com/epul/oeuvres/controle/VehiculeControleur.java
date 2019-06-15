package com.epul.oeuvres.controle;

import com.epul.oeuvres.dao.*;
import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;


@Controller
public class VehiculeControleur {

    private HashMap<VehiculeEntity, TypeVehiculeEntity> map;
    private int vehiculeID=0;

    @RequestMapping(value = "listerVehicule.htm")
    public ModelAndView listerVehicule(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage;
        try {
            if(map == null) {
                map = new HashMap<>();
                VehiculeService vehiculeService = new VehiculeService();
                List<VehiculeEntity> vehicules = vehiculeService.consulterListeVehicules();
                for(VehiculeEntity vehiculeEntity : vehicules) {
                    List<TypeVehiculeEntity> typeVehiculeEntities =
                            vehiculeService.getVehiculeType(vehiculeEntity.getIdVehicule());
                    map.put(vehiculeEntity, typeVehiculeEntities.get(0));
                    if(vehiculeID<vehiculeEntity.getIdVehicule()){
                        vehiculeID = vehiculeEntity.getIdVehicule();
                    }
                }
            }


            /*HashMap<TypeVehiculeEntity, Object> map = new HashMap<>();
            VehiculeService vehiculeService = new VehiculeService();
            TypeVehiculeService typeVehiculeService = new TypeVehiculeService();
            for(TypeVehiculeEntity type: typeVehiculeService.consulterListeTypeVehicules()) {
                map.put(type, vehiculeService.getNbVehiculesByType(type.getIdTypeVehicule()).get(0));
            }*/

            request.setAttribute("map", map);
            destinationPage = "/vues/listerVehicule";
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "/vues/Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "supprimerVehicule.htm")
    public ModelAndView supprimerVehicule(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage;
        try {
            VehiculeService vehiculeService = new VehiculeService();
            String[] ids = request.getParameter("id").split(",");
            for(int i=0; i<ids.length; i++) {
                VehiculeEntity vehiculeEntity = vehiculeService.consulterVehiculeById(Integer.parseInt(ids[i]));
                vehiculeService.supprimerVehicule(vehiculeEntity);
                map.remove(vehiculeEntity);
            }

            request.setAttribute("map", map);
            destinationPage = "vues/listerVehicule";
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "/vues/Erreur";
        }
        return new ModelAndView(destinationPage);
    }
	
	@RequestMapping(value = "insererVehicule.htm")
    public ModelAndView insererVehicule(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String destinationPage = "";
        try {
			
			VehiculeEntity vehiculeEntity = new VehiculeEntity();
			vehiculeEntity.setRfid(Integer.parseInt(request.getParameter("rfid")));
			vehiculeEntity.setEtatBatterie(Integer.parseInt(request.getParameter("etat")));
			vehiculeEntity.setDisponibilite("LIBRE");
			vehiculeEntity.setTypeVehicule(Integer.parseInt(request.getParameter("typeId")));
			
			StationService stationService = new StationService();
			StationEntity stationEntity = stationService.consulterStationById(
			        Integer.parseInt(request.getParameter("stationId")));
			vehiculeEntity.setLatitude(stationEntity.getLatitude());
			vehiculeEntity.setLongitude(stationEntity.getLongitude());
			vehiculeEntity.setIdVehicule(++vehiculeID);
			
			VehiculeService vehiculeService = new VehiculeService();
			vehiculeService.insertVehicule(vehiculeEntity);

            List<TypeVehiculeEntity> typeVehiculeEntities =
                    vehiculeService.getVehiculeType(vehiculeEntity.getIdVehicule());
            map.put(vehiculeEntity, typeVehiculeEntities.get(0));

            request.setAttribute("map", map);

        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }
        destinationPage = "/vues/listerVehicule";
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "ajouterVehicule.htm")
    public ModelAndView ajouterVehicule(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String destinationPage = "";
        try {
			TypeVehiculeService typeVehiculeService = new TypeVehiculeService();
			request.setAttribute("mesTypes", typeVehiculeService.consulterListeTypeVehicules());
			
			StationService stationService = new StationService();
			request.setAttribute("mesStations", stationService.consulterListeStations());

            destinationPage = "vues/ajouterVehicule";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }
        return new ModelAndView(destinationPage);
    }


    @RequestMapping(value = "updateVehicule.htm")
    public ModelAndView updateVehicule(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String destinationPage = "";
        try {

            VehiculeService vehiculeService = new VehiculeService();
            int id = Integer.parseInt(request.getParameter("id"));
			VehiculeEntity vehiculeEntity = vehiculeService.consulterVehiculeById(id);
			map.remove(vehiculeEntity);

			vehiculeEntity.setRfid(Integer.parseInt(request.getParameter("rfid")));
			vehiculeEntity.setEtatBatterie(Integer.parseInt(request.getParameter("etat")));
			vehiculeEntity.setTypeVehicule(Integer.parseInt(request.getParameter("typeId")));

			StationService stationService = new StationService();
			StationEntity stationEntity = stationService.consulterStationById(
			        Integer.parseInt(request.getParameter("stationId")));
			vehiculeEntity.setLatitude(stationEntity.getLatitude());
			vehiculeEntity.setLongitude(stationEntity.getLongitude());

			vehiculeService.modifierVehicule(vehiculeEntity);

            List<TypeVehiculeEntity> typeVehiculeEntities =
                    vehiculeService.getVehiculeType(vehiculeEntity.getIdVehicule());
            map.put(vehiculeEntity, typeVehiculeEntities.get(0));

            request.setAttribute("map", map);

        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }
        destinationPage = "/vues/listerVehicule";
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "modifierVehicule.htm")
    public ModelAndView modifierVehicule(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String destinationPage = "";
        try {
			TypeVehiculeService typeVehiculeService = new TypeVehiculeService();
			request.setAttribute("mesTypes", typeVehiculeService.consulterListeTypeVehicules());

			VehiculeService vehiculeService = new VehiculeService();
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("veh", vehiculeService.consulterVehiculeById(id));

            StationService stationService = new StationService();
			request.setAttribute("mesStations", stationService.consulterListeStations());

            destinationPage = "vues/modifierVehicule";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "reserverVehicule.htm")
    public ModelAndView reserverVehicule(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String destinationPage = "";
        try {
            VehiculeService vehiculeService = new VehiculeService();
            int id = Integer.parseInt(request.getParameter("id"));

            ReservationService reservationService = new ReservationService();
            for(ReservationEntity reservationEntity: reservationService.consulterListeReservation()) {
                if(reservationEntity.getVehicule() == id) {

                    Timestamp currentDate = new Timestamp(System.currentTimeMillis());
                    if(currentDate.after(reservationEntity.getDateReservation()) &&
                        currentDate.before(reservationEntity.getDateEcheance())) {

                        return new ModelAndView("vues/alertDejaReserver");
                    }
                }
            }

            VehiculeEntity vehiculeEntity = vehiculeService.consulterVehiculeById(id);
            request.setAttribute("veh", vehiculeEntity);

            ClientService clientService = new ClientService();
            request.setAttribute("mesClients", clientService.consulterListeClients());

            destinationPage = "vues/reserverVehicule";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "confirmation.htm")
    public ModelAndView confirmation(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String destinationPage = "";
        try {
            int id = Integer.parseInt(request.getParameter("id"));

            ReservationService reservationService = new ReservationService();
            String dateR = request.getParameter("dateReservation") + " " + request.getParameter("timeReservation") + ":00";
            String dateE = request.getParameter("dateEcheance") + " " + request.getParameter("timeEcheance") + ":00";
            Timestamp dateReservation = Timestamp.valueOf(dateR);
            Timestamp dateEcheance = Timestamp.valueOf(dateE);

            ReservationEntity reservationEntity = new ReservationEntity();
            reservationEntity.setClient(Integer.parseInt(request.getParameter("clientId")));
            reservationEntity.setDateReservation(dateReservation);
            reservationEntity.setDateEcheance(dateEcheance);
            reservationEntity.setVehicule(id);

            reservationService.insertReservation(reservationEntity);

            request.setAttribute("map", map);
            destinationPage = "vues/listerVehicule";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }
        return new ModelAndView(destinationPage);
    }

}