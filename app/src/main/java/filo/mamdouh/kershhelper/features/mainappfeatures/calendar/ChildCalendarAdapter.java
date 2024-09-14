package filo.mamdouh.kershhelper.features.mainappfeatures.calendar;

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
import filo.mamdouh.kershhelper.contracts.CalendarContract;
import filo.mamdouh.kershhelper.models.DaysOfWeek;
import filo.mamdouh.kershhelper.models.MealsItem;

public class ChildCalendarAdapter extends RecyclerView.Adapter<ChildCalendarAdapter.ChildCalendarHolder> {
    private List<MealsItem> mealsItemList;
    private final DaysOfWeek DAY ;
    private final Context context;
    private final CalendarContract.Listener listener;

    public ChildCalendarAdapter(List<MealsItem> mealsItemList, DaysOfWeek day, Context context, CalendarContract.Listener listener) {
        this.context = context;
        this.listener = listener;
        DAY = day;
        if(mealsItemList == null) this.mealsItemList = new ArrayList<>();
        else this.mealsItemList = mealsItemList;
    }

    @NonNull
    @Override
    public ChildCalendarHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChildCalendarHolder(LayoutInflater.from(context).inflate(R.layout.calendarcarditem, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChildCalendarHolder holder, int position) {
        holder.calendarItemName.setText(mealsItemList.get(position).getStrMeal());
        holder.calendarItemIngredients.setText(String.format(context.getString(R.string.ingredients),mealsItemList.get(position).getIngredients().size()));
        Glide.with(context).load(mealsItemList.get(position).getStrMealThumb()).placeholder(R.drawable.ic_launcher_background).into(holder.mealImage);
        holder.calendarRemoveBtn.setOnClickListener(l->listener.removeItemListener(DAY,mealsItemList.get(position)));
        holder.itemView.setOnClickListener(l->listener.onClick(mealsItemList.get(position).getIdMeal(),mealsItemList.get(position).isSaved()));
    }

    @Override
    public int getItemCount() {
        return mealsItemList.size();
    }

    public static class ChildCalendarHolder extends RecyclerView.ViewHolder{
        ImageView mealImage;
        TextView calendarItemName, calendarItemIngredients;
        ImageButton calendarRemoveBtn;
        public ChildCalendarHolder(@NonNull View itemView) {
            super(itemView);
            mealImage = itemView.findViewById(R.id.calendarItemBackground);
            calendarItemName = itemView.findViewById(R.id.calendarItemName);
            calendarItemIngredients = itemView.findViewById(R.id.calendarItemIngredients);
            calendarRemoveBtn = itemView.findViewById(R.id.calendarRemoveBtn);
        }
    }
}
