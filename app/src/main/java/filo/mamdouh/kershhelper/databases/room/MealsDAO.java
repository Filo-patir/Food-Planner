package filo.mamdouh.kershhelper.databases.room;

import android.database.Observable;

import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

public interface MealsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMeal(Meals meal);

    @Query("SELECT * FROM meals")
    Observable<List<Meals>> getMeals();

    @Query("Select * from meals WHERE isSaved = true")
    Observable<List<Meals>> getSavedMeals();

    @Query("Select * from meals WHERE week is not null")
    Observable<List<Meals>> getCalendar();
}
