package com.caved_in.dynamicquests.configuration;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

public class XmlLocation
{
	@Element(name = "X")
	private double X = 0.0;

	@Element(name = "Y")
	private double Y = 0.0;

	@Element(name = "Z")
	private double Z = 0.0;

	@Element(name = "radius")
	private double locationRadius = 3.0;

	@Element(name = "world")
	private String worldName = "PaganTemplar";

	public XmlLocation(@Element(name = "X")double X,
					   @Element(name = "Y")double Y,
					   @Element(name = "Z")double Z,
					   @Element(name = "radius")double locationRadius,
					   @Element(name = "world")String worldName
	)
	{
		this.X = X;
		this.Y = Y;
		this.Z = Z;
		this.locationRadius = locationRadius;
		this.worldName = worldName;
	}

	public XmlLocation(Location location, double locationRadius)
	{
		this.X = location.getX();
		this.Y = location.getY();
		this.Z = location.getZ();
		this.worldName = location.getWorld().getName();
		this.locationRadius = locationRadius;
	}

	public XmlLocation()
	{

	}

	public double getX()
	{
		return X;
	}

	public double getY()
	{
		return Y;
	}

	public double getZ()
	{
		return Z;
	}

	public double getLocationRadius()
	{
		return locationRadius;
	}

	public String getWorldName()
	{
		return worldName;
	}

	public Location getLocation()
	{
		return new Location(Bukkit.getWorld(this.worldName),getX(),getY(),getZ());
	}
}
