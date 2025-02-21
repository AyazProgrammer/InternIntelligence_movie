package com.example.movie.presentation.view.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.activity.viewModels
import com.example.movie.R
import com.example.movie.common.base.activity.BaseActivity
import com.example.movie.common.extensions.reverseReleaseDate
import com.example.movie.common.factory.CommonViewModelFactory
import com.example.movie.common.utils.ImageLoader
import com.example.movie.data.remote.data_source.actor.ActorRemoteDataSourceImpl
import com.example.movie.data.remote.network.RetrofitInstance
import com.example.movie.data.repository.ActorRepositoryImpl
import com.example.movie.databinding.ActivityDetailActorBinding
import com.example.movie.domain.usecase.actors.DetailActorUseCase
import com.example.movie.domain.usecase.actors.GetAllActorsUseCase
import com.example.movie.domain.usecase.actors.SearchActorUseCase
import com.example.movie.presentation.adapter.cast_crew_detail_page.CastCrewPagerAdapter
import com.example.movie.presentation.viewmodel.ActorViewModel
import com.google.android.material.tabs.TabLayoutMediator

class DetailActorActivity : BaseActivity<ActivityDetailActorBinding>(ActivityDetailActorBinding::inflate) {

    private val actorViewModel: ActorViewModel by viewModels {
        CommonViewModelFactory {
            ActorViewModel(
                GetAllActorsUseCase(ActorRepositoryImpl(ActorRemoteDataSourceImpl(RetrofitInstance.actorsApiService))),
                SearchActorUseCase(ActorRepositoryImpl(ActorRemoteDataSourceImpl(RetrofitInstance.actorsApiService))),
                DetailActorUseCase(ActorRepositoryImpl(ActorRemoteDataSourceImpl(RetrofitInstance.actorsApiService))),
            )
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val position = intent.getIntExtra("actorPosition", -1)
        actorViewModel.actorDetails(position)
         val adapter = CastCrewPagerAdapter(this,position)
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = if (position == 0) "Cast" else "Crew"
        }.attach()

        actorViewModel.actorDetails.observe(this, { actor ->
            binding.tvActorName.text = actor.name
            Log.e("actor detail name", "${actor.name}")
            binding.tvBiography.text = actor.biography
            binding.tvBirthInfo.text = actor.birthday.reverseReleaseDate() + " / ${actor.place_of_birth}"
            ImageLoader.loadImage(
                binding.ivActorProfile,
                actor?.profileUrl?.toString(),
                R.drawable.placeholder,
                R.drawable.error_image
            )
        })


        safeSetupReadMore(binding.tvBiography,binding.readMoreDetails)

       // binding.btnBack.setOnClickListener { onBackPressed() }
    }


}