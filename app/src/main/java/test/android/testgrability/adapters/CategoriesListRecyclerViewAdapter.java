package test.android.testgrability.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import test.android.testgrability.R;
import test.android.testgrability.activities.CategoriesActivity;
import test.android.testgrability.fragments.AppsFragment;
import test.android.testgrability.fragments.CategoriesAppFragment;
import test.android.testgrability.interfaces.OnClickActivityListener;
import test.android.testgrability.models.Genre;

public class CategoriesListRecyclerViewAdapter extends
        RecyclerView.Adapter<CategoriesListRecyclerViewAdapter.ViewHolder> {

    public static final String CLASS_TAG = CategoriesListRecyclerViewAdapter.class.getSimpleName();
    private final List<Genre> mValues;
    private OnClickActivityListener mListener;
    private Context mContext;
    private TypedArray mImgs;

    public CategoriesListRecyclerViewAdapter(List<Genre> items, OnClickActivityListener listener) {
        mValues = items;
        mListener = listener;
    }

    public CategoriesListRecyclerViewAdapter(List<Genre> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.categories_item_list, parent, false);
        mContext = parent.getContext();
        return new ViewHolder(view);
    }

    @SuppressWarnings("ResourceType")
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);

        holder.mCategoryName.setText(holder.mItem.getTitle());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(CLASS_TAG, "Category: "+position+ " ID: "+holder.mItem.getId());
                Bundle bundle = new Bundle();
                bundle.putString("category_id", String.valueOf(holder.mItem.getId()));
                bundle.putString("name_category", String.valueOf(holder.mItem.getTitle()));
                mListener.navigateTo(CategoriesAppFragment.newInstance(bundle));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mCategoryName;
//        public final TextView mCauseDate;
        public final SimpleDraweeView mAppImage;
//        public final View mCategoryBackground;

        public Genre mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mCategoryName = (TextView) view.findViewById(R.id.tvCategoryName);
//            mCauseDate = (TextView) view.findViewById(R.id.tvCauseDate);
            mAppImage = (SimpleDraweeView) view.findViewById(R.id.app_image);
//            mCategoryBackground = view.findViewById(R.id.category_bg);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mCategoryName.getText() + "'";
        }
    }
}
