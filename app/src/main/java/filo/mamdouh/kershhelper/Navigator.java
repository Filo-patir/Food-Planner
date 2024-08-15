package filo.mamdouh.kershhelper;

import android.content.Context;
import android.content.Intent;

public class Navigator {
    public static void toActivity(Context context, Class activity){
        Intent intent = new Intent(context,activity);
        context.startActivity(intent);
    }
}
