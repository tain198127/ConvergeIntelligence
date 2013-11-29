/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package www.convergeintelligence;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeSupport;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author baod
 */
public class AjaxRequestParam {
    public static final String PROP_ACTION = "PROP_ACTION";
    public static final String PROP_FULLCLASS = "PROP_FULLCLASS";
    public static final String PROP_DATA = "PROP_DATA";
    private String Action;
    private String FullClass;
    private ArrayList<Object> Data = new ArrayList<Object>();
    
    private final transient PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);
    private final transient VetoableChangeSupport vetoableChangeSupport = new java.beans.VetoableChangeSupport(this);
    public AjaxRequestParam()
    {
        
    }
    /*
    public AjaxRequestParam(String JSON) throws IOException 
    {
         ObjectMapper objMapper = new ObjectMapper();
         
        AjaxRequestParam p = objMapper.readValue(JSON, AjaxRequestParam.class );
    }
    */
    /**
     * @return the Action
     */
    public String getAction() {
        return Action;
    }

    /**
     * @param Action the Action to set
     */
    public void setAction(String Action) throws PropertyVetoException {
        java.lang.String oldAction = this.Action;
        vetoableChangeSupport.fireVetoableChange(PROP_ACTION, oldAction, Action);
        this.Action = Action;
        propertyChangeSupport.firePropertyChange(PROP_ACTION, oldAction, Action);
    }

    /**
     * @return the FullClass
     */
    public String getFullClass() {
        return FullClass;
    }

    /**
     * @param FullClass the FullClass to set
     */
    public void setFullClass(String FullClass) throws PropertyVetoException {
        java.lang.String oldFullClass = this.FullClass;
        vetoableChangeSupport.fireVetoableChange(PROP_FULLCLASS, oldFullClass, FullClass);
        this.FullClass = FullClass;
        propertyChangeSupport.firePropertyChange(PROP_FULLCLASS, oldFullClass, FullClass);
    }

    /**
     * @return the Data
     */
    public  ArrayList<Object> getData() {
        return Data;
    }

    /**
     * @param Data the Data to set
     */
    public void setData( ArrayList<Object> d) throws PropertyVetoException {
        List<Object> oldData = this.Data;
        vetoableChangeSupport.fireVetoableChange(PROP_DATA, oldData, Data);
        Data.clear();
        Data.addAll(d);
        propertyChangeSupport.firePropertyChange(PROP_DATA, oldData, Data);
    }
    
    public Object DoAction() throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException{
        Class clz = Class.forName(this.FullClass);
        
        Method[] ms = clz.getMethods();
        Method m = null;
        for(int i=0;i<ms.length;i++)
        {
            //名称一样并且参数长度一样
            if(ms[i].getName().equals(this.Action)&& ms[i].getParameterTypes().length  == this.Data.size())
            {
                m = ms[i];
                break;
            }
        }
        if(m== null)
        {
            throw new java.lang.RuntimeException(String.format("未找到%1$c中参数个数为%2$d个的%3$c方法", this.FullClass,this.Action,this.Data.size()));
        }
        Object result = m.invoke(clz.newInstance(),this.Data.toArray());
        return result;
    }
}
