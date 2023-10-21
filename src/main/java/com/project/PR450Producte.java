package com.project;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PR450Producte {
    private int id;
    private String nom;

    // llista de propertyChangeSupport
    private PropertyChangeSupport llistaObservers = new PropertyChangeSupport(10);
    // funciones de propertyChangeSupport
    public void addPropertyChangeListener(String name, PropertyChangeListener listener) {
        llistaObservers.addPropertyChangeListener(name, listener);
    }

    public void removePropertyChangeListener(String name, PropertyChangeListener listener) {
        llistaObservers.removePropertyChangeListener(name, listener);
    }


    public PR450Producte(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }
    public int getId() {
        return id;
    }
    public void setId(int newValue) {
        //System.out.println("Estoy aquii IDD");
        int oldValue = this.id;
        this.id = newValue;
        llistaObservers.firePropertyChange("producteId", oldValue, newValue);
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String newValue) {
        //System.out.println("Estoy aquii NOM");
        String oldValue = this.nom;
        this.nom = newValue;
        llistaObservers.firePropertyChange("producteName", oldValue, newValue);
        
    }
    @Override
    public String toString() {
        return Integer.toString(id)+ ": "+ nom;
    }

    
}
