package com.example.storageapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.storageapp.databinding.DogsItemBinding
import com.example.storageapp.room.Dog


class AdapterDogs: ListAdapter<Dog,DogsViewHolder>(itemComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val  binding=DogsItemBinding.inflate(layoutInflater,parent,false)
        return DogsViewHolder(binding)
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

class DogsViewHolder(private val binding: DogsItemBinding):RecyclerView.ViewHolder(binding.root){
    fun bind(item: Dog){
        binding.textAgeDog.text=item.age.toString()
        binding.textBreedDog.text=item.breed
        binding.textNameDog.text=item.name

    }
}