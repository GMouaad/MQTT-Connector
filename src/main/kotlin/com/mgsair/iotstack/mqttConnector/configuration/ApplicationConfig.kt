package com.mgsair.iotstack.mqttConnector.configuration

import com.mgsair.iotstack.mqttConnector.model.TtnApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer


@Configuration
@EnableRedisRepositories
class ApplicationConfig {

    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        return LettuceConnectionFactory()
    }

    @Bean
    fun redisTtnAppTemplate(connectionFactory: RedisConnectionFactory): RedisTemplate<String, TtnApplication> { //TtnApplication
        val template: RedisTemplate<String, TtnApplication> = RedisTemplate<String, TtnApplication>()
        val keySerializer = StringRedisSerializer()
        val valueSerializer = Jackson2JsonRedisSerializer<TtnApplication>(TtnApplication::class.java)
        template.setConnectionFactory(connectionFactory)
        template.keySerializer = keySerializer
        template.valueSerializer = valueSerializer
        return template
    }
}