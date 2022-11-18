package com.example.demoproject2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.demoproject2.R




class SearchFragment : Fragment() {



    fun newInstance(fromMessages: Boolean): SearchFragment {
        val args = Bundle()
        args.putBoolean("SEARCH", fromMessages)
        val f = SearchFragment()
        f.setArguments(args)
        return f
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var fromInMessage = arguments?.getBoolean("SEARCH") ?: false

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }



}