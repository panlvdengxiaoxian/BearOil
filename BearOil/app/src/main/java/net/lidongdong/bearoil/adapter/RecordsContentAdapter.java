package net.lidongdong.bearoil.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.lidongdong.bearoil.R;
import net.lidongdong.bearoil.db.ObservableSQLite;
import net.lidongdong.bearoil.entity.RecordEntity;
import net.lidongdong.bearoil.utils.DataCalculationUntil;
import net.lidongdong.bearoil.utils.TimeUntil;

import java.text.DecimalFormat;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author lidongdong(一个帅的惊天动地的男人)
 * @version 1.0
 * @ date 17/4/22
 * @ explain
 * @ function
 */

public class RecordsContentAdapter extends BaseAdapter {

    private List<RecordEntity> mData;

    private LayoutInflater mInflater;

    public RecordsContentAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    public void setData(List<RecordEntity> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mData == null ? 0 : mData.get(position).get_id();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder mHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_records_content, parent, false);
            mHolder = new ViewHolder(convertView);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }

        if (position > 0) {
            ViewHolder finalMHolder = mHolder;
            ObservableSQLite.queryRecord((int) getItemId(position))
                    .zipWith(ObservableSQLite.queryRecord((int) getItemId(position - 1)),
                            (recordEntity, recordEntity2) -> {
                                String data[] = new String[4];
                                data[0] = String.valueOf(recordEntity2.getYuan());
                                data[1] = String.valueOf(recordEntity2.getPrice());
                                data[2] = String.valueOf(recordEntity.getOdometer());
                                data[3] = String.valueOf(recordEntity2.getOdometer());
                                return data;
                            })
                    .map(strings -> DataCalculationUntil.kmData(Float.valueOf(strings[0])
                            , Float.valueOf(strings[1]), strings[2], strings[3]))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(km -> finalMHolder.itemRecordsKm.setText(new DecimalFormat(".00").format(km))
                    );
        }

        mHolder.itemRecordsDate.setText(TimeUntil.unixTimeStamp(Long.parseLong(mData.get(position).getDate())));

        if (position == 0) {
            mHolder.itemRecordsKm.setText("?");
        }

        mHolder.itemRecordsOdometer.setText(mData.get(position).getOdometer());

        return convertView;
    }

    private class ViewHolder {

        private TextView itemRecordsDate;
        private TextView itemRecordsOdometer;
        private TextView itemRecordsKm;

        ViewHolder(View view) {

            itemRecordsDate = (TextView) view.findViewById(R.id.item_records_date);
            itemRecordsOdometer = (TextView) view.findViewById(R.id.item_records_odometer);
            itemRecordsKm = (TextView) view.findViewById(R.id.item_records_km);

        }
    }
}
