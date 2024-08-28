package filo.mamdouh.kershhelper.presenters;

import filo.mamdouh.kershhelper.contracts.AuthContract;
import filo.mamdouh.kershhelper.logic.auth.Authentication;
import filo.mamdouh.kershhelper.models.Repostiry;
import filo.mamdouh.kershhelper.models.User;

public class AuthPresenter implements AuthContract.Presenter {
    private final AuthContract.View view;
    private Repostiry repo;

    private final Authentication auth = new Authentication(this);

    public AuthPresenter(AuthContract.View view, Repostiry repo){
        this.repo = repo;
        this.view = view;
    }

    public void onSignup(String email,String password){
        auth.signupAuth(email,password);
    }

    public void onLogin(String email,String password){
        auth.loginAuth(email,password);
    }

    public void googleLogin(){
        auth.loginWithGmail();
    }

    public void facebookLogin(){
        auth.loginWithFacebook();
    }

    @Override
    public void onSucessLogin(User user) {
        view.onSucess();
    }

    @Override
    public void onFailLogin(String message) {
        view.onFailed(message);
    }

    @Override
    public void onSuccessSignup() {
        view.onSucess();
    }

    @Override
    public void onFailSignup(String message) {
        view.onFailed(message);
    }
}
