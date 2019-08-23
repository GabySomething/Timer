/**
 *
 * A simple timer that can be paused and resetted
 * 
 * @author Gabriel Soto Ramos
 *
 */
public class Timer {

	private long t = System.currentTimeMillis();
	private long pT = 0;
	private long pause_time = 0;
	private String timer = "";
	private int mins = 0;
	private long ms = 0;
	private int s = 0;
	private int hrs = 0;
	private boolean pause = true;

	/**
	 * Resets the timer back to 0
	 * 
	 * @author Gabriel Soto Ramos
	 */
	public void reset() {
		t = System.currentTimeMillis();
		pT = 0;
		pause_time = 0;
	}

	/**
	 * Gets current milliseconds
	 * 
	 * @author Gabriel Soto Ramos
	 * @return long: Current milliseconds
	 */
	public long getMillis() {
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
	 * Gets current seconds
	 * 
	 * @author Gabriel Soto Ramos
	 * @return int: Current seconds
	 */
	public int getSeconds() {
		return (int) Math.floor(this.getMillis() / 1000) % 60;
	}

	/**
	 * Gets current minutes
	 * 
	 * @author Gabriel Soto Ramos
	 * @return int: Current minutes
	 */
	public int getMinutes() {
		return (int) Math.floor(this.getSeconds() / 60) % 60;
	}

	/**
	 * Gets current hours
	 * 
	 * @author Gabriel Soto Ramos
	 * @return int: Current hours
	 */
	public int getHours() {
		return (int) Math.floor(this.getMinutes() / 60) % 24;
	}

	/**
	 * Pauses the timer
	 * 
	 * @author Gabriel Soto Ramos
	 * @param button
	 *            : The button that needs it's text changed from Pause to Continue
	 *            and vice versa
	 */
	public void pause() {
		if (!pause) {
			pause = true;
			pT = System.currentTimeMillis();
		} else {
			pause = false;
		}
	}

	/**
	 * Converts an int to a strings and adds a number of zeroes before it
	 * 
	 * @author Gabriel Soto Ramos
	 * @param n
	 *            : The number to add the zeroes to
	 * @param zeroes
	 *            : Number of zeroes before the number
	 * @return String
	 */
	private String fillWithZeroes(int n, int zeroes) {
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
	 * Updates the timer
	 * 
	 * @author Gabriel Soto Ramos
	 */
	public void update() {
		ms = getMillis();
		s = (int) Math.floor(ms / 1000.0);
		mins = (int) Math.floor(s / 60.0);
		hrs = (int) Math.floor(mins / 60.0);

		ms = ms % 1000;
		s = s % 60;
		mins = mins % 60;
		hrs = hrs % 24;

		String milliseconds = fillWithZeroes((int) ms, 3);
		String secs = fillWithZeroes(s, 2);
		String minutes = fillWithZeroes(mins, 2);
		String hours = fillWithZeroes(hrs, 2);

		timer = hours + " : " + minutes + " : " + secs + " : " + milliseconds;
	}

	/**
	 * Gets the timer in hrs:mins:secs:ms
	 * 
	 * @author Gabriel Soto Ramos
	 * @return String
	 */
	public String getTimer() {
		this.update();
		return timer;
	}

	/**
	 * Gets the timer in hrs:mins:secs:ms
	 * 
	 * @author Gabriel Soto Ramos
	 * @param update
	 *            : Change this to false or leave it blank to get the timer without
	 *            updating it
	 * @return String
	 */
	public String getTimer(boolean update) {
		if (update)
			this.update();
		return timer;
	}

	/**
	 * Check if the timer is paused
	 * 
	 * @author Gabriel Soto Ramos
	 * @return boolean
	 */
	public boolean isPaused() {
		return pause;
	}

}
