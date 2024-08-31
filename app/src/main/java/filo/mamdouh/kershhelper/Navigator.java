package filo.mamdouh.kershhelper;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class Navigator {
    public static void toActivity(Context context, AppCompatActivity activity){
        Intent intent = new Intent(context,activity.getClass());
        context.startActivity(intent);
        ((AppCompatActivity)context).finish();
    }
}
