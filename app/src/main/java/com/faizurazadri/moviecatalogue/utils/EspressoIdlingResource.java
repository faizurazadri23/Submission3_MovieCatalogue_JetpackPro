package com.faizurazadri.moviecatalogue.utils;

import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.idling.CountingIdlingResource;

public class EspressoIdlingResource {

    private static final String RESOURCE = "GLOBAL";

    private static CountingIdlingResource countingIdlingResource = new CountingIdlingResource(RESOURCE);

    public static void increment() {
        countingIdlingResource.increment();
    }

    public static void decrement() {
        countingIdlingResource.decrement();
    }

    public static IdlingResource idlingResource() {
        return countingIdlingResource;
    }
}
