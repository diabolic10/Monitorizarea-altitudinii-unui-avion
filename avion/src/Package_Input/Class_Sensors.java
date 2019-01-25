package Package_Input;

import Package_GUI.Class_GUI;

public class Class_Sensors 
{
	// Citeste si returneaza valoarea altitudinii de la senzor/GUI (in cazul simulat)
	public static float Read_AltitudeSensor()
	{
		return Class_GUI.Get_AltitudeInputVariable();
	}	

	// Citeste si returneaza valoarea starii de achitare de la senzor/GUI (in cazul simulat)
	public static boolean Read_AcquittalSensor()
	{
		return Class_GUI.Get_AcquittalInputVariable();
	}
}
