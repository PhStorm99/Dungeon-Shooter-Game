package utility;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * <h3>Property and its subclasses like DoubleProperty and SimpleDoubleProperty</h3>
 * <p>
 * below are two {@link DoubleProperty} called x and y which will be used
 * in testing the {@link Point}.<br>
 * this variable can be initialized with {@link SimpleDoubleProperty}.
 * </p>
 * 
 * <pre>
 * //initializing
 * DoubleProperty i1 = new SimpleDoubleProperty( 1);
 * DoubleProperty i2 = new SimpleDoubleProperty();
 * //connecting two properties so if one changes so the other
 * i1.bind( i2);
 * //set a new value for i2, this set will also change i1
 * i2.set( 100);
 * System.out.println( i1.get()); // prints 100
 * </pre>
 */
class PointTest{
	
	/**
	 * create two {@link Point} variables.
	 * bind p1 to p2 by calling bind on p1 and pass p2.
	 * set a the new value p2 as 11,12. 
	 * now use assertEquals to compare the 2 points.
	 * compare the return value of x() for both points to 11.
	 * compare the return value of y() for both points to 12.
	 */
	@Test
	final void testPoint(){
	//	fail("");//TODO
		
		Point p1 = new Point();
		Point p2 = new Point();
		p1.bind(p2);
		
		p2.set(11,12);
		assertEquals(p1,p2);
		assertEquals(11,p1.x());
		assertEquals(11,p2.x());
		assertEquals(12,p1.y());
		assertEquals(12,p2.y());
		
	}
	
	/**
	 * create a {@link Point} called p1 and initialize to 10,11.
	 * create a {@link Point} called p2 and initialize to 12,13.
	 * bind p1 to p2 by calling bind on p1 and pass p2.
	 * now use assertEquals to compare the 2 points.
	 * compare the return value of x() for p1 to 10.
	 * compare the return value of y() for p1 to 11.
	 * optional, compare the return value of x() for p1 to 12.
	 * optional, compare the return value of y() for p1 to 13.
	 * compare the return value of x() for p2 to 12.
	 * compare the return value of y() for p2 to 13.
	 */
	@Test
	final void testPointDoubleDouble(){
	//	fail("");//TODO
		
		Point p1 = new Point(10,11);
		Point p2 = new Point(12,13);
		p1.bind(p2);
		assertEquals(p1,p2);
		assertNotEquals(10,p1.x());
		assertNotEquals(11,p1.y());
		assertEquals(12,p2.x());
		assertEquals(13,p2.y());
		
	}

	/**
	 * create a {@link Point} called p1 and initialize to 10,11.
	 * create a {@link Point} called p2 and initialize to p1.
	 * do NOT bind instead set a new value for p2 as 12,13.
	 * now use assertNotEquals to compare the 2 points.
	 * compare the return value of x() for p1 to 10.
	 * compare the return value of y() for p1 to 11.
	 * compare the return value of x() for p2 to 12.
	 * compare the return value of y() for p2 to 13.
	 */
	@Test
	final void testPointPoint(){
	//	fail("");//TODO
	
		Point p1 = new Point(10,11);
		Point p2 = new Point(p1);
		p2.set(12,13);
		assertNotEquals(p1,p2);
		assertEquals(10,p1.x());
		assertEquals(11,p1.y());
		assertEquals(12,p2.x());
		assertEquals(13,p2.y());

	}

	/**
	 * create a {@link Point} called p1 and initialize to 10,11.
	 * create a {@link Point} called p2 and initialize to 12,13.
	 * bind p1 to p2 by calling bind on p1 and pass p2.
	 * now use assertEquals to compare the 2 points.
	 * compare the return value of x() for p1 to 10.
	 * compare the return value of y() for p1 to 11.
	 * optional, compare the return value of x() for p1 to 12.
	 * optional, compare the return value of y() for p1 to 13.
	 * compare the return value of x() for p2 to 12.
	 * compare the return value of y() for p2 to 13.
	 * call unbind on p1. 											____________
	 * set 14,15 on p1 and set 16,17 on p2
	 * now use assertNotEquals to compare the 2 points.
	 * compare the return value of x() for p1 to 14.
	 * compare the return value of y() for p1 to 15.
	 * compare the return value of x() for p2 to 16.
	 * compare the return value of y() for p2 to 17.
	 */
	@Test
	final void testUnbind(){
//		fail("");//TODO
		
		Point p1 = new Point(10,11);
		Point p2 = new Point(12,13);
		p1.bind(p2);
		assertEquals(p1,p2);
		assertNotEquals(10,p1.x());
		assertNotEquals(11,p1.y());
		assertEquals(12,p2.x());
		assertEquals(13,p2.y());
		p1.unbind();
		p1.set(14, 15);
		p2.set(16,17);
		assertNotEquals(p1,p2);
		assertEquals(14,p1.x());
		assertEquals(15,p1.y());
		assertEquals(16,p2.x());
		assertEquals(17,p2.y());
		
	}

	/**
	 * create a {@link Point} called p1 and initialize to 10,11.
	 * create a {@link Point} called p2 and initialize to 12,13.
	 * bind p1 to p2 by calling bind on p1 and pass p2.
	 * call translate on p2 and pass 1,2.
	 * now use assertEquals to compare the 2 points.
	 * compare the return value of x() for p1 to 13.
	 * compare the return value of y() for p1 to 15.
	 * compare the return value of x() for p2 to 13.
	 * compare the return value of y() for p2 to 15.
	 * call unbind on p1.
	 * call translate on p1 and pass 1,2.
	 * call translate on p2 and pass 3,4.
	 * now use assertNotEquals to compare the 2 points.
	 * compare the return value of x() for p1 to 14.
	 * compare the return value of y() for p1 to 17.
	 * compare the return value of x() for p2 to 16.
	 * compare the return value of y() for p2 to 19.
	 */
	@Test
	final void testTranslate(){
	//	fail("");//TODO
		
		Point p1 = new Point(10,11);
		Point p2 = new Point(12,13);
		p1.bind(p2);
		p2.translate(1, 2);
		assertEquals(p1,p2);
		assertEquals(13,p1.x());
		assertEquals(15,p1.y());
		assertEquals(13,p2.x());
		assertEquals(15,p2.y());
		p1.unbind();
		p1.translate(1, 2);
		p2.translate(3, 4);
		assertNotEquals(p1,p2);
		assertEquals(14,p1.x());
		assertEquals(17,p1.y());
		assertEquals(16,p2.x());
		assertEquals(19,p2.y());
		
		
	}

	/**
	 * create a {@link Point} called p1 and initialize to 10,11.
	 * create a {@link Point} called p2 and initialize to 12,13.
	 * bind p1 to p2 by calling bind on p1 and pass p2.
	 * call move on p2 and pass 8, 9.
	 * now use assertEquals to compare the 2 points.
	 * compare the return value of x() for p1 to 8.
	 * compare the return value of y() for p1 to 9.
	 * compare the return value of x() for p2 to 8.
	 * compare the return value of y() for p2 to 9.
	 * call unbind on p1.
	 * call move on p1 and pass 6, 7.
	 * call move on p2 and pass 8, 9.
	 * now use assertNotEquals to compare the 2 points.
	 * compare the return value of x() for p1 to 6.
	 * compare the return value of y() for p1 to 7.
	 * compare the return value of x() for p2 to 8.
	 * compare the return value of y() for p2 to 9.
	 */
	@Test
	final void testMoveDoubleDouble(){
	//	fail("");//TODO
		
		Point p1 = new Point(10,11);
		Point p2 = new Point(12,13);
		p1.bind(p2);
		p2.move(8, 9);
		assertEquals(p1,p2);
		assertEquals(8,p1.x());
		assertEquals(9,p1.y());
		assertEquals(8,p2.x());
		assertEquals(9,p2.y());
		p1.unbind();
		p1.move(6, 7);
		p2.move(8, 9);
		assertNotEquals(p1,p2);
		assertEquals(6,p1.x());
		assertEquals(7,p1.y());
		assertEquals(8,p2.x());
		assertEquals(9,p2.y());
	}

	/**
	 * create a {@link Point} called p1 and initialize to 10,11.
	 * create a {@link Point} called p2 and initialize to 12,13.
	 * create a {@link Point} called p3 and initialize to 14,15.
	 * bind p1 to p2 by calling bind on p1 and pass p2.
	 * call move on p2 and pass p3.
	 * now use assertEquals to compare the p1,p3 and p2,p3 points.
	 * compare the return value of x() for p1 to x() of p3.
	 * compare the return value of y() for p1 to y() of p3.
	 * compare the return value of x() for p2 to x() of p3.
	 * compare the return value of y() for p2 to y() of p3.
	 * call unbind on p1.
	 * call move on p1 and pass 6, 7.
	 * call move on p2 and pass 8, 9.
	 * call move on p3 and pass 10, 11.
	 * now use assertNotEquals to compare p1, p3.
	 * now use assertNotEquals to compare p2, p3.
	 * now use assertNotEquals to compare p1, p2.
	 * compare the return value of x() for p1 to 6.
	 * compare the return value of y() for p1 to 7.
	 * compare the return value of x() for p2 to 8.
	 * compare the return value of y() for p2 to 9.
	 * optional, compare the return value of x() for p3 to 10.
	 * optional, compare the return value of y() for p3 to 11.
	 */
	@Test
	final void testMovePoint(){
	//	fail("");//TODO
		
		Point p1 = new Point(10,11);
		Point p2 = new Point(12,13);
		Point p3 = new Point(14,15);
		p1.bind(p2);
		p2.move(p3);
		
		assertEquals(p1,p3);
		assertEquals(p2,p3);
		
		assertEquals(p3.x(),p1.x());
		assertEquals(p3.y(),p1.y());
		assertEquals(p3.x(),p2.x());
		assertEquals(p3.y(),p2.y());
		
		p1.unbind();
		p1.move(6, 7);
		p2.move(8, 9);
		p3.move(10, 11);
		
		assertNotEquals(p1,p3);
		assertNotEquals(p2,p3);
		assertNotEquals(p1,p2);
		
		assertEquals(6,p1.x());
		assertEquals(7,p1.y());
		assertEquals(8,p2.x());
		assertEquals(9,p2.y());
		
	}

	/**
	 * create a {@link Point} called p1 and initialize to 10,11.
	 * create a {@link Point} called p2 and initialize to 12,13.
	 * bind p1 to p2 by calling bind on p1 and pass p2.
	 * when a property is binded to another value it cannot be
	 * changed directly. it must be changed through the binded value.
	 * use assertThrows to catch the exception that p1.set() will throw.
	 */
	@Test
	final void testSet(){
	//	fail("");//TODO
		Point p1 = new Point(10,11);
		Point p2 = new Point(12,13);
		
		p1.bind(p2);
		
	//	assertThrows(class <PointTest> exception, Executable );
		assertThrows(RuntimeException.class,()->p1.set(31, 32));
		
	}

	/**
	 * create a {@link Point} called p1 and initialize to 10,11.
	 * create a {@link Point} called p2 and initialize to 12,13.
	 * call toArray on p1 and p2 and compare to each other to confirm
	 * they are not the same.
	 * call bind on p1 and p2.
	 * use assertArrayEquals and pass toArray of both points.
	 */
	@Test
	final void testToArray(){
	//	fail("");//TODO
		
		Point p1 = new Point(10,11);
		Point p2 = new Point(12,13);
		p1.toArray();
		p2.toArray();
		assertNotEquals(p1,p2);
		p1.bind(p2);
		assertArrayEquals(p1.toArray(),p2.toArray());
	}

	/**
	 * create a {@link Point} called p1 and initialize to 10,11.
	 * create a {@link Point} called p2 and initialize to 12,13.
	 * now use assertNotEquals to compare p1, p2.
	 * call bind on p1 and pass p2.
	 * now use assertEquals to compare p1, p2.
	 */
	@Test
	final void testEqualsObject(){
		//fail("");//TODO
		
		Point p1 = new Point(10,11);
		Point p2 = new Point(12,13);
		assertNotEquals(p1,p2);
		p1.bind(p2);
		assertEquals(p1,p2);
		
		
	}

}
