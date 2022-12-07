package com.example.demoproject2.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.demoproject2.dialoges.CustomProgressDialog
import com.example.demoproject2.databinding.ActivityLoginBinding
import com.example.demoproject2.viewModels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
     val mViewModel: LoginViewModel by viewModels()
    lateinit var viewBinding:ActivityLoginBinding
    private lateinit var dialog: CustomProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        dialog= CustomProgressDialog(this)
       viewBinding.viewModel=mViewModel
        viewModelObservers()


    }

    private fun viewModelObservers() {
        mViewModel.apply {
            logInObserver.observe(this@LoginActivity){
                if(it){
                    startActivity(Intent(this@LoginActivity,HomeActivity::class.java))
                    finish()
                }

            }
            messageObserver.observe(this@LoginActivity) {
                Toast.makeText(
                    this@LoginActivity, it.toString(), Toast.LENGTH_SHORT
                ).show()
            }
            errorObserver.observe(this@LoginActivity){
               if(it=="Unprocessable Entity")
                Toast.makeText(
                    this@LoginActivity,"Invalid Email and/or Password", Toast.LENGTH_SHORT
                ).show()
                else
               {
                   Toast.makeText(
                       this@LoginActivity, it.toString(), Toast.LENGTH_SHORT
                   ).show()
               }
            }
            showErrorDialog.observe(this@LoginActivity){
                Toast.makeText(this@LoginActivity,it.toString(),Toast.LENGTH_SHORT).show()
            }
            isLoading.observe(this@LoginActivity){
                loadingObserver(it)
            }
        }
    }

    private fun showProgressDialog() {

        if (!dialog.isShowing)
            dialog.show()
    }

    private fun dismissProgressDialog() {
        if (dialog.isShowing)
            dialog.dismiss()
    }
    private fun loadingObserver(it:Boolean) {
        try {
            if (it) {
                showProgressDialog()
            } else {
                dismissProgressDialog()
            }
        } catch (e: Exception) {

        }


    }
}