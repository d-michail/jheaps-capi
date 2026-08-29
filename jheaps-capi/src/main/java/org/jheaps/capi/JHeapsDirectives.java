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
package org.jheaps.capi;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import org.graalvm.nativeimage.c.CContext;

public class JHeapsDirectives implements CContext.Directives {

	public JHeapsDirectives() {
	}

	@Override
	public boolean isInConfiguration() {
		return true;
	}

	@Override
	public List<String> getHeaderFiles() {

		/*
		 * The header file with the C declarations that are imported. The CMake build
		 * copies this module's src/ tree into the build directory and runs
		 * native-image from there, so the header is resolved relative to the current
		 * working directory instead of GraalVM's internal (and no longer accessible
		 * outside the module) ProjectHeaderFile helper.
		 */
		Path header = Paths.get("src", "main", "native", "jheaps_capi_types.h").toAbsolutePath();
		return Collections.singletonList("\"" + header.toString().replace('\\', '/') + "\"");
	}

}