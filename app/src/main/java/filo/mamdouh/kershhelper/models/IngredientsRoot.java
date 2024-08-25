package filo.mamdouh.kershhelper.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;

@Getter
public class IngredientsRoot {
    List<Ingredient> meals;

    @Getter
    public class Ingredient {
        @SerializedName("strIngredient")
        private String ingredientName;
        private String imgURL;
    }
}
