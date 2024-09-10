package filo.mamdouh.kershhelper.models;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.Getter;


@Data
public class HomeFragmentRowData {
    private String title;
    private List<MealsItem> item;
    private ArrayList<Desserts> desserts;
    @Getter
    private static int counter = 0;
    public HomeFragmentRowData(String title, List<MealsItem> item) {
        this.title = title;
        this.item = item;
        desserts = new ArrayList<>();
        counter++;
    }
    public HomeFragmentRowData(String title, ArrayList<Desserts> items) {
        this.title = title;
        this.desserts = items;
        counter++;
    }

}
