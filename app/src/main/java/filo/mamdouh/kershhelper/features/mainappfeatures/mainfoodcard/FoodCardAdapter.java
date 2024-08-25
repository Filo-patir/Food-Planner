package filo.mamdouh.kershhelper.features.mainappfeatures.mainfoodcard;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import filo.mamdouh.kershhelper.R;
import filo.mamdouh.kershhelper.features.communicators.OnItemClickListener;
import filo.mamdouh.kershhelper.features.mainappfeatures.home.Updater;
import filo.mamdouh.kershhelper.models.HomeFragmentRowData;
import filo.mamdouh.kershhelper.models.MealsItem;
import filo.mamdouh.kershhelper.models.Repostiry;


public class FoodCardAdapter extends RecyclerView.Adapter<FoodCardHolder> implements Updater {
    private List<MealsItem> items;
    private Context context;
    private OnItemClickListener listener;
    public FoodCardAdapter(List<MealsItem> items,Context context,OnItemClickListener listener) {
        if(items == null) this.items = new ArrayList<>();
        else this.items = items;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FoodCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FoodCardHolder(LayoutInflater.from(context).inflate(R.layout.food_card,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FoodCardHolder holder, int position) {
        MealsItem item = items.get(position);
        holder.mealname.setText(item.getStrMeal());
        holder.ingredients.setText(item.getIngredients().size() + " Ingredients");
        String imgUrl = Repostiry.getCOUNTERIES().get(item.getStrArea());
        Glide.with(context).load(imgUrl).placeholder(R.drawable.unknown_flag_icon).into(holder.areaFlag);
        Glide.with(context).load(item.getStrMealThumb()).placeholder(R.drawable.ic_launcher_background).into(holder.backgroundImg);
        holder.addToCalendarBtn.setOnClickListener(l-> listener.addToCalendarListener(item.getIdMeal(),item.getStrMeal()));
        holder.saveBtn.setOnClickListener(l->listener.saveItemListener(item,this));
        if(item.isSaved()) holder.saveBtn.setImageResource(R.drawable.baseline_bookmark_24);
        else holder.saveBtn.setImageResource(R.drawable.save_icon);
        holder.itemView.setOnClickListener(l-> listener.onItemClick(item.getIdMeal(),item.isSaved()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    @Override
    public void updateUI() {
        notifyDataSetChanged();
    }

}
