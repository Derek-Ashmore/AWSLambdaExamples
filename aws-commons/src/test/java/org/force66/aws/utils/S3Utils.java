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

import java.io.IOException;
import java.util.Properties;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

public class S3Utils {
	
	public static Properties loadProperties(String bucketName, String key) throws IOException  {
		Properties props = new Properties();
		AmazonS3 s3Client = new AmazonS3Client(new ProfileCredentialsProvider());
		S3Object s3object = s3Client.getObject(new GetObjectRequest(bucketName, key));
		props.load(s3object.getObjectContent());
		return props;
	}

}
