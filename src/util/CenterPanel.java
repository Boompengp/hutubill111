package util;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.panel.WorkingPanel;

public class CenterPanel extends JPanel{
	
	private double rate;  	//拉伸的比例	
	private JComponent c;	//拉伸的组件
	private boolean strech;	//是否拉伸 
	
	public CenterPanel(double rate,boolean strech){
		this.setLayout(null);
		this.rate = rate;
		this.strech = strech;
	}
	
	public CenterPanel(double rate) {
		this(rate, true);
	}
	
	public void repaint() {
		if(c != null){
			Dimension containerSize = this.getSize();
			Dimension conponentSize = c.getPreferredSize();
			
			if(strech)
				c.setSize((int)(containerSize.width*rate),(int)(containerSize.height*rate));
			else
				c.setSize(conponentSize);
			
			c.setLocation(containerSize.width / 2 - c.getSize().width / 2, containerSize.height / 2 - c.getSize().height / 2);
		}
		
		// 注释后无法正常来回切换
		super.repaint(); 
	}
	
	public void show(JComponent p) {
		this.c = p;
		Component[] cs = getComponents();
		for(Component c:cs){
			remove(c);
		}
		add(p);
		if (p instanceof WorkingPanel)
            ((WorkingPanel) p).updateData();
		this.updateUI();
	}
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setSize(200,200);
		f.setLocationRelativeTo(null);
		CenterPanel cp = new CenterPanel(0.85,true);
		f.setContentPane(cp);
		f.setDefaultCloseOperation(3);
		f.setVisible(true);
		JButton b = new JButton("ABC");
		cp.show(b);
	}
}
