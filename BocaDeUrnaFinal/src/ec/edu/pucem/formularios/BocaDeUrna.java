package ec.edu.pucem.formularios;

import javax.swing.JInternalFrame;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import ec.edu.pucem.dominios.Prefecto;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JButton;

public class BocaDeUrna extends JInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	private JTable table;
	private DefaultTableModel model;

	private List<Prefecto> prefectos;
	private JPanel panel;
	private JButton btnCancelar;

	public BocaDeUrna(List<Prefecto> prefectos) {
		this.prefectos = prefectos;
		setTitle("BOCA DE URNA - REGISTRO");
		setBounds(100, 100, 600, 427);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 115, 566, 224);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(model.getValueAt(0, 0));
			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Nombre", "Votos" }));
		scrollPane.setViewportView(table);

		panel = new JPanel();
		panel.setBounds(12, 12, 566, 84);
		getContentPane().add(panel);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(157, 351, 117, 25);
		getContentPane().add(btnCancelar);

		model = (DefaultTableModel) table.getModel();
		cargarCandidatos();
		llenarTabla();
	}

	private void cargarCandidatos() {
		int x = 0;
		for (Prefecto prefecto : prefectos) {
			JButton btnPrefecto = new JButton(prefecto.getNombre());
			btnPrefecto.setBounds(x * 155, 0, 150, 80);
			btnPrefecto.addActionListener(this);
			panel.setLayout(new FlowLayout());
			panel.add(btnPrefecto);
			x++;
		}
	}

	private void llenarTabla() {
		model.setRowCount(0);
		for (Prefecto prefecto : prefectos) {
			Object[] fila = new Object[2];
			fila[0] = prefecto.getNombre();
			fila[1] = prefecto.getVotos();
			model.addRow(fila);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancelar) {
            dispose();
        }
        String textoBotonPulsado = e.getActionCommand();
        for (Prefecto prefecto : prefectos) {
            if (textoBotonPulsado == prefecto.getNombre()) {
                prefecto.setVotos(prefecto.getVotos() + 1);
                llenarTabla();
                break;
            }
        }

    }

	public List<Prefecto> getPrefectos() {
		return prefectos;
	}

	public void setPrefectos(List<Prefecto> prefectos) {
		this.prefectos = prefectos;
	}
}
