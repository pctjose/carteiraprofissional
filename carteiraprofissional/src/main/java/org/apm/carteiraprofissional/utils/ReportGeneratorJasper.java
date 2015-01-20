package org.apm.carteiraprofissional.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.view.JasperViewer;
public class ReportGeneratorJasper {
	private static Connection connection;
	public static byte[] generateReport(String reportSource,Map<String,Object> params ) throws Exception{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/apmcarteira","root","DM2007Misau1");
			
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params,connection);
		
		connection.close();
		
		return JasperExportManager.exportReportToPdf(jasperPrint);
		
		//JasperViewer.viewReport(jasperPrint);
		
		
		
	}
	
	
	
}
