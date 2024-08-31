package filo.mamdouh.kershhelper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import filo.mamdouh.kershhelper.contracts.HomeContract;
import filo.mamdouh.kershhelper.contracts.WeekSetter;
import filo.mamdouh.kershhelper.datastorage.local.FileHandler;
import filo.mamdouh.kershhelper.datastorage.local.SharedPrefrenceHandler;
import filo.mamdouh.kershhelper.datastorage.network.RetrofitClient;
import filo.mamdouh.kershhelper.datastorage.room.calendar.CalendarDataSourceImpl;
import filo.mamdouh.kershhelper.datastorage.room.savedmeals.SavedMealsDataSourceImpl;
import filo.mamdouh.kershhelper.features.communicators.Planner;
import filo.mamdouh.kershhelper.models.Repostiry;
import filo.mamdouh.kershhelper.presenters.HomeActivityPresenter;

public class HomeActivity extends AppCompatActivity implements HomeContract.ToolBar , Planner {
    private ImageButton drawer;
    private Button bookMarkBtn;
    private DrawerLayout mDrawerLayout;
    private OnBackPressedCallback callback;
    private HomeActivityPresenter presenter;
    BottomNavigationView navigationView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        presenter = new HomeActivityPresenter(this, Repostiry.getInstance(FileHandler.getInstance(this),
                SavedMealsDataSourceImpl.getInstance(this), CalendarDataSourceImpl.getInstance(this)
                , RetrofitClient.getInstance(this), SharedPrefrenceHandler.getInstance(this)));
        presenter.getSavedIems();
        navigationView = findViewById(R.id.bottomNavigationView);
        mDrawerLayout =findViewById(R.id.HomeActivityLayout);
        drawer = findViewById(R.id.profileButton);
        bookMarkBtn = findViewById(R.id.toolbarSavedBtn);
        drawer.setOnClickListener(l->
            mDrawerLayout.openDrawer(GravityCompat.START)
        );
        mDrawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerClosed(View drawerView){
                super.onDrawerClosed(drawerView);

            }
        });
        callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if(mDrawerLayout.isDrawerOpen(GravityCompat.START)) mDrawerLayout.closeDrawer(GravityCompat.START);
                else {
                    Toast.makeText(HomeActivity.this, "press Back Again to leave", Toast.LENGTH_SHORT).show();
                    callback.setEnabled(false);
                    new Handler(Looper.getMainLooper()).postDelayed(()->callback.setEnabled(true),5000);
                }
            }
        };
        getOnBackPressedDispatcher().addCallback(this,callback);
        bookMarkBtn.setOnClickListener(l->{
            Navigation.findNavController(this,R.id.homeFragmentHost).navigate(R.id.action_global_bookmarkFragment);
        });
        NavController navController = Navigation.findNavController(this, R.id.homeFragmentHost);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public void updateSavedNumberI(int n) {
        bookMarkBtn.setText(String.valueOf(n));
    }

    @Override
    public void updateToolBarStatus(int n) {
        bookMarkBtn.setVisibility(n);
        drawer.setVisibility(n);
    }

    @Override
    public void addToCalendar(String id, ArrayList<String> data) {
        presenter.addToCalendar(id,data);
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getSavedWeek(String id, WeekSetter setter) {
        presenter.getSavedItemByID(id,setter);
    }
}
