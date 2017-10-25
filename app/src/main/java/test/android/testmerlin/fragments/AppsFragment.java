package test.android.testmerlin.fragments;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import test.android.testmerlin.R;
import test.android.testmerlin.activities.CategoriesActivity;
import test.android.testmerlin.adapters.AppsListRecyclerViewAdapter;
import test.android.testmerlin.databinding.FragmentAppsBinding;
import test.android.testmerlin.interfaces.OnClickActivityListener;
import test.android.testmerlin.models.Listing;
import test.android.testmerlin.models.RedditApiResponse;
import test.android.testmerlin.models.Thing;
import test.android.testmerlin.services.ApiClient;
import test.android.testmerlin.utils.App;
import test.android.testmerlin.utils.Constants;
import test.android.testmerlin.utils.Utils;

public class AppsFragment extends Fragment {

    public static final String CLASS_TAG = AppsFragment.class.getSimpleName();
    private AppsListRecyclerViewAdapter mAdapter;
    private Context mContext;
    List<Thing> things = new ArrayList<>();
    private OnClickActivityListener mListener;

    private FragmentAppsBinding binding;

    public AppsFragment() {
    }

    public static AppsFragment newInstance() {
        AppsFragment fragment = new AppsFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = initViews(inflater, container);

        setErrorConnectionOrShowPosts();

        setListeners();

        return view;
    }

    private void setListeners() {
        binding.postsListsReddit.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 || dy < 0 && binding.fab.isShown()) {
                    binding.fab.hide();
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    binding.fab.show();
                }

                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }

    private void setErrorConnectionOrShowPosts() {
        if (things.isEmpty()) {
            if (Utils.isNetworkConnected()) {
                binding.containerProgressIndicator.setVisibility(View.VISIBLE);
                getPostsRedditFromApiResponse();
            } else {
                binding.containerProgressIndicator.setVisibility(View.GONE);
                binding.containerNoInternetMessage.setVisibility(View.VISIBLE);
            }
        } else {
            mAdapter = new AppsListRecyclerViewAdapter(things, mListener, Constants.TYPE_FROM_APP_VIEW);
            binding.postsListsReddit.setAdapter(mAdapter);
        }
    }

    private View initViews(LayoutInflater inflater, ViewGroup container) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_apps, container, false);

        mListener.setTitleToolbar(App.getContext().getString(R.string.app_name));

        mContext = binding.getRoot().getContext();

        View view = binding.getRoot();

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCategoriesActivity();
            }
        });

        final LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        binding.postsListsReddit.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        // allows for optimizations if all item views are of the same size:
        binding.postsListsReddit.setHasFixedSize(true);
        mAdapter = new AppsListRecyclerViewAdapter(things, mListener, Constants.TYPE_FROM_APP_VIEW);
        binding.postsListsReddit.setAdapter(mAdapter);
        return view;
    }

    private void openCategoriesActivity() {
        Intent i = new Intent(getActivity(), CategoriesActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
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

    private void getPostsRedditFromApiResponse() {
        ApiClient.ApiInterface apiService = ApiClient.getApiClient().create(ApiClient.ApiInterface.class);
        Call<RedditApiResponse> call = apiService.getPostsLists();
        call.enqueue(new Callback<RedditApiResponse>() {
            @Override
            public void onResponse(Call<RedditApiResponse> call, Response<RedditApiResponse> response) {
                RedditApiResponse apiResponse = response.body();

                things = getPostsLists(apiResponse);

                if (response.isSuccessful()) {
                    binding.containerProgressIndicator.setVisibility(View.GONE);

                    mAdapter = new AppsListRecyclerViewAdapter(things, mListener, Constants.TYPE_FROM_APP_VIEW);
                    binding.postsListsReddit.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<RedditApiResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    private List<Thing> getPostsLists(RedditApiResponse feedWrapper) {
        Listing listing = null;
        List<Thing> things = new ArrayList<>();
        if (feedWrapper != null) {
            listing = feedWrapper.getListing();
        }
        if (listing != null) {
            if (listing.getThings() != null && !listing.getThings().isEmpty()) {
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
