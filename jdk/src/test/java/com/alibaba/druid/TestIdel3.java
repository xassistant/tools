/*
 * Copyright 1999-2017 Alibaba Group Holding Ltd.
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
package com.alibaba.druid;

import java.lang.management.ManagementFactory;
import java.sql.Connection;
import java.util.concurrent.CountDownLatch;

import javax.management.ObjectName;

import org.junit.Assert;
import junit.framework.TestCase;

public class TestIdel3 extends TestCase {

	public void test_idle2() throws Exception {

		concurrent("i have got it", 100, 1000);

	}

	private void concurrent(final String dataSource, int threadCount, final int loopCount) throws InterruptedException {

		final CountDownLatch startLatch = new CountDownLatch(1);
		final CountDownLatch endLatch = new CountDownLatch(threadCount);
		Thread[] threads = new Thread[threadCount];
		for (int i = 0; i < threadCount; ++i) {
			threads[i] = new Thread("thread-" + i) {

				public void run() {
					try {
						startLatch.await();
						for (int i = 0; i < loopCount; ++i) {
//							System.out.println();
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					} finally {
						endLatch.countDown();
					}
				}
			};
		}

		for (int i = 0; i < threadCount; ++i) {
			threads[i].start();
		}
		startLatch.countDown();
		System.out.println("concurrent start...");
		endLatch.await();
		System.out.println("concurrent end");
	}
}
