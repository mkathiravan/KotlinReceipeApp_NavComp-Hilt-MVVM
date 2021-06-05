package com.example.receipeapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.receipeapp.R
import com.example.receipeapp.adapter.RecipeAdapter
import com.example.receipeapp.databinding.FragmentHomeBinding
import com.example.receipeapp.di.ReceipeModule
import com.example.receipeapp.viewModel.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding?= null
    private val binding get() = _binding!!
    private val viewModel:RecipeViewModel by viewModels()
    private lateinit var recipeAdapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d("HF_ONCRETEVIEW_CALLED","HF_ONCRETEVIEW_CALLED");
        _binding = FragmentHomeBinding.inflate(inflater,container,false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRv()
    }

    private fun setUpRv() {

        recipeAdapter = RecipeAdapter()
        binding.rvRecipe.apply {
            layoutManager = GridLayoutManager(activity,2)
            setHasFixedSize(true)
            adapter = recipeAdapter

        }

        viewModel.recipeResponse.observe(requireActivity(),
            { result ->
                binding.tvTitle.text = result.title
                recipeAdapter.recipe = result.results
            })
    }

}