package com.example.countries_diparshan.repositories

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.countries_diparshan.models.Country
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firestore.v1.StructuredAggregationQuery.Aggregation.Count


class FavouritesRepository(private val context : Context) {

    private val TAG = this.toString();
    //get an instance of firestore database
    private val db = Firebase.firestore

    private val COLLECTION_COUNTRIES = "Countries"
    private val FIELD_NAME = "Name"

    var allFavourites : MutableLiveData<List<Country>> = MutableLiveData<List<Country>>()

    fun addCountryToDB(newFavourite: Country){
        try {
            val data: MutableMap<String, Any> = HashMap()

            data[FIELD_NAME] = newFavourite.name.common
            db.collection(COLLECTION_COUNTRIES)
                .add(data)
                .addOnSuccessListener { docRef ->

                    Log.d(TAG, "addCountryToDB: Document successfully added with ID : ${docRef.id}")
                }
                .addOnFailureListener { ex ->
                    Log.e(TAG, "addCountryToDB: Exception ocurred while adding a document : $ex", )
                }
        }catch (ex : java.lang.Exception){
            Log.d(TAG, "addCountryToDB: Couldn't perform insert on Favourites collection due to exception $ex")
        }
    }

}