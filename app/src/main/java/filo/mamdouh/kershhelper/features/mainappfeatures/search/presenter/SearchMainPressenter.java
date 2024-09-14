package filo.mamdouh.kershhelper.features.mainappfeatures.search.presenter;

import android.util.Log;

import java.util.ArrayList;

import filo.mamdouh.kershhelper.contracts.SearchContract;
import filo.mamdouh.kershhelper.models.IngredientsRoot;
import filo.mamdouh.kershhelper.models.Repository;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchMainPressenter {
    private Repository repo;
    SearchContract.View view;
    CompositeDisposable compositeDisposable;
    public SearchMainPressenter(SearchContract.View view, Repository repo){
        this.repo = repo;
        this.view = view;
        compositeDisposable = new CompositeDisposable();
    }


    public void getIngredients() {
        compositeDisposable.add(repo.getIngredients().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                ingredients -> {
                    ArrayList<IngredientsRoot.Ingredient> finalIngredients = new ArrayList<>();
                    for (int i = 0 ; i<10;i++){
                        finalIngredients.add(ingredients.get(i));
                    }
                    view.updateIngredients(finalIngredients);
                }, e -> Log.d("Filo", "getIngredients: "+ e.getMessage())
        ));
    }

    public void getCategories() {
        compositeDisposable.add(repo.getCategories().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                categories -> view.updateCategories(categories),e -> Log.d("Filo", "getCategories: "+e.getMessage())
        ));
    }

    public void onDestroy(){
        compositeDisposable.dispose();
    }

}
