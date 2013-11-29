/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package www.convergeintelligence;
import java.lang.*;
/**
 *
 * @author baod
 */
public class Analyze {
    private IDocWriter writer;
    private IDocReader reader;
    
    public IDocWriter getWriter() {
        return writer;
    }

    public void setWriter(IDocWriter writer) {
        this.writer = writer;
    }

    public IDocReader getReader() {
        return reader;
    }

    public void setReader(IDocReader reader) {
        this.reader = reader;
    }
    
    public String ShowName(Object obj){
        return "";
    }
    
}
