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
 * EmpruntListServlet
 */
public class EmpruntListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        EmpruntService empruntService = EmpruntServiceImpl.getInstance();
        List<Emprunt> emprunts = new ArrayList<>();

        try {
            String show = request.getParameter("show");
            if (show == "all") {
                emprunts = empruntService.getList();
            }else { emprunts = empruntService.getListCurrent();}
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        request.setAttribute("emprunts", emprunts);
        request.setAttribute("show", "all");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/emprunt_list.jsp");
        dispatcher.forward(request, response);

    }
    
}