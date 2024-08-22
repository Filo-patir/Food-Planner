package filo.mamdouh.kershhelper.features.mainappfeatures.home.presenter;


import android.util.Log;

import filo.mamdouh.kershhelper.contracts.HomeContract;
import filo.mamdouh.kershhelper.models.HomeFragmentRowData;
import filo.mamdouh.kershhelper.models.Repostiry;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenter {
    Repostiry repo;
    public void getHomeItems(HomeContract.View view){
        repo = Repostiry.getInstance();
        repo.getRandomMeal().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> {
                            view.updateUI(new HomeFragmentRowData("Daily Inspiration",meals));
                        }, e-> Log.d("TAG", "getHomeItems: "+e.getMessage())
                );
    }

}
