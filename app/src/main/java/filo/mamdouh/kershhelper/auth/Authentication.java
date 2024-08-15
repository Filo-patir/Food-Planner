package filo.mamdouh.kershhelper.auth;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import filo.mamdouh.kershhelper.models.User;

public class Authentication {
    private FirebaseAuth auth;
    public Authentication(){
        auth = FirebaseAuth.getInstance();
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
                    User user = toUser(auth.getCurrentUser());
                }
                else {
                    Log.d("FAILED TO SIGN UP", "onComplete: FAilED");
                }
            }
        });
    }
    public void loginAuth(String email,String password){
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
           if(task.isComplete()){
               User user = toUser(auth.getCurrentUser());
           }
           else {
               Log.d("FAILED TO LOGIN", "loginAuth: LOGIN FAILED");
           }
        });
    }

    public void loginWithGmail(){
    }
    public void loginWithFacebook(){
    }

    private User toUser(FirebaseUser user){
        return  new User(user.getUid(),user.getDisplayName(),user.getEmail(),String.valueOf(user.getPhotoUrl()));
    }
}
