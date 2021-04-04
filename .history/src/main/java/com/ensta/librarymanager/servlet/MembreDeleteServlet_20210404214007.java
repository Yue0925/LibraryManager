package com.ensta.librarymanager.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import javax.servlet.annotation.WebServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.model.*;
import com.ensta.librarymanager.service.*;
import com.ensta.librarymanager.exception.*;

/**
 * MembreDeleteServlet
 */
@WebServlet("/membre_delete")
public class MembreDeleteServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        MembreService membreService = MembreServiceImpl.getInstance();

        try {
            int idMembre = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("membre", membreService.getById(idMembre));
            request.setAttribute("idMembre", idMembre);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_delete.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            MembreService membreService = MembreServiceImpl.getInstance();
            membreService.delete(Integer.parseInt(request.getParameter("id")));

            response.sendRedirect(request.getContextPath() + "/membre_list");
        } catch (NumberFormatException e1) {
            System.out.println(e1.getMessage()); 
            e1.printStackTrace();
        } catch (IOException e2) {
            System.out.println(e2.getMessage());
            e2.printStackTrace();
        } catch (ServiceException e3) {
            System.out.println(e3.getMessage());
            throw new ServletException ("Error in doPost() of LivreDeleteServlet");
        }
    }

    
}