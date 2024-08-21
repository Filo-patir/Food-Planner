package filo.mamdouh.kershhelper.models;

import java.util.ArrayList;

import lombok.Data;

@Data
public class HomeFragmentRowData {
    private String title;
    private ArrayList<ItemData> item;
    @Data
    public static class ItemData {
        private String id;
        private String mealName;
        private String ingredients;
        private String areaImg;
    }
}
