package com.example.demoproject2.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.demoproject2.R
import com.example.demoproject2.activities.HomeActivity


class OnBoardingFragment : Fragment() {
    var layout: Int = R.layout.onboarding_first
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.takeIf { it.containsKey("LAYOUT") }?.apply {
            layout = getInt("LAYOUT")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        var view = inflater.inflate(layout, container, false)
        view.findViewById<Button>(R.id.submit_bv).setOnClickListener {
            val intent = Intent(requireContext(), HomeActivity::class.java)
            startActivity(intent)

        }
        return view
    }
}