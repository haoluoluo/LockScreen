package rf.example;
import jcumf.umf_javacall;

public class IcodeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        short status;
        int lDevice = 0;
        short findCardMode = 1; //set to 1 if multiply card mode, set to 0 if single card mode

        char[] pSnrM1 = new char[9];//M1 card serial number string
        int[] pSnr=new int[1];
        int[] pval = new int[1];

        char[] pSBuffer = new char[16];
        char[] pRBuffer = new char[16];
        
        byte[] pbTmp = new byte[2];
        byte[] pbRLen = new byte[2];
        byte[] pbRData = new byte[100];
        byte[] pbSData = new byte[100];
        byte[] pbUID = new byte[9];
        
        byte startBlock = 5;
        byte rwBlockNumber = 1;
        

        char[] pShowBuf=new char[1024];
        int i, j;

        try {
        	//link reader
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
            
            status = umf_javacall.fw_config_card(lDevice, (byte)0x31);//Set to iso15693
            if (status != 0) {
                System.out.print("fw_config_card error!\n");
                return;
            } else {
                System.out.print("fw_config_card ok!\n");
            }
            
            //find card...
            status = umf_javacall.fw_inventory(lDevice, (byte)0x36, (byte)0x00, (byte)0x00, pbRLen, pbUID);//As single card mode
            if (status != 0) {
                System.out.print("fw_inventory error!\n");
                return;
            } else {
                System.out.print("fw_inventory ok!\n");
            }

            status = umf_javacall.fw_select_uid(lDevice, (byte)0x22, pbUID);
            if (status != 0) {
                System.out.print("fw_select_uid error!\n");
                return;
            } else {
                System.out.print("fw_select_uid ok!\n");
            }
            
            //Write card
			for(j=0;j<4;j++)
			{
				pbSData[j]=(byte)'M';
			}
			
			status= umf_javacall.fw_writeblock(lDevice,(byte)0x22,startBlock, rwBlockNumber, pbUID, (byte)(4*rwBlockNumber), pbSData);//д��4ҳ
			
			if (status != 0)
			{
				System.out.print("fw_writeblock Error!");
				return;
			}
			
			System.out.print("fw_writeblock OK!");
			System.out.println(new String(pbSData));
		
			//Read card

			status = umf_javacall.fw_readblock(lDevice, (byte)0x22, startBlock, rwBlockNumber, pbUID, pbRLen, pbRData);
            if (status != 0)
			{
            	System.out.print("fw_readblock Error!\n");
				return;
			}
			
			System.out.print("fw_readblock Ok!");
			System.out.println(new String(pbRData));

             
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
