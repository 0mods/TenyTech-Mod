package com.algorithmlx.tenytech.capability.core

interface IJEIBlocker {
    fun getUseJEI(): Boolean

    fun setUseJEI(canUse: Boolean)
}