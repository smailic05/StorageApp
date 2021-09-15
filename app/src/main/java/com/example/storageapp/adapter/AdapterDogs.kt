package com.example.storageapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.storageapp.databinding.DogsItemBinding
import com.example.storageapp.room.Dog
import com.example.storageapp.ui.MainViewModel
import com.example.storageapp.ui.fragments.MainFragmentDirections



class AdapterDogs(val mainViewModel:MainViewModel): ListAdapter<Dog,DogsViewHolder>(itemComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val  binding=DogsItemBinding.inflate(layoutInflater,parent,false)
        return DogsViewHolder(binding,mainViewModel)
    }

    override fun onBindViewHolder(holder: DogsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    private companion object {

        private val itemComparator = object : DiffUtil.ItemCallback<Dog>() {

            override fun areItemsTheSame(oldItem: Dog, newItem: Dog): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Dog, newItem: Dog): Boolean {
                return oldItem.name == newItem.name&&
                        oldItem.age == newItem.age&&
                        oldItem.breed == newItem.breed
            }

            override fun getChangePayload(oldItem: Dog, newItem: Dog) = Any()
        }
    }
}


class DogsViewHolder(private val binding: DogsItemBinding,
                     val mainViewModel:MainViewModel
                     ): RecyclerView.ViewHolder(binding.root){


    fun bind(item: Dog){
        binding.root.setOnClickListener {
            val navController = Navigation.findNavController(binding.root)
            val action =MainFragmentDirections.actionMainFragmentToAddDogFragment()
            action.update=item.id
            navController.navigate(action)
        }
        binding.root.setOnLongClickListener {
                mainViewModel.deleteDogsInDatabase(item)
                return@setOnLongClickListener true
        }
        binding.textAgeDog.text=item.age.toString()
        binding.textBreedDog.text=item.breed
        binding.textNameDog.text=item.name

    }
}