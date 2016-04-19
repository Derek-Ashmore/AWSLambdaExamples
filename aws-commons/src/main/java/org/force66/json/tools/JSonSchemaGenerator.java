/*
 * This software is licensed under the Apache License, Version 2.0
 * (the "License") agreement; you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.force66.json.tools;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.Validate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.customProperties.HyperSchemaFactoryWrapper;

public class JSonSchemaGenerator {

	public static void main(String[] args) throws Exception {
		Validate.isTrue(args != null && args.length >= 2, "Usage arguments: className fileTarget");
		
		String className = args[0];
		String outputFileName = args[1];
		
		HyperSchemaFactoryWrapper schemaVisitor = new HyperSchemaFactoryWrapper();
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.acceptJsonFormatVisitor(ClassUtils.getClass(className), schemaVisitor);
	    JsonSchema jsonSchema = schemaVisitor.finalSchema();
	    
	    File outputFile = new File(outputFileName);
	    FileUtils.write(outputFile, mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonSchema));
	}

}
