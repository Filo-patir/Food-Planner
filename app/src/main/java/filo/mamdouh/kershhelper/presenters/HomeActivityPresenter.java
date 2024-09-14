package filo.mamdouh.kershhelper.presenters;

import android.util.Log;

import java.util.ArrayList;

import filo.mamdouh.kershhelper.contracts.HomeContract;
import filo.mamdouh.kershhelper.contracts.WeekSetter;
import filo.mamdouh.kershhelper.models.DaysOfWeek;
import filo.mamdouh.kershhelper.models.MealsItem;
import filo.mamdouh.kershhelper.models.Repository;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeActivityPresenter {
    private final Repository repo;
    private final HomeContract.Activity activityUpdater;
    public HomeActivityPresenter(HomeContract.Activity activityUpdater, Repository repo){
        this.repo = repo;
        this.activityUpdater = activityUpdater;
    }
    public void getSavedItems(){
        CompositeDisposable disposable = new CompositeDisposable();
        disposable.add(repo.getSavedMeals().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        mealsItems -> activityUpdater.updateSavedNumberI(mealsItems.size()),
                        throwable -> Log.d("Filo", "getSavedItems: "+throwable.getMessage()),
                        disposable::clear));
    }

    public void addToCalendar(MealsItem meal, ArrayList<DaysOfWeek> data){
        meal.setDaysOfWeeks(data);
        Log.d("Filo", "addToCalendar: " + meal.getDaysOfWeeks());
        CompositeDisposable disposable = new CompositeDisposable();
        disposable.add(repo.saveMeal(meal).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                () -> activityUpdater.showToast("Added to calendar"),
                e-> Log.d("Filo", "addToCalendar: "+e.getMessage()),
                disposable
        ));
    }

    public void getSavedItemByID(String id, WeekSetter setter) {
        CompositeDisposable disposable = new CompositeDisposable();
        disposable.add(repo.getSavedMealByID(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                mealsItem -> setter.setData(mealsItem.getDaysOfWeeks()),
                e-> Log.d("Filo", "getSavedItemByID: "+e.getMessage()),
                disposable::clear
        ));
    }
}
