package filo.mamdouh.kershhelper.features.mainappfeatures.cartfragment;

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
import filo.mamdouh.kershhelper.features.mainappfeatures.home.Updater;
import filo.mamdouh.kershhelper.models.MealsItem;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.CartItemViewHolder> {
    private List<MealsItem> carts;
    private Context context;
    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartItemViewHolder(LayoutInflater.from(context).inflate(R.layout.cart_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemViewHolder holder, int position) {
        Glide.with(context).load(carts.get(position).getStrMealThumb()).into(holder.itemImg);
        holder.itemName.setText(carts.get(position).getStrMeal());
        holder.deleteBtn.setOnClickListener(v -> {
            carts.remove(position);
            updateUI();
        });
        ArrayList<String> ingredients = new ArrayList<>();
        for (int i = 0; i < carts.get(position).getIngredients().size(); i++)
            ingredients.add(carts.get(position).getQuantity() + " of " + carts.get(position).getMeasures().get(i) + " " +carts.get(position).getIngredients().get(i));
        holder.recyclerView.setAdapter(new IngredientsAdapter(ingredients));
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }


    public void updateUI() {
        notifyDataSetChanged();
    }

    public static class CartItemViewHolder extends RecyclerView.ViewHolder{
        RecyclerView recyclerView;
        TextView itemName;
        ImageView itemImg , deleteBtn;

        public CartItemViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.CartItemRecyclerView);
            itemName = itemView.findViewById(R.id.CartItemName);
            itemImg = itemView.findViewById(R.id.cartItemImg);
            deleteBtn = itemView.findViewById(R.id.cartCheckBt);
        }
    }
}
