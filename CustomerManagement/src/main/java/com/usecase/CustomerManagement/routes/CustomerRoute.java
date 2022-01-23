package com.usecase.CustomerManagement.routes;

import com.usecase.CustomerManagement.controller.CustomerManagementController;
import com.usecase.CustomerManagement.controller.UserResponse;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.support.DefaultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerRoute extends RouteBuilder {

    private final CustomerManagementController customerManagementController;

    @Autowired
    public CustomerRoute(CustomerManagementController customerManagementController) {
        this.customerManagementController = customerManagementController;
    }

    @Override
    public void configure() throws Exception {
        from("rabbitmq://localhost:5672/amq.direct?queue=customer_req_queue&routingKey=req_routing_key&autoDelete=false")
                .routeId("rabbitMq-consumer")
                .to("direct:get-customer-data");

        from("direct:get-customer-data")
                .routeId("transformer")
                .unmarshal().json(JsonLibrary.Jackson, UserResponse.class)
                .process(this::transformMessage)
                .to("direct:response-customer-data");

        from("direct:response-customer-data")
                .routeId("rabbitmq-producer")
                .to("rabbitmq://localhost:5672/amq.direct?queue=customer_res_queue&routingKey=res_routing_key&autoDelete=false");
    }

    private void transformMessage(Exchange exchange) {
        var createreq = exchange.getMessage().getBody(UserResponse.class);
        createreq.setName(createreq.getName().toUpperCase());
        var response = customerManagementController.createUser(createreq);
        if (response.getStatusCode().is2xxSuccessful()) {
            Message message = new DefaultMessage(exchange.getContext());
            message.setBody(response.getBody());
            exchange.setMessage(message);
        } else {
            exchange.getMessage().setHeader(response.getStatusCode().toString(),response.getStatusCodeValue());
        }
    }


}
