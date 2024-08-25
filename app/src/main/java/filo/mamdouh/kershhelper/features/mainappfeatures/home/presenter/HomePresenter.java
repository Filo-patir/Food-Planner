package filo.mamdouh.kershhelper.features.mainappfeatures.home.presenter;


import android.annotation.SuppressLint;
import android.util.Log;

import java.time.LocalDate;
import java.util.ArrayList;
import filo.mamdouh.kershhelper.contracts.HomeContract;
import filo.mamdouh.kershhelper.features.mainappfeatures.home.Updater;
import filo.mamdouh.kershhelper.models.HomeFragmentRowData;
import filo.mamdouh.kershhelper.models.MealsItem;
import filo.mamdouh.kershhelper.models.Repostiry;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenter {
    private Repostiry repo;
    HomeContract.View view;
    CompositeDisposable compositeDisposable;
    public HomePresenter(HomeContract.View view,Repostiry repo){
        this.repo = repo;
        this.view = view;
        compositeDisposable = new CompositeDisposable();
    }
    @SuppressLint("CheckResult")
    public void getHomeItems(){
        getDailyInspiration();

        compositeDisposable.add(repo.getSavedMeals().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> {
                            view.updateUI(new HomeFragmentRowData("Saved Meals",meals));
                        }, e-> Log.d("TAG", "getHomeItems: "+e.getMessage())
                ));
        compositeDisposable.add(repo.getDesserts().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> {
                            view.updateUI(new HomeFragmentRowData("Desserts",meals));
                        }, e-> Log.d("TAG", "getHomeItems: "+e.getMessage())
                ));
        compositeDisposable.add(repo.getRandomMeals().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> {
                            view.updateUI(new HomeFragmentRowData("Recently Viewed",null));
                        }, e-> Log.d("TAG", "getHomeItems: "+e.getMessage())
                ));
        compositeDisposable.add(repo.getMore().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> {
                            view.updateUI(new HomeFragmentRowData("More You Might Like",meals));
                            compositeDisposable.dispose();
                        }, e-> Log.d("TAG", "getHomeItems: "+e.getMessage())
                ));
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
        compositeDisposable.add(Observable.fromCallable(() -> {
            ArrayList<String> ids = new ArrayList<>();
            repo.getLocalDailyInspiration().subscribeOn(Schedulers.io()).subscribe(
                    id -> ids.add(id),onError -> {
                        Log.d("Filo", "1: " + onError.getMessage());
                        repo.getDailyInspiration().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                                .subscribe(
                                        meals -> {
                                            view.updateUI(new HomeFragmentRowData("Daily Inspiration", meals));
                                            ArrayList<String> saveids = new ArrayList<>();
                                            for (MealsItem meal : meals)  saveids.add(meal.getIdMeal());
                                            saveids.add(LocalDate.now().toString());
                                            repo.saveLocalDailyInspiration(saveids).subscribeOn(Schedulers.io()).subscribe(o -> {},e-> Log.d("Filo", "5: "+e.getMessage()));
                                        }, e -> Log.d("TAG", "getHomeItems: " + e.getMessage())
                                );
                    });
            return ids;
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(strings -> {
            if(strings.get(strings.size()-1).equals(LocalDate.now().toString())){
                Observable.fromCallable(() -> {
                    ArrayList<MealsItem> mealsItems = new ArrayList<>();
                    for (int i = 0 ; i<strings.size()-1 ; i++) {
                        repo.getMealByID(strings.get(i)).subscribe(
                                item -> mealsItems.add(item)
                        );
                    }
                    return mealsItems;
                }).subscribe(mealsItems -> {
                    view.updateUI(new HomeFragmentRowData("Daily Inspiration", mealsItems));
                });
            }
            else{
                Log.d("Filo", "2: ");
                repo.getDailyInspiration().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                meals -> {
                                    view.updateUI(new HomeFragmentRowData("Daily Inspiration", meals));
                                }, e -> Log.d("TAG", "getHomeItems: " + e.getMessage())
                        );
            }
            },onError-> {
            Log.d("Filo", "2: "+onError.getMessage());
            repo.getDailyInspiration().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            meals -> {
                                view.updateUI(new HomeFragmentRowData("Daily Inspiration", meals));
                            }, e -> Log.d("TAG", "getHomeItems: " + e.getMessage())
                    );}));
    }
    public void onDestroy(){
        compositeDisposable.dispose();
    }
}
