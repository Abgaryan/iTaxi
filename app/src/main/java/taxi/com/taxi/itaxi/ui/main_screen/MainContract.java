package taxi.com.taxi.itaxi.ui.main_screen;

import java.util.ArrayList;

import taxi.com.taxi.itaxi.model.PointOfInterest;
import taxi.com.taxi.itaxi.ui.base_view.BasePresenter;
import taxi.com.taxi.itaxi.ui.base_view.BaseView;


/**
 * This specifies the contract between the view and the presenter.
 */
public class MainContract {

    public interface View extends BaseView<Presenter> {

        void setPOIs(ArrayList<PointOfInterest> poiList);

        void setPOIsMapMarkers(ArrayList<PointOfInterest> poiList);

        void setPoiFocusOnMap(PointOfInterest poi);

        void showProgress();

        void hideProgress();

        void showError();

    }

    interface Presenter extends BasePresenter<View> {
        void loadPOIs();

        void setPOIsMapMarkers();

        void onItemClicked(PointOfInterest poi);
    }

}
