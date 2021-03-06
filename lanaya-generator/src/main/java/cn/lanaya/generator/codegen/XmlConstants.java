/**
 *    Copyright 2006-2017 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package cn.lanaya.generator.codegen;

/**
 * Constants for iBatis/MyBatis XML IDs.
 * 
 * @author Jeff Butler
 */
public interface XmlConstants {
	String XML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>";

	String MYBATIS3_MAPPER_SYSTEM_ID = "http://mybatis.org/dtd/mybatis-3-mapper.dtd";

	String MYBATIS3_MAPPER_PUBLIC_ID = "-//mybatis.org//DTD Mapper 3.0//EN";

	String MYBATIS3_MAPPER_CONFIG_SYSTEM_ID = "http://mybatis.org/dtd/mybatis-3-config.dtd";

	String MYBATIS3_MAPPER_CONFIG_PUBLIC_ID = "-//mybatis.org//DTD Config 3.0//EN";

	String IBATOR_CONFIG_SYSTEM_ID = "http://ibatis.apache.org/dtd/ibator-config_1_0.dtd";

	String IBATOR_CONFIG_PUBLIC_ID = "-//Apache Software Foundation//DTD Apache iBATIS Ibator Configuration 1.0//EN";

	String MYBATIS_GENERATOR_CONFIG_SYSTEM_ID = "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd";

	String MYBATIS_GENERATOR_CONFIG_PUBLIC_ID = "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN";
}
