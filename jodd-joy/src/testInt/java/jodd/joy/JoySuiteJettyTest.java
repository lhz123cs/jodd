// Copyright (c) 2003-present, Jodd Team (http://jodd.org)
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions are met:
//
// 1. Redistributions of source code must retain the above copyright notice,
// this list of conditions and the following disclaimer.
//
// 2. Redistributions in binary form must reproduce the above copyright
// notice, this list of conditions and the following disclaimer in the
// documentation and/or other materials provided with the distribution.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
// AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
// IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
// ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
// LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
// CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
// SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
// INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
// CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
// ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
// POSSIBILITY OF SUCH DAMAGE.

package jodd.joy;

import jodd.exception.UncheckedException;
import jodd.joy.servers.JettyTestServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class JoySuiteJettyTest extends JoyTestBase {

	public static boolean isRunning;
	{
		port = 8174;
	}

	/**
	 * Starts Tomcat after the suite.
	 */
	@BeforeAll
	static void beforeClass() {
		isRunning = true;
		startJetty();
	}

	/**
	 * Stop Tomcat after the suite.
	 */
	@AfterAll
	static void afterSuite() {
		isRunning = false;
		stopJetty();
	}

	// ---------------------------------------------------------------- tomcat

	private static JettyTestServer server;

	public static void startJetty() {
		if (server != null) {
			return;
		}
		server = new JettyTestServer();
		try {
			server.start();
			System.out.println("Jetty test server started");
		} catch (Exception e) {
			throw new UncheckedException(e);
		}
	}

	public static void stopJetty() {
		if (server == null) {
			return;
		}
		if (isRunning) {	// dont stop tomcat if it we are still running in the suite!
			return;
		}
		try {
			server.stop();
		} catch (Exception ignore) {
		} finally {
			System.out.println("Jetty test server stopped");
			server = null;
		}
	}

}
