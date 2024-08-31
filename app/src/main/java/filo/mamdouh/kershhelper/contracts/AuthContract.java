package filo.mamdouh.kershhelper.contracts;

import filo.mamdouh.kershhelper.models.User;

public interface AuthContract {

        interface View{
                public void onFailed(String message);
                public void onSucess();
        }

        interface Presenter {
                void onSucessLogin(User user);

                void onFailLogin(String message);

                void onSuccessSignup(String userid);

                void onFailSignup(String message);
        }
}
