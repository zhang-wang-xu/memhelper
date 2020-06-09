package com.example.memhelper.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.memhelper.R;

public class GridViewAdapter extends BaseAdapter {
    private List<Map<String, Object>> myList;
    private Context mContext;
    private TextView name_tv;
    private View deleteView;
    private boolean isShowDelete;// 根据这个变量来判断是否显示删除图标，true是显示，false是不显示

    public GridViewAdapter(Context mContext,
                           List<Map<String, Object>> myList) {
        this.mContext = mContext;
        // this.names=names;
        this.myList = myList;
    }

    public void setIsShowDelete(boolean isShowDelete) {
        this.isShowDelete = isShowDelete;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {

        return myList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return myList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.cardset_item,
                null);
        name_tv = (TextView) convertView.findViewById(R.id.text);
        deleteView = convertView.findViewById(R.id.delete_markView);
        deleteView.setVisibility(isShowDelete ? View.VISIBLE : View.GONE);// 设置删除按钮是否显示
        name_tv.setText(myList.get(position).get("text").toString());
        return convertView;
    }
}
