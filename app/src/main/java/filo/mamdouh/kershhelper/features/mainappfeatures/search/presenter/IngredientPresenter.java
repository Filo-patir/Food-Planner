package filo.mamdouh.kershhelper.features.mainappfeatures.search.presenter;

import filo.mamdouh.kershhelper.contracts.SearchItemContract;
import filo.mamdouh.kershhelper.models.Repostiry;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class IngredientPresenter {
    private Repostiry repo;
    SearchItemContract.View view;
    CompositeDisposable compositeDisposable;
    public IngredientPresenter(SearchItemContract.View view, Repostiry repo){
        this.repo = repo;
        this.view = view;
        compositeDisposable = new CompositeDisposable();
    }
}
