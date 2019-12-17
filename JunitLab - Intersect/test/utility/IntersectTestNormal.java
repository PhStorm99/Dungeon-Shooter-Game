package utility;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utility.IntersectUtil;

class IntersectTestNormal{

	private boolean doesIntersect;
	private double[] intersect;

	@BeforeEach
	void setUp() throws Exception{
		intersect = new double[4];
	}

	@Test
	public void testABAD(){
		// doesIntersect = IntersectUtil.getIntersection( intersect, 100, 100, 200, 100, 100, 100, 100, 200);
		//intersect array is used as buffer for result. we pass it as argument to prevent remake of array.
		//instead use the same array over and over again.
		//call assertTrue if doesIntersect is expected to be true or assertFalse if expected to be false
		//user assertEquals to compare indexes 0, 1 and 2 to expected data in the pdf or csv.
		//make sure to use assertEquals that takes delta as third argument when comparing double.
		//intersect[0] is px which is the x of intersect point
		//intersect[1] is py which is the y of intersect point
		//intersect[2] is ray scalar which is the scaler distance of ray to segment line.
		//ray is the first 4 numbers and segment is the next 4 numbers.
		//ray has starting point and end point is just direction the length of ray is infinite from start toward end.
		//segment start and end are set and length of it is limited to what is given.
		 doesIntersect = IntersectUtil.getIntersection( intersect, 100, 100, 200, 100, 100, 100, 100, 200);
	      //  intersect = IntersectUtil.intersect();
	        assertTrue(doesIntersect);
	        assertEquals(intersect[0], 100, 0.00001);
	        assertEquals(intersect[1], 100, 0.00001);
	        assertEquals(intersect[2], 0, 0.00001);
	        
	        
	}
	
	@Test
	public void testABFH()
	{
		 doesIntersect = IntersectUtil.getIntersection( intersect, 100, 100, 200, 100, 150, 100, 150, 200);
	      //  intersect = IntersectUtil.intersect();
	        assertTrue(doesIntersect);
	        assertEquals(intersect[0], 150, 0.00001);
	        assertEquals(intersect[1], 100, 0.00001);
	        assertEquals(intersect[2], 0.5, 0.00001);
	}
	
	@Test
	public void testABBC()
	{
		 doesIntersect = IntersectUtil.getIntersection( intersect, 100, 100, 200, 100, 200, 100, 200, 200);
	      //  intersect = IntersectUtil.intersect();
	        assertTrue(doesIntersect);
	        assertEquals(intersect[0], 200, 0.00001);
	        assertEquals(intersect[1], 100, 0.00001);
	        assertEquals(intersect[2], 1, 0.00001);
	}
	
	@Test
	public void testBAAD()
	{
		 doesIntersect = IntersectUtil.getIntersection( intersect, 200, 100, 100, 100, 100, 100, 100, 200);
	      //  intersect = IntersectUtil.intersect();
	        assertTrue(doesIntersect);
	        assertEquals(intersect[0], 100, 0.00001);
	        assertEquals(intersect[1], 100, 0.00001);
	        assertEquals(intersect[2], 1, 0.00001);
	}
	
	@Test
	public void testBAFH()
	{
		doesIntersect = IntersectUtil.getIntersection( intersect,200, 100, 100, 100, 150, 100, 150, 200);
		 assertTrue(doesIntersect);
	        assertEquals(intersect[0], 150, 0.00001);
	        assertEquals(intersect[1], 100, 0.00001);
	        assertEquals(intersect[2], 0.5, 0.00001);
	}
	
	@Test
	public void testBABC()
	{
		   doesIntersect = IntersectUtil.getIntersection(intersect,200, 100, 100, 100, 200, 100, 200, 200);
	      
	        assertTrue(doesIntersect);
	        assertEquals(intersect[0], 200, 0.00001);
	        assertEquals(intersect[1], 100, 0.00001);
	        assertEquals(intersect[2], 0.0, 0.00001);
	}
	
	
	@Test
    public void  testABID() {
         doesIntersect = IntersectUtil.getIntersection(intersect,100, 100, 200, 100, 100, 150, 100, 200);
        
        assertFalse(doesIntersect);

    }

    @Test
    public void  testABEH() {
         doesIntersect = IntersectUtil.getIntersection(intersect,100, 100, 200, 100, 150, 150, 150, 200);
        assertFalse(doesIntersect);
    }

    @Test
    public void  testABGC() {
         doesIntersect = IntersectUtil.getIntersection(intersect,100, 100, 200, 100, 200, 150, 200, 200);
        assertFalse(doesIntersect);
    }

    @Test
    public void  testABIG() {
         doesIntersect = IntersectUtil.getIntersection(intersect,100, 100, 200, 100, 100, 150, 200, 150);
        assertFalse(doesIntersect);
    }

    @Test
    public void  testBAID() {
         doesIntersect = IntersectUtil.getIntersection(intersect,200, 100, 100, 100, 100, 150, 100, 200);
        assertFalse(doesIntersect);
    }

    @Test
    public void  testBAEH() {
         doesIntersect = IntersectUtil.getIntersection(intersect,200, 100, 100, 100, 150, 150, 150, 200);
        assertFalse(doesIntersect);
    }

    @Test
    public void testBAGC() {
         doesIntersect = IntersectUtil.getIntersection(intersect,200, 100, 100, 100, 200, 150, 200, 200);
         assertFalse(doesIntersect);
    }

    @Test
    public void testBAIG() {
         doesIntersect = IntersectUtil.getIntersection(intersect,200, 100, 100, 100, 100, 150, 200, 150);
        assertFalse(doesIntersect);
    }

    @Test
    public void testAFAD() {
         doesIntersect = IntersectUtil.getIntersection(intersect,100, 100, 150, 100, 100, 100, 100, 200);
        assertTrue(doesIntersect);
        assertEquals(intersect[0], 100, 0.00001);
        assertEquals(intersect[1], 100, 0.00001);
        assertEquals(intersect[2], 0.0, 0.00001);
    }

    @Test
    public void testAFFH() {
         doesIntersect = IntersectUtil.getIntersection(intersect,100, 100, 150, 100, 150, 100, 150, 200);
        assertTrue(doesIntersect);
        assertEquals(intersect[0], 150, 0.00001);
        assertEquals(intersect[1], 100, 0.00001);
        assertEquals(intersect[2], 1.0, 0.00001);
    }

    @Test
    public void testAFBC() {
         doesIntersect = IntersectUtil.getIntersection(intersect,100, 100, 150, 100, 200, 100, 200, 200);
        assertTrue(doesIntersect);
        assertEquals(intersect[0], 200, 0.00001);
        assertEquals(intersect[1], 100, 0.00001);
        assertEquals(intersect[2], 2.0, 0.00001);
    }

    @Test
    public void testFAAD() {
         doesIntersect = IntersectUtil.getIntersection(intersect,150, 100, 100, 100, 100, 100, 100, 200);
        assertTrue(doesIntersect);
        assertEquals(intersect[0], 100, 0.00001);
        assertEquals(intersect[1], 100, 0.00001);
        assertEquals(intersect[2], 1.0, 0.00001);
    }

    @Test
    public void testFAFH() {
         doesIntersect = IntersectUtil.getIntersection(intersect,150, 100, 100, 100, 150, 100, 150, 200);
        assertTrue(doesIntersect);
        assertEquals(intersect[0], 150, 0.00001);
        assertEquals(intersect[1], 100, 0.00001);
        assertEquals(intersect[2], 0.0, 0.00001);
    }

    @Test
    public void testFABC() {
         doesIntersect = IntersectUtil.getIntersection(intersect,150, 100, 100, 100, 200, 100, 200, 200);
        assertFalse(doesIntersect);
    }

    
    
	
	
}
