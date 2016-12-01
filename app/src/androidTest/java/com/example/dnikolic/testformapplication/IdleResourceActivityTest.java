package com.example.dnikolic.testformapplication;

import static org.junit.Assert.*;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;


/**
 * Created by d.nikolic on 12/1/2016.
 */
@RunWith(AndroidJUnit4.class)
public class IdleResourceActivityTest {
    @Rule
    public ActivityTestRule<IdleResourceActivity> mActivityRule= new ActivityTestRule<IdleResourceActivity>(IdleResourceActivity.class);

    @Test
    public void mainScreen_load(){

        onView(withId(R.id.main_text)).check(matches(withText(R.string.application_loaded)));
    }
}


