package com.example.mobilalkproject.screens.score

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mobilalkproject.R
import com.example.mobilalkproject.database.Scores
import com.example.mobilalkproject.database.ScoresActivity
import com.example.mobilalkproject.database.ScoresDatabase
import com.example.mobilalkproject.databinding.FragmentGameBinding
import com.example.mobilalkproject.databinding.FragmentScoreBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ScoreFragment : Fragment() {

    private lateinit var scoresDb: ScoresDatabase

    private lateinit var binding: FragmentScoreBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding= DataBindingUtil.inflate(
            inflater, R.layout.fragment_score, container, false)

        val scoreArgs by navArgs<ScoreFragmentArgs>()
        binding.displayResult.text = "${scoreArgs.name} scored ${scoreArgs.score}/10!"

        //writeData()

        binding.againButton.setOnClickListener{view: View->

            view.findNavController().navigate(R.id.action_scoreFragment_to_titleFragment)
        }

        binding.resultsButton.setOnClickListener{view: View->

            val intent = Intent(context, ScoresActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    private fun writeData(){

        val scoreArgs by navArgs<ScoreFragmentArgs>()

        val username = scoreArgs.name
        val score = "${scoreArgs.score}/10"
        val scores = Scores(null,username, score)
        GlobalScope.launch(Dispatchers.IO) {
            scoresDb.scoresDao().insert(scores)
        }
    }

}