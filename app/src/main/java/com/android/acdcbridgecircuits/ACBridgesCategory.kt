package com.android.acdcbridgecircuits

import android.content.Intent
import android.content.res.TypedArray
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_acbridges_category.*

class ACBridgesCategory : AppCompatActivity() {

    private var acBridges: ArrayList<ACBridges> = arrayListOf()
    private lateinit var dataPicture: TypedArray
    private lateinit var dataName: Array<String>
    private lateinit var dataDetail: Array<String>
    private lateinit var dataEquation: TypedArray

    private fun showSelectedACBridge(acBridge: ACBridges) {
        val moveWithObjectIntent = Intent(this@ACBridgesCategory, ACBridgesDetail::class.java)
        moveWithObjectIntent.putExtra(ACBridgesDetail.EXTRA_AC_BRIDGE, acBridge)
        startActivity(moveWithObjectIntent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acbridges_category)

        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = "AC Bridge Circuits"
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        rv_ac_bridge.setHasFixedSize(true)

        addItem()
        showRecyclerView()
    }

    private fun showRecyclerView() {
        rv_ac_bridge.layoutManager = LinearLayoutManager(this)
        val acBridgeAdapter = ACBridgesAdapter(acBridges)
        rv_ac_bridge.adapter = acBridgeAdapter

        acBridgeAdapter.setOnItemClickCallback(object : ACBridgesAdapter.OnItemClickCallback {
            override fun onItemClicked(data: ACBridges) {
                showSelectedACBridge(data)
            }
        })
    }

    private fun addItem(): ArrayList<ACBridges> {
        prepare()
        for (position in dataName.indices) {
            val acBridge = ACBridges(
                dataPicture.getResourceId(position, -1),
                dataName[position],
                dataDetail[position],
                dataEquation.getResourceId(position, -1)
            )
            acBridges.add(acBridge)
        }
        return acBridges
    }

    private fun prepare() {
        dataPicture = resources.obtainTypedArray(R.array.ac_bridge_picture)
        dataName = resources.getStringArray(R.array.ac_bridge_name)
        dataDetail = resources.getStringArray(R.array.ac_bridge_detail)
        dataEquation = resources.obtainTypedArray(R.array.ac_bridge_equation)
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
