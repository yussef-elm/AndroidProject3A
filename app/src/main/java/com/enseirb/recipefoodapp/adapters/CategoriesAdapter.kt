package com.enseirb.recipefoodapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.enseirb.recipefoodapp.R
import com.enseirb.recipefoodapp.models.Categorie

class CategoriesAdapter(private val context: Context,private val categories: List<Categorie>) : RecyclerView.Adapter<CategorieViewHolder>()  {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategorieViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.gridrecycler_item, parent, false)
        return CategorieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CategorieViewHolder, position: Int) {
        Glide.with(context)
            .load(categories.get(position).strCategoryThumb)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.strCategoryThumb);
        holder.strCategory.setText(categories.get(position).strCategory)
       // holder.strCategoryDescription.setText(categories.get(position).strCategoryDescription )
    }

    override fun getItemCount(): Int {
        return categories.count()
    }
}

class CategorieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var strCategory: TextView = itemView.findViewById(R.id.strCategory)
    var strCategoryThumb: ImageView = itemView.findViewById(R.id.strCategoryThumb)
    //var strCategoryDescription: TextView = itemView.findViewById(R.id.strCategoryDescription)
}