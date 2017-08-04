package org.force66.aws.s3;

import org.junit.Test;

import com.amazonaws.services.s3.AmazonS3ClientBuilder;

/**
 * Integration test for ListS3Assets.  Assumes properly configured AWS credentials.
 * @author D. Ashmore
 * @see <a href="http://docs.aws.amazon.com/cli/latest/userguide/cli-config-files.html">AWS Documentation</a>
 *
 */
public class ListS3AssetsTestIntegration {

	@Test
	public void testBasic() {
		ListS3Assets listAssets = new ListS3Assets(AmazonS3ClientBuilder.defaultClient());
		listAssets.list(System.out);
	}

}
