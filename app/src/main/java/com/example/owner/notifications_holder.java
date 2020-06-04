package com.example.owner;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class notifications_holder extends RecyclerView.ViewHolder {

    public TextView date,name,intime,outtime,msg;
    public notifications_holder(@NonNull View itemView) {
        super(itemView);
        this.date=itemView.findViewById(R.id.textView49);
        this.name=itemView.findViewById(R.id.textView51);
        this.intime=itemView.findViewById(R.id.textView53);
        this.outtime=itemView.findViewById(R.id.textView55);
        this.msg=itemView.findViewById(R.id.textView47);
    }
}
