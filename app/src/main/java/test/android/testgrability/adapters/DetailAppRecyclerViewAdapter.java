package test.android.testgrability.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import test.android.testgrability.R;
import test.android.testgrability.fragments.DetailFragment;
import test.android.testgrability.interfaces.OnClickActivityListener;
import test.android.testgrability.models.Entry;

public class DetailAppRecyclerViewAdapter extends RecyclerView.Adapter<DetailAppRecyclerViewAdapter.ViewHolder> {

    private final List<Entry> mValues;
    private OnClickActivityListener mListener;
    private Context mContext;
    private TypedArray mImgs;

    public DetailAppRecyclerViewAdapter(List<Entry> items, OnClickActivityListener listener) {
        mValues = items;
        mListener = listener;
    }

    public DetailAppRecyclerViewAdapter(List<Entry> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.apps_item_list, parent, false);
        mContext = parent.getContext();
        return new ViewHolder(view);
    }

    @SuppressWarnings("ResourceType")
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);

        holder.mAppName.setText(holder.mItem.getName().getAppName());
        holder.mAppDescription.setText(holder.mItem.getSummary().getDescriptionApp());
        holder.mAppCompany.setText(holder.mItem.getCompanyName().getLabel());
        holder.mAppAmount.setText(holder.mItem.getPrice().getAttributesApp().getAmount());
        holder.mAppCategory.setText(holder.mItem.getCategoryName().getAttributesApp().getLabel());
        holder.mAppRights.setText(holder.mItem.getRightsName().getLabel());

        final Uri uri = Uri.parse(holder.mItem.getAppImage().getIconApp());
        holder.mAppImage.setImageURI(uri);

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mAppName;
        public final TextView mAppDescription;
        public final TextView mAppCompany;
        public final TextView mAppAmount;
        public final TextView mAppCategory;
        public final TextView mAppRights;
        public final SimpleDraweeView mAppImage;

        public Entry mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mAppName = (TextView) view.findViewById(R.id.tvDetailAppName);
            mAppDescription = (TextView) view.findViewById(R.id.tvDetailDescription);
            mAppCompany = (TextView) view.findViewById(R.id.tvDetailAppMadeBy);
            mAppAmount = (TextView) view.findViewById(R.id.tvDetailAppCost);
            mAppCategory = (TextView) view.findViewById(R.id.tvCategory);
            mAppRights = (TextView) view.findViewById(R.id.tvRights);
            mAppImage = (SimpleDraweeView) view.findViewById(R.id.ivDetailIconApp);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mAppName.getText() + "'";
        }
    }
}
