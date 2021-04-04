package com.ensta.librarymanager.test;

import com.ensta.librarymanager.utils.*;

import com.ensta.librarymanager.model.*;

import com.ensta.librarymanager.dao.*;
import java.time.LocalDate;

public class DaoTest {
    public static void main(String[] args) {
        MembreDao membreImpl= MembreDaoImpl.getInstance();
		LivreDao livreImpl = LivreDaoImpl.getInstance();
        EmpruntDao empruntImpl= EmpruntDaoImpl.getInstance();
        
        try {
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
