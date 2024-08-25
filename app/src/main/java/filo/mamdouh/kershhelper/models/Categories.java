package filo.mamdouh.kershhelper.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;

@Getter
public class Categories {
    @SerializedName("categories")
    private List<Category> categoryList;
    @Getter
    public static class Category {
        @SerializedName("strCategory")
        private String name;
        @SerializedName("strCategoryThumb")
        private String imgurl;
    }
}
