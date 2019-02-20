package taxi.com.taxi.itaxi.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import taxi.com.taxi.itaxi.repository.NetworkService;
import taxi.com.taxi.itaxi.ui.main_screen.MainActivity;
import taxi.com.taxi.itaxi.ui.main_screen.MainPresenter;


@Singleton
@Component(
        modules = {
                ApplicationModule.class,
                NetModule.class,
                PresenterModule.class
        }
)
public interface ApplicationComponent {
    Context context();
    NetworkService networkService();

    void inject(MainActivity mainActivity);

    void inject(MainPresenter mainPresenter);

}
