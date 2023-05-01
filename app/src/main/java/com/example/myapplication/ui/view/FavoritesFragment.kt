package com.example.myapplication.ui.view

import android.nfc.NfcAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.data.database.CharacterDatabase
import com.example.myapplication.data.database.dao.FavoritesDao

import com.example.myapplication.databinding.FragmentFavoritesBinding
import com.example.myapplication.ui.adapters.FavoritesAdapter

import com.example.myapplication.ui.viewmodel.FavoritesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
        val favoritesViewModel =
            ViewModelProvider(this).get(FavoritesViewModel::class.java)

        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        favoritesDao = CharacterDatabase.getInstance(requireContext().applicationContext).favoritesDao()

        CoroutineScope(Dispatchers.IO).launch {

        }

        initRecyclerView()




        return root
    }




    private fun initRecyclerView() {
        adapter = FavoritesAdapter {  }
        binding.rvCharacterHome.layoutManager = LinearLayoutManager(context)
        binding.rvCharacterHome.adapter = adapter
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}