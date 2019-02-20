package taxi.com.taxi.common.Injection.component;

import javax.inject.Singleton;

import dagger.Component;
import taxi.com.taxi.common.Injection.module.ApplicationTestModule;
import taxi.com.taxi.common.Injection.module.PresenterModule;
import taxi.com.taxi.itaxi.di.ApplicationComponent;
/**
 *
 */

@Singleton
@Component(modules = {ApplicationTestModule.class, PresenterModule.class})
public interface TestComponent extends ApplicationComponent {

}