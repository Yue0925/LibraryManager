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
import com.ensta.librarymanager.utils.Abonnement;
import com.ensta.librarymanager.exception.*;

/**
 * MembreAddServlet
 */
@WebServlet("/membre_add")
public class MembreAddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        MembreService membreService = MembreServiceImpl.getInstance();

        try {
            request.setAttribute("membres", membreService.getList());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_add.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            MembreService membreService = MembreServiceImpl.getInstance();
            EmpruntService empruntService = EmpruntServiceImpl.getInstance();

            int id = membreService.create(request.getParameter("nom"), request.getParameter("prenom"), 
            request.getParameter("adresse"), request.getParameter("email"), 
            request.getParameter("telephone"), Abonnement.BASIC);
            
            response.sendRedirect(request.getContextPath() + "/livre_details?id=" + id);
        } catch (IOException e2) {
            System.out.println(e2.getMessage());
            e2.printStackTrace();
        } catch (ServiceException e3) {
            System.out.println(e3.getMessage());
            throw new ServletException ("Error in doPost() of LivreAddServlet.");
        }
    }

    
}