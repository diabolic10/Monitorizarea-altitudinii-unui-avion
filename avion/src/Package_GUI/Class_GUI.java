package Package_GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class Class_GUI extends JFrame 
{
	protected static final long serialVersionUID = 1L;
	
	protected final JPanel contentPane;
	protected final JTextField Altitude_Output_Text = new JTextField();
	protected final JTextField Alarm_Led_Text = new JTextField();
	protected final JTextField Warning_Led_Text = new JTextField();
	protected final JTextField Error_Led_Text = new JTextField();
	protected final JTextField Altitude_Input_Text = new JTextField();
	protected final JTextField Autopilot_Output_Text = new JTextField();
	
	protected static JLabel Error_Led_Output_Variable = new JLabel("\u2022");
	protected static JLabel Warning_Led_Output_Variable = new JLabel("\u2022");
	protected static JLabel Alarm_Led_Output_Variable = new JLabel("\u2022");
	protected static JTextField Altitude_Output_Variable = new JTextField();
	protected static JTextField Altitude_Input_Variable = new JTextField();
	protected static JTextField Autopilot_Output_Variable = new JTextField();
	protected static JButton Acquittal_Input_Variable = new JButton("Acquittal");

	protected static float Previous_Altitude_Value = 0;
	
	// Creeaza frame-ul GUI-ului si il afiseaza pe ecran
	public static void Application_Launch() 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Class_GUI frame = new Class_GUI();
					frame.setVisible(true);
				} 
				catch (Exception e) { }
			}
		});
	}

	// Constructorul clasei initializeaza elementele GUI-ului.
	public Class_GUI() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(130, 130, 385, 229);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);	
		
		Error_Led_Output_Variable.setHorizontalAlignment(SwingConstants.CENTER);
		Error_Led_Output_Variable.setFont(new Font("Tahoma", Font.PLAIN, 50));
		Error_Led_Output_Variable.setForeground(Color.GREEN);
		Error_Led_Output_Variable.setBounds(295, 20, 45, 36);
		contentPane.add(Error_Led_Output_Variable);
		
		Warning_Led_Output_Variable.setHorizontalAlignment(SwingConstants.CENTER);
		Warning_Led_Output_Variable.setFont(new Font("Tahoma", Font.PLAIN, 50));
		Warning_Led_Output_Variable.setForeground(Color.GREEN);
		Warning_Led_Output_Variable.setBounds(295, 87, 45, 36);
		contentPane.add(Warning_Led_Output_Variable);
		
		Alarm_Led_Output_Variable.setHorizontalAlignment(SwingConstants.CENTER);
		Alarm_Led_Output_Variable.setFont(new Font("Tahoma", Font.PLAIN, 50));
		Alarm_Led_Output_Variable.setForeground(Color.GREEN);
		Alarm_Led_Output_Variable.setBounds(295, 153, 45, 30);
		contentPane.add(Alarm_Led_Output_Variable);
		
		Altitude_Output_Text.setHorizontalAlignment(SwingConstants.CENTER);
		Altitude_Output_Text.setText("Altitude Output");
		Altitude_Output_Text.setEditable(false);
		Altitude_Output_Text.setBounds(165, 2, 84, 20);
		Altitude_Output_Text.setColumns(10);
		contentPane.add(Altitude_Output_Text);
		
		Altitude_Output_Variable.setHorizontalAlignment(SwingConstants.CENTER);	
		Altitude_Output_Variable.setEditable(false);
		Altitude_Output_Variable.setColumns(10);
		Altitude_Output_Variable.setBounds(165, 33, 84, 20);
		contentPane.add(Altitude_Output_Variable);
		
		Altitude_Input_Variable.setText("9000");	
		Altitude_Input_Variable.setHorizontalAlignment(SwingConstants.CENTER);
		Altitude_Input_Variable.setBounds(10, 31, 84, 20);
		Altitude_Input_Variable.setColumns(10);
		contentPane.add(Altitude_Input_Variable);
		
		Alarm_Led_Text.setHorizontalAlignment(SwingConstants.CENTER);
		Alarm_Led_Text.setText("Alarm LED");
		Alarm_Led_Text.setEditable(false);
		Alarm_Led_Text.setColumns(10);
		Alarm_Led_Text.setBounds(275, 129, 84, 20);
		contentPane.add(Alarm_Led_Text);
		
		Warning_Led_Text.setText("Warning LED");
		Warning_Led_Text.setHorizontalAlignment(SwingConstants.CENTER);
		Warning_Led_Text.setEditable(false);
		Warning_Led_Text.setColumns(10);
		Warning_Led_Text.setBounds(275, 66, 84, 20);
		contentPane.add(Warning_Led_Text);
		
		Error_Led_Text.setText("Error LED");
		Error_Led_Text.setHorizontalAlignment(SwingConstants.CENTER);
		Error_Led_Text.setEditable(false);
		Error_Led_Text.setColumns(10);
		Error_Led_Text.setBounds(275, 2, 84, 20);
		contentPane.add(Error_Led_Text);
		
		Altitude_Input_Text.setText("Altitude Input");
		Altitude_Input_Text.setHorizontalAlignment(SwingConstants.CENTER);
		Altitude_Input_Text.setEditable(false);
		Altitude_Input_Text.setColumns(10);
		Altitude_Input_Text.setBounds(10, 0, 84, 20);
		contentPane.add(Altitude_Input_Text);
			
		Acquittal_Input_Variable.setBounds(10, 126, 84, 23);
		contentPane.add(Acquittal_Input_Variable);
		
		Autopilot_Output_Text.setText("Autopilot State");
		Autopilot_Output_Text.setHorizontalAlignment(SwingConstants.CENTER);
		Autopilot_Output_Text.setEditable(false);
		Autopilot_Output_Text.setColumns(10);
		Autopilot_Output_Text.setBounds(165, 129, 84, 20);
		contentPane.add(Autopilot_Output_Text);
		
		Autopilot_Output_Variable.setText("OFF");
		Autopilot_Output_Variable.setHorizontalAlignment(SwingConstants.CENTER);
		Autopilot_Output_Variable.setEditable(false);
		Autopilot_Output_Variable.setColumns(10);
		Autopilot_Output_Variable.setBounds(165, 163, 84, 20);
		contentPane.add(Autopilot_Output_Variable);
	}

	// Modifica valoarea "ledului" asociat semnalului error
	public static void Set_ErrorLedOutputVariable(Color color)
	{	
		try
		{
			Error_Led_Output_Variable.setForeground(color);
		}
		catch (Exception e) { }
	}
	
	// Modifica valoarea "ledului" asociat semnaluluiwarning
	public static void Set_WarningLedOutputVariable(Color color)
	{
		try
		{
			Warning_Led_Output_Variable.setForeground(color);
		}
		catch (Exception e) { }
	}
	
	// Modifica valoarea "ledului" asociat semnalului alarm
	public static void Set_AlarmLedOutputVariable(Color color)
	{
		try
		{
			Alarm_Led_Output_Variable.setForeground(color);
		}
		catch (Exception e) { }
	}
	
	// Modifica valoarea altitudinii afisate pe display
	public static void Set_AltitudeOutputVariable(float value)
	{
		try
		{
			Altitude_Output_Variable.setText(Float.toString(value));
		}
		catch (Exception e) { }	
	}
	
	// Modifica valoarea starii pilotului automat afisata
	public static void Set_AutopilotOutputVariable(String value)
	{
		try
		{
			Autopilot_Output_Variable.setText(value);
		}
		catch (Exception e) { }	
	}
	
	// Citeste si returneaza valoarea altitudinii de la senzorul de altitudine
	public static float Get_AltitudeInputVariable()
	{
		float Current_Altitude = Previous_Altitude_Value;
		
		try
		{
			Current_Altitude = Float.parseFloat(Altitude_Input_Variable.getText());
			Previous_Altitude_Value = Current_Altitude;
		}
		catch (Exception e) { }
		
		return Current_Altitude;
	}
	
	// Citeste si returneaza starea achitarii de la senzor
	public static boolean Get_AcquittalInputVariable()
	{
		boolean Current_Acquittal_State = false;
		
		try
		{
			Current_Acquittal_State = Acquittal_Input_Variable.getModel().isPressed();
		}
		catch (Exception e) { }
		
		return Current_Acquittal_State;
	}
}
