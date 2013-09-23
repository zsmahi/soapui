package com.eviware.soapui.support.swing;

import com.eviware.soapui.support.UISupport;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

/**
 *  Factory class responsible for creation of JTable instances with a common style.
 */
public abstract class JTableFactory
{

	public abstract JTable makeJTable(TableModel tableModel);

	public static JTableFactory getInstance() {
		 return new DefaultJTableFactory();
	}

	private static class DefaultJTableFactory extends JTableFactory
	{
		@Override
		public JTable makeJTable( TableModel tableModel )
		{
			return UISupport.isMac() ? makeStripedTable( tableModel ) : new JTable( tableModel );
		}

		private JTable makeStripedTable( final TableModel tableModel )
		{
			JTable stripedTable = new JTable( tableModel )
			{
				@Override
				public Component prepareRenderer( TableCellRenderer renderer, int row, int column )
				{
					Component defaultRenderer = super.prepareRenderer( renderer, row, column );
					if( row % 2 == 1 )
					{
						defaultRenderer.setBackground( new Color( 241, 244, 247 ) );
					}
					else
					{
						defaultRenderer.setBackground( Color.WHITE );
					}
					return defaultRenderer;
				}

				@Override
				public boolean getShowVerticalLines()
				{
					return false;
				}
			};
			stripedTable.setShowGrid( false );
			stripedTable.setIntercellSpacing( new Dimension(0, 0) );
			return stripedTable;
		}
	}
}
