package com.example.pixabay_hw_3_5

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pixabay_hw_3_5.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var adapter = ImageAdapter(mutableListOf())
    private var page = 1
    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClickers()
        setupRecyclerView()
    }

    private fun initClickers() {
        with(binding) {
            btnSearch.setOnClickListener {
                page = 1
                adapter.listClear()
                getImage(page)
            }
            btnNext.setOnClickListener {
                page++
                adapter.listClear()
                getImage(page)
            }
        }
    }

    private fun setupRecyclerView() {
        with(binding) {
            val layoutManager = rvImages.layoutManager as GridLayoutManager
            rvImages.adapter = adapter

            rvImages.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val lastImage = layoutManager.findLastVisibleItemPosition()
                    val totalItemCount = layoutManager.itemCount

                    if (lastImage == totalItemCount.minus(1) && !isLoading) {
                        page++
                        getImage(page)
                    }
                }
            })
        }
    }

    private fun getImage(page: Int) {
        with(binding) {
            isLoading = true
            RetrofitService().api.getImages(etWord.text.toString(), page = page)
                .enqueue(object : Callback<PixaModel> {
                    override fun onResponse(
                        call: Call<PixaModel>, response: Response<PixaModel>
                    ) {
                        isLoading = false
                        if (response.isSuccessful) {
                            response.body()?.let {
                                adapter.addNewImages(it.hits)
                            }
                        }
                    }

                    override fun onFailure(call: Call<PixaModel>, t: Throwable) {
                        isLoading = false
                        Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
                    }
                })
        }
    }
}