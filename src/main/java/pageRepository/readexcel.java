package pageRepository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class readexcel {

	 private static XSSFSheet ExcelWSheet;
	 private static XSSFWorkbook ExcelWBook;
	 
     private static XSSFCell Cell;

     private static XSSFRow Row;
     
       
     public static void setExcelFile(String Path,String SheetName) throws Exception{
    	 
    	 try{
    		 FileInputStream ExcelFile = new FileInputStream(Path);

				ExcelWBook = new XSSFWorkbook(ExcelFile);

				ExcelWSheet = ExcelWBook.getSheet(SheetName);

				} catch (Exception e){

					throw (e);

				}

		}
     
      
     public static String getCellData(int RowNum, int ColNum) throws Exception{
    	 
         try{
   
             Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
   
             String CellData = Cell.getStringCellValue();
   
             return CellData;
   
             }catch (Exception e){
   
            	 return"";
   
             }
   
       }
     
     public static void deleteRow(String path) throws FileNotFoundException, IOException{
    	 
    	 int rowNo=1;
    	 System.out.println("Inside delete row function");
    	 int lastRowNum=ExcelWSheet.getLastRowNum();
    	 System.out.println("Last row num "+lastRowNum);
    	   	 
    	 if(rowNo>=0 && rowNo<lastRowNum){
    		ExcelWSheet.shiftRows(rowNo + 1, lastRowNum, -1);
    		       		
    	}
    	 else if(rowNo==lastRowNum){
    		 XSSFRow row = ExcelWSheet.getRow(1);
    		 ExcelWSheet.removeRow(row);
    		 System.out.println("Deleted the last credentials from excel");
    	}
    	 else{
    		 System.out.println("Reached EOF");
    	 }
    	 
    	 FileOutputStream fileOut = new FileOutputStream(path);
    	 ExcelWBook.write(fileOut);
    	 fileOut.flush();
    	 fileOut.close();
    		
   
     }
     

     }

