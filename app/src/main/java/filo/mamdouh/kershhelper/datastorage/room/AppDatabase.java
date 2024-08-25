package filo.mamdouh.kershhelper.datastorage.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import filo.mamdouh.kershhelper.datastorage.room.calendar.Calendar;
import filo.mamdouh.kershhelper.datastorage.room.calendar.CalendarDao;
import filo.mamdouh.kershhelper.datastorage.room.savedmeals.MealDAO;
import filo.mamdouh.kershhelper.models.MealsItem;

@Database(entities = {MealsItem.class, Calendar.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase appDatabase = null;
    public abstract MealDAO getMealDAO();
    public abstract CalendarDao getCalendarDao();
    public static synchronized AppDatabase getInstance(Context context){
        if(appDatabase == null){
            appDatabase = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,"filodb").build();
        }
        return appDatabase;
    }
}
