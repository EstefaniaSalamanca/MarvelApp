package com.example.myapplication.ui.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.model.ResultsModel
import com.example.myapplication.databinding.ItemCharacterBinding
import com.squareup.picasso.Picasso

class HomeViewHolder (view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemCharacterBinding.bind(view)

    fun bind(resultsModel: ResultsModel, onItemSelected: (Int) -> Unit){
        binding.tvCharacterName.text = resultsModel.characterName

        Picasso.get().load(resultsModel.imageUrl).into(binding.ivSuperhero)

        binding.root.setOnClickListener { onItemSelected(resultsModel.characterId) }


    }
}