package com.example.demoproject2.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demoproject2.R
import com.example.demoproject2.adapters.ChatMessageAdapter
import com.example.demoproject2.databinding.PersonChatFragmentBinding
import com.example.demoproject2.interfaces.ChatViewInterface
import com.example.demoproject2.models.ChatModel
import com.example.demoproject2.viewModels.ChatViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.person_chat_fragment.*
@AndroidEntryPoint
class OrganizationChatFragment : Fragment() {
    val mViewModel: ChatViewModel by viewModels()
    //region Properties
    lateinit var chatAdapter: ChatMessageAdapter
    lateinit var chatBinding: PersonChatFragmentBinding
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
    ): View {
        chatBinding=PersonChatFragmentBinding.inflate(layoutInflater)
        return chatBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.chatObserver.observe(viewLifecycleOwner){
            setChat(it)
        }
    }
    private fun setChat(data: ArrayList<ChatModel>) {
        chatAdapter = ChatMessageAdapter(data)
        val manager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        chatBinding.chatRcv.layoutManager = manager
        chatBinding.chatRcv.adapter = chatAdapter
    }



    override fun onDestroy() {
        super.onDestroy()

    }


}