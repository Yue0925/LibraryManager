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
@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    
    /** 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        LivreService livreService = LivreServiceImpl.getInstance();
        MembreService membreService = MembreServiceImpl.getInstance();
        EmpruntService empruntService = EmpruntServiceImpl.getInstance();
        int nbLivres = 0;
        int nbMembres = 0;
        int nbEmprunts = 0;
        List<Emprunt> empruntsCourant = new ArrayList<>();

        try {
             nbLivres = livreService.count();
             nbMembres = membreService.count();
             nbEmprunts = empruntService.count();
             empruntsCourant = empruntService.getListCurrent();
            /*System.out.println("nbLivres: "+ nbLivres + " nbMembres: " + nbMembres + 
            " nbEmprunts: " + nbEmprunts + " empruntsCourant: " + empruntsCourant);*/
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