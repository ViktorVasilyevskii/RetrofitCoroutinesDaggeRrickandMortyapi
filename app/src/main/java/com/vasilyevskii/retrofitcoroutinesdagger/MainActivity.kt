package com.vasilyevskii.retrofitcoroutinesdagger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.vasilyevskii.retrofitcoroutinesdagger.adapter.RickMortyAdapter
import com.vasilyevskii.retrofitcoroutinesdagger.databinding.ActivityMainBinding
import com.vasilyevskii.retrofitcoroutinesdagger.utils.Constants.SPAN_COUNT
import com.vasilyevskii.retrofitcoroutinesdagger.viewmodel.RickMortyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityMainBinding
    private lateinit var mAdapter: RickMortyAdapter
    private val viewModel: RickMortyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        loadData()

    }

    private fun loadData(){

        lifecycleScope.launch {
            viewModel.listData.collect { pagingData ->
                mAdapter.submitData(pagingData)
            }
        }
    }

    private fun initRecyclerView(){
        mAdapter = RickMortyAdapter()
        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(
                SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL
            )

            adapter = mAdapter
            setHasFixedSize(true)
        }
    }
}