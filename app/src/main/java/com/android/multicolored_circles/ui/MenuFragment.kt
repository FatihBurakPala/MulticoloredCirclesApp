package com.android.multicolored_circles.ui

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Vibrator
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.android.multicolored_circles.R
import com.android.multicolored_circles.databinding.FragmentMenuBinding
import com.android.multicolored_circles.viewmodel.MyViewModel

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!
    private lateinit var myViewModel: MyViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        myViewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)

        binding.apply {

            /* -------------------------------------IMAGES------------------------------------- */
            if(myViewModel.language.value == true) {
                menuSettings.setImageResource(R.drawable.settings_en)
                menuLevels.setImageResource(R.drawable.levels_en)
                menuStart.setImageResource(R.drawable.start_en)
            } else {
                menuSettings.setImageResource(R.drawable.settings_ru)
                menuLevels.setImageResource(R.drawable.levels_ru)
                menuStart.setImageResource(R.drawable.start_ru)
            }

            /* -------------------------------------ONCLICK------------------------------------- */
            menuSettings.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_menuFragment_to_settingsFragment)
            }
            menuLevels.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_menuFragment_to_levelsFragment)
            }
            menuStart.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_menuFragment_to_levelOneFragment)
            }
        }
        return binding.root
    }
}
