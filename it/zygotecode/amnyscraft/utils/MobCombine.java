package it.zygotecode.amnyscraft.utils;

public class MobCombine
{
	private int entityId;
	private double damage;
	private String customMobName;
	
	public MobCombine(int entityId, double damage, String customMobName)
	{
		this.entityId = entityId;
		this.damage = damage;
		this.customMobName = customMobName;
	}

	public int getEntityId()
	{
		return entityId;
	}

	public void setEntityId(int entityId)
	{
		this.entityId = entityId;
	}

	public double getDamage()
	{
		return damage;
	}

	public void setDamage(double damage)
	{
		this.damage = damage;
	}

	public String getCustomMobName()
	{
		return customMobName;
	}

	public void setCustomMobName(String customMobName)
	{
		this.customMobName = customMobName;
	}
}