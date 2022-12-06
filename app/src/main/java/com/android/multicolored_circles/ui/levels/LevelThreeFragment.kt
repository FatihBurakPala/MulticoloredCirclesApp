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
import com.android.multicolored_circles.databinding.FragmentLevelThreeBinding
import com.android.multicolored_circles.viewmodel.MyViewModel

class LevelThreeFragment : Fragment() {

    private var _binding: FragmentLevelThreeBinding? = null
    private val binding get() = _binding!!
    private lateinit var myViewModel: MyViewModel

    private var falseAnswerLock = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentLevelThreeBinding.inflate(inflater, container, false)
        myViewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)

        val vibration = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        /* -----------------------------------CountDown Timer----------------------------------- */
        val countDownTimer = object: CountDownTimer(30000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                binding.timerText3.text = "${millisUntilFinished / 1000}"
            }
            override fun onFinish() {
                binding.apply {
                    constLevel3.setBackgroundResource(R.drawable.back_white)
                    lvlNum3.isVisible = true
                    timerText3.isVisible = false
                    timerNumber3.isVisible = false
                    falseAnswerLock = true
                }
            }
        }

        binding.apply {

            /* -------------------------------------IMAGES------------------------------------- */
            if (myViewModel.language.value == true) {
                lvlNum3.setImageResource(R.drawable.level3_en)
                timerNumber3.setImageResource(R.drawable.level3_en)
                nextLevel3.setImageResource(R.drawable.next_level_en)
                backMenu3.setImageResource(R.drawable.menu_en)
            } else {
                lvlNum3.setImageResource(R.drawable.level3_ru)
                timerNumber3.setImageResource(R.drawable.level3_ru)
                nextLevel3.setImageResource(R.drawable.next_level_ru)
                backMenu3.setImageResource(R.drawable.menu_ru)
            }

            /* ----------------------------------FALSE ANSWERS---------------------------------- */
            circle3a.setOnClickListener {
                constLevel3.setBackgroundResource(R.drawable.back_dark)
                lvlNum3.isVisible = false
                timerText3.isVisible = true
                timerNumber3.isVisible = true
                falseAnswerLock = false
                countDownTimer.start()
            }
            circle3c.setOnClickListener {
                constLevel3.setBackgroundResource(R.drawable.back_dark)
                lvlNum3.isVisible = false
                timerText3.isVisible = true
                timerNumber3.isVisible = true
                falseAnswerLock = false
                countDownTimer.start()
            }
            circle3d.setOnClickListener {
                constLevel3.setBackgroundResource(R.drawable.back_dark)
                lvlNum3.isVisible = false
                timerText3.isVisible = true
                timerNumber3.isVisible = true
                falseAnswerLock = false
                countDownTimer.start()
            }

            /* -----------------------------------TRUE ANSWER----------------------------------- */
            circle3b.setOnClickListener {
                if(falseAnswerLock) {
                    if (myViewModel.vibration.value == true) {
                        vibration.vibrate(500)
                    }
                    popUpMenu3.isVisible = true
                    myViewModel.lock4.value = true
                }
            }

            /* ------------------------------------POPUP MENU------------------------------------ */
            nextLevel3.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_levelThreeFragment_to_levelFourFragment)
            }
            backMenu3.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_levelThreeFragment_to_menuFragment)
            }
        }
        return binding.root
    }
}