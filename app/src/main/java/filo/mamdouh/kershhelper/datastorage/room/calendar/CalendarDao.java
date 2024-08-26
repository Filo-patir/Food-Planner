package filo.mamdouh.kershhelper.datastorage.room.calendar;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface CalendarDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(Calendar calendar);

    @Query("SELECT * FROM calendar WHERE mealID = :id")
    Flowable<Calendar> getCalendar(String id);

    @Query("SELECT * FROM calendar")
    Flowable<Calendar> getCalendars();

    @Query("DELETE FROM calendar WHERE mealID =:id")
    Completable delete(String id);

    @Delete
    Completable delete(Calendar calendar);
}
