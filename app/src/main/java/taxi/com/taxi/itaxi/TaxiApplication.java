package taxi.com.taxi.itaxi;

import android.app.Application;
import android.content.Context;

import taxi.com.taxi.itaxi.di.ApplicationComponent;
import taxi.com.taxi.itaxi.di.ApplicationModule;
import taxi.com.taxi.itaxi.di.DaggerApplicationComponent;


public class TaxiApplication extends Application {
    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initAppComponent();

    }




    private void initAppComponent(){
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getAppComponent(){
        if (mApplicationComponent == null) {
            initAppComponent();
        }
        return mApplicationComponent;
    }



    public static  TaxiApplication get(Context context) {
        return ( TaxiApplication) context.getApplicationContext();
    }


    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }

}
