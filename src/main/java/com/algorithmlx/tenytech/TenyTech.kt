package com.algorithmlx.tenytech

import com.algorithmlx.tenytech.init.Register
import com.algorithmlx.tenytech.init.TTStartup
import net.minecraftforge.fml.common.Mod
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

const val ModId = "tenytech"
@JvmField val LOGGER: Logger = LogManager.getLogger("TenyTech")

@Mod(ModId)
class TenyTech {
    init {
        TTStartup.init()
    }
}