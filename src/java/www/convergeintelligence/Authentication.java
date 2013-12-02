/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package www.convergeintelligence;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import www.convergeintelligence.datamodel.*;
import www.convergeintelligence.action.UserJpaController;

/**
 *
 * @author tain198127
 */
public class Authentication {
    public Boolean Login(String UserName,String Password){
        EntityManagerFactory fa = javax.persistence.Persistence.createEntityManagerFactory("ConvergeIntelligencePU"); 
        
        EntityManager em = fa.createEntityManager();
        Query q = em.createNamedQuery("User.login")
        .setParameter("name",UserName)
        .setParameter("password",Password);
        List<User> us = (List<User>)q.getResultList();
        return us.size()>0;
        
    }
}
