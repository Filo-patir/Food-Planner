package filo.mamdouh.kershhelper.datastorage.room.savedmeals;

import java.util.List;

import filo.mamdouh.kershhelper.models.MealsItem;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public interface SavedMealsDataSource {
    Completable insertMeal(MealsItem meal);
    Completable deleteMeal(MealsItem meal);
    Flowable<List<MealsItem>> getSavedMeals();
    Flowable<MealsItem> getMealByID(String id);
    Completable clear();
}
