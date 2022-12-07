package com.imaginato.homeworkmvvm.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.imaginato.homeworkmvvm.R
import com.imaginato.homeworkmvvm.data.local.login.User
import com.imaginato.homeworkmvvm.data.local.login.UserDatabase
import com.imaginato.homeworkmvvm.data.remote.login.request.LoginRequest
import com.imaginato.homeworkmvvm.databinding.LoginFragmentBinding
import kotlin.concurrent.thread


class LoginFragment : Fragment() {

    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!
    lateinit var loginViewModel: LoginViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //binding login layout UI here
        _binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db = Room.databaseBuilder(
            requireActivity(),
            UserDatabase::class.java, "database-name"
        ).build()

        //Login Button click event and handle validation and webservice calling here
        binding.loginbtn.setOnClickListener {
            Log.d("LoginDatabinding", binding.editTextUserName.text.toString())
            val username: String = binding.editTextUserName.text.toString()
            val password: String = binding.editTextpassID.text.toString()
            //validate username and password
            loginViewModel.validateCredentials(username, password).observe(
                viewLifecycleOwner
            ) { t ->
                if (t.equals("Successful Login")) {
                    //after validate call api to check username and password is correct
                    loginViewModel.getLoginObservable(LoginRequest(password, username))
                        .observe(viewLifecycleOwner) { response ->
                            if (response != null && response.errorCode.equals("00") && response.data != null
                            ) {
                                //Success api call
                                val user = response.data
//                                val preference = activity?.getSharedPreferences(
//                                    resources.getString(R.string.app_name),
//                                    Context.MODE_PRIVATE
//                                )
//                                val editor = preference?.edit()
//                                if (editor != null && user != null) {
//                                    editor.putString("id", user.userId)
//                                    editor.putString("name", user.userName)
//                                    editor.commit()
//                                }

                                thread {
                                    val userDao = db.userDao
                                    userDao.insertUser(
                                        User(
                                            user.userId.toString(),
                                            user.userName.toString(),
                                            user.isDeleted!!
                                        )
                                    )
                                }


                                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)

                            } else {
                                ////Failed api call
                                Toast.makeText(activity, t, Toast.LENGTH_LONG).show()
                            }
                        }
                } else {
                    Toast.makeText(activity, t, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}