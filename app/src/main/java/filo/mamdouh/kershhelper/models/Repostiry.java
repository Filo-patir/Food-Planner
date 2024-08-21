package filo.mamdouh.kershhelper.models;

import filo.mamdouh.kershhelper.contracts.NetworkContract;
import lombok.Getter;
import lombok.Setter;

public class Repostiry implements NetworkContract.Callbacks {
    @Getter
    @Setter
    private User user;
    private static Repostiry repostiry = null;
    private Repostiry(){
    }

    public static Repostiry getInstance() {
        if(repostiry==null) repostiry = new Repostiry();
        return repostiry;
    }

    @Override
    public void onRandomMealSuccess(Meals meals) {
    }

    @Override
    public void onRandomMealFailure(String error) {

    }

    @Override
    public void onMealIDSuccess(Meals meals) {

    }

    @Override
    public void onMealIDFailure(String error) {

    }

    @Override
    public void onMealNameSuccess(Meals meals) {

    }

    @Override
    public void onMealNameFailure(String error) {

    }

    @Override
    public void onMealByLetterSuccess(Meals meals) {

    }

    @Override
    public void onMealByLetterFailure(String error) {

    }

    @Override
    public void onMealByCategorySuccess(Meals meals) {

    }

    @Override
    public void onMealByCategoryFailure(String error) {

    }

    @Override
    public void onMealByIngredientSuccess(Meals meals) {

    }

    @Override
    public void onMeaByIngredientFailure(String error) {

    }

    @Override
    public void onMealByAreaSuccess(Meals meals) {

    }

    @Override
    public void onMealByAreaFailure(String error) {

    }

    @Override
    public void onAreaSuccess(Meals meals) {

    }

    @Override
    public void onAreaFailure(String error) {

    }

    @Override
    public void onIngredientsSuccess(Meals meals) {

    }

    @Override
    public void onIngredientsFailure(String error) {

    }

    @Override
    public void onCategoriesSuccess(Categories meals) {

    }

    @Override
    public void onCategoriesFailure(String error) {

    }
}
