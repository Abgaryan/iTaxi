package taxi.com.taxi.itaxi.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;


public class PointOfInterest {
    private int id;
    @SerializedName("coordinate")
    private LatLng latLng;
    private String fleetType;
    private float heading;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public String getFleetType() {
        return fleetType;
    }

    public void setFleetType(String fleetType) {
        this.fleetType = fleetType;
    }

    public float getHeading() {
        return heading;
    }

    public void setHeading(float heading) {
        this.heading = heading;
    }
}
