package filo.mamdouh.kershhelper.features.mainappfeatures.calendar.presenter;

import android.util.Log;

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
        compositeDisposable.add(repo.getCalendar().subscribeOn(Schedulers.io()).filter(mealsItem -> {
            Log.d("Filo", "filter: " + mealsItem.getDaysOfWeeks());
            return mealsItem.getDaysOfWeeks().contains(DaysOfWeek.SUNDAY);
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(
                mealItems -> view.updateUI(DaysOfWeek.SUNDAY, mealItems),
                throwable -> Log.d("Filo", "getCalendar: "+ throwable.getMessage()),
                () -> {}
        ));
        compositeDisposable.add(repo.getCalendar().subscribeOn(Schedulers.io()).filter(mealsItem -> {
            Log.d("Filo", "filter: " + mealsItem.getDaysOfWeeks());
            return mealsItem.getDaysOfWeeks().contains(DaysOfWeek.MONDAY);
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(
                mealsItem -> view.updateUI(DaysOfWeek.MONDAY, mealsItem),
                throwable -> Log.d("Filo", "getCalendar: "+ throwable.getMessage()),
                () -> {}
        ));
        compositeDisposable.add(repo.getCalendar().subscribeOn(Schedulers.io()).filter(mealsItem -> {
            Log.d("Filo", "filter: " + mealsItem.getDaysOfWeeks());
            return mealsItem.getDaysOfWeeks().contains(DaysOfWeek.TUESDAY);
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(
                mealsItem -> view.updateUI(DaysOfWeek.TUESDAY, mealsItem),
                throwable -> Log.d("Filo", "getCalendar: "+ throwable.getMessage()),
                () -> {}
        ));
        compositeDisposable.add(repo.getCalendar().subscribeOn(Schedulers.io()).filter(mealsItem -> {
            Log.d("Filo", "filter: " + mealsItem.getDaysOfWeeks());
            return mealsItem.getDaysOfWeeks().contains(DaysOfWeek.WEDNESDAY);
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(
                mealsItem -> view.updateUI(DaysOfWeek.WEDNESDAY,mealsItem),
                throwable -> Log.d("Filo", "getCalendar: "+ throwable.getMessage()),
                () -> {}
        ));
        compositeDisposable.add(repo.getCalendar().subscribeOn(Schedulers.io()).filter(mealsItem -> {
            Log.d("Filo", "filter: " + mealsItem.getDaysOfWeeks());
            return mealsItem.getDaysOfWeeks().contains(DaysOfWeek.THURSDAY);
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(
                mealsItem -> view.updateUI(DaysOfWeek.THURSDAY,mealsItem),
                throwable -> Log.d("Filo", "getCalendar: "+ throwable.getMessage()),
                () -> {}
        ));
        compositeDisposable.add(repo.getCalendar().subscribeOn(Schedulers.io()).filter(mealsItem -> {
            Log.d("Filo", "filter: " + mealsItem.getDaysOfWeeks());
            return mealsItem.getDaysOfWeeks().contains(DaysOfWeek.FRIDAY);
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(
                mealsItem -> view.updateUI(DaysOfWeek.FRIDAY,mealsItem),
                throwable -> Log.d("Filo", "getCalendar: "+ throwable.getMessage()),
                ()->{}
        ));
        compositeDisposable.add(repo.getCalendar().subscribeOn(Schedulers.io()).filter(mealsItem -> {
            Log.d("Filo", "filter: " + mealsItem.getDaysOfWeeks());
            return mealsItem.getDaysOfWeeks().contains(DaysOfWeek.SATURDAY);
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(
                mealsItem -> view.updateUI(DaysOfWeek.SATURDAY,mealsItem),
                throwable -> Log.d("Filo", "getCalendar: "+ throwable.getMessage()),
                ()->{}
        ));
    }

    public void removeItem(DaysOfWeek day, MealsItem mealsItem) {
        CompositeDisposable disposable = new CompositeDisposable();
        mealsItem.removeDay(day);
        disposable.add(repo.saveMeal(mealsItem).subscribeOn(Schedulers.io()).subscribe(() -> {}, e -> Log.d("Filo", "removeItem: "+e.getMessage()), disposable));
    }

}
