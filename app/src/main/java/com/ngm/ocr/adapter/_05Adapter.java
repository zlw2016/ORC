package com.ngm.ocr.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ngm.ocr.R;

public class _05Adapter extends RecyclerView.Adapter<_05Adapter.myholder> {

    LayoutInflater inflater;

    public _05Adapter(Activity activity) {
        inflater = activity.getLayoutInflater();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == 0) {
            view = inflater.inflate(R.layout.adapter_05_head, parent, false);
        } else {
            view = inflater.inflate(R.layout.adapter_05, parent, false);
        }
        return new myholder(view);
    }

    @Override
    public void onBindViewHolder(myholder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class myholder extends RecyclerView.ViewHolder {


        public myholder(View itemView) {
            super(itemView);


        }
    }
};
