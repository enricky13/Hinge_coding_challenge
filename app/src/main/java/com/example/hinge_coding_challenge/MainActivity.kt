package com.example.hinge_coding_challenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.hinge_coding_challenge.entity.SharedPrefManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {
    private var presenter: MainContract.Presenter? = null
    private val sharedPrefManager: SharedPrefManager by lazy {
        SharedPrefManager().apply {
            with(
                application
            )
        }
    }

    private val totalCountTv: TextView by lazy { total_count_tv }
    private val currentCountTv: TextView by lazy { current_count_tv }
    private val resetBt: Button by lazy { reset_bt }
    private val subtractCustomerBt: Button by lazy { subtract_customer_bt }
    private val addCustomerBt: Button by lazy { add_customer_bt }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(this)
        presenter?.onViewCreated(sharedPrefManager)

        resetBt.setOnClickListener { presenter?.resetCustomerClick() }
        subtractCustomerBt.setOnClickListener { presenter?.subtractCustomerClick() }
        addCustomerBt.setOnClickListener { presenter?.addCustomerClick() }
    }

    override fun displayTotalCount(totalCount: Int) {
        totalCountTv.text = getString(R.string.total_count_tv_string, totalCount)
    }

    override fun displayCurrentCount(currentCount: Int) {
        currentCountTv.text = getString(R.string.current_count_tv_string, currentCount)
    }

    override fun displayRedTextColorCurrentCount() {
        currentCountTv.setTextColor(getColor(R.color.customerOverLimitColor))
    }

    override fun displayNormalColorCurrentCount() {
        currentCountTv.setTextColor(getColor(R.color.colorPrimaryDark))
    }

    override fun hideSubtractButton() {
        subtractCustomerBt.visibility = View.GONE
    }

    override fun showSubtractButton() {
        subtractCustomerBt.visibility = View.VISIBLE
    }

    override fun isSubtractButtonInvisible(): Boolean = subtractCustomerBt.visibility == View.GONE

    override fun onPause() {
        super.onPause()
        val bundle = Bundle()
        super.onSaveInstanceState(bundle)
        presenter?.saveCustomerData(sharedPrefManager)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
    }
}