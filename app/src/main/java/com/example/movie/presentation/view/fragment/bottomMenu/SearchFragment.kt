package com.example.movie.presentation.view.fragment.bottomMenu

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movie.common.base.fragment.BaseFragment
import com.example.movie.common.factory.CommonViewModelFactory
import com.example.movie.data.local.data_source.GenreLocalDataSourceImpl
import com.example.movie.data.local.data_source.MovieLocalDataSourceImpl
import com.example.movie.data.local.database.DatabaseInstance
import com.example.movie.data.remote.data_source.filter.FilterMovieDataSourceImpl
import com.example.movie.data.remote.data_source.genre.GenreRemoteDataSourceImpl
import com.example.movie.data.remote.data_source.movie.MovieRemoteDataSourceImpl
import com.example.movie.data.remote.network.RetrofitInstance
import com.example.movie.data.repository.FilterRepositoryImpl
import com.example.movie.data.repository.GenreRepositoryImpl
import com.example.movie.data.repository.MovieRepositoryImpl
import com.example.movie.databinding.FragmentSearchBinding
import com.example.movie.domain.usecase.home.GetAllGenresUseCase
import com.example.movie.domain.usecase.search.FilterMovieUseCase
import com.example.movie.domain.usecase.search.SearchMovieUseCase
import com.example.movie.presentation.adapter.home_movie_page.MovieAdapter
import com.example.movie.presentation.view.activity.DetailActivity
import com.example.movie.presentation.view.fragment.dialog.FilterDialogFragment
import com.example.movie.presentation.viewmodel.SearchViewModel


class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {
    private lateinit var moviesAdapter: MovieAdapter
    private var searchQuery = ""
    private val searchViewModel: SearchViewModel by activityViewModels {
        CommonViewModelFactory {
            SearchViewModel(
                SearchMovieUseCase(
                    MovieRepositoryImpl(
                        MovieRemoteDataSourceImpl(
                            RetrofitInstance.movieApiService
                        ),
                        MovieLocalDataSourceImpl(
                            DatabaseInstance.getDatabase(requireContext()).MovieDao()

                        )
                    )

                ),
                GetAllGenresUseCase(
                    GenreRepositoryImpl(
                        GenreRemoteDataSourceImpl(
                            RetrofitInstance.genreApiService
                        ),
                        GenreLocalDataSourceImpl(
                            DatabaseInstance.getDatabase(requireContext()).GenreDao()
                        )
                    )
                ),
                FilterMovieUseCase(
                    FilterRepositoryImpl(
                        FilterMovieDataSourceImpl(
                            RetrofitInstance.searchApiService
                        ),

                    )
                ),

            )
        }
    }

    @SuppressLint("SetTextI18n")
    override fun setupCreatedUI() {

        moviesAdapter = MovieAdapter(
            onPosterClick = { movie ->

                // findNavController().navigate(R.id.action_homeFragment_to_detailFragment23)
                val intent = Intent(requireContext(), DetailActivity::class.java).apply {
                    putExtra("movieDetail", movie.id)

                }
                startActivity(intent)


            },

        )
        searchViewModel.searchMovies()
        searchViewModel.getAllGenres()
       //
        // searchViewModel.searchMovies(null,null,FilterItem(listOf(12,45), 23.toString(),6.8f))
        binding.includePagination.apply {
            btnNext.setOnClickListener {
                val position = currentPage.text.toString().toInt()+1
                Log.e("position adapter",position.toString())
                searchViewModel.searchMovies(searchQuery,position)
            }
            btnPrevious.setOnClickListener {
                val position = currentPage.text.toString().toInt()-1
                Log.e("position adapter",position.toString())
                searchViewModel.searchMovies(searchQuery,position)
            }
        }



        binding.recycleViewSearch.layoutManager = GridLayoutManager(context, 3)
        binding.recycleViewSearch.adapter = moviesAdapter

        searchViewModel.searchFilter.observe(viewLifecycleOwner, Observer { movieList ->
            Log.e("adapter observer","${movieList}")
            binding.includePagination.currentPage.text = movieList.page.toString()
            binding.includePagination.allPage.text = " / ${if (movieList.total_pages > 500) 500 else movieList.total_pages}"
            moviesAdapter.updateList(movieList.results)
        })

        binding.includeSearch.filterIcon.setOnClickListener {
            val dialog = FilterDialogFragment()
            dialog.show(childFragmentManager, "FilterDialog")
        }


        binding.includeSearch.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                query?.let {
                   searchViewModel.searchMovies(query)
                    searchQuery=query
                }

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                newText?.let {
                    searchQuery=newText
                    searchViewModel.searchMovies(newText)
                }
                return true
            }
        })


    }


}