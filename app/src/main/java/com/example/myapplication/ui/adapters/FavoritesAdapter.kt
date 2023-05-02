package com.example.myapplication.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.database.entities.Favorite
import com.example.myapplication.data.model.ResultsModel

class FavoritesAdapter (
    var favoriteList: List<Favorite> = emptyList()
) :
    RecyclerView.Adapter<FavoritesViewHolder>() {

    fun setData(List: List<Favorite>) {
        favoriteList = List
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        return FavoritesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bind(favoriteList[position])
    }

    override fun getItemCount() = favoriteList.size

}

