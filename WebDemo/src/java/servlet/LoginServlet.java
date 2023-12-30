/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import registration.RegistrationDAO;
import registration.RegistrationDTO;

/**
 *
 * @author GIGABYTE
 */
public class LoginServlet extends HttpServlet {
//    private final String INVALID_PAGE = "invalid.html";
//    private final String SEARCH_PAGE = "search.html";
    private final String INVALID_PAGE = "invalidPage";
    private final String SEARCH_PAGE = "searchPage";
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            //1.get all parameters
//        String button = request.getParameter("btAction");
//        String url = INVALID_PAGE;
        
       
       //0. get current context
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        //1.get all parameters
        String url = siteMaps.getProperty(INVALID_PAGE);
            System.out.println(url);
        String username = request.getParameter("userName");
        String password = request.getParameter("password");
        try {
//            if (button.equals("Login")) {
//                
                //2. call DAO
                //  2.1 new DAO object
                RegistrationDAO dao = new RegistrationDAO();
                //  2.2 call method of DAO
                RegistrationDTO result = dao.checkLogin(username, password);
                //3. process result
                if (result != null) {
                    url = siteMaps.getProperty(SEARCH_PAGE);
                    System.out.println(url);
                    HttpSession session = request.getSession();//login thanh cong thi tao session cho phien dang nhap
                    session.setAttribute("USER_INFO", result);
                    
                }
                //end username and password are verified
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        catch(NamingException ex){
            ex.printStackTrace();
        }finally {
            //response.sendRedirect(url);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
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
