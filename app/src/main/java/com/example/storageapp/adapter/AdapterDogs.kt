package com.example.storageapp

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import bag.dev.rs_task_4_db.data.sqlite.SqliteDatabaseHelper
import com.example.storageapp.databinding.DogsItemBinding
import com.example.storageapp.room.DatabaseDao
import com.example.storageapp.room.Dog
import com.example.storageapp.room.RoomRepository
import com.example.storageapp.ui.MainActivity
import com.example.storageapp.ui.MainViewModel
import com.example.storageapp.ui.fragments.MainFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject


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