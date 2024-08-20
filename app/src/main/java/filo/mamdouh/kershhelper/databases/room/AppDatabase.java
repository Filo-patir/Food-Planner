package filo.mamdouh.kershhelper.databases.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = Meals.class,version = 1)
public abstract class AppDatabase extends RoomDatabase {
}
