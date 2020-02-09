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
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.utils.ColorTemplate
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
        chart1.legend.setDrawInside(false)


        val arrayEntry1 = ArrayList<Entry>()
        val arrayEntry2 = ArrayList<Entry>()
        val arrayEntry3 = ArrayList<Entry>()
        val arrayEntry4 = ArrayList<Entry>()
        var set1: LineDataSet
        var set2: LineDataSet
        var set3: LineDataSet
        var set4: LineDataSet

        stockViewModel = ViewModelProviders.of(this).get(StockViewModel::class.java)

        stockViewModel.getMutableCryptoData()
            .observe(this, Observer { data ->

                for (x in data.Data.Data) {
                    arrayEntry1.add(Entry(x.time.toFloat(), x.high.toFloat()))
                    arrayEntry2.add(Entry(x.time.toFloat(), x.low.toFloat()))
                    arrayEntry3.add(Entry(x.time.toFloat(), x.close.toFloat()))
                    arrayEntry4.add(Entry(x.time.toFloat(), x.open.toFloat()))
                }
                set1 = LineDataSet(arrayEntry1, "High")
                set2 = LineDataSet(arrayEntry2, "Low")
                set3 = LineDataSet(arrayEntry3, "Close")
                set4 = LineDataSet(arrayEntry4, "Open")

                set1.axisDependency = YAxis.AxisDependency.LEFT
                set1.color = ColorTemplate.getHoloBlue()
                set1.setCircleColor(Color.BLUE)
                set1.lineWidth = 2f
                set1.circleRadius = 3f
                set1.fillAlpha = 65
                set1.fillColor = ColorTemplate.getHoloBlue()
                set1.highLightColor = Color.rgb(244, 117, 117)
                set1.setDrawCircleHole(true)

                set2.axisDependency = YAxis.AxisDependency.LEFT
                set2.color = ColorTemplate.getHoloBlue()
                set2.setCircleColor(Color.GREEN)
                set2.lineWidth = 2f
                set2.circleRadius = 3f
                set2.fillAlpha = 65
                set2.fillColor = ColorTemplate.getHoloBlue()
                set2.highLightColor = Color.rgb(244, 117, 117)
                set2.setDrawCircleHole(true)


                set3.axisDependency = YAxis.AxisDependency.LEFT
                set3.color = ColorTemplate.getHoloBlue()
                set3.setCircleColor(Color.MAGENTA)
                set3.lineWidth = 2f
                set3.circleRadius = 3f
                set3.fillAlpha = 65
                set3.fillColor = ColorTemplate.getHoloBlue()
                set3.highLightColor = Color.rgb(244, 117, 117)
                set3.setDrawCircleHole(true)



                val linedata = LineData(set1, set2, set3,set4)
                linedata.setValueTextColor(Color.WHITE)
                linedata.setValueTextSize(9f)



                // set data
                chart1.data = linedata
                chart1.invalidate()
//                val dataSet = CandleDataSet(arrayEntry, "BitCoin")
//                dataSet.setDrawIcons(false)
//                dataSet.axisDependency= YAxis.AxisDependency.LEFT
//                dataSet.shadowColor=Color.DKGRAY
//
//                val candleData = CandleData(dataSet)
//                chart1.data = candleData
//                chart1.invalidate()


            })
    }
}
