package filo.mamdouh.kershhelper.features.mainappfeatures.search.presenter;


import android.util.Log;

import filo.mamdouh.kershhelper.contracts.SearchItemContract;
import filo.mamdouh.kershhelper.models.Repostiry;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchItemsPresenter {
    private Repostiry repo;
    SearchItemContract.View view;
    CompositeDisposable compositeDisposable;
    public SearchItemsPresenter(SearchItemContract.View view, Repostiry repo){
        this.repo = repo;
        this.view = view;
        compositeDisposable = new CompositeDisposable();
    }

    public void searchByIngredient(String name){
        compositeDisposable.add(repo.searchByIngredients(name).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                mealsItem -> view.updateUI(mealsItem),e-> Log.d("TAG", "searchByIngredient: "+e.getMessage())
        ));
    }

    public void searchByMeal(String name) {
        compositeDisposable.add(repo.searchByMeal(name).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                mealsItem -> view.updateUI(mealsItem),e-> Log.d("Filo", "searchByMeal: "+e.getMessage())
        ));
    }

    public void searchByCategory(String category) {
        compositeDisposable.add(repo.searchByCategory(category).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                mealsItem -> view.updateUI(mealsItem),e-> Log.d("Filo", "searchByMeal: "+e.getMessage())
        ));
    }
    public void searchByArea(String txt) {

        compositeDisposable.add(repo.getAREA().subscribeOn(Schedulers.io()).filter(area -> area.contains(txt.toLowerCase())).subscribe(
                area -> {
                    repo.searchByArea(area).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                            mealsItem -> view.updateUI(mealsItem),e-> Log.d("Filo", "searchByAREA: "+e.getMessage())
                    );
                }
        ));

    }
    public void disposeObservables(){
        compositeDisposable.clear();
    }


}
