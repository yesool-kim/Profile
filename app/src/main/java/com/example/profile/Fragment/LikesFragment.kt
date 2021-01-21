package com.example.profile.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.bumptech.glide.Glide
import com.example.profile.Api.Response.ApiResponse
import com.example.profile.R
import com.example.profile.MainViewModel
import com.example.profile.ProfileRecyclerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_tab.*
import kotlinx.android.synthetic.main.fragment_likes.*
import kotlin.properties.Delegates

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LikesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LikesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val params = mutableMapOf<String, Any>()
        params["limit"] = 30
        params["page"] = 1
        params["includeEvent"] = true
        mainViewModel.requestSellsWatch(params)
        mainViewModel.resultLiveData.observe(this) { watch ->
            likeRecyclerView.adapter = ProfileRecyclerAdapter(watch, Glide.with(this))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_likes, container, false)
    }



}