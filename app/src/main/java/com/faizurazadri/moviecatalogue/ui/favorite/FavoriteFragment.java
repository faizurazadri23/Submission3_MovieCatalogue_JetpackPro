package com.faizurazadri.moviecatalogue.ui.favorite;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.faizurazadri.moviecatalogue.R;
import com.faizurazadri.moviecatalogue.databinding.FragmentFavoriteBinding;
import com.faizurazadri.moviecatalogue.viewmodel.ViewModelFactoryMovie;
import com.faizurazadri.moviecatalogue.viewmodel.ViewModelFactoryTvShow;


public class FavoriteFragment extends Fragment {

    FragmentFavoriteBinding favoriteBinding;
    private boolean isFabOpen = false;
    private Animation animOpen, animClose, animFrom, animTo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        favoriteBinding = FragmentFavoriteBinding.inflate(inflater, container, false);
        return favoriteBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setAnim();

        if (getActivity() != null) {
            showFavoriteMovies();
        }


        favoriteBinding.fabShowMovie.setOnClickListener(new aksi_button());
        favoriteBinding.fabShowTvshow.setOnClickListener(new aksi_button());
        favoriteBinding.fabShow.setOnClickListener(new aksi_button());
    }


    class aksi_button implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fab_show:
                    if (!isFabOpen) {
                        showFabMenu();
                    } else {
                        closeFabMenu();
                    }
                    break;

                case R.id.fab_showMovie:
                    if (getActivity() != null) {
                        showFavoriteMovies();
                    }

                    break;

                case R.id.fab_show_tvshow:
                    if (getActivity() != null) {
                        showFavoriteTvShow();
                    }
                    break;
            }
        }
    }

    @SuppressLint("FragmentLiveDataObserve")
    private void showFavoriteMovies() {
        ViewModelFactoryMovie factoryMovie = ViewModelFactoryMovie.getInstance(getActivity());
        FavoriteMoviesViewModel viewModel = new ViewModelProvider(this, factoryMovie).get(FavoriteMoviesViewModel.class);

        FavoriteMovieAdapter adapter = new FavoriteMovieAdapter();

        favoriteBinding.progressFavorite.setVisibility(View.VISIBLE);
        viewModel.getFavorite().observe(this, moviesEntities -> {
            favoriteBinding.progressFavorite.setVisibility(View.GONE);
            adapter.submitList(moviesEntities);
        });

        favoriteBinding.rvFavorite.setLayoutManager(new LinearLayoutManager(getContext()));
        favoriteBinding.rvFavorite.setHasFixedSize(true);
        favoriteBinding.rvFavorite.setAdapter(adapter);
    }

    @SuppressLint("FragmentLiveDataObserve")
    private void showFavoriteTvShow() {
        ViewModelFactoryTvShow factoryTvShow = ViewModelFactoryTvShow.getInstance(getActivity());
        FavoriteTvShowViewModel viewModel = new ViewModelProvider(this, factoryTvShow).get(FavoriteTvShowViewModel.class);

        FavoriteTvShowAdapter adapter = new FavoriteTvShowAdapter();

        favoriteBinding.progressFavorite.setVisibility(View.VISIBLE);
        viewModel.getFavorite().observe(this, tvShowEntities -> {
            favoriteBinding.progressFavorite.setVisibility(View.GONE);
            adapter.submitList(tvShowEntities);
        });

        favoriteBinding.rvFavorite.setLayoutManager(new LinearLayoutManager(getContext()));
        favoriteBinding.rvFavorite.setHasFixedSize(true);
        favoriteBinding.rvFavorite.setAdapter(adapter);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        favoriteBinding = null;
    }

    private void closeFabMenu() {
        isFabOpen = false;
        favoriteBinding.fabShowMovie.setVisibility(View.INVISIBLE);
        favoriteBinding.fabShowTvshow.setVisibility(View.INVISIBLE);
        favoriteBinding.fabShow.startAnimation(animClose);
        favoriteBinding.fabShowMovie.startAnimation(animTo);
        favoriteBinding.fabShowTvshow.startAnimation(animTo);
    }

    private void showFabMenu() {
        isFabOpen = true;
        favoriteBinding.fabShowMovie.setVisibility(View.VISIBLE);
        favoriteBinding.fabShowTvshow.setVisibility(View.VISIBLE);
        favoriteBinding.fabShow.startAnimation(animOpen);
        favoriteBinding.fabShowMovie.startAnimation(animFrom);
        favoriteBinding.fabShowTvshow.startAnimation(animFrom);
    }

    private void setAnim() {
        animOpen = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_open_anim);
        animClose = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_close_anim);
        animFrom = AnimationUtils.loadAnimation(getActivity(), R.anim.from_bottom_anim);
        animTo = AnimationUtils.loadAnimation(getActivity(), R.anim.to_bottom_anim);
    }
}