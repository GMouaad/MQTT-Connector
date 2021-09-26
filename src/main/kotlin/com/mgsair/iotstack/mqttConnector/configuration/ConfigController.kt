package com.mgsair.iotstack.mqttConnector.configuration

import com.mgsair.iotstack.mqttConnector.ApplicationStartListener
import com.mgsair.iotstack.mqttConnector.model.TtnApplication
import com.mgsair.iotstack.mqttConnector.persistence.TtnApplicationRepositoryImpl
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/config")
class ConfigController(val ttnApplicationRepository: TtnApplicationRepositoryImpl) {

    private val logger = LoggerFactory.getLogger(ApplicationStartListener::class.java)

    @GetMapping("/applications")
    fun getAllApplications(): List<TtnApplication> {
        // return all applications/topics listening to
        val allApps = ttnApplicationRepository.findAll()
        logger.info("Returning ${allApps.size} applications info")
        return allApps
    }

    @PostMapping("/applications")
    fun addApplication(@RequestBody ttnApplication: TtnApplication) : ResponseEntity<String> {
        // TODO: add applications/topics to list and start thread for listening to it
        logger.info("Adding the following application: $ttnApplication")
        ttnApplicationRepository.save(ttnApplication)
        return ResponseEntity.ok().body("Added Successfully")
    }

//    @DeleteMapping("/applications/{id}")
//    fun deleteApplicationById(@PathVariable id: String) {
//        logger.info("Deleting the  application with id: $id")
//        return ttnApplicationRepository.delete(id)
//    }

    /**
     * Usage: DELETE http://localhost:8008/applications?name=app-1
     */
    @DeleteMapping("/applications")
    fun deleteApplicationByName(@RequestParam(value = "name") name: String): TtnApplication {
        val app = ttnApplicationRepository.findByName(name)
        logger.info("Deleting the following application: $app")
        return app
    }
}