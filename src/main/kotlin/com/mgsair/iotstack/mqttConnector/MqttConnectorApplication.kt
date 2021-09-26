package com.mgsair.iotstack.mqttConnector

import com.mgsair.iotstack.mqttConnector.persistence.TtnApplicationRepositoryImpl
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationStartedEvent
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

@SpringBootApplication
class MqttConnectorApplication

fun main(args: Array<String>) {
    runApplication<MqttConnectorApplication>(*args)
}

@Component
class ApplicationStartListener(val ttnApplicationRepositoryImpl: TtnApplicationRepositoryImpl) :
    ApplicationListener<ApplicationStartedEvent> {

    private val logger = LoggerFactory.getLogger(ApplicationStartListener::class.java)

    override fun onApplicationEvent(event: ApplicationStartedEvent) {
        // TODO: get list of topics to listen to from redis
        logger.info("Scanning DB for TTN applications..")
        val apps = ttnApplicationRepositoryImpl.findAll()
        logger.info("Found ${apps.size} TTN applications")
        // TODO: start the threads accordingly

    }
}