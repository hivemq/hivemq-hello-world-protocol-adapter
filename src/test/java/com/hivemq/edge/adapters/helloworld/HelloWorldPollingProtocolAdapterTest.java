package com.hivemq.edge.adapters.helloworld;


import com.hivemq.adapter.sdk.api.model.ProtocolAdapterInput;
import com.hivemq.adapter.sdk.api.polling.PollingInput;
import com.hivemq.adapter.sdk.api.polling.PollingOutput;
import com.hivemq.edge.adapters.helloworld.config.HelloWorldAdapterConfig;
import com.hivemq.edge.adapters.helloworld.config.HelloWorldPollingContext;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HelloWorldPollingProtocolAdapterTest {

    @TempDir
    @NotNull File temporaryDir;

    private final @NotNull ProtocolAdapterInput<HelloWorldAdapterConfig> adapterInput = mock();
    private final @NotNull  HelloWorldAdapterConfig config = mock();

    @Test
    void test_poll_whenFileIsPresent_thenFileContentsAreSetInOutput() throws IOException {
        final File fileWithData = new File(temporaryDir, "data.txt");
        Files.write(fileWithData.toPath(), "Hello World".getBytes(StandardCharsets.UTF_8));
        when(adapterInput.getConfig()).thenReturn(config);
        PollingInput<HelloWorldPollingContext> pollingInput = mock();
        when(pollingInput.getPollingContext()).thenReturn(new HelloWorldPollingContext("mqttTopic", 1, List.of()));
        TestPollingOutput pollingOutput = new TestPollingOutput();

        HelloWorldPollingProtocolAdapter adapter = new HelloWorldPollingProtocolAdapter(new HelloWorldProtocolAdapterInformation(), adapterInput);

        adapter.poll(pollingInput, pollingOutput);

        assertEquals(42, pollingOutput.getDataPoints().get("dataPoint1"));
        assertEquals(1337, pollingOutput.getDataPoints().get("dataPoint2"));

    }
}