package com.example.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class CardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
): LinearLayout(context, attrs) {

    private val nameTextView: TextView
    private val ageTextView: TextView
    private val imageView: ImageView

    init {
        LayoutInflater.from(context).inflate(R.layout.custom_card_layout, this, true)

        nameTextView = findViewById(R.id.name)
        ageTextView = findViewById(R.id.age)
        imageView = findViewById(R.id.imageView)
    }

    fun setName(name: String) {
        nameTextView.text = name
    }

    fun setAge(age: String) {
        ageTextView.text = age
    }

    fun setImage(resId: Int) {
        imageView.setImageResource(resId)
    }
}
