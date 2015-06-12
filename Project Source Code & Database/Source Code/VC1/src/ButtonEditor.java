import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
  
public class ButtonEditor extends DefaultCellEditor {
  protected JButton button;
  private String    label;
  private boolean   isPushed;
  private Request rqst;
  JTable table = Request.table;
  public void setReq(Request r)
  {
	  rqst = r;
  }
  public ButtonEditor(JCheckBox checkBox) {
    super(checkBox);
    button = new JButton();
    button.setOpaque(true);
    button.setText("Onayla");
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
    	  System.out.println("Girmiyor.");
    	  
    	  fireEditingStopped();
        
        /*int selectedRowIndex = table.getSelectedRow();
        int selectedColumnIndex = table.getSelectedColumn();
        String selectedObject = (String) table.getModel().getValueAt(selectedRowIndex, selectedColumnIndex);
        
        System.out.println("selectedRowIndex: " + selectedRowIndex);
        System.out.println("selectedColumnIndex: " + selectedColumnIndex);
        System.out.println("selectedObject: " + selectedObject);*/
      }
    });
  }
  
  
  
  public Component getTableCellEditorComponent(JTable table, Object value,
                   boolean isSelected, int row, int column) {
     //int[] array = table.getSelectedRows();
     
	  System.out.println("row: " + row + "column: " + column);
	  rqst.getIndexOfCell(row, column);
	  
	  if (isSelected) {
      button.setForeground(table.getSelectionForeground());
      button.setBackground(table.getSelectionBackground());
    } else{
      button.setForeground(table.getForeground());
      button.setBackground(table.getBackground());
    }
    label = (value ==null) ? "" : value.toString();
    button.setText( label );
    isPushed = true;

    if (isPushed)  {
    	button.setEnabled(false);
    	int idx = table.getSelectedRow();
  	  System.out.println("idex: " + idx);
        JOptionPane.showMessageDialog(null, "Kullanýcý listesine eklendi." + idx);
      }
    
    
    return button;
  }
  
  public Object getCellEditorValue() {
    if (isPushed)  {
      JOptionPane.showMessageDialog(null, "Kullanýcý listesine eklendi.");
    }
    isPushed = false;
    return new String( label ) ;
  }
    
  public boolean stopCellEditing() {
    isPushed = false;
    return super.stopCellEditing();
  }
  
  protected void fireEditingStopped() {
    super.fireEditingStopped();
  }
}