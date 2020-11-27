/*
 * (C) Copyright 2020-2020, by Dimitrios Michail
 *
 * JHeaps Library
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
 * 
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jheaps.capi.impl;

import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.ObjectHandles;
import org.graalvm.nativeimage.c.function.CEntryPoint;
import org.graalvm.nativeimage.c.type.WordPointer;
import org.jheaps.capi.Constants;
import org.jheaps.capi.JHeapsContext.HeapType;
import org.jheaps.capi.JHeapsContext.Status;
import org.jheaps.capi.error.StatusReturnExceptionHandler;
import org.jheaps.tree.FibonacciHeap;
import org.jheaps.tree.PairingHeap;
import org.jheaps.tree.RankPairingHeap;

/**
 * Basic graph operations
 */
public class HeapCreateApi {

	private static ObjectHandles globalHandles = ObjectHandles.getGlobal();

	/**
	 * Create a heap and return its handle.
	 *
	 * @param thread the thread isolate
	 * @return the heap handle
	 */
	@CEntryPoint(name = Constants.LIB_PREFIX + "heap_create", exceptionHandler = StatusReturnExceptionHandler.class)
	public static int createHeap(IsolateThread thread, HeapType heapType, WordPointer res) {
		Object heap = null;
		switch (heapType) {
		case HEAP_TYPE_PAIRING:
			heap = new PairingHeap<>();
			break;
		case HEAP_TYPE_RANKPAIRING:
			heap = new RankPairingHeap<>();
			break;
		case HEAP_TYPE_FIBONACCI:
		default:
			heap = new FibonacciHeap<>();
			break;
		}
		if (res.isNonNull()) {
			res.write(globalHandles.create(heap));
		}
		return Status.STATUS_SUCCESS.getCValue();
	}

}
