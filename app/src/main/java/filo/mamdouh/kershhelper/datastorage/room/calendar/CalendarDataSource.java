package filo.mamdouh.kershhelper.datastorage.room.calendar;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public interface CalendarDataSource {
    Completable insertMeal(Calendar meal);
    Completable deleteMeal(Calendar meal);
    Flowable<Calendar> getCalendars();
    Flowable<List<Boolean>> getMealPlan(String id);
    Flowable<Calendar> getCalendarByID(String id);
}
