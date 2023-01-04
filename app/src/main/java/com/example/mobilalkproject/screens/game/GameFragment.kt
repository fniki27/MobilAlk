package com.example.mobilalkproject.screens.game

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mobilalkproject.Question
import com.example.mobilalkproject.QuestionList
import com.example.mobilalkproject.R
import com.example.mobilalkproject.databinding.FragmentGameBinding

class GameFragment : Fragment(), View.OnClickListener {

    private lateinit var viewModel: GameViewModel
    private lateinit var mQuestionsList : ArrayList<Question>

    private  var mSelectedPosition: Int = 0
    private var mCorrectAnswer : Int = 0
    var mCurrentPosition: Int = 1

    private lateinit var binding: FragmentGameBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_game, container, false)


        mQuestionsList = QuestionList.getQuestion()


        binding.optionOne.setOnClickListener(this)
        binding.optionTwo.setOnClickListener(this)
        binding.optionThree.setOnClickListener(this)
        binding.optionFour.setOnClickListener(this)
        binding.submitButton.setOnClickListener(this)

        setQuestion()

        viewModel = ViewModelProvider(this)[GameViewModel::class.java]
        binding.gameViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.eventGameFinish.observe(viewLifecycleOwner, Observer { hasFinished ->
            if (hasFinished) questionFinished()
        })

        return binding.root

    }

    private fun questionFinished() {
        if(mCurrentPosition < mQuestionsList.size) {
            mCurrentPosition++
            setQuestion()
            viewModel.startTimer()
            viewModel.onGameFinishComplete()
        } else {
            val action =
                GameFragmentDirections.actionGameFragmentToScoreFragment()
            val nameOfPlayer by navArgs<GameFragmentArgs>()
            action.score = mCorrectAnswer
            action.name = nameOfPlayer.name
            findNavController().navigate(action)
            viewModel.onGameFinishComplete()
        }

    }

    private fun setQuestion() {

        val questions: Question = mQuestionsList[mCurrentPosition -1]

        binding.flagImage.setImageResource(questions.flagImage)
        binding.optionOne.text = questions.optionOne
        binding.optionTwo.text = questions.optionTwo
        binding.optionThree.text = questions.optionThree
        binding.optionFour.text = questions.optionFour

        binding.progressbar.progress = mCurrentPosition
        binding.progressNumber.text = "$mCurrentPosition" + "/" + binding.progressbar.max

        defaultAppearance()
        binding.submitButton.text = "SUBMIT"

    }

    private fun defaultAppearance() {

        val options = ArrayList<TextView>()
        options.add(0, binding.optionOne)
        options.add(1, binding.optionTwo)
        options.add(2, binding.optionThree)
        options.add(3, binding.optionFour)


        for (option in options) {


            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = context?.let {
                ContextCompat.getDrawable(it, R.drawable.default_option)

            }

        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {

            R.id.optionOne -> {

                selectedOptionView(binding.optionOne, 1)

            }

            R.id.optionTwo -> {

                selectedOptionView(binding.optionTwo, 2)


            }

            R.id.optionThree -> {

                selectedOptionView(binding.optionThree, 3)


            }

            R.id.optionFour -> {

                selectedOptionView(binding.optionFour, 4)

            }

            R.id.submitButton -> {

                viewModel.stopTimer()

                if (mSelectedPosition == 0) {

                    mCurrentPosition++

                    when {
                        mCurrentPosition <= mQuestionsList.size -> {

                            setQuestion()
                            viewModel.startTimer()

                        }
                        else -> {
                            val action =
                                GameFragmentDirections.actionGameFragmentToScoreFragment()
                            val nameOfPlayer by navArgs<GameFragmentArgs>()
                            action.score = mCorrectAnswer
                            action.name = nameOfPlayer.name
                            findNavController().navigate(action)
                        }
                    }
                } else {
                    // if user selected an option
                    // we will check if its correct or incorrect

                    val question = mQuestionsList[mCurrentPosition - 1]

                    // if selected position 1..4 matches value at the correct answer
                    // correct answer values are 1..4 if the value matches or not matches
                    if (question.correctAnswer != mSelectedPosition) {

                        answerView(mSelectedPosition, R.drawable.wrong_option)
                    } else {
                        mCorrectAnswer++


                    }


                    answerView(question.correctAnswer, R.drawable.right_option)

                    if (mCurrentPosition == mQuestionsList.size) {

                        binding.submitButton.text = "FINISH"

                    } else {

                        binding.submitButton.text = "NEXT"
                    }

                    mSelectedPosition = 0

                }


            }


        }
    }

    private fun selectedOptionView(tv: TextView, selectedPosition: Int) {
        //reset text views
        defaultAppearance()

        mSelectedPosition = selectedPosition



        tv.setTextColor(Color.parseColor("#363A43"))
        //default appearance
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background =
            context?.let { ContextCompat.getDrawable(it, R.drawable.selected_option) }


    }

    private fun answerView(mSelectedPosition: Int, drawableView: Int) {

        when (mSelectedPosition) {

            1 -> {

                binding.optionOne.background =
                    context?.let { ContextCompat.getDrawable(it, drawableView) }

            }

            2 -> {

                binding.optionTwo.background =
                    context?.let { ContextCompat.getDrawable(it, drawableView) }


            }

            3 -> {

                binding.optionThree.background =
                    context?.let { ContextCompat.getDrawable(it, drawableView) }


            }

            4 -> {

                binding.optionFour.background =
                    context?.let { ContextCompat.getDrawable(it, drawableView) }


            }
        }
    }
}