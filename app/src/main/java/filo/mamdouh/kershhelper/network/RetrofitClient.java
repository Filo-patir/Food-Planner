package filo.mamdouh.kershhelper.network;

import filo.mamdouh.kershhelper.contracts.NetworkContract;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient implements NetworkContract.Requests{
    private static final String BASE_URL = "https://www.themealdb.com";
    private final Retrofit retrofit;
    private APIService apiService;
    private static RetrofitClient instance = null;

    private RetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL).
                addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }
    public static RetrofitClient getInstance() {
        if(instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    @Override
    public void getMealByID(String id, NetworkContract.Callbacks callBack) {
    }

    @Override
    public void getMealByName(String name, NetworkContract.Callbacks callBack) {
        
    }

    @Override
    public void getMealByLetter(String letter, NetworkContract.Callbacks callBack) {
        
    }

    @Override
    public void getRandomMeal(NetworkContract.Callbacks callBack) {
        apiService = retrofit.create(APIService.class);
        apiService.getRandomMeal().subscribeOn(Schedulers.io()).subscribe(callBack::onRandomMealSuccess, onError -> callBack.onRandomMealFailure(onError.getMessage())).dispose();
    }

    @Override
    public void getMealByCategory(String category, NetworkContract.Callbacks callBack) {
        
    }

    @Override
    public void getMealByIngredient(String ingredient, NetworkContract.Callbacks callBack) {
        
    }

    @Override
    public void getMealByArea(String area, NetworkContract.Callbacks callBack) {
        
    }

    @Override
    public void getAreas(NetworkContract.Callbacks callBack) {
        
    }

    @Override
    public void getIngredients(NetworkContract.Callbacks callBack) {
        
    }

    @Override
    public void getCategories(NetworkContract.Callbacks callBack) {
        
    }
}
