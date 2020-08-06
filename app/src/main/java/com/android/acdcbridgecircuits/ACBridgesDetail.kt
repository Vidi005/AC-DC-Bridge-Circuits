package com.android.acdcbridgecircuits

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.ac_bridge_equation1.*
import kotlinx.android.synthetic.main.ac_bridge_equation2.*
import kotlinx.android.synthetic.main.ac_bridge_equation3.*
import kotlinx.android.synthetic.main.ac_bridge_equation4.*
import kotlinx.android.synthetic.main.ac_bridge_equation5.*
import kotlinx.android.synthetic.main.ac_bridge_equation6.*
import kotlinx.android.synthetic.main.activity_acbridges_detail.*
import kotlin.math.PI
import kotlin.math.pow
import kotlin.math.sqrt

class ACBridgesDetail : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val EXTRA_AC_BRIDGE = "extra_ac_bridge"
        private const val STATE_RX_CAP_COMP_RESULT = "state_rx_cap_comp_result"
        private const val STATE_CX_CAP_COMP_RESULT = "state_cx_cap_comp_result"
        private const val STATE_RX_IND_COMP_RESULT = "state_rx_ind_comp_result"
        private const val STATE_LX_IND_COMP_RESULT = "state_lx_ind_comp_result"
        private const val STATE_RX_MAXWELL_RESULT = "state_rx_maxwell_result"
        private const val STATE_LX_MAXWELL_RESULT = "state_lx_maxwell_result"
        private const val STATE_RX_HAY_RESULT = "state_rx_hay_result"
        private const val STATE_Q_HAY_RESULT = "state_q_hay_result"
        private const val STATE_LX_HAY_RESULT = "state_lx_hay_result"
        private const val STATE_HAY_INFO = "state_hay_info"
        private const val STATE_RX_SCHEERING_RESULT = "state_rx_scheering_result"
        private const val STATE_CX_SCHEERING_RESULT = "state_cx_scheering_result"
        private const val STATE_PF_SCHEERING_RESULT = "state_pf_scheering_result"
        private const val STATE_D_SCHEERING_RESULT = "state_d_scheering_result"
        private const val STATE_WIEN_RESULT = "state_wien_result"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acbridges_detail)

        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = "Detail AC Bridge"
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val acBridge = intent.getParcelableExtra(EXTRA_AC_BRIDGE) as ACBridges

        val image = acBridge.picture
        val nameACBridge = acBridge.name.toString()
        val detail = acBridge.detail
        val function = acBridge.equation
        image?.let{iv_picture_received.setImageResource(it)}
        tv_name_received.text = nameACBridge
        tv_detail_received.text = detail
        if (function != null) {
            vs_equation_received.layoutResource = function
            vs_equation_received.inflate()
        }

        if (btn_Rx_cap_comp_calculate != null) {
            btn_Rx_cap_comp_calculate.setOnClickListener(this)
            if (savedInstanceState != null) {
                val rxResult = savedInstanceState.getString(STATE_RX_CAP_COMP_RESULT) as String
                tv_Rx_cap_comp_result.text = rxResult
            }
        }
        if (btn_real_cap_comp_clear != null) btn_real_cap_comp_clear.setOnClickListener(this)
        if (btn_Cx_cap_comp_calculate != null) {
            btn_Cx_cap_comp_calculate.setOnClickListener(this)
            if (savedInstanceState != null) {
                val cxResult = savedInstanceState.getString(STATE_CX_CAP_COMP_RESULT) as String
                tv_Cx_cap_comp_result.text = cxResult
            }
        }
        if (btn_im_cap_comp_clear != null) btn_im_cap_comp_clear.setOnClickListener(this)
        if (btn_Rx_ind_comp_calculate != null) {
            btn_Rx_ind_comp_calculate.setOnClickListener(this)
            if (savedInstanceState != null) {
                val rxResult = savedInstanceState.getString(STATE_RX_IND_COMP_RESULT) as String
                tv_Rx_ind_comp_result.text = rxResult
            }
        }
        if (btn_real_ind_comp_clear != null) btn_real_ind_comp_clear.setOnClickListener(this)
        if (btn_Lx_ind_comp_calculate != null) {
            btn_Lx_ind_comp_calculate.setOnClickListener(this)
            if (savedInstanceState != null) {
                val lxResult = savedInstanceState.getString(STATE_LX_IND_COMP_RESULT) as String
                tv_Lx_ind_comp_result.text = lxResult
            }
        }
        if (btn_im_ind_comp_clear != null) btn_im_ind_comp_clear.setOnClickListener(this)
        if (btn_Rx_maxwell_calculate != null) {
            btn_Rx_maxwell_calculate.setOnClickListener(this)
            if (savedInstanceState != null) {
                val rxResult = savedInstanceState.getString(STATE_RX_MAXWELL_RESULT) as String
                tv_Rx_maxwell_result.text = rxResult
            }
        }
        if (btn_real_maxwell_clear != null) btn_real_maxwell_clear.setOnClickListener(this)
        if (btn_Lx_maxwell_calculate != null) {
            btn_Lx_maxwell_calculate.setOnClickListener(this)
            if (savedInstanceState != null) {
                val lxResult = savedInstanceState.getString(STATE_LX_MAXWELL_RESULT) as String
                tv_Lx_maxwell_result.text = lxResult
            }
        }
        if (btn_im_maxwell_clear != null) btn_im_maxwell_clear.setOnClickListener(this)
        if (btn_hay_calculate != null) {
            btn_hay_calculate.setOnClickListener(this)
            if (savedInstanceState != null) {
                val rxResult = savedInstanceState.getString(STATE_RX_HAY_RESULT) as String
                tv_Rx_hay_result.text = rxResult
                val qResult = savedInstanceState.getString(STATE_Q_HAY_RESULT) as String
                tv_Q_hay_result.text = qResult
                val lxResult = savedInstanceState.getString(STATE_LX_HAY_RESULT) as String
                tv_Lx_hay_result.text = lxResult
                val info = savedInstanceState.getString(STATE_HAY_INFO) as String
                tv_hay_info.text = info
            }
        }
        if (btn_hay_clear != null) btn_hay_clear.setOnClickListener(this)
        if (btn_scheering_calculate != null) {
            btn_scheering_calculate.setOnClickListener(this)
            if (savedInstanceState != null) {
                val rxResult = savedInstanceState.getString(STATE_RX_SCHEERING_RESULT) as String
                tv_Rx_scheering_result.text = rxResult
                val cxResult = savedInstanceState.getString(STATE_CX_SCHEERING_RESULT) as String
                tv_Cx_scheering_result.text = cxResult
                val pfResult = savedInstanceState.getString(STATE_PF_SCHEERING_RESULT) as String
                tv_PF_scheering_result.text = pfResult
                val dResult = savedInstanceState.getString(STATE_D_SCHEERING_RESULT) as String
                tv_D_scheering_result.text = dResult
            }
        }
        if (btn_scheering_clear != null) btn_scheering_clear.setOnClickListener(this)
        if (btn_wien_calculate != null) {
            btn_wien_calculate.setOnClickListener(this)
            if (savedInstanceState != null) {
                val fResult = savedInstanceState.getString(STATE_WIEN_RESULT) as String
                tv_f_wien_result.text = fResult
            }
        }
        if (btn_wien_clear != null) btn_wien_clear.setOnClickListener(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (btn_Rx_cap_comp_calculate != null) {
            outState.putString(STATE_RX_CAP_COMP_RESULT, tv_Rx_cap_comp_result.text.toString())
        }
        if (btn_Cx_cap_comp_calculate != null) {
            outState.putString(STATE_CX_CAP_COMP_RESULT, tv_Cx_cap_comp_result.text.toString())
        }
        if (btn_Rx_ind_comp_calculate != null) {
            outState.putString(STATE_RX_IND_COMP_RESULT, tv_Rx_ind_comp_result.text.toString())
        }
        if (btn_Lx_ind_comp_calculate != null) {
            outState.putString(STATE_LX_IND_COMP_RESULT, tv_Lx_ind_comp_result.text.toString())
        }
        if (btn_Rx_maxwell_calculate != null) {
            outState.putString(STATE_RX_MAXWELL_RESULT, tv_Rx_maxwell_result.text.toString())
        }
        if (btn_Lx_maxwell_calculate != null) {
            outState.putString(STATE_LX_MAXWELL_RESULT, tv_Lx_maxwell_result.text.toString())
        }
        if (btn_hay_calculate != null) {
            outState.putString(STATE_RX_HAY_RESULT, tv_Rx_hay_result.text.toString())
            outState.putString(STATE_Q_HAY_RESULT, tv_Q_hay_result.text.toString())
            outState.putString(STATE_LX_HAY_RESULT, tv_Lx_hay_result.text.toString())
            outState.putString(STATE_HAY_INFO, tv_hay_info.text.toString())
        }
        if (btn_scheering_calculate != null) {
            outState.putString(STATE_RX_SCHEERING_RESULT, tv_Rx_scheering_result.text.toString())
            outState.putString(STATE_CX_SCHEERING_RESULT, tv_Cx_scheering_result.text.toString())
            outState.putString(STATE_PF_SCHEERING_RESULT, tv_PF_scheering_result.text.toString())
            outState.putString(STATE_D_SCHEERING_RESULT, tv_D_scheering_result.text.toString())
        }
        if (btn_wien_calculate != null) {
            outState.putString(STATE_WIEN_RESULT, tv_f_wien_result.text.toString())
        }
    }

    private fun getRealCapacitanceComparationEquation() {
        var isEmptyFields = false
        var isInvalidDouble = false

        val inputR1 = edt_R1_real_cap_comp.text.toString().trim()
        val inputR2 = edt_R2_real_cap_comp.text.toString().trim()
        val inputRs = edt_Rs_real_cap_comp.text.toString().trim()

        if (inputR1.isEmpty()) {
            isEmptyFields = true
            edt_R1_real_cap_comp.error = "Nilai ini tidak boleh kosong"
        }
        if (inputR2.isEmpty()) {
            isEmptyFields = true
            edt_R2_real_cap_comp.error = "Nilai ini tidak boleh kosong"
        }
        if (inputRs.isEmpty()) {
            isEmptyFields = true
            edt_Rs_real_cap_comp.error = "Nilai ini tidak boleh kosong"
        }

        val r1 = toDouble(inputR1)
        val r2 = toDouble(inputR2)
        val rs = toDouble(inputRs)

        if (r1 == null) {
            isInvalidDouble = true
            edt_R1_real_cap_comp.error = "Nilai ini harus berupa nomer yang valid"
        }
        if (r2 == null) {
            isInvalidDouble = true
            edt_R1_real_cap_comp.error = "Nilai ini harus berupa nomer yang valid"
        }
        if (rs == null) {
            isInvalidDouble = true
            edt_R1_real_cap_comp.error = "Nilai ini harus berupa nomer yang valid"
        }
        if (!isEmptyFields && !isInvalidDouble) {
            val rx = rs as Double * r2 as Double / r1 as Double
            tv_Rx_cap_comp_result.text = rx.toString()
        }
    }

    private fun getImaginaryCapacitanceComparationEquation() {
        var isEmptyFields = false
        var isInvalidDouble = false

        val inputR1 = edt_R1_im_cap_comp.text.toString().trim()
        val inputR2 = edt_R2_im_cap_comp.text.toString().trim()
        val inputCs = edt_Cs_im_cap_comp.text.toString().trim()

        if (inputR1.isEmpty()) {
            isEmptyFields = true
            edt_R1_im_cap_comp.error = "Nilai ini tidak boleh kosong"
        }
        if (inputR2.isEmpty()) {
            isEmptyFields = true
            edt_R2_im_cap_comp.error = "Nilai ini tidak boleh kosong"
        }
        if (inputCs.isEmpty()) {
            isEmptyFields = true
            edt_Cs_im_cap_comp.error = "Nilai ini tidak boleh kosong"
        }

        val r1 = toDouble(inputR1)
        val r2 = toDouble(inputR2)
        val cs = toDouble(inputCs)

        if (r1 == null) {
            isInvalidDouble = true
            edt_R1_im_cap_comp.error = "Nilai ini harus berupa nomer yang valid"
        }
        if (r2 == null) {
            isInvalidDouble = true
            edt_R2_im_cap_comp.error = "Nilai ini harus berupa nomer yang valid"
        }
        if (cs == null) {
            isInvalidDouble = true
            edt_Cs_im_cap_comp.error = "Nilai ini harus berupa nomer yang valid"
        }
        if (!isEmptyFields && !isInvalidDouble) {
            val cx = cs as Double * r1 as Double / r2 as Double
            tv_Cx_cap_comp_result.text = cx.toString()
        }
    }

    private fun getRealInductanceComparationEquation() {
        var isEmptyFields = false
        var isInvalidDouble = false

        val inputR1 = edt_R1_real_ind_comp.text.toString().trim()
        val inputR2 = edt_R2_real_ind_comp.text.toString().trim()
        val inputRs = edt_Rs_real_ind_comp.text.toString().trim()

        if (inputR1.isEmpty()) {
            isEmptyFields = true
            edt_R1_real_ind_comp.error = "Nilai ini tidak boleh kosong"
        }
        if (inputR2.isEmpty()) {
            isEmptyFields = true
            edt_R2_real_ind_comp.error = "Nilai ini tidak boleh kosong"
        }
        if (inputRs.isEmpty()) {
            isEmptyFields = true
            edt_Rs_real_ind_comp.error = "Nilai ini tidak boleh kosong"
        }

        val r1 = toDouble(inputR1)
        val r2 = toDouble(inputR2)
        val rs = toDouble(inputRs)

        if (r1 == null) {
            isInvalidDouble = true
            edt_R1_real_ind_comp.error = "Nilai ini harus berupa nomer yang valid"
        }
        if (r2 == null) {
            isInvalidDouble = true
            edt_R1_real_ind_comp.error = "Nilai ini harus berupa nomer yang valid"
        }
        if (rs == null) {
            isInvalidDouble = true
            edt_R1_real_ind_comp.error = "Nilai ini harus berupa nomer yang valid"
        }
        if (!isEmptyFields && !isInvalidDouble) {
            val rx = rs as Double * r2 as Double / r1 as Double
            tv_Rx_ind_comp_result.text = rx.toString()
        }
    }

    private fun getImaginaryInductanceComparationEquation() {
        var isEmptyFields = false
        var isInvalidDouble = false

        val inputR1 = edt_R1_im_ind_comp.text.toString().trim()
        val inputR2 = edt_R2_im_ind_comp.text.toString().trim()
        val inputLs = edt_Ls_im_ind_comp.text.toString().trim()

        if (inputR1.isEmpty()) {
            isEmptyFields = true
            edt_R1_im_ind_comp.error = "Nilai ini tidak boleh kosong"
        }
        if (inputR2.isEmpty()) {
            isEmptyFields = true
            edt_R2_im_ind_comp.error = "Nilai ini tidak boleh kosong"
        }
        if (inputLs.isEmpty()) {
            isEmptyFields = true
            edt_Ls_im_ind_comp.error = "Nilai ini tidak boleh kosong"
        }

        val r1 = toDouble(inputR1)
        val r2 = toDouble(inputR2)
        val ls = toDouble(inputLs)

        if (r1 == null) {
            isInvalidDouble = true
            edt_R1_im_ind_comp.error = "Nilai ini harus berupa nomer yang valid"
        }
        if (r2 == null) {
            isInvalidDouble = true
            edt_R2_im_ind_comp.error = "Nilai ini harus berupa nomer yang valid"
        }
        if (ls == null) {
            isInvalidDouble = true
            edt_Ls_im_ind_comp.error = "Nilai ini harus berupa nomer yang valid"
        }
        if (!isEmptyFields && !isInvalidDouble) {
            val lx = ls as Double * r2 as Double / r1 as Double
            tv_Lx_ind_comp_result.text = lx.toString()
        }
    }

    private fun getRealMaxwellEquation() {
        var isEmptyFields = false
        var isInvalidDouble = false

        val inputR1 = edt_R1_real_maxwell.text.toString().trim()
        val inputR2 = edt_R2_real_maxwell.text.toString().trim()
        val inputR3 = edt_R3_real_maxwell.text.toString().trim()

        if (inputR1.isEmpty()) {
            isEmptyFields = true
            edt_R1_real_maxwell.error = "Nilai ini tidak boleh kosong"
        }
        if (inputR2.isEmpty()) {
            isEmptyFields = true
            edt_R2_real_maxwell.error = "Nilai ini tidak boleh kosong"
        }
        if (inputR3.isEmpty()) {
            isEmptyFields = true
            edt_R3_real_maxwell.error = "Nilai ini tidak boleh kosong"
        }

        val r1 = toDouble(inputR1)
        val r2 = toDouble(inputR2)
        val r3 = toDouble(inputR3)

        if (r1 == null) {
            isInvalidDouble = true
            edt_R1_real_maxwell.error = "Nilai ini harus berupa nomer yang valid"
        }
        if (r2 == null) {
            isInvalidDouble = true
            edt_R2_real_maxwell.error = "Nilai ini harus berupa nomer yang valid"
        }
        if (r3 == null) {
            isInvalidDouble = true
            edt_R3_real_maxwell.error = "Nilai ini harus berupa nomer yang valid"
        }
        if (!isEmptyFields && !isInvalidDouble) {
            val rx = r2 as Double * r3 as Double / r1 as Double
            tv_Rx_maxwell_result.text = rx.toString()
        }
    }

    private fun getImaginaryMaxwellEquation() {
        var isEmptyFields = false
        var isInvalidDouble = false

        val inputR2 = edt_R2_im_maxwell.text.toString().trim()
        val inputR3 = edt_R3_im_maxwell.text.toString().trim()
        val inputC1 = edt_C1_im_maxwell.text.toString().trim()

        if (inputR2.isEmpty()) {
            isEmptyFields = true
            edt_R2_im_maxwell.error = "Nilai ini tidak boleh kosong"
        }
        if (inputR3.isEmpty()) {
            isEmptyFields = true
            edt_R3_im_maxwell.error = "Nilai ini tidak boleh kosong"
        }
        if (inputC1.isEmpty()) {
            isEmptyFields = true
            edt_C1_im_maxwell.error = "Nilai ini tidak boleh kosong"
        }

        val r2 = toDouble(inputR2)
        val r3 = toDouble(inputR3)
        val c1 = toDouble(inputC1)

        if (r2 == null) {
            isInvalidDouble = true
            edt_R2_im_maxwell.error = "Nilai ini harus berupa nomer yang valid"
        }
        if (r3 == null) {
            isInvalidDouble = true
            edt_R3_im_maxwell.error = "Nilai ini harus berupa nomer yang valid"
        }
        if (c1 == null) {
            isInvalidDouble = true
            edt_C1_im_maxwell.error = "Nilai ini harus berupa nomer yang valid"
        }
        if (!isEmptyFields && !isInvalidDouble) {
            val lx = r2 as Double * r3 as Double / c1 as Double
            tv_Lx_maxwell_result.text = lx.toString()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun getHayEquation() {
        var isEmptyFields = false
        var isInvalidDouble = false

        val inputR1 = edt_R1_hay.text.toString().trim()
        val inputR2 = edt_R2_hay.text.toString().trim()
        val inputR3 = edt_R3_hay.text.toString().trim()
        val inputC1 = edt_C1_hay.text.toString().trim()
        val inputf = edt_f_hay.text.toString().trim()

        if (inputR1.isEmpty()) {
            isEmptyFields = true
            edt_R1_hay.error = "Nilai ini tidak boleh kosong"
        }
        if (inputR2.isEmpty()) {
            isEmptyFields = true
            edt_R2_hay.error = "Nilai ini tidak boleh kosong"
        }
        if (inputR3.isEmpty()) {
            isEmptyFields = true
            edt_R3_hay.error = "Nilai ini tidak boleh kosong"
        }
        if (inputC1.isEmpty()) {
            isEmptyFields = true
            edt_C1_hay.error = "Nilai ini tidak boleh kosong"
        }
        if (inputf.isEmpty()) {
            isEmptyFields = true
            edt_f_hay.error = "Nilai ini tidak boleh kosong"
        }

        val r1 = toDouble(inputR1)
        val r2 = toDouble(inputR2)
        val r3 = toDouble(inputR3)
        val c1 = toDouble(inputC1)
        val f = toDouble(inputf)

        if (r1 == null) {
            isInvalidDouble = true
            edt_R1_hay.error = "Nilai ini harus berupa nomer yang valid"
        }
        if (r2 == null) {
            isInvalidDouble = true
            edt_R2_hay.error = "Nilai ini harus berupa nomer yang valid"
        }
        if (r3 == null) {
            isInvalidDouble = true
            edt_R3_hay.error = "Nilai ini harus berupa nomer yang valid"
        }
        if (c1 == null) {
            isInvalidDouble = true
            edt_C1_hay.error = "Nilai ini harus berupa nomer yang valid"
        }
        if (f == null) {
            isInvalidDouble = true
            edt_f_hay.error = "Nilai ini harus berupa nomer yang valid"
        }
        if (!isEmptyFields && !isInvalidDouble) {
            val rx = (2 * PI * f as Double * c1 as Double).pow(2) * r1 as Double * r2 as Double * r3 as Double / (1 + (2 * PI * f * c1 * r1).pow(2))
            tv_Rx_hay_result.text = rx.toString()
            val q = 1 / (2 * PI * f * c1 * r1)
            tv_Q_hay_result.text = q.toString()
            val lx = r2 * r3 * c1 / (1 + (2 * PI * f * c1 * r1).pow(2))
            tv_Lx_hay_result.text = lx.toString()
            when {
                q >= 10 -> tv_hay_info.text = "Disarankan menggunakan Jembatan Hay karena Q ≥ 10 dan nilai Q dapat diabaikan"
                else -> tv_hay_info.text = "Nilai Q < 10 sehingga dapat menggunakan Jembatan Pembanding Induktansi"
            }
        }
    }

    private fun getScheeringEquation() {
        var isEmptyFields = false
        var isInvalidDouble = false

        val inputR1 = edt_R1_scheering.text.toString().trim()
        val inputR2 = edt_R2_scheering.text.toString().trim()
        val inputC1 = edt_C1_scheering.text.toString().trim()
        val inputC3 = edt_C3_scheering.text.toString().trim()
        val inputf = edt_f_scheering.text.toString().trim()

        if (inputR1.isEmpty()) {
            isEmptyFields = true
            edt_R1_scheering.error = "Nilai ini tidak boleh kosong"
        }
        if (inputR2.isEmpty()) {
            isEmptyFields = true
            edt_R2_scheering.error = "Nilai ini tidak boleh kosong"
        }
        if (inputC1.isEmpty()) {
            isEmptyFields = true
            edt_C1_scheering.error = "Nilai ini tidak boleh kosong"
        }
        if (inputC3.isEmpty()) {
            isEmptyFields = true
            edt_C3_scheering.error = "Nilai ini tidak boleh kosong"
        }
        if (inputf.isEmpty()) {
            isEmptyFields = true
            edt_f_scheering.error = "Nilai ini tidak boleh kosong"
        }

        val r1 = toDouble(inputR1)
        val r2 = toDouble(inputR2)
        val c1 = toDouble(inputC1)
        val c3 = toDouble(inputC3)
        val f = toDouble(inputf)

        if (r1 == null) {
            isInvalidDouble = true
            edt_R1_scheering.error = "Nilai ini harus berupa nomer yang valid"
        }
        if (r2 == null) {
            isInvalidDouble = true
            edt_R2_scheering.error = "Nilai ini harus berupa nomer yang valid"
        }
        if (c1 == null) {
            isInvalidDouble = true
            edt_C1_scheering.error = "Nilai ini harus berupa nomer yang valid"
        }
        if (c3 == null) {
            isInvalidDouble = true
            edt_C3_scheering.error = "Nilai ini harus berupa nomer yang valid"
        }
        if (f == null) {
            isInvalidDouble = true
            edt_f_scheering.error = "Nilai ini harus berupa nomer yang valid"
        }
        if (!isEmptyFields && !isInvalidDouble) {
            val rx = r2 as Double * c1 as Double / c3 as Double
            tv_Rx_scheering_result.text = rx.toString()
            val cx = c3 * r1 as Double / r2
            tv_Cx_scheering_result.text = cx.toString()
            val pf = 2 * PI * f as Double * cx * rx
            tv_PF_scheering_result.text = pf.toString()
            val d = 2 * PI * f * r1 * c1
            tv_D_scheering_result.text = d.toString()
        }
    }

    private fun getWienEquation() {
        var isEmptyFields = false
        var isInvalidDouble = false

        val inputR1 = edt_R1_wien.text.toString().trim()
        val inputR3 = edt_R3_wien.text.toString().trim()
        val inputC1 = edt_C1_wien.text.toString().trim()
        val inputC3 = edt_C3_wien.text.toString().trim()

        if (inputR1.isEmpty()) {
            isEmptyFields = true
            edt_R1_wien.error = "Nilai ini tidak boleh kosong"
        }
        if (inputR3.isEmpty()) {
            isEmptyFields = true
            edt_R3_wien.error = "Nilai ini tidak boleh kosong"
        }
        if (inputC1.isEmpty()) {
            isEmptyFields = true
            edt_C1_wien.error = "Nilai ini tidak boleh kosong"
        }
        if (inputC3.isEmpty()) {
            isEmptyFields = true
            edt_C3_wien.error = "Nilai ini tidak boleh kosong"
        }

        val r1 = toDouble(inputR1)
        val r3 = toDouble(inputR3)
        val c1 = toDouble(inputC1)
        val c3 = toDouble(inputC3)

        if (r1 == null) {
            isInvalidDouble = true
            edt_R1_wien.error = "Nilai ini harus berupa nomer yang valid"
        }
        if (r3 == null) {
            isInvalidDouble = true
            edt_R3_wien.error = "Nilai ini harus berupa nomer yang valid"
        }
        if (c1 == null) {
            isInvalidDouble = true
            edt_C1_wien.error = "Nilai ini harus berupa nomer yang valid"
        }
        if (c3 == null) {
            isInvalidDouble = true
            edt_C3_wien.error = "Nilai ini harus berupa nomer yang valid"
        }
        if (!isEmptyFields && !isInvalidDouble) {
            val f = 1 / (2 * PI * sqrt(c1 as Double * c3 as Double * r1 as Double * r3 as Double))
            tv_f_wien_result.text = f.toString()
        }
    }

    override fun onClick(v: View?) {
        when {
            v?.id == R.id.btn_Rx_cap_comp_calculate -> getRealCapacitanceComparationEquation()
            v?.id == R.id.btn_real_cap_comp_clear -> {
                edt_R1_real_cap_comp.text.clear()
                edt_R2_real_cap_comp.text.clear()
                edt_Rs_real_cap_comp.text.clear()
                tv_Rx_cap_comp_result.text = ""
            }
            v?.id == R.id.btn_Cx_cap_comp_calculate -> getImaginaryCapacitanceComparationEquation()
            v?.id == R.id.btn_im_cap_comp_clear -> {
                edt_R1_im_cap_comp.text.clear()
                edt_R2_im_cap_comp.text.clear()
                edt_Cs_im_cap_comp.text.clear()
                tv_Cx_cap_comp_result.text = ""
            }
            v?.id == R.id.btn_Rx_ind_comp_calculate -> getRealInductanceComparationEquation()
            v?.id == R.id.btn_real_ind_comp_clear -> {
                edt_R1_real_ind_comp.text.clear()
                edt_R2_real_ind_comp.text.clear()
                edt_Rs_real_ind_comp.text.clear()
                tv_Rx_ind_comp_result.text = ""
            }
            v?.id == R.id.btn_Lx_ind_comp_calculate -> getImaginaryInductanceComparationEquation()
            v?.id == R.id.btn_im_ind_comp_clear -> {
                edt_R1_im_ind_comp.text.clear()
                edt_R2_im_ind_comp.text.clear()
                edt_Ls_im_ind_comp.text.clear()
                tv_Lx_ind_comp_result.text = ""
            }
            v?.id == R.id.btn_Rx_maxwell_calculate -> getRealMaxwellEquation()
            v?.id == R.id.btn_real_maxwell_clear -> {
                edt_R1_real_maxwell.text.clear()
                edt_R2_real_maxwell.text.clear()
                edt_R3_real_maxwell.text.clear()
                tv_Rx_maxwell_result.text = ""
            }
            v?.id == R.id.btn_Lx_maxwell_calculate -> getImaginaryMaxwellEquation()
            v?.id == R.id.btn_im_maxwell_clear -> {
                edt_R2_im_maxwell.text.clear()
                edt_R3_im_maxwell.text.clear()
                edt_C1_im_maxwell.text.clear()
                tv_Lx_maxwell_result.text = ""
            }
            v?.id == R.id.btn_hay_calculate -> getHayEquation()
            v?.id == R.id.btn_hay_clear -> {
                edt_R1_hay.text.clear()
                edt_R2_hay.text.clear()
                edt_R3_hay.text.clear()
                edt_C1_hay.text.clear()
                edt_f_hay.text.clear()
                tv_Rx_hay_result.text = ""
                tv_Q_hay_result.text = ""
                tv_Lx_hay_result.text = ""
                tv_hay_info.text = ""
            }
            v?.id == R.id.btn_scheering_calculate -> getScheeringEquation()
            v?.id == R.id.btn_scheering_clear -> {
                edt_R1_scheering.text.clear()
                edt_R2_scheering.text.clear()
                edt_C1_scheering.text.clear()
                edt_C3_scheering.text.clear()
                edt_f_scheering.text.clear()
                tv_Rx_scheering_result.text = ""
                tv_Cx_scheering_result.text = ""
                tv_PF_scheering_result.text = ""
                tv_D_scheering_result.text = ""
            }
            v?.id == R.id.btn_wien_calculate -> getWienEquation()
            v?.id == R.id.btn_wien_clear -> {
                edt_R1_wien.text.clear()
                edt_R3_wien.text.clear()
                edt_C1_wien.text.clear()
                edt_C3_wien.text.clear()
                tv_f_wien_result.text = ""
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
