package com.example.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var tabTextList = arrayListOf("21 Likes","My Designs","Purchassed")
//    private var tabNumberList = arrayListOf("21","19","5")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //툴바
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val ab = supportActionBar!!
        ab.setDisplayHomeAsUpEnabled(true)
        //계정 정보
        NameText.text = "BICHON"
        FollowText.text = "26 Followers · 12 Followings"

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

    private fun init(){
        Viewpager.adapter = TabPagerAdapter(this)

        TabLayoutMediator(TabLayout, Viewpager){
            tab, position ->
            tab.text = tabTextList[position]
        }.attach()
    }
}
