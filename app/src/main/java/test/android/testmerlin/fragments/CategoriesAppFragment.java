package test.android.testmerlin.fragments;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import test.android.testmerlin.R;
import test.android.testmerlin.activities.CategoriesActivity;
import test.android.testmerlin.activities.MainActivity;
import test.android.testmerlin.adapters.AppsListRecyclerViewAdapter;
import test.android.testmerlin.databinding.FragmentAppsCategoryBinding;
import test.android.testmerlin.interfaces.OnClickActivityListener;
import test.android.testmerlin.models.Listing;
import test.android.testmerlin.models.RedditApiResponse;
import test.android.testmerlin.models.Thing;
import test.android.testmerlin.services.ApiClient;
import test.android.testmerlin.utils.Constants;
import test.android.testmerlin.utils.Utils;

public class CategoriesAppFragment extends Fragment {

    public static final String CLASS_TAG = CategoriesAppFragment.class.getSimpleName();
    private AppsListRecyclerViewAdapter mAdapter;
    private Context mContext;
    private List<Thing> things = new ArrayList<>();
    private OnClickActivityListener mListener;

    private String categoryId;
    private String categorySelected;

    private FragmentAppsCategoryBinding binding;

    public CategoriesAppFragment() {
    }

    public static CategoriesAppFragment newInstance() {
        CategoriesAppFragment fragment = new CategoriesAppFragment();
        return fragment;
    }

    public static CategoriesAppFragment newInstance(Bundle args) {
        CategoriesAppFragment fragment = new CategoriesAppFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            categoryId = getArguments().getString("category_id");
            categorySelected = getArguments().getString("name_category");

            getPostsRedditFromCategory(categoryId);
        } else {
            categoryId = null;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = initViews(inflater, container);

        setErrorConnectionOrShowCategories();

        setListeners();

        return view;
    }

    private void setErrorConnectionOrShowCategories() {
        if (things.isEmpty()) {

            if (categoryId != null) {
                if (Utils.isNetworkConnected()) {
                    binding.containerProgressIndicator.setVisibility(View.VISIBLE);
                    getPostsRedditFromCategory(categoryId);
                } else {
                    binding.containerProgressIndicator.setVisibility(View.GONE);
                    binding.containerNoInternetMessage.setVisibility(View.VISIBLE);
                }
            } else {
                if (Utils.isNetworkConnected()) {
                    binding.containerProgressIndicator.setVisibility(View.VISIBLE);
                    getPostsRedditFromCategory("0");
                } else {
                    binding.containerProgressIndicator.setVisibility(View.GONE);
                    binding.containerNoInternetMessage.setVisibility(View.VISIBLE);
                }
            }

        } else {
            mAdapter = new AppsListRecyclerViewAdapter(things, mListener, Constants.TYPE_FROM_BROWSER);
            binding.postsListsReddit.setAdapter(mAdapter);
        }
    }

    @NonNull
    private View initViews(LayoutInflater inflater, ViewGroup container) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_apps_category, container, false);

        View view = binding.getRoot();

        mContext = view.getContext();

        mListener.setTitleToolbar(categorySelected);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        binding.postsListsReddit.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);


        // allows for optimizations if all item views are of the same size:
        binding.postsListsReddit.setHasFixedSize(true);
        mAdapter = new AppsListRecyclerViewAdapter(things, mListener, Constants.TYPE_FROM_BROWSER);
        binding.postsListsReddit.setAdapter(mAdapter);
        return view;
    }

    private void setListeners() {
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), CategoriesActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(i, MainActivity.RESULT_OK);
            }
        });
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

    private void getPostsRedditFromCategory(String categoryId) {
        ApiClient.ApiInterface apiService = ApiClient.getApiClient().create(ApiClient.ApiInterface.class);
        Call<RedditApiResponse> call = apiService.getPostsFromCategories(categoryId);
        call.enqueue(new Callback<RedditApiResponse>() {
            @Override
            public void onResponse(Call<RedditApiResponse> call, Response<RedditApiResponse> response) {
                RedditApiResponse apiResponse = response.body();

                things = getListOfApplications(apiResponse);

                if (response.isSuccessful()) {
                    binding.containerProgressIndicator.setVisibility(View.GONE);

                    mAdapter = new AppsListRecyclerViewAdapter(things, mListener, Constants.TYPE_FROM_BROWSER);
                    binding.postsListsReddit.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<RedditApiResponse> call, Throwable t) {
                t.printStackTrace();
                binding.containerNoInternetMessage.setVisibility(View.VISIBLE);
            }
        });

    }

    private List<Thing> getListOfApplications(RedditApiResponse feedWrapper) {
        Listing listing = null;
        List<Thing> things = new ArrayList<>();
        if (feedWrapper != null) {
            listing = feedWrapper.getListing();
        }
        if (listing != null) {
            if (listing.getThings() != null && !listing.getThings().isEmpty()) {
                Log.e(CLASS_TAG, "" + listing.getThings().size());
                things = listing.getThings();
            } else {
                Log.e(CLASS_TAG, "Empty");
            }
        } else {
            Log.e(CLASS_TAG, "Empty listing!");
        }
        return things;
    }
}
