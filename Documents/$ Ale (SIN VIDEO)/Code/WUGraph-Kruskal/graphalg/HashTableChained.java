/* HashTableChained.java */

package graphalg;

import list.*;
import java.lang.Math;
import dict.*;



/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

  /**
   *  Place any data fields here.
   **/
  protected int size;
  protected int bucket;
  private DList[] listArray;


  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/

  public HashTableChained(int sizeEstimate) {
    // Your solution here.
//Load factor of 0.5 and 1. Look for a prime number bucket

    size = sizeEstimate;

    int min = (int) Math.floor(size/1);
    int max = (int) Math.floor(size/0.5);

    //System.out.println(min);
    //System.out.println(max);


    bucket = min;

    for (int i = min; i <= max; i++){

      if (isPrime(i)) {
        bucket = i;
        break;
      }

    
    }

//Initialize array

    listArray = new DList[bucket];

    for (int i = 0; i < bucket; i++){
      listArray[i] = new DList();
    }

  }

  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
    // Your solution here.
    bucket = 101;
    listArray = new DList[bucket];

    for (int i = 0; i < bucket; i++){
      listArray[i] = new DList();
    }

  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  int compFunction(int code) {
    // Replace the following line with your solution.

    int compHash = Math.abs(((3*code + 5) % 16908799)) % bucket;
    return compHash;
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
    // Replace the following line with your solution.
    return size;
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    // Replace the following line with your solution.
    if (size == 0){
      return true;
    } else {
      return false;
    }
    
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
    // Replace the following line with your solution.
    Entry e = new Entry();
    e.key = key;

    e.value = value;

    int hash = key.hashCode();
//System.out.println(hash);

    int compHash = compFunction(hash);
//System.out.println(compHash);

    DList l = listArray[compHash];

    l.insertBack(e);

    size++;

    return e;
  }

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key) {
    // Replace the following line with your solution.
    int hash = key.hashCode();
    int compHash = compFunction(hash);
    DList l = listArray[compHash];
    ListNode n = l.front();

    try {

      for (int i = 0; i < l.length(); i++){

        Entry e = (Entry) n.item();

//System.out.println(l.length());
//System.out.println(e.key());
//System.out.println(key);

//System.out.println(l.isEmpty());
        
        if (!l.isEmpty() && e.key().equals(key)){

//System.out.println("A1");

          return e;

        } else {

//System.out.println("A2");

          n = n.next();

        }
        
      }
      

    } catch (InvalidNodeException i){
      System.out.println("QUE ESTAS EN EL CATCH CANIO");
      return null;
    }

    return null;

    
  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
    // Replace the following line with your solution.

    int hash = key.hashCode();
    int compHash = compFunction(hash);
    DList l = listArray[compHash];
    ListNode n = l.front();

    try {

      for (int i = 0; i < l.length(); i++){


        Entry e = (Entry) n.item();

//System.out.println(l.length());
//System.out.println(e.key());
//System.out.println(key);

//System.out.println(l.isEmpty());


        
        if (!l.isEmpty() && e.key().equals(key)){

//System.out.println("A1");

          n.remove();
          size--;

        } else {

//System.out.println("A2");

          n = n.next();

        }
        
      }

      

    } catch (InvalidNodeException i){
      System.out.println("QUE ESTAS EN EL CATCH CANIO");
      return null;
    }


    return null;
  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
    // Your solution here.

    try{
      for(DList l : listArray ){

        if (l.length() == 0){
          continue;
        }

        ListNode n = l.front();
        ListNode nn;

        while (l.length() > 0){

          if(n.next().isValidNode()){
            nn = n.next();
            n.remove();
            size--;
            n = nn;

          } else {
            n.remove();
            size--;
          }

        }

      }
    } catch (InvalidNodeException i){}
  }

  public boolean isPrime(int number){

    for (int i = 2; i < number; i++){


      if (number % i == 0 && i != number){
          return false;
      }
    }

      return true;

  }

  public int numberOfCollisions(){

    int collisions = 0;

    for(DList l : listArray ){

      if (l.length() == 0){
      } else if (l.length() > 1) {
        collisions = collisions + l.length() -1;
      }

    }

    return collisions;

  }

  public static void main(String[] args) {

    HashTableChained h2 = new HashTableChained();
    //System.out.println(h2.size());
    //System.out.println(h2.bucket);


    h2.insert( (Object) 102, (Object) 2);
    //System.out.println(h2.find(102).value());
    h2.insert( (Object) 10254, (Object) 22);
    h2.insert( (Object) 10254, (Object) 22);
    h2.insert( (Object) 10254, (Object) 23);
    h2.insert( (Object) 102, (Object) 2);
    h2.insert( (Object) 102, (Object) 24);
    System.out.println(h2.numberOfCollisions());
    //System.out.println(h2.find(10254).value());
    //h2.insert( (Object) 102, (Object) 2);
    //System.out.println(h2.find(102).value());
    //h2.remove(10254);
    //System.out.println(h2.find(10254));
    //h2.remove(102);
    //System.out.println(h2.find(102));
    //h2.makeEmpty();
    //System.out.println(h2.isEmpty());
    //System.out.println(h2.find(10254));
    //System.out.println(h2.find(102));
    System.out.println(h2.numberOfCollisions());




    //System.out.println(h2.size());
    //h2.insert( (Object) 102, (Object) 5);
    //System.out.println(h2.size());

    //System.out.println(h2.find(102));
    //System.out.println(h2.find(102).key());




/*    

    HashTableChained h1 = new HashTableChained(12326);
    //System.out.println(h1.bucket);
    System.out.println(h1.size());
    h1.insert( (Object) 1, (Object) 2);
    System.out.println(h1.size());


    HashTableChained h11 = new HashTableChained(126);
    System.out.println(h11.size());
    System.out.println(h11.bucket);

    HashTableChained h12 = new HashTableChained(1232546);
    System.out.println(h12.size());
    System.out.println(h12.bucket);



*/

  }

}
