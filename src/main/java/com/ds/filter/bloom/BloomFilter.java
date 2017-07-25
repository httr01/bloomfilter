package com.ds.filter.bloom;
 
import java.util.BitSet;
import java.util.Random;

/** Very basic implementation of Bloom filter.
 * Things to improve further.
 * 1. Save it on disk to make it distributed.
 * 2. add additional constructor to take in false positive rate and calculate other parameters.
 * */
public class BloomFilter<T> {
	
	int howManyHashFunctions =1;
	/**How many elements are expected at max*/
	int totalExpectedElements ; 
	/**How many elements in filter at any point in time*/
	int totalCurrentElements ;
	/**How many bits per  element needs to be set**/
	int bitsPerElement ;
	BitSet dataDictionaryBitSet;
	
  public BloomFilter(int howManyHashFunctions,  int totalExpectedElements, int bitsPerElement){
		this.howManyHashFunctions =howManyHashFunctions;
		this.totalExpectedElements =totalExpectedElements;
		this.bitsPerElement=bitsPerElement;
		dataDictionaryBitSet = new BitSet(bitsPerElement * totalExpectedElements);
	}
  
  /** Adding data in bloom filter. This will remember that this data was added.
   * @param data : The data which needs to be added in bloom filter**/
  
	public void add(T data) {
		int[] hashCodes = generateMultipleHash(data );
		for (int index =0 ; index<hashCodes.length;++index) dataDictionaryBitSet.set(hashCodes[index]);
		++totalCurrentElements;
	}
	/**Find out if given data was previously added into filter. 
	 * @param data : The data which needs to be checked if it was previously added.
	 * **/
	public boolean exists(T data) {
 		int[] hashCodes = generateMultipleHash(data   );
		boolean exists = false;
		for (int index =0 ; index<hashCodes.length;++index) {
			if (dataDictionaryBitSet.get(hashCodes[index])) exists = true; 
			else {
				exists = false; 
				break;
			}
		}
		return exists;
	}
	
	/** Return how many element it has remembered. **/
	public int size(){
		return totalCurrentElements;
	}
	
    /** generate multiple hash codes
     * @param data the data which needs to be
     * @return array with positive hashes with hash values from 0 to size of bit set array  size
     */
    public  int[] generateMultipleHash(T data ) {
        int[] positions = new int[howManyHashFunctions];
        Random r = new Random(data.hashCode());
        for (int i = 0; i < howManyHashFunctions; i++) {
            positions[i] = r.nextInt(dataDictionaryBitSet.size());
        }
        return positions;
    }

}
