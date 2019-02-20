package taxi.com.taxi.itaxi.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PoiListResponse {
    @SerializedName("poiList")
   private ArrayList<PointOfInterest> poiList;

    public ArrayList<PointOfInterest> getPoiList() {
        return poiList;
    }

    public void setPoiList(ArrayList<PointOfInterest> poiList) {
        this.poiList = poiList;
    }
}
