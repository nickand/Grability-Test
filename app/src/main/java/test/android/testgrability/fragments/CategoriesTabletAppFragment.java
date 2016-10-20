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
import test.android.testgrability.activities.MainActivity;
import test.android.testgrability.adapters.AppsCategoriesTabletListRecyclerViewAdapter;
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
public class CategoriesTabletAppFragment extends Fragment {

    public static final String CLASS_TAG = CategoriesTabletAppFragment.class.getSimpleName();
    public static final String PARCEL_KEY = "parcel_key";
    private static final String PACKAGE = "test.android.testgrability";
    private RecyclerView mReciclerView;
    private AppsCategoriesTabletListRecyclerViewAdapter mAdapter;
    private Context mContext;
    private List<Entry> mEntryList = new ArrayList<>();
    private List<AppImage> mImageList = new ArrayList<>();
    private OnClickActivityListener mListener;
    private LinearLayout linearContainer;
    private LinearLayout linearNoInternetMessage;

    private String categoryId;
    private String categorySelected;
    private CrystalPreloader circleProgress;

    public CategoriesTabletAppFragment() {}

    public static CategoriesTabletAppFragment newInstance() {
        CategoriesTabletAppFragment fragment = new CategoriesTabletAppFragment();
        return fragment;
    }

    public static CategoriesTabletAppFragment newInstance(Bundle args) {
        CategoriesTabletAppFragment fragment = new CategoriesTabletAppFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            categoryId = getArguments().getString("category_id");
            categorySelected = getArguments().getString("name_category");

            getFreeApplications(categoryId);
        } else {
            categoryId = null;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.activity_app_categories_item_list, container, false);

        mContext = mView.getContext();

        circleProgress = (CrystalPreloader) mView.findViewById(R.id.loader);

        linearNoInternetMessage = (LinearLayout)
                mView.findViewById(R.id.containerNoInternetMessage);

        mListener.setTitleToolbar(categorySelected);

        FloatingActionButton fab = (FloatingActionButton) mView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), CategoriesActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(i, MainActivity.RESULT_OK);
            }
        });

        mReciclerView = (RecyclerView) mView.findViewById(R.id.item_list);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mReciclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);


        // allows for optimizations if all item views are of the same size:
        mReciclerView.setHasFixedSize(true);
        /*mAdapter = new AppsListRecyclerViewAdapter(mEntryList, mListener);
        mReciclerView.setAdapter(mAdapter);*/

        if (mEntryList.isEmpty()) {

            if (categoryId != null) {
                if (Utils.isNetworkConnected()) {
                    Log.d(CLASS_TAG, "Estoy conectado en internet");
                    circleProgress.setVisibility(View.VISIBLE);
                    getFreeApplications(categoryId);
                } else {
                    Log.d(CLASS_TAG, "No estoy conectado en internet");
                    circleProgress.setVisibility(View.GONE);
                    linearNoInternetMessage.setVisibility(View.VISIBLE);
                }
            } else {
                if (Utils.isNetworkConnected()) {
                    Log.d(CLASS_TAG, "Estoy conectado en internet");
                    circleProgress.setVisibility(View.VISIBLE);
                    getFreeApplications("0");
                } else {
                    Log.d(CLASS_TAG, "No estoy conectado en internet");
                    circleProgress.setVisibility(View.GONE);
                    linearNoInternetMessage.setVisibility(View.VISIBLE);
                }
            }

        } else {
            mAdapter = new AppsCategoriesTabletListRecyclerViewAdapter(mEntryList, mListener);
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

    private void getFreeApplications(String id){
        ApiClient.ApiInterface apiService = ApiClient.getApiClient().create(ApiClient.ApiInterface.class);
        Call<AppsApiResponse> call = apiService.getFreeApplications(id);
        call.enqueue(new Callback<AppsApiResponse>() {
            @Override
            public void onResponse(Call<AppsApiResponse> call, Response<AppsApiResponse> response) {
                AppsApiResponse apiResponse = response.body();

                if (response.isSuccessful()) {
                    mEntryList = apiResponse.getFeed().getEntry();
                    circleProgress.setVisibility(View.GONE);
                    Log.d(CLASS_TAG, "TOP APPS SUCCESS " +mEntryList);

                    for (int i = 0; i < mEntryList.size(); i++) {
                        Log.d(CLASS_TAG, mEntryList.get(i).getName().getAppName()+" "+
                                "Image: "+mEntryList.get(i).getAppImage().getIconApp());
                    }

                    mAdapter = new AppsCategoriesTabletListRecyclerViewAdapter(mEntryList, mListener);
                    mReciclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<AppsApiResponse> call, Throwable t) {
                t.printStackTrace();
                linearNoInternetMessage.setVisibility(View.VISIBLE);
            }
        });

    }
}
