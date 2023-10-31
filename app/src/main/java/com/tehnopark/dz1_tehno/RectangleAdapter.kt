package com.tehnopark.dz1_tehno;

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class RectangleAdapter(private val context: Context, private val items: List<MainActivity.RectangleItem>) : RecyclerView.Adapter<RectangleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_rectangle, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.itemView.findViewById<TextView>(R.id.textView).text = item.index.toString()
        holder.itemView.setBackgroundColor(
            ContextCompat.getColor(
                context,
                if (item.isEven) R.color.red else R.color.blue
            )
        )
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
