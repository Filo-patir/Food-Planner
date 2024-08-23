package filo.mamdouh.kershhelper.network;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import filo.mamdouh.kershhelper.contracts.NetworkContract;
import filo.mamdouh.kershhelper.models.HomeFragmentRowData;
import filo.mamdouh.kershhelper.models.Meals;
import filo.mamdouh.kershhelper.models.MealsItem;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient implements NetworkContract{
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private final Retrofit retrofit;
    private APIService apiService;
    private static RetrofitClient instance = null;

    private RetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL).
                addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        apiService = retrofit.create(APIService.class);
    }
    public static RetrofitClient getInstance() {
        if(instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    @Override
    public Observable<Meals> getMealByID(String id) {
        return null;
    }

    @Override
    public Observable<Meals> getMealByName(String name) {
        return null;
    }

    @Override
    public Observable<Meals> getMealByLetter(String letter) {
        return null;
    }

    @Override
    public Single<ArrayList<MealsItem>> getDailyInspiration() {
        return getRandomMeal().repeat(5).scan(new ArrayList<MealsItem>(), (list,value)->{
            list.add(value);
            return list;
        }).last(new ArrayList<>(List.of(new MealsItem())));
    }


    private Observable<MealsItem> getRandomMeal() {
        return apiService.getRandomMeal().map(meals -> meals.getMeals().get(0));
    }

    @Override
    public Observable<List<MealsItem>> getMealByCategory(String category) {
        return apiService.getMealByCategory(category).map(Meals::getMeals);
    }

    @Override
    public Observable<Meals> getMealByIngredient(String ingredient) {
        return null;
    }

    @Override
    public Observable<Meals> getMealByArea(String area) {
        return null;
    }

    @Override
    public Observable<Meals> getAreas() {
        return null;
    }

    @Override
    public Observable<Meals> getIngredients() {
        return null;
    }

    @Override
    public Observable<Meals> getCategories() {
        return null;
    }

    @Override
    public Single<ArrayList<MealsItem>> getRanomMeals() {
        return getRandomMeal().repeat(5).scan(new ArrayList<MealsItem>(), (list,value)->{
            list.add(value);
            return list;
        }).last(new ArrayList<>(List.of(new MealsItem())));
    }

    @Override
    public Single<ArrayList<MealsItem>> getMore() {
        Log.d("Small list", "getMore: ");
        return getRandomMeal().repeat(20).scan(new ArrayList<MealsItem>(), (list,value)->{
            list.add(value);
            return list;
        }).distinct().last(new ArrayList<>(List.of(new MealsItem())));
    }
}
