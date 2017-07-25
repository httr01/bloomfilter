package com.ds.filter.bloom;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Test;

public class BloomFilterTest {
	
	@Test
	public void testHappyPath_8KElements(){
		File inputdataFile = new File(this.getClass().getResource("/bloom-filter-data-10k.txt").getFile());
	    int howManyHashFunctions =3;
		int totalExpectedElements = 8000;
		int bitsPerElement =4;
		BloomFilter<String> bf =  new BloomFilter<String>(  howManyHashFunctions,    totalExpectedElements,   bitsPerElement);
		int actualElementSize=0,elementsAdded  =0;
		 
		try{
			BufferedReader bfreader =  new BufferedReader(new FileReader(inputdataFile));
			String line= bfreader.readLine();
			++actualElementSize;
			while (line  != null ){
				if (!bf.exists(line)) {
					bf.add(line);
					++elementsAdded;
				}
				else System.out.println("False Positive!! City:"+line+" already exists.");
				line= bfreader.readLine();
			}
			bfreader.close();
			/**Lets Assume: The rate of false positive should be less than 0.01 % **/
			Assert.assertTrue( (actualElementSize-elementsAdded )/actualElementSize * 100 < 0.01);
		}catch(Exception ee){
			ee.printStackTrace();
		}
	}
}
