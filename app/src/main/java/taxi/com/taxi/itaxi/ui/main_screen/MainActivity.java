package taxi.com.taxi.itaxi.ui.main_screen;


import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import taxi.com.taxi.itaxi.R;
import taxi.com.taxi.itaxi.TaxiApplication;
import taxi.com.taxi.itaxi.comman.Constants;
import taxi.com.taxi.itaxi.comman.DialogHelper;
import taxi.com.taxi.itaxi.model.PointOfInterest;


public class MainActivity extends AppCompatActivity implements MainContract.View, OnMapReadyCallback {

    @BindView(R.id.poi_recycle_view)
    RecyclerView mPoiRecyclerView;

    @BindView(R.id.progress_bar_stub)
    ViewStub mProgressStub;


    private GoogleMap mMap;

    @Inject
    MainPresenter mMainPresenter;

    @Inject
    PoiAdapter mPoiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ((TaxiApplication) getApplication()).getAppComponent().inject(this);
        initPresenter();
        initAdapter();
        initMapFragment();
    }

    //initialises  the MainPresenter
    private  void initPresenter(){
        mMainPresenter.attachView(this);
        mMainPresenter.loadPOIs();
    }


    //initialises  the  PoiAdapter
    private void initAdapter() {
        mPoiRecyclerView.setAdapter(mPoiAdapter);
        mPoiRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }


    // Obtain the SupportMapFragment and get notified when the map is ready to be used.
    private void initMapFragment() {
        com.google.android.gms.maps.SupportMapFragment mapFragment = (com.google.android.gms.maps.SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }





    @Override
    public void showProgress() {
        if (!mProgressStub.isShown()) mProgressStub.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressStub.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        DialogHelper.showErrorPopup(this, getResources().getString(R.string.dialog_message_error));
    }


    //sets the poiList in the PoiAdapter
    @Override
    public void setPOIs(ArrayList<PointOfInterest> poiList) {
        mPoiAdapter.setmPoiList(poiList);
        mPoiAdapter.notifyDataSetChanged();
    }


    //sets map markers from the poiList on the map
    @Override
    public void setPOIsMapMarkers(ArrayList<PointOfInterest> poiList) {
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.icon_taxi_marker);
        for (PointOfInterest poi : poiList) {
            mMap.addMarker(new MarkerOptions().position(poi.getLatLng()).title(poi.getFleetType()).icon(icon).flat(true).rotation(poi.getHeading()));
        }


    }

    //moves the camera into given poi  on the map
    @Override
    public void setPoiFocusOnMap(PointOfInterest poi) {
        if (mMap != null) {
            mMap.moveCamera(CameraUpdateFactory.newLatLng(poi.getLatLng()));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(poi.getLatLng(), 17));
            mMap.animateCamera(CameraUpdateFactory.zoomIn());
        }
    }




    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Create a LatLngBounds that includes the city of Hamburg
        LatLngBounds Hamburg = new LatLngBounds(
                Constants.P1, Constants.P2);
        // Constrain the camera target to the  Hamburg bounds.
        mMap.setLatLngBoundsForCameraTarget(Hamburg);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Hamburg.getCenter(), 10));
        mMainPresenter.setPOIsMapMarkers();
    }

    @Override
    protected void onDestroy() {
        mMainPresenter.detachView();
        super.onDestroy();
    }
}
