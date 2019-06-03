package com.learn.look.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.learn.look.Model.Person
import com.learn.look.R
import com.learn.look.Utils.IMAGE_BASE_URL
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_list_person.view.*


class PeopleAdapter(var people: List<Person>? = null) : RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {

    fun isCompleteLoadData(completion: (Boolean) -> Unit) {
        if (people?.size ?: 0 > 0) completion(true) else completion(false)
    }

    override fun getItemCount(): Int {
        return people?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val person = people?.get(position)
        holder.bindView(person)
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.recycler_list_person, parent,  false)
        return ViewHolder(cellForRow)
    }

    inner class ViewHolder(itemView: View, person: Person? = null): RecyclerView.ViewHolder(itemView) {

        private val homePersonImageView = itemView.homePersonImageView

        fun bindView(person: Person?) {
            val imageUrl = "$IMAGE_BASE_URL${person?.profilePath}"
            Picasso.get()
                .load(imageUrl)
                .into(homePersonImageView)
        }
    }
}