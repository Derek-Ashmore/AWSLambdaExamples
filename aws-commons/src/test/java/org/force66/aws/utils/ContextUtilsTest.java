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
package org.force66.aws.utils;

import org.junit.Assert;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.ClientContext;
import com.amazonaws.services.lambda.runtime.CognitoIdentity;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

public class ContextUtilsTest {

	private static final String TEST_AWS_REQUEST_ID = "myAwsRequestId"; 

	@Test
	public void test() throws Exception {
		Assert.assertNull(ContextUtils.toString(null));
		
		Context context = new MyContext();
		System.out.println(ContextUtils.toString(context));
		Assert.assertTrue(ContextUtils.toString(context).contains(TEST_AWS_REQUEST_ID));
	}
	
	public static class MyContext implements Context {
		
		private String awsRequestId = TEST_AWS_REQUEST_ID;

		public String getAwsRequestId() {
			// TODO Auto-generated method stub
			return awsRequestId;
		}

		public String getLogGroupName() {
			// TODO Auto-generated method stub
			return null;
		}

		public String getLogStreamName() {
			// TODO Auto-generated method stub
			return null;
		}

		public String getFunctionName() {
			// TODO Auto-generated method stub
			return null;
		}

		public String getFunctionVersion() {
			// TODO Auto-generated method stub
			return null;
		}

		public String getInvokedFunctionArn() {
			// TODO Auto-generated method stub
			return null;
		}

		public CognitoIdentity getIdentity() {
			// TODO Auto-generated method stub
			return null;
		}

		public ClientContext getClientContext() {
			// TODO Auto-generated method stub
			return null;
		}

		public int getRemainingTimeInMillis() {
			// TODO Auto-generated method stub
			return 0;
		}

		public int getMemoryLimitInMB() {
			// TODO Auto-generated method stub
			return 0;
		}

		public LambdaLogger getLogger() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}

}
