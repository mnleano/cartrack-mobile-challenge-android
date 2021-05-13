/*
 * Created by Naiyin Tan on  3/9/2020
 * Copyright © 2020 Odyssey Computing, Inc. All rights reserved.
 */

package com.neds.cartrackmobilechallenge.ui.widgets

class Entropy {
    /**
     * Entropy is responsible for background color.
     *
     * @param s
     * @return
     */
    fun get(s: String): Int {
        var entropy = 0
        for (element in s) {
            entropy += element.toInt()
        }
        return entropy
    }
}