/*
 * (C) Copyright 2014-2020, by Dimitrios Michail
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
package org.jgrapht.capi.impl;

import java.util.Iterator;

import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.ObjectHandle;
import org.graalvm.nativeimage.ObjectHandles;
import org.graalvm.nativeimage.c.function.CEntryPoint;
import org.graalvm.nativeimage.c.type.CDoublePointer;
import org.graalvm.nativeimage.c.type.CIntPointer;
import org.graalvm.nativeimage.c.type.WordPointer;
import org.jgrapht.capi.Constants;
import org.jgrapht.capi.JHeapsContext.Status;
import org.jgrapht.capi.error.StatusReturnExceptionHandler;

public class IteratorApi {

	private static ObjectHandles globalHandles = ObjectHandles.getGlobal();

	@CEntryPoint(name = Constants.LIB_PREFIX + "it_next_int", exceptionHandler = StatusReturnExceptionHandler.class)
	public static int iteratorNextInt(IsolateThread thread, ObjectHandle itHandle, CIntPointer res) {
		Iterator<Integer> it = globalHandles.get(itHandle);
		if (res.isNonNull()) {
			res.write(it.next());
		}
		return Status.STATUS_SUCCESS.getCValue();
	}

	@CEntryPoint(name = Constants.LIB_PREFIX + "it_next_double", exceptionHandler = StatusReturnExceptionHandler.class)
	public static int iteratorNextDouble(IsolateThread thread, ObjectHandle itHandle, CDoublePointer res) {
		Iterator<Double> it = globalHandles.get(itHandle);
		if (res.isNonNull()) {
			res.write(it.next());
		}
		return Status.STATUS_SUCCESS.getCValue();
	}

	@CEntryPoint(name = Constants.LIB_PREFIX + "it_next_object", exceptionHandler = StatusReturnExceptionHandler.class)
	public static int iteratorNextObject(IsolateThread thread, ObjectHandle itHandle, WordPointer res) {
		Iterator<?> it = globalHandles.get(itHandle);
		Object o = it.next();
		if (res.isNonNull()) {
			res.write(globalHandles.create(o));
		}
		return Status.STATUS_SUCCESS.getCValue();
	}

	@CEntryPoint(name = Constants.LIB_PREFIX + "it_hasnext", exceptionHandler = StatusReturnExceptionHandler.class)
	public static int iteratorHasNext(IsolateThread thread, ObjectHandle itHandle, CIntPointer res) {
		Iterator<?> it = globalHandles.get(itHandle);
		if (res.isNonNull()) {
			res.write(it.hasNext() ? 1 : 0);
		}
		return Status.STATUS_SUCCESS.getCValue();
	}

}
