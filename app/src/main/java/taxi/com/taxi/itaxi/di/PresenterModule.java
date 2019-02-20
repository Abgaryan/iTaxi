package taxi.com.taxi.itaxi.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import taxi.com.taxi.itaxi.repository.NetworkService;
import taxi.com.taxi.itaxi.ui.main_screen.MainPresenter;


@Module
 class PresenterModule {

    @Provides
    @Singleton
    MainPresenter MainPresenter(NetworkService networkService) {
        return new MainPresenter(networkService);
    }


}