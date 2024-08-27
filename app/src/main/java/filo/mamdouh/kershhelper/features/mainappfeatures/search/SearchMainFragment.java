package filo.mamdouh.kershhelper.features.mainappfeatures.search;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import filo.mamdouh.kershhelper.R;
import filo.mamdouh.kershhelper.contracts.SearchContract;
import filo.mamdouh.kershhelper.databinding.FragmentSearchMainBinding;
import filo.mamdouh.kershhelper.datastorage.local.FileHandler;
import filo.mamdouh.kershhelper.datastorage.network.RetrofitClient;
import filo.mamdouh.kershhelper.datastorage.room.calendar.CalendarDataSourceImpl;
import filo.mamdouh.kershhelper.datastorage.room.savedmeals.SavedMealsDataSourceImpl;
import filo.mamdouh.kershhelper.features.mainappfeatures.search.presenter.SearchMainPressenter;
import filo.mamdouh.kershhelper.models.Categories;
import filo.mamdouh.kershhelper.models.IngredientsRoot;
import filo.mamdouh.kershhelper.models.Repostiry;

public class SearchMainFragment extends Fragment implements SearchContract.View,SearchContract.Listener {
    Button searchBtn;
    TextView seeMore;
    RecyclerView categoryRV,ingredientRV;
    FragmentSearchMainBinding binding;
    SearchMainPressenter searchMainPressenter;
    CategoryAdapter categoryAdapter;
    IngredientAdapter ingredientAdapter;
    View view;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchMainPressenter = new SearchMainPressenter(this,Repostiry.getInstance(FileHandler.getInstance(getContext()), SavedMealsDataSourceImpl.getInstance(getContext()), CalendarDataSourceImpl.getInstance(getContext()), RetrofitClient.getInstance(getContext())));
        Log.d("TAG", "onCreate: Search");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchMainBinding.inflate(inflater,container,false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchBtn = binding.realSearchbtn;
        seeMore = binding.seeMoreIngredients;
        categoryRV = binding.categoryRecyclerView;
        ingredientRV = binding.ingredientsRecylcerView;
        categoryAdapter = new CategoryAdapter(this,getContext());
        ingredientAdapter = new IngredientAdapter(this,getContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        categoryRV.setAdapter(categoryAdapter);
        categoryRV.setLayoutManager(gridLayoutManager);
        ingredientRV.setAdapter(ingredientAdapter);
        searchBtn.setOnClickListener(l-> Navigation.findNavController(view).navigate(R.id.action_searchMainFragment_to_searchItemsFragment)
        );
        searchMainPressenter.getIngredients();
        searchMainPressenter.getCategories();
        this.view = view;
    }

    @Override
    public void onIngredientClick(String name) {
        Bundle bundle = new Bundle();
        bundle.putString("ingredient",name);
        Navigation.findNavController(view).navigate(R.id.action_searchMainFragment_to_searchItemsFragment,bundle);
    }

    @Override
    public void onCategoryClick(String name) {
        Bundle bundle = new Bundle();
        bundle.putString("category",name);
        Navigation.findNavController(view).navigate(R.id.action_searchMainFragment_to_searchItemsFragment,bundle);
    }


    @Override
    public void updateIngredients(List<IngredientsRoot.Ingredient> item) {
        ingredientAdapter.setIngredientList(item);
    }

    @Override
    public void updateCategories(List<Categories.Category> categories) {
        categoryAdapter.setCategoryList(categories);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        searchMainPressenter.onDestroy();
    }
}