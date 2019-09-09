package com.pcf.customize.binding

import androidx.databinding.Bindable

class GoodsBean : ObservableViewModel {

    constructor(count: Long) : super() {
        this.count = count
    }

    var count: Long = 0
        @Bindable
        set(value) {
            if (value == field) {
                return
            }
            field = value
            notifyPropertyChanged(BR._all)
        }

}