package com.example.profile

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.profile.Api.ApiRepository
import com.example.profile.Fragment.LikesFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_tab.*
import kotlinx.android.synthetic.main.custom_tab.view.*
import kotlinx.android.synthetic.main.fragment_likes.*


class MainActivity : AppCompatActivity() {

    private var tabTextList = arrayListOf("Likes", "My Designs", "Purchased")
    private var tabNumberList = arrayListOf("21", "19", "5")

    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ApiRepository.init(this)
        //툴바
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val ab = supportActionBar!!
        ab.setDisplayHomeAsUpEnabled(true)

        //계정 정보
        NameText.text = "BICHON"
        FollowText.text = "26 Followers · 12 Followings"
//        Log.d("!!!!", "tabNumberList: ${tabNumberList}")
        //탭
        init()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun init() {
        Viewpager.adapter = TabPagerAdapter(this)

        Viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.d("TEST onPageSelected!!", position.toString())
                numberText.setTextColor(Color.parseColor("#FFFFFF"))
                tabText.setTextColor(Color.parseColor("#FFFFFF"))
            }
        })

        TabLayoutMediator(TabLayout, Viewpager) { tab, position ->
            val view = LayoutInflater.from(applicationContext).inflate(R.layout.custom_tab, null)
            tab.customView = view
            view.numberText?.text = tabNumberList[position]
            view.tabText?.text = tabTextList[position]
        }.attach()
    }
}
