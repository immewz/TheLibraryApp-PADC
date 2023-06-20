package com.mewz.thelibrary.uitests.shelf

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.google.android.material.tabs.TabLayout
import com.mewz.thelibrary.R
import com.mewz.thelibrary.activities.MainActivity
import com.mewz.thelibrary.uitests.first
import com.mewz.thelibrary.views.viewholders.library.LibraryChipViewHolder
import com.mewz.thelibrary.views.viewholders.library.ShelvesListViewHolder
import org.hamcrest.CoreMatchers
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@RunWith(AndroidJUnit4ClassRunner::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ShelfTestFlow {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    @Test
    fun a_tapOnCreateButton_goToNewShelf(){

        Thread.sleep(1000L)

        onView(withId(R.id.libraryFragment)).perform(click())

        onView(withId(R.id.tlBookLibrary))
            .perform(selectTabAtPosition(1))
        onView(withId(R.id.btnCreateShelf))
            .check(matches(isDisplayed()))
        onView(withId(R.id.llEmptyShelves))
            .check(matches(isDisplayed()))

        onView(withId(R.id.btnCreateShelf))
            .perform(click())
        onView(withId(R.id.tvShelfTitle))
            .check(matches(isDisplayed()))

        onView(withId(R.id.etCreateShelf))
            .check(matches(isDisplayed()))
        onView(withId(R.id.etCreateShelf))
            .perform(ViewActions.typeText("Poem"), ViewActions.closeSoftKeyboard())
        Thread.sleep(1000L)
        onView(withId(R.id.btnBackCreateShelf)).perform(click())

        Thread.sleep(1000L)

        onView(withId(R.id.rvShelvesList))
            .check(matches(isDisplayed()))
    }


    @Test
    fun b_tapOnBookOption_goToAddToShelves(){

        Thread.sleep(3000L)
        onView(withId(R.id.libraryFragment)).perform(click())
        Thread.sleep(1000L)

        // First Book
        onView(withId(R.id.rvLibraryBookListView))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                MyViewAction.clickOnSameView()))

        onView(withId(R.id.btnBottomSheetAddToShelve)).perform(click())
        Thread.sleep(1000L)

        // Add First Book
        onView(first<View>(withId(R.id.cbAddToShelves)))
            .perform(click())
        onView(withId(R.id.btnClose)).perform(click())
        Thread.sleep(1000L)

        // Second Book
        onView(withId(R.id.rvLibraryBookListView))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1,
                MyViewAction.clickOnSameView()))

        onView(withId(R.id.btnBottomSheetAddToShelve)).perform(click())
        Thread.sleep(1000L)

        // Add Second Book
        onView(first<View>(withId(R.id.cbAddToShelves)))
            .perform(click())
        onView(withId(R.id.btnClose)).perform(click())
        Thread.sleep(1000L)

        // Third Book
        onView(withId(R.id.rvLibraryBookListView))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2,
                MyViewAction.clickOnSameView()))

        onView(withId(R.id.btnBottomSheetAddToShelve)).perform(click())
        Thread.sleep(1000L)

        // Add Third Book
        onView(first<View>(withId(R.id.cbAddToShelves)))
            .perform(click())
        onView(withId(R.id.btnClose)).perform(click())
        Thread.sleep(1000L)

    }

    @Test
    fun c_tapOnShelf_goToShelfDetail(){

        Thread.sleep(3000L)
        onView(withId(R.id.libraryFragment)).perform(click())
        Thread.sleep(1000L)

        onView(withId(R.id.tlBookLibrary))
            .perform(selectTabAtPosition(1))

        onView(withId(R.id.rvShelvesList))
            .perform(RecyclerViewActions.actionOnItemAtPosition<ShelvesListViewHolder>(0, click()))

        Thread.sleep(1000L)
        onView(CoreMatchers.allOf(withId(R.id.rvLibraryBookListView),
                isDescendantOfA(withId(R.id.vpShelfBookList))
            )
        )

        onView(withId(R.id.tvShelfNameShelfDetail))
            .check(matches(withText("Poem")))

        onView(withId(R.id.tvBookCount))
            .check(matches(withText("3 books")))

        // Click on View List
        onView(withId(R.id.btnViewBooks)).perform(click())
        // CLick on List Filter button
        onView(withId(R.id.rbList)).perform(click())
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

        // Click on View Small Grid
        onView(withId(R.id.btnViewBooks)).perform(click())
        // CLick on List Filter button
        onView(withId(R.id.rbSmallGrid)).perform(click())
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

    @Test
    fun d_shelfDetailActivity_renameAndDelete(){

        Thread.sleep(3000L)
        onView(withId(R.id.libraryFragment)).perform(click())
        Thread.sleep(1000L)

        onView(withId(R.id.tlBookLibrary))
            .perform(selectTabAtPosition(1))

        onView(withId(R.id.rvShelvesList))
            .perform(RecyclerViewActions.actionOnItemAtPosition<ShelvesListViewHolder>(0, click()))

        Thread.sleep(1000L)
            onView(CoreMatchers.allOf(withId(R.id.rvLibraryBookListView),
            isDescendantOfA(withId(R.id.vpShelfBookList)))
        )

        // Change rename
        onView(withId(R.id.btnShelfDetailOption)).perform(click())
        onView(withId(R.id.btnBottomSheetRename)).perform(click())
        onView(withId(R.id.tvShelfNameShelfDetail))
            .check(matches(isNotClickable()))
        onView(withId(R.id.etShelfNameShelfDetail))
            .check(matches(isDisplayed()))
        onView(withId(R.id.etShelfNameShelfDetail))
            .perform(ViewActions.clearText())

        onView(withId(R.id.etShelfNameShelfDetail))
            .perform(ViewActions.typeText("Novel"),ViewActions.closeSoftKeyboard())

        onView(withId(R.id.etShelfNameShelfDetail))
            .check(matches(withText("Novel")))

        Thread.sleep(500L)
        onView(withId(R.id.btnBackShelves)).perform(click())

        // Delete
        Thread.sleep(2000L)
        onView(withId(R.id.rvShelvesList))
            .perform(RecyclerViewActions.actionOnItemAtPosition<ShelvesListViewHolder>(0, click()))

        onView(withId(R.id.btnShelfDetailOption)).perform(click())
        onView(withId(R.id.btnBottomSheetDelete)).perform(click())
        onView(withText("Yes"))
            .perform(click())
    }
}

// Form chatGPT
fun selectTabAtPosition(position: Int): ViewAction {
    return object : ViewAction {
        override fun getConstraints(): Matcher<View> {
            return Matchers.allOf(isDisplayed(), isAssignableFrom(TabLayout::class.java))
        }

        override fun getDescription(): String {
            return "Select tab at position $position"
        }

        override fun perform(uiController: UiController?, view: View?) {
            if (view is TabLayout) {
                val tab = view.getTabAt(position)
                tab?.select()
            }
        }
    }
}

class MyViewAction {
    companion object {
        fun clickOnSameView(): ViewAction {
            return object : ViewAction {
                override fun getConstraints(): Matcher<View> {
                    return CoreMatchers.allOf(isDisplayed(), isAssignableFrom(View::class.java))
                }

                override fun getDescription(): String {
                    return "Click on the desired view"
                }

                override fun perform(uiController: UiController?, view: View?) {
                    // Perform the click action on the desired view
                    view?.findViewById<View>(R.id.btnOption)?.performClick()
                }
            }
        }
    }
}

