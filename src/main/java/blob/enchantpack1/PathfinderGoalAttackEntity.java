package blob.enchantpack1;

import java.util.EnumSet;

import org.bukkit.event.entity.EntityTargetEvent;

import net.minecraft.world.entity.EntityInsentient;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.ai.goal.PathfinderGoal;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalTarget;
import net.minecraft.world.entity.ai.targeting.PathfinderTargetCondition;

public class PathfinderGoalAttackEntity extends PathfinderGoalTarget {
	  
	private final EntityLiving b;
	  
	public PathfinderGoalAttackEntity(EntityInsentient entity, EntityInsentient target) {
		super(entity, false);
		this.b = target;
		a(EnumSet.of(PathfinderGoal.Type.d));
	}

	@Override
	public boolean a() {
		if (this.b == null) return false; 
	    return (a(this.b, PathfinderTargetCondition.a));
	}
	
	public void c() {
	    this.e.setTarget(this.b, EntityTargetEvent.TargetReason.TARGET_ATTACKED_OWNER, true);
	    super.c();
	  }

}
