package taxi.com.taxi.itaxi.di;

import dagger.Component;
import taxi.com.taxi.itaxi.TaxiApplication;
import taxi.com.taxi.itaxi.ui.main_screen.MainActivity;

/**
 * A dagger component that will live during the lifecycle of an Activity but it won't
 * be destroy during configuration changes. Check {@link MainActivity} to see how this components
 * survives configuration changes.
 * Use the {@link ConfigPersistent} scope to annotate dependencies that need to survive
 * configuration changes (for example Presenters).
 */
@ConfigPersistent
@Component(dependencies = TaxiApplication.class)
public interface ConfigPersistentComponent {

    ActivityModule getActivityModule(ActivityModule activityModule);

}