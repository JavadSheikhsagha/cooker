package jvd.ir.cooker.Model;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static  final String BASE_URL="http://192.168.1.104/digikala/";
    private static Retrofit retrofit=null;
    public static Retrofit getClient(){
        if (retrofit==null){
            retrofit=new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(BASE_URL)
                    .build();
        }
        return retrofit;
    }

}
