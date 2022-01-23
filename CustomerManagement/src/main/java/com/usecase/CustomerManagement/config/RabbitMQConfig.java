/*
package com.usecase.CustomerManagement.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue queue() {
        return new Queue("customer_req_queue");
    }

    @Bean
    public Queue resQueue() {
        return new Queue("customer_res_queue");
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("customer_exchange");
    }

    @Bean
    public TopicExchange resExchange() {
        return new TopicExchange("customer_res_exchange");
    }

    @Bean
    public Binding bindingResQueue(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("res_routing_key");
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("req_routing_key");
    }

    @Bean
    public MessageConverter msgConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(msgConverter());
        return rabbitTemplate;
    }
}

*/
