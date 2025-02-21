package com.example.movie.presentation.view.fragment.tabs_detail_actor

import android.content.Intent
import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie.common.base.fragment.BaseFragment
import com.example.movie.common.extensions.reverseReleaseDate
import com.example.movie.common.factory.CommonViewModelFactory
import com.example.movie.data.remote.data_source.actor.ActorRemoteDataSourceImpl
import com.example.movie.data.remote.network.RetrofitInstance
import com.example.movie.data.repository.ActorRepositoryImpl
import com.example.movie.databinding.FragmentCastCrewBinding
import com.example.movie.domain.model.cast_crew_roles.CastCrewItem
import com.example.movie.domain.usecase.actors.DetailActorUseCase
import com.example.movie.domain.usecase.actors.GetAllActorsUseCase
import com.example.movie.domain.usecase.actors.SearchActorUseCase
import com.example.movie.presentation.adapter.cast_crew_detail_page.CastCrewRoleAdapter
import com.example.movie.presentation.view.activity.DetailActivity
import com.example.movie.presentation.viewmodel.ActorViewModel


class CastCrewFragment : BaseFragment<FragmentCastCrewBinding>(FragmentCastCrewBinding::inflate) {

    private lateinit var adapter: CastCrewRoleAdapter
    private val actorViewModel: ActorViewModel by activityViewModels {
        CommonViewModelFactory {
            ActorViewModel(
                GetAllActorsUseCase(ActorRepositoryImpl(ActorRemoteDataSourceImpl(RetrofitInstance.actorsApiService))),
                SearchActorUseCase(ActorRepositoryImpl(ActorRemoteDataSourceImpl(RetrofitInstance.actorsApiService))),
                DetailActorUseCase(ActorRepositoryImpl(ActorRemoteDataSourceImpl(RetrofitInstance.actorsApiService))),
            )
        }
    }

    override fun setupCreatedUI() {

        adapter = CastCrewRoleAdapter(
            onRootClick = {
                showDetailPage(it.id.toInt())
            }
        )
        binding.rvMovies.layoutManager = LinearLayoutManager(requireContext())

        binding.rvMovies.adapter = adapter
        val actorId = arguments?.getInt("ACTOR_ID") ?: 0
        val type = arguments?.getString("TYPE") ?: "cast"
        Log.e("gelen idler ", "$actorId, $type")

        if (type == "cast") {
            actorViewModel.actorDetails.observe(viewLifecycleOwner) { items ->

                val castList = items.movie_credits.cast?.map {
                    CastCrewItem(it.id.toString(),it.title, "Character : ${it.character}",it.release_date.reverseReleaseDate(),it.vote_average.toString(), it.posterPath)
                } ?: emptyList()



                Log.e("adapter detaillll", "$items")

                adapter.updateList(castList)
            }

        } else {

            actorViewModel.actorDetails.observe(viewLifecycleOwner) { items ->

                val crewList = items.movie_credits.crew?.map {
                    CastCrewItem(
                        it.id.toString(),it.title, "Job : ${it.job}",it.release_date.reverseReleaseDate(),it.vote_average.toString(), it.posterPath,
                    )
                } ?: emptyList()





                adapter.updateList(crewList)
            }


        }

    }
    private fun showDetailPage(id:Int){
        val intent = Intent(requireContext(), DetailActivity::class.java).apply {
            putExtra("movieDetail", id)

        }
        startActivity(intent)
        requireActivity().finish()
    }

}