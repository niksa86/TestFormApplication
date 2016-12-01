package com.example.dnikolic.testformapplication;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.web.model.Evaluation;
import android.support.test.espresso.web.sugar.Web;
import android.support.test.espresso.web.webdriver.Locator;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.web.sugar.Web.onWebView;
import static android.support.test.espresso.web.webdriver.DriverAtoms.findElement;
import static android.support.test.espresso.web.webdriver.DriverAtoms.webClick;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.hamcrest.core.AllOf;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExampleInstrumentedTest {
    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule= new ActivityTestRule<LoginActivity>(LoginActivity.class);


    @Test
    public void testloginActivity() {
        //Type an email address in Email text form
        onView(withId(R.id.email)).perform(typeText("test@email.com")).check(matches(withText("test@email.com")));

        //Click on Spinner
        onView(withId(R.id.email_spinner)).perform(click());

        onData(allOf(is(instanceOf(String.class)), is("Other"))).perform(click());

        onView(withId(R.id.email_spinner)).check(matches(withSpinnerText(containsString("Other"))));

        //Type Password in Password text form
        onView(withId(R.id.password)).perform(typeText("Test@1234")).check(matches(withText("Test@1234")));

        closeSoftKeyboard();

        //Click on Login Button
        onView(withId(R.id.email_sign_in_button)).perform(click());

        //Check if new activity is opened
        onView(withId(R.id.activity_display_message)).check(matches(isDisplayed()));

        //Check if Espresso image is displayed
        onView(withId(R.id.espresso_icon)).check(matches(isDisplayed()));

        //Check if main activity is displayed
        ViewInteraction imageButton = onView(
                allOf(withContentDescription("Navigate up"),
                        withParent(allOf(withId(R.id.action_bar),
                                withParent(withId(R.id.action_bar_container)))),
                        isDisplayed()));
        imageButton.perform(click());

    }

    @Test
    public void testSpinnerText(){
        //Tap on spinner and choose item Work
        closeSoftKeyboard();

        onView(withId(R.id.email_spinner)).perform(click());

        onData(allOf(is(instanceOf(String.class)), is("Work"))).perform(click());

        onView(withId(R.id.email_spinner)).check(matches(withSpinnerText(containsString("Work"))));

        //Tap on spinner and choose item Home
        onView(withId(R.id.email_spinner)).perform(click());

        onData(allOf(is(instanceOf(String.class)), is("Home"))).perform(click());

        onView(withId(R.id.email_spinner)).check(matches(withSpinnerText(containsString("Home"))));

        //Tap on spinner and choose item Other
        onView(withId(R.id.email_spinner)).perform(click());

        onData(allOf(is(instanceOf(String.class)), is("Other"))).perform(click());

        onView(withId(R.id.email_spinner)).check(matches(withSpinnerText(containsString("Other"))));


        //Tap on spinner and choose item Custom
        onView(withId(R.id.email_spinner)).perform(click());

        onData(allOf(is(instanceOf(String.class)), is("Custom"))).perform(click());

        onView(withId(R.id.email_spinner)).check(matches(withSpinnerText(containsString("Custom"))));


    }

}
