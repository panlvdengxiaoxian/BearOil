package net.lidongdong.bearoil.ui.aty;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import net.lidongdong.bearoil.R;
import net.lidongdong.bearoil.adapter.RecordsContentAdapter;
import net.lidongdong.bearoil.db.ObservableSQLite;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ShowAllRecordsActivity extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_records);

        initView();

    }

    private void initView() {

        mListView = (ListView) findViewById(R.id.content_records_lv);
        RecordsContentAdapter adapter=new RecordsContentAdapter(this);

        ObservableSQLite.queryRecords()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(adapter::setData);

        mListView.setAdapter(adapter);

    }
}
