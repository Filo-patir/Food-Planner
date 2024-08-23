package filo.mamdouh.kershhelper.features.smallfoodcard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import filo.mamdouh.kershhelper.R;
import filo.mamdouh.kershhelper.features.communicators.OnItemClickListener;
import filo.mamdouh.kershhelper.features.mainappfeatures.home.Updater;
import filo.mamdouh.kershhelper.models.HomeFragmentRowData;
import filo.mamdouh.kershhelper.models.MealsItem;
import filo.mamdouh.kershhelper.models.Repostiry;


public class SmallCardAdapter extends RecyclerView.Adapter<SmallCardHolder> implements Updater {
    private List<MealsItem> items;
    private Context context;
    private OnItemClickListener listener;

    public SmallCardAdapter(List<MealsItem> items, Context context, OnItemClickListener listener) {
        this.items = items;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SmallCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SmallCardHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.small_card_item,
                parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull SmallCardHolder holder, int position)
    {
        MealsItem item = items.get(position);
        holder.mealName.setText(item.getStrMeal());
        holder.ingredientsNumber.setText(item.getIngredients().size() + " Ingredients");
        String imgUrl = Repostiry.getCOUNTERIES().get(item.getStrArea());
        Glide.with(context).load(imgUrl).placeholder(R.drawable.unknown_flag_icon).into(holder.flag);
        Glide.with(context).load(item.getStrMealThumb()).placeholder(R.drawable.ic_launcher_background).into(holder.mealImage);
        holder.saveBtn.setOnClickListener(l->listener.saveItemListener(item,this));
        if(item.isSaved()) holder.saveBtn.setImageResource(R.drawable.baseline_bookmark_24);
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
