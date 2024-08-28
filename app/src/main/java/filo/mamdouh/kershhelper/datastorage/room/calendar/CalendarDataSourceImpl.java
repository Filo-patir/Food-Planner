package filo.mamdouh.kershhelper.datastorage.room.calendar;

import android.content.Context;
import android.util.Log;

import java.util.List;

import filo.mamdouh.kershhelper.datastorage.room.AppDatabase;
import filo.mamdouh.kershhelper.models.Calendar;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CalendarDataSourceImpl implements CalendarDataSource {
    private CalendarDao dao;
    private static CalendarDataSourceImpl instance = null;
    private CalendarDataSourceImpl(Context context){
        AppDatabase db = AppDatabase.getInstance(context);
        dao = db.getCalendarDao();
    }

    public static CalendarDataSourceImpl getInstance(Context context) {
        if(instance == null) instance = new CalendarDataSourceImpl(context);
        return instance;
    }

    @Override
    public Completable insertMeal(Calendar meal) {
        return dao.insert(meal);
    }

    @Override
    public Completable deleteMeal(Calendar meal) {
        return dao.delete(meal);
    }

    @Override
    public Flowable<Calendar> getCalendars() {
        dao.getCalendars().subscribeOn(Schedulers.io()).subscribe(onNext-> Log.d("Filo", "getCalendars: "+onNext.getMealID()));
        return dao.getCalendars();
    }

    @Override
    public Flowable<List<Boolean>> getMealPlan(String id) {
        Log.d("Filo", "getMealPlan: ");
        return dao.getCalendar(id).subscribeOn(Schedulers.io()).map(Calendar::getWeek);
    }

    @Override
    public Flowable<Calendar> getCalendarByID(String id) {
        return dao.getCalendar(id);
    }
}
