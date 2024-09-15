package filo.mamdouh.kershhelper.features.mainappfeatures.calendar.presenter;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import filo.mamdouh.kershhelper.contracts.CalendarContract;
import filo.mamdouh.kershhelper.models.DaysOfWeek;
import filo.mamdouh.kershhelper.models.MealsItem;
import filo.mamdouh.kershhelper.models.Repository;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CalendarPresenter {
    private final CalendarContract.View view;
    private final CompositeDisposable compositeDisposable;
    private final Repository repo;

    public CalendarPresenter(Repository repo, CalendarContract.View view) {
        this.repo = repo;
        this.view = view;
        compositeDisposable = new CompositeDisposable();
    }

    public void getCalendar() {
        compositeDisposable.add(repo.getCalendar().subscribeOn(Schedulers.io()).map(mealsItems -> filterByDay(mealsItems, DaysOfWeek.SUNDAY)).observeOn(AndroidSchedulers.mainThread()).subscribe(
                mealsItems -> view.updateUI(DaysOfWeek.SUNDAY, mealsItems),
                throwable -> Log.d("Filo", "getCalendar: "+ throwable.getMessage()),
                () -> {},
                compositeDisposable
        ));
        compositeDisposable.add(repo.getCalendar().subscribeOn(Schedulers.io()).map(mealsItems -> filterByDay(mealsItems, DaysOfWeek.MONDAY)).subscribe(
                mealsItems -> view.updateUI(DaysOfWeek.MONDAY, mealsItems),
        throwable -> Log.d("Filo", "getCalendar: "+ throwable.getMessage()),
                () -> {},
                compositeDisposable
        ));
        compositeDisposable.add(repo.getCalendar().subscribeOn(Schedulers.io()).map(mealsItems -> filterByDay(mealsItems, DaysOfWeek.THURSDAY)).subscribe(
                mealsItems -> view.updateUI(DaysOfWeek.TUESDAY, mealsItems),
                throwable -> Log.d("Filo", "getCalendar: "+ throwable.getMessage()),
                () -> {},
                compositeDisposable
        ));
        compositeDisposable.add(repo.getCalendar().subscribeOn(Schedulers.io()).map(mealsItems -> filterByDay(mealsItems, DaysOfWeek.WEDNESDAY)).subscribe(
                mealsItems -> view.updateUI(DaysOfWeek.WEDNESDAY, mealsItems),
                throwable -> Log.d("Filo", "getCalendar: "+ throwable.getMessage()),
                () -> {},
                compositeDisposable
        ));
        compositeDisposable.add(repo.getCalendar().subscribeOn(Schedulers.io()).map(mealsItems -> filterByDay(mealsItems, DaysOfWeek.THURSDAY)).subscribe(
                mealsItems -> view.updateUI(DaysOfWeek.THURSDAY, mealsItems),
                throwable -> Log.d("Filo", "getCalendar: "+ throwable.getMessage()),
                () -> {},
                compositeDisposable
        ));
        compositeDisposable.add(repo.getCalendar().subscribeOn(Schedulers.io()).map(mealsItems -> filterByDay(mealsItems, DaysOfWeek.FRIDAY)).subscribe(
                mealsItems -> view.updateUI(DaysOfWeek.FRIDAY, mealsItems),
                throwable -> Log.d("Filo", "getCalendar: "+ throwable.getMessage()),
                () -> {},
                compositeDisposable
        ));
        compositeDisposable.add(repo.getCalendar().subscribeOn(Schedulers.io()).map(mealsItems -> filterByDay(mealsItems, DaysOfWeek.SATURDAY)).subscribe(
                mealsItems -> view.updateUI(DaysOfWeek.SATURDAY, mealsItems),
                throwable -> Log.d("Filo", "getCalendar: "+ throwable.getMessage()),
                () -> {},
                compositeDisposable
        ));
    }

    public ArrayList<MealsItem> filterByDay(List<MealsItem> items, DaysOfWeek day){
        ArrayList<MealsItem> list = new ArrayList<>();
        for (MealsItem item : items){
            if (item.getDaysOfWeeks().contains(day)){
                list.add(item);
            }
        }
        return list;
    }

    public void removeItem(DaysOfWeek day, MealsItem mealsItem) {
        CompositeDisposable disposable = new CompositeDisposable();
        mealsItem.removeDay(day);
        disposable.add(repo.saveMeal(mealsItem).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(this::getCalendar, e -> Log.d("Filo", "removeItem: "+e.getMessage()), disposable));
    }

}
