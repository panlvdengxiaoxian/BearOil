package net.lidongdong.bearoil.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.lidongdong.bearoil.entity.CarEntity;

import java.util.List;

/**
*
*
*  @author lidongdong(一个帅的惊天动地的男人)
*  @date 17/4/18
*  @explain
*  @function
*  @version 1.0
*
*/

public class PopupWindowAdapter extends BaseAdapter {
    private List<CarEntity> cars;
    private LayoutInflater mInflater;

    public PopupWindowAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    public void setCars(List<CarEntity> cars) {
        this.cars = cars;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return cars==null?0:cars.size();
    }

    @Override
    public Object getItem(int position) {
        return cars.get(position);
    }

    @Override
    public long getItemId(int position) {
        return cars.get(position).get_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView=mInflater.inflate(android.R.layout.simple_list_item_1,parent,false);
        }
        TextView tv= (TextView) convertView;
        tv.setText(cars.get(position).getName());
        return convertView;
    }
}
