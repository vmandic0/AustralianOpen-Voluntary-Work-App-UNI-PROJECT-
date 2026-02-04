/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author vuk
 */
import domain.Prijava;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class PrijavaTableModel extends AbstractTableModel {
    private List<Prijava> lista;
    private final String[] kolone = {"Ime", "Prezime", "Pozicija", "Datum rada", "Smena", "STATUS"};

    public PrijavaTableModel(List<Prijava> lista) {
        this.lista = (lista != null) ? lista : new ArrayList<>();
    }

    @Override
    public int getRowCount() { return lista.size(); }

    @Override
    public int getColumnCount() { return kolone.length; }

    @Override
    public String getColumnName(int column) { return kolone[column]; }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Prijava p = lista.get(rowIndex);
        switch (columnIndex) {
            case 0: return p.getIme();
            case 1: return p.getPrezime();
            case 2: return p.getPozicija();
            case 3: return p.getDatumVol(); // LocalDate
            case 4: return p.getSmena();
            case 5: return p.getStatus(); // Ovde se izvršava tvoja logika sa satima
            default: return null;
        }
    }

    public void osveziPodatke(List<Prijava> novaLista) {
        this.lista = novaLista;
        fireTableDataChanged();
    }
    public Prijava getPrijavaAt(int row) {
        return lista.get(row);
    }
}
