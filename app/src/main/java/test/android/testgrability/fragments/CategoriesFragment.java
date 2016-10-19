package test.android.testgrability.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import test.android.testgrability.R;
import test.android.testgrability.activities.CategoriesActivity;
import test.android.testgrability.activities.MainActivity;
import test.android.testgrability.adapters.AppsListRecyclerViewAdapter;
import test.android.testgrability.adapters.CategoriesListRecyclerViewAdapter;
import test.android.testgrability.interfaces.OnClickActivityListener;
import test.android.testgrability.models.AppImage;
import test.android.testgrability.models.AppsApiResponse;
import test.android.testgrability.models.Entry;
import test.android.testgrability.models.Genre;
import test.android.testgrability.services.ApiClient;

/**
 * Created by Nicolas on 13/10/2016.
 */
public class CategoriesFragment extends Fragment {

    public static final String CLASS_TAG = CategoriesFragment.class.getSimpleName();
    public static final String PARCEL_KEY = "parcel_key";
    private static final String PACKAGE = "test.android.testgrability";
    private RecyclerView mReciclerView;
    private CategoriesListRecyclerViewAdapter mAdapter;
    private Context mContext;
    private List<Entry> mEntryList = new ArrayList<>();
    private List<AppImage> mImageList = new ArrayList<>();
    private OnClickActivityListener mListener;

    private String categoryId;
    private List<Genre> mGenreList = new ArrayList<>();

    public CategoriesFragment() {}

    public static CategoriesFragment newInstance() {
        CategoriesFragment fragment = new CategoriesFragment();
        return fragment;
    }

    public static CategoriesFragment newInstance(Bundle args) {
        CategoriesFragment fragment = new CategoriesFragment();
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
        View mView = inflater.inflate(R.layout.fragment_category, container, false);

        ((AppCompatActivity) getActivity())
                .getSupportActionBar().setTitle("Categories");

        mContext = mView.getContext();

        mReciclerView = (RecyclerView) mView.findViewById(R.id.list);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mReciclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);


        // allows for optimizations if all item views are of the same size:
        mReciclerView.setHasFixedSize(true);
        /*mAdapter = new AppsListRecyclerViewAdapter(mEntryList, mListener);
        mReciclerView.setAdapter(mAdapter);*/

        if (Genre.getCategories(getActivity()).isEmpty()) {

            Genre.getCategories(getActivity());

        } else {
            mAdapter = new CategoriesListRecyclerViewAdapter(Genre.getCategories(getActivity()), mListener);
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
}
