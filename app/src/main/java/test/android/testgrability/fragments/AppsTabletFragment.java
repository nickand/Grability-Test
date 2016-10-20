package test.android.testgrability.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.crystal.crystalpreloaders.widgets.CrystalPreloader;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import test.android.testgrability.R;
import test.android.testgrability.activities.CategoriesActivity;
import test.android.testgrability.adapters.AppsListRecyclerViewAdapter;
import test.android.testgrability.interfaces.OnClickActivityListener;
import test.android.testgrability.models.AppImage;
import test.android.testgrability.models.AppsApiResponse;
import test.android.testgrability.models.Entry;
import test.android.testgrability.services.ApiClient;
import test.android.testgrability.utils.Utils;

/**
 * Created by Nicolas on 13/10/2016.
 */
public class AppsTabletFragment extends Fragment {

    public static final String CLASS_TAG = AppsTabletFragment.class.getSimpleName();
    public static final String PARCEL_KEY = "parcel_key";
    private static final String PACKAGE = "test.android.testgrability";
    private RecyclerView mReciclerView;
    private AppsListRecyclerViewAdapter mAdapter;
    private Context mContext;
    private List<Entry> mEntryList = new ArrayList<>();
    private List<AppImage> mImageList = new ArrayList<>();
    private OnClickActivityListener mListener;
    private LinearLayout linearContainer;
    private LinearLayout linearNoInternetMessage;
    private CrystalPreloader circleProgress;

    public AppsTabletFragment() {
    }

    public static AppsTabletFragment newInstance() {
        AppsTabletFragment fragment = new AppsTabletFragment();
        return fragment;
    }

    public static AppsTabletFragment newInstance(Bundle args) {
        AppsTabletFragment fragment = new AppsTabletFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.activity_app_item_list, container, false);

        mListener.setTitleToolbar("Test Grability");

        mContext = mView.getContext();

        FloatingActionButton fab = (FloatingActionButton) mView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), CategoriesActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

        circleProgress = (CrystalPreloader) mView.findViewById(R.id.loader);

        linearNoInternetMessage = (LinearLayout)
                mView.findViewById(R.id.containerNoInternetMessage);


        mReciclerView = (RecyclerView) mView.findViewById(R.id.item_list);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mReciclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);


        // allows for optimizations if all item views are of the same size:
        mReciclerView.setHasFixedSize(true);
        /*mAdapter = new AppsListRecyclerViewAdapter(mEntryList, mListener);
        mReciclerView.setAdapter(mAdapter);*/

        if (mEntryList.isEmpty()) {
            if (Utils.isNetworkConnected()) {
                Log.d(CLASS_TAG, "Estoy conectado en internet");
                circleProgress.setVisibility(View.VISIBLE);
                getTopApps();
            } else {
                Log.d(CLASS_TAG, "No estoy conectado en internet");
                circleProgress.setVisibility(View.GONE);
                linearNoInternetMessage.setVisibility(View.VISIBLE);
            }
        } else {
            mAdapter = new AppsListRecyclerViewAdapter(mEntryList, mListener);
            mReciclerView.setAdapter(mAdapter);
        }

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

    private void getTopApps() {
        ApiClient.ApiInterface apiService = ApiClient.getApiClient().create(ApiClient.ApiInterface.class);
        Call<AppsApiResponse> call = apiService.getTopApps();
        call.enqueue(new Callback<AppsApiResponse>() {
            @Override
            public void onResponse(Call<AppsApiResponse> call, Response<AppsApiResponse> response) {
                AppsApiResponse apiResponse = response.body();

                mEntryList = apiResponse.getFeed().getEntry();

                if (response.isSuccessful()) {
                    Log.d(CLASS_TAG, "TOP APPS SUCCESS " + mEntryList);
                    circleProgress.setVisibility(View.GONE);

                    for (int i = 0; i < mEntryList.size(); i++) {
                        Log.d(CLASS_TAG, mEntryList.get(i).getName().getAppName() + " " +
                                "Image: " + mEntryList.get(i).getAppImage().getIconApp());
                    }

                    mAdapter = new AppsListRecyclerViewAdapter(mEntryList, mListener);
                    mReciclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<AppsApiResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}
