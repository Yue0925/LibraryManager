package com.ensta.librarymanager.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
 * DashboardServlet
 */
@WebServlet("/")
public class DashboardServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String action = request.getServletPath();
        if (action.equals("/dashboard")) {
            System.out.println("/dashboard  doGet");
            showDashboard(request, response);
        }
    }
    
    private void showDashboard(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
        LivreService livreService = LivreServiceImpl.getInstance();
        MembreService membreService = MembreServiceImpl.getInstance();
        EmpruntService empruntService = EmpruntServiceImpl.getInstance();

        try {
            int nbLivres = livreService.count();
            int nbMembres = membreService.count();
            int nbEmprunts = empruntService.count();
            List<Emprunt> empruntsCourant = empruntService.getListCurrent();
            System.out.println("nbLivres: "+ nbLivres + " nbMembres: " + nbMembres + 
            " nbEmprunts: " + nbEmprunts + " empruntsCourant: " + empruntsCourant);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        
        request.setAttribute("nbLivres", nbLivres);
        request.setAttribute("nbMembres", nbMembres);
        request.setAttribute("nbEmprunts", nbEmprunts);
        request.setAttribute("empruntsCourant", empruntsCourant);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/dashboard.jsp");
        dispatcher.forward(request, response);
    }
}