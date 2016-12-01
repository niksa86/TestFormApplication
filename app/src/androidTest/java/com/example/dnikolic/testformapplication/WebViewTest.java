package com.example.dnikolic.testformapplication;

import android.app.Instrumentation;
import android.support.test.espresso.web.webdriver.DriverAtoms;
import android.support.test.espresso.web.webdriver.Locator;
import android.support.test.rule.ActivityTestRule;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isJavascriptEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.web.sugar.Web.onWebView;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.web.webdriver.DriverAtoms.findElement;
import static android.support.test.espresso.web.webdriver.DriverAtoms.webClick;
import static android.support.test.espresso.web.webdriver.DriverAtoms.getText;
import  android.support.test.espresso.web.assertion.WebViewAssertions;
import static android.support.test.espresso.web.webdriver.DriverAtoms.clearElement;

import static org.hamcrest.Matchers.containsString;

import org.junit.Rule;
import org.junit.Test;

/**
 * Created by d.nikolic on 11/23/2016.
 */

public class WebViewTest {
    @Rule
    public ActivityTestRule<com.example.dnikolic.testformapplication.WebView> wActivityRule= new ActivityTestRule<com.example.dnikolic.testformapplication.WebView>(com.example.dnikolic.testformapplication.WebView.class){
        @Override
        protected void afterActivityLaunched(){
            //Turn on javascript in WebView Activity
            onWebView().forceJavascriptEnabled();

        }

    };

    @Test
    public void testWebView() {

        onView(withId(R.id.html_view)).check(matches(isDisplayed()));
        //Find the input element by tag name and insert text
        onWebView().withElement(findElement(Locator.NAME, "name")).perform(clearElement()).perform(DriverAtoms.webKeys("Dejan"));
        //Find the input element by tag name and insert text
        onWebView().withElement(findElement(Locator.NAME, "password")).perform(clearElement()).perform(DriverAtoms.webKeys("test"));
        //Find the input element by tag name and click on it
        onWebView().withElement(findElement(Locator.NAME, "reset")).perform(webClick());
        //Find the input element by tag name and check inserted text
        onWebView().withElement(findElement(Locator.NAME, "name")).perform(webClick()).check(WebViewAssertions.webMatches(getText(), containsString("")));
        //Find the input element by tag name and check inserted text
        onWebView().withElement(findElement(Locator.NAME, "password")).perform(webClick()).check(WebViewAssertions.webMatches(getText(), containsString("")));
    }

}
