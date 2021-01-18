package com.example.profile.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.profile.R
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.contents_list.view.*

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
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
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
        
        likeAdapter = LikeAdap
    }

    class LikeAdapter(val requestManager:RequestManager): RecyclerView.Adapter<LikeAdapter.ViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikeAdapter.ViewHolder {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.contents_list,parent,false)
            return ViewHolder(v)
        }

        override fun getItemCount(): Int {
            TODO("Not yet implemented")
        }

        override fun onBindViewHolder(holder: LikeAdapter.ViewHolder, position: Int) {

        }

        class ViewHolder(view:View):RecyclerView.ViewHolder(view){
            fun bindItem(requestManager: RequestManager){
                requestManager
                    .load("https://static-cdn.jtvnw.net/jtv_user_pictures/89e29e2e-f165-40e6-bc0c-d42205935216-profile_image-300x300.png")
                    .into(itemView.watchImage)
            }
        }

    }
}