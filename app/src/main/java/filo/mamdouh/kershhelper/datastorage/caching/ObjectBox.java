package filo.mamdouh.kershhelper.datastorage.caching;

import android.content.Context;

import io.objectbox.BoxStore;
import lombok.Getter;

public class ObjectBox {
    @Getter
    private final BoxStore store;
    private static ObjectBox instance = null;
    private ObjectBox(Context context){
        store = MyObjectBox.builder()
                .androidContext(context)
                .build();
    }
    public static synchronized ObjectBox getInstance(Context context) {
        if (instance == null)  instance = new ObjectBox(context);
        return instance;
    }

}
