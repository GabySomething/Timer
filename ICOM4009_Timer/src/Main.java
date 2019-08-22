import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class Main {

	public static class Comp extends JComponent {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String timer_text = "";
		private Timer timer = null;
		private Font bigFont = new Font("Arial", Font.BOLD, 30);
		private Font smallFont = new Font("Arial", Font.BOLD, 14);
		private JButton pause_b;
		private JButton reset_b;
		int button_width = 150;
		int button_height = 50;

		/**
		 * Constructor made to grab pause and reset buttons to place under text
		 * 
		 * @author Jose Tua Colon
		 */
		public Comp(JButton pause, JButton reset, Timer timer) {
			this.pause_b = pause;
			this.reset_b = reset;
			this.timer = timer;
		}

		/**
		 * Draws content on the JFrame
		 * 
		 * @author Gabriel Soto Ramos
		 * @author Jose Tua Colon
		 * @author Mario Rodriguez
		 */
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.DARK_GRAY);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.setColor(Color.WHITE);
			timer.update();
			if (timer.isPaused())
				pause_b.setText("Run");
			else
				pause_b.setText("Pause");
			g.setFont(bigFont);
			timer_text = timer.getTimer();
			
			
			double timer_hwidth = g.getFontMetrics().stringWidth(timer_text) / 2.0;
						
			double text_hheight = g.getFontMetrics().getHeight() / 2.0;
			g.drawString(timer_text, (int) (this.getWidth() / 2.0 - timer_hwidth),
					(int) (this.getHeight() / 2.0 + text_hheight));
			
			pause_b.setBounds((int) (this.getWidth() / 2.0 - timer_hwidth) - button_width / 2,
					(int) (this.getHeight() / 2.0 + text_hheight) + 30, button_width, button_height);
			reset_b.setBounds((int) (this.getWidth() / 2.0 + timer_hwidth) - button_width / 2,
					(int) (this.getHeight() / 2.0 + text_hheight) + 30, button_width, button_height);
//			g.drawRect((int) (this.getWidth() / 2.0 - timer_hwidth), (int) (this.getHeight() / 2.0 - text_hheight),
//					(int) (timer_hwidth * 2), button_height);
			
			int wbox= g.getFontMetrics().stringWidth("00");
			int wmbox= g.getFontMetrics().stringWidth("000");
			int wboxborder= g.getFontMetrics().stringWidth(" : ");
			
			g.setFont(smallFont);
			String timer_upper_text = "Timer Time";
			double timerText_hwidth = g.getFontMetrics().stringWidth(timer_upper_text) / 2.0;

//			g.drawString(timer_upper_text, (int) (this.getWidth() / 2.0 - timerText_hwidth),
//					(int) (this.getHeight() / 2.0 - text_hheight));
			
			String tHours= "Hrs";
			String tMinutes= "Mins";
			String tSeconds= "Secs";
			String tMilliseconds= "Millis";
			//Component Width
			int compWith= this.getWidth();
			//Components Height
			int compHeight = this.getHeight();
			
			int wtHours= g.getFontMetrics().stringWidth(tHours);
			int wtMinutes= g.getFontMetrics().stringWidth(tMinutes);
			int wtSeconds= g.getFontMetrics().stringWidth(tSeconds);
			int wtMilliseconds= g.getFontMetrics().stringWidth(tMilliseconds);
			
			g.drawString(tHours, (int)((compWith+wbox-wtHours)/2-timer_hwidth), (int) (compHeight/2-text_hheight));
			g.drawString(tMinutes, (int)((compWith-wtMinutes+wtHours)/2-timer_hwidth+  wboxborder+ wbox), (int) (compHeight/2-text_hheight));
			g.drawString(tSeconds, (int)((compWith-wtSeconds+wtHours+wtMinutes)/2-timer_hwidth+  wboxborder+ 2*wbox+10), (int) (compHeight/2-text_hheight));
			g.drawString(tMilliseconds, (int)((compWith/2+timer_hwidth-wtMilliseconds/2-wmbox/2)), (int) (compHeight/2-text_hheight));
			
			
			for (int i = 0; i < timer.getSeconds() % 10; i++) {

			
				
				int w = 50, h = 50;
				
				int x= this.getWidth()-w;
				int y= (9-i)*h;
				
				
				
				if( i>=0 && i<3) {
				g.setColor(Color.GREEN);}
				
				else if(i>=3 && i<6) {
					g.setColor(Color.YELLOW);
				}
				
				else {
					g.setColor(Color.RED);
					
				}
				g.fillRect(x, y, w, h);
				
				g.setColor(Color.DARK_GRAY);
				g.drawRect(x, y, w, h);
				
				
				

			}

		}

	}

	public static void main(String[] args) {
		int frameWidth = 800, frameHeight = 600;
		JFrame frame = new JFrame();
		frame.setResizable(false);
		JButton pause_button = new JButton();
		JButton reset_button = new JButton();
		Timer timer = new Timer();
		JComponent comp = new Comp(pause_button, reset_button, timer);

		pause_button.addActionListener(new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				timer.pause();
			}

		});

		reset_button.addActionListener(new AbstractAction() {
			private static final long serialVersionUID = 2L;

			@Override
			public void actionPerformed(ActionEvent e) {
				timer.reset();
			}

		});
		pause_button.setText("Start");
		reset_button.setText("Reset");
		frame.setTitle("Timer");
		frame.setSize(frameWidth, frameHeight);
		frame.add(comp);
		comp.add(pause_button);
		comp.add(reset_button);
		comp.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		while (true) {
			frame.repaint();
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
