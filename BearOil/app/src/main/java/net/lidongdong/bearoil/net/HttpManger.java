package net.lidongdong.bearoil.net;

import net.lidongdong.bearoil.net.retrofit.BearOilApi;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
*
*
*  @author lidongdong(一个帅的惊天动地的男人)
*  @ date 17/4/24
*  @ explain
*  @ function
*  @version 1.0
*
*/

public class HttpManger {
    private static class SingletonHolder{
        private static final HttpManger INSTANCE = new HttpManger();
    }

    public static HttpManger getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private BearOilApi mBearOilApi;

    private Retrofit mRetrofit;

    public BearOilApi getBearOilApi() {
        return mBearOilApi;
    }

    private HttpManger(){

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BearOilApi.BEAR_URL)
                //加入 RxJava
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mBearOilApi = mRetrofit.create(BearOilApi.class);
    }
}
