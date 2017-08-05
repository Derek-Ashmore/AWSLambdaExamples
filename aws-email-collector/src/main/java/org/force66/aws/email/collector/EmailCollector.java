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
package org.force66.aws.email.collector;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.force66.aws.email.collector.model.EmailCollectionRequest;
import org.force66.aws.email.collector.model.EmailCollectionResponse;

public class EmailCollector {
	static final Logger log = LoggerFactory.getLogger(EmailCollector.class);
	
	public EmailCollectionResponse collect(EmailCollectionRequest emailCollectionRequest) {
		Validate.notNull(emailCollectionRequest, "Null emailCollectionRequest not allowed.");
		log.info("email collection request=" + emailCollectionRequest);
		
		// TODO Put real email collection logic here
		
		return new EmailCollectionResponse(true);

	}


}
