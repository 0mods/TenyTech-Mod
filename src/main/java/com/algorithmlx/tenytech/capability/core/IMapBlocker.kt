package com.algorithmlx.tenytech.capability.core

interface IMapBlocker {
    fun getUseMap(): Boolean

    fun setUseMap(canUse: Boolean)
}