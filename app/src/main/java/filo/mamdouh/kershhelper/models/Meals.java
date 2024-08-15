package filo.mamdouh.kershhelper.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class Meals {
    @SerializedName("meals")
    private List<MealsItem> meals;

    public List<MealsItem> getMeals(){
        return meals;
    }
}

