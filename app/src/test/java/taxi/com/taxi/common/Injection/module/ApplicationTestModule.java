package taxi.com.taxi.common.Injection.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import taxi.com.taxi.itaxi.TaxiApplication;
import taxi.com.taxi.itaxi.repository.NetworkService;

import static org.mockito.Mockito.mock;

/**
 * * Provides application-level dependencies for an app running on a
 * testing environment This allows injecting mocks if necessary.
 */
@Module
public class ApplicationTestModule {

    private Context mApplication;

    public ApplicationTestModule(TaxiApplication application) {
        mApplication = application;
    }


    @Provides
    @Singleton
    public Context getContext() {
        return mApplication;
    }

    /************* MOCk *************/
    @Provides
    @Singleton
    NetworkService provideNetworkService() {
        return mock(NetworkService.class);
    }


}