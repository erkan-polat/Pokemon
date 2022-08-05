package com.example.kotlinpokemon

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinpokemon.Adapter.PokemonListAdapter
import com.example.kotlinpokemon.Common.Common
import com.example.kotlinpokemon.Common.ItemOffsetDecoration
import com.example.kotlinpokemon.Retrofit.IPokemonList
import com.example.kotlinpokemon.Retrofit.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_pokemon_list.*
import retrofit2.Retrofit


class PokemonList : Fragment() {
    internal var compositeDisposable =CompositeDisposable()
    internal var iPokemonList:IPokemonList

    internal lateinit var recyclerView: RecyclerView

    init{
        val retrofit = RetrofitClient.instance
        iPokemonList = retrofit.create(IPokemonList::class.java)
    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val itemView = inflater.inflate(R.layout.fragment_pokemon_list, container, false)

        recyclerView = itemView.findViewById(R.id.pokemon_recyclerview) as RecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(activity,2)
        val itemDecoration = ItemOffsetDecoration(activity!!,R.dimen.spacing)
        recyclerView.addItemDecoration(itemDecoration)

        fetchData()
        return itemView
    }

    @SuppressLint("UseRequireInsteadOfGet")
    private fun fetchData() {
        compositeDisposable.add(iPokemonList.listPokemon
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{pokemonDex ->
                Common.pokemonList = pokemonDex.pokemon!!
                val adapter = PokemonListAdapter(activity!!,Common.pokemonList)
                recyclerView.adapter = adapter
            }
        )
    }
}