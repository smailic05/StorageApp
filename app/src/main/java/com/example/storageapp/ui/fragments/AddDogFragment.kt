package com.example.storageapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.storageapp.room.Dog
import com.example.storageapp.ui.MainViewModel
import com.example.storageapp.databinding.AddDogFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddDogFragment: Fragment() {
    private var _binding: AddDogFragmentBinding? = null
    private val binding get() = _binding!!
    private val model: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AddDogFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addButton.setOnClickListener {
            val name = binding.textName.text.toString()
            val age = binding.textAge.text.toString().toInt()
            val breed= binding.textBreed.text.toString()
            model.addDogsToDatabase(Dog(name = name,age= age,breed = breed))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}