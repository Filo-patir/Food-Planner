package filo.mamdouh.kershhelper.datastorage.caching;

import java.util.ArrayList;
import java.util.List;

public interface BoxesInterface {
    void addDessert(Desserts dessert);
    ArrayList<Desserts> getDesserts();
    void removeAll();
}
