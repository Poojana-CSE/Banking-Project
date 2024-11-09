package utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionHistoryUtil {
	private static final String FILEPATH = "transactionHistory.txt";
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	//METHODS TO SAVE TRANSACTION DETAILS
	public static void saveTransaction(String transactionType, int accountId, double amount) throws IOException {
		String timeStamp = dateFormat.format(new Date());
		String record = String.format("%s | %s | Account Id: % | Amount: %.2f",timeStamp,transactionType,accountId, amount);
		
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILEPATH,true))){
			writer.write(record);
			writer.newLine();
		}
		catch(IOException e) {
			System.err.println("Error writing to transactionHistory.txt");
		}
		
		
	}
	
	
	//Method to retrive and display transaction history
	public static List<String> retriveTransactionHistory() throws FileNotFoundException, IOException{
		
		List<String> history = new ArrayList<String>();
		try(BufferedReader reader = new BufferedReader(new FileReader(FILEPATH))){
			
			String line;
			while((line = reader.readLine())!=null) {
				history.add(line);
			}
		} catch(IOException e) {
			System.err.println("Error reading to transactionHistory.txt");
		}
		
		return history;
	}
	
}
