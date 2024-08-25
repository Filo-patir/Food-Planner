package filo.mamdouh.kershhelper.features.mainappfeatures.search.presenter;


import android.util.Log;

import filo.mamdouh.kershhelper.contracts.SearchItemContract;
import filo.mamdouh.kershhelper.models.Repostiry;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
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
        repo.searchByIngredients(name).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                mealsItem -> view.updateUI(mealsItem),e-> Log.d("TAG", "searchByIngredient: "+e.getMessage())
        );
    }

    public void searchByMeal(String name) {
        view.removeList();
        repo.searchByMeal(name).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                mealsItem -> view.updateUI(mealsItem),e-> Log.d("Filo", "searchByMeal: "+e.getMessage())
        );
    }
}
