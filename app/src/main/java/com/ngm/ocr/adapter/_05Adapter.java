package com.ngm.ocr.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ngm.ocr.R;
import com.ngm.ocr.activity._06Activity;
import com.ngm.ocr.entity.Firearms;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

public class _05Adapter extends RecyclerView.Adapter<_05Adapter.myholder> {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Firearms> data = new ArrayList<>();

    public _05Adapter(Activity activity) {
        this.activity = activity;
        inflater = activity.getLayoutInflater();
    }

    public void setData(List<Firearms> data) {
        this.data = data;
        notifyDataSetChanged();
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
        if(position == 0) return;
        final Firearms firearms = data.get(position-1);
        if (R.id.body == holder.root.getId()) {
            holder.v1.setText(firearms.getType());
            holder.v2.setText(firearms.getNum());
            holder.v3.setText(firearms.getTime());

            holder.root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    _body(firearms);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.size() == 0 ? 0 : data.size()+1;
    }

    private void _body(Firearms firearms) {
        Intent intent = new Intent(activity, _06Activity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("firearms",firearms);
        intent.putExtras(bundle);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
    }

    class myholder extends RecyclerView.ViewHolder {

        private View root;
        private TextView v1;
        private TextView v2;
        private TextView v3;

        public myholder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            root = itemView;
            if (R.id.body == root.getId()) {
                v1 = (TextView) root.findViewById(R.id.v1);
                v2 = (TextView) root.findViewById(R.id.v2);
                v3 = (TextView) root.findViewById(R.id.v3);
            }
        }
    }

};
