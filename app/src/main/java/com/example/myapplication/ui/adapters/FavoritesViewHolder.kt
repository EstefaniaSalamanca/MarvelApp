package com.example.myapplication.ui.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.database.entities.Favorite
import com.example.myapplication.data.model.ResultsModel
import com.example.myapplication.databinding.ItemCharacterBinding
import com.squareup.picasso.Picasso

class FavoritesViewHolder  (view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemCharacterBinding.bind(view)

    fun bind(favorite: Favorite) {
        binding.tvCharacterName.text = favorite.name

        Picasso.get().load(favorite.image).into(binding.ivSuperhero)


    }
}