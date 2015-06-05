import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

@SuppressWarnings("unused")
public class Pruebas extends TestCase {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StringBuilder builder = new StringBuilder(args[0]);
		ArrayList<String> strings = new ArrayList<String>();

		// permutation("","abc");
		// reverseInteger(-451);
		// intToBinaryString(-115);
		// numberOfOnes(-115);
		// fizzBuzz(50);
		// primeNumbers(49);
		// combinations("abcd", 4);
		// factors(756);
		// System.out.println(largestNumberSubset(250001, 2));
		// String splitTest="hola";
		// for(String t : splitTest.split(":")) System.out.println(t);
		// String ssad="[IamaconditionRule(ON)?[AndIAmNot(OFF)]]";
		// System.out.println(ssad.substring(ssad.indexOf("IamaconditionRule")));
		// System.out.println(numberTree(3,"",""));
		// QuickSort(new int[] { 12, 3, 4, 7, 33, 2, 44,8, 9, 22 }, 0, 9);
		// InsertSort(new int[] { 12, 3, 4, 7, 33, 2, 44, 8, 9, 22 });
		// MergeSort(new int[] { 12, 3, 4, 7, 33, 2, 44, 8, 9, 22 });
		// System.out.println(Power(2,4));
		// System.out.println(NPower2(5));
		// try {
		// System.out.println(factorial(5));
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		// System.out.println(isIsomorphic("paper","title"));
		// System.out.println(sumAPoweredB(9, 4000));

		// System.out.println(fizzBuzz(30));
		// System.out.println(smallestSumMN(1000,87));
		// System.out.println(minCoinCount(157, 3, 7, 10));
		// System.out.println(isDivisible9Recursive(153));
		System.out.println(isDivisible9Sum(936));
	}

	private static String numberTree(int num, String previous, String align) {

		if (num <= 0)
			return "";
		else if (num == 1) {
			if (!previous.isEmpty())
				return align + num + previous + num;
			else
				return align + num + "";
		}

		String currentRow = previous.isEmpty() ? String.valueOf(num) : num + previous + num;
		return align + currentRow + "\n" + numberTree(num - 1, currentRow, align + " ");

	}

	private static void permutation(String prefix, String string) {
		int size = string.length();
		if (string.isEmpty())
			System.out.println(prefix);
		else {
			for (int i = 0; i < size; i++)
				permutation(prefix + string.charAt(i), string.substring(0, i) + string.substring(i + 1));
		}
	}

	private static int reverseInteger(int num) {
		int auxNum = 0;
		while (num != 0) {
			int lastnum = num % 10;
			auxNum *= 10;
			auxNum += lastnum;
			num = num / 10;
		}
		System.out.println(auxNum);
		return auxNum;
	}

	private static String intToBinaryString(int num) {
		StringBuilder builder = new StringBuilder();

		while (num != 0) {
			builder.insert(0, Math.abs(num % 2));
			num = num >>> 1;
		}
		System.out.println(builder.toString());
		return builder.toString();
	}

	private static int numberOfOnes(int num) {
		int ones = 0;
		// if(num<0) num=~num;
		while (num != 0) {
			if (num % 2 != 0)
				ones++;
			num = num >>> 1;
		}
		System.out.println(ones);
		return ones;
	}

	private static String fizzBuzz(int n) {
		StringBuilder result = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			if (i % 3 == 0)
				result.append("Fizz");
			if (i % 5 == 0)
				result.append("Buzz");

			if (i % 5 != 0 && i % 3 != 0)
				result.append(i);

			if (i < n)
				result.append(", ");
		}
		System.out.println(result.toString());
		return result.toString();
	}

	private static String primeNumbers(int num) {
		StringBuilder result = new StringBuilder();
		for (int i = 2; i <= num; i++) {
			boolean prime = true;
			for (int j = 2; j <= Math.sqrt(i); j++) {
				if (i % j == 0) {
					prime = false;
					break;
				}
			}
			if (prime)
				result.append(i).append(", ");
		}
		System.out.println(result.toString());
		return result.toString();
	}

	private static void combinations(String string, int N) {
		if (N > 0 && N <= string.length()) {
			for (int i = 0; i <= string.length() - N; i++) {
				for (int j = i + 1; j + (N - 1) <= string.length(); j++)
					System.out.println(string.charAt(i) + string.substring(j, j + (N - 1)));
			}
		}

	}

	private static boolean isPrime(int num) {
		boolean prime = true;
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0) {
				prime = false;
				break;
			}
		}
		return prime;
	}

	private static void factors(int num) {
		if (num > 0) {
			StringBuilder builder = new StringBuilder();
			while (num > 1) {
				int divisor = num;
				if (num % 2 == 0) {
					divisor = 2;
				} else if (!isPrime(num)) {
					for (int i = 2; i <= Math.sqrt(num); i++) {
						if (num % i == 0) {
							divisor = i;
							break;
						}
					}
				}
				builder.append(divisor + ",");
				num /= divisor;
			}
			System.out.println(builder.toString());
		}
	}

	private static int largestNumberSubset(int number, int digitsToRemove) {
		int numberDigits = (int) Math.log10(number);
		if (digitsToRemove == 0)
			return number;
		if (digitsToRemove >= numberDigits)
			return 0;

		int result = 0, currentDigit = 0, largestDigit = 0, initIndex = numberDigits;

		while (digitsToRemove > 0) {
			for (int i = initIndex; i >= 0; i--) {
				if (i >= digitsToRemove - 1) {
					int digit = getDigit(number, i);
					if (digit > largestDigit) {
						initIndex = i - 1;
						largestDigit = digit;
					}
				}

			}
			result += largestDigit * Math.pow(10, digitsToRemove - 1);
			largestDigit = 0;
			digitsToRemove--;
		}
		return result;

	}

	private static int getDigit(int num, int index) {
		int numLength = (int) Math.log10(num) + 1;
		if (index > numLength)
			return -1;
		else {
			int i = 0, numAux = num;
			while (i <= index) {
				if (i == index)
					return numAux % 10;
				else {
					numAux /= 10;
					i++;
				}
			}
		}
		return -1;
	}

	public static void printArray(int[] A) {
		StringBuilder builder = new StringBuilder();
		builder.append("[");

		for (int i = 0; i < A.length; i++) {
			builder.append(A[i]);
			if (i < A.length - 1)
				builder.append(",");
		}

		builder.append("]");

		System.out.println(builder.toString());
	}

	public static void QuickSort(int[] A, int min, int max) {
		if (min < max) {
			int partition = QSPartition(A, min, max);

			QuickSort(A, min, partition - 1);
			QuickSort(A, partition + 1, max);
		}
		printArray(A);
	}

	private static int QSPartition(int[] A, int min, int max) {
		int pivotIndex = (min + max) / 2;

		swap(A, pivotIndex, max);

		int pivot = A[max];

		int i = min, j = max - 1;
		while (i <= j) {
			while (A[i] < pivot && i < max)
				i++;
			while (A[j] > pivot && j > 0)
				j--;

			if (i <= j) {
				swap(A, i, j);
				i++;
				j--;
			}
		}

		A[max] = A[i];
		A[i] = pivot;

		return i;
	}

	private static void swap(int[] A, int i, int j) {
		int aux = A[i];
		A[i] = A[j];
		A[j] = aux;
	}

	public static void InsertSort(int[] A) {
		printArray(A);

		for (int i = 1; i < A.length; i++) {
			int j = i;
			while (j > 0 && A[j] < A[j - 1]) {
				swap(A, j, j - 1);
				j--;
			}
		}

		printArray(A);
	}

	public static int Power(int base, int exp) {
		if (exp == 0)
			return 1;
		else
			return base * Power(base, exp - 1);
	}

	public static int NPower2(int n) {
		return (2 << n) - 2;
		// int suma=0;
		// int power=1;
		// for(int i=1; i<=n; i++)
		// {
		// power*=2;
		// suma+=power;
		// }
		//
		// return suma;
	}

	public static int factorial(int n) throws Exception {
		if (n < 0)
			throw new Exception("Número fuera de rango");
		else if (n < 2)
			return 1;
		else
			return n * factorial(n - 1);
	}

	public static boolean isIsomorphic(String s, String t) {
		if (s == null || t == null)
			return false;

		if (s.length() != t.length())
			return false;

		s = s.toLowerCase();
		t = t.toLowerCase();

		StringBuilder tBuilder = new StringBuilder(t);
		HashMap<Character, Character> map = new HashMap<Character, Character>();
		for (int i = 0; i < s.length(); i++) {
			if (map.containsKey(tBuilder.charAt(i)))
				tBuilder.setCharAt(i, map.get(tBuilder.charAt(i)));
			else {
				map.put(tBuilder.charAt(i), s.charAt(i));
				tBuilder.setCharAt(i, s.charAt(i));
			}

			// if(s.charAt(i)!=tBuilder.charAt(i)){
			// tBuilder.setCharAt(i, s.charAt(i));
			// }
		}

		return s.equals(tBuilder.toString());

	}

	/***
	 * 
	 * @param a
	 *            Base (0 <= A <= 9)
	 * @param b
	 *            Power (0 <= B <= 4000)
	 * @return The sum of the digits of A powered B
	 */
	public static int sumAPoweredB(int a, int b) {
		long initial_time = System.currentTimeMillis();

		if (a < 0 || a > 9 || b < 0 || b > 4000)
			return -1;

		long power = (long) Math.pow(a, b);

		int sum = 0;

		int num_digits = (int) Math.log10(power) + 1;

		for (int i = num_digits; i >= 0; i--) {
			sum += power % 10;
			power /= 10;
		}

		System.out.println("TIME: " + (System.currentTimeMillis() - initial_time));

		return sum;
	}

	public static int sum_of_digits(int n) {
		int sum = 0;
		int num_digits = (int) Math.log10(n) + 1;
		for (int i = num_digits; i >= 0; i--) {
			sum += n % 10;
			n /= 10;
		}

		return sum;

	}

	/***
	 * Given two positive numbers M and N, such that M is between 100 and 10000
	 * and N is less than 100, find the smallest integer that is greater than M
	 * and whose digits add up to N. For example, if M = 100 and N = 11, the
	 * minimum number is 119 whose digits add up to N. Write a program to accept
	 * the numbers M and N from the user and print the smallest required number
	 * whose sum of all its digits is equal to N. Also, print the total number
	 * of digits present in the required number. The program should check for
	 * the validity of the inputs and display an appropriate message for an
	 * invalid input.
	 * 
	 * @param M
	 * @param N
	 * @return
	 */
	public static int smallestSumMN(int M, int N) {
		if (M < 100 || N >= 100)
			return -1;

		int sum = -1;
		for (int i = M + 1;; i++) {
			if (sum_of_digits(i) == N) {
				sum = i;
				break;
			}
		}

		return sum;
	}

	/***
	 * Find minimum number of coins (count and list of coins too)
	 * 
	 * For a given set of denominations, you are asked to find the minimum
	 * number of coins with which a given amount of money can be paid. Assume
	 * that you can use as many coins of a particular denomination as necessary.
	 * For example, given the denominations 1, 3, 4, and the target amount 6,
	 * the algorithm should find the optimal 2 coins required: 3 + 3.
	 * 
	 * @param value
	 * @param coins
	 * @return
	 */

	// Asume sorted denominations
	public static int minCoinCount(int value, int... denominations) {
		int sum = 0, previoussum = 0, currentValue = 0;

		while (currentValue < value) {
			for (int i = denominations.length - 1; i >= 0; i--) {
				if (currentValue + denominations[i] <= value) {
					currentValue += denominations[i];
					sum++;
					break;
				}
			}
			if (previoussum == sum)
				break;

			previoussum = sum;
		}

		if (currentValue == value)
			return sum;
		else
			return -1;

	}

	public static boolean isDivisible9Recursive(int N) {
		if (N < 9)
			return false;
		if (N == 9)
			return true;

		return isDivisible9Recursive(N - 9);
	}

	private static boolean isDivisible9Sum(int N) {
		System.out.println("N MOD 9: " + N % 9);
		int suma = sum_of_digits(N);
		if (suma > 9) {
			while (suma > 9) {
				suma = sum_of_digits(suma);
			}
		}
		return suma == 9;
	}

	/***
	 * Each element of an int array points to the another element, eventually
	 * creating a cycle. Starting at array[0], find the length of the cycle.
	 * 
	 * Constraints:
	 * 
	 * Must be in Java 7 Elements will always be positive or 0 and never point
	 * outside the array Elements will never point to themselves There will
	 * always be a cycle The cycle will be at least length 2
	 * 
	 * Input: array = [1, 3, 0, 4, 1] Output: 3
	 * 
	 * @param numbers
	 *            Int array
	 * @return Circle count
	 */
	public int circleLength(int... numbers) {
		int currentIndex = 0, count = 0;

		boolean[] numberTick = new boolean[numbers.length];

		boolean circle = false;
		while (!circle) {
			int current = numbers[currentIndex];
			if (numberTick[numbers[currentIndex]]) {
				circle = true;
				break;
			} else {
				count++;
				numberTick[numbers[currentIndex]] = true;
				currentIndex = numbers[currentIndex];
			}
		}

		return count;
	}

	@Test
	public void testCircleLength() {
		assertEquals(3, circleLength(1, 3, 0, 4, 1));
		assertEquals(2, circleLength(1, 0));
		assertEquals(2, circleLength(1, 2, 1));
	}

	/***
	 * Problem
	 * 
	 * Your task will be to write a program for reversing numbers in binary. For
	 * instance, the binary representation of 13 is 1101, and reversing it gives
	 * 1011, which corresponds to number 11.
	 * 
	 * Input
	 * 
	 * The input contains a single line with an integer N, (where
	 * 1<=N<=1000000000 ).
	 * 
	 * Output Output one line with one integer, the number we get by reversing
	 * the binary representation of N.
	 * 
	 * Sample 1
	 * 
	 * input 13 output 11
	 * 
	 * @param N
	 * @return
	 */
	public int reverseBinary(int N) {

		int binary = 0;

		if (N < 1 || N > 1000000000)
			return -1;

		int result = 0;

		while (N > 0) {
			result <<= 1;
			result |= 1 & N;
			N = N >> 1;
		}

		return result;
	}

	@Test
	public void testReverseBinary() {
		// assertEquals(0, reverseBinary(1));
		// assertEquals(1, reverseBinary(2));
		assertEquals(11, reverseBinary(13));
		assertEquals(61, reverseBinary(47));
	}

	// O(N/2)
	public String reverseString(String str) {
		if (str == null)
			return null;
		if (str.isEmpty())
			return "";

		StringBuilder builder = new StringBuilder(str);
		for (int i = 0, j = str.length() - 1; i < j; i++, j--) {
			char temp = builder.charAt(j);
			builder.setCharAt(j, builder.charAt(i));
			builder.setCharAt(i, temp);
		}

		return builder.toString();
	}

	@Test
	public void testReverseString() {
		assertEquals("aro", reverseString("ora"));
		assertEquals("aloh", reverseString("hola"));
		assertEquals("tseTortO", reverseString("OtroTest"));
	}

	// String reverse and pairing reversed words
	// Reverse a string
	// Find matching anagrams in a word list
//	public HashMap<String,List<String>> findAnagrams(List<String> words, String text) {
//		HashMap<String,List<String>> anagrams = new HashMap<String,List<String>>();
//
//		if (text != null && !words.isEmpty() && !text.isEmpty()) {
//			
//			for (String s : text.split("\\s+")) {
//				for(String word : words)
//				{
//					if(isAnagram(s,word))
//					{
//						if(anagrams.containsKey(word))
//						{
//							anagrams.get(word).add(s);
//						}
//						else{
//							anagrams.put(word,Arrays.asList(new String[] {s }));
//						}
//					}
//				}
//			}
//			
//		}
//		return anagrams;
//	}

	public boolean isAnagram(String A, String B) {
		if (A == null || B == null)
			return false;
		if (A.length() != B.length())
			return false;
		if (A.equals(B))
			return true;

		// Count chars as this: [A,B...,Y,Z,a,b....,y,z]
		// int[] charCount = new int[26*2];

		HashMap<Character, Integer> map = new HashMap<Character, Integer>();

		// Count chars of strings. A is positive and B is negative
		for (int i = 0; i < A.length(); i++) {
			char charA = A.charAt(i);
			char charB = B.charAt(i);

			if (map.containsKey(charA))
				map.put(charA, map.get(charA) + 1);
			else
				map.put(charA, 1);

			if (map.containsKey(charB))
				map.put(charB, map.get(charB) - 1);
			else
				map.put(charB, -1);

			// int indexA = Character.isUpperCase(charA) ?
			// ((int)charA)-((int)'A') : 26+(((int)charA)-((int)'a'));
			// int indexB = Character.isUpperCase(charB) ?
			// ((int)charB)-((int)'A') : 26+(((int)charB)-((int)'a'));
			//
			// charCount[indexA]++;
			// charCount[indexB]--;
		}

		boolean allZero = true;

		for (Integer sum : map.values()) {
			if (sum != 0) {
				allZero = false;
				break;
			}

		}

		// for(int i=0; allZero && i<26*2; i++)
		// {
		// if(charCount[i]!=0)
		// allZero=false;
		// }

		return allZero;

	}

	@Test
	public void testAnam() {
		assertEquals(true, isAnagram("abada", "daaba"));
		assertEquals(false, isAnagram("da", "bc"));
		assertEquals(true, isAnagram("yuaReqBNs", "sBqNeRyau"));
	}
	
	public String reverseWords(String text)
	{
		if(text==null) return null;
		if(text.isEmpty()) return text;
		
		StringBuilder builder = new StringBuilder();
		
		for(String w : text.trim().split("\\s+"))
		{
			builder.insert(0, " "+w);
		}
		
		return builder.toString().trim();
	}
	
	@Test
	public void testReverseWords() {
		assertEquals("", reverseWords(""));
		assertEquals("world Hello", reverseWords("Hello world"));
		assertEquals("cinco cuatro tres dos uno", reverseWords("uno dos   tres    cuatro cinco"));
	}
	
	public long maxRearangedInt(long number)
	{
		long result=0;
		int pow=0;
		while(number!=0)
		{
			long digit = number%10;
			
			if(result==0)
				result=digit;
			else{
				boolean inserted=false;
				int result_length = (int) Math.log10(result)+1;
				for(int i=0; !inserted && i<result_length; ++i)
				{
					int power10 =(int) Math.pow(10, i);
					int resdigit = (int) (result/power10)%10;
					
					if(resdigit>=digit)
					{
						int result_right=(int) ((result/power10)*Math.pow(10, i+1));
						int result_center = (int) (digit*power10);
						int result_left=(int) (result%power10);
						result=result_right+result_center+result_left;
						inserted=true;
					}
				}
				if(!inserted)
				{
					result=result+(digit*(int)Math.pow(10,result_length));
				}
			}
			number/=10;
			pow++;
		}
		
		return result;
	}

	@Test
	public void testRearangedInt() {
		assertEquals(32, maxRearangedInt(23));
		assertEquals(521, maxRearangedInt(125));
		assertEquals(8765543, maxRearangedInt(8754365 ));
	}
	
	public boolean isPalindrome(int number)
	{
		boolean palindrome=true;
		int length=(int) Math.log10(number);
		int i=0, j=length;
		while(palindrome && j>i)
		{
			int power=(int) Math.pow(10, j+1);
			int largest_digit = (number/(power/10))%10;
//			largest_digit=number/power;
			int lowerpower=(int) Math.pow(10, i+1);
//			int lowest_digit = (number%lowerpower)-((number%(int)Math.pow(10,i))*(int)Math.pow(10,i-1));
			int lowest_digit = (number%lowerpower)/(int)Math.pow(10, i);
			
			palindrome=largest_digit==lowest_digit;
			
			j--;
			i++;
			
		}
		return palindrome;
	}
	
	public int largestPalindromeProduct()
	{
		long time = System.currentTimeMillis();
		int result=0;
		for(int i=999; i>=100; i--)
		{
			for(int j=999; j>i; j--)
			{
				if(isPalindrome(i*j))
				{
					result=Math.max(result, i*j);
				}
			}
		}
		
		System.out.println("Largest Palindrome Product Time : "+(System.currentTimeMillis()-time));
		return result;
	}
	
	@Test
	public void testIsPalindrome() {
		assertEquals(true, isPalindrome(1225221));
		assertEquals(false, isPalindrome(23));
		assertEquals(true, isPalindrome(3));
		assertEquals(false, isPalindrome(900099));
		assertEquals(true, isPalindrome(323));
		assertEquals(false, isPalindrome(133));
	}
	
	@Test
	public void testlargestPalindrome() {
		System.out.println(largestPalindromeProduct());
	}
	
}
