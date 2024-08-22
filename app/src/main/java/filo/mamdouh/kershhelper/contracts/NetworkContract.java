package filo.mamdouh.kershhelper.contracts;


import java.util.List;

import filo.mamdouh.kershhelper.models.Categories;
import filo.mamdouh.kershhelper.models.HomeFragmentRowData;
import filo.mamdouh.kershhelper.models.Meals;
import io.reactivex.rxjava3.core.Observable;

public interface NetworkContract {
    Observable<Meals> getMealByID(String id);

    Observable<Meals> getMealByName(String name);

    Observable<Meals> getMealByLetter(String letter);

    Observable<List<HomeFragmentRowData.ItemData>> getRandomMeal();

    Observable<Meals> getMealByCategory(String category);

    Observable<Meals> getMealByIngredient(String ingredient);

    Observable<Meals> getMealByArea(String area);

    Observable<Meals> getAreas();

    Observable<Meals> getIngredients();

    Observable<Meals> getCategories();
}
