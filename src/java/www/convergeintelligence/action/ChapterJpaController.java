/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package www.convergeintelligence.action;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import www.convergeintelligence.action.exceptions.NonexistentEntityException;
import www.convergeintelligence.action.exceptions.PreexistingEntityException;
import www.convergeintelligence.datamodel.Chapter;

/**
 *
 * @author tain198127
 */
public class ChapterJpaController implements Serializable {

    public ChapterJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Chapter chapter) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(chapter);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findChapter(chapter.getId()) != null) {
                throw new PreexistingEntityException("Chapter " + chapter + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Chapter chapter) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            chapter = em.merge(chapter);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = chapter.getId();
                if (findChapter(id) == null) {
                    throw new NonexistentEntityException("The chapter with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BigDecimal id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Chapter chapter;
            try {
                chapter = em.getReference(Chapter.class, id);
                chapter.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The chapter with id " + id + " no longer exists.", enfe);
            }
            em.remove(chapter);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Chapter> findChapterEntities() {
        return findChapterEntities(true, -1, -1);
    }

    public List<Chapter> findChapterEntities(int maxResults, int firstResult) {
        return findChapterEntities(false, maxResults, firstResult);
    }

    private List<Chapter> findChapterEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Chapter.class));
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

    public Chapter findChapter(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Chapter.class, id);
        } finally {
            em.close();
        }
    }

    public int getChapterCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Chapter> rt = cq.from(Chapter.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
