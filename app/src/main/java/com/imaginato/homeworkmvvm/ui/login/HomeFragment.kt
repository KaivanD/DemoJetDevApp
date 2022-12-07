package com.imaginato.homeworkmvvm.ui.login

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.imaginato.homeworkmvvm.data.local.login.User
import com.imaginato.homeworkmvvm.data.local.login.UserDatabase
import com.imaginato.homeworkmvvm.databinding.HomeFragmentBinding
import java.io.IOException
import java.util.concurrent.Executors


class HomeFragment : Fragment() {

    private var _binding: HomeFragmentBinding? = null
    val myExecutor = Executors.newSingleThreadExecutor()
    val myHandler = Handler(Looper.getMainLooper())

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db = Room.databaseBuilder(
            requireActivity(),
            UserDatabase::class.java, "database-name"
        ).build()

        try {
            myExecutor.execute {
                val userDao = db.userDao
                val user: User = userDao.getAll()
                myHandler.post {
                    binding.textviewSecondId.text = user.userId
                    binding.textviewSecondName.text = user.userName
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        //Set Login User Details
//        val preference=
//            activity?.getSharedPreferences(resources.getString(R.string.app_name), Context.MODE_PRIVATE)
//        if(preference!=null)
//        {
//            binding.textviewSecondId.text = ""+preference.getString("id", "")
//            binding.textviewSecondName.text = ""+preference.getString("name", "")
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}