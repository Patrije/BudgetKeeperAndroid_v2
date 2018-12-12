package com.example.pati.retrofitappintro.view;

/**
 * Created by Pati on 12.12.2018.
 */


import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pati.retrofitappintro.R;
import com.example.pati.retrofitappintro.model.Transaction;
import com.example.pati.retrofitappintro.util.TimeHelper;


import java.util.Calendar;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private Context context;
    private List<Transaction> eventsDataset;

    public CustomAdapter(final Context context, final List<Transaction> eventsDataset) {
        this.eventsDataset = eventsDataset;
        this.context=context;
    }

    public void updateEvents(final List<Transaction> events) {
        eventsDataset = events;
       // notifyDataSetChanged();
    }

    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View transView = layoutInflater.inflate(R.layout.row, parent, false);
        ViewHolder viewHolder = new ViewHolder(transView);
        return viewHolder;
//        View view = LayoutInflater
//                .from(parent.getContext())
//                .inflate(R.layout.row, parent, false);
//
//        return new ViewHolder(view);
    }

    public void clear() {
        final int size = eventsDataset.size();
        eventsDataset.clear();
        notifyItemRangeRemoved(0, size);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Transaction event = eventsDataset.get(position);

        String text;

//        if (event.isAllDayEvent()) {
//            text = "All day meeting";
//        } else {
//            Calendar calendar = TimeHelper.getNow();
//            calendar.setTimeInMillis(event.getStartDate());
//            String startDateStr =
//                    calendar.get(Calendar.DAY_OF_MONTH) + "." + String.format("%02d", calendar.get(Calendar.MONTH) + 1);
//            String startHourStr =
//                    calendar.get(Calendar.HOUR_OF_DAY) + ":" + String.format("%02d", calendar.get(Calendar.MINUTE));
//
//            calendar.setTimeInMillis(event.getEndDate());
//            String endDateStr =
//                    calendar.get(Calendar.DAY_OF_MONTH) + "." + String.format("%02d", calendar.get(Calendar.MONTH) + 1);
//            String endHourStr =
//                    calendar.get(Calendar.HOUR_OF_DAY) + ":" + String.format("%02d", calendar.get(Calendar.MINUTE));
//
//            if (event.isLongerThanOneDay()) {
//                text = String.format("%s (%s) - %s (%s)", startDateStr, startHourStr, endDateStr, endHourStr);
//            } else {
//                text = startHourStr + " - " + endHourStr;
//            }
//        }
        Calendar calendar = TimeHelper.getNow();
        calendar.setTimeInMillis(event.getDateOfTransaction());
        String dateOfTransaction= calendar.get(Calendar.DAY_OF_MONTH)+"-"+String.format("%2d", calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.YEAR);
holder.textViewValue.setText(String.valueOf(event.getValue()));
holder.textViewCategory.setText(event.getCategory());
holder.textViewDate.setText(dateOfTransaction);
//        holder.textViewTime.setText(text);
//        holder.textViewValue.setText(event.getTitle());
//
//        long currentTimeMillis = TimeHelper.getNow().getTimeInMillis();
//
//        int rowColor;
//
//        if (event.getStartDate() <= currentTimeMillis && currentTimeMillis <= event.getEndDate()) {
//            rowColor = ContextCompat.getColor(context, R.color.color_event_ongoing);
//        } else if (event.getEndDate() <= currentTimeMillis) {
//            rowColor = ContextCompat.getColor(context, R.color.color_event_finished);
//        } else {
//            rowColor = ContextCompat.getColor(context, R.color.color_event_incoming);
//        }
//
//        holder.textViewDate.setTextColor(rowColor);
//        holder.textViewValue.setTextColor(rowColor);
    }

    @Override
    public long getItemId(final int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return eventsDataset != null ? eventsDataset.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewValue;
        public TextView textViewDate;
        public TextView textViewCategory;
        public ViewHolder(final View view) {
            super(view);

            textViewValue =(TextView) view.findViewById(R.id.valueField);
            textViewCategory=(TextView) view.findViewById(R.id.categoryField);
            textViewDate = (TextView) view.findViewById(R.id.dateField);
        }
    }
}
