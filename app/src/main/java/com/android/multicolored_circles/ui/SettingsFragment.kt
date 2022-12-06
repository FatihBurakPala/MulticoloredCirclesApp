package com.android.multicolored_circles.ui

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Vibrator
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.android.multicolored_circles.R
import com.android.multicolored_circles.databinding.FragmentSettingsBinding
import com.android.multicolored_circles.viewmodel.MyViewModel

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private lateinit var myViewModel: MyViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        myViewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)

        val vibration = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        binding.apply {

            /* -------------------------------IMAGES------------------------------- */
            myViewModel.language.observe(viewLifecycleOwner){ it ->

                /* ------------------English------------------ */
                if(it == true) {
                    myViewModel.sound.observe(viewLifecycleOwner) {
                        if(it == true) {
                            soundBtn.setImageResource(R.drawable.sound_off_en)
                        } else
                            soundBtn.setImageResource(R.drawable.sound_on_en)
                    }

                    myViewModel.vibration.observe(viewLifecycleOwner) {
                        if(it == true) {
                            vibrationBtn.setImageResource(R.drawable.vibration_off_en)
                        } else
                            vibrationBtn.setImageResource(R.drawable.vibration_on_en)
                    }

                    languageBtn.setImageResource(R.drawable.language_ru)
                    backMenuBtn.setImageResource(R.drawable.menu_en)
                }

                /* ------------------Russian------------------ */
                else {
                    myViewModel.sound.observe(viewLifecycleOwner) {
                        if(it == true) {
                            soundBtn.setImageResource(R.drawable.sound_off_ru)
                        } else
                            soundBtn.setImageResource(R.drawable.sound_on_ru)
                    }

                    myViewModel.vibration.observe(viewLifecycleOwner) {
                        if(it == true) {
                            vibrationBtn.setImageResource(R.drawable.vibration_off_ru)
                        } else
                            vibrationBtn.setImageResource(R.drawable.vibration_on_ru)
                    }

                    languageBtn.setImageResource(R.drawable.language_en)
                    backMenuBtn.setImageResource(R.drawable.menu_ru)
                }
            }

            /* -----------------------------------Sound On/Off----------------------------------- */
            soundBtn.setOnClickListener {
                myViewModel.setSound()
            }

            /* ---------------------------------Vibration On/Off--------------------------------- */
            vibrationBtn.setOnClickListener {
                if (myViewModel.vibration.value == false) {
                    vibration.vibrate(500)
                }
                myViewModel.setVibration()
            }

            /* -------------------------------------Language------------------------------------- */
            languageBtn.setOnClickListener {
                myViewModel.setLanguage()
            }

            /* ------------------------------------Back Menu------------------------------------ */
            backMenuBtn.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_settingsFragment_to_menuFragment)
            }
        }
        return binding.root
    }
}
