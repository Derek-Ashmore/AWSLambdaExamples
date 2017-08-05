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
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;

@RunWith(MockitoJUnitRunner.class)
public class ListS3AssetsMockitoTest {
	
	private static final String TEST_REGION = "test-region";
	private ListS3Assets listS3Assets;
	private ByteArrayOutputStream outStream; 
	
	@Mock
	private AmazonS3 s3Mock;
	
	@Mock
	private OutputStream outputStreamMock;

	@Before
	public void setUp() throws Exception {
		listS3Assets = new ListS3Assets(s3Mock);
		outStream = new ByteArrayOutputStream();
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

	private void testOutputAssertions(String ...contentAssertions) {
		Arrays.stream(contentAssertions)
			.forEach(item -> Assert.assertTrue("Missing string " + item, outStream.toString().contains(item)));
		Assert.assertTrue("Missing string " + TEST_REGION, outStream.toString().contains(TEST_REGION));
	}

	private void prepareS3Mock(List<Bucket> bucketList) {
		Mockito.when(s3Mock.listBuckets()).thenReturn(bucketList);
		Mockito.when(s3Mock.getRegionName()).thenReturn(TEST_REGION);
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
	
	@Test
	public void testOutputExcepts() throws Exception {
		prepareS3Mock(new ArrayList<Bucket>());
		Mockito.doThrow(new IOException("crap")).when(outputStreamMock).write(ArgumentMatchers.any(byte[].class));
		try {
			listS3Assets.list(outputStreamMock);
			Assert.fail();
		}
		catch (ContextedRuntimeException e) {
			Assert.assertTrue(e.getMessage().contains("Error writing S3 assert list"));
			Assert.assertTrue(e.getMessage().contains("outStream"));
			Assert.assertTrue(e.getCause().getMessage().contains("crap"));
		}
	}

}
