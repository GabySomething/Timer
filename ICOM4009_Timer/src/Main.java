import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.geom.Rectangle2D;
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
		private JButton pause_button;
		private JButton reset_button;
		int button_width = 150;
		int button_height = 50;

		/**
		 * Constructor made to grab pause and reset buttons to place under text
		 * 
		 * @author Jose Tua Colon
		 */
		public Comp(JButton pause, JButton reset, Timer timer) {
			this.pause_button = pause;
			this.reset_button = reset;
			this.timer = timer;
		}

		/**
		 * Draws content on the JFrame
		 * 
		 * @author Gabriel Soto Ramos
		 * @author Jose Tua Colon
		 * @author Mario Rodriguez
		 * @author Bernardo Jr. Sein Acevedo
		 */
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			// Component Width
			int frame_width = this.getWidth();
			// Components Height
			int frame_height = this.getHeight();

			//Creates a gradient background
			Graphics2D g2 = (Graphics2D) g;
			GradientPaint bkgColor = new GradientPaint(0, 0, new Color(20,20,20), 0, frame_height, Color.DARK_GRAY);
			g2.setPaint(bkgColor);
			g2.fill(new Rectangle2D.Double(0, 0, frame_width, frame_height));
			
			g.setColor(Color.WHITE);
			timer.update();
			if (timer.isPaused())
				pause_button.setText("Run");
			else
				pause_button.setText("Pause");
			g.setFont(bigFont);
			timer_text = timer.getTimer();

			double timer_half_width = g.getFontMetrics().stringWidth(timer_text) / 2.0;
			double text_half_height = g.getFontMetrics().getHeight() / 2.0;
			
			g.drawString(timer_text, (int) (frame_width / 2.0 - timer_half_width),
					(int) (frame_height / 2.0 + text_half_height));

			pause_button.setBounds((int) (frame_width / 2.0 - timer_half_width) - button_width / 2,
					(int) (frame_height / 2.0 + text_half_height) + 30, button_width, button_height);
			reset_button.setBounds((int) (frame_width / 2.0 + timer_half_width) - button_width / 2,
					(int) (frame_height / 2.0 + text_half_height) + 30, button_width, button_height);

			int number_spacing = g.getFontMetrics().stringWidth("0");
			int milli_spacing = number_spacing * 3;
			number_spacing *= 2; // "Its in format: 00"
			int blank_spacing = g.getFontMetrics().stringWidth(" : ");

			g.setFont(smallFont);
			String text_Hours = "Hrs";
			String text_Mins = "Mins";
			String text_Secs = "Secs";
			String text_Millis = "Millis";
			int width_text_Hours = g.getFontMetrics().stringWidth(text_Hours);
			int width_text_Mins = g.getFontMetrics().stringWidth(text_Mins);
			int width_text_Secs = g.getFontMetrics().stringWidth(text_Secs);
			int width_text_Millis = g.getFontMetrics().stringWidth(text_Millis);

			g.drawString(text_Hours, (int) ((frame_width + number_spacing - width_text_Hours) / 2 - timer_half_width),
					(int) (frame_height / 2 - text_half_height));
			g.drawString(text_Mins,
					(int) ((frame_width - width_text_Mins + width_text_Hours) / 2 - timer_half_width + blank_spacing + number_spacing),
					(int) (frame_height / 2 - text_half_height));
			g.drawString(text_Secs, (int) ((frame_width - width_text_Secs + width_text_Hours + width_text_Mins) / 2 - timer_half_width
					+ blank_spacing + 2 * number_spacing + 10), (int) (frame_height / 2 - text_half_height));
			g.drawString(text_Millis,
					(int) ((frame_width / 2 + timer_half_width - width_text_Millis / 2 - milli_spacing / 2)),
					(int) (frame_height / 2 - text_half_height));

			int number_boxes = 9;
			int box_width = 20, box_height = frame_height / number_boxes;
			int x = frame_width - box_width;

			for (int i = 0; i < timer.getSeconds() % (number_boxes + 1); i++) {

				int y = frame_height - (i + 1) * box_height;
				if (i >= 6) {
					g.setColor(new Color(255-(i-6)*(255/3), 0, 0));
				} else if (i >= 3) {
					g.setColor(new Color(255, 255-(i-3)*(255/3), 0));
				} else {
					g.setColor(new Color(i*(255/3), 255, 0));

				}
				g.fillRect(x, y, box_width, box_height);
				g2.setPaint(bkgColor);
				g2.drawRect(x, y, box_width, box_height);

			}

		}

	}

	/**
	 * @author Gabriel Soto Ramos
	 */
	public static void main(String[] args) {
		int frameWidth = 500, frameHeight = 300, refresh_speed = 15; //refresh_speed in milliseconds
		JFrame frame = new JFrame();
		frame.setResizable(false);
		JButton pause_button = new JButton();
		JButton reset_button = new JButton();
		Timer timer = new Timer(refresh_speed);
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
		pause_button.setForeground(Color.GREEN);
		pause_button.setBackground(new Color(40, 40, 40));
		reset_button.setText("Reset");
		reset_button.setForeground(Color.RED);
		reset_button.setBackground(new Color(40, 40, 40));
		frame.setTitle("Timer");
		frame.setSize(frameWidth, frameHeight);
		frame.add(comp);
		comp.add(pause_button);
		comp.add(reset_button);
		comp.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Centers the window on screen
		frame.setLocationRelativeTo(null); 
		frame.setVisible(true);

		while (true) {
			frame.repaint();
			try {
				Thread.sleep(refresh_speed+refresh_speed);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	

}
