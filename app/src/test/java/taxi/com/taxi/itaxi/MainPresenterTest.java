package taxi.com.taxi.itaxi;


import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;
import taxi.com.taxi.common.TestDataFactory;
import taxi.com.taxi.itaxi.comman.Constants;
import taxi.com.taxi.itaxi.model.PoiListResponse;
import taxi.com.taxi.itaxi.model.PointOfInterest;
import taxi.com.taxi.itaxi.repository.NetworkService;
import taxi.com.taxi.itaxi.ui.main_screen.MainContract;
import taxi.com.taxi.itaxi.ui.main_screen.MainPresenter;
import taxi.com.taxi.itaxi.util.RxSchedulersOverrideRule;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Mock
    private
    MainContract.View  mMainView;

    @Mock
    private
    NetworkService mNetworkService;

    private MainPresenter mMainPresenter;



    @Rule
    public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();

    @Before
    public void setUp() {
        mMainPresenter = new MainPresenter(mNetworkService);
        mMainPresenter.attachView(mMainView);
    }





    @Test
    public void loadPOIs() {

        PoiListResponse serverResponseModel = TestDataFactory.makePoiListResponseModel();

        when(mNetworkService.getPOIs(Constants.P1.latitude, Constants.P1.longitude, Constants.P2.latitude, Constants.P2.longitude))
                .thenReturn(Observable.just(serverResponseModel));

        mMainPresenter.loadPOIs();
        verify(mMainView).showProgress();
        verify(mMainView).setPOIs(serverResponseModel.getPoiList());
        verify(mMainView, never()).showError();

    }


    @Test
    public void setPOIsMapMarkers() {

        PoiListResponse serverResponseModel = TestDataFactory.makePoiListResponseModel();

        when(mNetworkService.getPOIs(Constants.P1.latitude, Constants.P1.longitude, Constants.P2.latitude, Constants.P2.longitude))
                .thenReturn(Observable.just(serverResponseModel));

        mMainPresenter.loadPOIs();
        mMainPresenter.setPOIsMapMarkers();
        verify(mMainView).setPOIsMapMarkers(serverResponseModel.getPoiList());
        verify(mMainView, never()).showError();


    }


    @Test
    public void onItemClicked() {

        PoiListResponse serverResponseModel = TestDataFactory.makePoiListResponseModel();

        when(mNetworkService.getPOIs(Constants.P1.latitude, Constants.P1.longitude, Constants.P2.latitude, Constants.P2.longitude))
                .thenReturn(Observable.just(serverResponseModel));

        mMainPresenter.loadPOIs();
        PointOfInterest pointOfInterest = serverResponseModel.getPoiList().get(0);
        mMainPresenter.onItemClicked(pointOfInterest);
        verify(mMainView).setPoiFocusOnMap(pointOfInterest);
        verify(mMainView, never()).showError();

    }




    @After
    public void tearDown() {
        mMainPresenter.detachView();
    }





}
