package com.ngm.ocr.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ngm.ocr.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;

public class SimpleAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;

    /**
     * 步枪——95式步枪；03式步枪
     * 机枪——81班用机枪；95班用机枪
     * 狙击步枪——85狙击步枪；88狙击步枪
     * 防暴枪——08式防暴枪
     * 其他——信号枪；榴弹发射器
     */

    private ArrayList<String> data;

    public SimpleAdapter(Context context, ArrayList<String> data) {
        layoutInflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.simple_list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) view.findViewById(R.id.text_view);
            view.setTag(viewHolder);
            //对于listview，注意添加这一行，即可在item上使用高度
            AutoUtils.autoSize(view);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.textView.setText(data.get(position));
        return view;
    }

    static class ViewHolder {
        TextView textView;
    }
}