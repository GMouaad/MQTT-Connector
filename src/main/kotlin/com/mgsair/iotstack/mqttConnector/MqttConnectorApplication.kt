package com.mgsair.iotstack.mqttConnector

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MqttConnectorApplication

fun main(args: Array<String>) {
	runApplication<MqttConnectorApplication>(*args)
}
