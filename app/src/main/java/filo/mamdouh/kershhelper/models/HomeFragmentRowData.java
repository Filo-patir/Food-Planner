package filo.mamdouh.kershhelper.models;

import java.util.ArrayList;
import java.util.List;

import filo.mamdouh.kershhelper.datastorage.caching.Desserts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class HomeFragmentRowData {
    private String title;
    private List<MealsItem> item;
    private ArrayList<Desserts> desserts;
    public HomeFragmentRowData(String title, List<MealsItem> item) {
        this.title = title;
        this.item = item;
        desserts = new ArrayList<>();
    }
    public HomeFragmentRowData(String title, ArrayList<Desserts> items) {
        this.title = title;
        this.desserts = items;
    }
}
