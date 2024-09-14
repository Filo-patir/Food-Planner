package filo.mamdouh.kershhelper.features.mainappfeatures.home.presenter;


import android.util.Log;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import filo.mamdouh.kershhelper.contracts.HomeContract;
import filo.mamdouh.kershhelper.models.Desserts;
import filo.mamdouh.kershhelper.features.mainappfeatures.home.Updater;
import filo.mamdouh.kershhelper.models.CachingRepositry;
import filo.mamdouh.kershhelper.models.HomeFragmentRowData;
import filo.mamdouh.kershhelper.models.MealsItem;
import filo.mamdouh.kershhelper.models.Repository;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenter {
    private final Repository repo;
    HomeContract.View view;
    CachingRepositry cachingRepositry;
    CompositeDisposable compositeDisposable;
    public HomePresenter(HomeContract.View view, Repository repo , CachingRepositry cachingRepositry){
        this.repo = repo;
        this.view = view;
        compositeDisposable = new CompositeDisposable();
        this.cachingRepositry = cachingRepositry;
    }
    public void getHomeItems(){
        getDailyInspiration();

        compositeDisposable.add(repo.getSavedMeals().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> view.updateUI(new HomeFragmentRowData("Saved Meals",meals)), e-> Log.d("Filo", "getHomeItems: "+e.getMessage()),
                        () -> Log.d("Filo", "getHomeItems: 78"), compositeDisposable
                ));
        getDesserts();
        getRecentlyViewed();
        compositeDisposable.add(repo.getMore().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> view.updateUI(new HomeFragmentRowData("More You Might Like",meals)), e-> Log.d("Filo", "getHomeItems: "+e.getMessage()),
                        compositeDisposable
                ));
    }
    public void saveMeal(MealsItem mealsItem, Updater updater){
        if(mealsItem.isSaved()) {
            mealsItem.setSaved(false);
            compositeDisposable.add(repo.saveMeal(mealsItem).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(() -> view.onSave("Removed Successfully"), e -> view.onSave(e.getMessage()),
                    compositeDisposable));
            updater.updateUI();
        }
        else {
            mealsItem.setSaved(true);
            compositeDisposable.add(repo.saveMeal(mealsItem).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(() -> view.onSave("Saved Successfully"), e -> view.onSave(e.getMessage()),
                    compositeDisposable));
            updater.updateUI();
        }
    }

    private void getDailyInspiration(){
        compositeDisposable.add(getDailyInspirationID().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(strings -> {
            if(strings.get(strings.size()-1).equals(LocalDate.now().toString())){
                compositeDisposable.add(
                        getDailyInspirationItems(strings).subscribe(this::updateUInSaveToFile, e -> Log.d("Filo", "getHomeItems: 76"+e.getMessage()),
                                () -> {},compositeDisposable)
                );
            }
            else{
                Log.d("Filo", "HOME PRESENTER: ");
                compositeDisposable.add(
                        repo.getDailyInspiration().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                this::updateUInSaveToFile, e -> Log.d("Filo", "84" + e.getMessage()),
                                compositeDisposable
                        ));
            }
            },onError-> {
            Log.d("Filo", "2: "+onError.getMessage());
            compositeDisposable.add(repo.getDailyInspiration().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            this::updateUInSaveToFile, e -> Log.d("Filo", "getHomeItems: 91" + e.getMessage()),
                            compositeDisposable
                    ));
        }
        ));
    }

    private Observable<ArrayList<MealsItem>> getDailyInspirationItems(ArrayList<String> strings){
        return Observable.fromCallable(() -> {
            ArrayList<MealsItem> mealsItems = new ArrayList<>();
            for (int i = 0 ; i<strings.size()-1 ; i++) {
                compositeDisposable.add(
                        repo.getMealByID(strings.get(i)).subscribe(mealsItems::add, e -> Log.d("Filo", "getHomeItems: 72"+e.getMessage()))
                );
            }
            return mealsItems;
        });
    }

    private Observable<ArrayList<String>> getDailyInspirationID(){
        return Observable.fromCallable(() -> {
            ArrayList<String> ids = new ArrayList<>();
            compositeDisposable.add(repo.getLocalDailyInspiration().subscribeOn(Schedulers.io()).subscribe(
                    ids::add, onError -> {
                        Log.d("Filo", "58: " + onError.getMessage());
                        compositeDisposable.add(repo.getDailyInspiration().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                                .subscribe(
                                        this::updateUInSaveToFile, e -> Log.d("Filo", "getHomeItems: 61" + e.getMessage())
                                ));
                    }));
            return ids;
        });
    }

    private void getRecentlyViewed(){
        compositeDisposable.add(Observable.fromCallable(() -> {
            ArrayList<MealsItem> mealsItems = new ArrayList<>();
                    compositeDisposable.add(repo.getRecentlyViewed().subscribeOn(Schedulers.io()).subscribe(
                    id -> compositeDisposable.add( repo.getMealByID(id).subscribeOn(Schedulers.io()).subscribe(mealsItems::add, e -> Log.d("Filo", "getRecentlyViewed: "+e.getMessage())
                    )), throwable -> Log.d("Filo", "getRecentlyViewed: "+throwable.getMessage()), () -> {}, compositeDisposable));
            return mealsItems;
        }).observeOn(AndroidSchedulers.mainThread()).subscribe( mealsItems -> view.updateUI(new HomeFragmentRowData("Recently Viewed",mealsItems)),e -> Log.d("Filo", "getRecentlyViewed:"+e.getMessage()),
                        () -> {},compositeDisposable)
        );
    }

    private void getDesserts(){
        List<Desserts> desserts = cachingRepositry.getDesserts();
        if (desserts.isEmpty()) {
            compositeDisposable.add(getDessertsItems()
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(mealsItems -> view.updateUI(new HomeFragmentRowData("Desserts", mealsItems)), e -> Log.d("Filo", "getDesserts:" + e.getMessage()),
                            () -> {}, compositeDisposable)
            );
        }
        else {
            view.updateUI(new HomeFragmentRowData("Desserts", cachingRepositry.getDesserts()));
        }
    }

    private Observable<ArrayList<MealsItem>> getDessertsItems(){
        return Observable.fromCallable(() -> {
            ArrayList<MealsItem> mealsItems = new ArrayList<>();
            compositeDisposable.add(repo.getDesserts().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                    id -> {
                        for (MealsItem item : id) {
                            compositeDisposable.add(repo.getMealByID(item.getIdMeal()).subscribeOn(Schedulers.io()).subscribe(
                                    meal -> {
                                        mealsItems.add(meal);
                                        Desserts desert = new Desserts(meal.getIdMeal(), meal.getStrMeal(), meal.getStrArea(), meal.getIngredients().size(), meal.getStrMealThumb());
                                        cachingRepositry.addDessert(desert);
                                    }
                                    , e -> Log.d("Filo", "getDesserts: " + e.getMessage()),
                                    () -> {}, compositeDisposable
                            ));
                        }
                    }, throwable -> Log.d("Filo", "getDesserts: " + throwable.getMessage())));
            return mealsItems;});
    }


    private void updateUInSaveToFile(ArrayList<MealsItem> meals){
        view.updateUI(new HomeFragmentRowData("Daily Inspiration", meals));
        if(!meals.isEmpty()){
        ArrayList<String> saveids = new ArrayList<>();
        for (MealsItem meal : meals)  saveids.add(meal.getIdMeal());
        saveids.add(LocalDate.now().toString());
        compositeDisposable.add(repo.removeFile("Daily_Inspiration")
                .subscribeOn(Schedulers.io()).subscribe(o -> {}, e -> Log.d("Filo", "wtf: "+ e.toString()), () -> {}, compositeDisposable));
        compositeDisposable.add(repo.saveLocalDailyInspiration(saveids).subscribeOn(Schedulers.io()).subscribe(o -> {},e-> Log.d("Filo", "5: "+e.getMessage()),() -> {}, compositeDisposable));
            }
        }

    public void saveItemListener(Desserts mealID, Updater updater) {
        // TODO
    }
}
