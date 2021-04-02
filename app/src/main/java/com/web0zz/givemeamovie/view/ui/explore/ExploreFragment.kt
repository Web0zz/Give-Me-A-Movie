 
package com.web0zz.givemeamovie.view.ui.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.web0zz.givemeamovie.databinding.FragmentExploreBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExploreFragment : Fragment() {

    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!

    private val exploreViewModel: ExploreViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        exploreViewModel.currentMovieList.observe(
            viewLifecycleOwner,
            Observer {
                exploreViewModel.firstInit()

                binding.likeMovie.setOnClickListener {
                    exploreViewModel.getNewMovieForUi(true)
                }

                binding.skipMovie.setOnClickListener {
                    exploreViewModel.getNewMovieForUi(false)
                }
            }
        )

        with(binding) {
            vm = exploreViewModel
            executePendingBindings()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
