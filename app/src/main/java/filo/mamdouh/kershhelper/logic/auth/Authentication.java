package filo.mamdouh.kershhelper.logic.auth;

import android.util.Log;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import filo.mamdouh.kershhelper.contracts.AuthContract;
import filo.mamdouh.kershhelper.models.User;

public class Authentication {
    private FirebaseAuth auth;
    private AuthContract.Presenter presenter;

    public Authentication(AuthContract.Presenter presenter){
        auth = FirebaseAuth.getInstance();
        this.presenter = presenter;
    }

    public User getAccount(){
        User user = null;
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null){
            user = toUser(currentUser);
        }
        return user;
    }

    public void signupAuth(String email,String password){
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    presenter.onSuccessSignup();
                }
                else {
                    presenter.onFailSignup(task.getException().getMessage());
                }
            }
        });
    }

    public void loginAuth(String email,String password){
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
           if(task.isComplete()){
               User user = toUser(auth.getCurrentUser());
               presenter.onSucessLogin(user);
           }
           else {
               presenter.onFailLogin(task.getException().getMessage());
           }
        });
    }

    public void loginWithGmail(){
        try {


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void loginWithFacebook(){
    }

    public void signOut(){
        auth.signOut();
    }

    private User toUser(FirebaseUser user){
        return User.getInstance(user.getUid(),user.getDisplayName(),user.getEmail(),String.valueOf(user.getPhotoUrl()));
    }

}
