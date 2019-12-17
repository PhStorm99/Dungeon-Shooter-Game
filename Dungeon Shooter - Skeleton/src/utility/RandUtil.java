package utility;

import java.util.Random;

/**
 * a utility class for using {@link Random}. aside from extra functionality
 * it also provides the same random seed to all the project.
 * 
 * @author Shahriar (Shawn) Emami
 * @version Jan 12, 2019
 */
public final class RandUtil{

	/**
	 * final static {@link Random} object to be accessed globally.
	 */
	public static final Random RAND = new Random();

	/**
	 * choose a random argument from one of the given integer arguments.
	 * @param nums - collections of integer arguments to choose from.
	 * @return one of the given integer arguments
	 */
	public static int getFrom( int...nums){
		return nums[RAND.nextInt( nums.length)];
	}

	/**
	 * choose a random argument from one of the given double arguments.
	 * @param nums - collections of double arguments to choose from.
	 * @return one of the given double arguments
	 */
	public static double getFrom( double...nums){
		return nums[RAND.nextInt( nums.length)];
	}

	/**
	 * choose a random argument from one of the given arguments.
	 * @param nums - collections of arguments to choose from.
	 * @return one of the given arguments
	 */
	@SafeVarargs
	public static <T> T getFrom( T...ts){
		return ts[RAND.nextInt( ts.length)];
	}

	/**
	 * get 1 or -1
	 * @return 1 or -1
	 */
	public static int getPosNeg(){
		return RAND.nextBoolean() ? 1 : -1;
	}

	/**
	 * get a random integer between min and max.
	 * @param min - inclusive, min random integer
	 * @param max - exclusive, max random integer
	 * @return random integer value
	 */
	public static int getInt( int min, int max){
		return RAND.nextInt( max - min) + min;
	}

	/**
	 * get a random integer between 0 and max.
	 * @param max - exclusive, max random integer
	 * @return random integer value
	 */
	public static int getInt( int max){
		return RAND.nextInt( max);
	}

	/**
	 * get a random double between min and max.
	 * @param min - inclusive, min random double
	 * @param max - exclusive, max random double
	 * @return random double value
	 */
	public static double getDouble( double min, double max){
		return RAND.nextDouble() * (max - min) + min;
	}

	/**
	 * get a random double between 0.0 and max.
	 * @param max - exclusive, max random double
	 * @return random double value
	 */
	public static double getDouble( double max){
		return RAND.nextDouble() * max;
	}
}
