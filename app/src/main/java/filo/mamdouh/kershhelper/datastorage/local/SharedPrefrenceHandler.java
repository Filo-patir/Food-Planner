package filo.mamdouh.kershhelper.datastorage.local;

import android.content.Context;
import android.content.SharedPreferences;

import io.reactivex.rxjava3.core.Observable;

public class SharedPrefrenceHandler {
    private static final String PREF_NAME = "KershHelper";
    private static final String TAG = "SharedPrefrenceHandler";
    private SharedPreferences sharedPreferences;
    private static SharedPrefrenceHandler instance = null;
    private SharedPrefrenceHandler(Context context){
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }
    public static SharedPrefrenceHandler getInstance(Context context){
        if(instance == null) instance = new SharedPrefrenceHandler(context);
        return instance;
    }
    public void save(String key, String value){
        sharedPreferences.edit().putString(key, value).apply();
    }
    public Observable<String> get(String key){
        return Observable.just(sharedPreferences.getString(key, ""));
    }
    public void clear(){
        sharedPreferences.edit().clear().apply();
    }
}
