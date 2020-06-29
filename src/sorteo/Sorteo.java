package sorteo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionEvent;

public class Sorteo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableA;
	private JTable tableB;
	private JTable tableC;
	private JTable tableD;
	private JTable tableE;
	private JTable tableF;
	private JTable tableG;
	private JTable tableH;

	private Modelo modelo;

	private ArrayList<Equipo_has_grupo> sorteo;
	private ArrayList<JTable> tablas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sorteo frame = new Sorteo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Sorteo() {

		modelo = new Modelo();

		setTitle("Sorteo Champions");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 758, 294);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panelSorteo = new JPanel();

		JPanel panelBotones = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panelBotones, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 848, Short.MAX_VALUE)
						.addComponent(panelSorteo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 848, Short.MAX_VALUE))
				.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(panelSorteo, GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE).addGap(18)
						.addComponent(panelBotones, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		panelBotones.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		JButton buttonBorrar = new JButton("Borrar");
		buttonBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int opcion = JOptionPane.showConfirmDialog(null,
						"Esta usted seguro de querer borrar los datos del sorteo?. Esta operacion borrara los datos de la BD y no se podra deshacer.",
						"Confirmacion", JOptionPane.YES_NO_OPTION);

				if (opcion == JOptionPane.YES_OPTION) {

					try {
						modelo.borrarSorteo();
						inicializarTablas();

						JOptionPane.showMessageDialog(null, "Datos borrados.", "Info", JOptionPane.INFORMATION_MESSAGE);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Se ha producido un error al borrar los datos.", "Info",
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				}

			}

		});

		JButton buttonListadoAlfabetico = new JButton("Listado alfabeticamente");
		buttonListadoAlfabetico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (sorteo != null) {
					Collections.sort(sorteo, Equipo_has_grupo.COMPARTOR_BY_NAME);
					createFile(sorteo);
				} else {
					JOptionPane.showMessageDialog(null, "Debe primero realizar un sorteo", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panelBotones.add(buttonListadoAlfabetico);

		JButton buttonListadoGrupos = new JButton("Listado por grupos");
		buttonListadoGrupos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (sorteo != null) {
					Collections.sort(sorteo);
					createFile(sorteo);
				} else {
					JOptionPane.showMessageDialog(null, "Debe primero realizar un sorteo", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}

		});
		panelBotones.add(buttonListadoGrupos);
		panelBotones.add(buttonBorrar);

		JButton buttonSorteo = new JButton("Realizar sorteo");
		buttonSorteo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				realizarSorteo();
			}
		});
		panelBotones.add(buttonSorteo);
		panelSorteo.setLayout(new GridLayout(2, 4, 0, 0));

		JPanel panelA = new JPanel();
		panelSorteo.add(panelA);
		panelA.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panelA.add(scrollPane, BorderLayout.CENTER);

		tableA = new JTable();
		scrollPane.setViewportView(tableA);

		JPanel panelB = new JPanel();
		panelSorteo.add(panelB);
		panelB.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		panelB.add(scrollPane_1, BorderLayout.CENTER);

		tableB = new JTable();
		scrollPane_1.setViewportView(tableB);

		JPanel panelC = new JPanel();
		panelSorteo.add(panelC);
		panelC.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_2 = new JScrollPane();
		panelC.add(scrollPane_2, BorderLayout.CENTER);

		tableC = new JTable();
		scrollPane_2.setViewportView(tableC);

		JPanel panelD = new JPanel();
		panelSorteo.add(panelD);
		panelD.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_3 = new JScrollPane();
		panelD.add(scrollPane_3, BorderLayout.CENTER);

		tableD = new JTable();
		scrollPane_3.setViewportView(tableD);

		JPanel panelE = new JPanel();
		panelSorteo.add(panelE);
		panelE.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_4 = new JScrollPane();
		panelE.add(scrollPane_4, BorderLayout.CENTER);

		tableE = new JTable();
		scrollPane_4.setViewportView(tableE);

		JPanel panelF = new JPanel();
		panelSorteo.add(panelF);
		panelF.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_5 = new JScrollPane();
		panelF.add(scrollPane_5, BorderLayout.CENTER);

		tableF = new JTable();
		scrollPane_5.setViewportView(tableF);

		JPanel panelG = new JPanel();
		panelSorteo.add(panelG);
		panelG.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_6 = new JScrollPane();
		panelG.add(scrollPane_6, BorderLayout.CENTER);

		tableG = new JTable();
		scrollPane_6.setViewportView(tableG);

		JPanel panelH = new JPanel();
		panelSorteo.add(panelH);
		panelH.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_7 = new JScrollPane();
		panelH.add(scrollPane_7, BorderLayout.CENTER);

		tableH = new JTable();
		scrollPane_7.setViewportView(tableH);
		contentPane.setLayout(gl_contentPane);

		tablas = new ArrayList<JTable>();
		tablas.add(tableA);
		tablas.add(tableB);
		tablas.add(tableC);
		tablas.add(tableD);
		tablas.add(tableE);
		tablas.add(tableF);
		tablas.add(tableG);
		tablas.add(tableH);

		inicializarTablas();
	}

	private void realizarSorteo() {

		sorteo = new ArrayList<Equipo_has_grupo>();
		Bombo<Grupo> grupos = modelo.getGrupos();
		
		Bombo<Equipo> bombo1 = modelo.obtenerBombo(1);
		Bombo<Equipo> bombo2 = modelo.obtenerBombo(2);
		Bombo<Equipo> bombo3 = modelo.obtenerBombo(3);
		Bombo<Equipo> bombo4 = modelo.obtenerBombo(4);
		
		try {
			sacarBolas(bombo1, grupos.getCopia());
			sacarBolas(bombo2, grupos.getCopia());
			sacarBolas(bombo3, grupos.getCopia());
			sacarBolas(bombo4, grupos.getCopia());

			actualizarTablas();

		} catch (SQLException e) {

			JOptionPane.showMessageDialog(this,
					"Se ha producido un error en el acceso a la BD. Se deben borrar los sorteos previos", "Error",
					JOptionPane.ERROR_MESSAGE);

		}

	}

	private void inicializarTablas() {
		sorteo = modelo.getSorteo();
		actualizarTablas();

	}

	private void createFile(ArrayList<Equipo_has_grupo> sorteo) {

		JFileChooser jfc = new JFileChooser();
		int opcion = jfc.showSaveDialog(this);

		if (opcion == JFileChooser.APPROVE_OPTION) {

			try (PrintWriter pw = new PrintWriter(jfc.getSelectedFile())) {

				pw.write("-----------------------------------------------------\n");
				pw.write(String.format("|%-40s|", "Equipo") + String.format("%10s|\n", "Grupo"));
				pw.write("-----------------------------------------------------\n");

				for (Equipo_has_grupo ehg : sorteo) {
					pw.write(String.format("|%-40s|", ehg.getEquipo().getNombre())
							+ String.format("%10s|\n", ehg.getGrupo().getLetra()));
				}
				pw.write("-----------------------------------------------------\n");

				JOptionPane.showMessageDialog(this, "Fichero creado con exito.", "Info",
						JOptionPane.INFORMATION_MESSAGE);

			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(this, "Se ha producido un error al generar el fichero.", "Error",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}

		}

	}

	private void actualizarTablas() {

		ArrayList<Grupo> grupos = modelo.getGrupos().getBolas();
		ArrayList<JTable> ts = new ArrayList<JTable>(tablas);
		ArrayList<Equipo> eqs;

		for (Grupo g : grupos) {
			eqs = new ArrayList<Equipo>();

			for (Equipo_has_grupo ehg : sorteo) {

				if (ehg.getGrupo().getLetra().equals(g.getLetra())) {
					eqs.add(ehg.getEquipo());
				}
			}

			actualizarTabla(ts.remove(0), g, eqs);
		}

	}

	private void actualizarTabla(JTable table, Grupo g, ArrayList<Equipo> equipos) {

		DefaultTableModel dtm = new DefaultTableModel();
		dtm.addColumn(g.getLetra());

		for (Equipo e : equipos) {
			dtm.addRow(new String[] { e.getNombre() });
		}

		table.setModel(dtm);

	}

	private void sacarBolas(Bombo<Equipo> bombo, Bombo<Grupo> grupos) throws SQLException {

		// Segun la mecanica solo se mueve el bombo de los equipos
		bombo.moverBombo();
		
		// Se extrae la bola de equipo
		Equipo e = bombo.sacarBola();
		
		while(e!=null) {

			Equipo_has_grupo ehg = new Equipo_has_grupo(e, grupos.sacarBola()); // Se asigna el equipo al primer grupo disponible
			sorteo.add(ehg);

			modelo.insertarEquipo_has_grupo(ehg);
			
			e = bombo.sacarBola();
		}

	}

}
