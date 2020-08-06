package com.android.acdcbridgecircuits

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.android.acdcbridgecircuits.DCBridgesAdapter.RecyclerViewHolder

class DCBridgesAdapter (private val dcBridges: ArrayList<DCBridges>) : Adapter<RecyclerViewHolder>(){

    private lateinit var onItemClickCallback: OnItemClickCallback

    inner class RecyclerViewHolder (itemView: View) : ViewHolder(itemView) {
        var ivPicture: ImageView = itemView.findViewById(R.id.iv_item_picture)
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvDetail: TextView = itemView.findViewById(R.id.tv_item_detail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecyclerViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_dcbridges, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dcBridges.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val dcBridge = dcBridges[position]
        dcBridge.picture?.let {holder.ivPicture.setImageResource(it)}
        with(holder) {
            tvName.text = dcBridge.name
            tvDetail.text = dcBridge.detail

            itemView.setOnClickListener { onItemClickCallback.onItemClicked(dcBridges[holder.adapterPosition]) }
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: DCBridges)
    }
}