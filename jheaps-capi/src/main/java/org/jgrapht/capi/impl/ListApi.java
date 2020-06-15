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

import java.util.ArrayList;
import java.util.List;

import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.ObjectHandle;
import org.graalvm.nativeimage.ObjectHandles;
import org.graalvm.nativeimage.c.function.CEntryPoint;
import org.graalvm.nativeimage.c.type.CIntPointer;
import org.graalvm.nativeimage.c.type.WordPointer;
import org.jgrapht.capi.Constants;
import org.jgrapht.capi.JHeapsContext.Status;
import org.jgrapht.capi.error.StatusReturnExceptionHandler;

public class ListApi {

	private static ObjectHandles globalHandles = ObjectHandles.getGlobal();

	@CEntryPoint(name = Constants.LIB_PREFIX + "list_create", exceptionHandler = StatusReturnExceptionHandler.class)
	public static int createList(IsolateThread thread, WordPointer res) {
		if (res.isNonNull()) {
			res.write(globalHandles.create(new ArrayList<>()));
		}
		return Status.STATUS_SUCCESS.getCValue();
	}

	@CEntryPoint(name = Constants.LIB_PREFIX + "list_it_create", exceptionHandler = StatusReturnExceptionHandler.class)
	public static int createListIterator(IsolateThread thread, ObjectHandle handle, WordPointer res) {
		List<?> list = globalHandles.get(handle);
		if (res.isNonNull()) {
			res.write(globalHandles.create(list.iterator()));
		}
		return Status.STATUS_SUCCESS.getCValue();
	}

	@CEntryPoint(name = Constants.LIB_PREFIX + "list_size", exceptionHandler = StatusReturnExceptionHandler.class)
	public static int listSize(IsolateThread thread, ObjectHandle handle, CIntPointer res) {
		List<?> list = globalHandles.get(handle);
		if (res.isNonNull()) {
			res.write(list.size());
		}
		return Status.STATUS_SUCCESS.getCValue();
	}

	@CEntryPoint(name = Constants.LIB_PREFIX + "list_int_add", exceptionHandler = StatusReturnExceptionHandler.class)
	public static int listIntegerAdd(IsolateThread thread, ObjectHandle handle, int value, CIntPointer res) {
		List<Integer> list = globalHandles.get(handle);
		boolean result = list.add(value);
		if (res.isNonNull()) {
			res.write(result ? 1 : 0);
		}
		return Status.STATUS_SUCCESS.getCValue();
	}

	@CEntryPoint(name = Constants.LIB_PREFIX + "list_double_add", exceptionHandler = StatusReturnExceptionHandler.class)
	public static int listDoubleAdd(IsolateThread thread, ObjectHandle handle, double value, CIntPointer res) {
		List<Double> list = globalHandles.get(handle);
		boolean result = list.add(value);
		if (res.isNonNull()) {
			res.write(result ? 1 : 0);
		}
		return Status.STATUS_SUCCESS.getCValue();
	}

	@CEntryPoint(name = Constants.LIB_PREFIX + "list_int_remove", exceptionHandler = StatusReturnExceptionHandler.class)
	public static int listIntegerRemove(IsolateThread thread, ObjectHandle handle, int value) {
		List<Integer> list = globalHandles.get(handle);
		Integer objectToRemove = value;
		list.remove(objectToRemove);
		return Status.STATUS_SUCCESS.getCValue();
	}

	@CEntryPoint(name = Constants.LIB_PREFIX
			+ "list_double_remove", exceptionHandler = StatusReturnExceptionHandler.class)
	public static int listDoubleRemove(IsolateThread thread, ObjectHandle handle, double value) {
		List<Double> list = globalHandles.get(handle);
		list.remove(value);
		return Status.STATUS_SUCCESS.getCValue();
	}

	@CEntryPoint(name = Constants.LIB_PREFIX
			+ "list_int_contains", exceptionHandler = StatusReturnExceptionHandler.class)
	public static int listIntegerContains(IsolateThread thread, ObjectHandle handle, int value, CIntPointer res) {
		List<Integer> list = globalHandles.get(handle);
		boolean result = list.contains(value);
		if (res.isNonNull()) {
			res.write(result ? 1 : 0);
		}
		return Status.STATUS_SUCCESS.getCValue();
	}

	@CEntryPoint(name = Constants.LIB_PREFIX
			+ "list_double_contains", exceptionHandler = StatusReturnExceptionHandler.class)
	public static int listDoubleContains(IsolateThread thread, ObjectHandle handle, double value, CIntPointer res) {
		List<Double> list = globalHandles.get(handle);
		boolean result = list.contains(value);
		if (res.isNonNull()) {
			res.write(result ? 1 : 0);
		}
		return Status.STATUS_SUCCESS.getCValue();
	}

	@CEntryPoint(name = Constants.LIB_PREFIX + "list_clear", exceptionHandler = StatusReturnExceptionHandler.class)
	public static int clearList(IsolateThread thread, ObjectHandle handle) {
		List<?> list = globalHandles.get(handle);
		list.clear();
		return Status.STATUS_SUCCESS.getCValue();
	}

}
