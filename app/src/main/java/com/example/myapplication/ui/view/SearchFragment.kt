package com.example.myapplication.ui.view

import android.content.Intent
import android.nfc.NfcAdapter.EXTRA_ID
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.core.RetrofitHelper
import com.example.myapplication.data.database.entities.Favorite
import com.example.myapplication.data.model.CharacterModel
import com.example.myapplication.data.network.MarvelApiClient
import com.example.myapplication.databinding.FragmentSearchBinding
import com.example.myapplication.ui.adapters.CharacterAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private lateinit var adapter: CharacterAdapter
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initUI()
        return root
    }

    private fun initUI() {
        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?) = false
        })

        adapter = CharacterAdapter { navigateToDetail(it) }
        binding.rvCharacter.setHasFixedSize(true)
        binding.rvCharacter.layoutManager = LinearLayoutManager(context)
        binding.rvCharacter.adapter = adapter
    }

    private fun searchByName(query: String) {
        binding.searchView.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val retrofit = RetrofitHelper.getRetrofit()
            val call: Response<CharacterModel> = retrofit.create(MarvelApiClient::class.java).getCharacters(query)
            if (call.isSuccessful) {
                val response: CharacterModel? = call.body()
                Log.i("estefania", "funciona :)")
                if (response != null) {
                    Log.i("estefania", response.toString())
                    activity?.runOnUiThread {
                        response.data?.let { adapter.updateList(it.results) }
                        binding.progressBar.isVisible = false
                    }
                }
            } else {
                Log.i("estefania", "No funciona :(")
            }
            hideKeyboard()
        }
    }

    private fun hideKeyboard() {
        val imm = activity?.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.fragmentContainer.windowToken, 0)
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


