package com.example.kotlin_omdbapi2.ui.home

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.get
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_omdbapi2.R
import com.example.kotlin_omdbapi2.adapter.MoviesAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.coroutines.isActive

class HomeFragment : Fragment() {

    private lateinit var viewModel : HomeViewModel
    private val moviesAdapter = MoviesAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
       //

        movieList.layoutManager = LinearLayoutManager(context)
        movieList.adapter = moviesAdapter

        swipeRefreshLayout.setOnRefreshListener {
            movieList.visibility = View.GONE
            movieError.visibility = View.GONE
            movieLoading.visibility = View.VISIBLE

            viewModel.refreshFromAPI(editTextSearch.text.toString())
            swipeRefreshLayout.isRefreshing = false
            movieLoading.visibility = View.GONE
        }

        movieError.visibility = View.GONE
        movieLoading.visibility = View.GONE
        searchButton.setOnClickListener{
            if (editTextSearch?.text?.isEmpty() == true){
                alertDialog("Empty","Please Enter Movie Name")
            }else{
                viewModel.refreshData(editTextSearch?.text.toString())
                observeLiveData()
                    if(viewModel.movies.value.isNullOrEmpty()){
                        alertDialog("No Movie","Please Enter Valid Movie Name")
                    }


            }
        }
    }

    fun alertDialog(title: String, message: String){
        val dialogBuilder = AlertDialog.Builder(requireContext())
        dialogBuilder.setMessage(message)
            .setCancelable(false)
            .setNegativeButton("Okey", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
            })
        val alert = dialogBuilder.create()
        alert.setTitle(title)
        alert.show()
    }

    fun observeLiveData(){
        viewModel.movies.observe(this, Observer { movies ->
            movies?.let {
                    movieList.visibility = View.VISIBLE
                    moviesAdapter.updateGameList(movies)
            }

        })

        viewModel.movieError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if(it){
                    movieError.visibility = View.VISIBLE
                }else{
                    movieError.visibility = View.GONE
                }
            }
        })

        viewModel.movieLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if(it){
                    movieLoading.visibility = View.VISIBLE
                    movieList.visibility = View.GONE
                    movieError.visibility = View.GONE
                }else{
                    movieLoading.visibility = View.GONE
                }
            }
        })
    }

}