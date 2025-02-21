package com.example.movie.common.base.activity

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.movie.common.extensions.applySystemBarInsets
import java.lang.reflect.Method

abstract class BaseActivity<VB : ViewBinding>(
    private val bindingInflater: (LayoutInflater) -> VB
) : AppCompatActivity() {


    private var _binding: VB? = null
    protected val binding get() = _binding!!
    private var isExpanded = false

    open fun create() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



//        window.setFlags(
//            WindowManager.LayoutParams.FLAG_SECURE,
//            WindowManager.LayoutParams.FLAG_SECURE
//        )



        _binding = bindingInflater.invoke(layoutInflater)
        setContentView(binding.root)

        create()
    }
    fun safeSetupReadMore(textView: TextView?, button: TextView?) {
        if (textView != null && button != null) {
            button.setOnClickListener {
                if (isExpanded) {
                    textView.maxLines = 3
                    textView.ellipsize = TextUtils.TruncateAt.END
                    button.text = "Read More"
                } else {
                    textView.maxLines = Int.MAX_VALUE
                    textView.ellipsize = null
                    button.text = "Read Less"
                }
                isExpanded = !isExpanded
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
