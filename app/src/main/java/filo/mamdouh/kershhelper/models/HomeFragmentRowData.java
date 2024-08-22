package filo.mamdouh.kershhelper.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
public class HomeFragmentRowData {
    private String title;
    private List<ItemData> item;

    public HomeFragmentRowData(String title, List<ItemData> item) {
        this.title = title;
        this.item = item;
    }
    @Data
    @AllArgsConstructor
    public static class ItemData {
        private String id;
        private String mealName;
        private int ingredientsNumber;
        private String areaImg;
        private String mealImg;
    }
}
