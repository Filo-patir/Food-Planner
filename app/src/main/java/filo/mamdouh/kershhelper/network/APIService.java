package filo.mamdouh.kershhelper.network;

import filo.mamdouh.kershhelper.models.Categories;
import filo.mamdouh.kershhelper.models.Meals;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {
    @GET("/api/json/v1/1/lookup.php?i={id}")
    Observable<Meals> getMealByID(@Path("id") String id);

    @GET("/api/json/v1/1/search.php?s={name}")
    Observable<Meals> getMealByName(@Path("name") String name);

    @GET("/api/json/v1/1/search.php?f={letter}")
    Observable<Meals> getMealByLetter(@Path("letter") String letter);

    @GET("random.php")
    Observable<Meals> getRandomMeal();

    @GET("filter.php")
    Observable<Meals> getMealByCategory(@Query("c") String category);

    @GET("/api/json/v1/1/filter.php?i={ingredient}")
    Observable<Meals> getMealByIngredient(@Path("ingredient") String ingredient);

    @GET("/api/json/v1/1/filter.php?a={area}")
    Observable<Meals> getMealByArea(@Path("area") String area);

    @GET("/api/json/v1/1/list.php?a=list")
    Observable<Meals> getAreas();

    @GET("/api/json/v1/1/list.php?i=list")
    Observable<Meals> getIngredients();

    @GET("/api/json/v1/1/categories.php")
    Observable<Categories> getCategories();
}