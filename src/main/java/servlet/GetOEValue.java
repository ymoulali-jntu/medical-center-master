/**
 *
 * @author Administrator
 * Modernized GetOEValue Servlet using Jakarta EE
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.simple.JSONValue;
import medicalcenter.database;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetOEValue extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(GetOEValue.class);

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
            String str=null;
            ResultSet rs=null;
            String sql="SELECT observation_value FROM diagnosis_info "
                    + "where observation_name='"+request.getParameter("oename")+"' "
                    + "and observation_type='on_examination'";
        try {
            database db=new database();
               rs=db.executeQuery(sql);
            while(rs.next()){
                str=rs.getString("observation_value");
            }
               String jsonString = JSONValue.toJSONString(str);
           //    System.out.println(jsonString);
             //  System.out.println(jsonString);
                out.println(jsonString);
                }catch(Exception e){
                    out.println("Error : "+e);
        }  finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
