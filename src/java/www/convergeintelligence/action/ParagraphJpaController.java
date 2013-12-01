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
import www.convergeintelligence.datamodel.Paragraph;

/**
 *
 * @author tain198127
 */
public class ParagraphJpaController implements Serializable {

    public ParagraphJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Paragraph paragraph) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(paragraph);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findParagraph(paragraph.getGuid()) != null) {
                throw new PreexistingEntityException("Paragraph " + paragraph + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Paragraph paragraph) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            paragraph = em.merge(paragraph);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = paragraph.getGuid();
                if (findParagraph(id) == null) {
                    throw new NonexistentEntityException("The paragraph with id " + id + " no longer exists.");
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
            Paragraph paragraph;
            try {
                paragraph = em.getReference(Paragraph.class, id);
                paragraph.getGuid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The paragraph with id " + id + " no longer exists.", enfe);
            }
            em.remove(paragraph);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Paragraph> findParagraphEntities() {
        return findParagraphEntities(true, -1, -1);
    }

    public List<Paragraph> findParagraphEntities(int maxResults, int firstResult) {
        return findParagraphEntities(false, maxResults, firstResult);
    }

    private List<Paragraph> findParagraphEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Paragraph.class));
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

    public Paragraph findParagraph(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Paragraph.class, id);
        } finally {
            em.close();
        }
    }

    public int getParagraphCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Paragraph> rt = cq.from(Paragraph.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
