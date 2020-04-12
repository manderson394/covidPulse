package edu.matc.covidPulse.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

/**
 * Facilitates transactions with the database for any class type.
 * @author Matt Anderson
 * @version 11
 *
 * @param <T> the class type parameter
 */
public class GenericDao<T> {

    private Class<T> type;
    private final Logger logger = LogManager.getLogger(this.getClass());
    private SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * Instantiates a new Generic dao.
     *
     * @param type the type
     */
    public GenericDao(Class<T> type) {
        this.type = type;
    }

    /**
     * Gets entity by id.
     *
     * @param <T> the type parameter
     * @param id  the id
     * @return the entity with matching id
     */
    public <T>T getById(int id) {
        Session session = sessionFactory.openSession();
        T entity = (T)session.get(type, id);
        session.close();
        return entity;
    }

    /**
     * Gets all entities from the database.
     *
     * @return all the entities from the database.
     */
    public List<T> getAll() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T>  query = builder.createQuery(type);
        Root<T> root = query.from(type);
        List<T> list = session.createQuery(query).getResultList();
        session.close();
        return list;
    }

    /**
     * Gets by property equal from the database.
     *
     * @param propertyName  the property name
     * @param propertyValue the property value
     * @return the entities with equivalent property values
     */
    public List<T> getByPropertyEqual(String propertyName, Object propertyValue) {
        Session session = sessionFactory.openSession();
        logger.debug("Searching for {} with {} = {}.", type, propertyName, propertyValue);
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        Expression<String> propertyPath = root.get(propertyName);
        query.select(root).where(builder.equal(propertyPath, propertyValue));
        List<T> entities = session.createQuery(query).getResultList();
        session.close();
        return entities;
    }

    /**
     * Gets by property like from the database.
     *
     * @param propertyName  the property name
     * @param propertyValue the property value
     * @return the entities with like property values
     */
    public List<T> getByPropertyLike(String propertyName, String propertyValue) {
        Session session = sessionFactory.openSession();
        logger.debug("Searching for {} with {} like {}.", type, propertyName, propertyValue);
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        Expression<String> propertyPath = root.get(propertyName);
        query.where(builder.like(propertyPath, "%" + propertyValue + "%"));
        List<T> entities = session.createQuery( query ).getResultList();
        session.close();
        return entities;
    }

    /**
     * Save or update the database.
     *
     * @param entity the entity
     */
    public void saveOrUpdate(T entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
        session.close();
    }

    /**
     * Insert into the database.
     *
     * @param entity the entity
     * @return the int
     */
    public String insert(T entity) {
        String id = null;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (String)session.save(entity);
        transaction.commit();
        session.close();
        return id;
    }

    /**
     * Delete from the database.
     *
     * @param entity the entity
     */
    public void delete(T entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
    }

    /**
     * Gets by a date range.
     *
     * @param rangeProperty the range property
     * @param lowerBound    the lower bound
     * @param upperBound    the upper bound
     * @return the by range
     */
    public List<T> getByRange(String rangeProperty, LocalDate lowerBound, LocalDate upperBound) {
        Session session = sessionFactory.openSession();
        logger.debug("Searching on {} with bounds on {}: Lower: {}, Upper: {}.", type,
                rangeProperty, lowerBound, upperBound);
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        Expression<LocalDate> rangePropertyPath = root.get(rangeProperty);
        query.where(builder.between(rangePropertyPath, lowerBound, upperBound));
        List<T> entities = session.createQuery( query ).getResultList();
        session.close();
        return entities;
    }

    /**
     * Gets by a date range and an additional criteria that is equal to a value.
     *
     * @param rangeProperty the range property
     * @param lowerBound    the lower bound
     * @param upperBound    the upper bound
     * @param propertyTwo   the property two
     * @param valueTwo      the value two
     * @return the by range two param
     */
    public List<T> getByRangeTwoParam(String rangeProperty, LocalDate lowerBound, LocalDate upperBound, String propertyTwo, Object valueTwo) {
        Session session = sessionFactory.openSession();
        logger.debug("Searching on {} with bounds on {}: Lower: {}, Upper: {}, & {} equal to {}.", type,
                rangeProperty, lowerBound, upperBound, propertyTwo, valueTwo);
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        Expression<LocalDate> rangePropertyPath = root.get(rangeProperty);
        Expression<String> propertyTwoPath = root.get(propertyTwo);
        query.where(builder.between(rangePropertyPath, lowerBound, upperBound),
                builder.equal(propertyTwoPath, valueTwo));
        List<T> entities = session.createQuery( query ).getResultList();
        session.close();
        return entities;
    }
}