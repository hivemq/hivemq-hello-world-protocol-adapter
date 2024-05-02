package com.hivemq.edge.adapters.helloworld;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class HelloWorldProtocolAdapterInformationTest {

    @Test
    void getProtocolId_MustNotContainWhiteSpaces() {
        final HelloWorldProtocolAdapterInformation information = new HelloWorldProtocolAdapterInformation();
        assertFalse(information.getProtocolId().contains(" "));
    }


    @Test
    void getProtocolId_MustBeAlphaNummercialOrUnderscore() {
        final String ALPHA_NUM = "[A-Za-z0-9_]*";
        final Pattern alphaNumPattern = Pattern.compile(ALPHA_NUM);
        final HelloWorldProtocolAdapterInformation information = new HelloWorldProtocolAdapterInformation();
        assertTrue(alphaNumPattern.matcher(information.getProtocolId()).matches());
    }
}