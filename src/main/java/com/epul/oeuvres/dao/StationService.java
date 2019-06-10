package com.epul.oeuvres.dao;

import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.StationEntity;

import javax.persistence.EntityTransaction;
import java.util.List;

public class StationService extends EntityService {

    public List<StationEntity> consulterListeStations() throws MonException {
        List<StationEntity> mesStations = null;
        try
        {
            EntityTransaction transac = startTransaction();
            transac.begin();
            mesStations = (List<StationEntity>)
                    entitymanager.createQuery(
                            "SELECT a FROM StationEntity a " +
                                    "ORDER BY a.idStation").getResultList();
            entitymanager.close();
        }catch (RuntimeException e) {
            new MonException("Erreur de lecture", e.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
        }
        return mesStations;
    }

    public StationEntity consulterStationById(int numero) throws MonException {
        List<StationEntity> mesStations = null;
        StationEntity station = new StationEntity();
        try {
            EntityTransaction transac = startTransaction();
            transac.begin();

            mesStations = (List<StationEntity>)
                    entitymanager.createQuery(
                            "SELECT a FROM StationEntity a " +
                                    "WHERE a.idStation="+numero).getResultList();
            station = mesStations.get(0);
            entitymanager.close();
        }catch (RuntimeException e) {
            new MonException("Erreur de lecture", e.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
        }
        return station;
    }

    public void insertStation(StationEntity stationEntity) throws MonException {
        try {
            EntityTransaction transac = startTransaction();
            transac.begin();
            entitymanager.persist(stationEntity);
            transac.commit();
            entitymanager.close();
        } catch (RuntimeException e) {
            new MonException("Erreur de lecture", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void supprimerStation(StationEntity stationEntity) throws MonException {
        try {
            EntityTransaction transac = startTransaction();
            transac.begin();
            entitymanager.remove(entitymanager.find(StationEntity.class, stationEntity.getIdStation()));
            transac.commit();
            entitymanager.close();
        } catch (MonException e) {
            throw e;
        }
        catch (Exception exc) {
            throw new MonException(exc.getMessage(), "systeme");
        }
    }


}
