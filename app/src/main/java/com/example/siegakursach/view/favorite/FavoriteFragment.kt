package com.example.siegakursach.view.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.siegakursach.R
import com.example.siegakursach.databinding.FragmentFavoriteBinding
import com.example.siegakursach.view.favorite.auth.model.GameRequest
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FavoriteFragment: Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        database = Firebase.database.reference
        checkLoggedInState()

        binding.btnAuth.setOnClickListener {
            findNavController().navigate(R.id.action_favoriteFragment2_to_loginFragment)
        }
        binding.ivLogout.setOnClickListener {
            auth.signOut()
            binding.rvFavorite.adapter = null
            checkLoggedInState()
        }
    }

    private fun checkLoggedInState() {
        auth = FirebaseAuth.getInstance()
        database = Firebase.database.reference
        if (auth.currentUser !== null) {
            binding.ivLogout.isVisible = true
            binding.btnAuth.isVisible = false
            binding.textView.isVisible = false
            processDataFromDatabase()
        } else {
            binding.ivLogout.isVisible = false
            binding.btnAuth.isVisible = true
            binding.textView.isVisible = true
        }
    }

    private var movieList: MutableList<GameRequest> =
        emptyList<GameRequest>().toMutableList()
    private lateinit var adapter: FavoriteAdapter

    private fun processDataFromDatabase() {
        movieList.clear()
        database.child(auth.currentUser?.email.toString().substringBefore("@")).get()
            .addOnSuccessListener { animeId ->
                animeId.children.forEach { aboutAnime ->
                    movieList.add(
                        GameRequest(
                            aboutAnime.child("game_id").value.toString(),
                            aboutAnime.child("home").value.toString(),
                            aboutAnime.child("away").value.toString(),
                            aboutAnime.child("time").value.toString()
                        )
                    )
                }

                adapter = FavoriteAdapter(movieList)
                val linearLayoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                binding.rvFavorite.layoutManager = linearLayoutManager
                binding.rvFavorite.adapter = adapter
            }
    }
}