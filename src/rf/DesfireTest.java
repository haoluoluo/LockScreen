package rf.example;
import java.util.Scanner;

import jcumf.umf_javacall;

public class DesfireTest {
	
    static byte fileSize_standard = (byte)0x20;
    static byte recordSize = (byte)0x0A;
	
	public static void printHexString( byte[] b, int length) { 
		for (int i = 0; i < length; i++) { 
		String hex = Integer.toHexString(b[i] & 0xFF); 
		if (hex.length() == 1) { 
		hex = '0' + hex; 
		} 
		System.out.print(hex.toUpperCase() ); 
		} 
		
		System.out.printf("\n");
	}
	
	public static void testFile(int icdev, byte fID, byte fType, byte[] pSessionKey)
	{
		int i,status;
		byte[] wdata = new byte[1024];
		byte[] rdata = new byte[1024];
		int[] recLen = new int[2];
		int[] rlen = new int[2];
		int[] value = new int[2];
		int  credit = 200;
		int  debit = 100;
		
        switch(fType)
        {
        case 0x00://common standard file
        	
        	for(i=0;i<(int)(fileSize_standard); i++) wdata[i] = (byte)(i + 1);
        	
        	status = umf_javacall.fw_write_desfire(icdev, fID, 0, fileSize_standard, wdata, pSessionKey);
            if (status != 0) {
                System.out.print("fw_write_desfire error!\n");
                System.out.print(status + "\n");
                break;
            }
            System.out.printf("standard file write ok \n");
            
        	status = umf_javacall.fw_read_desfire(icdev, fID, 0, fileSize_standard, rdata, pSessionKey);
            if (status != 0) {
                System.out.print("fw_read_desfire error!\n");
                System.out.print(status + "\n");
                break;
            }
            System.out.printf("standard file read ok \n");
            printHexString(rdata, fileSize_standard);
            
            break;
        case 0x04://record file
        	
        	for(i=0;i<recordSize; i++) wdata[i] = (byte)(i + 1);
        	
        	status = umf_javacall.fw_writeRecord_desfire(icdev, fID, 0, recordSize, wdata, pSessionKey);
            if (status != 0) {
                System.out.print("fw_writeRecord_desfire error!\n");
                System.out.print(status + "\n");
                break;
            }
            umf_javacall.fw_commitTransfer_desfire(icdev);
            System.out.printf("record file write ok \n");
            
            
        	status = umf_javacall.fw_readRecord_desfire(icdev, fID, 0, 1, rdata, recLen, rlen, pSessionKey);
            if (status != 0) {
                System.out.print("fw_readRecord_desfire error!\n");
                System.out.print(status + "\n");
                break;
            }
            System.out.printf("Record file read ok \n");
            printHexString(rdata, rlen[0]);
        	break;
        case 0x02://value file
        	
        	status = umf_javacall.fw_getvalue_desfire(icdev, fID, value, pSessionKey);
            if (status != 0) {
                System.out.print("fw_getvalue_desfire error!\n");
                System.out.print(status + "\n");
                break;
            }
            System.out.printf("Value file read ok: "+value[0] + " \n");
            
        	status = umf_javacall.fw_credit_desfire(icdev, fID, credit, pSessionKey);
            if (status != 0) {
                System.out.print("fw_credit_desfire error!\n");
                System.out.print(status + "\n");
                break;
            }
            umf_javacall.fw_commitTransfer_desfire(icdev);
            System.out.printf("fw_credit_desfire ok \n");
            
        	status = umf_javacall.fw_debit_desfire(icdev, fID, debit, pSessionKey);
            if (status != 0) {
                System.out.print("fw_debit_desfire error!\n");
                System.out.print(status + "\n");
                break;
            }
            umf_javacall.fw_commitTransfer_desfire(icdev);
            System.out.printf("fw_debit_desfire ok \n");
            
        	status = umf_javacall.fw_getvalue_desfire(icdev, fID, value, pSessionKey);
            if (status != 0) {
                System.out.print("fw_getvalue_desfire error!\n");
                System.out.print(status + "\n");
                break;
            }
            System.out.printf("Value file read ok: "+value[0] + " \n");
            
        	break;
        	
        }
	}
	
	public static void operFiles(int icdev, byte[] fIDs, int fileNum, byte[] pSessionKey)
	{
		int i;
		byte curFileID;
		byte[] rlen = new byte[8];
		byte[] fileProper = new byte[512];
		int status;

		
		for(i=0; i< fileNum; i++)
		{
			curFileID = fIDs[i];
			
			status = umf_javacall.fw_getFileProper(icdev, curFileID, rlen, fileProper);
            if (status != 0) {
                System.out.print("fw_getFileProper error!\n");
                System.out.print(status + "\n");

            } else {
                System.out.println("File "+ curFileID + " preporter:\n");
                printHexString(fileProper, rlen[0]);
    
                testFile(icdev, curFileID, fileProper[0], pSessionKey);
            }
			
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        short status;
        int lDevice = 0;
        short findCardMode = 1; //set to 1 if multiply card mode, set to 0 if single card mode

        char[] pSnrM1 = new char[9];//M1 card serial number string
        long[] lSnr = new long[2];
        int tIndex = -1;
        byte[] rlen = new byte[8];
        byte[] rdata = new byte[256];
        Scanner input_cmd;// 
        byte[] idApp = {0x01,0x00,0x00};
        byte[] accessPermission = {(byte)0xee, (byte)0xee};
        byte[] standardFileSize = {(byte)fileSize_standard,0x00,0x00};
        byte[] recordFileSize = {(byte)recordSize, 0x00,0x00};
        byte[] recordFileNum = {0x10, 0x00,0x00};
        byte[] valueFileMin =  {0x00,0x00,0x00,0x00};
        byte[] valueFileMax = {(byte)0xFF,(byte)0xFF,0x00,0x00};
        byte[] valueDefVal ={0x00,0x00,0x00,0x00};
        
        byte keyNo = 0;
        byte[] sessionKey = new byte[64];
        byte[] userKey = {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,  0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,};
        byte[] fileIDs = new byte[512];
        
       

        try {
            lDevice = umf_javacall.fw_init((int)100, (long)115200);
            if (lDevice <= 0) {
                System.out.print("fw_init error!\n");
                return;
            }
            System.out.print("fw_init ok!\n");

            status = umf_javacall.fw_beep(lDevice, (short) 10);
            if (status != 0) {
                System.out.print("fw_beep error!\n");
            } else {
                System.out.print("fw_beep ok!\n");
            }
            
            while(tIndex != 0)
            {
            	
            	System.out.printf("\n\nChoose the test item you want:\n");
            	System.out.printf("1 --- Find card and reset \n");
            	System.out.printf("2 --- Create test app \n");
            	System.out.printf("3 --- Test exist files \n");
            	System.out.printf("4 --- Delete existed app \n");
            	System.out.printf("0 --- Exit \n");
            	
            	input_cmd = new Scanner(System.in);
            	tIndex = input_cmd.nextInt();
            	
                switch(tIndex)
                {
                case 1:
                {//find card and reset card
                	umf_javacall.fw_reset(lDevice, 10);
                	
                    status = umf_javacall.fw_card_hex(lDevice, (short) findCardMode, pSnrM1);
                    if (status != 0) {
                        System.out.print("fw_card error!\n");
                        System.out.print(status + "\n");
                        break;

                    } else {
                        System.out.print("fw_card ok!\n");
                        System.out.println(pSnrM1);
                    }
                    
                    status = umf_javacall.fw_anticoll2(lDevice, (byte)0, lSnr);
                    if(status != 0)
                    {
                    	System.out.print("fw_anticoll2 error!\n");
                    	System.out.print(status + "\n");
                    	break;
                    }
                    else System.out.print("fw_anticoll2 ok!\n");
                    
                    status = umf_javacall.fw_select2(lDevice, lSnr[0]);
                    if(status != 0)
                    {
                    	System.out.print("fw_select2 error!\n");
                    	System.out.print(status + "\n");
                    	break;
                    }
                    
                    System.out.print("fw_select2 ok.\n");
                    
                    status = umf_javacall.fw_reset_desfire(lDevice, rlen, rdata);
                    if(status != 0)
                    {
                    	System.out.print("fw_reset desfire error\n");
                    	System.out.print(status + "\n");
                    	break;
                    }
                    System.out.print("fw_reset_desfire ok\n");
                    printHexString(rdata,rlen[0]);
                    
                	status = umf_javacall.fw_authen_desfire(lDevice, keyNo, userKey, sessionKey);
                	if(status != 0)
                	{
                		System.out.printf("fw_authen_desfire error \n");
                		System.out.printf(status + "\n");
                		break;
                	}
                	System.out.printf("fw_authen_desfire ok \n");
                	
                    break;
                }
                case 2:
                {
                	status = umf_javacall.fw_createApp_desfire(lDevice, idApp, (byte)0x0F, (byte)0x0E);
                	if(status != 0)
                	{
                		System.out.printf("fw_createApp_desfire error \n");
                		System.out.printf(status + "\n");
                		break;
                	}
                	System.out.printf("fw_createApp_desfire ok \n");
                	
                	status = umf_javacall.fw_selectApp_desfire(lDevice, idApp);
                	if(status != 0)
                	{
                		System.out.printf("fw_selectApp_desfire error \n");
                		System.out.printf(status + "\n");
                		break;
                	}
                	System.out.printf("fw_selectApp_desfire ok \n");
                	
                	
                	status = umf_javacall.fw_createDataFile_desfire(lDevice, (byte)0x01, (byte)0x00, accessPermission,standardFileSize);
                	if(status != 0)
                	{
                		System.out.printf("fw_createDataFile_desfire error \n");
                		System.out.printf(status + "\n");
                		break;
                	}
                	System.out.printf("fw_createDataFile_desfire ok \n");
                	
                	status = umf_javacall.fw_createCsyRecord_desfire(lDevice, (byte)0x02, (byte)0x00, accessPermission, recordFileSize,recordFileNum);
                	if(status != 0)
                	{
                		System.out.printf("fw_createCsyRecord_desfire error \n");
                		System.out.printf(status + "\n");
                		break;
                	}
                	System.out.printf("fw_createCsyRecord_desfire ok \n");
                	
                	status = umf_javacall.fw_createValueFile_desfire(lDevice, (byte)0x03, (byte)0x00, accessPermission, valueFileMin,valueFileMax, valueDefVal, (byte)0x01);
                	if(status != 0)
                	{
                		System.out.printf("fw_createValueFile_desfire error \n");
                		System.out.printf(status + "\n");
                		break;
                	}
                	System.out.printf("fw_createValueFile_desfire ok \n");
                	
                }
                break;
                case 3:
                {

                	status = umf_javacall.fw_getAIDs_desfire(lDevice, rlen, idApp);
                	if(status != 0)
                	{
                		System.out.printf("fw_getAIDs_desfire error \n");
                		System.out.printf(status + "\n");
                		break;
                	}
                	System.out.printf("fw_getAIDs_desfire ok \n");
                	printHexString(idApp,rlen[0]);
                	
                	status = umf_javacall.fw_selectApp_desfire(lDevice, idApp);
                	if(status != 0)
                	{
                		System.out.printf("fw_selectApp_desfire error \n");
                		System.out.printf(status + "\n");
                		break;
                	}
                	System.out.printf("fw_selectApp_desfire ok \n");
                	
                	status = umf_javacall.fw_getFileIDs_desfire(lDevice, rlen, fileIDs);
                	if(status != 0)
                	{
                		System.out.printf("fw_getFileIDs_desfire error \n");
                		System.out.printf(status + "\n");
                		break;
                	}
                	System.out.printf("fw_getFileIDs_desfire ok \n");
                	printHexString(fileIDs,rlen[0]);
                	
                	operFiles(lDevice, fileIDs, rlen[0],sessionKey);
                }
                break;
                case 4:
                {
                	
                	status = umf_javacall.fw_formatPICC_desfire(lDevice);
                	if(status != 0)
                	{
                		System.out.printf("fw_formatPICC_desfire error \n");
                		System.out.printf(status + "\n");
                		break;
                	}
                	System.out.printf("fw_formatPICC_desfire ok \n");
                }
                break;
                case 0:
                break;
                }
            }
            
            status = umf_javacall.fw_exit(lDevice);
            if (status != 0) {
                System.out.print("fw_exit error!\n");
            } else {
                System.out.print("fw_exit ok!\n");
            }

        } catch (Exception e) {
            System.err.println("Exception caught: " + e.getMessage());
        }
	
	}

}
