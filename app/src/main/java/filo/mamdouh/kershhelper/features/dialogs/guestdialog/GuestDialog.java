package filo.mamdouh.kershhelper.features.dialogs.guestdialog;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import java.util.Objects;
import filo.mamdouh.kershhelper.MainActivity;
import filo.mamdouh.kershhelper.Navigator;
import filo.mamdouh.kershhelper.R;


public class GuestDialog {
    private final Activity activity;
    private AlertDialog alertDialog;

    public GuestDialog(Activity activity){
        this.activity = activity;
    }

    public void showDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.guest_dialog,null);
        builder.setView(view);
        alertDialog = builder.create();
        Objects.requireNonNull(alertDialog.getWindow()).setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        alertDialog.show();
        Button loginBtn = view.findViewById(R.id.guestDialogLoginBtn);
        Button cancelButton = view.findViewById(R.id.guestDialogCancelBtn);

        loginBtn.setOnClickListener(v -> Navigator.toActivity(activity, new MainActivity()));
        cancelButton.setOnClickListener(v -> alertDialog.dismiss());
    }
}
