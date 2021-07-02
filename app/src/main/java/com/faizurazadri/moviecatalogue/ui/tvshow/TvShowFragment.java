package com.faizurazadri.moviecatalogue.ui.tvshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.faizurazadri.moviecatalogue.R;
import com.faizurazadri.moviecatalogue.databinding.FragmentTvShowBinding;
import com.faizurazadri.moviecatalogue.viewmodel.ViewModelFactoryTvShow;


public class TvShowFragment extends Fragment {

    FragmentTvShowBinding fragmentTvShowBinding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentTvShowBinding = FragmentTvShowBinding.inflate(inflater, container, false);
        return fragmentTvShowBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getActivity() != null) {
            TvShowViewModel viewModel = obtainViewModel(getActivity());
            TvShowAdapter adapter = new TvShowAdapter();

            viewModel.getTvShow().observe(getActivity(), tvshow -> {
                if (tvshow != null) {
                    switch (tvshow.status) {
                        case LOADING:
                            fragmentTvShowBinding.progressTvshow.setVisibility(View.VISIBLE);
                            break;

                        case SUCCESS:
                            fragmentTvShowBinding.progressTvshow.setVisibility(View.GONE);
                            adapter.submitList(tvshow.data);
                            adapter.notifyDataSetChanged();
                            break;

                        case ERROR:
                            fragmentTvShowBinding.progressTvshow.setVisibility(View.GONE);
                            Toast.makeText(getActivity(), getResources().getString(R.string.error_message), Toast.LENGTH_LONG).show();

                    }
                }
            });

            fragmentTvShowBinding.rvTvshow.setLayoutManager(new LinearLayoutManager(getActivity()));
            fragmentTvShowBinding.rvTvshow.setHasFixedSize(true);
            fragmentTvShowBinding.rvTvshow.setAdapter(adapter);
        }
    }

    private static TvShowViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactoryTvShow factoryTvShow = ViewModelFactoryTvShow.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factoryTvShow).get(TvShowViewModel.class);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        fragmentTvShowBinding = null;
    }
}