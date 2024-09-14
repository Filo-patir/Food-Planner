package filo.mamdouh.kershhelper.presenters;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import filo.mamdouh.kershhelper.HomeActivity;
import filo.mamdouh.kershhelper.WelcomeActivity;
import filo.mamdouh.kershhelper.contracts.SplashScreenContract;
import filo.mamdouh.kershhelper.logic.auth.Authentication;
import filo.mamdouh.kershhelper.models.Client;
import filo.mamdouh.kershhelper.models.Repository;
import filo.mamdouh.kershhelper.models.User;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SplashScreenPresenter implements SplashScreenContract.Presenter{
    private final Repository repo;
    private final SplashScreenContract.View view;
    Authentication auth;
    CompositeDisposable compositeDisposable;
    public SplashScreenPresenter(SplashScreenContract.View view , Repository repo)
    {
        Log.d("Filo", "SplashScreenPresenter: A");
        this.view = view;
        this.repo = repo;
        auth = new Authentication();
        compositeDisposable = new CompositeDisposable();
    }

    public void checkLogin(){
        Log.d("Filo", "checkLogin: B");
        compositeDisposable.add(repo.getLoginStatus().subscribeOn(Schedulers.io()).delay(3, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(
                s -> {
                    if(s == null || s.isEmpty()) view.Navigate(new WelcomeActivity());
                    else {
                        auth.getAccount(this);
                    }
                }, throwable -> {
                    Log.d("Filo", "checkLogin: "+throwable.getMessage());
                    view.Navigate(new WelcomeActivity());
                }
        ));
    }


    public void disposeSubscribe(){
        Log.d("Filo", "disposeSubscribe: C");
        compositeDisposable.clear();
    }

    @Override
    public void onSuccess(String uid, Observable<User> user) {
        CompositeDisposable disposable = new CompositeDisposable();
        disposable.add(user.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        user1 -> {
                            Log.d("Filo", "onSuccess: "+ user1.getUsername());
                            Client.getInstance(uid,user1);
                            view.Navigate(new HomeActivity());
                            disposable.dispose();
                        },
                        throwable -> {
                            Log.d("Filo", "onSuccess: "+throwable.getMessage());
                            disposable.dispose();
                        }
        ));
    }
}
