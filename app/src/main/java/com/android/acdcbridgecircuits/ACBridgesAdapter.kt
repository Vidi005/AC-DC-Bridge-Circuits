package com.android.acdcbridgecircuits

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.android.acdcbridgecircuits.ACBridgesAdapter.RecyclerViewHolder

class ACBridgesAdapter (private val acBridges: ArrayList<ACBridges>) : Adapter<RecyclerViewHolder>(){

    private lateinit var onItemClickCallback: OnItemClickCallback

    inner class RecyclerViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivPicture: ImageView = itemView.findViewById(R.id.iv_item_picture)
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvDetail: TextView = itemView.findViewById(R.id.tv_item_detail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_acbridges, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return acBridges.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val acBridge = acBridges[position]
        acBridge.picture?.let {holder.ivPicture.setImageResource(it)}
        with(holder) {
            tvName.text = acBridge.name
            tvDetail.text = acBridge.detail

            itemView.setOnClickListener { onItemClickCallback.onItemClicked(acBridges[holder.adapterPosition]) }
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ACBridges)
    }
}