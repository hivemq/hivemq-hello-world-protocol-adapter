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

import com.codahale.metrics.MetricRegistry;
import com.hivemq.edge.adapters.http.model.HelloWorldData;
import com.hivemq.edge.modules.adapters.model.ProtocolAdapterStartOutput;
import com.hivemq.edge.modules.adapters.model.impl.AbstractPollingProtocolAdapter;
import com.hivemq.edge.modules.api.adapters.ProtocolAdapterInformation;
import com.hivemq.edge.modules.config.impl.AdapterSubscriptionImpl;
import com.hivemq.extension.sdk.api.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

/**
 * @author HiveMQ Adapter Generator
 */
public class HelloWorldProtocolAdapter extends AbstractPollingProtocolAdapter<HelloWorldAdapterConfig, HelloWorldData> {

    private static final @NotNull Logger log = LoggerFactory.getLogger(HelloWorldProtocolAdapter.class);

    private final @NotNull HelloWorldAdapterConfig adapterConfig;

    public HelloWorldProtocolAdapter(final @NotNull ProtocolAdapterInformation adapterInformation,
                                     final @NotNull HelloWorldAdapterConfig adapterConfig,
                                     final @NotNull MetricRegistry metricRegistry,
                                     final @NotNull String version) {
        super(adapterInformation, adapterConfig, metricRegistry);
        this.adapterConfig = adapterConfig;
    }

    @Override
    protected @NotNull CompletableFuture<ProtocolAdapterStartOutput> startInternal(final @NotNull ProtocolAdapterStartOutput output) {
        // any setup which should be done before the adapter starts polling comes here.
        // if heavy lifting needs to be done, the future of this method can be set async to avoid blocking the calling thread.
        try {
            setConnectionStatus(ConnectionStatus.STATELESS);

            // start the implemented polling scheduling of the AbstractPollingProtocolAdapter
            startPolling(new Sampler(adapterConfig));

            return CompletableFuture.completedFuture(output);
        } catch (Exception e) {
            return CompletableFuture.failedFuture(e);
        }
    }

    @Override
    protected @NotNull CompletableFuture<Void> stopInternal() {
        return CompletableFuture.completedFuture(null);
    }


    @Override
    protected @NotNull CompletableFuture<?> captureDataSample(final @NotNull HelloWorldData sample) {
        log.info("captureDataSample invoked");
        return super.captureDataSample(sample);
    }

    @Override
    protected @NotNull CompletableFuture<HelloWorldData> onSamplerInvoked(final HelloWorldAdapterConfig config) {
        // here the sampling must be done. F.e. sending a http request and returning the obtained value via a Completable Future
        final AdapterSubscriptionImpl adapterSubscription = new AdapterSubscriptionImpl(config.getDestination(), config.getQos(), null);
        final HelloWorldData data = new HelloWorldData(adapterSubscription, config.getUrl());
        data.addDataPoint("dataPoint1", 42);
        data.addDataPoint("dataPoint2", 1337);
        return CompletableFuture.completedFuture(data);
    }
}
