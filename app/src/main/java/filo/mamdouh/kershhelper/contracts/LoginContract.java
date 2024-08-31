package filo.mamdouh.kershhelper.contracts;

import filo.mamdouh.kershhelper.models.User;
import io.reactivex.rxjava3.core.Observable;

public interface LoginContract {
    interface View{
        void onSuccess();
        void onFailure(String msg);
    }
    interface Presenter{
        void onSuccess(String uid , Observable<User> user);
        void onFailure(String msg);
    }
}
