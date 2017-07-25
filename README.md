# bloomfilter
    This is a simple Bloom Filter.
# Example
    You can manipulate following three value to achieve expected rate of false positive.
    The higher these number "rate of false positive" will be higher.
    The more hash functions we have more math it has to do i.e. more CPU.
    
    int howManyHashFunctions =3;
    int totalExpectedElements = 8000;
    int bitsPerElement =4;
## Create Bloom Filter    
    BloomFilter<String> bloomfilter =  new BloomFilter<String>(  howManyHashFunctions,    totalExpectedElements,   bitsPerElement);
    
## Check if an element was added previously.
    
    bloomfilter.exists(data)
    
## Add an element into the filter.
    
    bloomfilter.add(data);
