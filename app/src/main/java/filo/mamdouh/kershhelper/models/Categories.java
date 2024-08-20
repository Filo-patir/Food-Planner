package filo.mamdouh.kershhelper.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Categories {
    @SerializedName("categories")
    private List<Category> categoryList;
    @Getter
    public class Category {
        @SerializedName("strCategory")
        private String name;
        @SerializedName("strCategoryThumb")
        private String imgurl;
    }
}
