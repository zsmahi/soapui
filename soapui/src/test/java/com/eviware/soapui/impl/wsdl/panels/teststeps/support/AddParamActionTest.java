/*
 * SoapUI, copyright (C) 2004-2013 smartbear.com
 *
 * SoapUI is free software; you can redistribute it and/or modify it under the
 * terms of version 2.1 of the GNU Lesser General Public License as published by
 * the Free Software Foundation.
 *
 * SoapUI is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details at gnu.org.
 */

package com.eviware.soapui.impl.wsdl.panels.teststeps.support;

import com.eviware.soapui.impl.rest.RestRequest;
import com.eviware.soapui.impl.rest.panels.resource.RestParamsTableModel;
import com.eviware.soapui.impl.rest.support.RestParamProperty;
import com.eviware.soapui.impl.rest.support.RestParamsPropertyHolder;
import com.eviware.soapui.support.SoapUIException;
import com.eviware.soapui.utils.ModelItemFactory;
import org.fest.assertions.ThrowableAssert;
import org.fest.swing.core.BasicRobot;
import org.fest.swing.data.TableCell;
import org.fest.swing.fixture.JTableFixture;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import static org.fest.swing.data.TableCell.row;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: Prakash
 * Date: 2013-10-18
 * Time: 11:06
 * To change this template use File | Settings | File Templates.
 */
public class AddParamActionTest
{
	public static final String PARAM = "Param";

	private JTable paramTable;
	private RestParamsPropertyHolder params;

	@Before
	public void setUp() throws SoapUIException
	{
		params = ModelItemFactory.makeRestRequest().getParams();
		paramTable = new JTable( new RestParamsTableModel( params ) );
		setCellEditorForNameAndValueColumns();
	}

	@Test
	public void testActionPerformed() throws Exception
	{
		invokeAddParamAction();

		JTableFixture jTableFixture = new JTableFixture(BasicRobot.robotWithNewAwtHierarchy(), paramTable );

		verifyEditingCell(0, 0);

		jTableFixture.cell( row( 0 ).column( 0 ) ).stopEditing();

		verifyEditingCell(0, 1);

		jTableFixture.cell( row( 0 ).column( 1 ) ).stopEditing();

	}

	private void setCellEditorForNameAndValueColumns()
	{
		paramTable.setDefaultEditor( String.class, new DefaultCellEditor( new JTextField() )
		{
			@Override
			public Object getCellEditorValue()
			{
				return PARAM;
			}
		} );
	}

	private void invokeAddParamAction()
	{
		ActionEvent actionEvent = Mockito.mock( ActionEvent.class );
		new AddParamAction( paramTable, params, "Add Param" ).actionPerformed( actionEvent );
	}

	private void verifyEditingCell(int row, int column)
	{
		assertThat( paramTable.getEditingRow(), is( row ) );
		assertThat( paramTable.getEditingColumn(), is( column ) );
	}
}
