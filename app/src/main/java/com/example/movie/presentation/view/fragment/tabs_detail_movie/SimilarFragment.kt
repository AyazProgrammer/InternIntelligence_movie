package com.example.movie.presentation.view.fragment.tabs_detail_movie

import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie.common.base.fragment.BaseFragment
import com.example.movie.common.factory.CoreViewModelFactory
import com.example.movie.databinding.FragmentSimilarBinding
import com.example.movie.presentation.adapter.home_movie_page.MovieAdapter
import com.example.movie.presentation.viewmodel.DetailViewModel
import org.koin.android.ext.android.inject


class SimilarFragment : BaseFragment<FragmentSimilarBinding>(FragmentSimilarBinding::inflate) {

    private val coreViewModelFactory: CoreViewModelFactory<DetailViewModel> by inject()

    private val detailViewModel: DetailViewModel by lazy {
        ViewModelProvider(requireActivity(), coreViewModelFactory).get(DetailViewModel::class.java)
    }


    private lateinit var similarMoviesAdapter: MovieAdapter


    override fun setupCreatedUI() {

        similarMoviesAdapter = MovieAdapter(
            onPosterClick = { movie ->
                detailViewModel.updateMovieId(movie.id)
                detailViewModel.getDetailMovies(movie.id)
                detailViewModel.getSimilarMovies(movie.id)
                detailViewModel.getVideos(movie.id)
                detailViewModel.getPersons(movie.id)
                detailViewModel.getReviewsForMovie(movie.id)
                Log.d("PosterClick", "Poster clicked: ${movie.title}")
            },
            onRootClick = { movie ->
                Log.d("RootClick", "Root clicked: ${movie.title}")
            }
        )

        binding.similarMovies.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.similarMovies.adapter = similarMoviesAdapter
        detailViewModel.similar.observe(viewLifecycleOwner) { items ->
            Log.e("similar observer", "${items}")
            similarMoviesAdapter.updateList(items.results)
        }


    }


}