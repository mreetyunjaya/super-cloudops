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
package com.wl4g.devops.tool.common.crypto.symmetric;

import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * {@link AES256ECBPKCS7}
 *
 * Note: Unlimited Oracle JCE support required.
 *
 * @author Wangl.sir <wanglsir@gmail.com, 983708408@qq.com>
 * @version v1.0 2020年5月27日
 * @since
 */
public class AES256ECBPKCS7 extends SymmetricCryptorSupport {

	static {
		// Let java support pkcs7padding
		Security.addProvider(new BouncyCastleProvider());
	}

	public AES256ECBPKCS7() {
		super(new AlgorithmSpec("AES", "AES/ECB/PKCS7Padding", false, 256, 32, 32));
	}

}