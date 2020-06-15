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
package org.jgrapht.capi;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.constant.CEnum;
import org.graalvm.nativeimage.c.constant.CEnumLookup;
import org.graalvm.nativeimage.c.constant.CEnumValue;
import org.graalvm.nativeimage.c.function.CFunctionPointer;
import org.graalvm.nativeimage.c.function.InvokeCFunctionPointer;
import org.graalvm.nativeimage.c.type.CCharPointer;

@CContext(JHeapsDirectives.class)
public class JHeapsContext {

	@CEnum("status_t")
	public enum Status {

		// @formatter:off
		STATUS_SUCCESS, 
		STATUS_ERROR,
		STATUS_ILLEGAL_ARGUMENT,
		STATUS_UNSUPPORTED_OPERATION,
		STATUS_INDEX_OUT_OF_BOUNDS,
		STATUS_NO_SUCH_ELEMENT,
		STATUS_NULL_POINTER,
		STATUS_CLASS_CAST,
		STATUS_IO_ERROR,
		;
		// @formatter:on

		@CEnumValue
		public native int getCValue();

		@CEnumLookup
		public static native Status fromCValue(int value);

	}

	@CEnum("heap_type_t")
	public enum HeapType {

		// @formatter:off
	    HEAP_TYPE_PAIRING,
	    HEAP_TYPE_FIBONACCI,
		;
		// @formatter:on

		@CEnumValue
		public native int toCEnum();

		@CEnumLookup
		public static native HeapType toJavaEnum(int value);

	}

	/* Import of a C function pointer type. */
	public interface NotifyAttributeFunctionPointer extends CFunctionPointer {

		/*
		 * Invocation of the function pointer. A call to the function is replaced with
		 * an indirect call of the function pointer.
		 */
		@InvokeCFunctionPointer
		void invoke(int element, CCharPointer key, CCharPointer value);
	}

	/*
	 * Function pointer for importers which give the user control on how to convert
	 * the input identifier of a vertex or edge into a long integer.
	 */
	public interface ImportIdFunctionPointer extends CFunctionPointer {

		@InvokeCFunctionPointer
		int invoke(CCharPointer id);

	}

}
