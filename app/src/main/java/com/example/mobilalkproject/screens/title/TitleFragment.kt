package com.example.mobilalkproject.screens.title

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.mobilalkproject.MainActivity
import com.example.mobilalkproject.R
import com.example.mobilalkproject.databinding.FragmentTitleBinding
import com.example.mobilalkproject.network.ApiService
import com.example.mobilalkproject.network.NetActivity
import com.example.mobilalkproject.network.PostModel
import com.example.mobilalkproject.network.ServiceGenerator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TitleFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val binding: FragmentTitleBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_title, container, false
        )

        binding.playButton.setOnClickListener {

            if (binding.username.text!!.isNotEmpty()) {
                val action = TitleFragmentDirections.actionTitleFragmentToGameFragment()
                action.name = binding.username.text.toString()
                findNavController().navigate(action)
            } else {
                Toast.makeText(context, "Please enter your name!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.netButton.setOnClickListener {
            val intent = Intent(context, NetActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }
}