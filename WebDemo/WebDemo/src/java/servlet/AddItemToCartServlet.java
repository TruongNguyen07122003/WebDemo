/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import truongnt.cart.CartObject;

/**
 *
 * @author GIGABYTE
 */
@WebServlet(name = "AddItemToCartServlet", urlPatterns = {"/AddItemToCartServlet"})
public class AddItemToCartServlet extends HttpServlet {
    private final String BOOKSTORE_PAGE = "bookStore.jsp";
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
        String url = BOOKSTORE_PAGE;
        try  {
            /* TODO output your page here. You may use following sample code. */
            
            //1.Cus go to cart place
            HttpSession session = request.getSession(); //default la true
            //2.Cus get cart
            CartObject cart = (CartObject)session.getAttribute("CART");
            if(cart == null){
                cart = new CartObject();
            }//cart has been innit
            //3.cus drops item to cart
            String itemID = request.getParameter("ddlBook");//copy lai, nhung tai chua lam xong
            String quantityRequest = request.getParameter("bookQuantity");
            if (!quantityRequest.trim().isEmpty()) {
                int quantity = Integer.parseInt(quantityRequest);
                cart.addItemToCart(itemID, quantity);
            }
            session.setAttribute("CART", cart);
      
            //4.cus tiep tuc di cho bang cach quay lai trang bookstore.html
        }finally{
            response.sendRedirect(url);
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
