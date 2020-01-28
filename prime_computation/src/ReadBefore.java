import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;

public class ReadBefore extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ReadBefore dialog = new ReadBefore();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ReadBefore() {
		setBounds(100, 100, 500, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblInstruction = new JLabel("Instruction");
		lblInstruction.setFont(new Font("Lao Sangam MN", Font.PLAIN, 20));
		
		JLabel i1 = new JLabel("1. 本测试仅供参考参考。");
		JLabel i2 = new JLabel("2. 不得用于商业行为。");
		JLabel i3 = new JLabel("3. 每个人选取splitDim的方式不一样，所以综合测试不测试它。");
		JLabel i4 = new JLabel("4. 不要捧杀作者。");
		JLabel i5 = new JLabel("5. 希望本测试对你有帮助。");
		JLabel i6 = new JLabel("6. 没想好");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(196)
							.addComponent(lblInstruction))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(40)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(i2)
								.addComponent(i1)
								.addComponent(i3)
								.addComponent(i4)
								.addComponent(i5)
								.addComponent(i6))))
					.addContainerGap(94, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(15)
					.addComponent(lblInstruction)
					.addGap(29)
					.addComponent(i1)
					.addGap(30)
					.addComponent(i2)
					.addGap(29)
					.addComponent(i3)
					.addGap(27)
					.addComponent(i4)
					.addGap(29)
					.addComponent(i5)
					.addGap(34)
					.addComponent(i6)
					.addContainerGap(213, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton agree = new JButton("agree");
				agree.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				agree.setActionCommand("OK");
				buttonPane.add(agree);
				getRootPane().setDefaultButton(agree);
			}
			{
				JButton disagree = new JButton("disagree");
				disagree.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				disagree.setActionCommand("Cancel");
				buttonPane.add(disagree);
			}
		}
	}
}
