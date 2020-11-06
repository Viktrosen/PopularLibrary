package com.hfrad.popularlibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),MainView {

    val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listener1 = View.OnClickListener {
            presenter.counterClick1()
        }
        val listener2 = View.OnClickListener {
            presenter.counterClick2()
        }
        val listener3 = View.OnClickListener {
            presenter.counterClick3()
        }

        btn_counter1.setOnClickListener(listener1)
        btn_counter2.setOnClickListener(listener2)
        btn_counter3.setOnClickListener(listener3)
    }

    override fun setButtonText(index: Int, text: String) {
        when(index){
            0 -> btn_counter1.text = text
            1 -> btn_counter2.text = text
            2 -> btn_counter3.text = text
        }
    }
}