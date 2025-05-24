/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package fichasmedicas;

import com.fichas_medicas.dao.CrudArea;
import com.fichas_medicas.domain.Area;
import com.fichas_medicas.views.VentanaLogin;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

/**
 *
 * @author Usuario
 */
public class FichasMedicas {

// nuevo reposistorio 2025

    /**
     * SUBIDA DESDE LABORATORIO  FECHA:27-11-2024  HORA:8:23AM
     * REPARACION DE PROYECTO 1-2
     * Primer cambio 6 noviembre 2024 Prueba Jose 7/11/2024 14:28pm Prueba Laura
     * 7/11/2024 14:43pm Prueba José Luis 7/11/2024 14:39 pm Prueba Victor Pico
     * 7/11/2024 14:45 pm Prueba Pablo Garcia 7/11/2024 02:52 pm Prueba Hector
     * Drouet 7/11/2024 14:47pm Prueba Alex Sanchez 7/11/2024 14:54pm Prueba
     * Leandro Cacao 7/11/2024 15:01pm
     *
     * @Prueba de Base de datos : Jose Luis Tapia Fecha:09-11-2024 hora:21:22pm
     */
    public static void main(String[] args) {
           var login = new VentanaLogin(new JFrame(), true);
           login.setVisible(true);
//        System.out.println("Practicas Bernardino");  // Imprime un saludo
//        // TODO code application logic here
//        Usuario ob = new Usuario("ccvvv", "wwww", "jose", "lopez", "hhjh",
//                0, "A");
//        System.out.println(ob.toString());
//        Usuario ob1 = new Usuario(10, "ccvvv", "wwww", "jose", "lopez", "hhjh",
//                0, "A");
//        System.out.println(ob.toString());
//        Area obA = new Area("Informatica", 1, "A");
//        System.out.println(obA.toString());
        //basedatos();
    }

    private static void basedatos() {

        List<Area> lista = new ArrayList<>();
        CrudArea crud = new CrudArea();
        System.out.println(crud.getId("INFORMATICA")+" VAMOS");
        var del = crud.delete(5);
        var obj1= crud.getOne(1);
        System.out.println(obj1.toString());
        
        var obj = new Area("ECA", "java", "A");
       // var op = crud.save(obj);
       // System.out.println(op);
        lista = crud.getAll();
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i).toString());
        }
        obj = new Area(3, "CIENCIAS", "C++", "I");
        var op = crud.update(obj);
        System.out.println(op);
        System.out.println("Despues");
        lista = crud.getAll();
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i).toString());
        }

    }
}
