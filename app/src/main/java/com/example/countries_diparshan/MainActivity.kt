package com.example.countries_diparshan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countries_diparshan.activities.FavouriteActivity
import com.example.countries_diparshan.adapters.CountryAdapter
import com.example.countries_diparshan.api.MyInterface
import com.example.countries_diparshan.api.RetrofitInstance
import com.example.countries_diparshan.databinding.ActivityMainBinding
import com.example.countries_diparshan.models.Country
import com.example.countries_diparshan.models.Name
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    lateinit var adapter: CountryAdapter

    var name1: Name = Name("Nepal", "hari")
    var name2: Name = Name("India", "hari")
    var datasource: MutableList<Country> = mutableListOf<Country>(
        Country(name1),
        Country(name2),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        setSupportActionBar(this.binding.menuToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        adapter = CountryAdapter(datasource,{pos -> rowClicked(pos) })
        // recyclerView
        binding.rvItems.adapter = adapter
        binding.rvItems.layoutManager = LinearLayoutManager(this)
        binding.rvItems.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )
    }

    init {
       var api: MyInterface = RetrofitInstance.retrofitService
            lifecycleScope.launch {

                val countryList: List<Country> = api.getAllCountries()

                //output it to the UI
//                var output:String = ""
//                for (country in countryList) {
//                    output += "${country.name.common} \n"
//                }
//
//                Log.d("TAG", countryList.toString())

//            binding.tvResults.setText(output)
                datasource.clear()
                datasource.addAll(countryList)
                adapter.notifyDataSetChanged()
            }
    }

    // click handler for the row
    fun rowClicked(position:Int) {
        val snackbar = Snackbar.make(binding.root, "Clicked on ${position}", Snackbar.LENGTH_LONG)
        snackbar.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_options, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mi_favourite -> {
                val favouriteIntent = Intent(this, FavouriteActivity::class.java)
//                historyIntent.putExtra("history_extra", ticketList.toTypedArray())
                startActivity(favouriteIntent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

}