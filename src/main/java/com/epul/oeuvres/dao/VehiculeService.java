package com.epul.oeuvres.dao;

import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.TypeVehiculeEntity;
import com.epul.oeuvres.metier.VehiculeEntity;

import javax.persistence.EntityTransaction;
import java.util.List;

public class VehiculeService extends EntityService {

	public List<VehiculeEntity> consulterListeVehicules() throws MonException {
		List<VehiculeEntity> mesVehicules = null;
		try
		{
			EntityTransaction transac = startTransaction();
			transac.begin();
			mesVehicules = (List<VehiculeEntity>)
					entitymanager.createQuery(
							"SELECT a FROM VehiculeEntity a " +
									"ORDER BY a.idVehicule").getResultList();
			entitymanager.close();
		}catch (RuntimeException e) {
			new MonException("Erreur de lecture", e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return mesVehicules;
	}

	public VehiculeEntity consulterVehiculeById(int numero) throws MonException {
		List<VehiculeEntity> mesVehicules = null;
		VehiculeEntity vehicule = new VehiculeEntity();
		try {
			EntityTransaction transac = startTransaction();
			transac.begin();

			mesVehicules = (List<VehiculeEntity>)
						entitymanager.createQuery(
								"SELECT a FROM VehiculeEntity a " +
										"WHERE a.idVehicule="+numero).getResultList();
			vehicule = mesVehicules.get(0);
			entitymanager.close();
		}catch (RuntimeException e) {
			new MonException("Erreur de lecture", e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return vehicule;
	}

	/*public List getNbVehiculesByType(int typeId) throws MonException {
		List typesVehicule = null;
		try
		{
			EntityTransaction transac = startTransaction();
			transac.begin();
			typesVehicule =
					entitymanager.createQuery("SELECT COUNT(v) FROM VehiculeEntity v WHERE v.typeVehicule="+ typeId).getResultList();
			entitymanager.close();
		}catch (RuntimeException e) {
			new MonException("Erreur de lecture", e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return typesVehicule;
	}*/

    public List<TypeVehiculeEntity> getVehiculeType(int vehiculeId) throws MonException {
        List<TypeVehiculeEntity> typesVehicule = null;
        try
        {
            EntityTransaction transac = startTransaction();
            transac.begin();
            typesVehicule = (List<TypeVehiculeEntity>)
                    entitymanager.createQuery("SELECT t FROM TypeVehiculeEntity t JOIN VehiculeEntity v ON t.idTypeVehicule = v.typeVehicule " +
                            "WHERE v.idVehicule="+ vehiculeId).getResultList();
            entitymanager.close();
        }catch (RuntimeException e) {
            new MonException("Erreur de lecture", e.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
        }
        return typesVehicule;
    }

    public void supprimerVehicule(VehiculeEntity vehiculeEntity) throws MonException {
        try {
            EntityTransaction transac = startTransaction();
            transac.begin();
            entitymanager.remove(entitymanager.find(VehiculeEntity.class, vehiculeEntity.getIdVehicule()));
            transac.commit();
            entitymanager.close();
        } catch (MonException e) {
            throw e;
        }
        catch (Exception exc) {
            throw new MonException(exc.getMessage(), "systeme");
        }
    }

	public void insertVehicule(VehiculeEntity vehiculeEntity) throws MonException {
		try {
			EntityTransaction transac = startTransaction();
			transac.begin();
			entitymanager.persist(vehiculeEntity);
			transac.commit();
			entitymanager.close();
		} catch (RuntimeException e) {
			new MonException("Erreur de lecture", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
