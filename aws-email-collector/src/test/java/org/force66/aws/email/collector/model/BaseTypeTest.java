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

import org.junit.Assert;
import org.junit.Test;

public class BaseTypeTest {

	private static final String TEST_VALUE = "Derek";

	@Test
	public void test() {
		EmailCollectionRequest type = new EmailCollectionRequest();
		type.setFirstName(TEST_VALUE);
		
		Assert.assertTrue(type.toString().contains(TEST_VALUE));
		
		Assert.assertTrue(type.hashCode() != 0);		
		Assert.assertEquals(type.hashCode(), type.hashCode());
		
		EmailCollectionRequest type2 = new EmailCollectionRequest();
		type2.setFirstName(TEST_VALUE);
		Assert.assertEquals(type, type2);
	}

}
