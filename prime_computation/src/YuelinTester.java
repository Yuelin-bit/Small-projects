import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.awt.event.ActionEvent;
/**
 * 
 * @author Yuelin-Bit
 *
 */
public class YuelinTester extends JFrame {

	private String ver = "YuelinTester 1.0";
	private JPanel contentPane;
	private Level l = new Level();
	private ZongheTest zonghe = new ZongheTest();
	private int selectLevel = 0;
	private ReadBefore instruction = new ReadBefore();
	boolean hasRead = false;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					YuelinTester frame = new YuelinTester();
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
	public YuelinTester() {
		setTitle(ver);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("YuelinTester 1.0");
		lblNewLabel.setFont(new Font("Libian SC", Font.PLAIN, 24));
		
		JButton zonghe = new JButton("综合测试");
		zonghe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(hasRead==true) {
					l.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Please read the instruction before you start.");
				}
			}
		});
		
		JButton fenlei = new JButton("分类测试");
		fenlei.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "敬请期待");
			}
		});
		
		JButton banben = new JButton("检查版本");
		banben.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, ver);
			}
		});
		
		JButton shiyong = new JButton("使用必读");
		shiyong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instruction.setVisible(true);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(131)
							.addComponent(lblNewLabel))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(144)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(zonghe)
								.addComponent(banben)
								.addComponent(shiyong)
								.addComponent(fenlei))))
					.addContainerGap(129, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(34)
					.addComponent(lblNewLabel)
					.addGap(43)
					.addComponent(zonghe)
					.addGap(52)
					.addComponent(fenlei)
					.addGap(49)
					.addComponent(banben)
					.addGap(53)
					.addComponent(shiyong)
					.addContainerGap(86, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
		
		
	}
	
	
	
	
	
	public class Level extends JDialog {

		private final JPanel contentPanel = new JPanel();
		private final ButtonGroup buttonGroup = new ButtonGroup();
		private JRadioButton easy;
		private JRadioButton medium;
		private JRadioButton difficult;
		private JRadioButton extreme;

		/**
		 * Launch the application.
		 */
//		public static void main(String[] args) {
//			try {
//				
//				level dialog = new level();
//				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//				dialog.setVisible(true);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}

		/**
		 * Create the dialog.
		 */
		public Level() {
			setTitle("YuelinTester 1.0");
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
							l.setVisible(false);
							zonghe.setVisible(true);
							//System.out.println(selectLevel);
							if(selectLevel==1) {
								zonghe.getContentPane().setBackground(Color.WHITE);
							}
							if(selectLevel==2) {
								zonghe.getContentPane().setBackground(Color.GREEN);
								}
							if(selectLevel==3) {
								zonghe.getContentPane().setBackground(Color.ORANGE);
								}
							if(selectLevel==4) {
								zonghe.getContentPane().setBackground(Color.RED);
								}
							zonghe.txtrTestttt.setText("Final Grade: ");
							
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
							l.setVisible(false);
						}
					});
					cancelButton.setActionCommand("Cancel");
					buttonPane.add(cancelButton);
				}
			}
		}
	}
	
	
	
	
	
	
	
	
	public class ZongheTest extends JFrame {

		private JPanel contentPane;
		private JTextArea txtrTestttt;
		double grade = 0.00;
		private JButton backButton;
		
		boolean passConstrutorD1 = false;
		boolean passCreatingIteratorD1 = false;
		boolean passIteratorD1 = false;
		boolean passFindD1 = false;
		
		boolean passConstrutorD2 = false;
		boolean passCreatingIteratorD2 = false;
		boolean passNumLeaves = false;
		boolean passIteratorD2 = false;
		boolean passFindD2 = false;
		
		boolean passconstructorD3 = false;
		boolean passFindD3 = true;//Default should be true
		boolean passCreatingPointsD3 = true;
		boolean passRemovingPointsD4 = true;
		
		boolean timing = true;

		/**
		 * Launch the application.
		 */
//		public static void main(String[] args) {
//			EventQueue.invokeLater(new Runnable() {
//				public void run() {
//					try {
//						ZongheTest frame = new ZongheTest();
//						frame.setVisible(true);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//			});
//		}

		/**
		 * Create the frame.
		 */
		public ZongheTest() {
			
			
			setTitle("YuelinTester 1.0");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 450, 300);
			contentPane = new JPanel();
			//contentPane.setBackground(Color.WHITE);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			
			JButton btnFormal = new JButton("开始运行");
			btnFormal.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
					
					

					if(selectLevel==1) {
					
						grade = 0.00;
						txtrTestttt.setText("Final Grade: ");
						int[] arr1_1 = {10};
						int[] arr2_1 = {-94};
						int[] arr3_1 = {-2};
						int[] arr4_1 = {-3};
						int[] arr5_1 = {-1};
						int[] arr6_1 = {-1};
						int[] arr7_1 = {1};
						int[] arr8_1 = {0};
						int[] arr9_1 = {-1};
						int[] arr10_1 = {8};
						int[] arr11_1 = {8};
						int[] arr12_1 = {8};
						int[] arr13_1 = {9};
						int[] arr14_1 = {-159};
						int[] arr00_1 = {5};
						int[] arr01_1 = {0};
						int[] arr02_1 = {-50};
						
						Datum a1_1 = new Datum(arr1_1);
						Datum a2_1 = new Datum(arr2_1);
						Datum a3_1 = new Datum(arr3_1);
						Datum a4_1 = new Datum(arr4_1);
						Datum a5_1 = new Datum(arr5_1);
						Datum a6_1 = new Datum(arr6_1);
						Datum a7_1 = new Datum(arr7_1);
						Datum a8_1 = new Datum(arr8_1);
						Datum a9_1 = new Datum(arr9_1);
						Datum a10_1 = new Datum(arr10_1);
						Datum a11_1 = new Datum(arr11_1);
						Datum a12_1 = new Datum(arr12_1);
						Datum a13_1 = new Datum(arr13_1);
						Datum a14_1 = new Datum(arr14_1);
						Datum a00_1 = new Datum(arr00_1);
						Datum a01_1 = new Datum(arr01_1);
						Datum a02_1 = new Datum(arr02_1);
						Datum[] listOfPointsD1 = {a1_1,a2_1,a3_1,a4_1,a5_1,a6_1,a7_1,a8_1,a9_1,a10_1,a11_1,a12_1,a13_1,a14_1};
						ArrayList<Datum> answerD1 = new ArrayList<Datum>();
						answerD1.add(a14_1);
						answerD1.add(a2_1);
						answerD1.add(a4_1);
						answerD1.add(a3_1);
						answerD1.add(a5_1);
						answerD1.add(a8_1);
						answerD1.add(a7_1);
						answerD1.add(a10_1);
						answerD1.add(a13_1);
						answerD1.add(a1_1);
						
						ArrayList<Datum> listOfPointsAD1 = new ArrayList<Datum>();
						listOfPointsAD1.add(a1_1);
						listOfPointsAD1.add(a2_1);
						listOfPointsAD1.add(a3_1);
						listOfPointsAD1.add(a4_1);
						listOfPointsAD1.add(a5_1);
						listOfPointsAD1.add(a6_1);
						listOfPointsAD1.add(a7_1);
						listOfPointsAD1.add(a8_1);
						listOfPointsAD1.add(a9_1);
						listOfPointsAD1.add(a10_1);
						listOfPointsAD1.add(a11_1);
						listOfPointsAD1.add(a12_1);
						listOfPointsAD1.add(a13_1);
						listOfPointsAD1.add(a14_1);
						
						try {
							KDTree mytreeD1 = new KDTree(listOfPointsAD1);
							passConstrutorD1 = true;
							grade +=50;
						}catch(Exception e1) {
							System.out.println("err in constructor");
						}
						try {
							KDTree mytreeD1 = new KDTree(listOfPointsAD1);
							Iterator<Datum> d1 = mytreeD1.iterator();
							passCreatingIteratorD1 = true;
							grade +=20;
						}catch(Exception e1) {
							System.out.println("err in creating iterator ");
						}
						
						try {
							KDTree mytreeD1 = new KDTree(listOfPointsAD1);
							Iterator<Datum> d1 = mytreeD1.iterator();
							//System.out.println(mytreeD1.numLeaves);
							//System.out.println(mytreeD1	.height());
							if(mytreeD1.nearestPoint(a00_1).equals(a10_1)) {
								if(mytreeD1.nearestPoint(a01_1).equals(a8_1)) {
									if(mytreeD1.nearestPoint(a02_1).equals(a2_1)){
										grade += 30;
										passFindD1 = true;
									}
								}
							}
							
							//System.out.println("Your grade: " + grade+"/30.0");
						}catch(Exception e1) {
							System.out.println("err in findNearestPoint");
						}
						
						
						
						System.out.println("Your grade: " + grade+"/100.0");
						txtrTestttt.setText("Final Grade: " + grade+"/100.0");
						
					}
					
					
					
					
					
					
					
					
					
					
					
					
					if(selectLevel==2) {
					
						grade = 0.00;
						txtrTestttt.setText("Final Grade: ");
						int[] arr1_1 = {10};
						int[] arr2_1 = {-94};
						int[] arr3_1 = {-2};
						int[] arr4_1 = {-3};
						int[] arr5_1 = {-1};
						int[] arr6_1 = {-1};
						int[] arr7_1 = {1};
						int[] arr8_1 = {0};
						int[] arr9_1 = {-1};
						int[] arr10_1 = {8};
						int[] arr11_1 = {8};
						int[] arr12_1 = {8};
						int[] arr13_1 = {9};
						int[] arr14_1 = {-159};
						int[] arr00_1 = {5};
						int[] arr01_1 = {0};
						int[] arr02_1 = {-50};
						
						Datum a1_1 = new Datum(arr1_1);
						Datum a2_1 = new Datum(arr2_1);
						Datum a3_1 = new Datum(arr3_1);
						Datum a4_1 = new Datum(arr4_1);
						Datum a5_1 = new Datum(arr5_1);
						Datum a6_1 = new Datum(arr6_1);
						Datum a7_1 = new Datum(arr7_1);
						Datum a8_1 = new Datum(arr8_1);
						Datum a9_1 = new Datum(arr9_1);
						Datum a10_1 = new Datum(arr10_1);
						Datum a11_1 = new Datum(arr11_1);
						Datum a12_1 = new Datum(arr12_1);
						Datum a13_1 = new Datum(arr13_1);
						Datum a14_1 = new Datum(arr14_1);
						Datum a00_1 = new Datum(arr00_1);
						Datum a01_1 = new Datum(arr01_1);
						Datum a02_1 = new Datum(arr02_1);
						Datum[] listOfPointsD1 = {a1_1,a2_1,a3_1,a4_1,a5_1,a6_1,a7_1,a8_1,a9_1,a10_1,a11_1,a12_1,a13_1,a14_1};
						ArrayList<Datum> answerD1 = new ArrayList<Datum>();
						answerD1.add(a14_1);
						answerD1.add(a2_1);
						answerD1.add(a4_1);
						answerD1.add(a3_1);
						answerD1.add(a5_1);
						answerD1.add(a8_1);
						answerD1.add(a7_1);
						answerD1.add(a10_1);
						answerD1.add(a13_1);
						answerD1.add(a1_1);
						
						ArrayList<Datum> listOfPointsAD1 = new ArrayList<Datum>();
						listOfPointsAD1.add(a1_1);
						listOfPointsAD1.add(a2_1);
						listOfPointsAD1.add(a3_1);
						listOfPointsAD1.add(a4_1);
						listOfPointsAD1.add(a5_1);
						listOfPointsAD1.add(a6_1);
						listOfPointsAD1.add(a7_1);
						listOfPointsAD1.add(a8_1);
						listOfPointsAD1.add(a9_1);
						listOfPointsAD1.add(a10_1);
						listOfPointsAD1.add(a11_1);
						listOfPointsAD1.add(a12_1);
						listOfPointsAD1.add(a13_1);
						listOfPointsAD1.add(a14_1);
						
						try {
							KDTree mytreeD1 = new KDTree(listOfPointsAD1);
							passConstrutorD1 = true;
							grade +=10;
						}catch(Exception e1) {
							System.out.println("err in constructor");
						}
						try {
							KDTree mytreeD1 = new KDTree(listOfPointsAD1);
							Iterator<Datum> d1 = mytreeD1.iterator();
							passCreatingIteratorD1 = true;
							grade +=10;
						}catch(Exception e1) {
							System.out.println("err in creating iterator ");
						}
						
						try {
							KDTree mytreeD1 = new KDTree(listOfPointsAD1);
							Iterator<Datum> d1 = mytreeD1.iterator();
							int i=0;
							passIteratorD1 = true;
							while(d1.hasNext()) {
								Datum s = d1.next();
								if(!(s.equals(answerD1.get(i)))){
									grade -= 0.5;
									passIteratorD1 = false;
								}
								i++;
							}
							//System.out.println("pass");
							passIteratorD1 = true;
							grade +=15;
						}catch(Exception e1) {
							System.out.println("err in size");
						}
						try {
							KDTree mytreeD1 = new KDTree(listOfPointsAD1);
							Iterator<Datum> d1 = mytreeD1.iterator();
							//System.out.println(mytreeD1.numLeaves);
							//System.out.println(mytreeD1	.height());
							if(mytreeD1.nearestPoint(a00_1).equals(a10_1)) {
								if(mytreeD1.nearestPoint(a01_1).equals(a8_1)) {
									if(mytreeD1.nearestPoint(a02_1).equals(a2_1)){
										grade += 15;
										passFindD1 = true;
									}
								}
							}
							
							//System.out.println("Your grade: " + grade+"/30.0");
						}catch(Exception e1) {
							System.out.println("err in findNearestPoint");
						}
						
						
						
						int[] arr1_2 = {-2,-1};
						int[] arr2_2 = {-1,-1};
						int[] arr3_2 = {3,12};
						int[] arr4_2 = {45,8};
						int[] arr5_2 = {33,10};
						int[] arr6_2 = {3,8};
						int[] arr7_2 = {7,11};
						int[] arr8_2 = {1,2};
						int[] arr9_2 = {2,3};
						int[] arr10_2 = {3,4};
						int[] arr11_2 = {4,5};
						int[] arr12_2 = {6,5};
						int[] arr13_2 = {230,540};
						int[] arr14_2 = {240,540};
						int[] arr00_2 = {-1,-1};
						int[] arr01_2 = {6,6};
						int[] arr02_2 = {234,540};
						
						Datum a1_2 = new Datum(arr1_2);
						Datum a2_2 = new Datum(arr2_2);
						Datum a3_2 = new Datum(arr3_2);
						Datum a4_2 = new Datum(arr4_2);
						Datum a5_2 = new Datum(arr5_2);
						Datum a6_2 = new Datum(arr6_2);
						Datum a7_2 = new Datum(arr7_2);
						Datum a8_2 = new Datum(arr8_2);
						Datum a9_2 = new Datum(arr9_2);
						Datum a10_2 = new Datum(arr10_2);
						Datum a11_2 = new Datum(arr11_2);
						Datum a12_2 = new Datum(arr12_2);
						Datum a13_2 = new Datum(arr13_2);
						Datum a14_2 = new Datum(arr14_2);
						Datum a00_2 = new Datum(arr00_2);
						Datum a01_2 = new Datum(arr01_2);
						Datum a02_2 = new Datum(arr01_2);
						
						//Datum[] listOfPoints = {a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13,a14};
						ArrayList<Datum> listOfPointsAD2 = new ArrayList<Datum>();
						listOfPointsAD2.add(a1_2);
						listOfPointsAD2.add(a2_2);
						listOfPointsAD2.add(a3_2);
						listOfPointsAD2.add(a4_2);
						listOfPointsAD2.add(a5_2);
						listOfPointsAD2.add(a6_2);
						listOfPointsAD2.add(a7_2);
						listOfPointsAD2.add(a8_2);
						listOfPointsAD2.add(a9_2);
						listOfPointsAD2.add(a10_2);
						listOfPointsAD2.add(a11_2);
						listOfPointsAD2.add(a12_2);
						listOfPointsAD2.add(a13_2);
						listOfPointsAD2.add(a14_2);
						listOfPointsAD2.add(a14_2);
						listOfPointsAD2.add(a14_2);
						listOfPointsAD2.add(a14_2);
						listOfPointsAD2.add(a14_2);
						listOfPointsAD2.add(a14_2);
						listOfPointsAD2.add(a14_2);
						listOfPointsAD2.add(a14_2);
						ArrayList<Datum> answerD2 = new ArrayList<Datum>();
						answerD2.add(a14_1);
						answerD2.add(a2_1);
						answerD2.add(a4_1);
						answerD2.add(a3_1);
						answerD2.add(a5_1);
						answerD2.add(a8_1);
						answerD2.add(a7_1);
						answerD2.add(a10_1);
						answerD2.add(a13_1);
						answerD2.add(a1_1);
					
						try {
							KDTree mytreeD2 = new KDTree(listOfPointsAD2);
							grade += 10;
							passConstrutorD2 = true;
						}catch(Exception e1) {
							
						}
						try {
							KDTree mytreeD2 = new KDTree(listOfPointsAD2);
							Iterator<Datum> a = mytreeD2.iterator();
							grade += 10;
							passCreatingIteratorD2 = true;
						}catch(Exception e1) {
							
						}
						try {
							KDTree mytreeD2 = new KDTree(listOfPointsAD2);
							Iterator<Datum> a = mytreeD2.iterator();
							if(mytreeD2.numLeaves == 14) {
								grade += 10;
								passNumLeaves = true;
							}
						}catch(Exception e1) {
							
						}
						try {
							KDTree mytreeD2 = new KDTree(listOfPointsAD2);
							Iterator<Datum> a = mytreeD2.iterator();
							if(mytreeD2.nearestPoint(a00_2).equals(a2_2)) {
								if(mytreeD2.nearestPoint(a01_2).equals(a12_2)) {
									if(mytreeD2.nearestPoint(a02_2).equals(a12_2)){
										grade += 10;
										passFindD2 = true;
									}
								}
							}
						}catch(Exception e1) {
							
						}
						ArrayList<Datum> listOfPointsAD2_2 = new ArrayList<Datum>();
						listOfPointsAD2_2.add(a14_2);
						listOfPointsAD2_2.add(a1_2);
						listOfPointsAD2_2.add(a14_2);
						listOfPointsAD2_2.add(a2_2);
						ArrayList<Datum> answerD2_2 = new ArrayList<Datum>();
						answerD2_2.add(a1_2);
						answerD2_2.add(a2_2);
						answerD2_2.add(a14_2);
						//System.out.println("Your grade: " + grade+"/55.0");
						try {
							KDTree mytreeD2_2 = new KDTree(listOfPointsAD2_2);
							Iterator<Datum> a = mytreeD2_2.iterator();
							int j=0;
							passIteratorD2 = true;
							while(a.hasNext()) {
								Datum d = a.next();
								if(!(d.equals(answerD2_2.get(j)))) {
									grade -= 0.5;
									passIteratorD2 = false;
								}
								j++;
							}
							grade += 10;
						}catch(Exception e1) {
							
						}
						System.out.println("Your grade: " + grade+"/100.0");
						txtrTestttt.setText("Final Grade: " + grade+"/100.0");
						
					}
					
					
					
					
					
					
					
					if(selectLevel==3) {
						
						grade = 0.00;
						txtrTestttt.setText("Final Grade: ");
						int[] arr1_1 = {10};
						int[] arr2_1 = {-94};
						int[] arr3_1 = {-2};
						int[] arr4_1 = {-3};
						int[] arr5_1 = {-1};
						int[] arr6_1 = {-1};
						int[] arr7_1 = {1};
						int[] arr8_1 = {0};
						int[] arr9_1 = {-1};
						int[] arr10_1 = {8};
						int[] arr11_1 = {8};
						int[] arr12_1 = {8};
						int[] arr13_1 = {9};
						int[] arr14_1 = {-159};
						int[] arr00_1 = {5};
						int[] arr01_1 = {0};
						int[] arr02_1 = {-50};
						
						Datum a1_1 = new Datum(arr1_1);
						Datum a2_1 = new Datum(arr2_1);
						Datum a3_1 = new Datum(arr3_1);
						Datum a4_1 = new Datum(arr4_1);
						Datum a5_1 = new Datum(arr5_1);
						Datum a6_1 = new Datum(arr6_1);
						Datum a7_1 = new Datum(arr7_1);
						Datum a8_1 = new Datum(arr8_1);
						Datum a9_1 = new Datum(arr9_1);
						Datum a10_1 = new Datum(arr10_1);
						Datum a11_1 = new Datum(arr11_1);
						Datum a12_1 = new Datum(arr12_1);
						Datum a13_1 = new Datum(arr13_1);
						Datum a14_1 = new Datum(arr14_1);
						Datum a00_1 = new Datum(arr00_1);
						Datum a01_1 = new Datum(arr01_1);
						Datum a02_1 = new Datum(arr02_1);
						Datum[] listOfPointsD1 = {a1_1,a2_1,a3_1,a4_1,a5_1,a6_1,a7_1,a8_1,a9_1,a10_1,a11_1,a12_1,a13_1,a14_1};
						ArrayList<Datum> answerD1 = new ArrayList<Datum>();
						answerD1.add(a14_1);
						answerD1.add(a2_1);
						answerD1.add(a4_1);
						answerD1.add(a3_1);
						answerD1.add(a5_1);
						answerD1.add(a8_1);
						answerD1.add(a7_1);
						answerD1.add(a10_1);
						answerD1.add(a13_1);
						answerD1.add(a1_1);
						
						ArrayList<Datum> listOfPointsAD1 = new ArrayList<Datum>();
						listOfPointsAD1.add(a1_1);
						listOfPointsAD1.add(a2_1);
						listOfPointsAD1.add(a3_1);
						listOfPointsAD1.add(a4_1);
						listOfPointsAD1.add(a5_1);
						listOfPointsAD1.add(a6_1);
						listOfPointsAD1.add(a7_1);
						listOfPointsAD1.add(a8_1);
						listOfPointsAD1.add(a9_1);
						listOfPointsAD1.add(a10_1);
						listOfPointsAD1.add(a11_1);
						listOfPointsAD1.add(a12_1);
						listOfPointsAD1.add(a13_1);
						listOfPointsAD1.add(a14_1);
//						boolean passConstrutorD1 = false;
//						boolean passCreatingIteratorD1 = false;
//						boolean passIteratorD1 = false;
//						boolean passFindD1 = false;
						try {
							KDTree mytreeD1 = new KDTree(listOfPointsAD1);
							passConstrutorD1 = true;
							grade +=5;
						}catch(Exception e1) {
							System.out.println("err in constructor");
						}
						try {
							KDTree mytreeD1 = new KDTree(listOfPointsAD1);
							Iterator<Datum> d1 = mytreeD1.iterator();
							passCreatingIteratorD1 = true;
							grade +=5;
						}catch(Exception e1) {
							System.out.println("err in creating iterator ");
						}
						
						try {
							KDTree mytreeD1 = new KDTree(listOfPointsAD1);
							Iterator<Datum> d1 = mytreeD1.iterator();
							int i=0;
							passIteratorD1 = true;
							while(d1.hasNext()) {
								Datum s = d1.next();
								if(!(s.equals(answerD1.get(i)))){
									grade -= 0.5;
									passIteratorD1 = false;
								}
								i++;
							}
							//System.out.println("pass");
							passIteratorD1 = true;
							grade +=10;
						}catch(Exception e1) {
							System.out.println("err in size");
						}
						try {
							KDTree mytreeD1 = new KDTree(listOfPointsAD1);
							Iterator<Datum> d1 = mytreeD1.iterator();
							//System.out.println(mytreeD1.numLeaves);
							//System.out.println(mytreeD1	.height());
							if(mytreeD1.nearestPoint(a00_1).equals(a10_1)) {
								if(mytreeD1.nearestPoint(a01_1).equals(a8_1)) {
									if(mytreeD1.nearestPoint(a02_1).equals(a2_1)){
										grade += 10;
										passFindD1 = true;
									}
								}
							}
							
							//System.out.println("Your grade: " + grade+"/30.0");
						}catch(Exception e1) {
							System.out.println("err in findNearestPoint");
						}
						
						
						
						int[] arr1_2 = {-2,-1};
						int[] arr2_2 = {-1,-1};
						int[] arr3_2 = {3,12};
						int[] arr4_2 = {45,8};
						int[] arr5_2 = {33,10};
						int[] arr6_2 = {3,8};
						int[] arr7_2 = {7,11};
						int[] arr8_2 = {1,2};
						int[] arr9_2 = {2,3};
						int[] arr10_2 = {3,4};
						int[] arr11_2 = {4,5};
						int[] arr12_2 = {6,5};
						int[] arr13_2 = {230,540};
						int[] arr14_2 = {240,540};
						int[] arr00_2 = {-1,-1};
						int[] arr01_2 = {6,6};
						int[] arr02_2 = {234,540};
						
						Datum a1_2 = new Datum(arr1_2);
						Datum a2_2 = new Datum(arr2_2);
						Datum a3_2 = new Datum(arr3_2);
						Datum a4_2 = new Datum(arr4_2);
						Datum a5_2 = new Datum(arr5_2);
						Datum a6_2 = new Datum(arr6_2);
						Datum a7_2 = new Datum(arr7_2);
						Datum a8_2 = new Datum(arr8_2);
						Datum a9_2 = new Datum(arr9_2);
						Datum a10_2 = new Datum(arr10_2);
						Datum a11_2 = new Datum(arr11_2);
						Datum a12_2 = new Datum(arr12_2);
						Datum a13_2 = new Datum(arr13_2);
						Datum a14_2 = new Datum(arr14_2);
						Datum a00_2 = new Datum(arr00_2);
						Datum a01_2 = new Datum(arr01_2);
						Datum a02_2 = new Datum(arr01_2);
						
						//Datum[] listOfPoints = {a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13,a14};
						ArrayList<Datum> listOfPointsAD2 = new ArrayList<Datum>();
						listOfPointsAD2.add(a1_2);
						listOfPointsAD2.add(a2_2);
						listOfPointsAD2.add(a3_2);
						listOfPointsAD2.add(a4_2);
						listOfPointsAD2.add(a5_2);
						listOfPointsAD2.add(a6_2);
						listOfPointsAD2.add(a7_2);
						listOfPointsAD2.add(a8_2);
						listOfPointsAD2.add(a9_2);
						listOfPointsAD2.add(a10_2);
						listOfPointsAD2.add(a11_2);
						listOfPointsAD2.add(a12_2);
						listOfPointsAD2.add(a13_2);
						listOfPointsAD2.add(a14_2);
						listOfPointsAD2.add(a14_2);
						listOfPointsAD2.add(a14_2);
						listOfPointsAD2.add(a14_2);
						listOfPointsAD2.add(a14_2);
						listOfPointsAD2.add(a14_2);
						listOfPointsAD2.add(a14_2);
						listOfPointsAD2.add(a14_2);
						ArrayList<Datum> answerD2 = new ArrayList<Datum>();
						answerD2.add(a14_1);
						answerD2.add(a2_1);
						answerD2.add(a4_1);
						answerD2.add(a3_1);
						answerD2.add(a5_1);
						answerD2.add(a8_1);
						answerD2.add(a7_1);
						answerD2.add(a10_1);
						answerD2.add(a13_1);
						answerD2.add(a1_1);
//						boolean passConstrutorD2 = false;
//						boolean passCreatingIteratorD2 = false;
//						boolean passNumLeaves = false;
//						boolean passIteratorD2 = false;
//						boolean passFindD2 = false;
						try {
							KDTree mytreeD2 = new KDTree(listOfPointsAD2);
							grade += 5;
							passConstrutorD2 = true;
						}catch(Exception e1) {
							
						}
						try {
							KDTree mytreeD2 = new KDTree(listOfPointsAD2);
							Iterator<Datum> a = mytreeD2.iterator();
							grade += 5;
							passCreatingIteratorD2 = true;
						}catch(Exception e1) {
							
						}
						try {
							KDTree mytreeD2 = new KDTree(listOfPointsAD2);
							Iterator<Datum> a = mytreeD2.iterator();
							if(mytreeD2.numLeaves == 14) {
								grade += 5;
								passNumLeaves = true;
							}
						}catch(Exception e1) {
							
						}
						try {
							KDTree mytreeD2 = new KDTree(listOfPointsAD2);
							Iterator<Datum> a = mytreeD2.iterator();
							if(mytreeD2.nearestPoint(a00_2).equals(a2_2)) {
								if(mytreeD2.nearestPoint(a01_2).equals(a12_2)) {
									if(mytreeD2.nearestPoint(a02_2).equals(a12_2)){
										grade += 10;
										passFindD2 = true;
									}
								}
							}
						}catch(Exception e1) {
							
						}
						ArrayList<Datum> listOfPointsAD2_2 = new ArrayList<Datum>();
						listOfPointsAD2_2.add(a14_2);
						listOfPointsAD2_2.add(a1_2);
						listOfPointsAD2_2.add(a14_2);
						listOfPointsAD2_2.add(a2_2);
						ArrayList<Datum> answerD2_2 = new ArrayList<Datum>();
						answerD2_2.add(a1_2);
						answerD2_2.add(a2_2);
						answerD2_2.add(a14_2);
						//System.out.println("Your grade: " + grade+"/55.0");
						try {
							KDTree mytreeD2_2 = new KDTree(listOfPointsAD2_2);
							Iterator<Datum> a = mytreeD2_2.iterator();
							int j=0;
							passIteratorD2 = true;
							while(a.hasNext()) {
								Datum d = a.next();
								if(!(d.equals(answerD2_2.get(j)))) {
									grade -= 0.5;
									passIteratorD2 = false;
								}
								j++;
							}
							grade += 10;
						}catch(Exception e1) {
							
						}
						///////addinggg//////
						int[] q;
				        ArrayList<Datum> points = null;
				        KDTree tree;
				        int[] lows = {-2, -2, -2};
                        int[] highs = {2, 2, 2};
                        
                        try {
							points = makeDataPointsRandom(lows, highs, 10, 2, true);
							grade +=5;
							passCreatingPointsD3 = true;
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
                        try {
                        	points = makeDataPointsRandom(lows, highs, 10, 2, true);
	                        tree = new KDTree(points);
	                        tree.size();
	                        tree.countNodes();
	                        grade +=5;
	                        tree.height();
	                        grade +=5;
	                        passconstructorD3 = true;
                        }catch(Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
                        try {
                        	points = makeDataPointsRandom(lows, highs, 10, 2, true);
                        	tree = new KDTree(points);
	                        q = new int[3];
	                        for (int x1= -1; x1 <= 1; x1++) { // make a unit 3x3 cube of query points
	                            for (int x2= -1; x2 <= 1; x2++) {
	                                for (int x3= -1; x3 <= 1; x3++) {
	                                    q[0] = x1;
	                                    q[1] = x2;
	                                    q[2] = x3;
	                                    if (testQuery(q, points, tree)==false) {
	                                    	passFindD3 = false;
	                                    	if(grade>60) {
 	                                    		grade -=0.5;
 	                                    	}
	                                    }
	                                }
	                            }
	                        }
	                        testIterator(tree, points);
	                        grade +=10;
                        }catch(Exception e1) {
                        	
                        }
                        try {
                        	points = makeDataPointsRandom(lows, highs, 10, 2, false);
                        	tree = new KDTree(points);
                        	 q = new int[3];
 	                        for (int x1= -1; x1 <= 1; x1++) { // make a unit 3x3 cube of query points
 	                            for (int x2= -1; x2 <= 1; x2++) {
 	                                for (int x3= -1; x3 <= 1; x3++) {
 	                                    q[0] = x1;
 	                                    q[1] = x2;
 	                                    q[2] = x3;
 	                                    if(testQuery(q, points, tree)==false) {
 	                                    	passRemovingPointsD4 = false;
 	                                    	if(grade>60) {
 	                                    		grade -=0.5;
 	                                    	}
 	                                    }
 	                                }
 	                            }
 	                        }
 	                        testIterator(tree, points);
                        	grade +=10;
                        	
                        }catch(Exception e1) {
                        	
                        }
						
						
						
						System.out.println("Your grade: " + grade+"/100.0");
						txtrTestttt.setText("Final Grade: " + grade+"/100.0");
						
						
						
						
						
					}
					
					
					
					
					
					
					
					
					
					
					if(selectLevel==4) {
						
						grade = 0.00;
						txtrTestttt.setText("Final Grade: ");
						int[] arr1_1 = {10};
						int[] arr2_1 = {-94};
						int[] arr3_1 = {-2};
						int[] arr4_1 = {-3};
						int[] arr5_1 = {-1};
						int[] arr6_1 = {-1};
						int[] arr7_1 = {1};
						int[] arr8_1 = {0};
						int[] arr9_1 = {-1};
						int[] arr10_1 = {8};
						int[] arr11_1 = {8};
						int[] arr12_1 = {8};
						int[] arr13_1 = {9};
						int[] arr14_1 = {-159};
						int[] arr00_1 = {5};
						int[] arr01_1 = {0};
						int[] arr02_1 = {-50};
						
						Datum a1_1 = new Datum(arr1_1);
						Datum a2_1 = new Datum(arr2_1);
						Datum a3_1 = new Datum(arr3_1);
						Datum a4_1 = new Datum(arr4_1);
						Datum a5_1 = new Datum(arr5_1);
						Datum a6_1 = new Datum(arr6_1);
						Datum a7_1 = new Datum(arr7_1);
						Datum a8_1 = new Datum(arr8_1);
						Datum a9_1 = new Datum(arr9_1);
						Datum a10_1 = new Datum(arr10_1);
						Datum a11_1 = new Datum(arr11_1);
						Datum a12_1 = new Datum(arr12_1);
						Datum a13_1 = new Datum(arr13_1);
						Datum a14_1 = new Datum(arr14_1);
						Datum a00_1 = new Datum(arr00_1);
						Datum a01_1 = new Datum(arr01_1);
						Datum a02_1 = new Datum(arr02_1);
						Datum[] listOfPointsD1 = {a1_1,a2_1,a3_1,a4_1,a5_1,a6_1,a7_1,a8_1,a9_1,a10_1,a11_1,a12_1,a13_1,a14_1};
						ArrayList<Datum> answerD1 = new ArrayList<Datum>();
						answerD1.add(a14_1);
						answerD1.add(a2_1);
						answerD1.add(a4_1);
						answerD1.add(a3_1);
						answerD1.add(a5_1);
						answerD1.add(a8_1);
						answerD1.add(a7_1);
						answerD1.add(a10_1);
						answerD1.add(a13_1);
						answerD1.add(a1_1);
						
						ArrayList<Datum> listOfPointsAD1 = new ArrayList<Datum>();
						listOfPointsAD1.add(a1_1);
						listOfPointsAD1.add(a2_1);
						listOfPointsAD1.add(a3_1);
						listOfPointsAD1.add(a4_1);
						listOfPointsAD1.add(a5_1);
						listOfPointsAD1.add(a6_1);
						listOfPointsAD1.add(a7_1);
						listOfPointsAD1.add(a8_1);
						listOfPointsAD1.add(a9_1);
						listOfPointsAD1.add(a10_1);
						listOfPointsAD1.add(a11_1);
						listOfPointsAD1.add(a12_1);
						listOfPointsAD1.add(a13_1);
						listOfPointsAD1.add(a14_1);
//						boolean passConstrutorD1 = false;
//						boolean passCreatingIteratorD1 = false;
//						boolean passIteratorD1 = false;
//						boolean passFindD1 = false;
						try {
							KDTree mytreeD1 = new KDTree(listOfPointsAD1);
							passConstrutorD1 = true;
							grade +=5;
						}catch(Exception e1) {
							System.out.println("err in constructor");
						}
						try {
							KDTree mytreeD1 = new KDTree(listOfPointsAD1);
							Iterator<Datum> d1 = mytreeD1.iterator();
							passCreatingIteratorD1 = true;
							grade +=5;
						}catch(Exception e1) {
							System.out.println("err in creating iterator ");
						}
						
						try {
							KDTree mytreeD1 = new KDTree(listOfPointsAD1);
							Iterator<Datum> d1 = mytreeD1.iterator();
							int i=0;
							passIteratorD1 = true;
							while(d1.hasNext()) {
								Datum s = d1.next();
								if(!(s.equals(answerD1.get(i)))){
									grade -= 0.5;
									passIteratorD1 = false;
								}
								i++;
							}
							//System.out.println("pass");
							passIteratorD1 = true;
							grade +=10;
						}catch(Exception e1) {
							System.out.println("err in size");
						}
						try {
							KDTree mytreeD1 = new KDTree(listOfPointsAD1);
							Iterator<Datum> d1 = mytreeD1.iterator();
							//System.out.println(mytreeD1.numLeaves);
							//System.out.println(mytreeD1	.height());
							if(mytreeD1.nearestPoint(a00_1).equals(a10_1)) {
								if(mytreeD1.nearestPoint(a01_1).equals(a8_1)) {
									if(mytreeD1.nearestPoint(a02_1).equals(a2_1)){
										grade += 10;
										passFindD1 = true;
									}
								}
							}
							
							//System.out.println("Your grade: " + grade+"/30.0");
						}catch(Exception e1) {
							System.out.println("err in findNearestPoint");
						}
						
						
						
						int[] arr1_2 = {-2,-1};
						int[] arr2_2 = {-1,-1};
						int[] arr3_2 = {3,12};
						int[] arr4_2 = {45,8};
						int[] arr5_2 = {33,10};
						int[] arr6_2 = {3,8};
						int[] arr7_2 = {7,11};
						int[] arr8_2 = {1,2};
						int[] arr9_2 = {2,3};
						int[] arr10_2 = {3,4};
						int[] arr11_2 = {4,5};
						int[] arr12_2 = {6,5};
						int[] arr13_2 = {230,540};
						int[] arr14_2 = {240,540};
						int[] arr00_2 = {-1,-1};
						int[] arr01_2 = {6,6};
						int[] arr02_2 = {234,540};
						
						Datum a1_2 = new Datum(arr1_2);
						Datum a2_2 = new Datum(arr2_2);
						Datum a3_2 = new Datum(arr3_2);
						Datum a4_2 = new Datum(arr4_2);
						Datum a5_2 = new Datum(arr5_2);
						Datum a6_2 = new Datum(arr6_2);
						Datum a7_2 = new Datum(arr7_2);
						Datum a8_2 = new Datum(arr8_2);
						Datum a9_2 = new Datum(arr9_2);
						Datum a10_2 = new Datum(arr10_2);
						Datum a11_2 = new Datum(arr11_2);
						Datum a12_2 = new Datum(arr12_2);
						Datum a13_2 = new Datum(arr13_2);
						Datum a14_2 = new Datum(arr14_2);
						Datum a00_2 = new Datum(arr00_2);
						Datum a01_2 = new Datum(arr01_2);
						Datum a02_2 = new Datum(arr01_2);
						
						//Datum[] listOfPoints = {a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13,a14};
						ArrayList<Datum> listOfPointsAD2 = new ArrayList<Datum>();
						listOfPointsAD2.add(a1_2);
						listOfPointsAD2.add(a2_2);
						listOfPointsAD2.add(a3_2);
						listOfPointsAD2.add(a4_2);
						listOfPointsAD2.add(a5_2);
						listOfPointsAD2.add(a6_2);
						listOfPointsAD2.add(a7_2);
						listOfPointsAD2.add(a8_2);
						listOfPointsAD2.add(a9_2);
						listOfPointsAD2.add(a10_2);
						listOfPointsAD2.add(a11_2);
						listOfPointsAD2.add(a12_2);
						listOfPointsAD2.add(a13_2);
						listOfPointsAD2.add(a14_2);
						listOfPointsAD2.add(a14_2);
						listOfPointsAD2.add(a14_2);
						listOfPointsAD2.add(a14_2);
						listOfPointsAD2.add(a14_2);
						listOfPointsAD2.add(a14_2);
						listOfPointsAD2.add(a14_2);
						listOfPointsAD2.add(a14_2);
						ArrayList<Datum> answerD2 = new ArrayList<Datum>();
						answerD2.add(a14_1);
						answerD2.add(a2_1);
						answerD2.add(a4_1);
						answerD2.add(a3_1);
						answerD2.add(a5_1);
						answerD2.add(a8_1);
						answerD2.add(a7_1);
						answerD2.add(a10_1);
						answerD2.add(a13_1);
						answerD2.add(a1_1);
//						boolean passConstrutorD2 = false;
//						boolean passCreatingIteratorD2 = false;
//						boolean passNumLeaves = false;
//						boolean passIteratorD2 = false;
//						boolean passFindD2 = false;
						try {
							KDTree mytreeD2 = new KDTree(listOfPointsAD2);
							grade += 5;
							passConstrutorD2 = true;
						}catch(Exception e1) {
							
						}
						try {
							KDTree mytreeD2 = new KDTree(listOfPointsAD2);
							Iterator<Datum> a = mytreeD2.iterator();
							grade += 5;
							passCreatingIteratorD2 = true;
						}catch(Exception e1) {
							
						}
						try {
							KDTree mytreeD2 = new KDTree(listOfPointsAD2);
							Iterator<Datum> a = mytreeD2.iterator();
							if(mytreeD2.numLeaves == 14) {
								grade += 5;
								passNumLeaves = true;
							}
						}catch(Exception e1) {
							
						}
						try {
							KDTree mytreeD2 = new KDTree(listOfPointsAD2);
							Iterator<Datum> a = mytreeD2.iterator();
							if(mytreeD2.nearestPoint(a00_2).equals(a2_2)) {
								if(mytreeD2.nearestPoint(a01_2).equals(a12_2)) {
									if(mytreeD2.nearestPoint(a02_2).equals(a12_2)){
										grade += 10;
										passFindD2 = true;
									}
								}
							}
						}catch(Exception e1) {
							
						}
						ArrayList<Datum> listOfPointsAD2_2 = new ArrayList<Datum>();
						listOfPointsAD2_2.add(a14_2);
						listOfPointsAD2_2.add(a1_2);
						listOfPointsAD2_2.add(a14_2);
						listOfPointsAD2_2.add(a2_2);
						ArrayList<Datum> answerD2_2 = new ArrayList<Datum>();
						answerD2_2.add(a1_2);
						answerD2_2.add(a2_2);
						answerD2_2.add(a14_2);
						//System.out.println("Your grade: " + grade+"/55.0");
						try {
							KDTree mytreeD2_2 = new KDTree(listOfPointsAD2_2);
							Iterator<Datum> a = mytreeD2_2.iterator();
							int j=0;
							passIteratorD2 = true;
							while(a.hasNext()) {
								Datum d = a.next();
								if(!(d.equals(answerD2_2.get(j)))) {
									grade -= 0.5;
									passIteratorD2 = false;
								}
								j++;
							}
							grade += 10;
						}catch(Exception e1) {
							
						}
						///////addinggg//////
						
                        
                        try {
                        	int[] q;
    				        ArrayList<Datum> points = null;
    				        KDTree tree;
    				        int[] lows = {-2, -2, -2};
                            int[] highs = {2, 2, 2};
							points = makeDataPointsRandom(lows, highs, 10, 2, true);
							grade +=5;
							passCreatingPointsD3 = true;
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
                        try {
                        	int[] q;
    				        ArrayList<Datum> points = null;
    				        KDTree tree;
    				        int[] lows = {-2, -2, -2};
                            int[] highs = {2, 2, 2};
                        	points = makeDataPointsRandom(lows, highs, 10, 2, true);
	                        tree = new KDTree(points);
	                        tree.size();
	                        tree.countNodes();
	                        grade +=5;
	                        tree.height();
	                        grade +=5;
	                        passconstructorD3 = true;
                        }catch(Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
                        try {
                        	int[] q;
    				        ArrayList<Datum> points = null;
    				        KDTree tree;
    				        int[] lows = {-2, -2, -2};
                            int[] highs = {2, 2, 2};
                        	points = makeDataPointsRandom(lows, highs, 10, 2, true);
                        	tree = new KDTree(points);
	                        q = new int[3];
	                        for (int x1= -1; x1 <= 1; x1++) { // make a unit 3x3 cube of query points
	                            for (int x2= -1; x2 <= 1; x2++) {
	                                for (int x3= -1; x3 <= 1; x3++) {
	                                    q[0] = x1;
	                                    q[1] = x2;
	                                    q[2] = x3;
	                                    if (testQuery(q, points, tree)==false) {
	                                    	passFindD3 = false;
	                                    	if(grade>60) {
 	                                    		grade -=0.5;
 	                                    	}
	                                    }
	                                }
	                            }
	                        }
	                        testIterator(tree, points);
	                        grade +=10;
                        }catch(Exception e1) {
                        	
                        }
                        try {
                        	int[] q;
    				        ArrayList<Datum> points = null;
    				        KDTree tree;
    				        int[] lows = {-2, -2, -2};
                            int[] highs = {2, 2, 2};
                        	points = makeDataPointsRandom(lows, highs, 10, 2, false);
                        	tree = new KDTree(points);
                        	 q = new int[3];
 	                        for (int x1= -1; x1 <= 1; x1++) { // make a unit 3x3 cube of query points
 	                            for (int x2= -1; x2 <= 1; x2++) {
 	                                for (int x3= -1; x3 <= 1; x3++) {
 	                                    q[0] = x1;
 	                                    q[1] = x2;
 	                                    q[2] = x3;
 	                                    if(testQuery(q, points, tree)==false) {
 	                                    	passRemovingPointsD4 = false;
 	                                    	if(grade>60) {
 	                                    		grade -=0.5;
 	                                    	}
 	                                    }
 	                                }
 	                            }
 	                        }
 	                        testIterator(tree, points);
                        	grade +=10;
                        	
                        }catch(Exception e1) {
                        	
                        }
                        try {
                        	  int[] q;
                              long s_time, e_time;
                              float t_time;
                              ArrayList<Datum> points = null;
                              KDTree tree;
                              
                              int[] lows = {-50, -50, -50};
                              int[] highs = {50, 50, 50};
                              points = makeDataPointsRandom(lows, highs, 100000, 2, true);
                              
                          	s_time = System.nanoTime();
                          	tree = new KDTree(points);
                          	e_time = System.nanoTime();
                          	t_time = ((float)(e_time-s_time)) / 1000000; // Convert from nanoseconds to milliseconds
                          	if (t_time >= 1200) {
                          		grade -= 10.1;
                          	}
                        
                            q = new int[3];
                            for (int x1= -40; x1 <= 40; x1 += 40) { // make a unit 3x3 cube of query points
                                for (int x2= -40; x2 <= 40; x2 += 40) {
                                    for (int x3= -40; x3 <= 40; x3 += 40) {
                                        q[0] = x1;
                                        q[1] = x2;
                                        q[2] = x3;
                                        if(testQueryH(q, points, tree)==false) {
                                        	//System.out.println("trstHH: " + testQueryH(q, points, tree));
                                        	grade -= 8.7;
                                        	timing = false;
                                        }
                                    }
                                }
                            }
              
                            testIterator(tree, points);
                        }catch(Exception e1) {
                        	
                        }
						
						
						
						System.out.println("Your grade: " + grade+"/100.0");
						txtrTestttt.setText("Final Grade: " + grade+"/100.0");
						
						
						
						
						
					}
					
					
					
					
					
					
					
					
					
						
					}
				});
			
			
			txtrTestttt = new JTextArea();
			txtrTestttt.setToolTipText("");
			txtrTestttt.setText("Final Grade: ");
			
			JButton button = new JButton("成绩报告");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(selectLevel==1) {
						JOptionPane.showMessageDialog(null, "Your grade: " + grade+"/100.0" + "\n" + "\n"
								+"passConstrutorD1: "+passConstrutorD1 + "\n" + "\n"
								+"passCreatingIteratorD1: " + passCreatingIteratorD1 + "\n" + "\n"
								+"passFindD1: " + passFindD1 + "\n" + "\n"
								+"当前难度：简单");
						}
					if(selectLevel==2) {
					JOptionPane.showMessageDialog(null, "Your grade: " + grade+"/100.0" + "\n" + "\n"
							+"passConstrutorD1: "+passConstrutorD1 + "\n" + "\n"
							+"passCreatingIteratorD1: " + passCreatingIteratorD1 + "\n" + "\n"
							+"passIteratorD1: " + passIteratorD1 + "\n" + "\n"
							+"passFindD1: " + passFindD1 + "\n" + "\n"
				+"passConstrutorD2: "+passConstrutorD2 + "\n" + "\n"
				+"passCreatingIteratorD2: " + passCreatingIteratorD2 + "\n" + "\n"
				+"passNumLeaves: " + passNumLeaves + "\n" + "\n"
				+"passIteratorD2: " + passIteratorD2 + "\n" + "\n"
				+"passFindD2: " + passFindD2 + "\n" + "\n"
				+"当前难度：普通");
					}
					if(selectLevel==3) {
						JOptionPane.showMessageDialog(null, "Your grade: " + grade+"/100.0" + "\n" + "\n"
								+"passConstrutorD1: "+passConstrutorD1 + "\n" + "\n"
								+"passCreatingIteratorD1: " + passCreatingIteratorD1 + "\n" + "\n"
								+"passIteratorD1: " + passIteratorD1 + "\n" + "\n"
								+"passFindD1: " + passFindD1 + "\n" + "\n"
					+"passConstrutorD2: "+passConstrutorD2 + "\n" + "\n"
					+"passCreatingIteratorD2: " + passCreatingIteratorD2 + "\n" + "\n"
					+"passNumLeaves: " + passNumLeaves + "\n" + "\n"
					+"passIteratorD2: " + passIteratorD2 + "\n" + "\n"
					+"passFindD2: " + passFindD2 + "\n" + "\n"
					+"passconstructorD3: " + passconstructorD3 + "\n" + "\n"
					+"passFindD3: " + passFindD3 + "\n" + "\n"
					+"passCreatingPointsD3: " + passCreatingPointsD3 + "\n" + "\n"
					+"passRemovingPointsD4: " + passRemovingPointsD4 + "\n" + "\n"
					+"当前难度：困难");
						}
					if(selectLevel==4) {
						JOptionPane.showMessageDialog(null, "Your grade: " + grade+"/100.0" + "\n" + "\n"
								+"passConstrutorD1: "+passConstrutorD1 + "\n" + "\n"
								+"passCreatingIteratorD1: " + passCreatingIteratorD1 + "\n" + "\n"
								+"passIteratorD1: " + passIteratorD1 + "\n" + "\n"
								+"passFindD1: " + passFindD1 + "\n" + "\n"
					+"passConstrutorD2: "+passConstrutorD2 + "\n" + "\n"
					+"passCreatingIteratorD2: " + passCreatingIteratorD2 + "\n" + "\n"
					+"passNumLeaves: " + passNumLeaves + "\n" + "\n"
					+"passIteratorD2: " + passIteratorD2 + "\n" + "\n"
					+"passFindD2: " + passFindD2 + "\n" + "\n"
					+"passconstructorD3: " + passconstructorD3 + "\n" + "\n"
					+"passFindD3: " + passFindD3 + "\n" + "\n"
					+"passCreatingPointsD3: " + passCreatingPointsD3 + "\n" + "\n"
					+"passRemovingPointsD4: " + passRemovingPointsD4 + "\n" + "\n"
					+"timing: " + timing + "\n" + "\n"
					+"当前难度：炼狱");
						}
				
				}
			});
			backButton = new JButton("返回菜单");
			backButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			});
			GroupLayout gl_contentPane = new GroupLayout(contentPane);
			gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap(168, Short.MAX_VALUE)
						.addComponent(btnFormal)
						.addGap(176))
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(41)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(button)
								.addPreferredGap(ComponentPlacement.RELATED, 170, Short.MAX_VALUE)
								.addComponent(backButton)
								.addGap(16))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(txtrTestttt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(320, Short.MAX_VALUE))))
			);
			gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(16)
						.addComponent(btnFormal)
						.addGap(42)
						.addComponent(txtrTestttt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(48)
						.addComponent(button)
						.addContainerGap(88, Short.MAX_VALUE))
					.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
						.addContainerGap(177, Short.MAX_VALUE)
						.addComponent(backButton)
						.addGap(62))
			);
			contentPane.setLayout(gl_contentPane);
		}
	}
	
	public static boolean testQueryH(int[] q, ArrayList<Datum> points,  KDTree tree) {
    	Datum query = new Datum(q);
    	
    	long tree_s_time = System.nanoTime();
    	Datum nearestPoint = tree.nearestPoint(query);
    	long tree_e_time = System.nanoTime();
    	long tree_np_sqdst = KDTree.distSquared(query, nearestPoint);
    	float tree_time = ((float)(tree_e_time-tree_s_time)) / 1000000; // Convert from nanoseconds to milliseconds
    	//System.out.println("tree time: "+tree_time);
    	
        
    	long brute_s_time = System.nanoTime();
    	long brute_np_sqdst = minDistSquaredBruteForce(points, query);
    	long brute_e_time = System.nanoTime();
    	float brute_time = ((float)(brute_e_time-brute_s_time)) / 1000000; // Convert from nanoseconds to milliseconds
    	//System.out.println("brute time: "+brute_time);

        double n = points.size();

        double log_n = Math.log(n) / Math.log(2);
        
        double constant = 0.002;
        double required_speedup = constant * (n / log_n);
        
       // System.out.println("required_speedup: "+required_speedup);
        if ((brute_time / tree_time) >= required_speedup*6) {
        	return true;
        }
        return false;
    }
	
	public static void testIterator(KDTree tree, ArrayList<Datum> points){
        Iterator<Datum> it = tree.iterator();
        int [] raw_points = new int[points.size()];
        int [] raw_points_set =  new int[tree.size()];
        if (points.get(0).x.length > 1) {
            
        }
        else {
            for (int i = 0; i < points.size(); i++)
                raw_points[i] = points.get(i).x[0];
           
            Arrays.sort(raw_points);
            
            raw_points_set[0] = raw_points[0];
            int j = 0;
            for (int i = 1; i < raw_points.length ; i++) {
                if (raw_points_set[j] != raw_points[i]) {
                    j++;
                    raw_points_set[j] = raw_points[i];
                }

            }
        }
        int ct = 0;
        boolean ordering_flag=true;
        while (it.hasNext()) {
            int iterval = it.next().x[0];
            
            if(raw_points_set[ct] != iterval)
                ordering_flag=false;
            ct++;
        }
       

        if (points.get(0).x.length==1)
        {
            if(ordering_flag) {
            	
            }
           
            else {
            	
            }
               

        }

    }
	
	public static long minDistSquaredBruteForce(ArrayList<Datum> points, Datum query) {
        long tmpDistSqr, minDistSqr = ((long) Integer.MAX_VALUE) * Integer.MAX_VALUE;
        Datum a = null;
        for (Datum d : points) {
            tmpDistSqr = KDTree.distSquared(query, d );
            if (tmpDistSqr < minDistSqr) {
                minDistSqr = tmpDistSqr;
                a=d;
            }
        }
        return minDistSqr;
    }
	
	public static boolean testQuery(int[] q, ArrayList<Datum> points,  KDTree tree) {
        Datum query = new Datum(q);
        Datum nearestPoint = tree.nearestPoint(query);
        if (KDTree.distSquared(query, nearestPoint) == minDistSquaredBruteForce(points, query)) {
            return true;
        }
        else{
        	
            return false;
        }
    }
	
	private static ArrayList<Datum> makeDataPointsRandom(int[] lows, int[] highs, int numpoints, long seed, boolean allow_duplicates) throws Exception {
        if (lows.length != highs.length) {
            throw new Exception();
        }

        Collection<Datum> points;
        if (allow_duplicates) {
            points = new ArrayList<>();
        } else {
            points = new HashSet<>();
        }
        Random r = new Random(seed);
        for (int n = 0; n < numpoints; n++) {
            int[] x = new int[lows.length];
            for (int i = 0; i < lows.length; i++) {
                x[i] = r.nextInt((highs[i]-lows[i])) + lows[i];
            }
            points.add(new Datum(x));
        }
        return new ArrayList<Datum>(points);
    }
	
	
	
	
	
	public class ReadBefore extends JDialog {

		private final JPanel contentPanel = new JPanel();

		/**
		 * Launch the application.
		 */
//		public static void main(String[] args) {
//			try {
//				ReadBefore dialog = new ReadBefore();
//				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//				dialog.setVisible(true);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}

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
			JLabel i4 = new JLabel("4. 请不要辱骂或捧杀作者。");
			JLabel i5 = new JLabel("5. 希望本测试对你有帮助。");
			JLabel i6 = new JLabel("6. lalala");
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
							hasRead = true;
							instruction.setVisible(false);
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
							instruction.setVisible(false);
							hasRead = false;
						}
					});
					disagree.setActionCommand("Cancel");
					buttonPane.add(disagree);
				}
			}
		}
	}
	
	
	
}







