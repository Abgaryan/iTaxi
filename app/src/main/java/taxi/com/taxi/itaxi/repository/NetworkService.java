package taxi.com.taxi.itaxi.repository;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import taxi.com.taxi.itaxi.model.PoiListResponse;

public interface NetworkService {


    @GET("/")
    Observable<PoiListResponse> getPOIs(@Query("p1Lat") double latitude1, @Query("p1Lon") double Longitude1, @Query("p2Lat") double latitude2, @Query("p2Lon") double longitude2);


}
