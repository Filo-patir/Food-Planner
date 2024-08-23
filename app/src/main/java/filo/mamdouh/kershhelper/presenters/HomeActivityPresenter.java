package filo.mamdouh.kershhelper.presenters;

import filo.mamdouh.kershhelper.contracts.HomeContract;
import filo.mamdouh.kershhelper.models.Repostiry;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeActivityPresenter {
    private Repostiry repo;
    private HomeContract.ToolBar toolBarUpdater;
    public HomeActivityPresenter(HomeContract.ToolBar toolBarUpdater,Repostiry repo){
        this.repo = repo;
        this.toolBarUpdater = toolBarUpdater;
    }
    public void getSavedIems(){
        repo.getSavedMeals().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(mealsItems -> toolBarUpdater.updateUI(mealsItems.size()));
    }
}
