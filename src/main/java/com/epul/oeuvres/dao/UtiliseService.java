package com.epul.oeuvres.dao;

import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.UtiliseEntity;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class UtiliseService extends EntityService {

	public List<UtiliseEntity> consulterListeUtilises() throws MonException {
		List<UtiliseEntity> mesUtilises = null;
		try
		{
			EntityTransaction transac = startTransaction();
			transac.begin();
			mesUtilises = (List<UtiliseEntity>)
					entitymanager.createQuery(
							"SELECT a FROM UtiliseEntity a " +
									"ORDER BY a.vehicule.idVehicule").getResultList();
			entitymanager.close();
		}catch (RuntimeException e) {
			new MonException("Erreur de lecture", e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return mesUtilises;
	}

	public List<UtiliseEntity> consulterListeUtilisesById(int Vehicule,int Client) throws MonException {
		List<UtiliseEntity> mesUtilises = null;
		try
		{
			EntityTransaction transac = startTransaction();
			transac.begin();
			mesUtilises = (List<UtiliseEntity>)
					entitymanager.createQuery(
							"SELECT a FROM UtiliseEntity a " +
									"WHERE a.vehicule="+Vehicule+" and a.client="+Client).getResultList();
			entitymanager.close();
		}catch (RuntimeException e) {
			new MonException("Erreur de lecture", e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return mesUtilises;
	}



	public void insertUtilise(UtiliseEntity uneUtilise) throws MonException {
		try
		{
			EntityTransaction transac = startTransaction();
			transac.begin();
			entitymanager.persist(uneUtilise);
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

	public void supprimerUtilise(UtiliseEntity Utilise) throws MonException {
		try
		{
			EntityTransaction transac = startTransaction();
			transac.begin();
			String qryString = "delete from UtiliseEntity s where s.vehicule="+Utilise.getVehicule()+" and s.client="+Utilise.getClient()+" and s.date="+Utilise.getDate();
			Query query = entitymanager.createQuery(qryString);
			int count = query.executeUpdate();
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

	public void modifUtilise(UtiliseEntity Utilise) throws MonException {
		try
		{
			EntityTransaction transac = startTransaction();
			transac.begin();
			entitymanager.merge(Utilise);
			entitymanager.flush();
			transac.commit();
		}
		catch (RuntimeException e)
		{
			new MonException("Erreur de lecture", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
