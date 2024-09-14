package filo.mamdouh.kershhelper.features.mainappfeatures.savedmeals;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import filo.mamdouh.kershhelper.R;
import filo.mamdouh.kershhelper.features.communicators.OnItemClickListener;
import filo.mamdouh.kershhelper.features.dialogs.guestdialog.GuestDialog;
import filo.mamdouh.kershhelper.features.mainappfeatures.home.Updater;
import filo.mamdouh.kershhelper.models.Client;
import filo.mamdouh.kershhelper.models.MealsItem;

public class SavedMealsAdapter extends RecyclerView.Adapter<SavedMealsAdapter.ItemHolder>   implements Updater {
    private List<MealsItem> items;
    private final Context context;
    private final Activity activity;
    private final OnItemClickListener listener;
    public SavedMealsAdapter(Activity context,OnItemClickListener listener) {
        this.items = new ArrayList<>();
        this.context = context;
        this.activity = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(context).inflate(R.layout.bookmark_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        MealsItem item = items.get(position);
        holder.name.setText(item.getStrMeal());
        holder.ingredients.setText(item.getIngredients().size() + R.string.ingredients);
        Glide.with(context).load(item.getStrMealThumb()).placeholder(R.drawable.ic_launcher_background).into(holder.background);
        holder.addToCalendarBtn.setOnClickListener(v -> {
            if (Client.getInstance(null,null).getUserName().isEmpty()) {
                new GuestDialog(activity).showDialog();
            }
            else
                listener.addToCalendarListener(item);
        });
        holder.removeBtn.setOnClickListener(v -> {
            if (Client.getInstance(null,null).getUserName().isEmpty()) {
                new GuestDialog(activity).showDialog();
            }
            else
                listener.saveItemListener(item, this);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void updateUI() {
        notifyDataSetChanged();
    }

    public void setItems(List<MealsItem> mealsItems) {
        if(mealsItems != null)
            items = mealsItems;
        notifyDataSetChanged();
    }

    public static class ItemHolder  extends  RecyclerView.ViewHolder{
        TextView name,ingredients;
        ImageButton removeBtn,addToCalendarBtn;
        ImageView background;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.saveItemName);
            ingredients = itemView.findViewById(R.id.savedItemIngredients);
            removeBtn = itemView.findViewById(R.id.savedRemoveBtn);
            background = itemView.findViewById(R.id.savedItemBackground);
            addToCalendarBtn = itemView.findViewById(R.id.savedItemAddToCalender);
        }
    }
}
