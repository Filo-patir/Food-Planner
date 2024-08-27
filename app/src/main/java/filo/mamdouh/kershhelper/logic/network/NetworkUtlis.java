package filo.mamdouh.kershhelper.logic.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;


public class NetworkUtlis {
    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
        return capabilities != null;
    }
}