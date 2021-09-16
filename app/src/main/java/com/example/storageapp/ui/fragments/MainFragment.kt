package com.example.storageapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.example.storageapp.AdapterDogs
import com.example.storageapp.DogsViewHolder
import com.example.storageapp.ui.MainViewModel
import com.example.storageapp.R
import com.example.storageapp.databinding.MainFragmentBinding
import com.example.storageapp.room.Dog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment: Fragment(),DogsViewHolder.DeleteListener {
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private val model: MainViewModel by activityViewModels()
    private var adapter= AdapterDogs(this)
    var spIndex= "name"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sp = PreferenceManager.getDefaultSharedPreferences(requireContext())
        spIndex = sp.getString("sortList","name").toString()
        val spSwitch = sp.getBoolean("switch_preference",false)
        model.sqlTurnedOn = spSwitch

        val adapter = AdapterDogs(this)
        binding.recyclerView.adapter = adapter
        model.arrayOfDogs.observe(viewLifecycleOwner,{
            adapter.submitList(it)
        })
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.addDogFragment)
        }

        updateArray()
    }

    private fun updateArray() {
        when (spIndex) {
            "name" -> model.sortDataByName()

            "breed" -> model.sortDataByBreed()

            "age" -> model.sortDataByAge()

        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    override fun delete(dog: Dog) {
        model.deleteDogsInDatabase(dog)
        updateArray()
    }
}