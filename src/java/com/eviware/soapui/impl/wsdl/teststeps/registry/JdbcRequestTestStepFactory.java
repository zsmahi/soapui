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

package com.eviware.soapui.impl.wsdl.teststeps.registry;

import com.eviware.soapui.config.TestStepConfig;
import com.eviware.soapui.impl.wsdl.testcase.WsdlTestCase;
import com.eviware.soapui.impl.wsdl.teststeps.JdbcRequestTestStep;
import com.eviware.soapui.impl.wsdl.teststeps.WsdlTestStep;

/**
 * Factory for creation Delay steps
 * 
 * @author Ole.Matzura
 */

public class JdbcRequestTestStepFactory extends WsdlTestStepFactory
{
	public static final String JDBC_TYPE = "jdbc";

	public JdbcRequestTestStepFactory()
	{
		super( JDBC_TYPE, "JDBC Request", "Jdbc Request returns xml result", "/jdbc_request.gif" );
	}

	public WsdlTestStep buildTestStep( WsdlTestCase testCase, TestStepConfig config, boolean forLoadTest )
	{
		return new JdbcRequestTestStep( testCase, config, forLoadTest );
	}

	public TestStepConfig createNewTestStep( WsdlTestCase testCase, String name )
	{
		TestStepConfig testStepConfig = TestStepConfig.Factory.newInstance();
		testStepConfig.setType( JDBC_TYPE );
		testStepConfig.setName( name );
		return testStepConfig;
	}

	public boolean canCreate()
	{
		return true;
	}
}