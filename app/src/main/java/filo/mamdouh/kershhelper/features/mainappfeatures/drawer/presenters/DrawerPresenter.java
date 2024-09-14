package filo.mamdouh.kershhelper.features.mainappfeatures.drawer.presenters;

import android.util.Log;

import filo.mamdouh.kershhelper.contracts.DrawerContract;
import filo.mamdouh.kershhelper.logic.auth.Authentication;
import filo.mamdouh.kershhelper.models.Client;
import filo.mamdouh.kershhelper.models.Repository;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DrawerPresenter {
    private final Authentication auth;
    private final DrawerContract.View view;
    private final Repository repo;
    public DrawerPresenter(DrawerContract.View view, Repository repo)
    {
        this.view = view;
        this.repo = repo;
        auth = new Authentication();
    }
    public void logOut(){
        auth.signOut();
        Client.clear();
        CompositeDisposable disposable = new CompositeDisposable();
        disposable.add(repo.logout().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                item -> disposable.add(item.subscribeOn(Schedulers.io()).subscribe()),
                e -> Log.d("Filo", "logOut: " + e.getMessage()),
                ()-> {
                    disposable.clear();
                    view.logOut();
                }
        ));
    }
}
