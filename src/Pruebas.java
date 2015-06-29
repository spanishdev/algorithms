import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import junit.framework.TestCase;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

@SuppressWarnings("unused")
public class Pruebas extends TestCase {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StringBuilder builder = new StringBuilder(args[0]);
		ArrayList<String> strings = new ArrayList<String>();
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

	/***
	 * 
	 * Prints all the permutations of a given String. The algorithm splits the
	 * string between 2 given a character and based on that character, it does
	 * the permutations given the two parts of the string.
	 * 
	 * EXAMPLE: We have the String "abc", so the calculation will be the
	 * following. For each step I will show the following format: PREFIX +
	 * STRING
	 * 
	 * STEP 1: "" + "abc" STEP 2: "a" + "bc" STEP 3: "ab" + "c" STEP 4: "abc" +
	 * "" --> PRINT "abc" STEP 5: "ac" + "b" --> RECURSION BACK TO STEP 3, WE
	 * CHANGE b for c STEP 6: "acb" + "" --> PRINT "acb" STEP 7: "b" + "ac" -->
	 * RECURSION BACK TO STEP 2 STEP 8: "ba" + "c" STEP 9: "bac" + "" --> PRINT
	 * "bac" STEP 10: "bc" + "a" --> RECURSION BACK ON STEP 8 STEP 11: "bca" +
	 * "" --> PRINT "bca" STEP 12: "c" + "ab" --> RECURSION BACK TO STEP 2 STEP
	 * 13: "ca" + "b" STEP 14: "cab" + "" --> PRINT "cab" STEP 15: "cb" + "a"
	 * --> RECURSION BACK TO STEP 13 STEP 16: "cba" + "" --> PRINT "cba" ENDED
	 * (Var String is Empty)
	 * 
	 * @param prefix
	 *            Prefix of the current Step (starts Empty)
	 * @param string
	 *            Sufix of the current Step (starts with the full string)
	 */
	private static void permutation(String prefix, String string) {
		int size = string.length();
		if (string.isEmpty())
			System.out.println(prefix);
		else {
			for (int i = 0; i < size; i++)
				permutation(prefix + string.charAt(i), string.substring(0, i) + string.substring(i + 1));
		}
	}

	/***
	 * 
	 * As the name says, it returns the integer reversed (EXAMPLE: 34 would be
	 * 43)
	 * 
	 * @param num
	 *            Number to reverse
	 * @return The number inversed
	 */
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

	@Test
	public void testReverseInteger() {
		assertEquals(3, reverseInteger(3));
		assertEquals(23, reverseInteger(32));
		assertEquals(2465, reverseInteger(5642));
		assertEquals(11, reverseInteger(11));
		assertNotSame(324, reverseInteger(432));
		assertEquals(-11, reverseInteger(-11));
		assertEquals(-123, reverseInteger(-321));
		assertNotSame(123, reverseInteger(-321));
	}

	/***
	 * It returns the binary value of the number
	 * 
	 * @param num
	 *            Number to transform
	 * @return Binary format of the number
	 */
	private static String intToBinaryString(int num) {

		if (num == 0)
			return "0";

		StringBuilder builder = new StringBuilder();

		while (num != 0) {
			builder.insert(0, Math.abs(num % 2));
			num = num >>> 1;
		}
		System.out.println(builder.toString());
		return builder.toString();
	}

	@Test
	public void testIntToBinaryString() {
		assertNotSame("101", intToBinaryString(12));
		assertEquals("11", intToBinaryString(3));
		assertEquals("10111", intToBinaryString(23));
		assertEquals("0", intToBinaryString(0));
		assertThat(intToBinaryString(-23), CoreMatchers.containsString("11101001"));
	}

	/***
	 * 
	 * Given a number, count the number of ones which have its binary number
	 * 
	 * @param num
	 *            Number to compute the operation
	 * @return Number of binary ones
	 */
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

	@Test
	public void testNumberOfOnes() {
		assertNotSame(3, numberOfOnes(12));
		assertEquals(29, numberOfOnes(-12));
		assertEquals(2, numberOfOnes(3));
		assertEquals(31, numberOfOnes(-2));
		assertEquals(4, numberOfOnes(23));
		assertEquals(0, numberOfOnes(0));
		assertEquals(29, numberOfOnes(-23));
	}

	/***
	 * 
	 * Problem solving FizzBuzz. Prints the numbers as is, except number 3 which
	 * is replaced by Fizz and 5 which is replaced by Buzz
	 * 
	 * @param n
	 *            Maximum number to print
	 * @return FizzBuzz String
	 */
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

	/***
	 * 
	 * Return a String containing the prime numbers until num
	 * 
	 * EXAMPLE: If num is 5, the result is: 2,3,5
	 * 
	 * @param num
	 *            Maximum number to print all the prime numbers
	 * @return The prime numbers, or empty string if the number is not
	 *         appropiate (num<2)
	 */
	private static String primeNumbers(int num) {
		StringBuilder result = new StringBuilder();
		for (int i = 2; i <= num; i++) {
			if (i % 2 == 0 && i != 2)
				continue;
			boolean prime = true;
			for (int j = 2; j <= Math.sqrt(i); j++) {
				if (i % j == 0) {
					prime = false;
					break;
				}
			}
			if (prime) {
				if (result.length() != 0)
					result.append(", ");
				result.append(i);
			}
		}

		System.out.println(result.toString());
		return result.toString();
	}

	@Test
	public void testPrimeNumbers() {
		assertEquals("", primeNumbers(-12));
		assertEquals("2, 3, 5", primeNumbers(5));
		assertEquals("2, 3, 5", primeNumbers(6));
		assertEquals("2, 3, 5, 7, 11, 13, 17, 19, 23", primeNumbers(25));
	}

	private static void combinations(String string, int N) {
		if (N > 0 && N <= string.length()) {
			for (int i = 0; i <= string.length() - N; i++) {
				for (int j = i + 1; j + (N - 1) <= string.length(); j++)
					System.out.println(string.charAt(i) + string.substring(j, j + (N - 1)));
			}
		}

	}

	/***
	 * 
	 * Informs whether a number is prime
	 * 
	 * @param num
	 *            Number to check
	 * @return True if is prime, False otherwise
	 */
	private static boolean isPrime(int num) {
		boolean prime = num > 1;
		for (int i = 2; i <= Math.sqrt(num) && prime; i++) {
			if (num % i == 0) {
				prime = false;
			}
		}
		return prime;
	}

	@Test
	public void testIsPrime() {
		assertEquals(false, isPrime(-12));
		assertEquals(false, isPrime(0));
		assertEquals(false, isPrime(1));
		assertEquals(true, isPrime(3));
		assertEquals(false, isPrime(6));
		assertEquals(true, isPrime(23));
		assertEquals(true, isPrime(127));
	}

	/***
	 * 
	 * Return the factors of a number
	 * 
	 * EXAMPLE: 12 returns 2,2,3
	 * 
	 * @param num
	 *            Number to get its factors
	 * @return A String containing the factors
	 */
	private static String factors(int num) {
		StringBuilder builder = new StringBuilder();
		while (num > 1) {
			int divisor = num;
			if (num % 2 == 0) {
				divisor = 2;
			} else if (!isPrime(num)) {
				for (int i = 3; i <= Math.sqrt(num); i++) {
					if (num % i == 0) {
						divisor = i;
						break;
					}
				}
			}
			if (builder.length() > 0)
				builder.append(", ");
			builder.append(divisor);
			num /= divisor;
		}
		return builder.toString();
	}

	@Test
	public void testFactors() {
		assertEquals("", factors(0));
		assertEquals("", factors(-12));
		assertEquals("", factors(1));
		assertEquals("2, 3", factors(6));
		assertEquals("5", factors(5));
		assertEquals("2, 11", factors(22));
		assertEquals("2, 2, 3", factors(12));
		assertEquals("2, 3, 7", factors(42));
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

	/***
	 * 
	 * Return the digit of num located at index position. IMPORTANT: It
	 * calculates the digit from right to left
	 * 
	 * 
	 * EXAMPLE: If num is 134 and index is 2, returns 3
	 * 
	 * ANOTHER EXAMPLE: If num is 326 and index is 0, it returns 6
	 * 
	 * @param num
	 *            Number
	 * @param index
	 *            Index of the desired digit
	 * @return The digit of num located at indexth position, or -1 if the index
	 *         is out of range
	 */
	private static int getDigit(int num, int index) {
		num = Math.abs(num);
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

	@Test
	public void testGetDigit() {
		assertEquals(-1, getDigit(0, 1));
		assertEquals(2, getDigit(23, 1));
		assertEquals(3, getDigit(23, 0));
		assertEquals(7, getDigit(1247423, 3));
		assertEquals(7, getDigit(-1247423, 3));
		assertEquals(3, getDigit(-343, 0));
	}

	/***
	 * 
	 * Returns a String representing an int array.
	 * 
	 * @param A
	 *            Array to get the string
	 * @return An String of the array in format [1,2,3...]
	 */
	public static String ArrayToString(int[] A) {
		StringBuilder builder = new StringBuilder();
		builder.append("[");

		for (int i = 0; i < A.length; i++) {
			builder.append(A[i]);
			if (i < A.length - 1)
				builder.append(",");
		}

		builder.append("]");

		return builder.toString();
	}

	/***
	 * 
	 * It sorts an array of ints A using QuickSort. It cals the auxiliary
	 * function QuickSortRecursive which performs the recursive cases.
	 * 
	 * 
	 * @param A
	 *            Array to sort
	 * 
	 */
	public static String QuickSort(int[] A) {
		return QuickSortRecursive(A, 0, A.length - 1);
	}

	/***
	 * 
	 * Implements the recursivity of QuickSort algorithm using an int array A.
	 * It splits the array in 2 parts: One using [min,....,partition-1] and
	 * other [partition+1,....,max]
	 * 
	 * It prints the Time in milliseconds which it takes
	 * 
	 * 
	 * @param A
	 *            Array to sort
	 * @param min
	 *            Minimum index
	 * @param max
	 *            Maximum index
	 */
	public static String QuickSortRecursive(int[] A, int min, int max) {

		long time = System.currentTimeMillis();

		if (A.length > 1 && min < max) {
			int partition = QSPartition(A, min, max);

			QuickSortRecursive(A, min, partition - 1);
			QuickSortRecursive(A, partition + 1, max);
		}

		System.out.println("TIME TAKEN: " + (System.currentTimeMillis() - time) + " ms");
		return ArrayToString(A);

	}

	/***
	 * 
	 * Sorts a part of an array of ints from min to max and returns the last
	 * sorted index. The pivot is the half point of the array. All the ints
	 * greather than the pivot go right, and the lower go left.
	 * 
	 * @param A
	 *            Array of ints
	 * @param min
	 *            Minimum index of the interval
	 * @param max
	 *            Maximum index of the interval
	 * @return The index of the pivot
	 */
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

	@Test
	public void testQuickSort() {
		assertEquals("[1,3,5,7,10]", QuickSort(new int[] { 1, 5, 3, 10, 7 }));
		assertEquals("[10]", QuickSort(new int[] { 10 }));
		assertEquals("[]", QuickSort(new int[] {}));
		assertEquals("[0,1,5,6,9,10,23,25,34,53,61,65,99,167,230,243,535]", QuickSort(new int[] { 9, 5, 167, 53, 243, 23, 25, 65, 99, 230, 535, 61, 34, 10, 6, 1, 0 }));
	}

	/***
	 * 
	 * Given an int array A, and index i and an index j, swaps both index of the
	 * array
	 * 
	 * @param A
	 *            Array
	 * @param i
	 *            Index 1
	 * @param j
	 *            Index 2
	 */
	private static void swap(int[] A, int i, int j) {

		if (i < 0 || i >= A.length || j < 0 || j >= A.length)
			return;

		int aux = A[i];
		A[i] = A[j];
		A[j] = aux;
	}

	/***
	 * 
	 * This function performs the InsertSort algorithm.
	 * 
	 * @param A
	 *            Array of ints to sort
	 */
	public static void InsertSort(int[] A) {

		for (int i = 1; i < A.length; i++) {
			int j = i;
			while (j > 0 && A[j] < A[j - 1]) {
				swap(A, j, j - 1);
				j--;
			}
		}

	}

	/***
	 * 
	 * Powers the base number to the exp. (Only works with positive exp)
	 * 
	 * Example: If base is 2 and exp is 4, it returns 16
	 * 
	 * @param base
	 *            Base number
	 * @param exp
	 *            Exponent number
	 * @return Base powered to Exponent
	 * @throws Exception
	 */
	public static int Power(int base, int exp) throws Exception {
		if (exp < 0)
			throw new Exception("Exponent less than 0");
		if (exp == 0)
			return 1;
		else {
			if (base == 2)
				return base << (exp - 1);
			else
				return base * Power(base, exp - 1);
		}
	}

	@Test
	public void testPower() {
		try {
			assertEquals(4, Power(2, 2));
			assertEquals(1, Power(2, 0));
			assertEquals(2, Power(2, 1));
			assertEquals(1, Power(5, 0));
			assertEquals(25, Power(5, 2));
			assertEquals(32, Power(2, 5));
			assertEquals(32, Power(2, 5));
			assertEquals(-32, Power(-2, 5));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// assertEquals(0, Power(2,-1));
	}

	/***
	 * 
	 * Returns the factorial of n
	 * 
	 * @param n
	 *            Number to calculate Factorial
	 * @return The factorial of n
	 * @throws Exception
	 *             n is out of range
	 */
	public static int factorial(int n) throws Exception {
		if (n < 0)
			throw new Exception("Número fuera de rango");
		else if (n < 2)
			return 1;
		else
			return n * factorial(n - 1);
	}

	@Test
	public void testFactorial() throws Exception {
		assertEquals(2, factorial(2));
		assertEquals(1, factorial(0));
		assertEquals(6 * 5 * 4 * 3 * 2, factorial(6));
		assertEquals(8 * 7 * 6 * 5 * 4 * 3 * 2, factorial(8));
	}

	/***
	 * 
	 * An isomorphic String is an String which, changed with other letters, can
	 * form the other String. The main rule is that a letter must be equal in
	 * all the word (if we change "l" for "m", all "l" must be "m")
	 * 
	 * Example:
	 * 
	 * hellos is isomorphic with hammer, but not with papers
	 * 
	 * @param s
	 *            String one
	 * @param t
	 *            String two
	 * @return If the String s is isomorphic with t
	 */
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
			if (map.containsKey(tBuilder.charAt(i))) {
				tBuilder.setCharAt(i, map.get(tBuilder.charAt(i)));
			} else if (!map.containsValue(s.charAt(i))) {
				map.put(tBuilder.charAt(i), s.charAt(i));
				tBuilder.setCharAt(i, s.charAt(i));
			}
		}

		return s.equals(tBuilder.toString());

	}

	@Test
	public void testIsomorphic() {
		assertEquals(true, isIsomorphic("", ""));
		assertEquals(false, isIsomorphic("", "sad"));
		assertEquals(true, isIsomorphic("paper", "title"));
		assertEquals(false, isIsomorphic("aaaa", "bcde"));
		assertEquals(false, isIsomorphic("bcde", "aaaa"));
		assertEquals(false, isIsomorphic("paper", "tiles"));
		assertEquals(true, isIsomorphic("trefd", "abvdt"));
		assertEquals(true, isIsomorphic("hello", "sally"));
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

	@Test
	public void testSumAPoweredB() {
		assertEquals(-1, sumAPoweredB(10, 4000));
		assertEquals(-1, sumAPoweredB(5, 4004));
		assertEquals(-1, sumAPoweredB(-1, 3212));
		assertEquals(-1, sumAPoweredB(3, -1));
		assertEquals(8, sumAPoweredB(5, 3));
	}

	/***
	 * 
	 * @param n
	 *            Number
	 * @return The sum of its digits
	 */
	public static int sum_of_digits(int n) {
		if (n < 0)
			return -1;
		int sum = 0;
		int num_digits = (int) Math.log10(n) + 1;
		for (int i = num_digits; i >= 0; i--) {
			sum += n % 10;
			n /= 10;
		}

		return sum;

	}

	@Test
	public void testSum_of_digits() {
		assertEquals(5, sum_of_digits(5));
		assertEquals(5, sum_of_digits(23));
		assertEquals(17, sum_of_digits(1934));
		assertEquals(-1, sum_of_digits(-1934));
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
		if (M < 100 || M > 10000 || N < 0 || N > 100)
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

	@Test
	public void testSmallestSumMN() {
		assertEquals(-1, smallestSumMN(5, 10));
		assertEquals(-1, smallestSumMN(150, 101));
		assertEquals(-1, smallestSumMN(10001, 100));
		assertEquals(-1, smallestSumMN(1056, -1));
		assertEquals(10699, smallestSumMN(10000, 25));
		assertEquals(89999, smallestSumMN(150, 44));
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
	 *            Value to pay
	 * @param denominations
	 *            Denominations of the coins
	 * @return The minimum number of coins to pay
	 * @throws Exception
	 */
	public static int minCoinCount(int value, int... denominations) throws Exception {
		if (value <= 0)
			return 0;
		
		if(denominations.length==0)
			return 0;

		Arrays.sort(denominations);

		int[] sums = new int[value + 1];
		sums[0] = 0;

		for (int i = 1; i < value + 1; i++)
			sums[i] = Integer.MAX_VALUE;

		for (int i = 1; i <= value; i++)
		{
			for (int coin : denominations) 
			{
				if(i>=coin && sums[i-coin]+1<sums[i] && sums[i-coin]!=Integer.MAX_VALUE)
					sums[i]=sums[i-coin]+1;
			}
		}

		return sums[value];
	}

	@Test
	public void testMinCoinCount() throws Exception {
		assertEquals(0, minCoinCount(-10, 10));
		assertEquals(Integer.MAX_VALUE, minCoinCount(11, 5, 4));
		assertEquals(3, minCoinCount(10, 3, 4));
		assertEquals(3, minCoinCount(10, 4, 3));
		assertEquals(2, minCoinCount(10, 1, 5, 3));
		assertEquals(2, minCoinCount(6, 3, 4));
		assertEquals(4, minCoinCount(20, 2, 6));
		assertEquals(3, minCoinCount(23, 5, 13, 15));
		assertEquals(5, minCoinCount(15, 1, 6));
		assertEquals(6, minCoinCount(19, 1, 5, 10, 20));

	}


	/***
	 * 
	 * Tells whether number N is divisible by 9. It must be calculated without
	 * using division.
	 * 
	 * @param N
	 *            Number
	 * @return True if N is divisible by 9, otherwise False
	 */
	public static boolean isDivisible9Recursive(int N) {
		if (N < 9)
			return false;
		if (N == 9)
			return true;

		return isDivisible9Recursive(N - 9);
	}

	@Test
	public void testIsDivisible9Recursive() {
		assertEquals(false, isDivisible9Recursive(10));
		assertEquals(false, isDivisible9Recursive(0));
		assertEquals(true, isDivisible9Recursive(18));
		assertEquals(true, isDivisible9Recursive(81));
	}

	/***
	 * Each element of an int array points to the another element, eventually
	 * creating a cycle. Starting at array[0], find the length of the cycle.
	 * 
	 * Constraints:
	 * 
	 * Must be in Java 7 Elements will always be positive or 0 and never point
	 * outside the array. Elements will never point to themselves. There will
	 * always be a cycle. The cycle will be at least length 2.
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
		assertEquals(-1, reverseBinary(0));
		assertEquals(1, reverseBinary(1));
		assertEquals(1, reverseBinary(2));
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
	// public HashMap<String,List<String>> findAnagrams(List<String> words,
	// String text) {
	// HashMap<String,List<String>> anagrams = new
	// HashMap<String,List<String>>();
	//
	// if (text != null && !words.isEmpty() && !text.isEmpty()) {
	//
	// for (String s : text.split("\\s+")) {
	// for(String word : words)
	// {
	// if(isAnagram(s,word))
	// {
	// if(anagrams.containsKey(word))
	// {
	// anagrams.get(word).add(s);
	// }
	// else{
	// anagrams.put(word,Arrays.asList(new String[] {s }));
	// }
	// }
	// }
	// }
	//
	// }
	// return anagrams;
	// }

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

	public String reverseWords(String text) {
		if (text == null)
			return null;
		if (text.isEmpty())
			return text;

		StringBuilder builder = new StringBuilder();

		for (String w : text.trim().split("\\s+")) {
			builder.insert(0, " " + w);
		}

		return builder.toString().trim();
	}

	@Test
	public void testReverseWords() {
		assertEquals("", reverseWords(""));
		assertEquals("world Hello", reverseWords("Hello world"));
		assertEquals("cinco cuatro tres dos uno", reverseWords("uno dos   tres    cuatro cinco"));
	}

	public long maxRearangedInt(long number) {
		long result = 0;
		int pow = 0;
		while (number != 0) {
			long digit = number % 10;

			if (result == 0)
				result = digit;
			else {
				boolean inserted = false;
				int result_length = (int) Math.log10(result) + 1;
				for (int i = 0; !inserted && i < result_length; ++i) {
					int power10 = (int) Math.pow(10, i);
					int resdigit = (int) (result / power10) % 10;

					if (resdigit >= digit) {
						int result_right = (int) ((result / power10) * Math.pow(10, i + 1));
						int result_center = (int) (digit * power10);
						int result_left = (int) (result % power10);
						result = result_right + result_center + result_left;
						inserted = true;
					}
				}
				if (!inserted) {
					result = result + (digit * (int) Math.pow(10, result_length));
				}
			}
			number /= 10;
			pow++;
		}

		return result;
	}

	@Test
	public void testRearangedInt() {
		assertEquals(32, maxRearangedInt(23));
		assertEquals(521, maxRearangedInt(125));
		assertEquals(8765543, maxRearangedInt(8754365));
	}

	public boolean isPalindrome(int number) {
		boolean palindrome = true;
		int length = (int) Math.log10(number);
		int i = 0, j = length;
		while (palindrome && j > i) {
			int power = (int) Math.pow(10, j + 1);
			int largest_digit = (number / (power / 10)) % 10;
			// largest_digit=number/power;
			int lowerpower = (int) Math.pow(10, i + 1);
			// int lowest_digit =
			// (number%lowerpower)-((number%(int)Math.pow(10,i))*(int)Math.pow(10,i-1));
			int lowest_digit = (number % lowerpower) / (int) Math.pow(10, i);

			palindrome = largest_digit == lowest_digit;

			j--;
			i++;

		}
		return palindrome;
	}

	public int largestPalindromeProduct() {
		long time = System.currentTimeMillis();
		int result = 0;
		for (int i = 999; i >= 100; i--) {
			for (int j = 999; j > i; j--) {
				if (isPalindrome(i * j)) {
					result = Math.max(result, i * j);
				}
			}
		}

		System.out.println("Largest Palindrome Product Time : " + (System.currentTimeMillis() - time));
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
