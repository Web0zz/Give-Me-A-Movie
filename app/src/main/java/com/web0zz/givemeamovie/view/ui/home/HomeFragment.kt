package com.web0zz.givemeamovie.view.ui.home

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.web0zz.givemeamovie.databinding.FragmentHomeBinding
import com.web0zz.givemeamovie.model.network.movie_lists.MovieListSection
import com.web0zz.givemeamovie.view.adapter.MovieListAdapter
import com.web0zz.givemeamovie.view.adapter.MovieListSectionAdapter
import com.web0zz.givemeamovie.view.ui.favorite.FavoriteFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel.mainFunction {
            Toast.makeText(context, "error on fetching movies", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        with(binding) {
            vm = homeViewModel
            clickListener = MovieListAdapter.MovieClickListener {
                val action = FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment(it)
                findNavController().navigate(action)
            }
            executePendingBindings()
        }

        // TODO bad way to do it
        binding.searchQuery.setOnEditorActionListener { _, actionId, event ->
            if (((event?.action ?: -1) == KeyEvent.ACTION_DOWN) ||
                actionId == EditorInfo.IME_ACTION_DONE
            ) {
                // TODO will change recycler view adapter
                homeViewModel.searchMovie(
                    binding.searchQuery.text.toString(),
                    onError = {
                        // TODO on error
                    }
                )
                homeViewModel.searchMovieList.observe(
                    viewLifecycleOwner,
                    Observer {
                        setAdapter(binding.movieSections, false, null)
                    }
                )

                val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
                    homeViewModel.listMovieSection.observe(
                        viewLifecycleOwner,
                        Observer {
                            setAdapter(binding.movieSections, true, it)
                        }
                    )
                }
                callback.isEnabled = false
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }

        return binding.root
    }

    private fun setAdapter(recyclerView: RecyclerView, st: Boolean, listMovieSection: List<MovieListSection>?) {
        // on home state adapter will be setup
        if (st) {
            recyclerView.adapter = MovieListSectionAdapter(
                listMovieSection!!,
                MovieListAdapter.MovieClickListener {
                    val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
                    findNavController().navigate(action)
                }
            )
            val layoutManager = LinearLayoutManager(context)
            layoutManager.orientation = LinearLayoutManager.VERTICAL
            binding.movieSections.layoutManager = layoutManager
        } else { // on search state adapter will be setup
            homeViewModel.searchMovieList.observe(
                viewLifecycleOwner,
                Observer { ListMovie ->
                    recyclerView.adapter = MovieListAdapter(
                        ListMovie,
                        MovieListAdapter.MovieClickListener {
                            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
                            findNavController().navigate(action)
                        }
                    )
                }
            )
            val gridLayoutManager = GridLayoutManager(context, 3)
            gridLayoutManager.orientation = GridLayoutManager.VERTICAL
            binding.movieSections.layoutManager = gridLayoutManager
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
