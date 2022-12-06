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
import com.android.multicolored_circles.databinding.FragmentLevelTwoBinding
import com.android.multicolored_circles.viewmodel.MyViewModel

class LevelTwoFragment : Fragment() {

    private var _binding: FragmentLevelTwoBinding? = null
    private val binding get() = _binding!!
    private lateinit var myViewModel: MyViewModel

    private var falseAnswerLock = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentLevelTwoBinding.inflate(inflater, container, false)
        myViewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)

        val vibration = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        /* -----------------------------------CountDown Timer----------------------------------- */
        val countDownTimer = object: CountDownTimer(30000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                binding.timerText2.text = "${millisUntilFinished / 1000}"
            }
            override fun onFinish() {
                binding.apply {
                    constLevel2.setBackgroundResource(R.drawable.back_white)
                    lvlNum2.isVisible = true
                    timerText2.isVisible = false
                    timerNumber2.isVisible = false
                    falseAnswerLock = true
                }
            }
        }

        binding.apply {

            /* -------------------------------------IMAGES------------------------------------- */
            if (myViewModel.language.value == true) {
                lvlNum2.setImageResource(R.drawable.level2_en)
                timerNumber2.setImageResource(R.drawable.level2_en)
                nextLevel2.setImageResource(R.drawable.next_level_en)
                backMenu2.setImageResource(R.drawable.menu_en)
            } else {
                lvlNum2.setImageResource(R.drawable.level2_ru)
                timerNumber2.setImageResource(R.drawable.level2_ru)
                nextLevel2.setImageResource(R.drawable.next_level_ru)
                backMenu2.setImageResource(R.drawable.menu_ru)
            }

            /* ----------------------------------FALSE ANSWERS---------------------------------- */
            circle2a.setOnClickListener {
                constLevel2.setBackgroundResource(R.drawable.back_dark)
                lvlNum2.isVisible = false
                timerText2.isVisible = true
                timerNumber2.isVisible = true
                falseAnswerLock = false
                countDownTimer.start()
            }
            circle2b.setOnClickListener {
                constLevel2.setBackgroundResource(R.drawable.back_dark)
                lvlNum2.isVisible = false
                timerText2.isVisible = true
                timerNumber2.isVisible = true
                falseAnswerLock = false
                countDownTimer.start()
            }
            circle2c.setOnClickListener {
                constLevel2.setBackgroundResource(R.drawable.back_dark)
                lvlNum2.isVisible = false
                timerText2.isVisible = true
                timerNumber2.isVisible = true
                falseAnswerLock = false
                countDownTimer.start()
            }

            /* -----------------------------------TRUE ANSWER----------------------------------- */
            circle2d.setOnClickListener {
                if(falseAnswerLock) {
                    if (myViewModel.vibration.value == true) {
                        vibration.vibrate(500)
                    }
                    popUpMenu2.isVisible = true
                    myViewModel.lock3.value = true
                }
            }

            /* ------------------------------------POPUP MENU------------------------------------ */
            nextLevel2.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_levelTwoFragment_to_levelThreeFragment)
            }
            backMenu2.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_levelTwoFragment_to_menuFragment)
            }
        }
        return binding.root
    }
}
