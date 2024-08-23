package filo.mamdouh.kershhelper.models;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import filo.mamdouh.kershhelper.contracts.NetworkContract;
import filo.mamdouh.kershhelper.datastorage.local.FileHandler;
import filo.mamdouh.kershhelper.datastorage.room.SavedMealsDataSource;
import filo.mamdouh.kershhelper.datastorage.room.SavedMealsDataSourceImpl;
import filo.mamdouh.kershhelper.network.APIService;
import filo.mamdouh.kershhelper.network.RetrofitClient;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import lombok.Getter;
import lombok.Setter;

public class Repostiry {
    @Getter
    @Setter
    private User user;
    private final NetworkContract api;
    private final FileHandler fileHandler;
    private final SavedMealsDataSource savedMealsDataSource;
    @Getter
    private Flowable<List<MealsItem>> savedMeals;
    @Getter
    private static final HashMap<String,String> COUNTERIES = new HashMap<>();
    private static Repostiry repostiry = null;
    static {
        COUNTERIES.put("American" , "https://www.worldometers.info/img/flags/us-flag.gif");
        COUNTERIES.put("British" , "https://www.worldometers.info/img/flags/uk-flag.gif");
        COUNTERIES.put("Canadian" , "https://www.worldometers.info/img/flags/ca-flag.gif");
        COUNTERIES.put("Chinese" , "https://www.worldometers.info/img/flags/ch-flag.gif");
        COUNTERIES.put("Croatian" , "https://www.worldometers.info/img/flags/hr-flag.gif");
        COUNTERIES.put("Dutch" , "https://www.worldometers.info/img/flags/gm-flag.gif");
        COUNTERIES.put("Egyptian" , "https://www.worldometers.info/img/flags/eg-flag.gif");
        COUNTERIES.put("Filipino" , "https://www.worldometers.info/img/flags/rp-flag.gif");
        COUNTERIES.put("French" , "https://www.worldometers.info/img/flags/fr-flag.gif");
        COUNTERIES.put("Greek" , "https://www.worldometers.info/img/flags/gr-flag.gif");
        COUNTERIES.put("Indian" ,"https://www.worldometers.info/img/flags/in-flag.gif");
        COUNTERIES.put("Irish" , "https://www.worldometers.info/img/flags/iz-flag.gif");
        COUNTERIES.put("Italian" , "https://www.worldometers.info/img/flags/it-flag.gif");
        COUNTERIES.put("Jamaican" , "https://www.worldometers.info/img/flags/jm-flag.gif");
        COUNTERIES.put("Japanese" , "https://www.worldometers.info/img/flags/ja-flag.gif");
        COUNTERIES.put("Kenyan" , "https://www.worldometers.info/img/flags/ke-flag.gif");
        COUNTERIES.put("Malaysian" , "https://www.worldometers.info/img/flags/my-flag.gif");
        COUNTERIES.put("Mexican" , "https://www.worldometers.info/img/flags/mx-flag.gif");
        COUNTERIES.put("Moroccan" , "https://www.worldometers.info/img/flags/mo-flag.gif");
        COUNTERIES.put("Polish" , "https://www.worldometers.info/img/flags/pl-flag.gif");
        COUNTERIES.put("Portuguese" , "https://www.worldometers.info/img/flags/po-flag.gif");
        COUNTERIES.put("Russian" , "https://www.worldometers.info/img/flags/rs-flag.gif");
        COUNTERIES.put("Spanish" ,"https://www.worldometers.info/img/flags/sp-flag.gif");
        COUNTERIES.put("Thai" , "https://www.worldometers.info/img/flags/th-flag.gif");
        COUNTERIES.put("Tunisian" , "https://www.worldometers.info/img/flags/ts-flag.gif");
        COUNTERIES.put("Turkish" , "https://www.worldometers.info/img/flags/tu-flag.gif");
        COUNTERIES.put("Ukrainian" , "https://www.worldometers.info/img/flags/up-flag.gif");
        COUNTERIES.put("Vietnamese" , "https://www.worldometers.info/img/flags/vm-flag.gif");
    }
    private Repostiry(FileHandler fileHandler, SavedMealsDataSource savedMealsDataSource){
        api = RetrofitClient.getInstance();
        this.fileHandler = fileHandler;
        this.savedMealsDataSource = savedMealsDataSource;
        savedMeals = savedMealsDataSource.getSavedMeals();
    }

    public static Repostiry getInstance(FileHandler fileHandler, SavedMealsDataSource savedMealsDataSource) {
        if(repostiry==null) repostiry = new Repostiry(fileHandler, savedMealsDataSource);
        return repostiry;
    }
    public Single<ArrayList<MealsItem>> getDailyInspiration(){

        return api.getDailyInspiration();
    }

    public Observable<String> getLocalDailyInspiration(){
        return fileHandler.readFile("Daily Inspiration");
    }
    public void saveLocalDailyInspiration(String data){
        fileHandler.writeFile("Daily Inspiration",data);
    }

    public Completable saveMeal(MealsItem meal){
        return savedMealsDataSource.insertMeal(meal);
    }


    public Observable<List<MealsItem>> getDesserts() {
        return api.getMealByCategory("Dessert");
    }

    public Single<ArrayList<MealsItem>> getRandomMeals() {
        return api.getRanomMeals();
    }

    public Single<ArrayList<MealsItem>> getMore() {
        return api.getMore();
    }

    public Observable<String> recentlyViewed() {
        return fileHandler.readFile("Recently Viewed");
    }

    public Observable<MealsItem> getMealByID(String ids) {
        return api.getMealByID(ids);
    }
}
