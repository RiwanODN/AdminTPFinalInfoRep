package com.epul.oeuvres.dao;

import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.ReservationEntity;
import javax.persistence.EntityTransaction;
import java.util.List;

public class ReservationService extends EntityService {

    public void insertReservation(ReservationEntity uneRes) throws MonException {
        try
        {
            EntityTransaction transac = startTransaction();
            transac.begin();
            entitymanager.persist(uneRes);
            transac.commit();
            entitymanager.close();
        }
        catch (RuntimeException e)
        {
            new MonException("Erreur de lecture", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ReservationEntity> consulterListeReservation() throws MonException {
        List<ReservationEntity> mesReservations = null;
        try
        {
            EntityTransaction transac = startTransaction();
            transac.begin();
            mesReservations = (List<ReservationEntity>)
                    entitymanager.createQuery(
                            "SELECT r FROM ReservationEntity r ").getResultList();
            entitymanager.close();
        }
        catch (RuntimeException e)
        {
            new MonException("Erreur de lecture", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mesReservations;
    }
}
