/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package www.convergeintelligence;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import www.convergeintelligence.datamodel.ArticleDetailView;
import www.convergeintelligence.exceptions.NonexistentEntityException;
import www.convergeintelligence.exceptions.PreexistingEntityException;

/**
 *
 * @author tain198127
 */
public class ArticleDetailViewJpaController implements Serializable {

    public ArticleDetailViewJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ArticleDetailView articleDetailView) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(articleDetailView);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findArticleDetailView(articleDetailView.getParagraphGuid()) != null) {
                throw new PreexistingEntityException("ArticleDetailView " + articleDetailView + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ArticleDetailView articleDetailView) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            articleDetailView = em.merge(articleDetailView);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = articleDetailView.getParagraphGuid();
                if (findArticleDetailView(id) == null) {
                    throw new NonexistentEntityException("The articleDetailView with id " + id + " no longer exists.");
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
            ArticleDetailView articleDetailView;
            try {
                articleDetailView = em.getReference(ArticleDetailView.class, id);
                articleDetailView.getParagraphGuid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The articleDetailView with id " + id + " no longer exists.", enfe);
            }
            em.remove(articleDetailView);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ArticleDetailView> findArticleDetailViewEntities() {
        return findArticleDetailViewEntities(true, -1, -1);
    }

    public List<ArticleDetailView> findArticleDetailViewEntities(int maxResults, int firstResult) {
        return findArticleDetailViewEntities(false, maxResults, firstResult);
    }

    private List<ArticleDetailView> findArticleDetailViewEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ArticleDetailView.class));
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

    public ArticleDetailView findArticleDetailView(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ArticleDetailView.class, id);
        } finally {
            em.close();
        }
    }

    public int getArticleDetailViewCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ArticleDetailView> rt = cq.from(ArticleDetailView.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
