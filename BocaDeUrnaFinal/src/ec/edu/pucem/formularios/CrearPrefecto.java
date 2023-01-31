package ec.edu.pucem.formularios;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import ec.edu.pucem.dominios.Prefecto;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class CrearPrefecto extends JInternalFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;

	private Prefecto prefecto;
	private JTable table;
	private DefaultTableModel model;
	private JButton btnGuardar;
	private JButton btnNuevo;
	private JButton btnCancelar;

	private List<Prefecto> prefectos;
	private int idPrefecto;
	
	public CrearPrefecto(List<Prefecto> prefectos) {
		idPrefecto=1;
		this.prefectos=prefectos;
		setTitle("CANDIDATOS A PREFECTO");
		setBounds(100, 100, 443, 385);
		getContentPane().setLayout(null);

		JLabel lblNombres = new JLabel("Nombres");
		lblNombres.setBounds(30, 25, 70, 15);
		getContentPane().add(lblNombres);

		txtNombre = new JTextField();
		txtNombre.addActionListener(this);
		txtNombre.setBounds(97, 23, 231, 19);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);

		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(this);
		btnNuevo.setBounds(30, 72, 117, 25);
		getContentPane().add(btnNuevo);

		btnGuardar = new JButton("Agregar");
		btnGuardar.addActionListener(this);
		btnGuardar.setBounds(157, 72, 117, 25);
		getContentPane().add(btnGuardar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(286, 72, 117, 25);
		getContentPane().add(btnCancelar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 115, 416, 224);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(model.getValueAt(0, 0));
			}
		});
		
		txtNombre.addKeyListener(new KeyAdapter() {
			  public void keyPressed(KeyEvent e) {
			    if (e.getKeyCode() == KeyEvent.VK_ENTER) {    
			      String nombre = txtNombre.getText();
			      DefaultTableModel model = (DefaultTableModel) table.getModel();
			      boolean nombreExistente = false;
			      for (int i = 0; i < model.getRowCount(); i++) {
			        if (model.getValueAt(i, 0).equals(nombre)) {
			          nombreExistente = true;
			          break;
			        }
			      }
			      if (!nombreExistente) {
			        model.addRow(new Object[]{nombre});
			        agregarPrefecto(); 
			      } else {
			        JOptionPane.showMessageDialog(null, "Ese nombre ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
			      }
			    }
			  }
			});

		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Nombre" }));
		scrollPane.setViewportView(table);

		model = (DefaultTableModel) table.getModel();
		agregarFila();
	}

	private void nuevo() {
		prefecto = new Prefecto();
		txtNombre.setText(prefecto.getNombre());
	}

	private void agregarPrefecto() {
		  String nombre = txtNombre.getText();
		  boolean nombreExistente = false;
		  for (Prefecto p : prefectos) {
		    if (p.getNombre().equals(nombre)) {
		      nombreExistente = true;
		      break;
		    }
		  }
		  if (!nombreExistente) {
		    prefecto = new Prefecto();
		    prefecto.setId(idPrefecto);
		    prefecto.setNombre(nombre);
		    prefectos.add(prefecto);
		    agregarFila();
		    txtNombre.setText("");
		    idPrefecto++;
		  } else {
		    JOptionPane.showMessageDialog(null, "Ese nombre ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
		  }
		}


	private void agregarFila() {
		model.setRowCount(0);
		for (Prefecto prefecto : prefectos) {
			Object[] fila = new Object[1];
			fila[0] = prefecto.getNombre();
			model.addRow(fila);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNuevo) {
			nuevo();
		} else if (e.getSource() == btnGuardar) {
			agregarPrefecto();
		} else if (e.getSource() == btnCancelar) {
			dispose();
		} 
		
	}

	public List<Prefecto> getPrefectos() {
		return prefectos;
	}

	public void setPrefectos(List<Prefecto> prefectos) {
		this.prefectos = prefectos;
	}

}



