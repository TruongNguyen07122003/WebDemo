/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import registration.RegistrationCreateErrors;
import registration.RegistrationDAO;
import registration.RegistrationDTO;

/**
 *
 * @author GIGABYTE
 */
@WebServlet(name = "CreateAccountServlet", urlPatterns = {"/CreateAccountServlet"})
public class CreateAccountServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String USER_LENGTH_ERROR = "USer is required typing from 6 to 30 characters";
    private final String PASSWORD_LENGTH_ERROR = "Pass word need to be from 6 to 30 charaters";
    private final String CONFRIM_ERRORS = "confirm not matched password";
    private final String FULLNAME_LENGTH_ERROR = "fullname errors";
    private final String CREATE_ERROR_PAGE = "createAccount.jsp";
    private final String USER_EXISTED_ERRORS = "user is existed";
    private final String LOGIN_PAGE = "login.html";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullName = request.getParameter("txtFullName");
        RegistrationCreateErrors err = new RegistrationCreateErrors();
        
        boolean foundErr = false;
        String url = CREATE_ERROR_PAGE;
        try {
            //2. verify all user's errors
            if(username.trim().length() < 6 || username.trim().length() > 30){
                foundErr = true;
                err.setUsernameLengthErr(USER_LENGTH_ERROR);
            }
            if(password.trim().length() < 6 || password.trim().length() > 30){
                foundErr = true;
                err.setPassworrdLengthErr(PASSWORD_LENGTH_ERROR);
            }else if (!confirm.trim().equals(password.trim())){
                foundErr = true;
                err.setConfirmLengthErr(CONFRIM_ERRORS);
            }if(fullName.trim().length() < 2 || fullName.trim().length() > 50){
                foundErr = true;
                err.setFullNameLengthErr(FULLNAME_LENGTH_ERROR);
            }
            if(foundErr){//error occur
                //cache to attribute then fw page to display
                request.setAttribute("CREATE_ERRORS", err);
            }else{
                //call DAO
                //3.1 new DAO
                RegistrationDAO dao = new RegistrationDAO();
              
                //3.2 call method of DAO
                RegistrationDTO dto = new RegistrationDTO(username, password, fullName, false);
                System.out.println("da khoi tao dto de nhet vao dao");
                boolean result = dao.createAccount(dto);
                System.out.println("da add thanh cong vao dao");
                //4.process result
                if(result){
                    url = LOGIN_PAGE;
                }
            }
            
        }catch (SQLException ex) {
            String errMsg = ex.getMessage();
            log("CreateAccountServlet _ SQL: " + errMsg);
            if(errMsg.contains("duplicate")) {
                err.setUsernameIsExisted(username + USER_EXISTED_ERRORS);
                request.setAttribute("CREATE_ERRORS", err);
            }
        } catch (NamingException ex) {
            log("CreateAccountServlet _ Naming: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("CreateAccountServlet _ ClassNotFound: " + ex.getMessage());
        } finally {
            //response tra ve co mat hay khong
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            
            
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
