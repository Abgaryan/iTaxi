package taxi.com.taxi.itaxi.di;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import taxi.com.taxi.itaxi.comman.Constants;
import taxi.com.taxi.itaxi.repository.NetworkService;

@Module
class NetModule {


    @Provides
    @Singleton
    OkHttpClient getOkHttpClient(){
        return new OkHttpClient.Builder()
                .build();
    }

    @Provides
    @Singleton
    Gson getGson(){
        return new GsonBuilder()
                .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'").create();
    }


    @Provides
    @Singleton
    Retrofit getRetrofit(OkHttpClient okHttpClient, Gson gson){
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }


    @Provides
    @Singleton
    NetworkService getNetworkService(Retrofit retrofit){
        return retrofit.create(NetworkService.class);
    }



}