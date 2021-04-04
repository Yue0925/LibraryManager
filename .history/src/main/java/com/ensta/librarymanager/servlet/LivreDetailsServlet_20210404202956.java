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
 * LivreDetailsServlet
 */
@WebServlet("/livre_details")
public class LivreDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        LivreService livreService = LivreServiceImpl.getInstance();
        EmpruntService empruntService = EmpruntServiceImpl.getInstance();
        try {
            request.setAttribute("livre", livreService.getById(Integer.parseInt(request.getParameter("id"))));
            request.setAttribute("livresCourants", empruntService.getListCurrentByLivre(Integer.parseInt(request.getParameter("id"))));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/View/livre_details.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            LivreService livreService = LivreServiceImpl.getInstance();
            int id = livreService.create(request.getParameter("titre"), request.getParameter("auteur"), request.getParameter("isbn"));
            //TODO à tester
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