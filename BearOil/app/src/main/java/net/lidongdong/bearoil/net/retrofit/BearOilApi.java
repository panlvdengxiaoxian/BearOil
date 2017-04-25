package net.lidongdong.bearoil.net.retrofit;

import net.lidongdong.bearoil.entity.BrandEntity;
import net.lidongdong.bearoil.entity.CarDetailEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

public interface BearOilApi {

    String BEAR_URL = "http://www.xiaoxiongyouhao.com/";

    @GET("models/pinpai.json")
    Observable<BrandEntity> getBrands();

    @GET("models/{index}/che_xi.json")
    Observable<BrandEntity> getCarSeries(@Path("index") int index);

    @GET("models/{indexBrand}/che_xing_{indexSeries}.json")
    Observable<BrandEntity> getCarType(@Path("indexBrand") int brand,
                                       @Path("indexSeries") int series);
    @GET("models/spec.php")
    Observable<CarDetailEntity> getCarDetail(@Query("cheXing") int type);




}
