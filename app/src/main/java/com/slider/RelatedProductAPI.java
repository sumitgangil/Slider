package com.slider;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by sumit on 6/2/2017.
 */

public interface RelatedProductAPI {


    @FormUrlEncoded
    @POST("/wonderhomes.co/interior/itemlist.php")
    public void insertUser(


            @Field("submit") Object itemlist,

            Callback<Response> callback);
}
