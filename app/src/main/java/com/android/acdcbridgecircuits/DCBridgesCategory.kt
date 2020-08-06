package com.android.acdcbridgecircuits

import android.content.Intent
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_dcbridges_category.*

class DCBridgesCategory : AppCompatActivity() {

    private var dcBridges: ArrayList<DCBridges> = arrayListOf()
    private lateinit var dataPicture: TypedArray
    private lateinit var dataName: Array<String>
    private lateinit var dataDetail: Array<String>
    private lateinit var dataEquation: TypedArray

    private fun showSelectedDCBridge(dcBridge: DCBridges) {
        val moveWithObjectIntent = Intent(this@DCBridgesCategory, DCBridgesDetail::class.java)
        moveWithObjectIntent.putExtra(DCBridgesDetail.EXTRA_DC_BRIDGE, dcBridge)
        startActivity(moveWithObjectIntent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dcbridges_category)

        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = "DC Bridge Circuits"
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        rv_dc_bridge.setHasFixedSize(true)

        addItem()
        showRecyclerView()
    }

    private fun showRecyclerView() {
        rv_dc_bridge.layoutManager = LinearLayoutManager(this)
        val dcBridgeAdapter = DCBridgesAdapter(dcBridges)
        rv_dc_bridge.adapter = dcBridgeAdapter

        dcBridgeAdapter.setOnItemClickCallback(object : DCBridgesAdapter.OnItemClickCallback {
            override fun onItemClicked(data: DCBridges) {
                showSelectedDCBridge(data)
            }
        })
    }

    private fun addItem(): ArrayList<DCBridges> {
        prepare()
        for (position in dataName.indices) {
            val dcBridge = DCBridges(
                dataPicture.getResourceId(position, -1),
                dataName[position],
                dataDetail[position],
                dataEquation.getResourceId(position, -1)
            )
            dcBridges.add(dcBridge)
        }
        return dcBridges
    }

    private fun prepare() {
        dataPicture = resources.obtainTypedArray(R.array.dc_bridge_picture)
        dataName = resources.getStringArray(R.array.dc_bridge_name)
        dataDetail = resources.getStringArray(R.array.dc_bridge_detail)
        dataEquation = resources.obtainTypedArray(R.array.dc_bridge_equation)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}

