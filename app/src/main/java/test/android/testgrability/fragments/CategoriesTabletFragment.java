package test.android.testgrability.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import test.android.testgrability.R;
import test.android.testgrability.adapters.CategoriesListRecyclerViewAdapter;
import test.android.testgrability.interfaces.OnClickActivityListener;
import test.android.testgrability.models.AppImage;
import test.android.testgrability.models.Entry;
import test.android.testgrability.models.Genre;

/**
 * Created by Nicolas on 13/10/2016.
 */
public class CategoriesTabletFragment extends Fragment {

    public static final String CLASS_TAG = CategoriesTabletFragment.class.getSimpleName();
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

    public CategoriesTabletFragment() {}

    public static CategoriesTabletFragment newInstance() {
        CategoriesTabletFragment fragment = new CategoriesTabletFragment();
        return fragment;
    }

    public static CategoriesTabletFragment newInstance(Bundle args) {
        CategoriesTabletFragment fragment = new CategoriesTabletFragment();
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
        View mView = inflater.inflate(R.layout.activity_categories_item_list, container, false);

        mListener.setTitleToolbar("Categories");

        mContext = mView.getContext();

        mReciclerView = (RecyclerView) mView.findViewById(R.id.item_list);

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
