package com.android.multicolored_circles.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.android.multicolored_circles.R
import com.android.multicolored_circles.databinding.FragmentLevelsBinding
import com.android.multicolored_circles.viewmodel.MyViewModel

class LevelsFragment : Fragment() {

    private var _binding: FragmentLevelsBinding? = null
    private val binding get() = _binding!!
    private lateinit var myViewModel: MyViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentLevelsBinding.inflate(inflater, container, false)
        myViewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)

        binding.apply {

            /* -------------------------------------IMAGES------------------------------------- */
            if(myViewModel.language.value == true) {
                lev1.setImageResource(R.drawable.level1_en)
                lev2.setImageResource(R.drawable.level2_en)
                lev3.setImageResource(R.drawable.level3_en)
                lev4.setImageResource(R.drawable.level4_en)
                lev5.setImageResource(R.drawable.level5_en)
                lev6.setImageResource(R.drawable.level6_en)
                backMenuLevel.setImageResource(R.drawable.menu_en)
            } else {
                lev1.setImageResource(R.drawable.level1_ru)
                lev2.setImageResource(R.drawable.level2_ru)
                lev3.setImageResource(R.drawable.level3_ru)
                lev4.setImageResource(R.drawable.level4_ru)
                lev5.setImageResource(R.drawable.level5_ru)
                lev6.setImageResource(R.drawable.level6_ru)
                backMenuLevel.setImageResource(R.drawable.menu_ru)
            }

            /* -------------------------------------ONCLICK------------------------------------- */
            lev1.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_levelsFragment_to_levelOneFragment)
            }
            lev2.setOnClickListener {
                if(myViewModel.lock2.value == true) {
                    Navigation.findNavController(it)
                        .navigate(R.id.action_levelsFragment_to_levelTwoFragment)
                }
            }
            lev3.setOnClickListener {
                if(myViewModel.lock3.value == true) {
                    Navigation.findNavController(it)
                        .navigate(R.id.action_levelsFragment_to_levelThreeFragment)
                }
            }
            lev4.setOnClickListener {
                if(myViewModel.lock4.value == true) {
                    Navigation.findNavController(it)
                        .navigate(R.id.action_levelsFragment_to_levelFourFragment)
                }
            }
            lev5.setOnClickListener {
                if(myViewModel.lock5.value == true) {
                    Navigation.findNavController(it)
                        .navigate(R.id.action_levelsFragment_to_levelFiveFragment)
                }
            }
            backMenuLevel.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_levelsFragment_to_menuFragment)
            }
        }
        return binding.root
    }
}
