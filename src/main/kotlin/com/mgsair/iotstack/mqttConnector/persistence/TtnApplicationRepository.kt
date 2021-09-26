package com.mgsair.iotstack.mqttConnector.persistence

import com.mgsair.iotstack.mqttConnector.model.TtnApplication

interface TtnApplicationRepository {
    fun save(ttnApplication: TtnApplication)
    fun findAll(): List<TtnApplication>
    fun findById(id: String): TtnApplication
    fun findByName(id: String): TtnApplication
    fun update(ttnApplication: TtnApplication)
    fun delete(id: String)
}