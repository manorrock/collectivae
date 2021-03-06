/*
 * Copyright (c) 2002-2021 Manorrock.com. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 *
 *   1. Redistributions of source code must retain the above copyright notice, 
 *      this list of conditions and the following disclaimer.
 *   2. Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *   3. Neither the name of the copyright holder nor the names of its 
 *      contributors may be used to endorse or promote products derived from
 *      this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE 
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package com.manorrock.sphynx.hue.bridge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

/**
 * The JUnit test for the DefaultHueBridge class.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class HueBridgeTest {

    /**
     * Test authorize method.
     */
    @Test
    public void authorize() {
        if (!System.getProperty("sphynx.hue.bridge.baseUrl").equals("")) {
            DefaultHueBridge bridge = new DefaultHueBridge();
            bridge.setBaseUrl(System.getProperty("sphynx.hue.bridge.baseUrl"));
            assertNotNull(bridge.authorize("testAuthorize"));
        }
    }

    /**
     * Test changeLightState method.
     */
    @Test
    public void testChangeLightState() {
        if (!System.getProperty("sphynx.hue.lct06.id").equals("")) {
            DefaultHueBridge bridge = new DefaultHueBridge();
            bridge.setBaseUrl(System.getProperty("sphynx.hue.bridge.baseUrl"));
            bridge.setUsername(System.getProperty("sphynx.hue.bridge.username"));
            bridge.changeLightState(
                    Integer.valueOf(System.getProperty("sphynx.hue.lct06.id")),
                    "on",
                    true);
        }
    }

    /**
     * Test getBaseConfig method.
     */
    @Test
    public void testGetBaseConfig() {
        DefaultHueBridge bridge = new DefaultHueBridge();
        bridge.setBaseUrl("http://localhost");
        assertNull(bridge.getBaseConfig());
    }

    /**
     * Test getBaseConfig method.
     */
    @Test
    public void testGetBaseConfig2() {
        if (!System.getProperty("sphynx.hue.bridge.baseUrl").equals("")) {
            DefaultHueBridge bridge = new DefaultHueBridge();
            bridge.setBaseUrl(System.getProperty("sphynx.hue.bridge.baseUrl"));
            assertNotNull(bridge.getBaseConfig());
        }
    }

    /**
     * Test getBaseUrl method.
     */
    @Test
    public void testGetBaseUrl() {
        DefaultHueBridge bridge = new DefaultHueBridge();
        bridge.setBaseUrl("https://localhost");
        assertEquals("https://localhost", bridge.getBaseUrl());
    }

    /**
     * Test getLightInfo method.
     */
    @Test
    public void testGetLightInfo() {
        if (!System.getProperty("sphynx.hue.lct06.id").equals("")) {
            DefaultHueBridge bridge = new DefaultHueBridge();
            bridge.setBaseUrl(System.getProperty("sphynx.hue.bridge.baseUrl"));
            bridge.setUsername(System.getProperty("sphynx.hue.bridge.username"));
            assertNotNull(bridge.getLightInfo(
                    Integer.valueOf(System.getProperty("sphynx.hue.lct06.id"))));
        }
    }

    /**
     * Test getUsername method.
     */
    @Test
    public void testGetUsername() {
        DefaultHueBridge bridge = new DefaultHueBridge();
        bridge.setUsername(System.getProperty("sphynx.hue.bridge.username"));
        assertEquals(System.getProperty("sphynx.hue.bridge.username"), bridge.getUsername());
    }
}
