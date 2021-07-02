package com.faizurazadri.moviecatalogue.ui.home;

import androidx.test.InstrumentationRegistry;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.contrib.RecyclerViewActions;

import com.faizurazadri.moviecatalogue.R;
import com.faizurazadri.moviecatalogue.data.source.local.entity.MoviesEntity;
import com.faizurazadri.moviecatalogue.data.source.local.entity.TvShowEntity;
import com.faizurazadri.moviecatalogue.utils.DataDummy;
import com.faizurazadri.moviecatalogue.utils.EspressoIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static java.lang.Thread.sleep;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HomeActivityTest {

    private ArrayList<MoviesEntity> dummyMovie = DataDummy.generateDummyMovies();
    private ArrayList<TvShowEntity> dummyTvShow = DataDummy.generateDummyTvShow();

    @Before
    public void setup() {
        ActivityScenario.launch(HomeActivity.class);
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource());
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource());
    }

    @Test
    public void A_loadMovie() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.scrollToPosition(dummyMovie.size()));
    }

    @Test
    public void B_loadDetailMovies() {
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.tv_item_title)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_item_title)).check(matches(withText(dummyMovie.get(0).getTitle())));

        onView(withId(R.id.tv_description)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_description)).check(matches(withText(dummyMovie.get(0).getOverview())));

        onView(withId(R.id.img_poster)).check(matches(isDisplayed()));

        onView(withId(R.id.tv_item_imdb)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_item_imdb)).check(matches(withText(dummyMovie.get(0).getVote_average())));

        onView(withId(R.id.tv_vote_count)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_vote_count)).check(matches(withText(dummyMovie.get(0).getVote_count())));

        onView(withId(R.id.tv_item_relase)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_item_relase)).check(matches(withText(dummyMovie.get(0).getRelease_date())));

        onView(withId(R.id.tv_country)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_country)).check(matches(withText(dummyMovie.get(0).getOriginal_language())));

        onView(withId(R.id.tv_popularity)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_popularity)).check(matches(withText(dummyMovie.get(0).getPopularity())));
    }

    @Test
    public void C_addFavoriteMovie() throws InterruptedException {
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        sleep(1000);
        onView(withId(R.id.add_favorite)).perform(click());
    }

    @Test
    public void D_loadFavoriteMovie() {
        onView(withText("FAVORITE")).perform(click());
        onView(withId(R.id.rv_favorite)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_favorite)).perform(RecyclerViewActions.scrollToPosition(dummyMovie.size()));
    }

    @Test
    public void E_deleteFavoriteMovie() {
        onView(withText("FAVORITE")).perform(click());
        onView(withId(R.id.rv_favorite)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_favorite)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.add_favorite)).perform(click());
    }

    @Test
    public void F_loadTvShow() {
        onView(withText("TV SHOW")).perform(click());
        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.scrollToPosition(dummyTvShow.size()));
    }

    @Test
    public void G_loadDetailTvShow() {
        onView(withText("TV SHOW")).perform(click());
        onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.tv_item_title)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_item_title)).check(matches(withText(dummyTvShow.get(0).getName())));

        onView(withId(R.id.tv_item_relase)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_item_relase)).check(matches(withText(dummyTvShow.get(0).getFirst_air_date())));

        onView(withId(R.id.img_poster)).check(matches(isDisplayed()));


        onView(withId(R.id.tv_language)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_language)).check(matches(withText(dummyTvShow.get(0).getOriginal_language())));

        onView(withId(R.id.tv_item_showstar)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_item_showstar)).check(matches(withText(dummyTvShow.get(0).getVote_average())));

        onView(withId(R.id.tv_popularity)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_popularity)).check(matches(withText(dummyTvShow.get(0).getPopularity())));

        onView(withId(R.id.tv_description)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_description)).check(matches(withText(dummyTvShow.get(0).getOverview())));
    }

    @Test
    public void H_addFavoriteTvShow() throws InterruptedException {
        onView(withText("TV SHOW")).perform(click());
        onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        sleep(1000);
        onView(withId(R.id.add_favorite)).perform(click());
    }

    @Test
    public void I_loadFavoriteTvShow() {
        onView(withText("FAVORITE")).perform(click());
        onView(withId(R.id.fab_show)).perform(click());
        onView(withId(R.id.fab_show_tvshow)).perform(click());
        onView(withId(R.id.rv_favorite)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_favorite)).perform(RecyclerViewActions.scrollToPosition(dummyTvShow.size()));
    }

    @Test
    public void J_deleteFavoriteTvShow() {
        onView(withText("FAVORITE")).perform(click());
        onView(withId(R.id.fab_show)).perform(click());
        onView(withId(R.id.fab_show_tvshow)).perform(click());
        onView(withId(R.id.rv_favorite)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_favorite)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.add_favorite)).perform(click());
    }
}