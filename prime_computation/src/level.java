import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class level extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton easy;
	private JRadioButton medium;
	private JRadioButton difficult;
	private JRadioButton extreme;
	private int selectLevel = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			
			level dialog = new level();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public level() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		easy = new JRadioButton("简单");
		easy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectLevel = 1;
			}
		});
		buttonGroup.add(easy);
		medium = new JRadioButton("中等");
		medium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectLevel = 2;
			}
		});
		buttonGroup.add(medium);
		difficult = new JRadioButton("困难");
		difficult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectLevel = 3;
			}
		});
		buttonGroup.add(difficult);
		extreme = new JRadioButton("炼狱");
		extreme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectLevel = 4;
			}
		});
		buttonGroup.add(extreme);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(186)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(difficult, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
						.addComponent(easy)
						.addComponent(medium, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
						.addComponent(extreme, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(196, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addGap(18)
					.addComponent(easy)
					.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
					.addComponent(medium)
					.addGap(34)
					.addComponent(difficult)
					.addGap(31)
					.addComponent(extreme)
					.addGap(23))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
