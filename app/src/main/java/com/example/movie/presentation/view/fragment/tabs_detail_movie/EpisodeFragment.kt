package com.example.movie.presentation.view.fragment.tabs_detail_movie

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie.common.base.fragment.BaseFragment
import com.example.movie.common.extensions.observeInIO
import com.example.movie.common.factory.CoreViewModelFactory
import com.example.movie.databinding.FragmentEpisodeBinding
import com.example.movie.presentation.adapter.movie_detail_page.VideosAdapter
import com.example.movie.presentation.viewmodel.DetailViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject


class EpisodeFragment : BaseFragment<FragmentEpisodeBinding>(FragmentEpisodeBinding::inflate) {

    private val coreViewModelFactory: CoreViewModelFactory<DetailViewModel> by inject()

    private val detailViewModel: DetailViewModel by lazy {
        ViewModelProvider(requireActivity(), coreViewModelFactory).get(DetailViewModel::class.java)
    }
    private lateinit var videosAdapter: VideosAdapter


    override fun setupCreatedUI() {

        videosAdapter = VideosAdapter(
            onRootClick = { video ->

                val youtubeUrl = "https://www.youtube.com/watch?v=${video.key}"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl))
                requireActivity().startActivity(intent)

            }
        )

        binding.movieVideos.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.movieVideos.adapter = videosAdapter

        detailViewModel.videos.observeInIO((viewLifecycleOwner.lifecycleScope)) { item ->
            lifecycleScope.launch(Dispatchers.Main) {
                Log.e("episodes","$item")
                videosAdapter.updateList(item)
            }

        }


    }

}