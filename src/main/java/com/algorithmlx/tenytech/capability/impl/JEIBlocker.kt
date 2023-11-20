package com.algorithmlx.tenytech.capability.impl

import com.algorithmlx.tenytech.capability.core.IJEIBlocker

class JEIBlocker: IJEIBlocker {
    private var canUse = false

    constructor(canUse: Boolean) {
        this.canUse = canUse
    }

    constructor() {
        this.canUse = false
    }

    override fun getUseJEI(): Boolean = this.canUse

    override fun setUseJEI(canUse: Boolean) {
        this.canUse = canUse
    }
}