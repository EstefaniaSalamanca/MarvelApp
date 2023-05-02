package com.example.myapplication.ui.view

import android.content.Context
import android.nfc.NfcAdapter.EXTRA_ID
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.MarvelApp
import com.example.myapplication.core.RetrofitHelper
import com.example.myapplication.data.database.CharacterDatabase
import com.example.myapplication.data.database.dao.FavoritesDao
import com.example.myapplication.data.database.entities.Favorite

import com.example.myapplication.data.model.CharacterDetailModel
import com.example.myapplication.data.model.ResultDetailModel
import com.example.myapplication.data.network.MarvelApiClient
import com.example.myapplication.databinding.FragmentCharacterDetailBinding
import com.example.myapplication.ui.viewmodel.CharacterDetailViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class CharacterDetailFragment : Fragment() {



    private var _binding: FragmentCharacterDetailBinding? = null
    private lateinit var viewModel: CharacterDetailViewModel
    private var id: Int = 0
    private val binding get() = _binding ?: throw IllegalStateException("Binding is null")
    private lateinit var favoritesDao: FavoritesDao
    private lateinit var applicationContext: Context
private var itemDetalle: ResultDetailModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        favoritesDao = MarvelApp.database.favoritesDao()

        arguments?.let {
            id = it.getInt(EXTRA_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        applicationContext = requireContext().applicationContext
        viewModel = ViewModelProvider(this).get(CharacterDetailViewModel::class.java)

        favoritesDao = CharacterDatabase.getInstance(applicationContext).favoritesDao()



        binding.btnFav.setOnClickListener {

                // Agregar a favoritos
                CoroutineScope(Dispatchers.IO).launch {

                    favoritesDao.insertFavorite(Favorite(itemDetalle!!.characterId,
                        itemDetalle!!.characterName , itemDetalle!!.imageUrl))
                    Log.i("estefania", favoritesDao.getAllFavorites().toString())
                }

        }

    getCharactersInformation(id)
    }

    private fun getCharactersInformation(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val retrofit = RetrofitHelper.getRetrofit()
            val characterDetail: Response<CharacterDetailModel> =
                retrofit.create(MarvelApiClient::class.java).getCharacterDetail(id)
            val response: CharacterDetailModel? = characterDetail.body()

            if (response != null) {
                Log.i("Estefania", response.toString())

                val characters: List<ResultDetailModel> = response.data?.results ?: emptyList()
                itemDetalle=characters[0]
                withContext(Dispatchers.Main) {
                    createUI(characters)
                }
            }

        }
    }

    private fun createUI(characters: List<ResultDetailModel>) {
        for (character in characters) {
            Picasso.get().load(character.image.path + "." + character.image.extension)
                .into(binding.ivCharacter)

            binding.tvDescription.text = character.description

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}