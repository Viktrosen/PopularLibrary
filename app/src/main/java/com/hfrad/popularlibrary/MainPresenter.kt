package com.hfrad.popularlibrary

class MainPresenter(val view: MainView) {
    val model = CountersModel()


    fun counterClick1(){
        val nextValue = model.next(0)
        view.setButtonText(0, nextValue.toString())
    }

    fun counterClick2(){
        val nextValue = model.next(1)
        view.setButtonText(1, nextValue.toString())
    }
    fun counterClick3(){
        val nextValue = model.next(2)
        view.setButtonText(2, nextValue.toString())
    }


}
