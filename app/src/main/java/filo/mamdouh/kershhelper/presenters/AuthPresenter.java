package filo.mamdouh.kershhelper.presenters;

import filo.mamdouh.kershhelper.contracts.AuthContract;
import filo.mamdouh.kershhelper.datastorage.firebase.FirebaseFireStoreDB;
import filo.mamdouh.kershhelper.logic.auth.Authentication;
import filo.mamdouh.kershhelper.models.User;

public class AuthPresenter implements AuthContract.Presenter {
    private final AuthContract.View view;
    private final FirebaseFireStoreDB db;
    private User user;
    private final Authentication auth = new Authentication(this);

    public AuthPresenter(AuthContract.View view){
        this.view = view;
        db = FirebaseFireStoreDB.getInstance();
    }

    public void onSignup(String displayName,String email,String password){
        user = new User(displayName,email,"");
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
    public void onSuccessSignup(String uid) {
        db.saveUser(uid,user.getUsername(),user.getEmail(),user.getImg());
        view.onSuccess();
    }

    @Override
    public void onFailSignup(String message) {
        view.onFailed(message);
    }
}
