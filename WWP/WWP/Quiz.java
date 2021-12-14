// Author: Montaril
package WWP;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

// Game class.
public class Quiz implements ActionListener
{

	// Declare colors and font
	Color fg = new Color(0xD8DEE9);
	Color bg = new Color(0x2E3440);
	Color green = new Color(0xA3BE8C);
	Color red = new Color(0xBF616A);
	Color orange = new Color(0xD08770);
	
	Font sf_mono = new Font("SF Mono", Font.PLAIN, 25);

	// Declare set of questions
	String[] questions = 
	{
		"Capable of receiving messages, processing information or data, and sending messages to another class created at a runtime.",
		"A blueprint which describes methods, functions, and constructors.",
		"Wrapping Data in a single unit.",
		"The same class name can have many different functions.",
		"Getting data and commands into the computer.",
		"Getting your results out of the computer.",
		"Testing to see if a condition is true or false, and cycling through a set of instructions until some condition is met.",
		"What is the output of this code?\nwhile (false) \n{\n\tSystem.out.println(“hotdogs”);\n}",
		"It refers to a method of solving a problem where the solution depends on solutions to smaller instances of the same problem.",
		"What is the output of this code?\nfor(int i = 10; i >= 0; i--)\n{\n\tSystem.out.println(\"SPAM\");\n}",
	};

	String[][] options = 
	{
		{"Object","Encapsulation","Polymorphism","Class" },
		{"Encapsulation","Polymorphism","Class","Blueprint" },
		{"Class","Encapsulation","Object","Recursion" },
		{"Looping","Object","Polymorphism","Iteration" },
		{"Output","Input","Class","Object" },
		{"Input","Printing","Encapsulation","Output" },
		{"Nesting","Variable and Data Structure","Syntax","Looping and conditionals" },
		{"Syntax error","Infinite hotdogs","None","hotdogs" },
		{"Looping","Recursion","Nesting","If statements" },
		{"10 SPAMs","11 SPAMs","12 SPAMs","0 SPAMs" },
	};

	char[] answers = 
	{
		'A',
		'C',
		'B',
		'C',
		'B',
		'D',
		'D',
		'C',
		'B',
		'B',
	};

	char guess;
	char answer;
	int index;
	int correct_guesses =0;
	int total_questions = questions.length;
	int result;
	int seconds = 25;

	JFrame frame = new JFrame();
	JTextField textfield = new JTextField();
	JTextArea textarea = new JTextArea();

	ImageIcon Img = new ImageIcon("./resources/bg.png");
	JLabel bgImg = new JLabel("", Img, JLabel.CENTER);

	JButton buttonA = new JButton();
	JButton buttonB = new JButton();
	JButton buttonC = new JButton();
	JButton buttonD = new JButton();
	JButton buttonPlay = new JButton();
	JButton buttonExit = new JButton();

	JLabel answer_labelA = new JLabel();
	JLabel answer_labelB = new JLabel();
	JLabel answer_labelC = new JLabel();
	JLabel answer_labelD = new JLabel();
	JLabel time_label = new JLabel();
	JLabel seconds_left = new JLabel();

	JTextField number_right = new JTextField();
	JTextField percentage = new JTextField();

	Timer timer = new Timer(1000, new ActionListener() 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			seconds--;
			seconds_left.setText(String.valueOf(seconds));

			if(seconds<=0) 
			{
				textarea.setText(" TIMES UP!");
				textarea.setBorder(BorderFactory.createLineBorder(red, 3));
				seconds_left.setForeground(fg);
				seconds_left.setBackground(red);
				seconds_left.setBorder(BorderFactory.createLineBorder(red, 3));
				seconds_left.setText("!");
				displayAnswer();
			}
		}
	});

	public Quiz() 
	{
		// Background Image.
		bgImg.setBounds(0, 0, 1280, 720);

		// Foundation of the window.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1280, 750);
		frame.setTitle("Who Wants to be a Programmer?");
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setResizable(false);

		textfield.setBounds(50, 50, 700, 50);
		textfield.setBackground(bg);
		textfield.setForeground(fg);
		textfield.setFont(sf_mono);
		textfield.setBorder(BorderFactory.createLineBorder(fg, 3));
		textfield.setHorizontalAlignment(JTextField.CENTER);
		textfield.setEditable(false);

		// Text area for questions.
		textarea.setBounds(25, 25, 1131, 200);
		textarea.setLineWrap(true);
		textarea.setWrapStyleWord(true);
		textarea.setBackground(bg);
		textarea.setForeground(fg);
		textarea.setFont(sf_mono);
		textarea.setBorder(BorderFactory.createLineBorder(fg, 3));
		textarea.setEditable(false);

		// Button A.
		buttonA.setBounds(50, 250, 100, 100);
		buttonA.setFont(sf_mono);
		buttonA.setBackground(bg);
		buttonA.setForeground(fg);
		buttonA.setBorder(BorderFactory.createLineBorder(fg, 3));
		buttonA.setFocusable(false);
		buttonA.addActionListener(this);
		buttonA.setText("A");

		// Button B.
		buttonB.setBounds(50, 356,100,100);
		buttonB.setFont(sf_mono);
		buttonB.setBackground(bg);
		buttonB.setForeground(fg);
		buttonB.setBorder(BorderFactory.createLineBorder(fg, 3));
		buttonB.setFocusable(false);
		buttonB.addActionListener(this);
		buttonB.setText("B");

		// Button C.
		buttonC.setBounds(50, 462, 100, 100);
		buttonC.setFont(sf_mono);
		buttonC.setBackground(bg);
		buttonC.setForeground(fg);
		buttonC.setBorder(BorderFactory.createLineBorder(fg, 3));
		buttonC.setFocusable(false);
		buttonC.addActionListener(this);
		buttonC.setText("C");

		// Button D.
		buttonD.setBounds(50, 568, 100, 100);
		buttonD.setFont(sf_mono);
		buttonD.setBackground(bg);
		buttonD.setForeground(fg);
		buttonD.setBorder(BorderFactory.createLineBorder(fg, 3));
		buttonD.setFocusable(false);
		buttonD.addActionListener(this);
		buttonD.setText("D");

		// Play button.
		buttonPlay.setBounds(540, 290, 200, 100);
        buttonPlay.setFont(sf_mono);
        buttonPlay.setBackground(bg);
        buttonPlay.setForeground(fg);
		buttonPlay.setBorder(BorderFactory.createLineBorder(fg, 3));
        buttonPlay.setFocusable(false);
        buttonPlay.addActionListener(this);
        buttonPlay.setText("Play again");

		// Exit button.
        buttonExit.setBounds(540, 400, 200, 100);
        buttonExit.setFont(sf_mono);
        buttonExit.setBackground(bg);
        buttonExit.setForeground(fg);
		buttonExit.setBorder(BorderFactory.createLineBorder(fg, 3));
        buttonExit.setFocusable(false);
        buttonExit.addActionListener(this);
        buttonExit.setText("Exit game");

		// Text area for answer letter A.
		answer_labelA.setBounds(175, 250, 500, 100);
		answer_labelA.setBackground(bg);
		answer_labelA.setForeground(fg);
		answer_labelA.setFont(sf_mono);
		
		// Text area for answer letter B.
		answer_labelB.setBounds(175, 356, 500, 100);
		answer_labelB.setBackground(bg);
		answer_labelB.setForeground(fg);
		answer_labelB.setFont(sf_mono);

		// Text area for answer letter C.
		answer_labelC.setBounds(175, 462, 500, 100);
		answer_labelC.setBackground(bg);
		answer_labelC.setForeground(fg);
		answer_labelC.setFont(sf_mono);

		// Text area for answer letter D.
		answer_labelD.setBounds(175, 568, 500, 100);
		answer_labelD.setBackground(bg);
		answer_labelD.setForeground(fg);
		answer_labelD.setFont(sf_mono);
		
		// Timer text.
		seconds_left.setBounds(1155, 25, 100, 200);
		seconds_left.setBackground(fg);
		seconds_left.setForeground(bg);
		seconds_left.setFont(sf_mono);
		seconds_left.setBorder(BorderFactory.createLineBorder(fg, 3));
		seconds_left.setOpaque(true);
		seconds_left.setHorizontalAlignment(JTextField.CENTER);
		seconds_left.setText(String.valueOf(seconds));

		// Number of questions answered correctly.
		number_right.setBounds(527, 75, 100, 100);
		number_right.setBackground(bg);
		number_right.setForeground(fg);
		number_right.setFont(sf_mono);
		number_right.setBorder(BorderFactory.createLineBorder(fg, 3));
		number_right.setHorizontalAlignment(JTextField.CENTER);
		number_right.setEditable(false);

		// Overall score percentage.
		percentage.setBounds(653, 75, 100, 100);
		percentage.setBackground(bg);
		percentage.setForeground(fg);
		percentage.setFont(sf_mono);
		percentage.setBorder(BorderFactory.createLineBorder(fg, 3));
		percentage.setHorizontalAlignment(JTextField.CENTER);
		percentage.setEditable(false);

		// Add all objects to the frame.
		frame.add(buttonPlay);
		frame.add(buttonExit);
		frame.add(time_label);
		frame.add(seconds_left);
		frame.add(answer_labelA);
		frame.add(answer_labelB);
		frame.add(answer_labelC);
		frame.add(answer_labelD);
		frame.add(buttonA);
		frame.add(buttonB);
		frame.add(buttonC);
		frame.add(buttonD);
		frame.add(textarea);
		frame.add(bgImg);
		frame.setVisible(true);

		buttonPlay.setEnabled(false);
		buttonExit.setEnabled(false);
		buttonPlay.setVisible(false);
		buttonExit.setVisible(false);

		nextQuestion();
	}

	public void nextQuestion() 
	{
		if(index>=total_questions) 
		{
			results();
		}

		else 
		{
			textfield.setText("Question "+(index+1));
			textarea.setText(questions[index]);
			answer_labelA.setText(options[index][0]);
			answer_labelB.setText(options[index][1]);
			answer_labelC.setText(options[index][2]);
			answer_labelD.setText(options[index][3]);

			timer.start();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
			seconds_left.setForeground(fg);

			buttonA.setEnabled(false);
			buttonB.setEnabled(false);
			buttonC.setEnabled(false);
			buttonD.setEnabled(false);

			buttonA.setForeground(fg);
			buttonB.setForeground(fg);
			buttonC.setForeground(fg);
			buttonD.setForeground(fg);

			if(e.getSource()==buttonA) 
			{
				answer = 'A';
				if(answer == answers[index]) 
				{
					textarea.setBorder(BorderFactory.createLineBorder(green, 3));
					seconds_left.setBackground(green);
					seconds_left.setBorder(BorderFactory.createLineBorder(green, 3));
					seconds_left.setText(":)");
					correct_guesses++;
				}
				else
				{
					textarea.setBorder(BorderFactory.createLineBorder(red, 3));
					seconds_left.setBackground(red);
					seconds_left.setBorder(BorderFactory.createLineBorder(red, 3));
					seconds_left.setText(":(");
				}
			}

			if(e.getSource()==buttonB) 
			{
				answer= 'B';
				if(answer == answers[index]) 
				{
					textarea.setBorder(BorderFactory.createLineBorder(green, 3));
					seconds_left.setBackground(green);
					seconds_left.setBorder(BorderFactory.createLineBorder(green, 3));
					seconds_left.setText(":)");
					correct_guesses++;
				}
				else
				{
					textarea.setBorder(BorderFactory.createLineBorder(red, 3));
					seconds_left.setBackground(red);
					seconds_left.setBorder(BorderFactory.createLineBorder(red, 3));
					seconds_left.setText(":(");
				}
			}

			if(e.getSource()==buttonC) 
			{
				answer= 'C';
				if(answer == answers[index]) 
				{
					textarea.setBorder(BorderFactory.createLineBorder(green, 3));
					seconds_left.setBackground(green);
					seconds_left.setBorder(BorderFactory.createLineBorder(green, 3));
					seconds_left.setText(":)");
					correct_guesses++;
				}
				else
				{
					textarea.setBorder(BorderFactory.createLineBorder(red, 3));
					seconds_left.setBackground(red);
					seconds_left.setBorder(BorderFactory.createLineBorder(red, 3));
					seconds_left.setText(":(");
				}
			}

			if(e.getSource()==buttonD) 
			{
				answer= 'D';
				if(answer == answers[index]) 
				{
					textarea.setBorder(BorderFactory.createLineBorder(green, 3));
					seconds_left.setBackground(green);
					seconds_left.setBorder(BorderFactory.createLineBorder(green, 3));
					seconds_left.setText(":)");
					correct_guesses++;
				}
				else
				{
					textarea.setBorder(BorderFactory.createLineBorder(red, 3));
					seconds_left.setBackground(red);
					seconds_left.setBorder(BorderFactory.createLineBorder(red, 3));
					seconds_left.setText(":(");
				}
			}

			if(e.getSource()==buttonPlay)
			{
				Quiz quiz = new Quiz();
				frame.dispose();
			}
        
			if(e.getSource()==buttonExit)
			{
				System.exit(0);
			}

			displayAnswer();
	}

	public void displayAnswer() 
	{
		// Stops the timer when a button is clicked.
		timer.stop();
		
		// Prevents multiple answers.
		buttonA.setEnabled(false);
		buttonB.setEnabled(false);
		buttonC.setEnabled(false);
		buttonD.setEnabled(false);

		// Reveals correct answer after answering.
		if(answers[index] != 'A')
		{
			answer_labelA.setForeground(red);
			buttonA.setBackground(red);
			buttonA.setBorder(BorderFactory.createLineBorder(red, 3));
		}
		if(answers[index] != 'B')
		{
			answer_labelB.setForeground(red);
			buttonB.setBackground(red);
			buttonB.setBorder(BorderFactory.createLineBorder(red, 3));
		}
		if(answers[index] != 'C')
		{
			answer_labelC.setForeground(red);
			buttonC.setBackground(red);
			buttonC.setBorder(BorderFactory.createLineBorder(red, 3));
		}
		if(answers[index] != 'D')
		{
			answer_labelD.setForeground(red);
			buttonD.setBackground(red);
			buttonD.setBorder(BorderFactory.createLineBorder(red, 3));
		}
		if(answers[index] == 'A')
		{
			answer_labelA.setForeground(green);
			buttonA.setBackground(green);
			buttonA.setBorder(BorderFactory.createLineBorder(green, 3));
		}
		if(answers[index] == 'B')
		{
			answer_labelB.setForeground(green);
			buttonB.setBackground(green);
			buttonB.setBorder(BorderFactory.createLineBorder(green, 3));
		}
		if(answers[index] == 'C')
		{
			answer_labelC.setForeground(green);
			buttonC.setBackground(green);
			buttonC.setBorder(BorderFactory.createLineBorder(green, 3));
		}
		if(answers[index] == 'D')
		{
			answer_labelD.setForeground(green);
			buttonD.setBackground(green);
			buttonD.setBorder(BorderFactory.createLineBorder(green, 3));
		}
		// Pauses then proceeds to the next question.
		Timer pause = new Timer(2000, new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				answer_labelA.setForeground(fg);
				answer_labelB.setForeground(fg);
				answer_labelC.setForeground(fg);
				answer_labelD.setForeground(fg);

				buttonA.setBackground(bg);
				buttonB.setBackground(bg);
				buttonC.setBackground(bg);
				buttonD.setBackground(bg);
				buttonA.setBorder(BorderFactory.createLineBorder(fg, 3));
				buttonB.setBorder(BorderFactory.createLineBorder(fg, 3));
				buttonC.setBorder(BorderFactory.createLineBorder(fg, 3));
				buttonD.setBorder(BorderFactory.createLineBorder(fg, 3));

				seconds = 25;
				seconds_left.setText(String.valueOf(seconds));
				textarea.setBorder(BorderFactory.createLineBorder(fg, 3));
				seconds_left.setBackground(fg);
				seconds_left.setForeground(bg);
				seconds_left.setBorder(BorderFactory.createLineBorder(fg, 3));
				
				answer = ' ';

				buttonA.setEnabled(true);
				buttonB.setEnabled(true);
				buttonC.setEnabled(true);
				buttonD.setEnabled(true);

				index++;

				nextQuestion();
			}
		});
		
		pause.setRepeats(false);
		pause.start();
	}

	// Reveal scores to the player.
	public void results()
	{
		// Disables and hides the button.
		buttonA.setEnabled(false);
		buttonB.setEnabled(false);
		buttonC.setEnabled(false);
		buttonD.setEnabled(false);
		buttonA.setVisible(false);
		buttonB.setVisible(false);
		buttonC.setVisible(false);
		buttonD.setVisible(false);

		seconds_left.setText("");

		// Calculates the score.
		result = (int)((correct_guesses/(double)total_questions)*100);

		textarea.setBackground(fg);
		textarea.setText("");

		frame.remove(answer_labelA);
		frame.remove(answer_labelB);
		frame.remove(answer_labelC);
		frame.remove(answer_labelD);

		number_right.setText("("+correct_guesses+"/"+total_questions+")");
		percentage.setText(result+"%");

		frame.add(number_right);
		frame.add(percentage);

		buttonPlay.setEnabled(true);
		buttonExit.setEnabled(true);
		buttonPlay.setVisible(true);
		buttonExit.setVisible(true);
	}
}