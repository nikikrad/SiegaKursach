package com.example.siegakursach.view.favorite.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.siegakursach.databinding.FragmentRegistrationBinding
import com.example.siegakursach.view.favorite.auth.model.UserResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegistrationFragment:Fragment() {

    lateinit var binding: FragmentRegistrationBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private var usersList: MutableList<UserResponse> = emptyList<UserResponse>().toMutableList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        database = Firebase.database.reference
        binding.btnLogIn.setOnClickListener {
            var checkingExistingUser = false
            if (binding.etRepeatPassword.text.toString() == binding.etPassword.text.toString()) {
                if (binding.etPassword.text.toString().length < 6) {
                    binding.tvErrorLabel.text = "Пароль должен быть больше 6 символов"
                } else {
                    database.child("users").get().addOnSuccessListener {
                        it.children.forEach { data ->
                            usersList.add(
                                UserResponse(
                                    data.child("userId").value.toString(),
                                    data.child("email").value.toString()
                                )
                            )
                        }
                        val userValue = usersList.size
                        if (userValue == 0) {
                            checkingExistingUser = checkExistingUser(
                                binding.etLogin.text.toString()
                            )
                        } else {
                            checkingExistingUser = checkExistingUser(

                                binding.etLogin.text.toString()
                            )
                        }
                        if (checkingExistingUser)
                            registerUser(usersList[userValue - 1].userId)
                        else binding.tvErrorLabel.text = "Данный пользователь зарегестрирован!"
                    }

                }
            } else {
                binding.tvErrorLabel.text = "Пароли не совпадают!"
            }
        }
    }

    private fun checkExistingUser(email: String): Boolean {
        usersList.forEach {
            if (email == it.email) {
                return false
            }
        }
        if (usersList.size == 0) {
            return true
        }
        return true
    }

    private fun registerUser(userId: String) {
        val email = binding.etLogin.text.toString()
        val password = binding.etPassword.text.toString()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnFailureListener { exception ->
                        binding.tvErrorLabel.text = exception.toString()
                    }.addOnSuccessListener {
                        database.child("users").child((userId.toInt() + 1).toString())
                            .setValue(UserResponse((userId.toInt() + 1).toString(), email))
                        checkLoggedInState()
                    }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun checkLoggedInState() {
        Toast.makeText(context, "Вы Зарегестрировались!", Toast.LENGTH_SHORT).show()
        findNavController().popBackStack()
    }


}