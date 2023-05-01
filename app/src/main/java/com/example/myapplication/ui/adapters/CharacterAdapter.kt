package com.example.myapplication.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.model.ResultsModel


class CharacterAdapter(
    var characterList: List<ResultsModel> = emptyList(), private val onItemSelected: (Int) -> Unit
) :
    RecyclerView.Adapter<CharacterViewHolder>() {

    fun updateList(List: List<ResultsModel>) {
        characterList = List
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(characterList[position] , onItemSelected)
    }

    override fun getItemCount() = characterList.size

}
