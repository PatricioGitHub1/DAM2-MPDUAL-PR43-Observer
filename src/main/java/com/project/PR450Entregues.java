package com.project;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class PR450Entregues {
    private ArrayList<PR450Producte> productes;

    // llista de propertyChangeSupport
    private PropertyChangeSupport llistaObservers = new PropertyChangeSupport(10);
    // funciones de propertyChangeSupport
    public void addPropertyChangeListener(String name, PropertyChangeListener listener) {
        llistaObservers.addPropertyChangeListener(name, listener);
    }

    public void removePropertyChangeListener(String name, PropertyChangeListener listener) {
        llistaObservers.removePropertyChangeListener(name, listener);
    }

    public PR450Entregues() {
        this.productes = new ArrayList<PR450Producte>();
    }

    public ArrayList<PR450Producte> getProductes() {
        return productes;
    }

    public void setProductes(ArrayList<PR450Producte> productes) {
        this.productes = productes;
    }

    public void addProducte(PR450Producte product) {
        this.productes.add(product);
        llistaObservers.firePropertyChange("entreguesAdd", product.getId(), null);
    }

    public void removeProducte(int id) {
        for (int i = 0; i < productes.size(); i++) {
            if (productes.get(i).getId() == id) {
                PR450Producte producte = productes.get(i);
                productes.remove(i);
                llistaObservers.firePropertyChange("entreguesRemove", producte.getId(), null);

            }
        }
    }

    @Override
    public String toString() {
        String output = "Productes per entregar: [";
        for (PR450Producte prd : productes) {
            output += " "+prd.getId()+": "+prd.getNom()+",";
        }
        output.substring(0, output.length()-1);
        return output + " ]";
    }
}