package com.example.demoproject2.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.demoproject2.SharedPreferences.MySharedPreferences
import com.example.demoproject2.adapters.OnBoardingAdapter
import com.example.demoproject2.databinding.ActivityOnBoardingBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OnBoardActivity : AppCompatActivity() {
    lateinit var binding: ActivityOnBoardingBinding
    @Inject
    lateinit var mUserDataStore: MySharedPreferences
    private var viewPager: ViewPager? = null
    private var myViewPagerAdapter: OnBoardingAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding=ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViewPager()

    }
    private fun setUpViewPager() {
        viewPager = binding.viewPager
        myViewPagerAdapter = OnBoardingAdapter(supportFragmentManager)
        viewPager?.adapter = myViewPagerAdapter
        binding.indicator.setViewPager(viewPager)
        binding.submitBv.setOnClickListener {
            if(mUserDataStore.isuserlogin)
            {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
            else {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

    }
}