package com.example.demoproject2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demoproject2.R
import com.example.demoproject2.adapters.ChatMessageAdapter
import com.example.demoproject2.databinding.PersonChatFragmentBinding
import com.example.demoproject2.databinding.SampleBottomSheetBinding
import com.example.demoproject2.interfaces.ChatViewInterface
import com.example.demoproject2.interfaces.onClickInterface
import com.example.demoproject2.models.ChatModel
import com.example.demoproject2.viewModels.ChatViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class OrganizationChatFragment : Fragment() {
    val mViewModel: ChatViewModel by viewModels()
    private lateinit var optionsBottomDialog: BottomSheetDialog
    private var mPosition = 0
    private var isLoading: Boolean = false
    private var data=ArrayList<ChatModel>()
    private lateinit var chatAdapter: ChatMessageAdapter
    private lateinit var chatBinding:PersonChatFragmentBinding
    private lateinit var sampleBottomSheetBinding: SampleBottomSheetBinding
    companion object {
        private var tabSetViewInterface: ChatViewInterface? = null

        fun newInstance(mInterface: ChatViewInterface): PersonChatFragment {
            tabSetViewInterface = mInterface
            return PersonChatFragment()
        }
    }

    private var currentPage = 1


    private fun setChat(data: ArrayList<ChatModel>) {
        chatAdapter = ChatMessageAdapter(cardItemClickLister,data)
        val manager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        chatBinding.chatRcv.layoutManager = manager
        chatBinding.chatRcv.adapter = chatAdapter
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
        mViewModel.getChatData(currentPage)
        recyclerViewOnScroll()
        setBottomSheet()
        setBottomSheetClickListeners()
        mViewModel.chatObserver.observe(viewLifecycleOwner){
            setChat(it)
        }

    }
    private val cardItemClickLister = object : onClickInterface {
        override fun onClickItem(index: Int) {
            mPosition = index
            optionsBottomDialog.show()
        }
    }
    private fun recyclerViewOnScroll() {

        chatBinding.chatRcv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() ==
                        data.size-1) {
                        if((mViewModel.metaData.value?.total_pages
                                ?: 1) > (mViewModel.metaData.value?.current_page ?: 1)
                        ) {
                            currentPage++
                            mViewModel.getChatData(currentPage)
                            isLoading = true
                        }
                        else{
                            Toast.makeText(requireContext(),"No More Data", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        })
    }
    private fun setBottomSheet() {
        optionsBottomDialog =
            BottomSheetDialog(requireContext(), R.style.CustomBottomSheetDialogTheme)
        sampleBottomSheetBinding= SampleBottomSheetBinding.inflate(layoutInflater)
        optionsBottomDialog.setContentView(sampleBottomSheetBinding.root)
    }
    private fun setBottomSheetClickListeners() {
        sampleBottomSheetBinding.apply {
            optionsBottomDialog.apply {
                moveAsClientContainer.setOnClickListener {
                    dismiss()
                }
                deleteContainer.setOnClickListener {
                    dismiss()
                }
                btnCancel.setOnClickListener {
                    dismiss()
                }
            }
        }
    }
}