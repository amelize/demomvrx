package ru.sebely.demoapplication.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import ru.sebely.demoapplication.R
import ru.sebely.demoapplication.databinding.ButtonViewBinding

interface OnAddListener {
    fun onAdd(id: Int)
}

object ButtonAdapters {
    @JvmStatic
    @BindingAdapter("price")
    fun setPrice(button: AddButtonView, price: String?) {
        button.setText(price)
    }

    @JvmStatic
    @BindingAdapter("itemId")
    fun setItemId(button: AddButtonView, item: Int?) {
        button.setItemId(item)
    }
}

class AddButtonView(context: Context, attrs: AttributeSet): FrameLayout(context, attrs), OnAddListener {
    var binding : ButtonViewBinding = DataBindingUtil.inflate<ButtonViewBinding>(LayoutInflater.from(context), R.layout.button_view, this, true)

    init {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.AddButtonView)
        binding.text = attributes.getString(R.styleable.AddButtonView_text)
        binding.clickHandler = this

        attributes.recycle()
    }

    fun setText(string : String?) {
        binding.text = string
    }

    override fun onAdd(id: Int) {
        binding.addText.alpha = 0f
        binding.addText.visibility = View.VISIBLE

        binding.addText.animate()
            .withEndAction {
                binding.addText.animate()
                    .alpha(0.0f)
                    .setStartDelay(1000)
                    .withEndAction {
                        binding.addText.visibility = View.GONE
                    }
                    .setDuration(150)
                    .start()

            }
            //.setInterpolator()
            .setDuration(150)
            .alpha(1.0f)
            .start()


    }

    fun setItemId(id: Int?) {
        binding.item = id
    }
}