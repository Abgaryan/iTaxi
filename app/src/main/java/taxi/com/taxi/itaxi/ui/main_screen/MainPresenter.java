package taxi.com.taxi.itaxi.ui.main_screen;

import android.annotation.SuppressLint;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Provider;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import taxi.com.taxi.itaxi.comman.Constants;
import taxi.com.taxi.itaxi.di.ConfigPersistent;
import taxi.com.taxi.itaxi.model.PoiListResponse;
import taxi.com.taxi.itaxi.model.PointOfInterest;
import taxi.com.taxi.itaxi.repository.NetworkService;
import taxi.com.taxi.itaxi.rx.RxUtils;

import static dagger.internal.Preconditions.checkNotNull;

/**
 * Listens to user actions from the UI ({@link MainActivity}), retrieves the data and updates the UI
 * as required.
 */
@ConfigPersistent
public class MainPresenter implements MainContract.Presenter, Provider<MainPresenter> {


    private Disposable mDisposable;

    @NonNull
    private MainContract.View mMainView;

    private NetworkService mNetworkService;


    private ArrayList<PointOfInterest> mPoiList;

    //shows if the Poi MapMarkers are  loaded in the view
    private  Boolean mSetMapMarker = false;


    @Inject
    public MainPresenter(NetworkService networkService) {
        mNetworkService = networkService;
    }


    @SuppressLint("RestrictedApi")
    @Override
    public void attachView(@NonNull MainContract.View mainView) {
        mMainView = checkNotNull(mainView, "mainView cannot be null!");
    }

    /*
     loads the PointOfInterest list  bounds of  the city Hamburg
    * */
    @Override
    public void loadPOIs() {
        mMainView.showProgress();
        RxUtils.dispose(mDisposable);
        mNetworkService.getPOIs(Constants.P1.latitude, Constants.P1.longitude, Constants.P2.latitude, Constants.P2.longitude)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<PoiListResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(PoiListResponse response) {
                        mMainView.hideProgress();
                        if (response.getPoiList() != null && !response.getPoiList().isEmpty()) {
                            mPoiList = response.getPoiList();
                            mMainView.setPOIs(mPoiList);
                            if(mSetMapMarker){
                                setPOIsMapMarkers();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mMainView.hideProgress();
                        mMainView.showError();
                    }

                    @Override
                    public void onComplete() {
                        mMainView.hideProgress();
                    }
                });
    }


    //loads the poi markers on the map
    @Override
    public void setPOIsMapMarkers() {
        if (mPoiList != null && !mPoiList.isEmpty()) {
            mMainView.setPOIsMapMarkers(mPoiList);
            mSetMapMarker = true;
        }else {
            mSetMapMarker = true;
        }
    }

    // handles the PoiListAdapter item click
    @Override
    public void onItemClicked(PointOfInterest poi) {
        mMainView.setPoiFocusOnMap(poi);
    }


    @Override
    public MainPresenter get() {
        return this;
    }


    @Override
    public void detachView() {
        if (mDisposable != null) mDisposable.dispose();
        mPoiList = null;
        mSetMapMarker = false;
    }

}
