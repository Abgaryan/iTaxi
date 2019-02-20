package taxi.com.taxi.itaxi;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.junit.Before;
import org.junit.Test;

import io.reactivex.observers.TestObserver;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import taxi.com.taxi.common.TestDataFactory;
import taxi.com.taxi.itaxi.comman.Constants;
import taxi.com.taxi.itaxi.model.PoiListResponse;
import taxi.com.taxi.itaxi.repository.NetworkService;

public class NetworkServiceTest {
    private MockWebServer mMockWebServer;
    private TestObserver<PoiListResponse> mSubscriber;
    private PoiListResponse mPoiListResponse;


    @Before
    public void setUp() {
        mPoiListResponse = TestDataFactory.makePoiListResponseModel();
        mMockWebServer = new MockWebServer();
        mSubscriber = new TestObserver<PoiListResponse>();
    }




    @Test
    public void getPOIsWithSuccessful() {
        mMockWebServer.enqueue(new MockResponse().setBody(new Gson().toJson(mPoiListResponse)));
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(mMockWebServer.url(Constants.BASE_URL))
                .build();

        NetworkService mNetworkService = retrofit.create(NetworkService.class);
        //When
        mNetworkService.getPOIs(Constants.P1.latitude, Constants.P1.longitude, Constants.P2.latitude, Constants.P2.longitude).subscribeWith(mSubscriber);

        //Then
        mSubscriber.assertNoErrors();
        mSubscriber.assertComplete();
    }


}

