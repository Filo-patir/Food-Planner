package filo.mamdouh.kershhelper.logic.auth;

import android.util.Log;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import filo.mamdouh.kershhelper.contracts.AuthContract;
import filo.mamdouh.kershhelper.contracts.LoginContract;
import filo.mamdouh.kershhelper.contracts.SplashScreenContract;
import filo.mamdouh.kershhelper.datastorage.firebase.FirebaseFireStoreDB;

public class Authentication {
    private final FirebaseAuth auth;
    private AuthContract.Presenter presenter;
    private LoginContract.Presenter loginPresenter;
    private FirebaseFireStoreDB db;

    public Authentication(){
        auth = FirebaseAuth.getInstance();
        db = FirebaseFireStoreDB.getInstance();
    }
    public Authentication(AuthContract.Presenter presenter){
        auth = FirebaseAuth.getInstance();
        this.presenter = presenter;
    }
    public Authentication(LoginContract.Presenter presenter){
        auth = FirebaseAuth.getInstance();
        this.loginPresenter = presenter;
        db = FirebaseFireStoreDB.getInstance();
    }

    public void getAccount(SplashScreenContract.Presenter listener){
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null){
            Log.d("Filo", "getAccount: " + currentUser.getUid());
            listener.onSuccess(currentUser.getUid(),db.getUser(currentUser.getUid()));
        }
    }

    public void signupAuth(String email,String password){
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                presenter.onSuccessSignup(Objects.requireNonNull(task.getResult().getUser()).getUid());
            }
            else {
                presenter.onFailSignup(Objects.requireNonNull(task.getException()).getMessage());
            }
        });
    }

    public void loginAuth(String email,String password){
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
           if(task.isComplete()){
               FirebaseUser currentUser = auth.getCurrentUser();
               assert currentUser != null;
               loginPresenter.onSuccess(currentUser.getUid(),db.getUser(currentUser.getUid()));
           }
           else {
               loginPresenter.onFailure(Objects.requireNonNull(task.getException()).getMessage());
           }
        });
    }

    public void loginWithGmail(){
    }
    public void loginWithFacebook(){
    }

    public void signOut(){
        auth.signOut();
    }

}
