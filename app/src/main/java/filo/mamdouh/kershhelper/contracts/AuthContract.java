package filo.mamdouh.kershhelper.contracts;

public interface AuthContract {

        interface View{
                void onFailed(String message);
                void onSuccess();
        }

        interface Presenter {
                void onSuccessSignup(String userid);
                void onFailSignup(String message);
        }
}
