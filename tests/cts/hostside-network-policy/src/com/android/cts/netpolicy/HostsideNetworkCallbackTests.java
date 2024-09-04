/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.android.cts.netpolicy;

import static com.android.cts.netpolicy.arguments.InstrumentationArguments.ARG_WAIVE_BIND_PRIORITY;

import android.platform.test.annotations.FlakyTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

@FlakyTest(bugId = 288324467)
public class HostsideNetworkCallbackTests extends HostsideNetworkPolicyTestCase {

    @Before
    public void setUp() throws Exception {
        uninstallPackage(TEST_APP2_PKG, false);
        installPackage(TEST_APP2_APK);
    }

    @After
    public void tearDown() throws Exception {
        uninstallPackage(TEST_APP2_PKG, true);
    }

    @Test
    public void testOnBlockedStatusChanged_dataSaver() throws Exception {
        runDeviceTestsWithCustomOptions(TEST_PKG,
                TEST_PKG + ".NetworkCallbackTest", "testOnBlockedStatusChanged_dataSaver");
    }

    @Test
    public void testOnBlockedStatusChanged_powerSaver() throws Exception {
        runDeviceTestsWithCustomOptions(TEST_PKG,
                TEST_PKG + ".NetworkCallbackTest", "testOnBlockedStatusChanged_powerSaver");
    }

    // TODO(b/321848487): Annotate with @RequiresFlagsEnabled to mirror the device-side test.
    @Test
    public void testOnBlockedStatusChanged_default() throws Exception {
        runDeviceTestsWithCustomOptions(TEST_PKG, TEST_PKG + ".NetworkCallbackTest",
                "testOnBlockedStatusChanged_default", Map.of(ARG_WAIVE_BIND_PRIORITY, "true"));
    }
}

