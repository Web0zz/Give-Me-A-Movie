package com.web0zz.givemeamovie.view.ui.detail

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.web0zz.givemeamovie.databinding.FragmentDetailBinding
import com.web0zz.givemeamovie.view.adapter.MovieListAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val detailViewModel: DetailViewModel by viewModels()
    private val watchListViewModel: WatchListViewModel by viewModels()

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailViewModel.getMovieDetails(
            args.selectedMovie
        ) {
            Timber.e("It couldn't fetch the data on onAttach $it")
        } /*
        watchListViewModel.setSelectedMovie(args.selectedMovie)*/
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        Timber.e("Detail movie fragment ${args.selectedMovie}")

        with(binding) {
            vm = detailViewModel/*
            movie = args.selectedMovie*/
            clickListener = MovieListAdapter.MovieClickListener {
                val action = DetailFragmentDirections.actionDetailFragmentSelf(it)
                findNavController().navigate(action)
            }
            executePendingBindings()
        }

        /*with(binding.watchlistFrameInclude) {
            vm = watchListViewModel
            clickListenerAdd = AddToWatchlistAdapter.WatchListAddToListClickListener {  stateIsActive, selectedLibrary ->
                if (stateIsActive) {
                    watchListViewModel.insertMovieToAvailableLibrary(selectedLibrary)
                } else {
                    watchListViewModel.deleteMovieFromAvailableLibrary(selectedLibrary)
                }
            }
            executePendingBindings()
        }*/
        detailViewModel.movieVideo.observe(viewLifecycleOwner, Observer { video ->
            binding.watchTrailerButton.setOnClickListener {
                if (detailViewModel.movieVideo.value == null) {
                    Toast.makeText(context, "We couldn't find", Toast.LENGTH_SHORT).show()
                } else {
                    var trailer = ""
                    var teaser = ""

                    video.results.map { video_result ->
                        if (video_result.site == "YouTube" && video_result.type == "Trailer") {
                            trailer = video_result.key
                            Timber.d("trailer video link is $trailer")
                        }
                        if (video_result.site == "YouTube" && video_result.type == "Teaser") {
                            teaser = video_result.key
                            Timber.d("teaser video link is $teaser")
                        }
                    }
                    if (trailer == "" && teaser == "") {
                        Toast.makeText(context, "We couldn't find", Toast.LENGTH_SHORT).show()
                    } else {
                        if (trailer != "") {
                            Timber.d("trailer video link is $trailer")
                            openTrailer(trailer)
                        } else {
                            Timber.d("teaser video link is $teaser")
                            openTrailer(teaser)
                        }
                    }
                }
            }
        })

       /* binding.addMovieToWatchlistButton.setOnClickListener {
            if (binding.watchlistFrameInclude.root.visibility == View.GONE) {
                binding.watchlistFrameInclude.root.visibility = View.VISIBLE
            } else {
                binding.watchlistFrameInclude.root.visibility = View.GONE
            }
        }*/

        /*binding.likeButton.setOnClickListener {
            if(detailViewModel.likeAction(args.selectedMovie)) {
                Toast.makeText(context, "Deleted from your likes", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Added to your likes", Toast.LENGTH_SHORT).show()
            }
        }
*/
        /*binding.watchlistFrameInclude.closeView.setOnClickListener {
            binding.watchlistFrameInclude.root.visibility = View.GONE
        }

        binding.watchlistFrameInclude.addNewList.setOnClickListener {
            binding.watchlistFrameInclude.addNewList.visibility = View.GONE
            binding.watchlistFrameInclude.newListNameEdittext.visibility = View.VISIBLE
            binding.watchlistFrameInclude.addingNewList.visibility = View.VISIBLE
        }

        binding.watchlistFrameInclude.addNewList.setOnClickListener {
            val newLibraryName = binding.watchlistFrameInclude.newListNameEdittext.text.toString()
            if(newLibraryName != "") {
                watchListViewModel.insertMovieToNewLibrary(newLibraryName)
                Toast.makeText(context, "Added to $newLibraryName", Toast.LENGTH_SHORT).show()
                binding.watchlistFrameInclude.addNewList.visibility = View.VISIBLE
                binding.watchlistFrameInclude.newListNameEdittext.visibility = View.GONE
                binding.watchlistFrameInclude.addingNewList.visibility = View.GONE
                binding.watchlistFrameInclude.root.visibility = View.GONE
            } else {
                Toast.makeText(context, "Oops, Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }*/

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun openTrailer(video_path: String) {
        val intentApp = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$video_path"))
        val intentBrowser = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=$video_path"))
        try {
            this.startActivity(intentApp)
        } catch (ex: ActivityNotFoundException) {
            this.startActivity(intentBrowser)
        }
    }
}
