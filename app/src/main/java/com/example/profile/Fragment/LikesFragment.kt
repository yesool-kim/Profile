package com.example.profile.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.profile.R
import com.bumptech.glide.RequestManager
import com.example.profile.Api.Response.ApiResponse
import com.example.profile.Api.Response.WatchSell
import com.example.profile.MainViewModel
import kotlinx.android.synthetic.main.contents_list.view.*
import kotlinx.android.synthetic.main.fragment_likes.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

lateinit var likeAdapter: LikesFragment.LikeAdapter

/**
 * A simple [Fragment] subclass.
 * Use the [LikesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LikesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        mainViewModel.requestInfo("popular/weekly?skip=0&limit=100&withoutFree=true")
        mainViewModel.resultLiveData.observe(this){image->
            likeRecyclerView.adapter = LikeAdapter(image,Glide.with(this))
        }
//        likeAdapter = LikeAdapter(it,Glide.with(this))
//        likeRecyclerView.adapter = likeAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_likes, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LikesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LikesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    class LikeAdapter(val apiResponse: ApiResponse, val requestManager: RequestManager) :
        RecyclerView.Adapter<LikeAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikeAdapter.ViewHolder {
            val v =
                LayoutInflater.from(parent.context).inflate(R.layout.contents_list, parent, false)
            return ViewHolder(v)
        }

        override fun getItemCount(): Int {
            return apiResponse.watchSells.count()
        }

        override fun onBindViewHolder(holder: LikeAdapter.ViewHolder, position: Int) {
             holder.bindItem(apiResponse.watchSells[position],requestManager)
        }

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val name = itemView.findViewById<TextView>(R.id.watchText)
            val color = itemView.findViewById<View>(R.id.watchColor)

            fun bindItem(watchSell: WatchSell, requestManager: RequestManager) {
                name?.text = watchSell.title
                requestManager
                    .load("http://mrtimemaker.com/"+watchSell.watch.images.preview)
                    .into(itemView.watchImage)
            }
        }

    }
}