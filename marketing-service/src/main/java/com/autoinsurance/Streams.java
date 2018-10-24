package com.autoinsurance;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Streams {

    @Output("insurance-topic")
    MessageChannel outboundChannel();

    String INPUT = "insurance-topic";

    @Input(INPUT)
    SubscribableChannel inboundGreetings();

}
