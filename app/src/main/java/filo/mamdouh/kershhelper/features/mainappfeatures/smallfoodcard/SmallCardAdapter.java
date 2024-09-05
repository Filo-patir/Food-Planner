package filo.mamdouh.kershhelper.features.mainappfeatures.smallfoodcard;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import filo.mamdouh.kershhelper.R;
import filo.mamdouh.kershhelper.contracts.SearchItemContract;
import filo.mamdouh.kershhelper.features.communicators.OnItemClickListener;
import filo.mamdouh.kershhelper.features.dialogs.guestdialog.GuestDialog;
import filo.mamdouh.kershhelper.features.mainappfeatures.home.Updater;
import filo.mamdouh.kershhelper.models.Client;
import filo.mamdouh.kershhelper.models.MealsItem;
import filo.mamdouh.kershhelper.models.Repostiry;


public class SmallCardAdapter extends RecyclerView.Adapter<SmallCardHolder> implements Updater {
    private List<MealsItem> items;
    private Activity context;
    private OnItemClickListener listener;
    private SearchItemContract.Listener searchListener;

    public SmallCardAdapter(List<MealsItem> items, Activity context, OnItemClickListener listener) {
        this.items = items;
        this.context = context;
        this.listener = listener;
    }
    public SmallCardAdapter(Activity context, SearchItemContract.Listener listener) {
        items = new ArrayList<>();
        this.context = context;
        this.searchListener = listener;
    }

    @NonNull
    @Override
    public SmallCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SmallCardHolder smallCardHolder = new SmallCardHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.small_card_item,
                parent, false));
        Log.d("Small List holder", "onBindViewHolder: "+smallCardHolder.getAdapterPosition());
        return new SmallCardHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.small_card_item,
                parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull SmallCardHolder holder, int position)
    {
        Log.d("Small List", "onBindViewHolder: "+position);
        MealsItem item = items.get(position);
        holder.mealName.setText(item.getStrMeal());
        holder.ingredientsNumber.setText(String.format(context.getString(R.string.ingredients),item.getIngredients().size()));
        String imgUrl = Repostiry.getCOUNTERIES().get(item.getStrArea());
        Glide.with(context.getApplicationContext()).load(imgUrl).placeholder(R.drawable.unknown_flag_icon).into(holder.flag);
        Glide.with(context.getApplicationContext()).load(item.getStrMealThumb()).placeholder(R.drawable.ic_launcher_background).into(holder.mealImage);
        holder.saveBtn.setOnClickListener(l->{
            if (Client.getInstance(null,null).getUserName().isEmpty()) {
                new GuestDialog(context).showDialog();
            }
            else{
                if (listener != null)
                    listener.saveItemListener(item, this);
                else
                    searchListener.saveItemListener(item, this);
            }
        });
        if(item.isSaved()) holder.saveBtn.setImageResource(R.drawable.baseline_bookmark_24);
        else holder.saveBtn.setImageResource(R.drawable.save_icon);
        holder.itemView.setOnClickListener(l-> {
            if(listener != null)
                listener.onItemClick(item.getIdMeal(),item.isSaved());
            else
                searchListener.onItemClick(item.getIdMeal(),item.isSaved());
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void updateUI() {
        Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
        notifyDataSetChanged();
    }

    public void updateUI(MealsItem mealsItem) {
        items.add(mealsItem);
        notifyDataSetChanged();
    }
    public void removeList(){
        items = new ArrayList<>();
        notifyDataSetChanged();
    }
}
