/**
 *
 * @author Administrator
 * Modernized GetStdInfo Servlet using Jakarta EE
 */
package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import medicalcenter.database;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetStdInfo extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(GetStdInfo.class);

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
        //out.println(request.getParameter("regNo"));
        //request.setAttribute("reg", request.getParameter("regNo"));
            Connection con=null;
            Statement st=null;
            ResultSet rs=null;
            String sql="select full_name,sex,(YEAR(CURDATE())-YEAR(date_of_birth)) - (RIGHT(CURDATE(),5)<RIGHT(date_of_birth,5)) "
                    + "AS age,present_address,photo,dept_code "
                    + "from student_personal_info,student,department,degree_offered_by_"
                    + "dept,student_batch where student_personal_pk_fk=student.student_pk "
                    + "and department.dept_pk=degree_offered_by_dept.department_fk "
                    + "and degree_offered_by_dept.dept_degree_pk=student_batch.dept_degree_fk "
                    + "and student_batch.student_batch_pk=student.student_batch_fk "
                    + "and student.registration_no="+request.getParameter("reg_id");
            try{
                database db=new database();
                rs=db.executeQuery(sql);
               // st=con.createStatement();
               // rs=st.executeQuery(sql);
                String imgLen="";
                if(rs.next()){
                        request.setAttribute("name", rs.getString("full_name"));System.out.println(rs.getString("full_name"));
                        request.setAttribute("sex", rs.getString("sex"));
                        request.setAttribute("age", rs.getInt("age"));
                        request.setAttribute("address", rs.getString("present_address"));
                        request.setAttribute("dept", rs.getString("dept_code"));

                       /* imgLen = rs.getString("photo");
                       // rs = st.executeQuery(sql);
                        if(rs.next()){
                            int len = imgLen.length();
                            byte [] rb = new byte[len];
                            InputStream readImg = rs.getBinaryStream("photo");
                            int index=readImg.read(rb, 0, len);
                            st.close();
                            response.reset();
                            response.setContentType("image/jpg");
                            response.getOutputStream().write(rb,0,len);
                            response.getOutputStream().flush();
                        }
                        */

                       // getServletConfig().getServletContext().getRequestDispatcher("/InvalidPatient.jsp").forward(request,response);
                       // response.sendRedirect("http://localhost:8090/MedicalCentre/InvalidPatient.jsp");
                       getServletContext().getRequestDispatcher("/add_std_prescription.jsp").forward(request, response);
                       }else{
                       //out.println("Error");
                       getServletContext().getRequestDispatcher("/InvalidPatient.jsp").forward(request, response);
                       }
                }catch(Exception e){
                    out.println("Error : "+e);
                    System.out.println("Error : "+e);
                    }

        try {
        }
        finally {
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
