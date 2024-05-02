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
package com.hivemq.edge.adapters.helloworld.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hivemq.edge.modules.adapters.data.DataPoint;
import com.hivemq.edge.modules.adapters.data.ProtocolAdapterDataSample;
import com.hivemq.edge.modules.adapters.factories.DataPointFactory;
import com.hivemq.edge.modules.config.AdapterSubscription;
import com.hivemq.edge.modules.config.UserProperty;
import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extension.sdk.api.annotations.Nullable;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author HiveMQ Adapter Generator
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HelloWorldData implements ProtocolAdapterDataSample {

    private final @NotNull AdapterSubscription adapterSubscription;
    private final @NotNull String requestUrl;
    private final @NotNull DataPointFactory dataPointFactory;

    //-- Handle multiple tags in the same sample
    protected @NotNull List<DataPoint> dataPoints = new CopyOnWriteArrayList<>();
    private @NotNull Long timestamp = System.currentTimeMillis();

    public HelloWorldData(
            final @NotNull AdapterSubscription adapterSubscription,
            final @NotNull String requestUrl,
            final @NotNull DataPointFactory dataPointFactory) {
        this.adapterSubscription = adapterSubscription;
        this.requestUrl = requestUrl;
        this.dataPointFactory = dataPointFactory;
    }

    @Override
    public @NotNull AdapterSubscription getSubscription() {
        return adapterSubscription;
    }

    @Override
    public @NotNull Long getTimestamp() {
        return timestamp;
    }

    @Override
    public void addDataPoint(@NotNull String tagName, @NotNull Object tagValue) {
        dataPoints.add(dataPointFactory.create(tagName, tagValue));
    }

    @Override
    public void setDataPoints(@NotNull List<DataPoint> dataPoints) {
        this.dataPoints = dataPoints;
    }

    @Override
    public @NotNull List<DataPoint> getDataPoints() {
        return dataPoints;
    }
}
