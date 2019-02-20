package taxi.com.taxi.itaxi.ui.base_view;

public interface BasePresenter<T> {

    void attachView(T view);

    void detachView();
}