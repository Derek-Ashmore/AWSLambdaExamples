package org.force66.aws.s3;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.exception.ContextedRuntimeException;

import com.amazonaws.services.s3.AmazonS3;

/**
 * Example AWS sdk S3 program that will list S3 assets
 * @author D. Ashmore
 *
 */
public class ListS3Assets {
	
	private AmazonS3 s3 = null;
	
	public ListS3Assets(AmazonS3 s3Client) {
		Validate.notNull(s3Client, "Null AmazonS3 s3Client not allowed");
		s3 = s3Client;
	}
	
	public void list(OutputStream outStream) {
		Validate.notNull(outStream, "Null output stream not allowed.");
		List<String> lineList = new ArrayList<String>();
		
		s3.listBuckets().forEach( bucket->lineList.add(bucket.getName()) );
		if (lineList.isEmpty()) {
			lineList.add( String.format("You don't have any S3 buckets in region %s.", s3.getRegionName()) );
		} else {
			lineList.add(0, String.format("Your Amazon S3 buckets in region %s are:", s3.getRegionName()) );
		}
		
		try {
			IOUtils.writeLines(lineList, System.lineSeparator(), outStream, StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new ContextedRuntimeException("Error writing S3 assert list", e)
				.addContextValue("outStream", outStream);
		}
	}

}
