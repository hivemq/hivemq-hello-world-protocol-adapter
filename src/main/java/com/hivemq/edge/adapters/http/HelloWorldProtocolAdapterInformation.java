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

import com.hivemq.edge.modules.adapters.ProtocolAdapterConstants;
import com.hivemq.edge.modules.adapters.model.impl.AbstractProtocolAdapterInformation;
import com.hivemq.edge.modules.api.adapters.ProtocolAdapterCapability;
import com.hivemq.edge.modules.api.adapters.ProtocolAdapterInformation;
import com.hivemq.extension.sdk.api.annotations.NotNull;

import java.util.List;

/**
 * @author HiveMQ Adapter Generator
 */
public class HelloWorldProtocolAdapterInformation extends AbstractProtocolAdapterInformation {

    public static final @NotNull ProtocolAdapterInformation INSTANCE = new HelloWorldProtocolAdapterInformation();

    protected HelloWorldProtocolAdapterInformation() {
    }

    @Override
    public @NotNull String getProtocolName() {
        return "Hello World Protocol";
    }

    @Override
    public @NotNull String getProtocolId() {
        return "Hello_World_Protocol";
    }

    @Override
    public @NotNull String getDisplayName() {
        return "Hello World Protocol Adapter";
    }

    @Override
    public @NotNull String getDescription() {
        return "CHANGE ME: Here the description of your protocol adapter needs to go.";
    }

    @Override
    public String getVersion() {
        return "0.0.1";
    }

    @Override
    public ProtocolAdapterConstants.CATEGORY getCategory() {
        // this indicates for which use cases this protocol adapter is intended. See the ProtocolAdapterConstants.CATEGORY enum for more information.
        return ProtocolAdapterConstants.CATEGORY.CONNECTIVITY;
    }

    @Override
    public byte getCapabilities() {
        // this indicates what capabilities this protocol adapter has. E.g. READ/WRITE. See the ProtocolAdapterCapability enum for more information.
        return ProtocolAdapterCapability.READ;
    }

    @Override
    public @NotNull String getLogoUrl() {
        // this is a default image that is always available.
        return "/images/hivemq-icon.png";
    }

    @Override
    public List<ProtocolAdapterConstants.TAG> getTags() {
        // here you can set which Tags should be applied to this protocol adapter
        return List.of(ProtocolAdapterConstants.TAG.INTERNET,
                ProtocolAdapterConstants.TAG.TCP,
                ProtocolAdapterConstants.TAG.WEB);
    }
}
