package com.example.myapplication.ui.view

import android.nfc.NfcAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.core.RetrofitHelper
import com.example.myapplication.data.database.CharacterDatabase
import com.example.myapplication.data.database.dao.FavoritesDao
import com.example.myapplication.data.database.entities.Favorite
import com.example.myapplication.data.model.CharacterModel
import com.example.myapplication.data.network.MarvelApiClient

import com.example.myapplication.databinding.FragmentFavoritesBinding
import com.example.myapplication.ui.adapters.FavoritesAdapter

import com.example.myapplication.ui.viewmodel.FavoritesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private lateinit var adapter: FavoritesAdapter
    private lateinit var favoritesDao: FavoritesDao

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        val application = requireNotNull(activity).application
        favoritesDao = CharacterDatabase.getInstance(application).favoritesDao()

        val root: View = binding.root
        initRecyclerView()
        getAllFavorites()

        return root
    }

    private fun initRecyclerView() {
        adapter = FavoritesAdapter()
        binding.rvCharacterHome.layoutManager = LinearLayoutManager(context)
        binding.rvCharacterHome.adapter = adapter
    }

    private fun getAllFavorites() {
        binding.progressBarHome.visibility = View.VISIBLE

        CoroutineScope(Dispatchers.IO).launch {
            val favoritesList = favoritesDao.getAllFavorites()

            withContext(Dispatchers.Main) {
                adapter.setData(favoritesList)
            }

            binding.progressBarHome.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}