package com.example.receipeapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.receipeapp.databinding.RecipeLayoutAdapterBinding
import com.example.receipeapp.fragments.HomeFragmentDirections
import com.example.receipeapp.model.Result

class RecipeAdapter : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>()
{
    inner class RecipeViewHolder(val binding: RecipeLayoutAdapterBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object :DiffUtil.ItemCallback<Result>()
    {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this,diffCallback)
    var recipe:List<Result>
        get() = differ.currentList
    set(value) {
        differ.submitList(value)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(RecipeLayoutAdapterBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        var currRecipe = recipe.get(position)

        Log.d(" RECIPE_ITM_CNT ", " RECIPE_ITM_CNT " + recipe.size)

        holder.binding.apply {
            tvTitleRecipe.text = currRecipe.title
            imageView.load(currRecipe.thumbnail) {
                crossfade(true)
                crossfade(1000)
            }
        }

        holder.itemView.setOnClickListener { mView ->
            val direction = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(currRecipe)
            mView.findNavController().navigate(direction)
        }
    }

    override fun getItemCount() = recipe.size


}