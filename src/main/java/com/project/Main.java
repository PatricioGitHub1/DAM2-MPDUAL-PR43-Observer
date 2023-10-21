package com.project;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/*
 * Aquest exemple defineix un
 * model observer simple, sobre
 * tipus de dades primitius
 * 
 * També mira els canvis a l'arxiu
 * 'arxiu.txt' i els mostra
 * per pantalla quan es modifica
 * encara que siguin fets per una
 * eina externa
 */

public class Main {

    public static void main (String[] args) {
        /*
        String basePath = System.getProperty("user.dir") + "/data/";
        String filePath = basePath + "./arxiu.txt";
        File arxiu = new File(filePath);

        Observable<Integer> obsNum = new Observable<Integer>(0) {
            @Override
            public void propertyChange(Integer oldValue, Integer newValue) {
                System.out.printf("obsNum ha canviat de %s cap a %s\n", oldValue, newValue);
            }            
        };

        Observable<String> obsTxt = new Observable<String>("poma") {
            @Override
            public void propertyChange(String oldValue, String newValue) {
                System.out.printf("obsTxt ha canviat de %s cap a %s\n", oldValue, newValue);
            }            
        };

        ObservableFile obsFile = new ObservableFile(arxiu) {
            @Override
            public void onChange() {
                System.out.println("Arxiu modificat");
            }
        };

        obsNum.setValue(1);
        obsTxt.setValue("llimona");
        obsNum.setValue(2);
        obsTxt.setValue("meló");

        System.out.println("Esperem 10 segons per si hi ha canvis a l'arxiu 'arxiu.txt'");
        for (int cnt = 10; cnt > 0; cnt = cnt - 1) {
            System.out.println("Contador: " + cnt);
            wait(1);
            if (cnt == 9) {
                // Escribim l'arxiu per comprovar que detecta l'event
                escriuArxiu(arxiu, "Hola arxiu");
            }
        } */   
        PR450Producte p0 = new PR450Producte(0, "Llibre");
        PR450Producte p1 = new PR450Producte(1, "Llapis");
        PR450Producte p2 = new PR450Producte(2, "Rotulador");
        PR450Producte p3 = new PR450Producte(3, "Carpeta");
        PR450Producte p4 = new PR450Producte(4, "Motxilla");


        PR450Magatzem magatzem = new PR450Magatzem();
        PR450Entregues entregues = new PR450Entregues();


        // Aquí afegir els property listeners adequats
        PropertyChangeListener l0 = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                System.out.printf("Producte ha canviat el nom de '%s' a '%s'\n",   
                evt.getOldValue(), 
                evt.getNewValue());               
            }
          };
        p0.addPropertyChangeListener("producteName", l0);          
        
        PropertyChangeListener l1 = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                System.out.printf("Producte ha canviat l'id de '%s' a '%s'\n",    
                evt.getOldValue(), 
                evt.getNewValue());               
            }
          };
        p0.addPropertyChangeListener("producteId", l1);     

        PropertyChangeListener l2 = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
              System.out.printf("Producte ha canviat el nom de '%s' a '%s'\n",
                evt.getOldValue(), 
                evt.getNewValue());                  
            }
          };
        p1.addPropertyChangeListener("producteName", l2);  
        
        PropertyChangeListener ml0 = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
              System.out.printf("S'ha afegit el producte amb id '%s' al magatzem, capacitat '%s'\n",
                evt.getOldValue(), 
                evt.getNewValue());                  
            }
          };
        magatzem.addPropertyChangeListener("magatzemAdd", ml0);
        
        PropertyChangeListener ml1 = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
              System.out.printf("S'ha esborrat el producte amb id '%s' del magatzem, capacitat '%s'\n",
                evt.getOldValue(), 
                evt.getNewValue());                  
            }
          };
        magatzem.addPropertyChangeListener("magatzemRemove", ml1);
        
        PropertyChangeListener ml2 = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                PR450Producte newProducte = (PR450Producte) evt.getOldValue();
                entregues.addProducte(newProducte);   
                System.out.printf("S'ha mogut el producte amb id '%s' del magatzem cap a entregues\n",
                newProducte.getId());              
            }
          };
        magatzem.addPropertyChangeListener("magatzemEntrega", ml2);
        
        PropertyChangeListener el0 = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                System.out.printf("S'ha afegit el producte amb id '%s' a la llista d'entregues\n",
                evt.getOldValue());     
            }
          };
        entregues.addPropertyChangeListener("entreguesAdd", el0);
        
        PropertyChangeListener el1 = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                System.out.printf("S'ha entregat el producte amb id '%s'\n",
                evt.getOldValue()); 
            }
          };
        entregues.addPropertyChangeListener("entreguesRemove", el1);
        
        // Modificacions
        p0.setId(5);
        p0.setNom("Llibreta");
        p1.setNom("Boli");


        magatzem.addProducte(p0);
        magatzem.addProducte(p1);
        magatzem.addProducte(p2);
        magatzem.addProducte(p3);
        magatzem.addProducte(p4);


        magatzem.removeProducte(2);
        magatzem.removeProducte(3);
        magatzem.removeProducte(4);


        entregues.removeProducte(2);
        entregues.removeProducte(3);


        System.out.println(magatzem);
        System.out.println(entregues);

    }

    public static void wait (int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) { e.printStackTrace(); }
    }

    public static void escriuArxiu (File arxiu, String valor) {
		try {
			FileWriter fileWriter = new FileWriter(arxiu);
			fileWriter.write(valor);
			fileWriter.close();

		} catch (IOException e) { e.printStackTrace(); }
    }
}