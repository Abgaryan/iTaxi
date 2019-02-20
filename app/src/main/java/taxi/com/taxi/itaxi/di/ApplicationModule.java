package taxi.com.taxi.itaxi.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import taxi.com.taxi.itaxi.TaxiApplication;

/**
 * This is where we will inject application-wide dependencies.
 */
@Module
public class ApplicationModule {

    private Context mApplication;

    public ApplicationModule(TaxiApplication application) {
        mApplication = application;
    }


    @Provides
    @Singleton
    public Context getContext() {
        return mApplication;
    }

}