package com.epul.oeuvres.dao;

import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.BorneEntity;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class BorneService extends EntityService {

	public List<BorneEntity> consulterListeBornes() throws MonException {
		List<BorneEntity> mesBornes = null;
		try
		{
			EntityTransaction transac = startTransaction();
			transac.begin();
			mesBornes = (List<BorneEntity>)
					entitymanager.createQuery(
							"SELECT a FROM BorneEntity a " +
									"ORDER BY a.idBorne").getResultList();
			entitymanager.close();
		}catch (RuntimeException e) {
			new MonException("Erreur de lecture", e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return mesBornes;
	}

	public List<BorneEntity> consulterListeBornesParStation(int numero) throws MonException {
		List<BorneEntity> mesBornes = null;
		try
		{
			EntityTransaction transac = startTransaction();
			transac.begin();
			mesBornes = (List<BorneEntity>)
					entitymanager.createQuery(
							"SELECT a FROM BorneEntity a " +
									"WHERE a.station="+numero).getResultList();
			entitymanager.close();
		}catch (RuntimeException e) {
			new MonException("Erreur de lecture", e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return mesBornes;
	}

	public BorneEntity consulterBorneById(int numero) throws MonException {
		List<BorneEntity> mesBornes = null;
		BorneEntity borne = new BorneEntity();
		try {
			EntityTransaction transac = startTransaction();
			transac.begin();

			mesBornes = (List<BorneEntity>)
						entitymanager.createQuery(
								"SELECT a FROM BorneEntity a " +
										"WHERE a.idBorne="+numero).getResultList();
			borne = mesBornes.get(0);
			entitymanager.close();
		}catch (RuntimeException e) {
			new MonException("Erreur de lecture", e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return borne;
	}

	public void insertBorne(BorneEntity uneBorne) throws MonException {
		try
		{
			EntityTransaction transac = startTransaction();
			transac.begin();
			entitymanager.persist(uneBorne);
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

	public void supprimerBorne(BorneEntity Borne) throws MonException {
		try
		{

			EntityTransaction transac = startTransaction();
			transac.begin();
			String qryString = "delete from BorneEntity s where s.idBorne="+Borne.getIdBorne();
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

	public void modifBorne(BorneEntity Borne) throws MonException {
		try
		{
			EntityTransaction transac = startTransaction();
			transac.begin();
			entitymanager.merge(Borne);
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
