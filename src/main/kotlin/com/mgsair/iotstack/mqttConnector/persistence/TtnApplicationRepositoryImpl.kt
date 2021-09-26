package com.mgsair.iotstack.mqttConnector.persistence

import com.mgsair.iotstack.mqttConnector.model.TtnApplication
import com.mgsair.iotstack.mqttConnector.model.TtnApplication.Companion.TTN_APP_KEY
import org.springframework.data.redis.core.HashOperations
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class TtnApplicationRepositoryImpl(
    redisTemplate: RedisTemplate<String, TtnApplication>
) : TtnApplicationRepository {

    val hashOperations: HashOperations<String, String, TtnApplication> = redisTemplate.opsForHash()


    override fun save(ttnApplication: TtnApplication) {
        if (ttnApplication.id.isNullOrBlank()) {
            val id = UUID.randomUUID().toString()
            val newApplication = TtnApplication(id, name = ttnApplication.name, appKey = ttnApplication.appKey)
            hashOperations.put(TTN_APP_KEY, id, newApplication)
        } else {
            hashOperations.put(TTN_APP_KEY, ttnApplication.id, ttnApplication)
        }
    }

    override fun findAll(): List<TtnApplication> {
        return hashOperations.values(TTN_APP_KEY)
    }

    override fun findById(id: String): TtnApplication {
        return hashOperations.get(TTN_APP_KEY, id) as TtnApplication
    }

    override fun findByName(id: String): TtnApplication {
        // TODO property name should be @Indexed ??
        TODO("Not yet implemented")
    }

    override fun update(ttnApplication: TtnApplication) {
        save(ttnApplication)
    }

    override fun delete(id: String) {
        hashOperations.delete(TTN_APP_KEY, id)
    }
}