package utility;

public class IntersectUtil{

	/**
	 * <p>
	 * Determine if a light ray and a line segment intersect.
	 * </p>
	 * 
	 * @see <a href="https://stackoverflow.com/a/565282/764951">Two line segments intersect</a>
	 * @see <a href="https://ncase.me/sight-and-light/">Sight and Light</a>
	 * 
	 * @param result - must be an array of size 4, this array will hold in order x, y, distance of intersect point from start and point scaler on line segment
	 * @param rsx - light ray start x
	 * @param rsy - light ray start y
	 * @param rex - light ray end x
	 * @param rey - light ray end y
	 * @param ssx - line segment start x
	 * @param ssy - line segment start y
	 * @param sex - line segment end x
	 * @param sey - line segment end y
	 * @return true if intersect and data stored in {@link AnimatorSingleRay#RESULT} array else false.
	 */
	public static boolean getIntersection( double[] result, double rsx, double rsy, double rex, double rey, double ssx, double ssy, double sex, double sey){
		// given 2 line segments as vectors their intersect will q + tr or p + us where
		// q and p are the starting point in from of (x, y),
		// r and s are the distance of end point to start point in form of ( x2-x1, y2-y1),
		// t and u are scaler values belonging to real numbers, such as 0.5, 1, -1.
		// by finding t and u the intersect can be found.
		// t and u can be found by equaling q + tr = p + us
		// this function can be refactored as below, look at the link in documentation for more details.
		// x is cross product
		// t = (q - p) x s / (r x s)
		// u = (q - p) x r / (r x s)
		// (q - p) x s = ((qx-px)sy-sx(qy-py))
		// (q - p) x r = ((qx-px)ry-rx(qy-py))
		// (r x s) = (rxsy-sxry)

		double qpx = rsx - ssx;
		double qpy = rsy - ssy;

		double rx = rex - rsx;
		double ry = rey - rsy;
		double sx = sex - ssx;
		double sy = sey - ssy;

		double qps = qpx * sy - sx * qpy;
		double qpr = qpx * ry - rx * qpy;

		double rs = rx * sy - sx * ry;

		double rayScaler = -qps / rs;
		double segmentScaler = -qpr / rs;

		result[0] = rsx + rx * rayScaler;
		result[1] = rsy + ry * rayScaler;
		result[2] = rayScaler;
		result[3] = segmentScaler;

		return rs != 0 && rayScaler >= 0 && segmentScaler >= 0 && segmentScaler <= 1;
	}
}
