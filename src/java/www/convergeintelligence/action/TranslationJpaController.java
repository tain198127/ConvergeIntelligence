/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package www.convergeintelligence.action;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import www.convergeintelligence.action.exceptions.NonexistentEntityException;
import www.convergeintelligence.action.exceptions.PreexistingEntityException;
import www.convergeintelligence.datamodel.Translation;

/**
 *
 * @author tain198127
 */
public class TranslationJpaController implements Serializable {

    public TranslationJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Translation translation) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(translation);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTranslation(translation.getGuid()) != null) {
                throw new PreexistingEntityException("Translation " + translation + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Translation translation) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            translation = em.merge(translation);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = translation.getGuid();
                if (findTranslation(id) == null) {
                    throw new NonexistentEntityException("The translation with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Translation translation;
            try {
                translation = em.getReference(Translation.class, id);
                translation.getGuid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The translation with id " + id + " no longer exists.", enfe);
            }
            em.remove(translation);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Translation> findTranslationEntities() {
        return findTranslationEntities(true, -1, -1);
    }

    public List<Translation> findTranslationEntities(int maxResults, int firstResult) {
        return findTranslationEntities(false, maxResults, firstResult);
    }

    private List<Translation> findTranslationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Translation.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Translation findTranslation(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Translation.class, id);
        } finally {
            em.close();
        }
    }

    public int getTranslationCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Translation> rt = cq.from(Translation.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
