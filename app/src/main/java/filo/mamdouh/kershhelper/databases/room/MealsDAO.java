package filo.mamdouh.kershhelper.databases.room;

import android.database.Observable;

import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public interface MealsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertMeal(Meals meal);

    @Query("SELECT * FROM meals")
    Flowable<List<Meals>> getMeals();

    @Query("Select * from meals WHERE isSaved = true")
    Flowable<List<Meals>> getSavedMeals();

    @Query("Select * from meals WHERE week is not null")
    Flowable<List<Meals>> getCalendar();
}
