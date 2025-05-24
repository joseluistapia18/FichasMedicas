
package com.fichas_medicas.components;


import com.fichas_medicas.dao.CrudArea;
import com.fichas_medicas.dao.CrudPersona;
import com.fichas_medicas.domain.FichaMedica;
import java.awt.Font;
import java.util.List;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

public class TablasTabSummary {
    DefaultTableModel modelo;
    
    public DefaultTableModel vaciarTabla(JTable tabla){
        DefaultTableModel modelo1 = (DefaultTableModel) tabla.getModel();
        while (modelo1.getRowCount() > 0){
            modelo1.removeRow(0);            
        }
        return modelo1;
    } 
    

public void getTabSummary(List<FichaMedica> lista1, JTable tabla) {
    int[] anchoColumnas = {40, 80, 150};
    String[] columnas = {"#", "Fecha", "Antecedentes Patológicos Personales"};

    // Crear modelo
    modelo = new DefaultTableModel(columnas, 0);

    // Llenar datos
    for (int i = 0; i < lista1.size(); i++) {
        String[] fila = new String[3];
        fila[0] = lista1.get(i).getId_fichaMedica()+"";
        fila[1] = FechaComponente.getStringFecha(lista1.get(i).getFecha_registro());
        fila[2] = lista1.get(i).getAnt_patologicos_per();
        modelo.addRow(fila);
    }

    tabla.setModel(modelo);

    // Ajustes visuales
    tabla.setRowHeight(30);
    tabla.setFont(new Font("Arial", Font.PLAIN, 13));

    JTableHeader header = tabla.getTableHeader();
    header.setFont(new Font("Arial", Font.BOLD, 15));
    DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
    headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

    DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
    centrado.setHorizontalAlignment(SwingConstants.CENTER);

    for (int i = 0; i < columnas.length; i++) {
        tabla.getColumnModel().getColumn(i).setPreferredWidth(anchoColumnas[i]);
        tabla.getColumnModel().getColumn(i).setCellRenderer(centrado);
    }

    tabla.setShowGrid(true);
}



    
public void filter(String valor, JTable tabla) {
    if (tabla.getModel() instanceof DefaultTableModel m) {
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(m);
        tabla.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter("(?i)" + valor));
    }
}

}
