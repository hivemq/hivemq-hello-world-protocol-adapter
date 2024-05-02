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
package com.hivemq.edge.adapters.helloworld;

import com.hivemq.edge.adapters.helloworld.model.HelloWorldData;
import com.hivemq.edge.modules.adapters.PollingProtocolAdapter;
import com.hivemq.edge.modules.adapters.data.ProtocolAdapterDataSample;
import com.hivemq.edge.modules.adapters.factories.AdapterFactories;
import com.hivemq.edge.modules.adapters.model.ProtocolAdapterInput;
import com.hivemq.edge.modules.adapters.model.ProtocolAdapterStartInput;
import com.hivemq.edge.modules.adapters.model.ProtocolAdapterStartOutput;
import com.hivemq.edge.modules.api.adapters.ProtocolAdapterInformation;
import com.hivemq.edge.modules.api.adapters.ProtocolAdapterState;
import com.hivemq.edge.modules.config.AdapterSubscription;
import com.hivemq.extension.sdk.api.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

/**
 * @author HiveMQ Adapter Generator
 */
public class HelloWorldPollingProtocolAdapter implements PollingProtocolAdapter {

    private final @NotNull HelloWorldAdapterConfig adapterConfig;
    private final @NotNull ProtocolAdapterInformation adapterInformation;
    private final @NotNull ProtocolAdapterState protocolAdapterState;
    private final @NotNull AdapterFactories adapterFactories;

    public HelloWorldPollingProtocolAdapter(final @NotNull ProtocolAdapterInformation adapterInformation, final @NotNull ProtocolAdapterInput<HelloWorldAdapterConfig> input) {
        this.adapterInformation = adapterInformation;
        this.adapterConfig = input.getConfig();
        this.protocolAdapterState = input.getProtocolAdapterState();
        this.adapterFactories = input.adapterFactories();
    }

    @Override
    public @NotNull CompletableFuture<? extends ProtocolAdapterDataSample> poll() {
        // here the sampling must be done. F.e. sending a http request and returning the obtained value via a Completable Future


        final AdapterSubscription adapterSubscription = adapterFactories.adapterSubscriptionFactory().create(adapterConfig.getDestination(), adapterConfig.getQos(), null);
        final HelloWorldData data = new HelloWorldData(adapterSubscription, adapterConfig.getUrl(), adapterFactories.dataPointFactory());
        data.addDataPoint("dataPoint1", 42);
        data.addDataPoint("dataPoint2", 1337);
        return CompletableFuture.completedFuture(data);
    }

    @Override
    public @NotNull String getId() {
        return adapterConfig.getId();
    }

    @Override
    public @NotNull CompletableFuture<ProtocolAdapterStartOutput> start(final @NotNull ProtocolAdapterStartInput input, final @NotNull ProtocolAdapterStartOutput output) {
        // any setup which should be done before the adapter starts polling comes here.
        // if heavy lifting needs to be done, the future of this method can be set async to avoid blocking the calling thread.
        try {
            protocolAdapterState.setConnectionStatus(ProtocolAdapterState.ConnectionStatus.STATELESS);
            return CompletableFuture.completedFuture(output);
        } catch (Exception e) {
            return CompletableFuture.failedFuture(e);
        }
    }

    @Override
    public @NotNull CompletableFuture<Void> stop() {
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public @NotNull ProtocolAdapterInformation getProtocolAdapterInformation() {
        return adapterInformation;
    }
}
