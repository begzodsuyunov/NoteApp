package com.example.noteapp.ui.dialogs

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import com.example.noteapp.R
import com.google.android.material.imageview.ShapeableImageView

class ColorPickDialog(context: Context) : AlertDialog(context) {
    private lateinit var black: ShapeableImageView
    private lateinit var green: ShapeableImageView
    private lateinit var pink: ShapeableImageView
    private lateinit var orange: ShapeableImageView
    private lateinit var red: ShapeableImageView
    private lateinit var yellow: ShapeableImageView
    private lateinit var blue: ShapeableImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.colors)
        setCancelable(true)

        init()
        black.setOnClickListener {
            onColorClickListener?.invoke(6)
            dismiss()
        }

        green.setOnClickListener {
            onColorClickListener?.invoke(7)
            dismiss()
        }

        pink.setOnClickListener {
            onColorClickListener?.invoke(3)
            dismiss()
        }

        orange.setOnClickListener {
            onColorClickListener?.invoke(4)
            dismiss()
        }

        red.setOnClickListener {
            onColorClickListener?.invoke(2)
            dismiss()
        }

        yellow.setOnClickListener {
            onColorClickListener?.invoke(1)
            dismiss()
        }

        blue.setOnClickListener {
            onColorClickListener?.invoke(5)
            dismiss()
        }

    }


    private fun init() {
        black = findViewById(R.id.color_black)
        green = findViewById(R.id.color_green)
        pink = findViewById(R.id.color_pink)
        orange = findViewById(R.id.color_orange)
        red = findViewById(R.id.color_red)
        yellow = findViewById(R.id.color_yellow)
        blue = findViewById(R.id.color_blue)
    }

    private var onColorClickListener: ((Int) -> Unit)? = null

    fun setOnColorClickListener(block: ((Int) -> Unit)) {
        onColorClickListener = block
    }

}