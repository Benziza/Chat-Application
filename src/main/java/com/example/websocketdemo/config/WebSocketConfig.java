package com.example.websocketdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
//The @EnableWebSocketMessageBroker is used to enable our WebSocket server.
@EnableWebSocketMessageBroker
/*
We implement WebSocketMessageBrokerConfigurer interface and provide implementation for some of its methods to configure
the websocket connection.
 */
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {


    //In the first method, we register a websocket endpoint that the clients will use to connect to our websocket server.
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        /*
            we use of withSockJS() with the endpoint configuration. SockJS is used to enable fallback options for browsers
            that don’t support websocket.
            Fallback options : can be defined as a backup option which is reverted to when a primary option fails to make contact.
         */
        registry.addEndpoint("/ws").withSockJS();
        /*
            STOMP stands for Simple Text Oriented Messaging Protocol.
            It is a messaging protocol that defines the format and rules for data exchange.
        */
        /*
            Why do we need STOMP? Well, WebSocket is just a communication protocol.
            It doesn’t define things like - How to send a message only to users who are subscribed to a particular topic,
            or how to send a message to a particular user.
            We need STOMP for these functionalities.
         */
    }

    @Override
    //we’re configuring a message broker that will be used to route messages from one client to another.
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        /*
            the messages whose destination starts with “/app” should be routed to message-handling methods
            (we’ll define these methods shortly).
         */
        registry.setApplicationDestinationPrefixes("/app");
        //the messages whose destination starts with “/topic” should be routed to the message broker.
        //"message broker" do a broadcasts messages to all the connected clients who are subscribed to a particular topic.
        registry.enableSimpleBroker("/topic");
    }
}