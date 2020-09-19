package com.example.hinge_coding_challenge

import com.example.hinge_coding_challenge.entity.CustomerData
import com.example.hinge_coding_challenge.entity.SharedPrefManager

class MainInteractor : MainContract.Interactor {
    override fun fetchData(
        sharedPrefManager: SharedPrefManager,
        interactorOutput: MainContract.InteractorOutput
    ) {
        interactorOutput.dataFetched(
            sharedPrefManager.get(CUSTOMER_DATA_KEY) as? CustomerData ?: CustomerData(0, 0)
        )
    }

    override fun saveData(customerData: CustomerData?, sharedPrefManager: SharedPrefManager) {
        sharedPrefManager.put(customerData, CUSTOMER_DATA_KEY)
    }

    companion object {
        const val CUSTOMER_DATA_KEY = "key"
    }
}