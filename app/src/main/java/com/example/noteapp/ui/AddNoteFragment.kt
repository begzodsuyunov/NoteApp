package com.example.noteapp.ui

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.chinalwb.are.AREditText
import com.chinalwb.are.styles.toolbar.ARE_ToolbarDefault
import com.chinalwb.are.styles.toolbar.IARE_Toolbar
import com.chinalwb.are.styles.toolitems.ARE_ToolItem_AlignmentCenter
import com.chinalwb.are.styles.toolitems.ARE_ToolItem_AlignmentLeft
import com.chinalwb.are.styles.toolitems.ARE_ToolItem_AlignmentRight
import com.chinalwb.are.styles.toolitems.ARE_ToolItem_At
import com.chinalwb.are.styles.toolitems.ARE_ToolItem_Bold
import com.chinalwb.are.styles.toolitems.ARE_ToolItem_FontColor
import com.chinalwb.are.styles.toolitems.ARE_ToolItem_Hr
import com.chinalwb.are.styles.toolitems.ARE_ToolItem_Italic
import com.chinalwb.are.styles.toolitems.ARE_ToolItem_Link
import com.chinalwb.are.styles.toolitems.ARE_ToolItem_ListBullet
import com.chinalwb.are.styles.toolitems.ARE_ToolItem_ListNumber
import com.chinalwb.are.styles.toolitems.ARE_ToolItem_Quote
import com.chinalwb.are.styles.toolitems.ARE_ToolItem_Strikethrough
import com.chinalwb.are.styles.toolitems.ARE_ToolItem_Subscript
import com.chinalwb.are.styles.toolitems.ARE_ToolItem_Superscript
import com.chinalwb.are.styles.toolitems.ARE_ToolItem_Underline
import com.chinalwb.are.styles.toolitems.IARE_ToolItem
import com.example.noteapp.R
import com.example.noteapp.data.models.NoteData
import com.example.noteapp.databinding.FragmentAddNoteBinding
import com.example.noteapp.presenter.AddNoteViewModel
import com.example.noteapp.presenter.impl.AddNoteViewModelImpl
import com.example.noteapp.ui.dialogs.ColorPickDialog
import com.example.noteapp.utils.getDrawables
import com.google.android.material.imageview.ShapeableImageView
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import ru.ldralighieri.corbind.widget.textChanges
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class AddNoteFragment : Fragment(R.layout.fragment_add_note) {

    private lateinit var mToolbar: IARE_Toolbar
    private lateinit var mEditText: AREditText
    private var scrollerAtEnd = false

    private lateinit var btnSave: ImageButton
    private lateinit var btnBack: ImageButton
    private lateinit var titleInput: EditText
    private lateinit var colorPicker: ShapeableImageView
    private var colorNumber = R.drawable.color_pick_yellow

    private val viewModel: AddNoteViewModel by viewModels<AddNoteViewModelImpl>()
    private val viewBinding by viewBinding(FragmentAddNoteBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val items = listOf(
            "High",
            "Medium",
            "Simple")

        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)
        (viewBinding.menu.editText as? AutoCompleteTextView)?.setAdapter(adapter)


        view.apply {

            btnBack = findViewById(R.id.btnBackFromAdd)
            btnSave = findViewById(R.id.btnSave)
            titleInput = findViewById(R.id.text_input)
            colorPicker = findViewById(R.id.colorPick)
            colorPicker.setImageResource(colorNumber)

            mToolbar = findViewById(R.id.areToolbar)
            val bold: IARE_ToolItem = ARE_ToolItem_Bold()
            val italic: IARE_ToolItem = ARE_ToolItem_Italic()
            val underline: IARE_ToolItem = ARE_ToolItem_Underline()
            val strikethrough: IARE_ToolItem = ARE_ToolItem_Strikethrough()
            val quote: IARE_ToolItem = ARE_ToolItem_Quote()
            val color: IARE_ToolItem = ARE_ToolItem_FontColor()
            val listNumber: IARE_ToolItem = ARE_ToolItem_ListNumber()
            val listBullet: IARE_ToolItem = ARE_ToolItem_ListBullet()
            val hr: IARE_ToolItem = ARE_ToolItem_Hr()
            val link: IARE_ToolItem = ARE_ToolItem_Link()
            val subscript: IARE_ToolItem = ARE_ToolItem_Subscript()
            val superscript: IARE_ToolItem = ARE_ToolItem_Superscript()
            val left: IARE_ToolItem = ARE_ToolItem_AlignmentLeft()
            val center: IARE_ToolItem = ARE_ToolItem_AlignmentCenter()
            val right: IARE_ToolItem = ARE_ToolItem_AlignmentRight()
            val at: IARE_ToolItem = ARE_ToolItem_At()

            mToolbar.addToolbarItem(bold)
            mToolbar.addToolbarItem(italic)
            mToolbar.addToolbarItem(underline)
            mToolbar.addToolbarItem(strikethrough)
            mToolbar.addToolbarItem(quote)
            mToolbar.addToolbarItem(color)
            mToolbar.addToolbarItem(listNumber)
            mToolbar.addToolbarItem(listBullet)
            mToolbar.addToolbarItem(hr)
            mToolbar.addToolbarItem(link)
            mToolbar.addToolbarItem(subscript)
            mToolbar.addToolbarItem(superscript)
            mToolbar.addToolbarItem(left)
            mToolbar.addToolbarItem(center)
            mToolbar.addToolbarItem(right)
            mToolbar.addToolbarItem(at)
            mEditText = findViewById(R.id.arEditText)
            mEditText.setToolbar(mToolbar)

            val imageView: ImageView = findViewById(R.id.arrow)
            if (mToolbar is ARE_ToolbarDefault) {
                (mToolbar as ARE_ToolbarDefault).viewTreeObserver.addOnScrollChangedListener {
                    val scrollX = (mToolbar as ARE_ToolbarDefault).scrollX
                    val scrollWidth = (mToolbar as ARE_ToolbarDefault).width
                    val fullWidth = (mToolbar as ARE_ToolbarDefault).getChildAt(0).width
                    scrollerAtEnd = if (scrollX + scrollWidth < fullWidth) {
                        imageView.setImageResource(R.drawable.ic_baseline_arrow_forward_24)
                        false
                    } else {
                        imageView.setImageResource(R.drawable.ic_baseline_arrow_back_24)
                        true
                    }
                }
            }

            imageView.setOnClickListener {
                scrollerAtEnd = if (scrollerAtEnd) {
                    (mToolbar as ARE_ToolbarDefault?)!!.smoothScrollBy(-Int.MAX_VALUE, 0)
                    false
                } else {
                    val hsWidth = (mToolbar as ARE_ToolbarDefault?)!!.getChildAt(0).width
                    (mToolbar as ARE_ToolbarDefault?)!!.smoothScrollBy(hsWidth, 0)
                    true
                }
            }


            combine(
                titleInput.textChanges()
                    .map { it.isNotEmpty() },

                mEditText.textChanges()
                    .map { it.isNotEmpty() },

                transform = { title, desc -> title && desc }
            )
                .onEach {
                    btnSave.isEnabled = it
                    if (it) btnSave.setColorFilter(Color.GREEN) else btnSave.setColorFilter(
                        Color.GRAY
                    )
                }
                .flowWithLifecycle(lifecycle)
                .launchIn(lifecycleScope)



            btnSave.setOnClickListener {

                val title = titleInput.text.toString()
                val desc = mEditText.html
                val date = SimpleDateFormat("yyyy.mm.dd HH:mm", Locale.ROOT).format(Date())

                val priority = viewBinding.menu.editText?.text.toString()
                var high = false
                var medium = false
                var simple = false
                when(priority){
                    "High" -> {
                        high = true
                    }
                    "Medium" -> {
                        medium = true
                    }
                    "Default" -> {
                        simple = true
                    }
                    else -> {
                        simple = true
                    }
                }

                val note = NoteData(
                    0,
                    title,
                    desc,
                    date,
                    colorNumber,
                    high,
                    medium,
                    simple
                )

                viewModel.addToBase(note)
                Toast.makeText(requireContext(), "ADDED", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            }


            btnBack.setOnClickListener {
                findNavController().navigateUp()
            }


            colorPicker.setOnClickListener{
                val dialog = ColorPickDialog(requireContext())

                dialog.setOnColorClickListener {
                    colorNumber = it
                    colorPicker.setImageResource(it.getDrawables())
                }

                dialog.show()
            }

        }











    }



}