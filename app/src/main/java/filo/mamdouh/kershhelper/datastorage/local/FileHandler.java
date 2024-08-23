package filo.mamdouh.kershhelper.datastorage.local;

import android.content.Context;

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
