package com.autoinsurance;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Streams {

    @Output("insurance-topic")
    MessageChannel outboundChannel();

}
