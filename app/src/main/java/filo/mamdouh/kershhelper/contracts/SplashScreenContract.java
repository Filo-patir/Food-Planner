package filo.mamdouh.kershhelper.contracts;


import androidx.appcompat.app.AppCompatActivity;

import filo.mamdouh.kershhelper.models.User;
import io.reactivex.rxjava3.core.Observable;

public interface SplashScreenContract {
    interface View {
        void Navigate(AppCompatActivity app);
    }
    interface Presenter{
        void onSuccess(String uid, Observable<User> user);
    }
}
