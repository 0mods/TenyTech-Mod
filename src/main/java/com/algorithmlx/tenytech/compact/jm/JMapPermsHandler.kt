package com.algorithmlx.tenytech.compact.jm

import journeymap.client.ui.UIManager
import journeymap.client.waypoint.WaypointStore

class JMapPermsHandler(private val uiManager: UIManager = UIManager.INSTANCE, private val waypointStore: WaypointStore = WaypointStore.INSTANCE) {
    fun toggleMinimap(enable: Boolean) {
        this.uiManager.isMiniMapEnabled = enable
    }

    fun clearDeathPoints() {
        for (point in waypointStore.all) {
            if (point.isDeathPoint) {
                point.isEnable = false
                point.setDirty()
            }
        }
    }

    fun clearWaypoints() {
        for (point in waypointStore.all) {
            if (!point.isDeathPoint) {
                point.isEnable = false
                point.setDirty()
            }
        }
    }

    fun disableAll() {
        this.toggleMinimap(false)
        this.clearWaypoints()
        this.clearDeathPoints()
    }
}