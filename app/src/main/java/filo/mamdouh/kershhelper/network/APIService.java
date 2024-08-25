package filo.mamdouh.kershhelper.network;

import java.util.List;

import filo.mamdouh.kershhelper.models.Categories;
import filo.mamdouh.kershhelper.models.IngredientsRoot;
import filo.mamdouh.kershhelper.models.Meals;
import filo.mamdouh.kershhelper.models.MealsItem;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {
    @GET("lookup.php")
    Observable<Meals> getMealByID(@Query("i") String id);

    @GET("search.php")
    Observable<Meals> getMealByName(@Query("s") String name);

    @GET("search.php")
    Observable<Meals> getMealByLetter(@Query("f") String letter);

    @GET("random.php")
    Observable<Meals> getRandomMeal();

    @GET("filter.php")
    Observable<Meals> getMealByCategory(@Query("c") String category);

    @GET("filter.php")
    Observable<Meals> getMealByIngredient(@Query("i") String ingredient);

    @GET("filter.php")
    Observable<Meals> getMealByArea(@Query("a") String area);

    @GET("list.php?a=list")
    Observable<Meals> getAreas();

    @GET("list.php?i=list")
    Observable<IngredientsRoot> getIngredients();

    @GET("categories.php")
    Observable<Categories> getCategories();
}