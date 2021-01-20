package com.example.profile

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.profile.Api.Response.ApiResponse
import com.example.profile.Api.Response.WatchSell
import kotlinx.android.synthetic.main.contents_list.view.*

class ProfileRecyclerAdapter(val apiResponse: ApiResponse, val requestManager: RequestManager) :
    RecyclerView.Adapter<ProfileRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProfileRecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.contents_list, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return apiResponse.watchSells.count()
    }

    override fun onBindViewHolder(holder: ProfileRecyclerAdapter.ViewHolder, position: Int) {
        holder.bindItem(apiResponse.watchSells[position], requestManager)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = itemView.findViewById<TextView>(R.id.watchText)
        val color = itemView.findViewById<View>(R.id.watchColor)

        fun bindItem(watchSell: WatchSell, requestManager: RequestManager) {
//            Log.d("!!!!", "${watchSell.watch.images.preview}")
            var colorToRGB = watchSell.watch.colors.colors.get(0)
//            Log.d("!!!!","${watchSell.watch.colors.colors}")
            //시계 타이틀
            name?.text = watchSell.title
            //시계 색상
            color?.setBackgroundColor(Color.rgb(colorToRGB[0], colorToRGB[1], colorToRGB[2]))
            //시계 이미지
            requestManager
                .load("http://mrtimemaker.com" + watchSell.watch.images.preview)
                .into(itemView.watchImage)
        }
    }
}