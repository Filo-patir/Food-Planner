package filo.mamdouh.kershhelper.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("api/json/v1/1/lookup.php?i=52772")
    Call<Meals> getMeals();
}