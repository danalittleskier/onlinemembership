package org.ussa.beans;

public class MessageBean
{
	private String resourceKey;
	private Object[] params;


	public MessageBean(String resourceKey, Object param1)
	{
		this.resourceKey = resourceKey;
		this.params = new Object[] {param1};
	}

	public MessageBean(String resourceKey, Object param1, Object param2)
	{
		this.resourceKey = resourceKey;
		this.params = new Object[] {param1, param2};
	}

	public MessageBean(String resourceKey, Object[] params)
	{
		this.resourceKey = resourceKey;
		this.params = params;
	}

	public String getResourceKey()
	{
		return resourceKey;
	}

	public void setResourceKey(String resourceKey)
	{
		this.resourceKey = resourceKey;
	}

	public Object[] getParams()
	{
		return params;
	}

	public void setParams(Object[] params)
	{
		this.params = params;
	}
}
