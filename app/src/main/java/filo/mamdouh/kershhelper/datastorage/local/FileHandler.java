package filo.mamdouh.kershhelper.datastorage.local;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Scheduler;
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

    public Observable<String> readFile(String name) {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                FileInputStream fis = context.openFileInput(name);
                BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
                try
                {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        try {
                            emitter.onNext(line); // Emit each integer line
                        } catch (NumberFormatException e) {
                            // Handle lines that cannot be parsed as integers
                            emitter.onError(new Exception("Error parsing line: " + line, e));
                        }
                    }
                    emitter.onComplete(); // Signal completion after reading all lines
                } catch (IOException e) {
                    emitter.onError(new RuntimeException(e)); // Emit error if file cannot be opened
                }
                    reader.close();
            }
        });
    }

    public Observable<Object> writeFile(String name, ArrayList<String> data) {
        return Observable.fromCallable(() -> {
            PrintStream fos = new PrintStream(context.openFileOutput(name,Context.MODE_APPEND),true);
            Log.d("Filo", "writeFile: "+data);
            for (String item : data)  fos.append(item+"\n");
            fos.close();
            return null;
        });
    }
    public Observable<Object> writeFile(String name, String data) {
        return Observable.fromCallable(() -> {
            PrintStream fos = new PrintStream(context.openFileOutput(name,Context.MODE_APPEND),true);
            Log.d("Filo", "writeFile: "+data);
            fos.append(data+"\n");
            fos.close();
            return null;
        });
    }
    public Observable<String> removeFile(String name) {
        return Observable.create(emitter -> {
            PrintStream fos = null;
            try {
                fos = new PrintStream(context.openFileOutput(name, Context.MODE_PRIVATE));
                Log.d("Filo", "removefile: ");
                fos.print("");
                emitter.onNext("Sucess");
            } catch (Exception e) {
                emitter.onError(e);
            }
            if(fos != null)
                fos.close();
        });
    }

}
