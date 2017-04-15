package net.lidongdong.mydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class MainActivity extends AppCompatActivity {
    private EditText inputSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputSearch = (EditText) findViewById(R.id.inputSearch);

        String [] args=new String[]{"A","B","C",null};
        Observable.fromArray(args).onErrorResumeNext(new Function<Throwable, ObservableSource<? extends String>>() {
            @Override
            public ObservableSource<? extends String> apply(@NonNull Throwable throwable) throws Exception {
                return Observable.just("sb,你是谁?");
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                Log.d("xxx", s);
            }
        });
//                .subscribe(new Observer<String>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//                Log.d("MainActivity", s);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.d("MainActivity", "e:" + e.getMessage());
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });



//        Observable.create(new ObservableOnSubscribe<CharSequence>() {
//            @Override
//            public void subscribe(@NonNull final ObservableEmitter<CharSequence> e) throws Exception {
//                inputSearch.addTextChangedListener(new TextWatcher() {
//                    @Override
//                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//                    }
//
//                    @Override
//                    public void onTextChanged(CharSequence s, int start, int before, int count) {
//                        e.onNext(s);
//                    }
//
//                    @Override
//                    public void afterTextChanged(Editable s) {
//
//                    }
//                });
//            }
//        }).debounce(1000, java.util.concurrent.TimeUnit.MICROSECONDS)
//                .subscribe(new Consumer<CharSequence>() {
//            @Override
//            public void accept(@NonNull CharSequence charSequence) throws Exception {
//                Log.d("xxx", "charSequence:" + charSequence);
//            }
//        });


    }
}
