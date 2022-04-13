package blob.enchantpack1;

import java.util.EnumSet;

import org.bukkit.event.entity.EntityTargetEvent;

import net.minecraft.world.entity.EntityCreature;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.ai.goal.PathfinderGoal;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalTarget;
import net.minecraft.world.entity.ai.targeting.PathfinderTargetCondition;

public class ParentHurtTarget extends PathfinderGoalTarget {
	  
	  private EntityLiving b;
	  
	  private EntityLiving p;
	  
	  private int c;
	  
	  public ParentHurtTarget(EntityCreature cwnsm, EntityLiving parent) {
	    super(cwnsm, false);
	    this.p = parent;
	    a(EnumSet.of(PathfinderGoal.Type.d));
	  }
	  
	  public boolean a() {
	      EntityLiving entityliving = this.p;
	      if (entityliving == null)
	        return false; 
	      this.b = entityliving.dO();
	      int i = entityliving.dP();
	      return (i != this.c && a(this.b, PathfinderTargetCondition.a));
	  }
	  
	  public void c() {
	    this.e.setTarget(this.b, EntityTargetEvent.TargetReason.OWNER_ATTACKED_TARGET, true);
	    EntityLiving entityliving = this.p;
	    if (entityliving != null)
	      this.c = entityliving.dP(); 
	    super.c();
	  }
	}
