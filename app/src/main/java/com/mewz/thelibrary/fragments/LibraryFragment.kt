package com.mewz.thelibrary.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import com.mewz.thelibrary.R
import com.mewz.thelibrary.activities.NewShelfActivity
import com.mewz.thelibrary.databinding.FragmentLibraryBinding
import com.mewz.thelibrary.utils.libraryTab

class LibraryFragment : Fragment() {

    private lateinit var binding: FragmentLibraryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_library, container, false)

        binding = FragmentLibraryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpLibraryTabLayout()
        setUpListeners()
    }

    private fun setUpListeners() {
        binding.tlBookLibrary.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if(tab?.position == 0){
                    binding.btnCreateShelf.visibility = View.GONE
                }else{
                    binding.btnCreateShelf.visibility = View.VISIBLE
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        binding.btnCreateShelf.setOnClickListener {
            startActivity(context?.let { NewShelfActivity.newIntent(it) })
        }
    }

    private fun setUpLibraryTabLayout() {

        childFragmentManager.beginTransaction()
            .add(R.id.fragmentOne, LibraryYourBookFragment())
            .commit()

        libraryTab.forEach{
            binding.tlBookLibrary.newTab().apply {
                text = it
                binding.tlBookLibrary.addTab(this)
            }
        }

        binding.tlBookLibrary.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null){
                    when(tab.position){
                        0 -> {
                            switchFragment(LibraryYourBookFragment())
                        }
                        1 -> {
                            switchFragment(LibraryYourShelvesFragment())
                        }
                    }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

    }

    private fun switchFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(R.id.fragmentOne, fragment)
            .commit()
    }


}