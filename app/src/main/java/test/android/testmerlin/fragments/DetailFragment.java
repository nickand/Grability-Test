package test.android.testmerlin.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import test.android.testmerlin.R;
import test.android.testmerlin.databinding.FragmentDetailAppBinding;
import test.android.testmerlin.interfaces.OnClickActivityListener;
import test.android.testmerlin.models.Thing;
import test.android.testmerlin.services.ApiClient;

public class DetailFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String CLASS_TAG = DetailFragment.class.getSimpleName();

    private Thing thing;
    private boolean isShowing = false;

    private OnClickActivityListener mListener;

    private FragmentDetailAppBinding binding;

    public DetailFragment() {
    }

    public static DetailFragment newInstanceWithArguments(Thing e) {
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

            thing = getArguments().getParcelable(ARG_PARAM1);
            Log.d(CLASS_TAG, thing.getData().getDisplayName());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = initViews(inflater, container);

        setListeners();

        return view;
    }

    private void setListeners() {
        binding.btnGoToWeb.setOnClickListener(this);
        binding.tvShowMore.setOnClickListener(this);
    }

    private View initViews(LayoutInflater inflater, ViewGroup container) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_detail_app, container, false);

        // Inflate the layout for this fragment
        View view = binding.getRoot();

        mListener.setTitleToolbar(thing.getData().getDisplayName());

        binding.btnGoToWeb.setVisibility(View.VISIBLE);
        binding.tvShowMore.setVisibility(View.VISIBLE);
        binding.tvTitleDescription.setVisibility(View.VISIBLE);

        binding.tvDetailAppName.setText(thing.getData().getTitle());
        binding.tvDetailDescription.setText(thing.getData().getPublicDescription());

        binding.tvDetailAppMadeBy.setText("By " + thing.getData().getUrl());

        Uri uri = Uri.parse(thing.getData().getIconImg());
        binding.ivDetailIconApp.setImageURI(uri);

        return view;
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGoToWeb:
                mListener.openWebView(getActivity(), ApiClient.BASE_URL.concat(thing.getData().getUrl()));
                break;
            case R.id.tvShowMore:
                setShowMoreOrLess();
                break;
        }
    }

    private void setShowMoreOrLess() {
        if (!isShowing) {
            isShowing = true;
            binding.tvDetailDescription.setMaxLines(Integer.MAX_VALUE);
            binding.tvShowMore.setText(R.string.show_less);
        } else {
            isShowing = false;
            binding.tvDetailDescription.setMaxLines(4);
            binding.tvShowMore.setText(R.string.read_more);
        }
    }
}
