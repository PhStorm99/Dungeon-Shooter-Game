package dungeonshooter.entity;

import dungeonshooter.entity.property.Drawable;
import dungeonshooter.entity.property.HitBox;

/**
 * This is an Entity interface which has a drawable method just declared. no
 * body in it.
 * 
 * @author Harsh Patel
 * @version Dec 06, 2019
 * @since 1.8
 */
public interface Entity {

	public void update();
	
	public boolean hasHitbox();
	
	public Drawable<?> getDrawable();

	public boolean isDrawable();
	
	public HitBox getHitBox();

}
