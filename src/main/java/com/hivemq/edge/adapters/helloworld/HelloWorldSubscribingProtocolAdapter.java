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

import com.hivemq.edge.modules.adapters.PollingProtocolAdapter;
import com.hivemq.edge.modules.adapters.data.ProtocolAdapterDataSample;
import com.hivemq.edge.modules.adapters.factories.AdapterFactories;
import com.hivemq.edge.modules.adapters.model.*;
import com.hivemq.edge.modules.api.adapters.ProtocolAdapterInformation;
import com.hivemq.edge.modules.api.adapters.ProtocolAdapterState;
import com.hivemq.extension.sdk.api.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class HelloWorldSubscribingProtocolAdapter implements PollingProtocolAdapter {
    private static final Logger log = LoggerFactory.getLogger(HelloWorldSubscribingProtocolAdapter.class);

    private final @NotNull HelloWorldAdapterConfig adapterConfig;
    private final @NotNull ProtocolAdapterInformation adapterInformation;
    private final @NotNull ProtocolAdapterState protocolAdapterState;
    private final @NotNull AdapterFactories adapterFactories;

    public HelloWorldSubscribingProtocolAdapter(
            final @NotNull ProtocolAdapterInformation adapterInformation, final @NotNull ProtocolAdapterInput<HelloWorldAdapterConfig> input) {
        this.adapterInformation = adapterInformation;
        this.adapterConfig = input.getConfig();
        this.protocolAdapterState = input.getProtocolAdapterState();
        this.adapterFactories = input.adapterFactories();
    }
    @Override
    public @NotNull String getId() {
        return adapterConfig.getId();
    }

    @Override
    public @NotNull CompletableFuture<ProtocolAdapterStartOutput> start(@NotNull ProtocolAdapterStartInput input, @NotNull ProtocolAdapterStartOutput output) {
        try {
            // connect and subscribe your client here
            return new CompletableFuture<>();
        } catch (Exception e) {
            // error handling like logging
            return CompletableFuture.failedFuture(e);
        }
    }

    @Override
    public @NotNull CompletableFuture<Void> stop() {
        try {
            // gracefully disconnect and cleanup resources here
        } catch (Exception e) {
            return CompletableFuture.failedFuture(e);
        }
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public @NotNull CompletableFuture<? extends ProtocolAdapterDataSample> poll() {
        return null;
    }


    @Override
    public @NotNull CompletableFuture<Void> discoverValues(
            final @NotNull ProtocolAdapterDiscoveryInput input, final @NotNull ProtocolAdapterDiscoveryOutput output) {
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public @NotNull ProtocolAdapterInformation getProtocolAdapterInformation() {
        return adapterInformation;
    }


}
