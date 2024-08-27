package filo.mamdouh.kershhelper.datastorage.network;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import filo.mamdouh.kershhelper.contracts.NetworkContract;
import filo.mamdouh.kershhelper.models.Categories;
import filo.mamdouh.kershhelper.models.IngredientsRoot;
import filo.mamdouh.kershhelper.models.Meals;
import filo.mamdouh.kershhelper.models.MealsItem;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient implements NetworkContract{
    final int CACHESIZE = 5*1024*1024;
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private final Retrofit retrofit;
    private APIService apiService;
    private static RetrofitClient instance = null;

    private RetrofitClient(Context context){
        Cache cache = new Cache(context.getCacheDir(),CACHESIZE);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().cache(cache).build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        apiService = retrofit.create(APIService.class);
    }
    public static RetrofitClient getInstance(Context context) {
        if(instance == null) {
            instance = new RetrofitClient(context);
        }
        return instance;
    }

    @Override
    public  Observable<MealsItem> getMealByID(String id) {
        return apiService.getMealByID(id).map(meals -> meals.getMeals().get(0));
    }

    @Override
    public Observable<MealsItem> searchMealByCategory(String category) {
        return Observable.create(emitter -> apiService.getMealByCategory(category).map(Meals::getMeals).subscribeOn(Schedulers.io()).subscribe(
                onNext -> {
                    for (MealsItem meal : onNext)
                        apiService.getMealByID(meal.getIdMeal()).map(Meals::getMeals).subscribeOn(Schedulers.io()).subscribe(
                                item -> emitter.onNext(item.get(0))
                        );
                }, emitter::onError
        ));
    }

    @Override
    public Observable<MealsItem> getMealByName(String name) {
        return Observable.create(emitter -> apiService.getMealByName(name).map(Meals::getMeals).subscribeOn(Schedulers.io()).subscribe(
                                onNext -> {
                                    for (MealsItem meal : onNext)
                                        emitter.onNext(meal);
                                }, emitter::onError
        ));
    }

    @Override
    public Observable<MealsItem> getMealByLetter(String letter) {
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
    public Observable<MealsItem> getMealByIngredient(String ingredient) {
        return Observable.create(new ObservableOnSubscribe<MealsItem>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<MealsItem> emitter) {
                apiService.getMealByIngredient(ingredient).subscribeOn(Schedulers.io()).map(Meals::getMeals).subscribe(
                        mealsItems -> {
                            for (MealsItem meal : mealsItems)
                                apiService.getMealByID(meal.getIdMeal()).map(Meals::getMeals).subscribeOn(Schedulers.io()).subscribe(
                                        onNext -> emitter.onNext(onNext.get(0)), emitter::onError
                                );
                        }
                );
            }
        });
    }

    @Override
    public Observable<MealsItem> getMealByArea(String area) {
        return Observable.create(new ObservableOnSubscribe<MealsItem>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<MealsItem> emitter) {
                apiService.getMealByArea(area).subscribeOn(Schedulers.io()).map(Meals::getMeals).subscribe(
                        mealsItems -> {
                            for (MealsItem meal : mealsItems)
                                apiService.getMealByID(meal.getIdMeal()).map(Meals::getMeals).subscribeOn(Schedulers.io()).subscribe(
                                        onNext -> emitter.onNext(onNext.get(0)), emitter::onError
                                );
                        }
                );
            }
        });
    }



    @Override
    public Observable<List<IngredientsRoot.Ingredient>> getIngredients() {
        return apiService.getIngredients().map(IngredientsRoot::getMeals);
    }

    @Override
    public Observable<List<Categories.Category>> getCategories() {
        return apiService.getCategories().map(Categories::getCategoryList);
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
