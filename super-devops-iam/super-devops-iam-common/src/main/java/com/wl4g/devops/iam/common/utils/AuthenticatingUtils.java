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
package com.wl4g.devops.iam.common.utils;

import java.net.URI;
import java.net.URISyntaxException;

import static org.apache.commons.lang3.StringUtils.*;
import static com.wl4g.devops.common.constants.IAMDevOpsConstants.*;

/**
 * IAM authenticating security tools.
 *
 * @author Wangl.sir <983708408@qq.com>
 * @version v1.0 2019年5月21日
 * @since
 */
public abstract class AuthenticatingUtils extends IamSecurityHolder {

	/**
	 * Default authentication status.
	 */
	final public static String SESSION_STATUS_AUTHC = "Authenticated";

	/**
	 * Default Unauthenticated status.
	 */
	final public static String SESSION_STATUS_UNAUTHC = "Unauthenticated";

	/**
	 * Current session authentication status.
	 *
	 * @return
	 */
	public static String sessionStatus() {
		return getSubject().isAuthenticated() ? SESSION_STATUS_AUTHC : SESSION_STATUS_UNAUTHC;
	}

	/**
	 * Get the URI address of the authenticator interface on the client or
	 * server side.</br>
	 * e.g.
	 *
	 * <pre>
	 *  http://iam.xx.com/iam-server/xx/list?id=1  =>  http://iam.xx.com/iam-server/authenticator?id=1
	 *  http://iam.xx.com/xx/list?id=1             =>  http://iam.xx.com/xx/authenticator?id=1
	 *  http://iam.xx.com/xx/list/?id=1            =>  http://iam.xx.com/xx/authenticator?id=1
	 *  http://iam.xx.com:8080/xx/list/?id=1       =>  http://iam.xx.com:8080/xx/authenticator?id=1
	 *  /view/index.html					       =>  /view/index.html
	 * </pre>
	 * <p>
	 * Implementing the IAM-CAS protocol: When successful login, you must
	 * redirect to the back-end server URI of IAM-CAS-Client. (Note: URI of
	 * front-end pages can not be used directly).
	 *
	 * @param url
	 * @return
	 * @see {@link com.wl4g.devops.iam.client.filter.AuthenticatorAuthenticationFilter}
	 * @see {@link com.wl4g.devops.iam.filter.AuthenticatorAuthenticationFilter#determineSuccessUrl()}
	 */
	public static String correctAuthenticaitorURI(String url) {
		if (isBlank(url)) {
			return EMPTY;
		}
		try {
			URI _uri = new URI(url);
			// e.g. /view/index.html => /view/index.html
			if (isAnyBlank(_uri.getScheme(), _uri.getHost())) {
				return url;
			}

			if (!endsWith(_uri.getPath(), URI_AUTHENTICATOR)) {
				String portPart = (_uri.getPort() == 80 || _uri.getPort() == 443 || _uri.getPort() < 0) ? EMPTY
						: (":" + _uri.getPort());
				String queryPart = isBlank(_uri.getQuery()) ? EMPTY : ("?" + _uri.getQuery());
				String contextPath = _uri.getPath();
				String[] pathPart = split(_uri.getPath(), "/");
				if (pathPart.length > 1) {
					contextPath = "/" + pathPart[0];
				}
				return new StringBuffer(_uri.getScheme()).append("://").append(_uri.getHost()).append(portPart)
						.append(contextPath).append(URI_AUTHENTICATOR).append(queryPart).toString();
			}
			return url;
		} catch (URISyntaxException e) {
			throw new IllegalStateException("Can't to obtain a redirect authenticaitor URL.", e);
		}
	}

}