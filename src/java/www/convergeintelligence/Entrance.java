/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package www.convergeintelligence;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.*;
import java.io.BufferedReader;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.logging.Level;

/**
 *
 * @author baod
 */
public class Entrance extends HttpServlet {

    Logger log = Logger.getLogger("tst");

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.ClassNotFoundException
     * @throws java.lang.IllegalAccessException
     * @throws java.lang.reflect.InvocationTargetException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FileHandler fh = new FileHandler("c:\\mylog\\mylog.log");
        log.addHandler(fh);
        response.setContentType("application/x-javascript;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            ObjectMapper objMapper = new ObjectMapper();
            BufferedReader br = new java.io.BufferedReader(request.getReader());

            StringBuilder strB = new StringBuilder();
            String line = "";
            while ((line = br.readLine()) != null) {
                strB.append(line);
            }

            br.close();

            AjaxRequestParam param = objMapper.readValue(strB.toString(), AjaxRequestParam.class);

            Object rst = null;
            try {
                rst = param.DoAction();
            } catch (ClassNotFoundException ex) {
                log.info(ex.getMessage() + "[" + strB.toString() + "]"+ex.getStackTrace().toString());
            } catch (IllegalAccessException ex) {
                log.info(ex.getMessage() + "[" + strB.toString() + "]"+ex.getStackTrace().toString());
            } catch (IllegalArgumentException ex) {
                log.info(ex.getMessage() + "[" + strB.toString() + "]"+ex.getStackTrace().toString());
            } catch (InvocationTargetException ex) {
                log.info(ex.getMessage() + "[" + strB.toString() + "]"+ex.getStackTrace().toString());
            } catch (InstantiationException ex) {
                log.info(ex.getMessage() + "[" + strB.toString() + "]"+ex.getStackTrace().toString());
            }
            objMapper.writeValue(out, rst);
        } catch (IOException e) {
            out.write(e.getMessage()+e.getLocalizedMessage());
            log.info(e.getMessage());
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
