package org.force66.aws.s3;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;

import mockit.Expectations;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
public class ListS3AssetsJMockitTest {
	
	private static final String TEST_REGION = "test-region";
	private ByteArrayOutputStream outStream; 
	
	private ListS3Assets listS3Assets;
	
	@Mocked AmazonS3 s3Mock;
	//@Mocked OutputStream outputStreamMock;
	
	@Before
	public void setUp() throws Exception {
		outStream = new ByteArrayOutputStream();
		listS3Assets = new ListS3Assets(s3Mock);
	}

	@Test(expected = NullPointerException.class)
	public void testNullConstructor() {
		new ListS3Assets(null);
	}
	
	@Test
	public void testConstructor() throws Exception {
		Assert.assertEquals(s3Mock, FieldUtils.readField(listS3Assets, "s3", true));
	}
	
	@Test(expected = NullPointerException.class)
	public void testNullOutputStream() throws Exception {
		listS3Assets.list(null);
	}
	
	@Test
	public void testNoBuckets() throws Exception {
		prepareS3Mock(new ArrayList<Bucket>());
		listS3Assets.list(outStream);
		
		testOutputAssertions("You don't have any S3 buckets in region");
	}
	
	@Test
	public void testHasBuckets() throws Exception {
		List<Bucket> bucketList = new ArrayList<Bucket>();
		bucketList.add(new Bucket("Thing1"));
		bucketList.add(new Bucket("Thing2"));
		
		prepareS3Mock(bucketList);
		listS3Assets.list(outStream);
		
		testOutputAssertions("Your Amazon S3 buckets in region", "Thing1", "Thing2", System.lineSeparator());
	}
	
	//@Test   Not currently possible with JMockIt -- see https://groups.google.com/forum/#!topic/jmockit-users/iBhKe-N08_Q
	public void testOutputExcepts() throws Exception {
		prepareS3Mock(new ArrayList<Bucket>());
		new Expectations() {{
			//outputStreamMock.write( (byte[]) any); result = new IOException("crap");
		}};
		
		try {
			//listS3Assets.list(outputStreamMock);
			Assert.fail();
		}
		catch (ContextedRuntimeException e) {
			Assert.assertTrue(e.getMessage().contains("Error writing S3 assert list"));
			Assert.assertTrue(e.getMessage().contains("outStream"));
			Assert.assertTrue(e.getCause().getMessage().contains("crap"));
		}
	}
	
	private void prepareS3Mock(List<Bucket> bucketList) {
		new Expectations() {{
			s3Mock.listBuckets(); result = bucketList;
			s3Mock.getRegionName(); result = TEST_REGION;
		}};
	}
	
	private void testOutputAssertions(String ...contentAssertions) {
		Arrays.stream(contentAssertions)
			.forEach(item -> Assert.assertTrue("Missing string " + item, outStream.toString().contains(item)));
		Assert.assertTrue("Missing string " + TEST_REGION, outStream.toString().contains(TEST_REGION));
	}

}
