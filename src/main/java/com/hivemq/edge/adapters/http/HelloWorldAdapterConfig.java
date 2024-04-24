/*
 * Copyright 2023-present HiveMQ GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hivemq.edge.adapters.http;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.hivemq.edge.modules.adapters.annotations.ModuleConfigField;
import com.hivemq.edge.modules.config.impl.AbstractPollingProtocolAdapterConfig;
import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extension.sdk.api.annotations.Nullable;

@JsonPropertyOrder({
        "url",
        "destination"})
public class HelloWorldAdapterConfig extends AbstractPollingProtocolAdapterConfig {

    @JsonProperty("url")
    @ModuleConfigField(title = "URL", description = "The url of the endpoint you want to connect to.",
            format = ModuleConfigField.FieldType.URI, required = true)
    private @NotNull String url;

    @JsonProperty(value = "destination", required = true)
    @ModuleConfigField(title = "Destination Topic",
            description = "The topic to publish data on",
            required = true,
            format = ModuleConfigField.FieldType.MQTT_TOPIC)
    private @Nullable String destination;

    @JsonProperty(value = "qos", required = true)
    @ModuleConfigField(title = "QoS",
            description = "MQTT Quality of Service level",
            required = true,
            numberMin = 0,
            numberMax = 2,
            defaultValue = "0")
    private int qos = 0;


    public HelloWorldAdapterConfig() {
    }

    public HelloWorldAdapterConfig(final @NotNull String adapterId) {
        this.id = adapterId;
    }


    public @NotNull String getUrl() {
        return url;
    }

    public @NotNull String getDestination() {
        return destination;
    }

    public int getQos() {
        return qos;
    }
}
