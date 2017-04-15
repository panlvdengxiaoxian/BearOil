package com.example;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyClass {

    public static String PATH_DIR = "/Users/dllo/Desktop/img";
    public static String URL_GANK = "http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/1";

    public static void main(String[] args) {
     final  OkHttpClient client = new OkHttpClient.Builder().build();

        Observable.just(URL_GANK).map(s -> {
            Request request = new Request.Builder().url(s).build();
            Response response = client.newCall(request).execute();
            return response.body().string();

        }).map(s -> new Gson().fromJson(s, GankEntity.class))
                .map(GankEntity::getResults).flatMap(new Function<List<GankEntity.ResultsBean>, ObservableSource<GankEntity.ResultsBean>>() {
            @Override
            public ObservableSource<GankEntity.ResultsBean> apply(@NonNull List<GankEntity.ResultsBean> resultsBeen) throws Exception {
                return Observable.fromIterable(resultsBeen);
            }
        }).map(GankEntity.ResultsBean::getUrl).map(s -> {
            Request request=new Request.Builder().url(s).build();
            Response response = client.newCall(request).execute();
            InputStream is=response.body().byteStream();
            String imgeName=s.substring(s.lastIndexOf("/"),s.length());
            File imageFile=new File(PATH_DIR,imgeName);
            if (!imageFile.exists()){
                imageFile.createNewFile();
            }
            FileOutputStream fos=new FileOutputStream(imageFile);
            int length=-1;
            byte [] bytes=new byte[256];
            while ((length=is.read(bytes))!=-1){
                fos.write(bytes,0,length);
            }
            fos.close();
            is.close();
            return imageFile.getAbsolutePath();

        }).subscribe(System.out::println);
    }
}

