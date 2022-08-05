package com.example.kotlinpokemon.Adapter

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.kotlinpokemon.Common.Common
import com.example.kotlinpokemon.Interface.IItemClientListener
import com.example.kotlinpokemon.R
import com.robertlevonyan.views.chip.Chip




class PokemonTypeAdapter(internal var context:Context,internal var typeList:List<String>) :
RecyclerView.Adapter<PokemonTypeAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.chip_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.chip.text = typeList[position]

    }

    override fun getItemCount(): Int {
        return typeList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var chip: Chip
        internal var iItemClickListener: IItemClientListener? = null


        init {
            chip = itemView.findViewById(R.id.chip) as Chip
            chip.setOnClickListener  {
                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show()
            }
        }
    }

}