package com.mgsair.iotstack.mqttConnector.model

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import java.io.Serializable

@RedisHash("ttn-app")
data class TtnApplication(
    @Id val id: String?,
    val name: String,
    val appKey: String
) : Serializable {

    companion object {
        const val TTN_APP_KEY = "ttn-app"
    }

}