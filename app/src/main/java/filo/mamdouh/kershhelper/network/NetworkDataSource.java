package filo.mamdouh.kershhelper.network;

import filo.mamdouh.kershhelper.models.Categories;
import filo.mamdouh.kershhelper.models.Meals;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NetworkDataSource {
    Observable<Meals> getMealByID( String id);

    Observable<Meals> getMealByName(String name);

    Observable<Meals> getMealByLetter(String letter);

    Observable<Meals> getRandomMeal();

    Observable<Meals> getMealByCategory(String category);

    Observable<Meals> getMealByIngredient(String ingredient);

    Observable<Meals> getMealByArea(String area);

    Observable<Meals> getAreas();

    Observable<Meals> getIngredients();

    Observable<Categories> getCategories();
}
