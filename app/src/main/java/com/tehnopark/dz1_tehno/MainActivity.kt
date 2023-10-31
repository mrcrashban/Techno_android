package com.tehnopark.dz1_tehno;

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var addButton: Button
    private val items = mutableListOf<RectangleItem>()
    private lateinit var adapter: RectangleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        addButton = findViewById(R.id.addButton)
        adapter = RectangleAdapter(this, items)
        recyclerView.adapter = adapter

        // Определение ориентации и установка количества колонок
        val orientation = resources.configuration.orientation
        val layoutManager = GridLayoutManager(this, if (orientation == Configuration.ORIENTATION_PORTRAIT) 3 else 4)
        recyclerView.layoutManager = layoutManager

        addButton.setOnClickListener {
            val index = items.size
            val isEven = index % 2 == 0
            items.add(RectangleItem(index, isEven))
            adapter.notifyItemInserted(items.size - 1)
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        // Сохраняем индексы и флаги для каждого элемента в списке
        val indices = items.map { it.index }.toIntArray()
        val isEvenFlags = items.map { it.isEven }.toBooleanArray()

        outState.putIntArray("indices", indices)
        outState.putBooleanArray("isEvenFlags", isEvenFlags)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        // Восстанавливаем список на основе сохраненных индексов и флагов
        val indices = savedInstanceState.getIntArray("indices") ?: intArrayOf()
        val isEvenFlags = savedInstanceState.getBooleanArray("isEvenFlags") ?: booleanArrayOf()

        items.clear()
        for (i in indices.indices) {
            val index = indices[i]
            val isEven = isEvenFlags[i]
            items.add(RectangleItem(index, isEven))
        }

        adapter.notifyItemInserted(items.size - 1)
    }

    data class RectangleItem(val index: Int, val isEven: Boolean)
}
