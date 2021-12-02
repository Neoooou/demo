package com.example.tutorial.basics;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

/**
 * @author: neo.zr
 * @since: 2021/5/8ø
 */

public class ExcelReaderDemo {

    public static void main(String[] args) throws IOException {
        String excelFileName = "resource/a.xlsx";
        FileInputStream fls = new FileInputStream(new File(excelFileName));
        // create work book instance that refers to excel file
        Workbook wb = null;
        Sheet sheet = null;
        if(excelFileName.endsWith(".xlsx")){
            wb = new XSSFWorkbook(fls);
        }else if(excelFileName.endsWith(".xls")){
            wb = new HSSFWorkbook(fls);
        }else{
            throw new RuntimeException("can not handle this file-type");
        }
        sheet = wb.getSheetAt(0);
        FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
        for(Row row:sheet){
            for(Cell cell: row){
                switch(formulaEvaluator.evaluateInCell(cell).getCellType()){
                    case NUMERIC:
                        System.out.print(cell.getNumericCellValue() + "\t\t");
                        break;
                    case STRING:
                        System.out.print(cell.getStringCellValue() + "\t\t");
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + formulaEvaluator.evaluateInCell(cell).getCellType());
                }
            }
            System.out.println();
        }


    }

     class A{

    }

    interface B extends Iterable<A>{


    }

    class C implements B{


        @Override
        public Iterator<A> iterator() {
            return null;
        }
    }
}
