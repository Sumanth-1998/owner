package com.example.owner;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class credits_holder extends RecyclerView.ViewHolder {
    public TextView bid,type,date,duration,amount;
    public credits_holder(@NonNull View itemView) {
        super(itemView);
        bid=itemView.findViewById(R.id.textView12);
        type=itemView.findViewById(R.id.textView8);
        date=itemView.findViewById(R.id.textView13);
        duration=itemView.findViewById(R.id.textView14);
        amount=itemView.findViewById(R.id.textView15);
    }
}
