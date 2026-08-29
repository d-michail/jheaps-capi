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

import java.util.Comparator;

import org.jheaps.capi.JHeapsContext.LongComparatorFunctionPointer;

/**
 * A {@link Comparator} backed by a native function pointer. Kept as a named class rather than a
 * lambda since native-image does not support capturing a Word value (such as a function pointer)
 * inside a lambda.
 */
class FunctionPointerComparator implements Comparator<Long> {

	private final LongComparatorFunctionPointer functionPointer;

	FunctionPointerComparator(LongComparatorFunctionPointer functionPointer) {
		this.functionPointer = functionPointer;
	}

	@Override
	public int compare(Long a, Long b) {
		return functionPointer.invoke(a, b);
	}

}
