package filo.mamdouh.kershhelper.databases.room;

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
    @Query("SELECT * FROM meals")
    Flowable<List<MealsItem>> getSaved();
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insertProduct(MealsItem meal);
    @Delete
    Completable delete(MealsItem meal);
}
