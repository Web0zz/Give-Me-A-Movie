package com.web0zz.givemeamovie.view.ui.home

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.web0zz.givemeamovie.databinding.FragmentHomeBinding
import com.web0zz.givemeamovie.model.network.movie_lists.MovieListSection
import com.web0zz.givemeamovie.view.adapter.MovieListAdapter
import com.web0zz.givemeamovie.view.adapter.MovieListSectionAdapter
import com.web0zz.givemeamovie.view.ui.favorite.FavoriteFragmentDirections


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        homeViewModel.mainFunction {
            //TODO On error fetching data
        }

        //TODO Bad way to initialize data
        val list = mutableListOf<MovieListSection>()
        list.add(MovieListSection("Popular", homeViewModel.popularMovie.value!!.results))
        list.add(MovieListSection("Top Rated", homeViewModel.topRatedMovie.value!!.results))
        list.add( MovieListSection("Now Playing", homeViewModel.nowPlayingMovie.value!!.results))

        binding.movieSections.adapter = MovieListSectionAdapter(list, MovieListAdapter.MovieClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
            findNavController().navigate(action)
        })

        //TODO bad way to do it
        binding.searchQuery.setOnEditorActionListener { _, actionId, event ->
            if (((event?.action ?: -1) == KeyEvent.ACTION_DOWN)
                    || actionId == EditorInfo.IME_ACTION_DONE) {
                    //TODO will change recycler view adapter
                    homeViewModel.searchMovie(
                            binding.searchQuery.text.toString(),
                            onError = {
                                //TODO on error
                            }
                    )
                    binding.movieSections.adapter = MovieListAdapter(
                            homeViewModel.searchMovieList.value!!,
                            MovieListAdapter.MovieClickListener {
                                val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
                                findNavController().navigate(action)
                            }
                    )
                    val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
                        binding.movieSections.adapter = MovieListSectionAdapter(list, MovieListAdapter.MovieClickListener {
                            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
                            findNavController().navigate(action)
                        })
                    }
                    callback.isEnabled = false
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}