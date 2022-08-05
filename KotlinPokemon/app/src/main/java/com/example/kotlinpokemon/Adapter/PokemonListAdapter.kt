package com.example.kotlinpokemon.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlinpokemon.Common.Common
import com.example.kotlinpokemon.Interface.IItemClientListener
import com.example.kotlinpokemon.Model.Pokemon
import com.example.kotlinpokemon.PokemonList
import com.example.kotlinpokemon.R
import kotlinx.android.synthetic.main.pokemon_list_item.view.*

class PokemonListAdapter (internal var context:Context,
                          internal var pokemonList:List<Pokemon>):RecyclerView.Adapter<PokemonListAdapter.MyViewHolder>() {
    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        internal var img_pokemon: ImageView
        internal var txt_pokemon: TextView

        internal var itemClickListener:IItemClientListener?=null
        fun setItemClickListener(iItemClientListener: IItemClientListener)
        {
            this.itemClickListener = iItemClientListener
        }

        init {
            img_pokemon = itemView.findViewById(R.id.pokemon_image) as ImageView
            txt_pokemon = itemView.findViewById(R.id.pokemon_name) as TextView

            itemView.setOnClickListener { view -> itemClickListener!!.onClickView(view,adapterPosition) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.pokemon_list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(context).load(pokemonList[position].img).into(holder.img_pokemon)
        holder.txt_pokemon.text = pokemonList[position].name

        holder.setItemClickListener(object:IItemClientListener {
            override fun onClickView(view: View, position: Int) {
                //Toast.makeText(context, "Click Pokemon: " +pokemonList[position],Toast.LENGTH_SHORT).show()
                LocalBroadcastManager.getInstance(context)
                    .sendBroadcast(Intent(Common.KEY_ENABLE_HOME).putExtra("position",position))
            }
            
        })
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }
}