package filo.mamdouh.kershhelper.features.mainappfeatures.mealsfragment.presenter;

import android.util.Log;

import filo.mamdouh.kershhelper.contracts.MealDetailsContract;
import filo.mamdouh.kershhelper.models.MealsItem;
import filo.mamdouh.kershhelper.models.Repostiry;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealDetailsPressenter {
    private Repostiry repo;
    MealDetailsContract.View view;
    CompositeDisposable compositeDisposable;

    public MealDetailsPressenter(MealDetailsContract.View view, Repostiry repo){
        this.repo = repo;
        this.view = view;
        compositeDisposable = new CompositeDisposable();
    }

    public void getSavedItem(String id){
        repo.getSavedMealByID(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                item-> view.updateUI(item),e-> Log.d("Filo", "getSavedItem: "+ e.getMessage())
        );
    }

    public void getMealByID(String mealID) {
        repo.getMealByID(mealID).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                item-> view.updateUI(item),e-> Log.d("Filo", "getMealByID: "+ e.getMessage())
        );
    }

    public void onSaveButtonClick(MealsItem meal) {
        if(meal.isSaved()){
            meal.setSaved(false);
            repo.saveMeal(meal).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                    ()-> view.updateSaveBtn(false),e-> Log.d("Filo", "onSaveButtonClick: "+ e.getMessage())
            );
        }
        else {
            meal.setSaved(true);
            repo.saveMeal(meal).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                    ()-> view.updateSaveBtn(true),e-> Log.d("Filo", "onSaveButtonClick: "+ e.getMessage())
            );
        }
    }

}
