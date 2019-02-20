package taxi.com.taxi.common;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import taxi.com.taxi.itaxi.model.PoiListResponse;
import taxi.com.taxi.itaxi.model.PointOfInterest;

/**
 * Factory class that makes instances of data models with random
 * field values. The aim of this class is to help setting up test fixtures.
 */

public class TestDataFactory {

    public static PoiListResponse makePoiListResponseModel() {
        PoiListResponse poiListResponse = new PoiListResponse();
        poiListResponse.setPoiList(makePoiList(10));


        return poiListResponse;
    }

    public static ArrayList<PointOfInterest> makePoiList(int size) {
        ArrayList<PointOfInterest> movieList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            movieList.add(makePointOfInterest(i));
        }
        return movieList;
    }


    public static PointOfInterest makePointOfInterest(int id) {
        PointOfInterest pointOfInterest = new PointOfInterest();
        pointOfInterest.setId(id);
        pointOfInterest.setFleetType("TAXI");
        pointOfInterest.setLatLng(new LatLng(53.46036882190762, 9.909716434648558));
        pointOfInterest.setHeading((float) 245.2005654202569);
        return pointOfInterest;
    }


}
