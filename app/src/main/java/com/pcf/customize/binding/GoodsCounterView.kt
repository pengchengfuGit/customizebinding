package com.pcf.customize.binding

import android.content.Context
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.InverseBindingListener


class GoodsCounterView : LinearLayout {
    var listener :InverseBindingListener ?= null
    lateinit var editText: EditText
    lateinit var reduceButton: ImageButton
    lateinit var addButton: ImageButton

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
//        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.goods_Counter)
//        val count = typedArray.getInt(R.styleable.goods_Counter_count, 1)

        setVerticalGravity(HORIZONTAL)
        gravity = Gravity.CENTER_VERTICAL

        val buttonWidth = DensityUtil.dp2px(37.33f)

        reduceButton = ImageButton(context)
        reduceButton.layoutParams = ViewGroup.LayoutParams(buttonWidth.toInt(), buttonWidth.toInt())
        reduceButton.setImageResource(R.drawable.selector_good_reduce)
        reduceButton.background = resources.getDrawable(R.color.gray_fbfbfb)
        reduceButton.isEnabled = false
        val textViewWidth = DensityUtil.dp2px(47.33f)
        editText = EditText(context)
        editText.width = textViewWidth.toInt()
        editText.height = buttonWidth.toInt()
        editText.gravity = Gravity.CENTER
        editText.setTextColor(resources.getColor(R.color.blue_32374a))
        editText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 19f)
        editText.inputType = InputType.TYPE_CLASS_NUMBER
        editText.background = resources.getDrawable(android.R.color.transparent)
//        editText.setText(count.toString())
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                parseNum()
                //触发反向数据的传递
//                if (mInverseBindingListener != null) {
//                    mInverseBindingListener?.onChange()
//                }
            }
        })
        addButton = ImageButton(context)
        addButton.layoutParams = LayoutParams(buttonWidth.toInt(), buttonWidth.toInt())
        addButton.setImageResource(R.drawable.selector_good_add)
        addButton.background = resources.getDrawable(R.color.gray_fbfbfb)

        addView(reduceButton)
        addView(editText)
        addView(addButton)

        reduceButton.setOnClickListener {
            var num = editText.text.toString().toLong()
            num -= 1
            editText.setText(if (num > 1) num.toString() else "1")
            if (num <= 1) {
                reduceButton.isEnabled = false
            }
        }
        addButton.setOnClickListener {
            var num = editText.text.toString().toLong()
            num += 1
            editText.setText(num.toString())
            reduceButton.isEnabled = true
        }
        editText.filters = arrayOf(NumberInputFilter())
    }

    private fun parseNum() {
        val text = editText.text.toString()
        if (text.isNotEmpty()) {
            var num = text.toLong()
            addButton.isEnabled = true
            reduceButton.isEnabled = num > 1
        } else {
            reduceButton.isEnabled = false
            addButton.isEnabled = false
        }
        listener?.onChange()
    }

    fun setText(text: String) {
        editText.setText(text)
    }

    fun getText(): String {
        return editText.text.toString()
    }
}