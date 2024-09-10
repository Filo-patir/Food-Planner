package filo.mamdouh.kershhelper.models;

import java.util.ArrayList;

import filo.mamdouh.kershhelper.datastorage.caching.BoxesInterface;

public class CachingRepositry {
    private BoxesInterface boxesInterface;
    private static CachingRepositry instance = null;
    private CachingRepositry(BoxesInterface boxesInterface) {
        this.boxesInterface = boxesInterface;
    }
    public static CachingRepositry getInstance(BoxesInterface boxesInterface) {
        if (instance == null)
            instance = new CachingRepositry(boxesInterface);
        return instance;
    }
    public void addDessert(Desserts dessert)
    {
        boxesInterface.addDessert(dessert);
    }
    public ArrayList<Desserts> getDesserts()
    {
        return boxesInterface.getDesserts();
    }
    public void removeAll()
    {
        boxesInterface.removeAll();
    }
}
