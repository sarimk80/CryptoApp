package com.example.cryptoapp.ui.fragment


import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.cryptoapp.R
import com.example.cryptoapp.viewmodels.StockViewModel
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.CandleData
import com.github.mikephil.charting.data.CandleDataSet
import com.github.mikephil.charting.data.CandleEntry
import kotlinx.android.synthetic.main.fragment_stock.*

/**
 * A simple [Fragment] subclass.
 */
class Stock : Fragment() {

    private lateinit var stockViewModel: StockViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stock, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chart1.setPinchZoom(true)
        chart1.setDrawGridBackground(false)
        chart1.setMaxVisibleValueCount(3)
        chart1.zoom(10.toFloat(),10.toFloat(),10.toFloat(),10.toFloat())
        chart1.legend.setDrawInside(false)


        val arrayEntry = ArrayList<CandleEntry>()

        stockViewModel = ViewModelProviders.of(this).get(StockViewModel::class.java)

        stockViewModel.getMutableCryptoData()
            .observe(this, Observer { data ->

                for (x in data.Data.Data) {
                    arrayEntry.add(
                        CandleEntry(
                            x.time.toFloat(),
                            x.high.toFloat(),
                            x.close.toFloat(),
                            x.open.toFloat(),
                            x.low.toFloat()
                        )
                    )
                }

                val dataSet = CandleDataSet(arrayEntry, "BitCoin")
                dataSet.setDrawIcons(false)
                dataSet.axisDependency= YAxis.AxisDependency.LEFT
                dataSet.shadowColor=Color.DKGRAY

                val candleData = CandleData(dataSet)
                chart1.data = candleData
                chart1.invalidate()
            })
    }
}
