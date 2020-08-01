package com.pkeugine.whewigo.common;

import com.pkeugine.whewigo.data.Detail;
import com.pkeugine.whewigo.data.Review;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitExService {
    // http 통신
    String Tmap_URL = "https://apis.openapi.sk.com";
    String Tour_URL = "http://api.visitkorea.or.kr";
    String DB_URL = "http://192.168.1.239:8000";

    @GET("/openapi/service/rest/KorService/searchFestival")
    Call<JsonObject> getTourData(@Query(value = "serviceKey",encoded = true) String serviceKey, @Query("MobileOS") String mobileOS, @Query("MobileApp") String mobileApp,
                                 @Query("numOfRows") int numOfRows, @Query("arrange") char arrange, @Query("areaCode") int areaCode,
                                 @Query("eventStartDate") String eventStartDate, @Query("eventEndDate") String eventEndDate,@Query("_type") String type);

    @GET("/openapi/service/rest/KorService/detailCommon")
    Call<JsonObject> getTourDetailData(@Query(value = "serviceKey",encoded = true) String serviceKey, @Query("MobileOS") String mobileOS, @Query("MobileApp") String mobileApp,
                                 @Query("contentId") String contentId, @Query("defaultYN") char defaultYN, @Query("overviewYN") char overviewYN,
                                       @Query("mapinfoYN") char mapinfoYN, @Query("addrinfoYN") char addrinfoYN, @Query("_type") String type);


    @GET("/tmap/pois/{poiId}")
    Call<Detail> getDetailData(@Path("poiId") String poiId, @Query("version") int version, @Query("appKey") String appKey);

    @GET("/tmap/pois/search/around")
    Call<JsonObject> getPOIData(@Query("version") int version, @Query("page") int page, @Query("count") int count, @Query("categories") String categories,
                                @Query("centerLon") double centerLon, @Query("centerLat") double centerLat, @Query("radius") int radius, @Query("appKey") String appKey);

    @POST("/mavenweb/{jsp}")
    Call<List<Review>> useDB(@Path("jsp") String jsp, @Query("placeId") int placeId);

}
