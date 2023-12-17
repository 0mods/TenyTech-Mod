package com.algorithmlx.tenytech.capability.impl

import com.algorithmlx.tenytech.capability.core.IMapBlocker

class MapBlocker: IMapBlocker {
    private var canUse = false

    constructor(canUse: Boolean) {
        this.canUse = canUse
    }

    constructor() {
        this.canUse = false
    }

    override fun getUseMap(): Boolean = canUse

    override fun setUseMap(canUse: Boolean) {
        this.canUse = canUse
    }

    override fun copyFrom(original: IMapBlocker) {
        this.setUseMap(original.getUseMap())
    }
}