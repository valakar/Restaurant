package org.com.restaurant.dao.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class GenericJpaDao<T, ID extends Serializable> implements GenericDao<T, ID> {
	private final static String UNIT_NAME = "restaurantPU";
	
	private Class<T> persistentClass;
	
	@PersistenceContext(unitName = UNIT_NAME)
	private EntityManager entityManager;
	
	public GenericJpaDao(Class<T> persistentClass) {
		super();
		this.persistentClass = persistentClass;
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
    public void setEntityManager(EntityManager entityManager) {
            this.entityManager = entityManager;
    }
    
	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	@Override
	public void save(T entity) {
		if (getEntityManager().contains(entity)) {
			getEntityManager().merge(entity);
//			getEntityManager().flush();
        } 
        getEntityManager().persist(entity);
//        getEntityManager().flush();
        
//		getEntityManager().persist(entity);
//		T entitySave = getEntityManager().merge(entity);
//		getEntityManager().flush();
//		return entitySave;
	}

	@Override
	public T findById(ID id) {
		T entity = (T) getEntityManager()
//				.getReference(getPersistentClass(), id);
				.find(getPersistentClass(), id);
//		getEntityManager().flush();
//				find(getPersistentClass(), id);
		return entity;
	}

	@Override
	public T update(T entity) {
		T mergedEntity = getEntityManager().merge(entity);
//		getEntityManager().flush();
		return mergedEntity;
	}

	@Override
	public void delete(ID id, Class<T> entity) {
		T entityToBeRemoved = getEntityManager().getReference(entity, id);
		getEntityManager().remove(entityToBeRemoved);
//		getEntityManager().flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		return getEntityManager()
				.createQuery("SELECT x FROM " + getPersistentClass().getSimpleName() + " x")
				.getResultList();
	}

	@Override
	public void flush() {
		getEntityManager().flush();
	}
	
    // Using the unchecked because JPA does not have a
    // ery.getSingleResult()<T> method
    @SuppressWarnings("unchecked")
    protected T findOneResult(String namedQuery, Map<String, Object> parameters) {
        T result = null;
 
        try {
            Query query = entityManager.createNamedQuery(namedQuery);
 
            // Method that will populate parameters if they are passed not null and empty
            if (parameters != null && !parameters.isEmpty()) {
                populateQueryParameters(query, parameters);
            }
 
            result = (T) query.getSingleResult();
 
        } catch (Exception e) {
            System.out.println("Error while running query: " + e.getMessage());
            e.printStackTrace();
        }
 
        return result;
    }
 
    private void populateQueryParameters(Query query, Map<String, Object> parameters) {
 
        for (Entry<String, Object> entry : parameters.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
    }


}
