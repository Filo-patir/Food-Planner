package filo.mamdouh.kershhelper.presenters;

import android.util.Log;

import java.util.ArrayList;

import filo.mamdouh.kershhelper.contracts.HomeContract;
import filo.mamdouh.kershhelper.contracts.WeekSetter;
import filo.mamdouh.kershhelper.models.Calendar;
import filo.mamdouh.kershhelper.models.Repostiry;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeActivityPresenter {
    private Repostiry repo;
    private HomeContract.ToolBar toolBarUpdater;
    public HomeActivityPresenter(HomeContract.ToolBar toolBarUpdater,Repostiry repo){
        this.repo = repo;
        this.toolBarUpdater = toolBarUpdater;
    }
    public void getSavedIems(){
        repo.getSavedMeals().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(mealsItems -> toolBarUpdater.updateSavedNumberI(mealsItems.size()));
    }

    public void addToCalendar(String mealid,ArrayList<String> data){
        repo.addToCalendar(new Calendar(mealid,data)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
    }

    public void getSavedItemByID(String id, WeekSetter setter) {
        repo.getMealPlan(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                setter::setData, e-> Log.d("Filo", "getSavedItemByID: "+e.getMessage())
        );
    }
}
