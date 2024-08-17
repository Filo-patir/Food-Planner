package filo.mamdouh.kershhelper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.Navigation;

import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {
    private static int savedNumber;
    private ImageButton drawer;
    private Button bookMarkBtn;
    private DrawerLayout mDrawerLayout;
    AppCompatActivity homeActivity;
    private OnBackPressedCallback callback;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeActivity = this;
        setContentView(R.layout.activity_home);
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
                //TODO
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
    }
}
