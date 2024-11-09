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
	private static final String FILE_PATH=" transaction_history.text";
	private static final SimpleDateFormat dateFormat=new SimpleDateFormat("yyy-mm-dd HH:mm:ss");
	
	//method to save transaction details
	public static void saveTransaction(String transactionType,int accountId,double amount) throws IOException
	{
		String timestamp=dateFormat.format(new Date());
		String record = String.format("%s | %s |Account ID: %d"+" | Amount: %.2f ",timestamp,transactionType,accountId,amount);
		try(BufferedWriter writer=new BufferedWriter(new FileWriter(FILE_PATH,true)))
		{
			writer.write(record);
			writer.newLine();
		}
		catch(IOException e)
		{
			System.err.println("Error writing to transaction history file");
		}
	}
//method to retrieve and display the transaction history

	public static List<String>retriveTransactionHistory() throws FileNotFoundException, IOException
	{
		List<String> history =new ArrayList<String>();
		
		try(BufferedReader reader=new BufferedReader(new FileReader(FILE_PATH)))
		{
			String line;
			while((line=reader.readLine())!=null){
				history.add(line);
			}
		}
		catch(IOException e)
		{
			System.err.println("Error reading transaction history file!");
		}
		return null;
	}
	
	
	public static void saveTransaction(String transactionType,int from_account,int to_account,double amount) throws IOException{
        String timestamp=dateFormat.format(new Date());
        String record=String.format("%s |%s| From AccountId: %d To Account %d "+"|Amount: %.2f",timestamp,transactionType,from_account,to_account,amount);
        try(BufferedWriter writer=new BufferedWriter(new FileWriter(FILE_PATH,true))){
            writer.write(record);
            writer.newLine();
            
        }
        catch(IOException e){
            System.err.println("Error writing to transcation history file");
        }
    }//transaction history
	
}