package Package_Output;

import java.awt.Color;
import Package_GUI.Class_GUI;

public class Class_Indicators 
{
	// Retine tipul ledului obiectului: "error, warning, alarm". 
	protected String Led_IndicatorType;
	
	// Constructorul clasei initializeaza tipul ledului obiectului: error, warning, alarm.
	public Class_Indicators(String led_signal_type) 
	{
		this.Led_IndicatorType = led_signal_type;
	}
	
	// Transmite comenzi (culoare verde, rosu) LED-urilor/GUI (in cazul simulat).
	public void Write_LedIndicator(boolean value)
	{
		if (this.Led_IndicatorType == "Error")
		{
			if (value == false)
			{
				Class_GUI.Set_ErrorLedOutputVariable(Color.GREEN);
			}
			else
			{
				Class_GUI.Set_ErrorLedOutputVariable(Color.RED);
			}	
		}
		else if (this.Led_IndicatorType == "Warning")
		{
			if (value == false)
			{
				Class_GUI.Set_WarningLedOutputVariable(Color.GREEN);
			}
			else
			{
				Class_GUI.Set_WarningLedOutputVariable(Color.RED);
			}
		}
		else if (this.Led_IndicatorType == "Alarm")
		{
			if (value == false)
			{
				Class_GUI.Set_AlarmLedOutputVariable(Color.GREEN);
			}
			else
			{
				Class_GUI.Set_AlarmLedOutputVariable(Color.RED);
			}
		}
	}
	
	// Transmite comanda de afisare a altitudinii ceasului/GUI-ului (in cazul simulat).
	public static void Write_AltitudeIndicator(float value)
	{
		Class_GUI.Set_AltitudeOutputVariable(value);
	}
	
	// Transmite comanda de afisare a starii autopilotului/GUI-ului (in cazul simulat).
	public static void Write_AutopilotIndicator(boolean value)
	{
		if (value == false)
		{
			Class_GUI.Set_AutopilotOutputVariable("OFF");
		}
		else
		{
			Class_GUI.Set_AutopilotOutputVariable("ON");
		}
	}
}
