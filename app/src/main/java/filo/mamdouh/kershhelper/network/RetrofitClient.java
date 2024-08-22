package filo.mamdouh.kershhelper.network;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import filo.mamdouh.kershhelper.contracts.NetworkContract;
import filo.mamdouh.kershhelper.models.HomeFragmentRowData;
import filo.mamdouh.kershhelper.models.Meals;
import filo.mamdouh.kershhelper.models.MealsItem;
import io.reactivex.rxjava3.core.Observable;
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
    public Observable<List<HomeFragmentRowData.ItemData>> getRandomMeal() {
        apiService.getRandomMeal().doOnNext(meals -> Log.d("TAG", "getRandomMeal: "+ meals.getMeals().get(0).getStrMeal()));
        return apiService.getRandomMeal().map(meals -> {
            ArrayList<HomeFragmentRowData.ItemData> list = new ArrayList<>();
            for (int i = 0; i<meals.getMeals().size();i++){
                MealsItem item = meals.getMeals().get(i);
                list.add(new HomeFragmentRowData.ItemData(item.getIdMeal(),item.getStrMeal(),item.getIngredients().size(), item.getStrArea(),item.getStrMealThumb()));
            }
            return list;
        });
    }

    @Override
    public Observable<Meals> getMealByCategory(String category) {
        return null;
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
}
