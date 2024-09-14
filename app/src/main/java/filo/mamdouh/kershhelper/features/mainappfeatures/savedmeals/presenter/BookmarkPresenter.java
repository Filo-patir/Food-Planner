package filo.mamdouh.kershhelper.features.mainappfeatures.savedmeals.presenter;

import android.util.Log;

import filo.mamdouh.kershhelper.contracts.BookmarkContract;
import filo.mamdouh.kershhelper.models.MealsItem;
import filo.mamdouh.kershhelper.models.Repository;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class BookmarkPresenter {
    private Repository repo;
    BookmarkContract.View view;
    CompositeDisposable compositeDisposable;
    public BookmarkPresenter(BookmarkContract.View view, Repository repo){
        this.repo = repo;
        this.view = view;
        compositeDisposable = new CompositeDisposable();
    }
    public void getSavedMeals(){
        compositeDisposable.add(repo.getSavedMeals()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view::onGetSavedMeals,e-> Log.d("Filo", "getSavedMeals: "+e.getMessage())));
    }
    public void deleteMeal(MealsItem meal){
        meal.setSaved(false);
        compositeDisposable.add(repo.saveMeal(meal).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(() -> view.removeItem("Removed Successfuly"), e -> view.removeItem(e.getMessage())));
    }
    public void addToCalendar(){

    }
    public void onDestroy(){
        compositeDisposable.dispose();
    }
}
