package filo.mamdouh.kershhelper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
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
import filo.mamdouh.kershhelper.datastorage.room.savedmeals.SavedMealsDataSourceImpl;
import filo.mamdouh.kershhelper.features.communicators.Planner;
import filo.mamdouh.kershhelper.features.dialogs.guestdialog.GuestDialog;
import filo.mamdouh.kershhelper.models.Client;
import filo.mamdouh.kershhelper.models.DaysOfWeek;
import filo.mamdouh.kershhelper.models.MealsItem;
import filo.mamdouh.kershhelper.models.Repository;
import filo.mamdouh.kershhelper.presenters.HomeActivityPresenter;

public class HomeActivity extends AppCompatActivity implements HomeContract.Activity, Planner {
    private ImageButton drawer;
    private Button bookMarkBtn;
    private DrawerLayout mDrawerLayout;
    private OnBackPressedCallback callback;
    private HomeActivityPresenter presenter;
    BottomNavigationView navigationView;
    NavController drawerNavController;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        presenter = new HomeActivityPresenter(this, Repository.getInstance(FileHandler.getInstance(this),
                SavedMealsDataSourceImpl.getInstance(this)
                , RetrofitClient.getInstance(this), SharedPrefrenceHandler.getInstance(this)));
        presenter.getSavedItems();
        navigationView = findViewById(R.id.bottomNavigationView);
        mDrawerLayout =findViewById(R.id.HomeActivityLayout);
        drawer = findViewById(R.id.profileButton);
        bookMarkBtn = findViewById(R.id.toolbarSavedBtn);
        drawer.setOnClickListener(l->
            mDrawerLayout.openDrawer(GravityCompat.START)
        );
        checkLoginStatus();
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
        drawerNavController = Navigation.findNavController(this , R.id.fragmentContainerView3);
        getOnBackPressedDispatcher().addCallback(this,callback);
        NavController navController = Navigation.findNavController(this, R.id.homeFragmentHost);
        NavigationUI.setupWithNavController(navigationView, navController);
        mDrawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                drawerNavController.navigate(R.id.action_global_homeFragment);
            }
        });
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
    public void addToCalendar(MealsItem item, ArrayList<DaysOfWeek> data) {
        presenter.addToCalendar(item,data);

    }

    @Override
    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getSavedWeek(String id, WeekSetter setter) {
        presenter.getSavedItemByID(id,setter);
    }

    public void checkLoginStatus(){
        Log.d("Filo", "checkLoginStatus: " + Client.getInstance(null,null));
        if (Client.getInstance(null,null).getUserName().isEmpty()){
            bookMarkBtn.setOnClickListener(l-> new GuestDialog(this).showDialog());
            MenuItem bookmarkBtn = navigationView.getMenu().findItem(R.id.bookmarkFragment);
            MenuItem calendarBtn = navigationView.getMenu().findItem(R.id.calendarFragment);
            MenuItem cartBtn = navigationView.getMenu().findItem(R.id.cartFragment);
            bookmarkBtn.setOnMenuItemClickListener(item -> {
                new GuestDialog(this).showDialog();
                return true;
            });
            calendarBtn.setOnMenuItemClickListener(item -> {
                new GuestDialog(this).showDialog();
                return true;
            });
            cartBtn.setOnMenuItemClickListener(item -> {
                new GuestDialog(this).showDialog();
                return true;
            });
        }
        else
            bookMarkBtn.setOnClickListener(l-> Navigation.findNavController(this,R.id.homeFragmentHost).navigate(R.id.action_global_bookmarkFragment));
    }
}
