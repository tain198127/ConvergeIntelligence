/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package www.convergeintelligence;

import java.io.File;
import java.io.IOException;
import static www.convergeintelligence.Entrance.CommonPaht;

/**
 *
 * @author baod
 */
public class Comment {

    public String WriteComment(String id, String UserID, String cmt) throws IOException {
        
        String commonPath = (String) WebContext.GetInstance().getContext().get(VerbDefine.CommonPaht);
        if (commonPath == null || commonPath.length() <= 0 || !(new java.io.File(commonPath)).exists()) {
            throw new java.io.FileNotFoundException(VerbDefine.CommonPaht);
        }
        String rst = String.format("%s提交了ID为%s的内容[%s]", UserID, id, cmt);
        
        java.io.FileWriter fw = new java.io.FileWriter(String.format("%s\\%s-%s.txt", commonPath, UserID, id));
        fw.write(cmt);
        fw.close();
        return rst;
    }

    public String UpComment(String id, String UserID) {
        return "";
    }

    public String DownComment(String id, String UserID) {
        return "";
    }
}
