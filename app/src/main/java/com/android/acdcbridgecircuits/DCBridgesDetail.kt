package com.android.acdcbridgecircuits

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_dcbridges_detail.*
import kotlinx.android.synthetic.main.dc_bridge_equation1.*
import kotlinx.android.synthetic.main.dc_bridge_equation1.btn_Rx_calculate
import kotlinx.android.synthetic.main.dc_bridge_equation1.btn_Rx_clear
import kotlinx.android.synthetic.main.dc_bridge_equation1.btn_calculate_wheatstone
import kotlinx.android.synthetic.main.dc_bridge_equation1.edt_E_wheatstone
import kotlinx.android.synthetic.main.dc_bridge_equation1.edt_R1
import kotlinx.android.synthetic.main.dc_bridge_equation1.edt_R2
import kotlinx.android.synthetic.main.dc_bridge_equation1.edt_R2_not_balanced_wheatstone
import kotlinx.android.synthetic.main.dc_bridge_equation1.edt_R3
import kotlinx.android.synthetic.main.dc_bridge_equation1.edt_R3_not_balanced_wheatstone
import kotlinx.android.synthetic.main.dc_bridge_equation1.edt_R4_not_balanced_wheatstone
import kotlinx.android.synthetic.main.dc_bridge_equation1.tv_Rx_result
import kotlinx.android.synthetic.main.dc_bridge_equation2.*

class DCBridgesDetail : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val EXTRA_DC_BRIDGE = "extra_dc_bridge"
        private const val STATE_RX_RESULT = "state_rx_result"
        private const val STATE_ETH_RESULT = "state_eth_result"
        private const val STATE_RTH_RESULT = "state_rth_result"
        private const val STATE_IG_RESULT = "state_ig_result"
        private const val STATE_EKL_RESULT = "state_ekl_result"
        private const val STATE_I_RESULT = "state_i_result"
        private const val STATE_ELMP_RESULT = "state_elmp_result"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dcbridges_detail)

        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = "Detail DC Bridge"
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val dcBridge = intent.getParcelableExtra(EXTRA_DC_BRIDGE) as DCBridges

        val image = dcBridge.picture
        val nameDCBridge = dcBridge.name.toString()
        val detail = dcBridge.detail
        val function = dcBridge.equation
        image?.let{iv_picture_received.setImageResource(it)}
        tv_name_received.text = nameDCBridge
        tv_detail_received.text = detail
        if (function != null) {
            vs_equation_received.layoutResource = function
            vs_equation_received.inflate()
        }

        if (btn_Rx_calculate != null) {
            btn_Rx_calculate.setOnClickListener(this)
            if (savedInstanceState != null) {
                val rxResult = savedInstanceState.getString(STATE_RX_RESULT) as String
                tv_Rx_result.text = rxResult
            }
        }
        if (btn_Rx_clear != null) btn_Rx_clear.setOnClickListener(this)
        if (btn_calculate_wheatstone != null) {
            btn_calculate_wheatstone.setOnClickListener(this)
            if (savedInstanceState != null) {
                val ethResult = savedInstanceState.getString(STATE_ETH_RESULT) as String
                tv_Eth_result.text = ethResult
                val rthResult = savedInstanceState.getString(STATE_RTH_RESULT) as String
                tv_Rth_result.text = rthResult
                val igResult = savedInstanceState.getString(STATE_IG_RESULT) as String
                tv_Ig_result.text = igResult
            }
        }
        if (btn_clear_wheatstone != null) btn_clear_wheatstone.setOnClickListener(this)
        if (btn_calculate_kelvin != null) {
            btn_calculate_kelvin.setOnClickListener(this)
            if (savedInstanceState != null) {
                val eklResult = savedInstanceState.getString(STATE_EKL_RESULT) as String
                tv_Ekl_result.text = eklResult
                val iResult = savedInstanceState.getString(STATE_I_RESULT) as String
                tv_I_result.text = iResult
                val elmpResult = savedInstanceState.getString(STATE_ELMP_RESULT) as String
                tv_Elmp_result.text = elmpResult
            }
        }
        if (btn_clear_kelvin != null) btn_clear_kelvin.setOnClickListener(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RX_RESULT, tv_Rx_result.text.toString())
        if (btn_calculate_wheatstone != null) {
            outState.putString(STATE_ETH_RESULT, tv_Eth_result.text.toString())
            outState.putString(STATE_RTH_RESULT, tv_Rth_result.text.toString())
            outState.putString(STATE_IG_RESULT, tv_Ig_result.text.toString())
        }
        if (btn_calculate_kelvin != null) {
            outState.putString(STATE_EKL_RESULT, tv_Ekl_result.text.toString())
            outState.putString(STATE_I_RESULT, tv_I_result.text.toString())
            outState.putString(STATE_ELMP_RESULT, tv_Elmp_result.text.toString())
        }
    }

    private fun getDCBalancedEquation() {
        var isEmptyFields = false
        var isInvalidDouble = false

        val inputR1 = edt_R1.text.toString().trim()
        val inputR2 = edt_R2.text.toString().trim()
        val inputR3 = edt_R3.text.toString().trim()

        if (inputR1.isEmpty()) {
            isEmptyFields = true
            edt_R1.error = "Nilai ini tidak boleh kosong"
        }
        if (inputR2.isEmpty()) {
            isEmptyFields = true
            edt_R2.error = "Nilai ini tidak boleh kosong"
        }
        if (inputR3.isEmpty()) {
            isEmptyFields = true
            edt_R3.error = "Nilai ini tidak boleh kosong"
        }

        val r1 = toDouble(inputR1)
        val r2 = toDouble(inputR2)
        val r3 = toDouble(inputR3)

        if (r1 == null) {
            isInvalidDouble = true
            edt_R1.error = "Nilai ini harus berupa nomer yang valid"
        }
        if (r2 == null) {
            isInvalidDouble = true
            edt_R2.error = "Nilai ini harus berupa nomer yang valid"
        }
        if (r3 == null) {
            isInvalidDouble = true
            edt_R3.error = "Nilai ini harus berupa nomer yang valid"
        }
        if (!isEmptyFields && !isInvalidDouble) {
            val rx = r2 as Double * r3 as Double / r1 as Double
            tv_Rx_result.text = rx.toString()
        }
    }

    private fun getWheatstoneOutOfBalancedEquation() {
        var isEmptyFields = false
        var isInvalidDouble = false

        val inputE = edt_E_wheatstone.text.toString().trim()
        val inputR1 = edt_R1_not_balanced_wheatstone.text.toString().trim()
        val inputR2 = edt_R2_not_balanced_wheatstone.text.toString().trim()
        val inputR3 = edt_R3_not_balanced_wheatstone.text.toString().trim()
        val inputR4 = edt_R4_not_balanced_wheatstone.text.toString().trim()
        val inputRg = edt_Rg_not_balanced_wheatstone.text.toString().trim()

        if (inputE.isEmpty()) {
            isEmptyFields = true
            edt_E_wheatstone.error = "Nilai ini tidak boleh kosong"
        }
        if (inputR1.isEmpty()) {
            isEmptyFields = true
            edt_R1_not_balanced_wheatstone.error = "Nilai ini tidak boleh kosong"
        }
        if (inputR2.isEmpty()) {
            isEmptyFields = true
            edt_R2_not_balanced_wheatstone.error = "Nilai ini tidak boleh kosong"
        }
        if (inputR3.isEmpty()) {
            isEmptyFields = true
            edt_R3_not_balanced_wheatstone.error = "Nilai ini tidak boleh kosong"
        }
        if (inputR4.isEmpty()) {
            isEmptyFields = true
            edt_R4_not_balanced_wheatstone.error = "Nilai ini tidak boleh kosong"
        }
        if (inputRg.isEmpty()) {
            isEmptyFields = true
            edt_Rg_not_balanced_wheatstone.error = "Nilai ini tidak boleh kosong"
        }

        val volt = toDouble(inputE)
        val r1 = toDouble(inputR1)
        val r2 = toDouble(inputR2)
        val r3 = toDouble(inputR3)
        val r4 = toDouble(inputR4)
        val rg = toDouble(inputRg)

        if (volt == null) {
            isInvalidDouble = true
            edt_E_wheatstone.error = "Nilai ini harus berupa nomor yang valid"
        }
        if (r1 == null) {
            isInvalidDouble = true
            edt_R1_not_balanced_wheatstone.error = "Nilai ini harus berupa nomor yang valid"
        }
        if (r2 == null) {
            isInvalidDouble = true
            edt_R2_not_balanced_wheatstone.error = "Nilai ini harus berupa nomor yang valid"
        }
        if (r3 == null) {
            isInvalidDouble = true
            edt_R3_not_balanced_wheatstone.error = "Nilai ini harus berupa nomor yang valid"
        }
        if (r4 == null) {
            isInvalidDouble = true
            edt_R4_not_balanced_wheatstone.error = "Nilai ini harus berupa nomor yang valid"
        }
        if (rg == null) {
            isInvalidDouble = true
            edt_Rg_not_balanced_wheatstone.error = "Nilai ini harus berupa nomor yang valid"
        }
        if (!isEmptyFields && !isInvalidDouble) {
            val eth = volt as Double * ((r1 as Double / (r1 + r3 as Double)) - (r2 as Double / (r2 + r4 as Double)))
            tv_Eth_result.text = eth.toString()
            val rth = (r1 * r3 / (r1 + r3)) + (r2 * r4 / (r2 + r4))
            tv_Rth_result.text = rth.toString()
            val ig = eth / (rth + rg as Double)
            tv_Ig_result.text = ig.toString()
        }
    }

    private fun getKelvinOutOfBalancedEquation() {
        var isEmptyFields = false
        var isInvalidDouble = false

        val inputE = edt_E_kelvin.text.toString().trim()
        val inputR1 = edt_R1_not_balanced_kelvin.text.toString().trim()
        val inputR2 = edt_R2_not_balanced_kelvin.text.toString().trim()
        val inputR3 = edt_R3_not_balanced_kelvin.text.toString().trim()
        val inputR4 = edt_R4_not_balanced_kelvin.text.toString().trim()
        val inputa = edt_a_not_balanced_kelvin.text.toString().trim()
        val inputb = edt_b_not_balanced_kelvin.text.toString().trim()
        val inputRy = edt_Ry_not_balanced_kelvin.text.toString().trim()

        if (inputE.isEmpty()) {
            isEmptyFields = true
            edt_E_kelvin.error = "Nilai ini tidak boleh kosong"
        }
        if (inputR1.isEmpty()) {
            isEmptyFields = true
            edt_R1_not_balanced_kelvin.error = "Nilai ini tidak boleh kosong"
        }
        if (inputR2.isEmpty()) {
            isEmptyFields = true
            edt_R2_not_balanced_kelvin.error = "Nilai ini tidak boleh kosong"
        }
        if (inputR3.isEmpty()) {
            isEmptyFields = true
            edt_R3_not_balanced_kelvin.error = "Nilai ini tidak boleh kosong"
        }
        if (inputR4.isEmpty()) {
            isEmptyFields = true
            edt_R4_not_balanced_kelvin.error = "Nilai ini tidak boleh kosong"
        }
        if (inputa.isEmpty()) {
            isEmptyFields = true
            edt_a_not_balanced_kelvin.error = "Nilai ini tidak boleh kosong"
        }
        if (inputb.isEmpty()) {
            isEmptyFields = true
            edt_b_not_balanced_kelvin.error = "Nilai ini tidak boleh kosong"
        }
        if (inputRy.isEmpty()) {
            isEmptyFields = true
            edt_Ry_not_balanced_kelvin.error = "Nilai ini tidak boleh kosong"
        }

        val volt = toDouble(inputE)
        val r1 = toDouble(inputR1)
        val r2 = toDouble(inputR2)
        val r3 = toDouble(inputR3)
        val r4 = toDouble(inputR4)
        val a = toDouble(inputa)
        val b = toDouble(inputb)
        val ry = toDouble(inputRy)

        if (volt == null) {
            isInvalidDouble = true
            edt_E_kelvin.error = "Nilai ini harus berupa nomor yang valid"
        }
        if (r1 == null) {
            isInvalidDouble = true
            edt_R1_not_balanced_kelvin.error = "Nilai ini harus berupa nomor yang valid"
        }
        if (r2 == null) {
            isInvalidDouble = true
            edt_R2_not_balanced_kelvin.error = "Nilai ini harus berupa nomor yang valid"
        }
        if (r3 == null) {
            isInvalidDouble = true
            edt_R3_not_balanced_kelvin.error = "Nilai ini harus berupa nomor yang valid"
        }
        if (r4 == null) {
            isInvalidDouble = true
            edt_R4_not_balanced_kelvin.error = "Nilai ini harus berupa nomor yang valid"
        }
        if (a == null) {
            isInvalidDouble = true
            edt_a_not_balanced_kelvin.error = "Nilai ini harus berupa nomor yang valid"
        }
        if (b == null) {
            isInvalidDouble = true
            edt_b_not_balanced_kelvin.error = "Nilai ini harus berupa nomor yang valid"
        }
        if (ry == null) {
            isInvalidDouble = true
            edt_Ry_not_balanced_kelvin.error = "Nilai ini harus berupa nomor yang valid"
        }
        if (!isEmptyFields && !isInvalidDouble) {
            val ekl = volt as Double * (r2 as Double / (r1 as Double + r2))
            tv_Ekl_result.text = ekl.toString()
            val i = volt / (r3 as Double + r4 as Double + ((a as Double + b as Double) * ry as Double) / (a + b + ry))
            tv_I_result.text = i.toString()
            val elmp = i * ((r3 + b / (a + b)) * (((a + b) * ry) / (a + b + ry)))
            tv_Elmp_result.text = elmp.toString()
        }
    }

    override fun onClick(v: View) {
        when {
            v.id == R.id.btn_Rx_calculate -> getDCBalancedEquation()
            v.id == R.id.btn_Rx_clear -> {
                edt_R1.text.clear()
                edt_R2.text.clear()
                edt_R3.text.clear()
                tv_Rx_result.text = ""
            }
            v.id == R.id.btn_calculate_wheatstone -> getWheatstoneOutOfBalancedEquation()
            v.id == R.id.btn_clear_wheatstone -> {
                edt_E_wheatstone.text.clear()
                edt_R1_not_balanced_wheatstone.text.clear()
                edt_R2_not_balanced_wheatstone.text.clear()
                edt_R3_not_balanced_wheatstone.text.clear()
                edt_R4_not_balanced_wheatstone.text.clear()
                edt_Rg_not_balanced_wheatstone.text.clear()
                tv_Eth_result.text = ""
                tv_Rth_result.text = ""
                tv_Ig_result.text = ""
            }
            v.id == R.id.btn_calculate_kelvin -> getKelvinOutOfBalancedEquation()
            v.id == R.id.btn_clear_kelvin -> {
                edt_E_kelvin.text.clear()
                edt_R1_not_balanced_kelvin.text.clear()
                edt_R2_not_balanced_kelvin.text.clear()
                edt_R3_not_balanced_kelvin.text.clear()
                edt_R4_not_balanced_kelvin.text.clear()
                edt_a_not_balanced_kelvin.text.clear()
                edt_b_not_balanced_kelvin.text.clear()
                edt_Ry_not_balanced_kelvin.text.clear()
                tv_Ekl_result.text = ""
                tv_I_result.text = ""
                tv_Elmp_result.text = ""
            }
        }
    }

    private fun toDouble(str: String): Double? {
        return try {
            str.toDouble()
        } catch (e: NumberFormatException) {
            null
        }
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
