package filo.mamdouh.kershhelper.datastorage.room.savedmeals;

import android.content.Context;

import java.util.List;

import filo.mamdouh.kershhelper.datastorage.room.AppDatabase;
import filo.mamdouh.kershhelper.models.MealsItem;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public class SavedMealsDataSourceImpl implements SavedMealsDataSource{
    private MealDAO dao;
    private static SavedMealsDataSourceImpl instance = null;

    private SavedMealsDataSourceImpl(Context context){
        AppDatabase db = AppDatabase.getInstance(context);
        dao = db.getMealDAO();
    }

    public static SavedMealsDataSourceImpl getInstance(Context context) {
        if(instance == null) instance = new SavedMealsDataSourceImpl(context);
        return instance;
    }

    @Override
    public Completable insertMeal(MealsItem meal) {
        return dao.insertProduct(meal);
    }

    @Override
    public Completable deleteMeal(MealsItem meal) {
        return dao.delete(meal);
    }

    @Override
    public Flowable<List<MealsItem>> getSavedMeals() {
        return dao.getSaved();
    }

    @Override
    public Flowable<MealsItem> getMealByID(String id) {
        return dao.getMealByID(id);
    }

    @Override
    public Completable clear() {
        return dao.clear();
    }
}
