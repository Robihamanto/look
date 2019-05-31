package com.learn.look.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.learn.look.Model.Company
import com.learn.look.R
import com.learn.look.Utils.IMAGE_BASE_URL
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.reycler_list_company.view.*

class CompanyAdapter(var companies: List<Company>? = null) : RecyclerView.Adapter<CompanyAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return companies?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val company = companies?.get(position)
        holder.bindView(company)
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.reycler_list_company, parent,  false)
        return ViewHolder(cellForRow)
    }


    inner class ViewHolder(itemView: View, company: Company? = null): RecyclerView.ViewHolder(itemView) {

        val companyImageView = itemView.movieDetailCompanyLogoImageView
        val companyName = itemView.movieDetailCompanyNameTextView

        fun bindView(company: Company?) {
            Picasso.get()
                .load("$IMAGE_BASE_URL${company?.logoPath}")
                .into(companyImageView)
            companyName.text = company?.name
        }

    }
}