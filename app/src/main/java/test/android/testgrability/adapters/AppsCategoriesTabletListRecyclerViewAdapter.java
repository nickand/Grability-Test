package test.android.testgrability.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.util.List;

import test.android.testgrability.R;
import test.android.testgrability.activities.CategoriesActivity;
import test.android.testgrability.activities.MainActivity;
import test.android.testgrability.fragments.DetailFragment;
import test.android.testgrability.fragments.DetailTabletFragment;
import test.android.testgrability.interfaces.OnClickActivityListener;
import test.android.testgrability.models.Entry;

public class AppsCategoriesTabletListRecyclerViewAdapter extends RecyclerView.Adapter<AppsCategoriesTabletListRecyclerViewAdapter.ViewHolder> {

    private final List<Entry> mValues;
    private OnClickActivityListener mListener;
    private Context mContext;
    private TypedArray mImgs;

    public AppsCategoriesTabletListRecyclerViewAdapter(List<Entry> items, OnClickActivityListener listener) {
        mValues = items;
        mListener = listener;
    }

    public AppsCategoriesTabletListRecyclerViewAdapter(List<Entry> items) {
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
        holder.mAppCompany.setText(holder.mItem.getCompanyName().getLabel());
        if (holder.mItem.getPrice().getAttributesApp().getAmount().equals("0.00000"))
            holder.mAppAmount.setText("FREE");
        holder.mAppCategory.setText(holder.mItem.getCategoryName().getAttributesApp().getLabel());

        ImagePipeline imagePipeline = Fresco.getImagePipeline();

        //pre fetching image tree
        Uri uri = Uri.parse(holder.mItem.getAppImage().getIconApp());
        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(uri).build();
        imagePipeline.prefetchToDiskCache(imageRequest, null);

        holder.mAppImage.setImageURI(uri);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (null != mListener) {
                    boolean tabletSize = mContext.getResources().getBoolean(R.bool.isTablet);
                    if (tabletSize) {
                        // do something
                        Bundle arguments = new Bundle();
                        arguments.putParcelable("parcel_data", holder.mItem);
                        DetailTabletFragment fragment = new DetailTabletFragment();
                        fragment.setArguments(arguments);
                        ((CategoriesActivity) mContext).getSupportFragmentManager().beginTransaction()
                                .add(R.id.item_detail_container, fragment)
                                .commit();
                    } else {
                        // do something else
                        mListener.navigateTo(DetailFragment.newInstance(holder.mItem));
                    }
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mAppName;
        public final TextView mAppCompany;
        public final TextView mAppAmount;
        public final TextView mAppCategory;
        public final SimpleDraweeView mAppImage;

        public Entry mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mAppName = (TextView) view.findViewById(R.id.tvAppName);
            mAppCompany = (TextView) view.findViewById(R.id.tvCompanyName);
            mAppAmount = (TextView) view.findViewById(R.id.tvPriceName);
            mAppCategory = (TextView) view.findViewById(R.id.tvTypeName);
            mAppImage = (SimpleDraweeView) view.findViewById(R.id.app_image);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mAppName.getText() + "'";
        }
    }
}
