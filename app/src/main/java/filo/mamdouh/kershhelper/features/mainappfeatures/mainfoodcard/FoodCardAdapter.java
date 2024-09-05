package filo.mamdouh.kershhelper.features.mainappfeatures.mainfoodcard;

import android.app.Activity;
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
import filo.mamdouh.kershhelper.datastorage.caching.Desserts;
import filo.mamdouh.kershhelper.features.communicators.OnItemClickListener;
import filo.mamdouh.kershhelper.features.dialogs.guestdialog.GuestDialog;
import filo.mamdouh.kershhelper.features.mainappfeatures.home.Updater;
import filo.mamdouh.kershhelper.models.Client;
import filo.mamdouh.kershhelper.models.HomeFragmentRowData;
import filo.mamdouh.kershhelper.models.MealsItem;
import filo.mamdouh.kershhelper.models.Repostiry;


public class FoodCardAdapter extends RecyclerView.Adapter<FoodCardHolder> implements Updater {
    private List<MealsItem> items;
    private List<Desserts> dessertsitems;
    private Activity context;
    private OnItemClickListener listener;
    public FoodCardAdapter(List<MealsItem> items, Activity context, OnItemClickListener listener) {
        if(items == null) this.items = new ArrayList<>();
        else this.items = items;
        this.context = context;
        this.listener = listener;
    }
    public FoodCardAdapter(ArrayList<Desserts> items, Activity context, OnItemClickListener listener) {
        if(items == null) this.items = new ArrayList<>();
        else this.dessertsitems = items;
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
        if (dessertsitems == null) {
            MealsItem item = items.get(position);
            holder.mealname.setText(item.getStrMeal());
            holder.ingredients.setText(item.getIngredients().size() + " Ingredients");
            String imgUrl = Repostiry.getCOUNTERIES().get(item.getStrArea());
            Glide.with(context).load(imgUrl).placeholder(R.drawable.unknown_flag_icon).into(holder.areaFlag);
            Glide.with(context).load(item.getStrMealThumb()).placeholder(R.drawable.ic_launcher_background).into(holder.backgroundImg);
            holder.addToCalendarBtn.setOnClickListener(l-> {
                if (Client.getInstance(null,null).getUserName().isEmpty()) {
                    new GuestDialog(context).showDialog();
                }
                else
                    listener.addToCalendarListener(item.getIdMeal(),item.getStrMeal());
            });
            holder.saveBtn.setOnClickListener(l->{
                if (Client.getInstance(null,null).getUserName().isEmpty()) {
                    new GuestDialog(context).showDialog();
                }
                else
                    listener.saveItemListener(item, this);
            });
            if(item.isSaved()) holder.saveBtn.setImageResource(R.drawable.baseline_bookmark_24);
            else holder.saveBtn.setImageResource(R.drawable.save_icon);
            holder.itemView.setOnClickListener(l-> listener.onItemClick(item.getIdMeal(),item.isSaved()));
        }
        else {
            setDessertItem(dessertsitems.get(position), holder);
        }
    }
    private void setDessertItem(Desserts item, FoodCardHolder holder){
        holder.mealname.setText(item.getName());
        holder.ingredients.setText(item.getIngredientsCount() + " Ingredients");
        String imgUrl = Repostiry.getCOUNTERIES().get(item.getAreaFlag());
        Glide.with(context).load(imgUrl).placeholder(R.drawable.unknown_flag_icon).into(holder.areaFlag);
        Glide.with(context).load(item.getThumbnail()).placeholder(R.drawable.ic_launcher_background).into(holder.backgroundImg);
        holder.addToCalendarBtn.setOnClickListener(l-> {
            if (Client.getInstance(null,null).getUserName().isEmpty()) {
                new GuestDialog(context).showDialog();
            }
            else
                listener.addToCalendarListener(item.getIdMeal(),item.getName());
        });
        holder.saveBtn.setOnClickListener(l->{
            if (Client.getInstance(null,null).getUserName().isEmpty()) {
                new GuestDialog(context).showDialog();
            }
            else
                listener.saveItemListener(item.getIdMeal(), this);
        });
        if(item.getIsSaved()) holder.saveBtn.setImageResource(R.drawable.baseline_bookmark_24);
        else holder.saveBtn.setImageResource(R.drawable.save_icon);
        holder.itemView.setOnClickListener(l-> listener.onItemClick(item.getIdMeal(),item.getIsSaved()));
    }

    @Override
    public int getItemCount() {
        if (dessertsitems != null)
            return dessertsitems.size();
        else
            return items.size();
    }
    @Override
    public void updateUI() {
        notifyDataSetChanged();
    }

}
