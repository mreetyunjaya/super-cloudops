/*
 * Copyright 2017 ~ 2025 the original author or authors. <wanglsir@gmail.com, 983708408@qq.com>
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
package com.wl4g.devops.coss.common.internal;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public abstract class CossRequest {

	public static final CossRequest NOOP = new CossRequest() {
	};

	// This flag is used to enable/disable INFO and WARNING logs for requests
	// We enable INFO and WARNING logs by default.
	private boolean logEnabled = true;

	private Map<String, String> parameters = new LinkedHashMap<String, String>();
	private Map<String, String> headers = new LinkedHashMap<String, String>();

	/**
	 * Additional header names
	 */
	private Set<String> additionalHeaderNames = new HashSet<String>();

	public Map<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}

	public void addParameter(String key, String value) {
		this.parameters.put(key, value);
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public void addHeader(String key, String value) {
		this.headers.put(key, value);
	}

	public Set<String> getAdditionalHeaderNames() {
		return additionalHeaderNames;
	}

	public void setAdditionalHeaderNames(Set<String> additionalHeaderNames) {
		this.additionalHeaderNames = additionalHeaderNames;
	}

	public void addAdditionalHeaderName(String name) {
		this.additionalHeaderNames.add(name);
	}

	public boolean isLogEnabled() {
		return logEnabled;
	}

	public void setLogEnabled(boolean logEnabled) {
		this.logEnabled = logEnabled;
	}

}