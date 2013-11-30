/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package www.convergeintelligence;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import static www.convergeintelligence.Entrance.CommonPaht;

/**
 *
 * @author baod
 */
public class Comment {

    public String WriteComment(final String id, final String UserID, final String cmt) throws IOException {

        final String commonPath = (String) WebContext.GetInstance().getContext().get(VerbDefine.CommonPaht);
        if (commonPath == null || commonPath.length() <= 0 || !(new java.io.File(commonPath)).exists()) {
            throw new java.io.FileNotFoundException(VerbDefine.CommonPaht);
        }
        String rst = String.format("%s提交了ID为%s的内容[%s]", UserID, id, cmt);

        //java.io.FileWriter fw = new java.io.FileWriter(String.format("%s\\%s-%s.txt", commonPath, UserID, id));
        WebContext.GetInstance().getExecutorService().execute(new Runnable() {
            @Override
            public void run() {
                java.io.FileWriter fw;
                try {
                    fw = new java.io.FileWriter(String.format("%s\\%s-%s.txt", commonPath, UserID, id));
                    fw.write(cmt);
                    fw.close();
                } catch (IOException ex) {
                    Logger.getLogger(Comment.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        return rst;
    }

    public String UpComment(String id, String UserID) {
        return "";
    }

    public String DownComment(String id, String UserID) {
        return "";
    }
}
