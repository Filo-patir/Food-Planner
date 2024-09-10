package filo.mamdouh.kershhelper.datastorage.caching;

import java.util.ArrayList;

import filo.mamdouh.kershhelper.models.Desserts;

public interface BoxesInterface {
    void addDessert(Desserts dessert);
    ArrayList<Desserts> getDesserts();
    void removeAll();
}
