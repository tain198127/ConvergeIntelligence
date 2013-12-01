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
import www.convergeintelligence.datamodel.Section;

/**
 *
 * @author tain198127
 */
public class SectionJpaController implements Serializable {

    public SectionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Section section) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(section);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSection(section.getGuid()) != null) {
                throw new PreexistingEntityException("Section " + section + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Section section) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            section = em.merge(section);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = section.getGuid();
                if (findSection(id) == null) {
                    throw new NonexistentEntityException("The section with id " + id + " no longer exists.");
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
            Section section;
            try {
                section = em.getReference(Section.class, id);
                section.getGuid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The section with id " + id + " no longer exists.", enfe);
            }
            em.remove(section);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Section> findSectionEntities() {
        return findSectionEntities(true, -1, -1);
    }

    public List<Section> findSectionEntities(int maxResults, int firstResult) {
        return findSectionEntities(false, maxResults, firstResult);
    }

    private List<Section> findSectionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Section.class));
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

    public Section findSection(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Section.class, id);
        } finally {
            em.close();
        }
    }

    public int getSectionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Section> rt = cq.from(Section.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
