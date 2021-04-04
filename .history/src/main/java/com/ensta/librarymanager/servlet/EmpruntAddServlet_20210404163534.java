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
 * EmpruntAddServlet
 */
@WebServlet("/")
public class EmpruntAddServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String action = request.getServletPath();
        if (action.equals("/emprunt_add")) {
            System.out.println("/emprunt_add  doGet");
            showAddEmprunt(request, response);
        }
    }

    private void showAddEmprunt(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
        LivreService livreService = LivreServiceImpl.getInstance();
        MembreService membreService = MembreServiceImpl.getInstance();
        List<Livre> livres = new ArrayList<>();
        List <Membre> membres = new ArrayList<>();

        try {
             livres = livreService.getListDispo();
             membres = membreService.getListMembreEmpruntPossible();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        
        request.setAttribute("livres", livres);
        request.setAttribute("membres", membres);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/emprunt_add.jsp");
        dispatcher.forward(request, response);
    }
}