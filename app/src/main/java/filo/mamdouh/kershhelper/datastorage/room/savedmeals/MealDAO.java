package filo.mamdouh.kershhelper.datastorage.room.savedmeals;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import filo.mamdouh.kershhelper.models.MealsItem;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface MealDAO {
    @Query("SELECT * FROM meals WHERE isSaved = 1")
    Flowable<List<MealsItem>> getSaved();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertProduct(MealsItem meal);
    @Delete
    Completable delete(MealsItem meal);
    @Query("SELECT * FROM meals WHERE daysOfWeeks IS NOT NULL")
    Flowable<MealsItem> getCalendars();
    @Query("SELECT * FROM meals WHERE idMeal = :id")
    Flowable<MealsItem> getMealByID(String id);
    @Query("DELETE FROM meals")
    Completable clear();
}