/*
 * Copyright 2023-present HiveMQ GmbH
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.hivemq.edge.adapters.helloworld;

import com.hivemq.adapter.sdk.api.ProtocolAdapterInformation;
import com.hivemq.adapter.sdk.api.model.*;
import com.hivemq.adapter.sdk.api.polling.PollingInput;
import com.hivemq.adapter.sdk.api.polling.PollingOutput;
import com.hivemq.adapter.sdk.api.polling.PollingProtocolAdapter;
import com.hivemq.adapter.sdk.api.state.ProtocolAdapterState;
import com.hivemq.edge.adapters.helloworld.config.HelloWorldAdapterConfig;
import com.hivemq.edge.adapters.helloworld.config.HelloWorldPollingContext;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class HelloWorldPollingProtocolAdapter implements PollingProtocolAdapter<HelloWorldPollingContext> {

    private final @NotNull HelloWorldAdapterConfig adapterConfig;
    private final @NotNull ProtocolAdapterInformation adapterInformation;
    private final @NotNull ProtocolAdapterState protocolAdapterState;
    private final @NotNull List<HelloWorldPollingContext> pollingContext;

    public HelloWorldPollingProtocolAdapter(final @NotNull ProtocolAdapterInformation adapterInformation, final @NotNull ProtocolAdapterInput<HelloWorldAdapterConfig> input) {
        this.adapterInformation = adapterInformation;
        this.adapterConfig = input.getConfig();
        this.protocolAdapterState = input.getProtocolAdapterState();
        this.pollingContext = adapterConfig.getPollingContexts();
    }

    @Override
    public @NotNull String getId() {
        return adapterConfig.getId();
    }

    @Override
    public void start(final @NotNull ProtocolAdapterStartInput input, final @NotNull ProtocolAdapterStartOutput output) {
        // any setup which should be done before the adapter starts polling comes here.
        try {
            protocolAdapterState.setConnectionStatus(ProtocolAdapterState.ConnectionStatus.STATELESS);
            output.startedSuccessfully();
        } catch (final Exception e) {
            output.failStart(e, null);
        }
    }

    @Override
    public void stop(final @NotNull ProtocolAdapterStopInput protocolAdapterStopInput, final @NotNull ProtocolAdapterStopOutput protocolAdapterStopOutput) {
        protocolAdapterStopOutput.stoppedSuccessfully();
    }


    @Override
    public @NotNull ProtocolAdapterInformation getProtocolAdapterInformation() {
        return adapterInformation;
    }

    @Override
    public void poll(final @NotNull PollingInput<HelloWorldPollingContext> pollingInput, final @NotNull PollingOutput pollingOutput) {
        // here the sampling must be done. F.e. sending a http request
        pollingOutput.addDataPoint("dataPoint1", 42);
        pollingOutput.addDataPoint("dataPoint2", 1337);
        pollingOutput.finish();
    }

    @Override
    public @NotNull List<HelloWorldPollingContext> getPollingContexts() {
        return pollingContext;
    }

    @Override
    public int getPollingIntervalMillis() {
        return adapterConfig.getPollingIntervalMillis();
    }

    @Override
    public int getMaxPollingErrorsBeforeRemoval() {
        return adapterConfig.getMaxPollingErrorsBeforeRemoval();
    }
}
