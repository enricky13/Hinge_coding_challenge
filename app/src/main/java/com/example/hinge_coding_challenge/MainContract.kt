package com.example.hinge_coding_challenge

import com.example.hinge_coding_challenge.entity.CustomerData
import com.example.hinge_coding_challenge.entity.SharedPrefManager

interface MainContract {
    interface View {
        fun displayTotalCount(totalCount: Int)
        fun displayCurrentCount(currentCount: Int)
        fun displayRedTextColorCurrentCount()
        fun displayNormalColorCurrentCount()
        fun hideSubtractButton()
        fun showSubtractButton()
        fun isSubtractButtonInvisible(): Boolean
    }

    interface Interactor {
        fun fetchData(sharedPrefManager: SharedPrefManager, interactorOutput: InteractorOutput)
        fun saveData(customerData: CustomerData?, sharedPrefManager: SharedPrefManager)
    }

    interface InteractorOutput {
        fun dataFetched(customerData: CustomerData)
    }

    interface Presenter {
        fun onViewCreated(sharedPrefManager: SharedPrefManager)
        fun saveCustomerData(sharedPrefManager: SharedPrefManager)
        fun addCustomerClick()
        fun subtractCustomerClick()
        fun resetCustomerClick()
        fun onDestroy()
    }
}