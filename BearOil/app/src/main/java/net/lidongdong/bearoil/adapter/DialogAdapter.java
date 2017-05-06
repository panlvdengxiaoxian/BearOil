package net.lidongdong.bearoil.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.lidongdong.bearoil.R;
import net.lidongdong.bearoil.entity.CarEntity;

import java.util.List;

/**
 * @author lidongdong(一个帅的惊天动地的男人)
 * @version 1.0
 * @date 17/4/18
 * @explain
 * @function
 */

public class DialogAdapter extends BaseAdapter {
    private List<CarEntity> cars;
    private LayoutInflater mInflater;
    private DialogDeleteListener mListener;

    public DialogAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    public void setCars(List<CarEntity> cars) {
        this.cars = cars;
        notifyDataSetChanged();
    }

    public void setListener(DialogDeleteListener listener) {
        mListener = listener;
    }

    @Override
    public int getCount() {
        return cars == null ? 0 : cars.size();
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
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_dialog, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv.setText(cars.get(position).getName());
        holder.iv.setOnClickListener(v ->
                mListener.setDeleteListener((int) getItemId(position)));
        return convertView;
    }

    private class ViewHolder {
        private TextView tv;
        private ImageView iv;

        ViewHolder(View view) {
            tv = (TextView) view.findViewById(R.id.item_dialog_tv);
            iv = (ImageView) view.findViewById(R.id.delete_iv);


        }
    }
}
