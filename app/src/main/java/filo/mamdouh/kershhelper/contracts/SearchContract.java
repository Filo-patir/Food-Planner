package filo.mamdouh.kershhelper.contracts;

import java.util.List;

import filo.mamdouh.kershhelper.models.Categories;
import filo.mamdouh.kershhelper.models.IngredientsRoot;

public interface SearchContract {
    interface Listener{
        void onIngredientClick(String name);
        void onCategoryClick(String name);
    }
    interface View{
        void updateIngredients(List<IngredientsRoot.Ingredient> item);
        void updateCategories(List<Categories.Category> categories);
    }
}
