package com.ensta.librarymanager.service;

import com.ensta.librarymanager.service.EmpruntService;
import com.ensta.librarymanager.service.EmpruntServiceImpl;
import com.ensta.librarymanager.service.LivreService;
import com.ensta.librarymanager.service.LivreServiceImpl;
import com.ensta.librarymanager.service.MembreService;
import com.ensta.librarymanager.service.MembreServiceImpl;

/**
 * ServiceTest
 */
public class ServiceTest {

    public static void main(String[] args) {
        MembreService membreService = MembreServiceImpl.getInstance();
        LivreService livreService = LivreServiceImpl.getInstance();
        EmpruntService empruntService = EmpruntServiceImpl.getInstance();

        try {
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}