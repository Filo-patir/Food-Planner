package filo.mamdouh.kershhelper.features.welcome_screen_features.presenters;

import filo.mamdouh.kershhelper.contracts.LoginContract;
import filo.mamdouh.kershhelper.logic.auth.Authentication;
import filo.mamdouh.kershhelper.models.Client;
import filo.mamdouh.kershhelper.models.Repository;
import filo.mamdouh.kershhelper.models.User;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginPresenter implements LoginContract.Presenter {
    private final Repository repo;
    private final LoginContract.View view;
    private final CompositeDisposable compositeDisposable;
    private Authentication authentication;

    public LoginPresenter(LoginContract.View view, Repository repo) {
        this.repo = repo;
        this.view = view;
        compositeDisposable = new CompositeDisposable();
        authentication = new Authentication(this);
    }

    public void onLogin(String email, String password){
        authentication.loginAuth(email, password);
    }

    @Override
    public void onSuccess(String uid , Observable<User> user) {
        CompositeDisposable disposable = new CompositeDisposable();
        disposable.add(user.subscribeOn(Schedulers.io()).subscribe(
                currentUser -> {
                    Client.getInstance(uid, currentUser);
                    view.onSuccess();
                    disposable.clear();
                }
        ));
        repo.saveLogin(uid);
    }

    @Override
    public void onFailure(String msg) {
        view.onFailure(msg);
    }
}
