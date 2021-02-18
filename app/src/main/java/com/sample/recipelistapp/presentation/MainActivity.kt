package com.sample.recipelistapp.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.sample.recipelistapp.R
import com.sample.recipelistapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.toolbar.view.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.root.toolbar)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.apply {
            navigationLiveData.observe(this@MainActivity, Observer { navEvent ->
                val consume = navEvent?.getContentIfNotHandled()
                consume?.invoke(findNavController(R.id.nav_host_fragment))
            })
        }
    }
}