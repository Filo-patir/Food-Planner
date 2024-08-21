package filo.mamdouh.kershhelper.contracts;


import filo.mamdouh.kershhelper.models.Categories;
import filo.mamdouh.kershhelper.models.Meals;

public interface NetworkContract {
    interface Requests{
        void getMealByID(String id, NetworkContract.Callbacks callBack);

        void getMealByName(String name, NetworkContract.Callbacks callBack);

        void getMealByLetter(String letter, NetworkContract.Callbacks callBack);

        void getRandomMeal(NetworkContract.Callbacks callBack);

        void getMealByCategory(String category, NetworkContract.Callbacks callBack);

        void getMealByIngredient(String ingredient, NetworkContract.Callbacks callBack);

        void getMealByArea(String area, NetworkContract.Callbacks callBack);

        void getAreas(NetworkContract.Callbacks callBack);

        void getIngredients(NetworkContract.Callbacks callBack);

        void getCategories(NetworkContract.Callbacks callBack);
    }
    interface Callbacks {
        void onRandomMealSuccess(Meals meals);

        void onRandomMealFailure(String error);

        void onMealIDSuccess(Meals meals);

        void onMealIDFailure(String error);

        void onMealNameSuccess(Meals meals);

        void onMealNameFailure(String error);

        void onMealByLetterSuccess(Meals meals);

        void onMealByLetterFailure(String error);

        void onMealByCategorySuccess(Meals meals);

        void onMealByCategoryFailure(String error);

        void onMealByIngredientSuccess(Meals meals);

        void onMeaByIngredientFailure(String error);

        void onMealByAreaSuccess(Meals meals);

        void onMealByAreaFailure(String error);

        void onAreaSuccess(Meals meals);

        void onAreaFailure(String error);

        void onIngredientsSuccess(Meals meals);

        void onIngredientsFailure(String error);

        void onCategoriesSuccess(Categories meals);

        void onCategoriesFailure(String error);
    }
}
