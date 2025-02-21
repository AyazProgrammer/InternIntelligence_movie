package com.example.movie.presentation.view.activity

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.movie.R
import com.example.movie.common.base.activity.BaseActivity
import com.example.movie.common.extensions.applySystemBarInsets
import com.example.movie.common.extensions.reverseReleaseDate
import com.example.movie.common.factory.CoreViewModelFactory
import com.example.movie.common.utils.ImageLoader
import com.example.movie.databinding.ActivityDetailBinding
import com.example.movie.presentation.adapter.movie_detail_page.MyPagerAdapter
import com.example.movie.presentation.viewmodel.DetailViewModel
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.android.ext.android.inject
import kotlin.properties.Delegates

class DetailActivity : BaseActivity<ActivityDetailBinding>(ActivityDetailBinding::inflate) {
    private val coreViewModelFactory: CoreViewModelFactory<DetailViewModel> by inject()


    private val detailViewModel: DetailViewModel by lazy {
        ViewModelProvider(this, coreViewModelFactory).get(DetailViewModel::class.java)
    }
    private var  isCheck by Delegates.notNull<Boolean>()

    @SuppressLint("SetTextI18n")
    override fun create() {
        //enableEdgeToEdge()
        binding.root.applySystemBarInsets()
        val adapter = MyPagerAdapter(this)
        binding.recyclerView.adapter = adapter



        val position = intent.getIntExtra("movieDetail", -1)

        detailViewModel.updateMovieId(position)
        detailViewModel.getDetailMovies(position)
        detailViewModel.getVideos(position)
        detailViewModel.getPersons(position)
        detailViewModel.getReviewsForMovie(position)
        detailViewModel.getSimilarMovies(position)



        detailViewModel.loading.observe(this) { loading ->
            if (!loading){
                binding.progressBar.visibility = View.INVISIBLE
            }else{
                binding.progressBar.visibility = View.VISIBLE
            }
        }

        binding.recyclerView.isUserInputEnabled = false
        val tabTitles = listOf("Episode", "Similar", "About","Review")
        TabLayoutMediator(binding.tabLayout, binding.recyclerView) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()


        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        detailViewModel.isFavorite.observe(this) { isFav ->

            Handler(Looper.getMainLooper()).postDelayed({
                isCheck = isFav
                updateFavoriteIcon(isFav)
            }, 100)
        }







        detailViewModel.movieDetail.observe(this, Observer { movie ->
            Log.e("detail observer", "${movie}")
            binding.movieTitle.text = movie.title
            binding.movieDetails.text = "${movie.release_date.reverseReleaseDate().takeLast(4)} • ${
                movie.genres.take(2).joinToString(separator = " • ") { it.name }
            } • ${movie.runtime / 60}h ${movie.runtime % 60}m"
            binding.movieDescription.text = movie.overview
            Log.e("detail poster error", "${movie.poster_path}")
            Log.e("detail poster error", "${movie.posterUrl}")
            ImageLoader.loadImage2(binding.posterImage, movie.posterUrl)
            // Handle the movieList or movieDetail here
        })




        safeSetupReadMore(binding.movieDescription,binding.readMoreDetails)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_favorite -> {
                if (isCheck){
                    updateFavoriteIcon(false)
                    isCheck = false
                    detailViewModel.changeMovie()
                    Toast.makeText(this, "Remove to Favorites", Toast.LENGTH_SHORT).show()


                }else{
                    updateFavoriteIcon(true)
                    isCheck =true
                    Toast.makeText(this, "Added to Favorites", Toast.LENGTH_SHORT).show()
                    detailViewModel.changeMovie()
                }

                true
            }
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun updateFavoriteIcon(isFav: Boolean) {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        val favItem = toolbar.menu.findItem(R.id.action_favorite)

        favItem.setIcon(if (isFav) R.drawable.ic_favorite_filled else R.drawable.ic_favorite_border)
    }
}