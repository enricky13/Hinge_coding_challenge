package com.example.hinge_coding_challenge

import com.example.hinge_coding_challenge.entity.CustomerData
import com.example.hinge_coding_challenge.entity.SharedPrefManager

class MainPresenter(private val view: MainContract.View?) : MainContract.Presenter,
    MainContract.InteractorOutput {
    private var customerData: CustomerData? = null
    private var interactor: MainInteractor? = MainInteractor()

    override fun onViewCreated(sharedPrefManager: SharedPrefManager) {
        interactor?.fetchData(sharedPrefManager, this)
        customerData?.let {
            view?.displayCurrentCount(it.currentCount)
            if (it.currentCount < 1) {
                view?.hideSubtractButton()
            } else {
                view?.showSubtractButton()
            }
            if (it.currentCount > 14) {
                view?.displayRedTextColorCurrentCount()
            } else {
                view?.displayNormalColorCurrentCount()
            }
            view?.displayCurrentCount(it.currentCount)
            view?.displayTotalCount(it.totalCount)
        }
    }

    override fun saveCustomerData(sharedPrefManager: SharedPrefManager) {
        interactor?.saveData(customerData, sharedPrefManager)
    }

    override fun dataFetched(customerData: CustomerData) {
        this.customerData = customerData
    }

    override fun addCustomerClick() {
        customerData?.let {
            it.currentCount += 1
            it.totalCount += 1
            view?.displayCurrentCount(it.currentCount)
            view?.displayTotalCount(it.totalCount)
            if (view?.isSubtractButtonInvisible() == true) view.showSubtractButton()
            if (it.currentCount > 14) view?.displayRedTextColorCurrentCount()
        }
    }

    override fun subtractCustomerClick() {
        customerData?.let {
            it.currentCount -= 1
            view?.displayCurrentCount(it.currentCount)
            if (it.currentCount < 1) view?.hideSubtractButton()
            if (it.currentCount < 15) view?.displayNormalColorCurrentCount()
        }
    }

    override fun resetCustomerClick() {
        customerData?.let {
            it.currentCount = 0
            it.totalCount = 0
            view?.hideSubtractButton()
            view?.displayCurrentCount(0)
            view?.displayTotalCount(0)
            view?.displayNormalColorCurrentCount()
        }
    }

    override fun onDestroy() {
        interactor = null
        customerData = null
    }
}