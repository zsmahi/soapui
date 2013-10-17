/*
 *  soapUI, copyright (C) 2004-2013 smartbear.com
 *
 *  soapUI is free software; you can redistribute it and/or modify it under the 
 *  terms of version 2.1 of the GNU Lesser General Public License as published by 
 *  the Free Software Foundation.
 *
 *  soapUI is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 *  even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 *  See the GNU Lesser General Public License for more details at gnu.org.
 */

package com.eviware.soapui.impl.rest.panels.resource;

import com.eviware.soapui.impl.rest.RestRequest;
import com.eviware.soapui.impl.rest.support.RestParamProperty;
import com.eviware.soapui.impl.rest.support.RestParamsPropertyHolder;
import com.eviware.soapui.impl.rest.support.RestParamsPropertyHolder.ParameterStyle;
import com.eviware.soapui.impl.wsdl.panels.teststeps.support.DefaultPropertyTableHolderModel;
import com.eviware.soapui.model.ModelItem;

import static com.eviware.soapui.impl.rest.actions.support.NewRestResourceActionBase.ParamLocation;

public class RestParamsTableModel extends DefaultPropertyTableHolderModel<RestParamsPropertyHolder>
{
	public static final int PARAM_LOCATION_COLUMN_INDEX = 3;
	static String[] COLUMN_NAMES = new String[] { "Name", "Default value", "Style", "Level" };
	static Class[] COLUMN_TYPES = new Class[] { String.class, String.class, ParameterStyle.class, ParamLocation.class };

	public RestParamsTableModel( RestParamsPropertyHolder params )
	{
		super( params );

		ModelItem parametersOwner = params.getModelItem();
		if( parametersOwner != null )
		{
			parametersOwner.addPropertyChangeListener( this );
		}

	}

	@Override
	public int getColumnCount()
	{
		return 4;
	}

	@Override
	public String getColumnName( int columnIndex )
	{
		if( isColumnIndexOutOfBound( columnIndex ) )
		{
			return null;
		}
		return COLUMN_NAMES[columnIndex];
	}

	@Override
	public Class<?> getColumnClass( int columnIndex )
	{
		if( isColumnIndexOutOfBound( columnIndex ) )
		{
			return null;
		}
		return COLUMN_TYPES[columnIndex];
	}

	private boolean isColumnIndexOutOfBound( int columnIndex )
	{
		return ( columnIndex < 0 ) || ( columnIndex > 3 );
	}

	@Override
	public boolean isCellEditable( int rowIndex, int columnIndex )
	{
		return true;
	}

	public ParamLocation getParamLocationAt( int rowIndex )
	{
		return ( ParamLocation )getValueAt( rowIndex, PARAM_LOCATION_COLUMN_INDEX );
	}


	@Override
	public Object getValueAt( int rowIndex, int columnIndex )
	{
		RestParamProperty prop = getParameterAt( rowIndex );

		switch( columnIndex )
		{
			case 0:
				return prop.getName();
			case 1:
				return prop.getValue();
			case 2:
				return prop.getStyle();
			case 3:
				return prop.getParamLocation();
		}

		return null;
	}

	@Override
	public void setValueAt( Object value, int rowIndex, int columnIndex )
	{
		RestParamProperty prop = getParameterAt( rowIndex );

		switch( columnIndex )
		{
			case 0:
				if( propertyExists( value, prop ) )
				{
					return;
				}

				params.renameProperty( prop.getName(), value.toString() );
				return;
			case 1:
				//if( !prop.getParamLocation().equals( ParamLocation.REQUEST ) )
				//{
				prop.setDefaultValue( value.toString() );
				//}
				prop.setValue( value.toString() );
				return;
			case 2:
				prop.setStyle( ( ParameterStyle )value );
				return;
			case 3:
				if( params.getModelItem() != null && params.getModelItem() instanceof RestRequest )
				{
					this.isLastChangeParameterLevelChange = true;
				}
				prop.setParamLocation( ( ParamLocation )value );
				return;
		}
	}

	public RestParamProperty getParameterAt( int selectedRow )
	{
		return ( RestParamProperty )super.getPropertyAtRow( selectedRow );
	}

	public ParameterStyle[] getParameterStylesForEdit()
	{
		return new ParameterStyle[] { ParameterStyle.QUERY, ParameterStyle.TEMPLATE, ParameterStyle.HEADER,
				ParameterStyle.MATRIX, ParameterStyle.PLAIN };
	}

	public ParamLocation[] getParameterLevels()
	{
		return ParamLocation.values();
	}

	public void setParams( RestParamsPropertyHolder params )
	{
		this.params.removeTestPropertyListener( testPropertyListener );
		this.params = params;
		this.params.addTestPropertyListener( testPropertyListener );

		buildParamNameIndex( params );
		fireTableDataChanged();
	}

	public void removeProperty( String propertyName )
	{
		params.remove( propertyName );
	}


}
