package blob.enchantpack1;

import java.util.EnumSet;

import org.bukkit.event.entity.EntityTargetEvent;

import net.minecraft.world.entity.EntityCreature;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.ai.goal.PathfinderGoal;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalTarget;
import net.minecraft.world.entity.ai.targeting.PathfinderTargetCondition;

public class ParentHurtByTarget extends PathfinderGoalTarget {
	  
	  private EntityLiving b;
	  
	  private EntityLiving p;
	  
	  private int c;
	  
	  public ParentHurtByTarget(EntityCreature cwnsm, EntityLiving parent) {
	    super(cwnsm, false);
	    this.p = parent;
	    a(EnumSet.of(PathfinderGoal.Type.d));
	  }
	  
	  public boolean a() {
	      EntityLiving entityliving = this.p;
	      if (entityliving == null)
	        return false; 
	      this.b = entityliving.dM();
	      int i = entityliving.dN();
	      return (i != this.c && a(this.b, PathfinderTargetCondition.a));
	  }
	  
	  public void c() {
	    this.e.setTarget(this.b, EntityTargetEvent.TargetReason.TARGET_ATTACKED_OWNER, true);
	    EntityLiving entityliving = this.p;
	    if (entityliving != null)
	      this.c = entityliving.dN(); 
	    super.c();
	  }
	}
