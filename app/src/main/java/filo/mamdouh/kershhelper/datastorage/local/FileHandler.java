package filo.mamdouh.kershhelper.datastorage.local;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FileHandler {
    private Context context;
    private static FileHandler instance= null;
    private FileHandler(Context context){
        this.context = context;
    }

    public static FileHandler getInstance(Context context) {
        if(instance == null) instance = new FileHandler(context);
        return instance;
    }

    public Observable<ArrayList<Integer>> readFile(String name) {
        return Observable.fromCallable(() -> {
            try {
                FileInputStream fis = context.openFileInput(name);
                BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
                ArrayList<Integer> result = new ArrayList<>();
                String line;
                while ((line=reader.readLine())!=null){
                    result.add(Integer.valueOf(line));
                }
                return result;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).subscribeOn(Schedulers.io());
    }

    public Single<Object> writeFile(String name, String data) {
        return Single.fromCallable(() -> {
            try {
                PrintStream fos = new PrintStream(context.openFileOutput(name,Context.MODE_PRIVATE));
                fos.println();
                String[] formatted = data.split("\\n");
                for (String s : formatted) {
                    fos.append(s+"\n");
                }
                return null;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).subscribeOn(Schedulers.io());
    }

}
