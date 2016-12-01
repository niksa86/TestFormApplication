package com.example.dnikolic.testformapplication;


import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.EditText;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.hamcrest.core.IsEqual;

import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.intent.Intents.times;
import static org.hamcrest.Matchers.not;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.intent.matcher.BundleMatchers.hasEntry;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtras;
import static org.hamcrest.Matchers.allOf;


/**
 * Created by d.nikolic on 11/10/2016.
 */
@RunWith(AndroidJUnit4.class)
public class IntentTest {

    public static final String VALID_PHONE_NUMBER="064333222";
    public static final Uri INTENT_DATA_PHONE_NUMBER= Uri.parse("tel:" + VALID_PHONE_NUMBER);
    public static String PACKAGE_ANDROID_DIALER = "com.android.contacts";

    //Intent test rule
    @Rule
    public IntentsTestRule<LoginActivity> mIntentsTestRule= new IntentsTestRule<>(LoginActivity.class);

    //Intent validation
    @Test
    public void testIntentValidation(){
        //User action that results in "WebView" activity being launched.
        onView(withId(R.id.google_button)).perform(click());

        // Using a canned RecordedIntentMatcher to validate that an intent resolving
        // to the "WebView" activity has been sent.
        intending(toPackage("com.example.dnikolic.testformapplication"));

    }

    //Intent stubbing-mocking
    @Test
    public void IntentSubbing(){
        //Build a result to return when a particular activity is launched.
        Intent resultData= new Intent();
        resultData.putExtra("phone", VALID_PHONE_NUMBER);
        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);

        // Set up result stubbing when an intent sent to "contacts" is seen.
        intending(toPackage("com.android.phone")).respondWith(result);

    }

    //Intent matching
    @Test
    public void testIntentMatchingActionDial(){
        //Types a phone number into the dialer edit text field and presses the call button.

        onView(withId(R.id.phone_call)).perform(typeText(VALID_PHONE_NUMBER), closeSoftKeyboard());
        onView(withId(R.id.phone)).perform(click());

        // Verify that an intent to the dialer was sent with the correct action, phone
        // number and package. Think of Intents intended API as the equivalent to Mockito's verify.
        intended(allOf(hasAction(Intent.ACTION_DIAL), toPackage(PACKAGE_ANDROID_DIALER)));

    }



}
