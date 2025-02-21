package com.example.movie.presentation.view.fragment.tabs_detail_movie

import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie.common.base.fragment.BaseFragment
import com.example.movie.common.extensions.observeInIO
import com.example.movie.common.extensions.reverseReleaseDate
import com.example.movie.common.factory.CoreViewModelFactory
import com.example.movie.databinding.FragmentAboutBinding
import com.example.movie.presentation.adapter.movie_detail_page.cast_crew.CastMemberAdapter
import com.example.movie.presentation.adapter.movie_detail_page.cast_crew.CrewMemberAdapter
import com.example.movie.presentation.view.activity.DetailActorActivity
import com.example.movie.presentation.viewmodel.DetailViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject


class AboutFragment : BaseFragment<FragmentAboutBinding>(FragmentAboutBinding::inflate) {


    private val coreViewModelFactory: CoreViewModelFactory<DetailViewModel> by inject()

    private val detailViewModel: DetailViewModel by lazy {
        ViewModelProvider(requireActivity(), coreViewModelFactory).get(DetailViewModel::class.java)
    }
    private lateinit var castMemberAdapter: CastMemberAdapter
    private lateinit var crewMemberAdapter: CrewMemberAdapter
    override fun setupCreatedUI() {

        castMemberAdapter = CastMemberAdapter(
            onRootClick = {
                val intent = Intent(requireContext(), DetailActorActivity::class.java).apply {
                    putExtra("actorPosition", it.id)

                }
                requireActivity().finish()
                startActivity(intent)

            }
        )
        crewMemberAdapter = CrewMemberAdapter(
            onRootClick = {
                val intent = Intent(requireContext(), DetailActorActivity::class.java).apply {
                    putExtra("actorPosition", it.id)

                }
                startActivity(intent)
                requireActivity().finish()
            }
        )

        detailViewModel.movieDetail.observeInIO((viewLifecycleOwner.lifecycleScope)){ movieDetail ->
            lifecycleScope.launch(Dispatchers.Main) {
                binding.genreAbout.text =movieDetail.genres.joinToString(separator = " , ") { it.name }
                binding.languageAbout.text = movieDetail.original_language.toUpperCase()
                binding.overviewAbout.text = movieDetail.overview
                binding.countryAbout.text = movieDetail.origin_country.joinToString(separator = ",") { it }
                binding.yearAbout.text = movieDetail.release_date.reverseReleaseDate()
                binding.taglineAbout.text = movieDetail.tagline.toString()
                binding.voteAverageAbout.text = movieDetail.vote_average.toString()
                binding.voteCountAbout.text = movieDetail.vote_count.toString()
                binding.movieTitle.text = movieDetail.title.toString()
            }


        }


        binding.castRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.castRecyclerView.adapter =castMemberAdapter
        binding.crewRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.crewRecyclerView.adapter =crewMemberAdapter


        detailViewModel.persons.observe(viewLifecycleOwner) { credits ->
            Log.e("all casts","${credits.cast}")
            Log.e("all casts","${credits.crew}")
            castMemberAdapter.updateList(credits.cast)
            crewMemberAdapter.updateList(credits.crew)

        }
    }







}