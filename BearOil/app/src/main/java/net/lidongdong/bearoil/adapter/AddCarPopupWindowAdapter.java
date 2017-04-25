package net.lidongdong.bearoil.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.lidongdong.bearoil.R;
import net.lidongdong.bearoil.entity.BrandEntity;

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

public class AddCarPopupWindowAdapter extends BaseAdapter {
    private BrandEntity mBrandEntity;
    private LayoutInflater mInflater;

    public AddCarPopupWindowAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    public void setBrandEntity(BrandEntity brandEntity) {
        mBrandEntity = brandEntity;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mBrandEntity.getNames()==null?0: mBrandEntity.getNames().size();
    }

    @Override
    public Object getItem(int position) {
        return mBrandEntity.getNames().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView==null){
            convertView=mInflater.inflate(R.layout.item_add_car_brand,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.brandTv.setText(mBrandEntity.getNames().get(position));
        return convertView;
    }

    private class ViewHolder{
        TextView brandTv;
         ViewHolder(View v) {
            brandTv = (TextView) v.findViewById(R.id.add_car_brand);
        }
    }
}
