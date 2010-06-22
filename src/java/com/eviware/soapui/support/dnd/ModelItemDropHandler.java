/*
 *  soapUI, copyright (C) 2004-2010 eviware.com 
 *
 *  soapUI is free software; you can redistribute it and/or modify it under the 
 *  terms of version 2.1 of the GNU Lesser General Public License as published by 
 *  the Free Software Foundation.
 *
 *  soapUI is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 *  even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 *  See the GNU Lesser General Public License for more details at gnu.org.
 */

package com.eviware.soapui.support.dnd;

public interface ModelItemDropHandler<T>
{
	public boolean canDrop( T source, T target, int dropAction, int dropType );

	public boolean drop( T source, T target, int dropAction, int dropType );

	public String getDropInfo( T sourceModelItem, T targetModelItem, int dropAction, int dropType );
}