package com.example.demoproject2.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.demoproject2.R
import com.example.demoproject2.SharedPreferences.MySharedPreferences
import com.example.demoproject2.activities.OnBoardActivity
import com.example.demoproject2.databinding.FragmentLogoutBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class LogoutFragment : Fragment() {

    @Inject
    lateinit var mUserDataStore: MySharedPreferences

     lateinit var binding:FragmentLogoutBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.logout.setOnClickListener {
            mUserDataStore.isuserlogin=false
            startActivity(Intent(requireContext(),OnBoardActivity::class.java))
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentLogoutBinding.inflate(layoutInflater)
        return binding.root
    }

}