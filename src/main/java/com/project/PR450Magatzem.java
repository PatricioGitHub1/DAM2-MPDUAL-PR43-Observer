package com.project;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class PR450Magatzem {
    private ArrayList<PR450Producte> productes;
    private int capacitat;

    // llista de propertyChangeSupport
    private PropertyChangeSupport llistaObservers = new PropertyChangeSupport(10);
    // funciones de propertyChangeSupport
    public void addPropertyChangeListener(String name, PropertyChangeListener listener) {
        llistaObservers.addPropertyChangeListener(name, listener);
    }

    public void removePropertyChangeListener(String name, PropertyChangeListener listener) {
        llistaObservers.removePropertyChangeListener(name, listener);
    }

    public PR450Magatzem() {
        this.productes = new ArrayList<PR450Producte>();;
        this.capacitat = 10;
    }
    public ArrayList<PR450Producte> getProductes() {
        return productes;
    }
    public void setProductes(ArrayList<PR450Producte> productes) {
        this.productes = productes;
    }
    public int getCapacitat() {
        return capacitat;
    }
    public void setCapacitat(int capacitat) {
        this.capacitat = capacitat;
    }
    public void addProducte(PR450Producte product) {
        this.productes.add(product);
        this.capacitat = capacitat - 1;
        llistaObservers.firePropertyChange("magatzemAdd", product.getId(), this.capacitat);
    }

    public void removeProducte(int id) {
        for (int i = 0; i < productes.size(); i++) {
            if (productes.get(i).getId() == id) {
                PR450Producte producte = productes.get(i);
                // Treure producte del magatzem i aumentar capacitat
                productes.remove(i);
                this.capacitat = capacitat + 1;

                llistaObservers.firePropertyChange("magatzemRemove", producte.getId(), this.capacitat);
                llistaObservers.firePropertyChange("magatzemEntrega", producte, false);
                break;
            }
        }
    }
    @Override
    public String toString() {
        String output = "Productes al magatzem: [";
        for (PR450Producte prd : productes) {
            output += " "+prd.getId()+": "+prd.getNom()+",";
        }
        output = output.substring(0, output.length() - 1);
        return output + " ]";
    }
    
}
