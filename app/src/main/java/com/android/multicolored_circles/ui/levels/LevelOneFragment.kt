package com.android.multicolored_circles.ui.levels

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Vibrator
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.android.multicolored_circles.R
import com.android.multicolored_circles.databinding.FragmentLevelOneBinding
import com.android.multicolored_circles.viewmodel.MyViewModel

class LevelOneFragment : Fragment() {

    private var _binding: FragmentLevelOneBinding? = null
    private val binding get() = _binding!!
    private lateinit var myViewModel: MyViewModel

    private var falseAnswerLock = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentLevelOneBinding.inflate(inflater, container, false)
        myViewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)

        val vibration = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        /* -----------------------------------CountDown Timer----------------------------------- */
        val countDownTimer = object: CountDownTimer(30000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                binding.timerText1.text = "${millisUntilFinished / 1000}"
            }
            override fun onFinish() {
                binding.apply {
                    constLevel1.setBackgroundResource(R.drawable.back_white)
                    lvlNum1.isVisible = true
                    timerText1.isVisible = false
                    timerNumber1.isVisible = false
                    falseAnswerLock = true
                }
            }
        }

        binding.apply {

            /* -------------------------------------IMAGES------------------------------------- */
            if (myViewModel.language.value == true) {
                lvlNum1.setImageResource(R.drawable.level1_en)
                timerNumber1.setImageResource(R.drawable.level1_en)
                nextLevel1.setImageResource(R.drawable.next_level_en)
                backMenu1.setImageResource(R.drawable.menu_en)
            } else {
                lvlNum1.setImageResource(R.drawable.level1_ru)
                timerNumber1.setImageResource(R.drawable.level1_ru)
                nextLevel1.setImageResource(R.drawable.next_level_ru)
                backMenu1.setImageResource(R.drawable.menu_ru)
            }

            /* ----------------------------------FALSE ANSWERS---------------------------------- */
            circle1a.setOnClickListener {
                constLevel1.setBackgroundResource(R.drawable.back_dark)
                lvlNum1.isVisible = false
                timerText1.isVisible = true
                timerNumber1.isVisible = true
                falseAnswerLock = false
                countDownTimer.start()
            }
            circle1b.setOnClickListener {
                constLevel1.setBackgroundResource(R.drawable.back_dark)
                lvlNum1.isVisible = false
                timerText1.isVisible = true
                timerNumber1.isVisible = true
                falseAnswerLock = false
                countDownTimer.start()
            }
            circle1d.setOnClickListener {
                constLevel1.setBackgroundResource(R.drawable.back_dark)
                lvlNum1.isVisible = false
                timerText1.isVisible = true
                timerNumber1.isVisible = true
                falseAnswerLock = false
                countDownTimer.start()
            }

            /* -----------------------------------TRUE ANSWER----------------------------------- */
            circle1c.setOnClickListener {
                if(falseAnswerLock) {
                    if (myViewModel.vibration.value == true) {
                        vibration.vibrate(500)
                    }
                    popUpMenu1.isVisible = true
                    myViewModel.lock2.value = true
                }
            }

            /* ------------------------------------POPUP MENU------------------------------------ */
            nextLevel1.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_levelOneFragment_to_levelTwoFragment)
            }
            backMenu1.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_levelOneFragment_to_menuFragment)
            }
        }
        return binding.root
    }
}
