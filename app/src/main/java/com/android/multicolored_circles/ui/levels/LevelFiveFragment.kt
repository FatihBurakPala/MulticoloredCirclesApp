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
import com.android.multicolored_circles.databinding.FragmentLevelFiveBinding
import com.android.multicolored_circles.databinding.FragmentLevelTwoBinding
import com.android.multicolored_circles.viewmodel.MyViewModel

class LevelFiveFragment : Fragment() {

    private var _binding: FragmentLevelFiveBinding? = null
    private val binding get() = _binding!!
    private lateinit var myViewModel: MyViewModel

    private var falseAnswerLock = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentLevelFiveBinding.inflate(inflater, container, false)
        myViewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)

        val vibration = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        /* -----------------------------------CountDown Timer----------------------------------- */
        val countDownTimer = object: CountDownTimer(30000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                binding.timerText5.text = "${millisUntilFinished / 1000}"
            }
            override fun onFinish() {
                binding.apply {
                    constLevel5.setBackgroundResource(R.drawable.back_white)
                    lvlNum5.isVisible = true
                    timerText5.isVisible = false
                    timerNumber5.isVisible = false
                    falseAnswerLock = true
                }
            }
        }

        binding.apply {

            /* -------------------------------------IMAGES------------------------------------- */
            if (myViewModel.language.value == true) {
                lvlNum5.setImageResource(R.drawable.level5_en)
                timerNumber5.setImageResource(R.drawable.level5_en)
                backMenu5.setImageResource(R.drawable.menu_en)
            } else {
                lvlNum5.setImageResource(R.drawable.level5_ru)
                timerNumber5.setImageResource(R.drawable.level5_ru)
                backMenu5.setImageResource(R.drawable.menu_ru)
            }

            /* ----------------------------------FALSE ANSWERS---------------------------------- */
            circle5a.setOnClickListener {
                constLevel5.setBackgroundResource(R.drawable.back_dark)
                lvlNum5.isVisible = false
                timerText5.isVisible = true
                timerNumber5.isVisible = true
                falseAnswerLock = false
                countDownTimer.start()
            }
            circle5c.setOnClickListener {
                constLevel5.setBackgroundResource(R.drawable.back_dark)
                lvlNum5.isVisible = false
                timerText5.isVisible = true
                timerNumber5.isVisible = true
                falseAnswerLock = false
                countDownTimer.start()
            }
            circle5d.setOnClickListener {
                constLevel5.setBackgroundResource(R.drawable.back_dark)
                lvlNum5.isVisible = false
                timerText5.isVisible = true
                timerNumber5.isVisible = true
                falseAnswerLock = false
                countDownTimer.start()
            }

            /* -----------------------------------TRUE ANSWER----------------------------------- */
            circle5b.setOnClickListener {
                if(falseAnswerLock) {
                    if (myViewModel.vibration.value == true) {
                        vibration.vibrate(500)
                    }
                    popUpMenu5.isVisible = true
                }
            }

            /* ------------------------------------POPUP MENU------------------------------------ */
            backMenu5.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_levelFiveFragment_to_menuFragment)
            }
        }
        return binding.root
    }
}