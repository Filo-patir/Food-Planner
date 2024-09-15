package filo.mamdouh.kershhelper.features.mainappfeatures.calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import filo.mamdouh.kershhelper.R;
import filo.mamdouh.kershhelper.contracts.CalendarContract;
import filo.mamdouh.kershhelper.models.DaysOfWeek;
import filo.mamdouh.kershhelper.models.MealsItem;

public class BaseCalendarAdapter extends RecyclerView.Adapter<BaseCalendarAdapter.BaseCalendarHolder> {
    Map<DaysOfWeek, List<MealsItem>> calendarRowArrayList;
    private final Context context;
    private final CalendarContract.Listener listener;
    ChildCalendarAdapter adapter;
    public BaseCalendarAdapter( Context context, CalendarContract.Listener listener) {
        this.calendarRowArrayList = new HashMap<>();
        this.context = context;
        this.listener = listener;
    }
    public void setCalendarRowArrayList(DaysOfWeek key , List<MealsItem> item){
        calendarRowArrayList.put(key,item);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public BaseCalendarHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BaseCalendarHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_row_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseCalendarHolder holder, int position) {
        switch (position){
            case 0:
                setDay(holder,DaysOfWeek.SUNDAY);
                break;
            case 1:
                setDay(holder,DaysOfWeek.MONDAY);
                break;
            case 2:
                setDay(holder,DaysOfWeek.TUESDAY);
                break;
            case 3:
                setDay(holder,DaysOfWeek.WEDNESDAY);
                break;
                case 4:
                setDay(holder,DaysOfWeek.THURSDAY);
                break;
                case 5:
                setDay(holder,DaysOfWeek.FRIDAY);
                break;
                case 6:
                setDay(holder,DaysOfWeek.SATURDAY);
                break;
                default:
                break;
        }
    }
    private void setDay(BaseCalendarHolder holder, DaysOfWeek days){
        holder.title.setText(days.toString());
        adapter = new ChildCalendarAdapter(calendarRowArrayList.get(days),days,context,listener);
        holder.childRecyclerView.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public static class BaseCalendarHolder extends RecyclerView.ViewHolder {
        TextView title;
        RecyclerView childRecyclerView;
        public BaseCalendarHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.calendarDay);
            childRecyclerView = itemView.findViewById(R.id.calendarRowRV);
        }
    }
}
