package filo.mamdouh.kershhelper.features.mainappfeatures.mealsfragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import filo.mamdouh.kershhelper.R;
import filo.mamdouh.kershhelper.contracts.HomeContract;
import filo.mamdouh.kershhelper.contracts.MealDetailsContract;
import filo.mamdouh.kershhelper.databinding.FragmentMealDetailsBinding;
import filo.mamdouh.kershhelper.datastorage.local.FileHandler;
import filo.mamdouh.kershhelper.datastorage.room.calendar.CalendarDataSourceImpl;
import filo.mamdouh.kershhelper.datastorage.room.savedmeals.SavedMealsDataSourceImpl;
import filo.mamdouh.kershhelper.features.mainappfeatures.addtocalendardialog.PlanDialog;
import filo.mamdouh.kershhelper.features.mainappfeatures.mealsfragment.presenter.MealDetailsPressenter;
import filo.mamdouh.kershhelper.models.MealsItem;
import filo.mamdouh.kershhelper.models.Repostiry;

public class MealDetailsFragment extends Fragment implements MealDetailsContract.View {
    FragmentMealDetailsBinding binding;
    TextView detailsIngredients,category,instruction,mealName;
    ImageButton saveButton,addToCalendarBtn,srcBtn,backBtn;
    ImageView mealImg,areaFlag;
    String mealID;
    MealDetailsPressenter pressenter;
    HomeContract.ToolBar toolBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pressenter = new MealDetailsPressenter(this, Repostiry.getInstance(FileHandler.getInstance(getContext()), SavedMealsDataSourceImpl.getInstance(getContext()), CalendarDataSourceImpl.getInstance(getContext())));
        toolBar = (HomeContract.ToolBar) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMealDetailsBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolBar.updateToolBarStatus(View.GONE);
        detailsIngredients = binding.detailsIngredients;
        category = binding.detailsCategory;
        instruction = binding.detailsInstruction;
        mealName = binding.detailsMealName;
        saveButton = binding.detailsSaveBtn;
        addToCalendarBtn = binding.detailsAddToCalendarBtn;
        srcBtn = binding.detailsSrcBtn;
        mealImg = binding.detalisMainImage;
        areaFlag = binding.detailsFlag;
        backBtn = binding.detailsBackBtn;
        Bundle bundle = getArguments();
        mealID = bundle.getString("mealID");
        if (bundle.getBoolean("isSaved")){
            pressenter.getSavedItem(mealID);
        }
        else pressenter.getMealByID(mealID);
        backBtn.setOnClickListener(v -> Navigation.findNavController(view).navigateUp());

    }

    @Override
    public void updateUI(MealsItem meal) {
        mealName.setText(meal.getStrMeal());
        category.setText(meal.getStrCategory());
        instruction.setText(meal.getStrInstructions());
        detailsIngredients.setText(meal.getIngredients().toString());
        Glide.with(getContext()).load(meal.getStrMealThumb()).placeholder(R.drawable.ic_launcher_background).into(mealImg);
        Glide.with(getContext()).load(Repostiry.getCOUNTERIES().get(meal.getStrArea())).placeholder(R.drawable.unknown_flag_icon).into(areaFlag);
        saveButton.setOnClickListener(l-> pressenter.onSaveButtonClick(meal));
        addToCalendarBtn.setOnClickListener(v -> {
            PlanDialog dialog = new PlanDialog(getActivity(),meal.getIdMeal(),meal.getStrMeal());
            dialog.showDialog();
        });
        srcBtn.setOnClickListener(v -> {
            Uri uri = Uri.parse(meal.getStrSource());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            // Start the activity to display the web page
            startActivity(intent);
        });
    }

    @Override
    public void updateSaveBtn(boolean b) {
        if (b) {
            saveButton.setImageResource(R.drawable.baseline_bookmark_24);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
        else {
            saveButton.setImageResource(R.drawable.save_icon);
            Toast.makeText(getContext(), "Removed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        toolBar.updateToolBarStatus(View.VISIBLE);
    }
}