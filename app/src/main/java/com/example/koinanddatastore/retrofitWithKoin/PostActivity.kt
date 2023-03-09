package com.example.koinanddatastore.retrofitWithKoin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.koinanddatastore.databinding.ActivityPostBinding
import com.example.koinanddatastore.retrofitWithKoin.data.BusAdapter
import com.example.koinanddatastore.retrofitWithKoin.data.utils.ApiState
import com.example.koinanddatastore.retrofitWithKoin.data.utils.Listener
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class PostActivity : AppCompatActivity(), Listener {
    private lateinit var binding: ActivityPostBinding
    private val component = Component()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerview()
        getBus()
        BusAdapter(this@PostActivity)
    }
    private fun initRecyclerview() {
        binding.apply {
            recyclerview.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@PostActivity)
                adapter = component.busAdapter
            }
        }
    }


    private fun getBus() {
        component.mainViewModel.getBus()
        lifecycleScope.launchWhenStarted {
            component.mainViewModel.busApi.collect {
                binding.apply {
                    when (it) {
                        is ApiState.Success -> {
                            recyclerview.isVisible = true
                            progressbar.isVisible = false
                            Log.d("main", " ${it.data} ")
                            component.busAdapter.submitList(it.data)
                        }
                        is ApiState.Failure -> {
                            recyclerview.isVisible = false
                            progressbar.isVisible = false
                            Log.d("main", " ${it.msg} ")
                        }
                        is ApiState.Loading -> {
                            recyclerview.isVisible = false
                            progressbar.isVisible = true
                        }
                        is ApiState.Empty -> {

                        }
                    }
                }
            }
        }
    }

    override fun onClickDelete(position: Int, busNo: String) {

    }
}