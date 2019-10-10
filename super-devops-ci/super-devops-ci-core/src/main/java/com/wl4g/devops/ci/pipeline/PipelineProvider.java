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
package com.wl4g.devops.ci.pipeline;

import com.wl4g.devops.common.bean.ci.Project;
import com.wl4g.devops.common.bean.ci.TaskHistory;
import com.wl4g.devops.common.bean.ci.dto.TaskResult;
import com.wl4g.devops.support.beans.DelegateAliasPrototypeBean;

/**
 * Based pipeline provider.
 *
 * @author Wangl.sir <983708408@qq.com>
 * @date 2019-05-05 17:17:00
 */
public abstract interface PipelineProvider extends DelegateAliasPrototypeBean {

	void execute() throws Exception;

	void rollback() throws Exception;

	Project getProject();

	TaskResult getTaskResult();

	TaskHistory getTaskHistory();

	String getShaLocal();

	String getShaGit();

	/**
	 * Integrate pipeline type definition.
	 * 
	 * @author Wangl.sir
	 * @version v1.0 2019年8月29日
	 * @since
	 */
	public static abstract class PipelineType {

		/** MAVEN assemble tar provider alias. */
		final public static String MVN_ASSEMBLE_TAR1 = "PipeWithMvnAssembleTar";
		final public static String MVN_ASSEMBLE_TAR2 = "PipeWithMvnAssTar";

		/** Spring boot executable jar provider alias. */
		final public static String SPRING_EXECUTABLE_JAR1 = "PipeWithSpringExecutableJar";
		final public static String SPRING_EXECUTABLE_JAR2 = "PipeWithSpringExecJar";

		/** Docker native provider alias. */
		final public static String DOCKER_NATIVE1 = "PipeWithDockerNative";
		final public static String DOCKER_NATIVE2 = "PipeWithDockerNat";

		/** DJANGO standard provider alias. */
		final public static String DJANGO_STD1 = "PipeWithDjangoStdand";
		final public static String DJANGO_STD2 = "PipeWithDjangoStd";

	}

}