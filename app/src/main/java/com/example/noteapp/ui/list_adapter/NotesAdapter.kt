package com.example.noteapp.ui.list_adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chinalwb.are.render.AreTextView
import com.example.noteapp.R
import com.example.noteapp.data.models.NoteData
import com.example.noteapp.utils.getDrawables
import com.google.android.material.card.MaterialCardView
import javax.security.auth.callback.Callback

class NotesAdapter : ListAdapter<NoteData, NotesAdapter.ViewHolder>(CallBack) {

    private var onClickListener: ((NoteData) -> Unit)? = null
    private var onLongClickListener: ((NoteData) -> Unit)? = null

    fun setOnClickListener(block: ((NoteData) -> Unit)) {
        onClickListener = block
    }

    fun setOnLongClickListener(block: ((NoteData) -> Unit)) {
        onLongClickListener = block
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val title: TextView = view.findViewById(R.id.note_title)
        val desc: AreTextView = view.findViewById(R.id.note_text)
        val date: TextView = view.findViewById(R.id.note_date)
        val card : MaterialCardView = view.findViewById(R.id.materialCardView)
        val topBar: ImageView = view.findViewById(R.id.item_color)
        val bottomBar: ImageView = view.findViewById(R.id.item_bottom)


        fun bind(position: Int){

            title.text = getItem(position).title
            val html = getItem(position).description
            desc.fromHtml(html)
            date.text = getItem(position).date

            topBar.setImageResource(getItem(position).colorNumber.getDrawables())
            bottomBar.setImageResource(getItem(position).colorNumber.getDrawables())

            card.bringToFront()
            card.setOnClickListener{
                onClickListener?.invoke(getItem(position))
            }
//
            desc.setOnClickListener {
                onClickListener?.invoke(getItem(position))
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NotesAdapter.ViewHolder, position: Int) {
        holder.bind(position)
    }
    object CallBack: DiffUtil.ItemCallback<NoteData>() {
        override fun areItemsTheSame(oldItem: NoteData, newItem: NoteData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NoteData, newItem: NoteData): Boolean {
            return oldItem.id == newItem.id
                    && oldItem.date == newItem.date
                    && oldItem.description == newItem.description
                    && oldItem.colorNumber == newItem.colorNumber
                    && oldItem.high == newItem.high
                    && oldItem.medium == newItem.medium
                    && oldItem.simple == newItem.simple
        }

    }

}