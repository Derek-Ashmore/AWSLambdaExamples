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
package org.force66.aws.email.collector.model;

import org.force66.beantester.BeanTester;
import org.junit.Before;
import org.junit.Test;

public class ModelTypeTest {
	
	private BeanTester beanTester;

	@Before
	public void setUp() throws Exception {
		beanTester = new BeanTester();
	}

	@Test
	public void test() throws Exception {
		beanTester.testBean(EmailCollectionRequest.class);
		beanTester.testBean(EmailCollectionResponse.class);
		beanTester.testBean(FieldInputError.class);
	}

}
