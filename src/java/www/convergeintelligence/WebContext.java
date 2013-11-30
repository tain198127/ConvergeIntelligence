/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package www.convergeintelligence;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author tain198127
 */
public class WebContext {
    private WebContext(){}
    private static WebContext _instance = null;
    public static WebContext GetInstance(){
        if(_instance == null)
        {
            _instance = new WebContext();
        }
        return _instance;
    }
    private Map<String,Object> _context = new HashMap<String,Object>();
    public Map<String,Object> getContext()
    {
       
        return _context;
    }
     private ExecutorService exectorService =  Executors.newCachedThreadPool();
     public ExecutorService getExecutorService()
     {
         return exectorService;
     }
}
