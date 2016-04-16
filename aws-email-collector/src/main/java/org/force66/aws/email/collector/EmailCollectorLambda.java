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

import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.apache.log4j.Logger;
import org.force66.aws.email.collector.model.EmailCollectionRequest;
import org.force66.aws.email.collector.model.EmailCollectionResponse;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class EmailCollectorLambda implements RequestHandler<EmailCollectionRequest, EmailCollectionResponse> {
	private static final Logger LOGGER = Logger.getLogger(EmailCollectorLambda.class);

	@Override
	public EmailCollectionResponse handleRequest(EmailCollectionRequest input, Context context) {
		try {return new EmailCollector().collect(input);}
		catch (Exception e) {
			ContextedRuntimeException ce = new ContextedRuntimeException(e)
					.addContextValue("firstName", input.getFirstName())
					.addContextValue("lastName", input.getLastName())
					.addContextValue("emailAddress", input.getEmailAddress());
			LOGGER.error("Error in Lambda Email Collector", ce);
			throw ce;
		}
	}


}
