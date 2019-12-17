package raycast.entity;

import raycast.entity.property.Drawable;

/**
 * This is an Entity interface which has a drawable method just declared. no
 * body in it.
 * 
 * @author hnpav
 * @version Nov 02,2019
 */
public interface Entity {

	public Drawable<?> getDrawable();

	public boolean isDrawable();

}
