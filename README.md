# Timer

This is a simple timer made in Java.

## Branch explanations

- Threaded:
   - The timer uses thread speed for the millisecond updates.
      ```java
      milliseconds += 15;
      ```
- Backup:
  - The timer uses the system time for the millisecond updates.
     ```java
     milliseconds = System.currentTimeMillis() - t; //t = start time
     ```
  - Attention: Using this branch in a Windows computer may output wrong values in the milliseconds when multiple instances of the program are running at the same time; as the system time for some reason changes randomly between applications.
- Master (Default):
  - It is currently the same as the Threaded branch.

## Usage

The program is really simple and self explanatory: press the run button to start the timer, pause to pause it and reset to reset it.
The run button changes itself to a pause button when the timer is running.
