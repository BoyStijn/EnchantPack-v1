package blob.enchantpack1;

import java.util.EnumSet;

import net.minecraft.core.BaseBlockPosition;
import net.minecraft.core.BlockPosition;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.ai.goal.PathfinderGoal;
import net.minecraft.world.entity.animal.EntityAnimal;
import net.minecraft.world.level.IBlockAccess;
import net.minecraft.world.level.IWorldReader;
import net.minecraft.world.level.block.state.IBlockData;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.pathfinder.PathfinderNormal;

public class ParentFollow  extends PathfinderGoal {
	  public static final int a = 12;
	  
	  private static final int b = 2;
	  
	  private static final int c = 3;
	  
	  private static final int d = 1;
	  
	  private final EntityAnimal e;
	  
	  private EntityLiving f;
	  
	  private EntityLiving p;
	  
	  private final IWorldReader g;
	  
	  private final double h;
	  
	  private int j;
	  
	  private final float k;
	  
	  private final float l;
	  
	  private float m;
	  
	  private final boolean n;
	  
	  public ParentFollow(EntityAnimal entitytameableanimal, double d0, float f, float f1, boolean flag, EntityLiving P) {
	    this.e = entitytameableanimal;
	    this.g = (IWorldReader)entitytameableanimal.s;
	    this.h = d0;
	    this.l = f;
	    this.k = f1;
	    this.n = flag;
	    this.p = P;
	    a(EnumSet.of(PathfinderGoal.Type.a, PathfinderGoal.Type.b));
	  }
	  
	  public boolean a() {
	    EntityLiving entityliving = this.p;
	    if (entityliving == null)
	      return false; 
	    if (entityliving.B_())
	      return false; 
	    if (this.e.f((Entity)entityliving) < (this.l * this.l))
	      return false; 
	    this.f = entityliving;
	    return true;
	  }
	  
	  public boolean b() {
	    return this.e.D().m() ? false : ((this.e.f((Entity)this.f) > (this.k * this.k)));
	  }
	  
	  public void c() {
	    this.j = 0;
	    this.m = this.e.a(PathType.i);
	    this.e.a(PathType.i, 0.0F);
	  }
	  
	  public void d() {
	    this.f = null;
	    //this.e.D().o();
	    this.e.a(PathType.i, this.m);
	  }
	  
	  public void e() {
	    this.e.z().a((Entity)this.f, 10.0F, this.e.T());
	    if (--this.j <= 0) {
	      this.j = a(10);
	      if (!this.e.fq() && !this.e.bF())
	        if (this.e.f((Entity)this.f) >= 144.0D) {
	          g();
	        } else {
	          this.e.D().a((Entity)this.f, this.h);
	        }  
	    } 
	  }
	  
	  private void g() {
	    BlockPosition blockposition = this.f.cW();
	    for (int i = 0; i < 10; i++) {
	      int j = a(-3, 3);
	      int k = a(-1, 1);
	      int l = a(-3, 3);
	      boolean flag = a(blockposition.u() + j, blockposition.v() + k, blockposition.w() + l);
	      if (flag)
	        return; 
	    } 
	  }
	  
	  private boolean a(int i, int j, int k) {
	    if (Math.abs(i - this.f.dc()) < 2.0D && Math.abs(k - this.f.di()) < 2.0D)
	      return false; 
	    if (!a(new BlockPosition(i, j, k)))
	      return false; 
	    //this.e.D().o();
	    return true;
	  }
	  
	  private boolean a(BlockPosition blockposition) {
	    PathType pathtype = PathfinderNormal.a((IBlockAccess)this.g, blockposition.i());
	    if (pathtype != PathType.c)
	      return false; 
	    IBlockData iblockdata = this.g.a_(blockposition.c());
	    if (!this.n && iblockdata.b() instanceof net.minecraft.world.level.block.BlockLeaves)
	      return false; 
	    BlockPosition blockposition1 = blockposition.b((BaseBlockPosition)this.e.cW());
	    return this.g.a((Entity)this.e, this.e.cw().a(blockposition1));
	  }
	  
	  private int a(int i, int j) {
	    return this.e.dL().nextInt(j - i + 1) + i;
	  }
}