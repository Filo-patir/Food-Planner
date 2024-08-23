package filo.mamdouh.kershhelper.models;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class HomeFragmentRowData {
    private String title;
    private List<MealsItem> item;
    public HomeFragmentRowData(String title, List<MealsItem> item) {
        this.title = title;
        this.item = item;
    }
}
