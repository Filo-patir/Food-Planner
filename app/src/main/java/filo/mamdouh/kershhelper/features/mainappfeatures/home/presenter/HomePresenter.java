package filo.mamdouh.kershhelper.features.mainappfeatures.home.presenter;


import android.annotation.SuppressLint;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import filo.mamdouh.kershhelper.contracts.HomeContract;
import filo.mamdouh.kershhelper.features.communicators.OnItemClickListener;
import filo.mamdouh.kershhelper.features.mainappfeatures.home.Updater;
import filo.mamdouh.kershhelper.models.HomeFragmentRowData;
import filo.mamdouh.kershhelper.models.Meals;
import filo.mamdouh.kershhelper.models.MealsItem;
import filo.mamdouh.kershhelper.models.Repostiry;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenter {
    private Repostiry repo;
    HomeContract.View view;
    public HomePresenter(HomeContract.View view,Repostiry repo){
        this.repo = repo;
        this.view = view;
    }
    @SuppressLint("CheckResult")
    public void getHomeItems(){
        repo.getDailyInspiration().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> {
                            view.updateUI(new HomeFragmentRowData("Daily Inspiration", meals));
                        }, e -> Log.d("TAG", "getHomeItems: " + e.getMessage())
                );
        repo.getSavedMeals().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> {
                            view.updateUI(new HomeFragmentRowData("Saved Meals",meals));
                        }, e-> Log.d("TAG", "getHomeItems: "+e.getMessage())
                );
        repo.getDesserts().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> {
                            view.updateUI(new HomeFragmentRowData("Desserts",meals));
                        }, e-> Log.d("TAG", "getHomeItems: "+e.getMessage())
                );
        repo.getRandomMeals().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> {
                            view.updateUI(new HomeFragmentRowData("Recently Viewed",null));
                        }, e-> Log.d("TAG", "getHomeItems: "+e.getMessage())
                );
        repo.getMore().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> {
                            view.updateUI(new HomeFragmentRowData("More You Might Like",meals));
                        }, e-> Log.d("TAG", "getHomeItems: "+e.getMessage())
                );
    }
    public void saveMeal(MealsItem mealsItem, Updater updater){
        if(mealsItem.isSaved()) {
            mealsItem.setSaved(false);
            repo.saveMeal(mealsItem).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(() -> view.onSave("Removed Successfuly"), e -> view.onSave(e.getMessage()));
            updater.updateUI();
        }
        else {
            mealsItem.setSaved(true);
            repo.saveMeal(mealsItem).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(() -> view.onSave("Saved Successfuly"), e -> view.onSave(e.getMessage()));
            updater.updateUI();
        }
    }

    public void getDailyInspiration(){
        List<ArrayList<Integer>> ids = repo.getLocalDailyInspiration().subscribeOn(Schedulers.io()).toList().blockingGet();
        if(ids != null){
            repo.getArrayofMealsByID();
        }
        else {

        }
    }
}
