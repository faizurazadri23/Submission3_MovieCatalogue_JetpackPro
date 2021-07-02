package com.faizurazadri.moviecatalogue.ui.movie;

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
import com.faizurazadri.moviecatalogue.databinding.FragmentMovieBinding;
import com.faizurazadri.moviecatalogue.viewmodel.ViewModelFactoryMovie;


public class MovieFragment extends Fragment {


    private FragmentMovieBinding fragmentMovieBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentMovieBinding = FragmentMovieBinding.inflate(inflater, container, false);
        return fragmentMovieBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getActivity() != null) {
            MovieViewModel viewModel = obtainViewModel(getActivity());
            MovieAdapter adapter = new MovieAdapter();

            viewModel.getMovies().observe(getActivity(), movies -> {
                if (movies != null) {
                    switch (movies.status) {
                        case LOADING:
                            fragmentMovieBinding.progressMovie.setVisibility(View.VISIBLE);
                            break;

                        case SUCCESS:
                            fragmentMovieBinding.progressMovie.setVisibility(View.GONE);
                            adapter.submitList(movies.data);
                            adapter.notifyDataSetChanged();

                            break;

                        case ERROR:
                            fragmentMovieBinding.progressMovie.setVisibility(View.GONE);
                            Toast.makeText(getActivity(), getResources().getString(R.string.error_message), Toast.LENGTH_LONG).show();

                    }
                }
            });

            fragmentMovieBinding.rvMovies.setLayoutManager(new LinearLayoutManager(getActivity()));
            fragmentMovieBinding.rvMovies.setHasFixedSize(true);
            fragmentMovieBinding.rvMovies.setAdapter(adapter);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        fragmentMovieBinding = null;
    }

    private static MovieViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactoryMovie factoryMovie = ViewModelFactoryMovie.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factoryMovie).get(MovieViewModel.class);
    }
}