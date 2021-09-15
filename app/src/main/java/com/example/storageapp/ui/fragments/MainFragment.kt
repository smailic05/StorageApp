package com.example.storageapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.example.storageapp.AdapterDogs
import com.example.storageapp.ui.MainViewModel
import com.example.storageapp.R
import com.example.storageapp.databinding.MainFragmentBinding
import com.example.storageapp.room.RoomRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment: Fragment() {
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private val model: MainViewModel by activityViewModels()

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
        val spIndex = sp.getString("sortList","0").toString()
        val spSwitch = sp.getBoolean("switch_preference",false)
        model.sqlTurnedOn = spSwitch

        val adapter = AdapterDogs(model)
        binding.recyclerView.adapter = adapter
        model.getDogsFromDatabase().observe(viewLifecycleOwner,{
            adapter.submitList(it)
        })
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.addDogFragment)
        }

        when(spIndex){
            "name"-> model.sortDataByName().observe(viewLifecycleOwner,{
                adapter.submitList(it)
            })
            "breed"-> model.sortDataByBreed().observe(viewLifecycleOwner,{
                adapter.submitList(it)
            })
            "age"->model.sortDataByAge().observe(viewLifecycleOwner,{
                adapter.submitList(it)
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}