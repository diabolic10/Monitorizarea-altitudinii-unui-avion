package Package_Monitor;

import java.util.concurrent.TimeUnit;

import Package_GUI.Class_GUI;
import Package_Input.Class_Sensors;
import Package_Output.Class_Indicators;

public class Class_AltitudeMonitor 
{
	protected static final float MIN_ALTITUDE = 0;
	protected static final float MAX_ALTITUDE = 15000;
	protected static final int MAX_WRONG_READS = 3;
	protected static final float MAX_WARNING_ALTITUDE = 8000;
	protected static final float MAX_ALARM_ALTITUDE = 5000;
	protected static final float AUTOPILOT_DISABLE_ALTITUDE = 9000;
	
	protected static int Counter_Wrong_Value_Altitude_Sensor = 0;
	
	protected static float Altitude_Input_Value = 0;
	protected static boolean Acquittal_Input_Value = false;
	protected static boolean Acquittal_In_Warning_Zone_Requested = false;
	protected static boolean Acquittal_In_Alarm_Zone_Requested = false;
	protected static boolean Autopilot_Output_Value = false;
	
	protected static Class_Indicators Error_Led_Output_Indicator = new Class_Indicators("Error");
	protected static Class_Indicators Warning_Led_Output_Indicator = new Class_Indicators("Warning");
	protected static Class_Indicators Alarm_Led_Output_Indicator = new Class_Indicators("Alarm");
	
	protected static boolean Error_Led_Output_Value = false;
	protected static boolean Warning_Led_Output_Value = false;
	protected static boolean Alarm_Led_Output_Value = false;
	
	protected static float Altitude_Output_Value = 0;
	protected static float Previous_Altitude_Input_Value = 0;
	
	public static void Application_AltitudeMonitor()
	{
		// Valorile senzorilor sunt citite la intervale de o secunda
		try 
		{
			// asteapta o secunda
			TimeUnit.SECONDS.sleep(1);
		} 
		catch (InterruptedException e) { }
		
		// Altitudinea este citita cu ajutorul unui "senzor" (GUI).
		Altitude_Input_Value = Class_Sensors.Read_AltitudeSensor();
		Acquittal_Input_Value = Class_Sensors.Read_AcquittalSensor();
		
		if (Acquittal_Input_Value == true)
		{
			if (Altitude_Input_Value < MAX_ALARM_ALTITUDE)
			{
				Acquittal_In_Alarm_Zone_Requested = true;
			}
			else if (Altitude_Input_Value < MAX_WARNING_ALTITUDE)
			{
				Acquittal_In_Warning_Zone_Requested = true;
			}
		}
		
		// Toate valorile citite trebuie sa fie intr-un interval predefinit... 
		if ( (Altitude_Input_Value < MIN_ALTITUDE) || (Altitude_Input_Value > MAX_ALTITUDE))
		{
			// ...Valorile in afara intervalului sunt ignorate.
			Altitude_Input_Value = Previous_Altitude_Input_Value;
			Counter_Wrong_Value_Altitude_Sensor++;
		}
		else
		{
			Previous_Altitude_Input_Value = Altitude_Input_Value;
			Counter_Wrong_Value_Altitude_Sensor = 0;
		}
		
		// Cand trei citiri succesive ale unui senzor genereaza valori in afara intervalului predefinit... 
		if (Counter_Wrong_Value_Altitude_Sensor == MAX_WRONG_READS)
		{
			// ...un led, ce corespunde senzorului respectiv, isi schimba culoarea din verde in rosu (se genereaza ERROR)
			Error_Led_Output_Value = true;
		}
		
		// In functie de valorile citite se pot genera semnale WARNING sau ALARM
		
		// Atunci cand altitudinea este este mai mica decat 8000m, un semnal WARNING este generat
		// Dupa achitare un nou WARNING va fi generat doar daca a existat cel putin o citire pentru care nu s-a generat WARNING sau ALARM.
		// Cand un semnal WARNING este generat...
		if ( (Altitude_Input_Value < MAX_ALARM_ALTITUDE) && (Acquittal_In_Alarm_Zone_Requested == false) )
		{
			// ...un led isi schimba culoarea din verde in rosu.
			Warning_Led_Output_Value = true;
		}
		else if ( (Altitude_Input_Value >= MAX_ALARM_ALTITUDE) && (Altitude_Input_Value < MAX_WARNING_ALTITUDE) && (Acquittal_In_Warning_Zone_Requested == false) )
		{
			// ...un led isi schimba culoarea din verde in rosu.
			Warning_Led_Output_Value = true;
		}
		else
		{
			// Un WARNING persista pana cand este achitat de pilot sau dispar conditiile care l-au generat.
			// Daca semnalul WARNING este achitat atunci se activeaza pilotul automat.
			if (Altitude_Input_Value >= MAX_WARNING_ALTITUDE)
			{
				Acquittal_In_Alarm_Zone_Requested = false;
				Acquittal_In_Warning_Zone_Requested = false;
			}
			else if (Acquittal_In_Warning_Zone_Requested == true)
			{
				Autopilot_Output_Value = true;
			}
			
			Warning_Led_Output_Value = false;
		}		
		
		// Atunci cand altitudinea este este mai mica decat 5000, un semnal ALARM este generat
		// Un ALARM persista pana cand dispar conditiile care l-au generat
		// Dupa aparitia semnalului ALARM se activeaza pilotul automat.
		// Cand un semnal ALARM este generat...
		if (Altitude_Input_Value < MAX_ALARM_ALTITUDE)
		{
			// ...un led isi schimba culoarea din verde in rosu.
			Alarm_Led_Output_Value = true;
			Autopilot_Output_Value = true;
		}
		else
		{
			Alarm_Led_Output_Value = false;
		}
		
		// Pilotul automat se dezactiveaza daca altitudinea este mai mare decat 9000m
		if (Altitude_Input_Value > AUTOPILOT_DISABLE_ALTITUDE)
		{
			Autopilot_Output_Value = false;
		}	
		
		Error_Led_Output_Indicator.Write_LedIndicator(Error_Led_Output_Value);
		Warning_Led_Output_Indicator.Write_LedIndicator(Warning_Led_Output_Value);
		Alarm_Led_Output_Indicator.Write_LedIndicator(Alarm_Led_Output_Value);
		
		// Altitudinea este afisata pe un "ecran de tip ceas" (GUI)
		Altitude_Output_Value = Altitude_Input_Value;
		Class_Indicators.Write_AltitudeIndicator(Altitude_Output_Value);
		
		// afiseaza starea pilotului automat in GUI
		Class_Indicators.Write_AutopilotIndicator(Autopilot_Output_Value);
	}
	
	public static void main(String[] args) 
	{
		Class_GUI.Application_Launch();
		
		while(true)
		{
			Application_AltitudeMonitor();
		}
	}
}
