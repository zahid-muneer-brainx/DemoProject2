package com.example.demoproject2.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.demoproject2.R
import com.example.demoproject2.databinding.ActivityLoginBinding
import com.example.demoproject2.viewModels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
     val mViewModel: LoginViewModel by viewModels()
    lateinit var viewBinding:ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewBinding.loginBtn.setOnClickListener {
            mViewModel.email=(viewBinding.userEmail.text.toString())
            mViewModel.password=(viewBinding.userPass.text.toString())
            mViewModel.signInApi()
            viewBinding.loginProgress.visibility=View.VISIBLE
        }
          mViewModel.logInObserver.observe(this){
                         if(it){
                             Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show()
                             startActivity(Intent(this@LoginActivity,HomeActivity::class.java))
                             finish()
                         }

          }
        mViewModel.messageObserver.observe(this){
            Toast.makeText(this,it.toString(),Toast.LENGTH_SHORT).show()
        }
        mViewModel.showErrorDialog.observe(this){
            Toast.makeText(this,it.toString(),Toast.LENGTH_SHORT).show()
            viewBinding.loginProgress.visibility=View.GONE
        }
    }
}