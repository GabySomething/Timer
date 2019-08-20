import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class Main {

	public static class Comp extends JComponent {

		private long t = System.currentTimeMillis();
		private long pT = 0;
		private long pause_time = 0;
		private String timer = "";
		private String timerText = "";
		private int mins = 0;
		private long ms = 0;
		private int s = 0;
		private int hrs = 0;
		private boolean pause = true;
		private Font bigFont = new Font("Arial", Font.BOLD, 30);
		private Font smallFont = new Font("Arial", Font.BOLD, 14);
		private JButton pause_b;
		private JButton reset_b;
		
		/**
		 * Constructor made to grab pause and reset buttons to place under text
		 * 
		 * @author Jose Tua Colon
		 */
		public Comp(JButton pause, JButton reset) {
			this.pause_b = pause;
			this.reset_b = reset;
		}
		/**
		 * Resets the timer
		 * 
		 * @author Gabriel Soto Ramos
		 */
		public void reset() {
			t = System.currentTimeMillis();
			pT = 0;
			pause_time = 0;
		}

		/**
		 * Gets current miliseconds
		 * 
		 * @author Gabriel Soto Ramos
		 * @return long: Current miliseconds
		 */
		public long getMilis() {
			if (pause)
				pause_time = System.currentTimeMillis() - pT;
			long time = System.currentTimeMillis() - pause_time - t;
			if (time <= 0) {
				time = 0;
				reset();
			}
			return time;
		}

		/**
		 * Pauses the timer
		 * 
		 * @author Gabriel Soto Ramos
		 * @param button : The button that needs it's text changed from Pause to Continue and vice versa
		 */
		public void pause(JButton button) {
			if (!pause) {
				button.setText("Continue");
				pause = true;
				pT = System.currentTimeMillis();
			} else {
				button.setText("Pause");
				pause = false;
			}
		}

		/**
		 * Converts an int to a strings and adds a number of zeroes before it
		 * 
		 * @author Gabriel Soto Ramos
		 * @param n : The number to add the zeroes to
		 * @param zeroes : Number of zeroes before the number
		 * @return String
		 */
		public String fillWithZeroes(int n, int zeroes) {
			String number = n + "";
			int len = number.length();
			int dl = zeroes - len;
			if (dl > 0) {
				for (int i = 0; i < dl; i++) {
					number = "0" + number;
				}
			}
			return number;

		}

		/**
		 * Draws content on the JFrame
		 * 
		 * @author Gabriel Soto Ramos
		 * @author Jose Tua Colon
		 */
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			ms = getMilis();
			s = (int) Math.floor(ms / 1000.0);
			mins = (int) Math.floor(s / 60.0);
			hrs = (int) Math.floor(mins / 60.0);

			ms = ms % 1000;
			s = s % 60;
			mins = mins % 60;
			hrs = hrs % 100;

			String miliseconds = fillWithZeroes((int) ms, 3);
			String secs = fillWithZeroes(s, 2);
			String minutes = fillWithZeroes(mins, 2);
			String hours = fillWithZeroes(hrs, 3);

			g.setFont(bigFont);
			
			timer = hours + " : " + minutes + " : " + secs + " : " + miliseconds;
			timerText = "Timer Time";
			double xc = g.getFontMetrics().stringWidth(timer) / 2.0;
			double yc = g.getFontMetrics().getHeight() / 2.0;
			g.drawString(timer, (int) (this.getWidth() / 2.0 - xc), (int) (this.getHeight() / 2.0 + yc));
			g.drawString(timerText, (int) (this.getWidth() / 2.0 - xc + 35), (int) (this.getHeight() / 2.0 - yc - 5));
			pause_b.setBounds((int) (this.getWidth() / 2.0 - xc) - 20, (int) (this.getHeight()/ 2.0 + yc) + 30, 128, 64);
			reset_b.setBounds((int) (this.getWidth() / 2.0 - xc) + 200, (int) (this.getHeight()/ 2.0 + yc) + 30, 128, 64);
			
			g.drawRect((int) (this.getWidth() / 2.0 - xc) - 5, (int) (this.getHeight() / 2.0 - yc), 260, 50);
		}

	}

	public static void main(String[] args) {
		int frameWidth = 800, frameHeight = 600;
		JFrame frame = new JFrame();
		JButton pause_button = new JButton();
		JButton reset_button = new JButton();
		JComponent comp = new Comp(pause_button, reset_button);

		pause_button.addActionListener(new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				((Comp) comp).pause(pause_button);
			}

		});

		reset_button.addActionListener(new AbstractAction() {
			private static final long serialVersionUID = 2L;

			@Override
			public void actionPerformed(ActionEvent e) {
				((Comp) comp).reset();
			}

		});
		pause_button.setBounds(200, 200, 128, 64);
		reset_button.setBounds(400, 200, 128, 64);
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
