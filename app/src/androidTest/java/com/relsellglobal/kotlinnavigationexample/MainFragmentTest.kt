package com.relsellglobal.kotlinnavigationexample

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainFragmentTest {

    @Test
    fun testMainFragmentIsInView() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        val mainFragmentSenario = launchFragmentInContainer<MainFragment>()
        mainFragmentSenario.onFragment{
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(it.requireView(),navController)
        }

        onView(ViewMatchers.withId(R.id.parentLayoutFragmentMain))
            .check(matches(isDisplayed()))


    }


}

