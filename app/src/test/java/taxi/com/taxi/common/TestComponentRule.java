package taxi.com.taxi.common;

import android.content.Context;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import taxi.com.taxi.common.Injection.component.DaggerTestComponent;
import taxi.com.taxi.common.Injection.component.TestComponent;
import taxi.com.taxi.common.Injection.module.ApplicationTestModule;
import taxi.com.taxi.itaxi.TaxiApplication;
import taxi.com.taxi.itaxi.repository.NetworkService;



/**
 *
 * Test rule that creates and sets a Dagger TestComponent into the application overriding the
 * existing application component.
 * Use this rule in your test case in order for the app to use mock dependencies.
 * It also exposes some of the dependencies so they can be easily accessed from the tests, e.g. to
 * stub mocks etc.
 */
public class TestComponentRule implements TestRule {

    private final TestComponent mTestComponent;
    private final Context mContext;

    public TestComponentRule(Context context) {
        mContext = context;
        TaxiApplication application = TaxiApplication .get(context);
        mTestComponent = DaggerTestComponent.builder()
                .applicationTestModule(new ApplicationTestModule(application))
                .build();
    }

    public Context getContext() {
        return mContext;
    }

    public NetworkService getMockNetworkService() {
        return mTestComponent.networkService();
    }

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                TaxiApplication application = TaxiApplication .get(mContext);
                application.setComponent(mTestComponent);
                base.evaluate();
                application.setComponent(null);
            }
        };
    }
}
