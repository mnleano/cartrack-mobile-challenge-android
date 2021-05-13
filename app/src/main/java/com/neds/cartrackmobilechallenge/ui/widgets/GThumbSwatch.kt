package com.neds.cartrackmobilechallenge.ui.widgets

import java.util.*

/**
 * Created by hbb20 on 30/4/16.
 */
internal class GThumbSwatch(var colorBG: Int, var colorText: Int) {
    companion object {
        var gThumbSwatchList: MutableList<GThumbSwatch>? = null

        fun getGThumbSwatchForEntropy(id: Int): GThumbSwatch {
            if (gThumbSwatchList == null) {
                prepareSwatchList()
            }
            val index = id % gThumbSwatchList!!.size
            return gThumbSwatchList!![index]
        }

        private fun prepareSwatchList() {
            gThumbSwatchList = ArrayList()
            gThumbSwatchList!!.add(GThumbSwatch(-11629914, -1))
            gThumbSwatchList!!.add(GThumbSwatch(-274339, -1))
            gThumbSwatchList!!.add(GThumbSwatch(-16428406, -1))
            gThumbSwatchList!!.add(GThumbSwatch(-959438, -1))
            gThumbSwatchList!!.add(GThumbSwatch(-9323585, -1))
            gThumbSwatchList!!.add(GThumbSwatch(-7754161, -1))
            gThumbSwatchList!!.add(GThumbSwatch(-2736778, -1))
            gThumbSwatchList!!.add(GThumbSwatch(-10588086, -1))
            gThumbSwatchList!!.add(GThumbSwatch(-2838635, -1))
            gThumbSwatchList!!.add(GThumbSwatch(-6443714, -1))
            gThumbSwatchList!!.add(GThumbSwatch(-1608771, -1))
            gThumbSwatchList!!.add(GThumbSwatch(-13792785, -1))
            gThumbSwatchList!!.add(GThumbSwatch(-10470214, -1))
            gThumbSwatchList!!.add(GThumbSwatch(-9722393, -1))
            gThumbSwatchList!!.add(GThumbSwatch(-8505224, -1))
            gThumbSwatchList!!.add(GThumbSwatch(-16733271, -1))
            gThumbSwatchList!!.add(GThumbSwatch(-65385, -1))
            gThumbSwatchList!!.add(GThumbSwatch(-16735488, -1))
            gThumbSwatchList!!.add(GThumbSwatch(-34084, -1))
            gThumbSwatchList!!.add(GThumbSwatch(-4645561, -1))
            gThumbSwatchList!!.add(GThumbSwatch(-3226068, -1))
            gThumbSwatchList!!.add(GThumbSwatch(-3305296, -1))
            gThumbSwatchList!!.add(GThumbSwatch(-4120949, -1))
            gThumbSwatchList!!.add(GThumbSwatch(-351167, -1))
            gThumbSwatchList!!.add(GThumbSwatch(-6077896, -1))
            gThumbSwatchList!!.add(GThumbSwatch(-14577551, -1))
            gThumbSwatchList!!.add(GThumbSwatch(-10125361, -1))
            gThumbSwatchList!!.add(GThumbSwatch(-4410616, -1))
            gThumbSwatchList!!.add(GThumbSwatch(-13932115, -1))
            gThumbSwatchList!!.add(GThumbSwatch(-10272846, -1))
            gThumbSwatchList!!.add(GThumbSwatch(-3497887, -1))
            gThumbSwatchList!!.add(GThumbSwatch(-7054033, -1))
            gThumbSwatchList!!.add(GThumbSwatch(-6465613, -1))
            gThumbSwatchList!!.add(GThumbSwatch(-9057954, -1))
            gThumbSwatchList!!.add(GThumbSwatch(-5519340, -1))
            gThumbSwatchList!!.add(GThumbSwatch(-426384, -1))
            gThumbSwatchList!!.add(GThumbSwatch(-1820104, -1))
            gThumbSwatchList!!.add(GThumbSwatch(-10075513, -1))
            gThumbSwatchList!!.add(GThumbSwatch(-1731730, -1))
            gThumbSwatchList!!.add(GThumbSwatch(-1731730, -1))
            gThumbSwatchList!!.add(GThumbSwatch(-16134970, -1))
            gThumbSwatchList!!.add(GThumbSwatch(-1522413, -1))
            gThumbSwatchList!!.add(GThumbSwatch(-2655489, -1))
        }
    }
}
