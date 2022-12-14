package com.enseirb.recipefoodapp.models.modelsresponse

import com.enseirb.recipefoodapp.models.Categorie
import java.io.Serializable

class CategoriesResponse : Serializable {

    var categories : ArrayList<Categorie> = arrayListOf()
}