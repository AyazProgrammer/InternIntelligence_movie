package com.example.movie.presentation.view.fragment.tabs_detail_movie

import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie.common.base.fragment.BaseFragment
import com.example.movie.common.extensions.observeInIO
import com.example.movie.common.factory.CoreViewModelFactory
import com.example.movie.databinding.FragmentReviewBinding
import com.example.movie.presentation.adapter.movie_detail_page.ReviewAdapter
import com.example.movie.presentation.viewmodel.DetailViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject


class ReviewFragment : BaseFragment<FragmentReviewBinding>(FragmentReviewBinding::inflate){

    private val coreViewModelFactory: CoreViewModelFactory<DetailViewModel> by inject()

    private val detailViewModel: DetailViewModel by lazy {
        ViewModelProvider(requireActivity(), coreViewModelFactory).get(DetailViewModel::class.java)
    }
    private lateinit var reviewAdapter: ReviewAdapter


    override fun setupCreatedUI() {

        reviewAdapter = ReviewAdapter()

        binding.reviewRecycle.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.reviewRecycle.adapter = reviewAdapter

        detailViewModel.review.observeInIO((viewLifecycleOwner.lifecycleScope)) { item ->
            lifecycleScope.launch(Dispatchers.Main) {
                Log.e("episodes","$item")
                reviewAdapter.updateList(item.results)
            }

        }


    }

}