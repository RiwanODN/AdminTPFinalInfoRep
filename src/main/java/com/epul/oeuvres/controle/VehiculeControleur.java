package com.epul.oeuvres.controle;

import com.epul.oeuvres.dao.TypeVehiculeService;
import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.TypeVehiculeEntity;
import com.epul.oeuvres.metier.VehiculeEntity;
import com.epul.oeuvres.dao.VehiculeService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;


@Controller
public class VehiculeControleur {

    @RequestMapping(value = "listerVehicule.htm")
    public ModelAndView listerVehicule(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage;
        try {
            HashMap<VehiculeEntity, TypeVehiculeEntity> map = new HashMap<>();
            VehiculeService vehiculeService = new VehiculeService();
            List<VehiculeEntity> vehicules = vehiculeService.consulterListeVehicules();
            for(VehiculeEntity vehiculeEntity : vehicules) {
                List<TypeVehiculeEntity> typeVehiculeEntities =
                        vehiculeService.getVehiculeType(vehiculeEntity.getIdVehicule());
                map.put(vehiculeEntity, typeVehiculeEntities.get(0));
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


}