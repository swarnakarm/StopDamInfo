package com.manish.misc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class InsertDB {

	private static final String EXTENSION_CSV = ".csv";
	
	public static void main(String args[]) throws Exception{
		
		File csvFolder = new File("csv");
		String csvFiles[] = csvFolder.list();
		Connection conn = null;
		try{
			String url = "jdbc:mysql://localhost:3306/GramYojana";
			Class.forName ("com.mysql.jdbc.Driver").newInstance ();
			conn = DriverManager.getConnection (url, "manish", "swarnakar");
			for(String csv:csvFiles){
				FileReader fread =  null;
				BufferedReader bfread = null;
				PreparedStatement pstmt = null;
				boolean isMultiTableCSV = false;
				if(csv.contains("_")){
					isMultiTableCSV = true;
				}else{
					continue;
				}
				try{
					File csvFile  = new File("csv/"+csv);
					System.out.println("CSV File: "+csv);
					fread = new FileReader(csvFile);
					bfread = new BufferedReader(fread);
					csv = csv.substring(0,csv.indexOf(EXTENSION_CSV));
					String line = bfread.readLine();
					pstmt = conn.prepareStatement("INSERT INTO "+csv+"("+line+") VALUES (?,?)");
					while((line = bfread.readLine())!=null){
						String insertArr[] = line.split(",");
						pstmt.setLong(1,Long.parseLong(insertArr[0].trim()));
						if(isMultiTableCSV){
							pstmt.setLong(2, Long.parseLong(insertArr[1].trim()));
						}else{
//							pstmt.setString(2, insertArr[1]);
						}
						pstmt.addBatch();
					}
					int row[]=pstmt.executeBatch();
					System.out.println("No. of Entries in "+csv+" table: "+row.length);
				}catch(Exception e){
					System.err.println(e.getMessage());
					e.printStackTrace();
				}finally{
					if(pstmt!=null){
						try{
							pstmt.close();
						}catch(Exception e){
							e.printStackTrace();
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(conn!=null){
				try{
					conn.close();
				}catch(Exception e1){
					e1.printStackTrace();
				}
			}
		}
	}
}
