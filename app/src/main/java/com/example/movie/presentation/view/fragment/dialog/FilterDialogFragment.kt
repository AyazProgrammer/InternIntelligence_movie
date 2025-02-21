package com.example.movie.presentation.view.fragment.dialog



import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.ListView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
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
import com.example.movie.databinding.FragmentFilterDialogBinding
import com.example.movie.domain.model.filter.FilterItem
import com.example.movie.domain.model.genre.Genre
import com.example.movie.domain.usecase.home.GetAllGenresUseCase
import com.example.movie.domain.usecase.search.FilterMovieUseCase
import com.example.movie.domain.usecase.search.SearchMovieUseCase
import com.example.movie.presentation.adapter.home_movie_page.SelectedGenresAdapter
import com.example.movie.presentation.viewmodel.SearchViewModel
import java.util.Calendar

class FilterDialogFragment : DialogFragment() {
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

    private var _binding: FragmentFilterDialogBinding? = null
    private val binding get() = _binding!!

    private val selectedGenres = mutableListOf<Genre>()
    private lateinit var selectedGenresAdapter: SelectedGenresAdapter

    private val years = (1990..Calendar.getInstance().get(Calendar.YEAR)).toList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFilterDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Genre ListView Adapter
        val genreAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_multiple_choice,
            mutableListOf<String>()
        )
        binding.genreListView.adapter = genreAdapter
        binding.genreListView.choiceMode = ListView.CHOICE_MODE_MULTIPLE

        // RecyclerView Setup
        selectedGenresAdapter = SelectedGenresAdapter(selectedGenres) { genre ->
            removeGenre(genre)
        }
        binding.selectedGenresRecyclerView.adapter = selectedGenresAdapter
        binding.selectedGenresRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Year Spinner Adapter
        val yearAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            years
        )
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.yearSpinner.adapter = yearAdapter
        binding.yearSpinner.setSelection(years.lastIndex)

        // IMDb Slider
        binding.imdbSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.imdbValue.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })


        searchViewModel.genres.observe(viewLifecycleOwner) { genres ->
            if (genres.isNullOrEmpty()) {
                binding.genreListView.visibility = View.GONE

            } else {
                binding.genreListView.visibility = View.VISIBLE
                genreAdapter.clear()
                genreAdapter.addAll(genres.map { it.name })
                genreAdapter.notifyDataSetChanged()
            }
        }

        // Handle Genre Selection
        binding.genreListView.setOnItemClickListener { _, _, position, _ ->
            val genreName = genreAdapter.getItem(position)
            val genre = searchViewModel.genres.value?.firstOrNull { it.name == genreName }

            if (genre != null) {
                if (selectedGenres.contains(genre)) {
                    removeGenre(genre)
                } else {
                    addGenre(genre)
                }
            }
        }

        // Apply Button
        binding.applyButton.setOnClickListener {
            val selectedYear = binding.yearSpinner.selectedItem?.toString()
            val imdbRating = binding.imdbSeekBar.progress.toFloat()

            val filter = FilterItem(
                genres = selectedGenres.map { it.id },
                year = selectedYear,
                imdbRating = if (imdbRating > 0) imdbRating else 0.0f
            )
            searchViewModel.searchMovies(null,1,filter)
            Log.e("filters", "$filter")
            dismiss()
        }

        // Clear Filters Button
        binding.clearButton.setOnClickListener {
            clearSelections()
        }
    }

    private fun addGenre(genre: Genre) {
        selectedGenres.add(genre)
        updateGenreListViewCheckState()
        selectedGenresAdapter.notifyDataSetChanged()
    }

    private fun removeGenre(genre: Genre) {
        selectedGenres.remove(genre)
        updateGenreListViewCheckState()
        selectedGenresAdapter.notifyDataSetChanged()
    }

    private fun clearSelections() {
        selectedGenres.clear()
        for (i in 0 until binding.genreListView.count) {
            binding.genreListView.setItemChecked(i, false)
        }
        binding.yearSpinner.setSelection(0)
        binding.imdbSeekBar.progress = 0
        binding.imdbValue.text = "0"
        selectedGenresAdapter.notifyDataSetChanged()
    }

    private fun updateGenreListViewCheckState() {
        for (i in 0 until binding.genreListView.count) {
            val genreName = binding.genreListView.getItemAtPosition(i) as String
            val genre = searchViewModel.genres.value?.firstOrNull { it.name == genreName }
            binding.genreListView.setItemChecked(i, selectedGenres.contains(genre))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}





