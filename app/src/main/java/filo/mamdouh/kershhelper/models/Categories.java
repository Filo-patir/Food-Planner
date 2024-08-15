package filo.mamdouh.kershhelper.models;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Categories {
    private List<Category> categoryList;
    @Data
    public class Category {
        private int id;
        private String name;
        private String imgurl;
    }
}
