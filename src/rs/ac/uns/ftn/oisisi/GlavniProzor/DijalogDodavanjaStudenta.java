package rs.ac.uns.ftn.oisisi.GlavniProzor;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controlleri.PredmetKontroler;
import modeli.BazaPredmeta;
import modeli.BazaStudenta;
import modeli.Student;



public class DijalogDodavanjaStudenta  extends JDialog implements ActionListener {
	
	private static final long serialVersionUID = 9712937289179L;
	
	public DijalogDodavanjaStudenta(JFrame owner,int rowSelectedIndex) {
		
		super(owner,"Dodavanje studenta na predmet ",true);
		
		setSize(350, 200);
		setLocationRelativeTo(owner);
		
		JPanel panelBottom = new JPanel();
		JButton potvrda=new JButton("Potvrda");
		JButton odustanak=new JButton("Odustanak");
		
		odustanak.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			int dialogButton = JOptionPane.YES_NO_OPTION;
			           int dialogResult = JOptionPane.showConfirmDialog(MainFrame.getInstance(), "Da li ste sigurni?", "Potvrda odustanka", dialogButton);

			           if (dialogResult == JOptionPane.YES_OPTION) {
			          dispose();
			           }
			             


			}
			});
		
		panelBottom.add(potvrda);
	    panelBottom.add(odustanak);
	    
	    this.add(panelBottom, BorderLayout.SOUTH);
		
	    JPanel panelCenter = new JPanel();
	    panelCenter.setLayout(new GridBagLayout());
	    this.add(panelCenter, BorderLayout.CENTER);
	    
	    JLabel lblStudenta = new JLabel("Broj indeksa studenta* ");
	    final JTextField txtIndexStudenta = new JTextField();
	    
	    GridBagConstraints gbcLStudenta = new GridBagConstraints();
	    gbcLStudenta.gridx = 0;
	    gbcLStudenta.gridy = 0;
	    gbcLStudenta.insets = new Insets(20, 0, 0,0);
	    panelCenter.add(lblStudenta, gbcLStudenta);
	    
	    GridBagConstraints gbcTxtIndexStudenta = new GridBagConstraints();
	    gbcTxtIndexStudenta  .gridx = 1;
	    gbcTxtIndexStudenta  .gridy = 0;
	    gbcTxtIndexStudenta  .weightx = 100;
	    gbcTxtIndexStudenta  .fill = GridBagConstraints.HORIZONTAL;
	    gbcTxtIndexStudenta  .insets = new Insets(20, 20, 0, 20);
	    
	    panelCenter.add(txtIndexStudenta, gbcTxtIndexStudenta );
	    
	    potvrda.addActionListener(new ActionListener() {
	    	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				int dialogButton = JOptionPane.YES_NO_OPTION;
		           int dialogResult = JOptionPane.showConfirmDialog(MainFrame.getInstance(), "Da li ste sigurni?", "Potvrda odustanka", dialogButton);

		           if (dialogResult == JOptionPane.YES_OPTION) {
		          
				
				String indexStudenta = txtIndexStudenta.getText();
				
				for(int i=0;i<BazaPredmeta.getInstance().getRow(rowSelectedIndex).getSpisak_studenata().size();i++) {
					if(BazaPredmeta.getInstance().getRow(rowSelectedIndex).getSpisak_studenata().get(i).getBroj_indexa().equals(indexStudenta)) {
						JOptionPane.showMessageDialog(MainFrame.getInstance(), "Ovaj student vec slusa ovaj predmet!", "Greska!",
							       JOptionPane.ERROR_MESSAGE);
							return;
					}
				}
					
				
				if(BazaStudenta.getInstance().getStudentPrekoIndexa(indexStudenta)==null) {
					JOptionPane.showMessageDialog(MainFrame.getInstance(), "Ne postoji student sa tim indeksom!", "Greska!",
						       JOptionPane.ERROR_MESSAGE);
						return;
				}else {
				PredmetKontroler.getInstance().dodavanjeStudentaNaPredmet(indexStudenta, rowSelectedIndex);
				dispose();
		           }
		          }
			
				
			}
		});
	    
	    
	    
	    
	    setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}