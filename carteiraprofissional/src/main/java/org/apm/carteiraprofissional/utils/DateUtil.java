package org.apm.carteiraprofissional.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static String ptDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int ano = c.get(Calendar.YEAR);
		int mes = c.get(Calendar.MONTH);
		int dia = c.get(Calendar.DAY_OF_MONTH);

		String diaS = String.valueOf(dia);
		if (dia <= 9)
			diaS = "0" + diaS;

		String mesS = String.valueOf(mes);
		if (mes <= 9)
			mesS = "0" + mesS;

		String dataNascpt = diaS + "-" + mesS + "-" + ano;

		return dataNascpt;

	}
}
