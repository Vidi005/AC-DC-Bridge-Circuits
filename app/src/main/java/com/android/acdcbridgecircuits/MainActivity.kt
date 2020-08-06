package com.android.acdcbridgecircuits

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iv_ac_bridge_circuit.setOnClickListener(this)
        iv_dc_bridge_circuit.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        setMode(item?.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int?) {
        if (selectedMode == R.id.action_about) {
            val moveAboutActivity = Intent (this@MainActivity, AboutActivity::class.java)
            startActivity(moveAboutActivity)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            iv_ac_bridge_circuit -> {
                val moveACBridgesIntent = Intent (this@MainActivity, ACBridgesCategory::class.java)
                startActivity(moveACBridgesIntent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }
            iv_dc_bridge_circuit -> {
                val moveDCBridgesIntent = Intent (this@MainActivity, DCBridgesCategory::class.java)
                startActivity(moveDCBridgesIntent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }
        }
    }
}
