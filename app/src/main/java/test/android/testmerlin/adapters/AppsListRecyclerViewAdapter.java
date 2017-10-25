package test.android.testmerlin.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
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

import test.android.testmerlin.R;
import test.android.testmerlin.fragments.DetailFragment;
import test.android.testmerlin.interfaces.OnClickActivityListener;
import test.android.testmerlin.models.Thing;
import test.android.testmerlin.utils.Constants;


public class AppsListRecyclerViewAdapter extends RecyclerView.Adapter<AppsListRecyclerViewAdapter.ViewHolder> {

    private final List<Thing> mValues;
    private OnClickActivityListener mListener;
    private Context mContext;
    private int mViewType;

    public AppsListRecyclerViewAdapter(List<Thing> items, OnClickActivityListener listener) {
        mValues = items;
        mListener = listener;
    }

    public AppsListRecyclerViewAdapter(List<Thing> items, OnClickActivityListener listener, int viewType) {
        mValues = items;
        mListener = listener;
        mViewType = viewType;
    }

    public AppsListRecyclerViewAdapter(List<Thing> items) {
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

        holder.mAppName.setText(holder.mItem.getData().getTitle());
        holder.mAppCompany.setText(holder.mItem.getData().getAdvertiserCategory());
        holder.mAppCategory.setText(holder.mItem.getData().getPublicDescription());

        if (mViewType == Constants.TYPE_FROM_APP_VIEW) {
            ImagePipeline imagePipeline = Fresco.getImagePipeline();

            //pre fetching image tree
            Uri uri = Uri.parse(holder.mItem.getData().getIconImg());
            ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(uri).build();
            imagePipeline.prefetchToDiskCache(imageRequest, null);

            holder.mAppImage.setImageURI(uri);
        } else {
            holder.mAppImage.setVisibility(View.GONE);
            holder.mAppCategory.setVisibility(View.GONE);
            holder.mAppCompany.setVisibility(View.GONE);
            holder.mAppName.setTextColor(Color.parseColor("#009452"));
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (null != mListener) {
                    // do something else
                    switch (mViewType) {
                        case Constants.TYPE_FROM_APP_VIEW:
                            mListener.navigateTo(DetailFragment.newInstanceWithArguments(holder.mItem));
                            break;
                        case Constants.TYPE_FROM_BROWSER:
                            mListener.openWebView((Activity) mContext, holder.mItem.getData().getUrl());
                            break;

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
        public final TextView mAppCategory;
        public final SimpleDraweeView mAppImage;

        public Thing mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mAppName = (TextView) view.findViewById(R.id.tvAppName);
            mAppCompany = (TextView) view.findViewById(R.id.tvCompanyName);
            mAppCategory = (TextView) view.findViewById(R.id.tvTypeName);
            mAppImage = (SimpleDraweeView) view.findViewById(R.id.app_image);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mAppName.getText() + "'";
        }
    }
}
