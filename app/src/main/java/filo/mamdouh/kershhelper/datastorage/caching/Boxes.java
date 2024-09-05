package filo.mamdouh.kershhelper.datastorage.caching;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;

public class Boxes implements BoxesInterface {
    private final Box<Desserts> dessertsBox;
    private static Boxes instance = null;
    private Boxes(Context context) {
        BoxStore store = ObjectBox.getInstance(context).getStore();
        dessertsBox = store.boxFor(Desserts.class);
    }
    public static Boxes getInstance(Context context) {
        if (instance == null) {
            instance = new Boxes(context);
        }
        return instance;
    }
    public ArrayList<Desserts> getDesserts() {
        return (ArrayList<Desserts>) dessertsBox.getAll();
    }

    public void addDessert(Desserts dessert){
        Log.d("Filo", "addDessert: " + dessert.toString());
        dessertsBox.put(dessert);
    }
    public void removeAll(){
        dessertsBox.removeAll();
    }
}
