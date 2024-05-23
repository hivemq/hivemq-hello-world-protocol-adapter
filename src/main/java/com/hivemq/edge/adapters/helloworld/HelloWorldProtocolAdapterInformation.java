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


import com.hivemq.adapter.sdk.api.ProtocolAdapterCapability;
import com.hivemq.adapter.sdk.api.ProtocolAdapterCategory;
import com.hivemq.adapter.sdk.api.ProtocolAdapterInformation;
import com.hivemq.adapter.sdk.api.ProtocolAdapterTag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


import java.util.EnumSet;
import java.util.List;

/**
 * @author HiveMQ Adapter Generator
 */
public class HelloWorldProtocolAdapterInformation implements ProtocolAdapterInformation {

    public static final @NotNull ProtocolAdapterInformation INSTANCE = new HelloWorldProtocolAdapterInformation();

    // TODO improve Java Doc

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
    public @NotNull String getUrl() {
        return "CHANGE_ME";
    }

    @Override
    public @NotNull String getVersion() {
        return "0.0.1";
    }

    @Override
    public @NotNull EnumSet<ProtocolAdapterCapability> getCapabilities() {
        // this indicates what capabilities this protocol adapter has. E.g. READ/WRITE. See the ProtocolAdapterCapability enum for more information.
        return EnumSet.of(ProtocolAdapterCapability.READ);
    }

    @Override
    public @NotNull String getLogoUrl() {
        // this is a default image that is always available.
        return "/images/helloWorld.png";
    }

    @Override
    public @NotNull String getAuthor() {
        return "CHANGE_ME";
    }

    @Override
    public @Nullable ProtocolAdapterCategory getCategory() {
        // this indicates for which use cases this protocol adapter is intended. See the ProtocolAdapterConstants.CATEGORY enum for more information.
        return ProtocolAdapterCategory.CONNECTIVITY;
    }

    @Override
    public List<ProtocolAdapterTag> getTags() {
        // here you can set which Tags should be applied to this protocol adapter
        return List.of(ProtocolAdapterTag.INTERNET,
                ProtocolAdapterTag.TCP,
                ProtocolAdapterTag.WEB);
    }
}
