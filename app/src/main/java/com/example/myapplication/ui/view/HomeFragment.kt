package com.example.myapplication.ui.view

import android.content.Intent
import android.nfc.NfcAdapter.EXTRA_ID
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.core.RetrofitHelper
import com.example.myapplication.data.database.dao.FavoritesDao
import com.example.myapplication.data.model.CharacterModel
import com.example.myapplication.data.network.MarvelApiClient
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.ui.adapters.HomeAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var adapter: HomeAdapter
    private lateinit var favoritesDao: FavoritesDao

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        getAllCharacters()
        initRecyclerView()
        return root
    }

    private fun getAllCharacters() {
        binding.progressBarHome.visibility = View.VISIBLE
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val retrofit = RetrofitHelper.getRetrofit()
                val call: Response<CharacterModel> = retrofit.create(MarvelApiClient::class.java).getAllCharacters()
                if (call.isSuccessful) {
                    val response: CharacterModel? = call.body()
                    Log.i("estefania", "funciona :)")
                    if (response != null) {
                        Log.i("estefania", response.toString())
                        withContext(Dispatchers.Main) {
                            response.data?.let { adapter.setData(it.results) }
                            binding.progressBarHome.visibility = View.GONE
                        }
                    }
                } else {
                    Log.i("estefania", "No funciona :(")
                }
            } catch (e: Exception) {
                Log.e("estefania", "Error al obtener los personajes: ${e.message}")
            }
        }
    }

    private fun initRecyclerView() {
        adapter = HomeAdapter { navigateToDetail(it) }
        binding.rvCharacterHome.layoutManager = LinearLayoutManager(context)
        binding.rvCharacterHome.adapter = adapter
    }
    private fun navigateToDetail(id: Int) {
        val bundle = Bundle()
        bundle.putInt(EXTRA_ID, id)
        val fragment = CharacterDetailFragment()
        fragment.arguments = bundle
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}