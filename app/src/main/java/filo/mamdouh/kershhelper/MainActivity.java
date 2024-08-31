package filo.mamdouh.kershhelper;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import filo.mamdouh.kershhelper.contracts.SplashScreenContract;
import filo.mamdouh.kershhelper.datastorage.local.FileHandler;
import filo.mamdouh.kershhelper.datastorage.local.SharedPrefrenceHandler;
import filo.mamdouh.kershhelper.datastorage.network.RetrofitClient;
import filo.mamdouh.kershhelper.datastorage.room.calendar.CalendarDataSourceImpl;
import filo.mamdouh.kershhelper.datastorage.room.savedmeals.SavedMealsDataSourceImpl;
import filo.mamdouh.kershhelper.models.Repostiry;
import filo.mamdouh.kershhelper.presenters.SplashScreenPresenter;

public class MainActivity extends AppCompatActivity implements SplashScreenContract.View {
    SplashScreenPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN  | View.SYSTEM_UI_FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);
        presenter = new SplashScreenPresenter(this, Repostiry.getInstance(FileHandler.getInstance(this),
                SavedMealsDataSourceImpl.getInstance(this), CalendarDataSourceImpl.getInstance(this)
                , RetrofitClient.getInstance(this), SharedPrefrenceHandler.getInstance(this)));
    }


    @Override
    public void Navigate(AppCompatActivity app) {
        Navigator.toActivity(this, app);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.disposeSubscribe();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(presenter != null)
            presenter.checkLogin();
    }
}
