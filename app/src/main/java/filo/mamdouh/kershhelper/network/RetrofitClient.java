package filo.mamdouh.kershhelper.network;

import filo.mamdouh.kershhelper.models.Categories;
import filo.mamdouh.kershhelper.models.Meals;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient implements NetworkDataSource{
    private static final String BASE_URL = "https://www.themealdb.com";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL).
                    addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
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
    public Observable<Meals> getRandomMeal() {
        return null;
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
    public Observable<Categories> getCategories() {
        return null;
    }
}
