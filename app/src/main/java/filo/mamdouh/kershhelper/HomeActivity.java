package filo.mamdouh.kershhelper;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class HomeActivity extends AppCompatActivity {
    private static int savedNumber;
    private ImageButton drawer;
    private Button savedButton;
    private DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mDrawerLayout =findViewById(R.id.HomeActivityLayout);
        drawer = findViewById(R.id.profileButton);
        savedButton = findViewById(R.id.toolbarSavedBtn);
        drawer.setOnClickListener(l->
            mDrawerLayout.openDrawer(GravityCompat.START)
        );
    }
}
