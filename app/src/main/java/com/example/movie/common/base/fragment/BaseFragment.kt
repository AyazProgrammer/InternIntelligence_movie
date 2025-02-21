package com.example.movie.common.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.movie.common.utils.VideoLoader

abstract class BaseFragment<VB : ViewBinding> (
    private val inflateBinding: (LayoutInflater, ViewGroup?, Boolean)-> VB
): Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!
    protected var exoPlayer: ExoPlayer? = null

    //  protected abstract val viewModel: VM

    open fun setupCreatedUI(){}


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflateBinding(inflater, container, false)
        setupCreatedUI()
        return binding.root
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }




    protected fun navigateTo(view: View? = null, destinationId: Int) {
        view?.setOnClickListener {

            findNavController().navigate(destinationId)
        } ?: run {
            findNavController().navigate(destinationId)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        VideoLoader.releasePlayer(exoPlayer)
    }
}