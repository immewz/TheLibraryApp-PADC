package com.mewz.thelibrary.uitests.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.mewz.thelibrary.R
import com.mewz.thelibrary.activities.MainActivity
import com.mewz.thelibrary.uitests.first
import com.mewz.thelibrary.views.viewholders.library.LibraryChipViewHolder
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.allOf
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@RunWith(AndroidJUnit4ClassRunner::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class BookTestFlow {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    @Test
    fun a_tapOnFirstCategory_goToBookDetail(){

        // First Category
        onView(withId(R.id.tvFirstCategory))
            .check(matches(isDisplayed()))
        onView(withId(R.id.vpFirstBookList))
            .check(matches((isDisplayed())))
        Thread.sleep(1000L)

        // click on item in recyclerview
        onView(allOf(withId(R.id.rvBookList), isDescendantOfA(withId(R.id.vpFirstBookList))))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                    click()
                )
            )
        Thread.sleep(1000L)

        // Verify views in BookDetail
        onView(withId(R.id.tvBookTitle))
            .check(matches(withText("HAPPY PLACE")))
        onView(withId(R.id.tvAuthor))
            .check(matches(withText("Emily Henry")))
        onView(withId(R.id.ivBook))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tvOverview))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tvBookType))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tvPage))
            .check(matches(isDisplayed()))
        onView(withId(R.id.llButton))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tvInfo))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tvAbout))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tvRating))
            .check(matches(isDisplayed()))
        onView(withId(R.id.vpRatingAndReview))
            .check(matches(isDisplayed()))
        onView(withId(R.id.btnBackBookDetail)).perform(click())

        Thread.sleep(1000L)

    }

    @Test
    fun b_tapOnSecondCategory_goToDetail(){

        Thread.sleep(1000L)
        onView(withId(R.id.nestedScrollView))
            .perform(ViewActions.swipeUp())

        // Second Category
        onView(withId(R.id.tvSecondCategory))
            .check(matches(isDisplayed()))
        onView(withId(R.id.vpSecondBookList))
            .check(matches((isDisplayed())))
        Thread.sleep(1000L)

        // click on item in recyclerview
        onView(allOf(withId(R.id.rvBookList), isDescendantOfA(withId(R.id.vpSecondBookList))))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                    click()
                )
            )
        Thread.sleep(1000L)

        // Verify views in BookDetail
        onView(withId(R.id.tvBookTitle))
            .check(matches(withText("PAGEBOY")))
        onView(withId(R.id.tvAuthor))
            .check(matches(withText("Elliot Page")))
        onView(withId(R.id.ivBook))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tvOverview))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tvBookType))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tvPage))
            .check(matches(isDisplayed()))
        onView(withId(R.id.llButton))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tvInfo))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tvAbout))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tvRating))
            .check(matches(isDisplayed()))
        onView(withId(R.id.vpRatingAndReview))
            .check(matches(isDisplayed()))
        onView(withId(R.id.btnBackBookDetail)).perform(click())
        Thread.sleep(1000L)

    }

    @Test
    fun c_tapOnThirdCategory_goToDetail(){

        Thread.sleep(1000L)
        onView(withId(R.id.nestedScrollView))
            .perform(ViewActions.swipeUp())

        // Third Category
        onView(withId(R.id.tvThirdCategory))
            .check(matches(isDisplayed()))
        onView(withId(R.id.vpThirdBookList))
            .check(matches((isDisplayed())))
        Thread.sleep(1000L)

        // click on item in recyclerview
        onView(allOf(withId(R.id.rvBookList), isDescendantOfA(withId(R.id.vpThirdBookList))))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2,
                    click()
                )
            )
        Thread.sleep(1000L)

        // Verify views in BookDetail
        onView(withId(R.id.tvBookTitle))
            .check(matches(withText("CRYING IN H MART")))
        onView(withId(R.id.tvAuthor))
            .check(matches(withText("Michelle Zauner")))
        onView(withId(R.id.ivBook))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tvOverview))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tvBookType))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tvPage))
            .check(matches(isDisplayed()))
        onView(withId(R.id.llButton))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tvInfo))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tvAbout))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tvRating))
            .check(matches(isDisplayed()))
        onView(withId(R.id.vpRatingAndReview))
            .check(matches(isDisplayed()))
        onView(withId(R.id.btnBackBookDetail)).perform(click())
        Thread.sleep(1000L)

    }

    @Test
    fun tapOnAllCategoryBookItem_isVisibleBanner(){
        Thread.sleep(1000L)
        onView(withId(R.id.viewPagerBanner))
            .check(matches(isDisplayed()))

    }

    @Test
    fun d_tapOnLibraryTab_ViewPod(){

        Thread.sleep(1000L)

        // Click on library tab
        onView(withId(R.id.libraryFragment)).perform(click())
        Thread.sleep(1000L)

        // Verify TabLayout in library tab
        onView(withId(R.id.tlBookLibrary))
            .check(matches(isDisplayed()))

        // Verify Viewpod
        onView(withId(R.id.vpLibraryBook))
            .check(matches(isDisplayed()))
        // Verify Title and Author by List View
        onView(first<View>(withId(R.id.tvBookTitle)))
            .check(matches(isDisplayed()))
        onView(first<View>(withId(R.id.tvAuthor)))
            .check(matches(isDisplayed()))
        // Verify Title and Author by List View
        onView(first<View>(withId(R.id.tvBookTitle)))
            .check(matches(withText("HAPPY PLACE")))
        onView(first<View>(withId(R.id.tvAuthor)))
            .check(matches(withText("Emily Henry")))

        // Click on View List
        onView(withId(R.id.btnViewBooks)).perform(click())
        // CLick on List Filter button
        onView(withId(R.id.rbList)).perform(click())
        onView(isRoot()).perform(click())
        onView(withId(R.id.btnClearChip)).perform(click())
        Thread.sleep(1000L)

        // Click on View Small Grid
        onView(withId(R.id.btnViewBooks)).perform(click())
        // CLick on List Filter button
        onView(withId(R.id.rbSmallGrid)).perform(click())
        onView(isRoot()).perform(click())
        onView(withId(R.id.btnClearChip)).perform(click())
        Thread.sleep(1000L)

        // Click on View Large Grid
        onView(withId(R.id.btnViewBooks)).perform(click())
        // CLick on List Filter button
        onView(withId(R.id.rbLargeGrid)).perform(click())
        onView(isRoot()).perform(click())
        onView(withId(R.id.btnClearChip)).perform(click())
        Thread.sleep(1000L)


        // Click on first chip
        onView(withId(R.id.rvChipList))
            .perform(RecyclerViewActions.actionOnItemAtPosition<LibraryChipViewHolder>(0, click()))
        // Scroll left
        onView(withId(R.id.rvChipList))
            .perform(RecyclerViewActions.scrollToPosition<LibraryChipViewHolder>(0))
        // Click on second chip
        onView(withId(R.id.rvChipList))
            .perform(RecyclerViewActions.actionOnItemAtPosition<LibraryChipViewHolder>(1, click()))
        // Click on third chip
        onView(withId(R.id.rvChipList))
            .perform(RecyclerViewActions.actionOnItemAtPosition<LibraryChipViewHolder>(2, click()))

        // Click on clear button for chips
        onView(withId(R.id.btnClearChip)).perform(click())

        // Click on SortBy
        onView(withId(R.id.btnSortByLibrary)).perform(click())
        // CLick on SortBy List
        onView(withId(R.id.rbTitle)).perform(click())
        onView(isRoot()).perform(click())
        Thread.sleep(1000L)

        // Click on SortBy
        onView(withId(R.id.btnSortByLibrary)).perform(click())
        // CLick on SortBy List
        onView(withId(R.id.rbAuthor)).perform(click())
        onView(isRoot()).perform(click())
        Thread.sleep(1000L)

    }

}