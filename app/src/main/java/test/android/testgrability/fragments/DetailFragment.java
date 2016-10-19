package test.android.testgrability.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import test.android.testgrability.R;
import test.android.testgrability.interfaces.OnClickActivityListener;
import test.android.testgrability.models.Entry;

/**
 * Created by Nicolas on 13/10/2016.
 */
public class DetailFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String CLASS_TAG = DetailFragment.class.getSimpleName();
    @BindView(R.id.ivDetailIconApp)
    SimpleDraweeView ivDetailIconApp;
    @BindView(R.id.tvDetailAppName)
    TextView tvDetailAppName;
    @BindView(R.id.tvDetailAppMadeBy)
    TextView tvDetailAppMadeBy;
    @BindView(R.id.tvDetailAppCost)
    TextView tvDetailAppCost;
    @BindView(R.id.tvDetailDescription)
    TextView tvDetailDescription;
    @BindView(R.id.tvShowMore)
    TextView tvShowMore;
    @BindView(R.id.tvCategory)
    TextView tvCategory;
    @BindView(R.id.tvAppType)
    TextView tvAppType;
    @BindView(R.id.tvRights)
    TextView tvRights;
    @BindView(R.id.btnGoItunesStore)
    Button btnGoItunesStore;
    @BindView(R.id.scrollViewContainer)
    ScrollView scrollViewContainer;

    private Entry mEntry;
    private boolean isShowing = false;

    private Unbinder unbinder;
    private OnClickActivityListener mListener;

    public DetailFragment() {
    }

    public static DetailFragment newInstance() {
        DetailFragment fragment = new DetailFragment();
        return fragment;
    }

    public static DetailFragment newInstance(Entry e) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, e);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Log.d(CLASS_TAG, " getting args");

            mEntry = getArguments().getParcelable(ARG_PARAM1);
            Log.d(CLASS_TAG, mEntry.getName().getAppName());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_detail_app, container, false);

        unbinder = ButterKnife.bind(this, mView);

        mListener.setTitleToolbar(mEntry.getName().getAppName());

        tvDetailAppName.setText(mEntry.getName().getAppName());
        tvDetailDescription.setText(mEntry.getSummary().getDescriptionApp());
        tvCategory.setText(mEntry.getCategoryName().getAttributesApp().getLabel());

        if (mEntry.getPrice().getAttributesApp().getAmount().equals("0.00000"))
            tvDetailAppCost.setText("FREE");

        tvDetailAppMadeBy.setText("By " + mEntry.getCompanyName().getLabel());
        tvAppType.setText(mEntry.getContentType().getAttributesApp().getLabel());
        tvRights.setText(mEntry.getRightsName().getLabel());

        Uri uri = Uri.parse(mEntry.getAppImage().getIconApp());
        ivDetailIconApp.setImageURI(uri);


        return mView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnClickActivityListener) {
            mListener = (OnClickActivityListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnClickActivityListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.tvShowMore)
    public void onClick() {
        if (!isShowing) {
            isShowing = true;
            tvDetailDescription.setMaxLines(Integer.MAX_VALUE);
            tvShowMore.setText(R.string.show_less);
        } else {
            isShowing = false;
            tvDetailDescription.setMaxLines(4);
            tvShowMore.setText(R.string.read_more);
        }
    }

    @OnClick(R.id.btnGoItunesStore)
    public void btnGoItunes() {
        String url = mEntry.getAppLink().getAttributesApp().getHref();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}
