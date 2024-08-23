package filo.mamdouh.kershhelper.contracts;


import java.util.ArrayList;
import java.util.List;

import filo.mamdouh.kershhelper.models.Categories;
import filo.mamdouh.kershhelper.models.HomeFragmentRowData;
import filo.mamdouh.kershhelper.models.Meals;
import filo.mamdouh.kershhelper.models.MealsItem;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public interface NetworkContract {
    Observable<Meals> getMealByID(String id);

    Observable<Meals> getMealByName(String name);

    Observable<Meals> getMealByLetter(String letter);

    Single<ArrayList<MealsItem>> getDailyInspiration();

    Observable<List<MealsItem>> getMealByCategory(String category);

    Observable<Meals> getMealByIngredient(String ingredient);

    Observable<Meals> getMealByArea(String area);

    Observable<Meals> getAreas();

    Observable<Meals> getIngredients();

    Observable<Meals> getCategories();

    Single<ArrayList<MealsItem>> getRanomMeals();

    Single<ArrayList<MealsItem>> getMore();
}
