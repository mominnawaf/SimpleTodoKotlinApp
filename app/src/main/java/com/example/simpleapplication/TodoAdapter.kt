package com.example.simpleapplication

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*

class TodoAdapter(
    private val todos : MutableList<Todo>
): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    class  TodoViewHolder (itemView :View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item,
                parent,
                false
            )
        )
    }
    fun addTodo(todo : Todo){
        todos.add(todo)
        notifyItemInserted(todos.size -1)
    }
     fun deleteTodo(){
        todos.removeAll { todo ->
            todo.status
        }
        notifyDataSetChanged()
    }
    private fun toggleStrikeThrough(tvTodoText : TextView, isChecked: Boolean){
        if (isChecked){
            tvTodoText.paintFlags = tvTodoText.paintFlags or STRIKE_THRU_TEXT_FLAG
        }
        else{
            tvTodoText.paintFlags = tvTodoText.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todos[position]
        holder.itemView.apply {
            tvTodoTitle.text =curTodo.title
            checkBox.isChecked = curTodo.status
            toggleStrikeThrough(tvTodoTitle,curTodo.status)
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                    toggleStrikeThrough(tvTodoTitle,isChecked)
                curTodo.status = !curTodo.status
                      }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }

}