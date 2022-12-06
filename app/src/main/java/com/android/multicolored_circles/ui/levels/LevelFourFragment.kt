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
import com.android.multicolored_circles.databinding.FragmentLevelFourBinding
import com.android.multicolored_circles.viewmodel.MyViewModel

class LevelFourFragment : Fragment() {

    private var _binding: FragmentLevelFourBinding? = null
    private val binding get() = _binding!!
    private lateinit var myViewModel: MyViewModel

    private var falseAnswerLock = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentLevelFourBinding.inflate(inflater, container, false)
        myViewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)

        val vibration = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        /* -----------------------------------CountDown Timer----------------------------------- */
        val countDownTimer = object: CountDownTimer(30000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                binding.timerText4.text = "${millisUntilFinished / 1000}"
            }
            override fun onFinish() {
                binding.apply {
                    constLevel4.setBackgroundResource(R.drawable.back_white)
                    lvlNum4.isVisible = true
                    timerText4.isVisible = false
                    timerNumber4.isVisible = false
                    falseAnswerLock = true
                }
            }
        }

        binding.apply {

            /* -------------------------------------IMAGES------------------------------------- */
            if (myViewModel.language.value == true) {
                lvlNum4.setImageResource(R.drawable.level4_en)
                timerNumber4.setImageResource(R.drawable.level4_en)
                nextLevel4.setImageResource(R.drawable.next_level_en)
                backMenu4.setImageResource(R.drawable.menu_en)
            } else {
                lvlNum4.setImageResource(R.drawable.level4_ru)
                timerNumber4.setImageResource(R.drawable.level4_ru)
                nextLevel4.setImageResource(R.drawable.next_level_ru)
                backMenu4.setImageResource(R.drawable.menu_ru)
            }

            /* ----------------------------------FALSE ANSWERS---------------------------------- */
            circle4a.setOnClickListener {
                constLevel4.setBackgroundResource(R.drawable.back_dark)
                lvlNum4.isVisible = false
                timerText4.isVisible = true
                timerNumber4.isVisible = true
                falseAnswerLock = false
                countDownTimer.start()
            }
            circle4b.setOnClickListener {
                constLevel4.setBackgroundResource(R.drawable.back_dark)
                lvlNum4.isVisible = false
                timerText4.isVisible = true
                timerNumber4.isVisible = true
                falseAnswerLock = false
                countDownTimer.start()
            }
            circle4d.setOnClickListener {
                constLevel4.setBackgroundResource(R.drawable.back_dark)
                lvlNum4.isVisible = false
                timerText4.isVisible = true
                timerNumber4.isVisible = true
                falseAnswerLock = false
                countDownTimer.start()
            }

            /* -----------------------------------TRUE ANSWER----------------------------------- */
            circle4c.setOnClickListener {
                if(falseAnswerLock) {
                    if (myViewModel.vibration.value == true) {
                        vibration.vibrate(500)
                    }
                    popUpMenu4.isVisible = true
                    myViewModel.lock5.value = true
                }
            }

            /* ------------------------------------POPUP MENU------------------------------------ */
            nextLevel4.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_levelFourFragment_to_levelFiveFragment)
            }
            backMenu4.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_levelFourFragment_to_menuFragment)
            }
        }
        return binding.root
    }
}