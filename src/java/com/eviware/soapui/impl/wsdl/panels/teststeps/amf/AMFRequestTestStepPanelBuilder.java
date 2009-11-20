/*
 *  soapUI, copyright (C) 2004-2009 eviware.com 
 *
 *  soapUI is free software; you can redistribute it and/or modify it under the 
 *  terms of version 2.1 of the GNU Lesser General Public License as published by 
 *  the Free Software Foundation.
 *
 *  soapUI is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 *  even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 *  See the GNU Lesser General Public License for more details at gnu.org.
 */

package com.eviware.soapui.impl.wsdl.panels.teststeps.amf;

import java.awt.Component;

import com.eviware.soapui.impl.EmptyPanelBuilder;
import com.eviware.soapui.impl.wsdl.teststeps.AMFRequestTestStep;
import com.eviware.soapui.ui.desktop.DesktopPanel;

/**
 * PanelBuilder for AMFRequestTestStep
 * 
 * @author nebojsa.tasic
 */

public class AMFRequestTestStepPanelBuilder extends EmptyPanelBuilder<AMFRequestTestStep>
{
	public AMFRequestTestStepPanelBuilder()
	{
	}

	public DesktopPanel buildDesktopPanel( AMFRequestTestStep testStep )
	{
		return new AMFRequestTestStepDesktopPanel( testStep );
	}

	public boolean hasDesktopPanel()
	{
		return true;
	}
	@Override
	public Component buildOverviewPanel( AMFRequestTestStep modelItem )
	{
		return buildDefaultProperties( modelItem, "AMFRequestTestStep Properties" );
	}
}