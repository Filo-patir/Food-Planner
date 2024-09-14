package filo.mamdouh.kershhelper.features.mainappfeatures.search.presenter;

import filo.mamdouh.kershhelper.contracts.SearchItemContract;
import filo.mamdouh.kershhelper.models.Repository;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class IngredientPresenter {
    private Repository repo;
    SearchItemContract.View view;
    CompositeDisposable compositeDisposable;
    public IngredientPresenter(SearchItemContract.View view, Repository repo){
        this.repo = repo;
        this.view = view;
        compositeDisposable = new CompositeDisposable();
    }
}
