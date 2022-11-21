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
import javax.inject.Inject
@AndroidEntryPoint
class PersonChatFragment : Fragment() {
    val mViewModel: ChatViewModel by viewModels()


    lateinit var chatAdapter: ChatMessageAdapter
    lateinit var chatBinding:PersonChatFragmentBinding
    companion object {
        private var tabSetViewInterface: ChatViewInterface? = null

        fun newInstance(mInterface: ChatViewInterface): PersonChatFragment {
            tabSetViewInterface = mInterface
            return PersonChatFragment()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    private fun setChat(data: ArrayList<ChatModel>) {
        chatAdapter = ChatMessageAdapter(data)
        val manager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        chatBinding.chatRcv.layoutManager = manager
        chatBinding.chatRcv.adapter = chatAdapter
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        chatBinding=PersonChatFragmentBinding.inflate(layoutInflater)
        return chatBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.getChatData(1)
        mViewModel.chatObserver.observe(viewLifecycleOwner){
            setChat(it)
            println(11111111111111111)
        }

    }
    override fun onDestroy() {
        super.onDestroy()

    }


}