package com.example.simpleapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var todoAdapter: TodoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter = TodoAdapter(mutableListOf())
        rvTodoListItem.adapter =todoAdapter
        rvTodoListItem.layoutManager = LinearLayoutManager(this)
        btnAddToDO.setOnClickListener{
            val title = etAddToDo.text.toString()
            if(title.isNotEmpty()){
                val newTodo = Todo(title)
                todoAdapter.addTodo(newTodo)
                etAddToDo.text.clear()
            }
            else{
                Toast.makeText(this, "Fuck You", Toast.LENGTH_SHORT).show()
            }
        }
        btnCancel.setOnClickListener {
            todoAdapter.deleteTodo()
        }
    }
}