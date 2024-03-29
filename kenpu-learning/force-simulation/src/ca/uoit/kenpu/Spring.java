package ca.uoit.kenpu;

import android.graphics.PointF;

public class Spring {
	public static int ID = 0;
	public int id;
	public float k;
	public float l0;
	public float f; // Current force in the spring
	public Particle x1, x2;
	
	public Spring(float k, float l0) {
		this.k = k;
		this.l0 = l0;
		this.id = (Spring.ID ++);
	}
	
	public Spring connect(Particle x1, Particle x2) {
		this.x1 = x1;
		this.x2 = x2;
		this.x1.conn.add(this);
		this.x2.conn.add(this);
		this.update();
		return this;
	}
	
	/**
	 * Compute the current force (scalar) in the spring
	 */
	public void update() {
		this.f = (Particle.distance(x1, x2) - l0) * k;
	}
	
	public PointF forceOn(Particle x) {
		if(x != x1 && x != x2) {
			return new PointF(0,0);
		}
		PointF F = (x == x1) ? (Particle.unit(x1, x2)) : (Particle.unit(x2, x1));
		Particle.Multiply(F, f);
		return F;
	}
}
