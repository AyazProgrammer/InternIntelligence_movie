package com.example.movie.presentation.view.fragment.bottomMenu

import android.content.Intent
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movie.common.base.fragment.BaseFragment
import com.example.movie.common.factory.CommonViewModelFactory
import com.example.movie.data.remote.data_source.actor.ActorRemoteDataSourceImpl
import com.example.movie.data.remote.network.RetrofitInstance
import com.example.movie.data.repository.ActorRepositoryImpl
import com.example.movie.databinding.FragmentCadreBinding
import com.example.movie.domain.usecase.actors.DetailActorUseCase
import com.example.movie.domain.usecase.actors.GetAllActorsUseCase
import com.example.movie.domain.usecase.actors.SearchActorUseCase
import com.example.movie.presentation.adapter.cast_crew_page.ActorMemberAdapter
import com.example.movie.presentation.view.activity.DetailActorActivity
import com.example.movie.presentation.viewmodel.ActorViewModel


class CadreFragment :   BaseFragment<FragmentCadreBinding>(FragmentCadreBinding::inflate) {

    private val actorViewModel: ActorViewModel by activityViewModels {
        CommonViewModelFactory {
            ActorViewModel(
                GetAllActorsUseCase(ActorRepositoryImpl(ActorRemoteDataSourceImpl(RetrofitInstance.actorsApiService))),
                SearchActorUseCase(ActorRepositoryImpl(ActorRemoteDataSourceImpl(RetrofitInstance.actorsApiService))),
                DetailActorUseCase(ActorRepositoryImpl(ActorRemoteDataSourceImpl(RetrofitInstance.actorsApiService))),
            )
        }
    }
    private var searchQuery = ""
    private lateinit var actorAdapter: ActorMemberAdapter

    override fun setupCreatedUI() {
        actorAdapter = ActorMemberAdapter(
            onRootClick = {
                val intent = Intent(requireContext(), DetailActorActivity::class.java).apply {
                    putExtra("actorPosition", it.id)

                }
                startActivity(intent)
            }
        )
        actorViewModel.getPopularActors()


        binding.actorRecyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.actorRecyclerView.adapter =actorAdapter

      binding.includePagination.apply {
          actorViewModel.actors.observe(viewLifecycleOwner) { items ->
              Log.e("similar observer", "${items}")
              currentPage.text = items.page.toString()
              allPage.text = " / ${if (items.totalResults > 500) 500 else items.totalResults}"
              actorAdapter.updateList(items.results)
          }

          btnNext.setOnClickListener {
              val position = currentPage.text.toString().toInt()+1
              Log.e("position adapter",position.toString())
              actorViewModel.searchActor(searchQuery,position)
          }
          btnPrevious.setOnClickListener {
              val position = currentPage.text.toString().toInt()-1
              Log.e("position adapter",position.toString())
              actorViewModel.searchActor(searchQuery,position)
          }
      }
        binding.includeSearch.apply {
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {

                    query?.let {
                        actorViewModel.searchActor(query)
                        searchQuery=query
                    }

                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    newText?.let {
                        searchQuery=newText
                        actorViewModel.searchActor(newText)
                    }
                    return true
                }
            })

        }



    }



}