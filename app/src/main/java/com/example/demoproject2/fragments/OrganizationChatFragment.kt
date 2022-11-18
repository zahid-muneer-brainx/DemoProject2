package com.example.demoproject2.fragments



import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demoproject2.R
import com.example.demoproject2.interfaces.ChatViewInterface


class OrganizationChatFragment : Fragment() {
    companion object {
        private var tabSetViewInterface: ChatViewInterface? = null

        fun newInstance(mInterface: ChatViewInterface): OrganizationChatFragment {
            tabSetViewInterface = mInterface
            return OrganizationChatFragment()
        }
    }
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.person_chat_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }




    override fun onDestroy() {
        super.onDestroy()

    }


}