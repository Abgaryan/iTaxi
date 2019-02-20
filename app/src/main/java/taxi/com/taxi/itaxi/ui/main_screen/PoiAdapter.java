package taxi.com.taxi.itaxi.ui.main_screen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import taxi.com.taxi.itaxi.R;
import taxi.com.taxi.itaxi.model.PointOfInterest;

public class PoiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Provider<PoiAdapter> {


    private List<PointOfInterest> mPoiList;

    @Inject
    Context context;

    @Inject
    MainPresenter mMainPresenter;



    @Inject
    public PoiAdapter() {
        this.mPoiList = new ArrayList<>();
    }

    public void setmPoiList(List<PointOfInterest> mPoiList) {
        this.mPoiList = mPoiList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  inflateView(parent);
        return new POIViewHolder(view);

    }

    private View inflateView(ViewGroup parent) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_poi, parent, false);

    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        POIViewHolder  poiViewHolder  = (POIViewHolder ) holder;
        PointOfInterest poi = mPoiList.get(position);
        poiViewHolder.setmPoi(poi);
        poiViewHolder.setData();
        holder.itemView.setTag(poiViewHolder);
    }

    @Override
    public int getItemCount() {
        return mPoiList.size();
    }

    @Override
    public PoiAdapter get() {
        return this;
    }

    class POIViewHolder extends RecyclerView.ViewHolder{
        private PointOfInterest mPoi;

        @BindView(R.id.type_text_view)
        TextView mTypeTextView;

        @BindView(R.id.id_text_view)
        TextView mIdTextView;




        POIViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            setInItemViewClick(itemView);
        }

        void setmPoi(@NonNull PointOfInterest mPoi) {
            this.mPoi = mPoi;
        }

        void setData(){
            mTypeTextView.setText(context.getResources().getString(R.string.fleet_type,mPoi.getFleetType()));
            mIdTextView.setText(context.getResources().getString(R.string.fleet_id,mPoi.getId()));

        }

        private void setInItemViewClick(View itemView) {
            itemView.setOnClickListener((v) -> {
                mMainPresenter.onItemClicked(mPoi);
            });
        }


    }
}
