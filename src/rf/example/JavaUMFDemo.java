package rf.example;

import jcumf.umf_javacall;

public class JavaUMFDemo {
	
	public static  void main(String[] args)
	{
        short status;
        int lDevice = 0;
        short tblk = 8;
        short tsector = 2;
        short findCardMode = 1; //set to 1 if multiply card mode, set to 0 if single card mode
        short keyMode = 0;// refer to manual doc

        char[] pSnrM1 = new char[8];//M1 card serial number string
        int[] pSnr=new int[1];
        int[] pval = new int[1];

        char[] pSBuffer = new char[16];
        char[] pRBuffer = new char[16];

        short lSlen;
        char[] pScupBuffer = {'0', '0', 'A', '4', '0', '0', '0', '0', '0',
                             '2', '2', 'F', '0', '2'};

        short[] pRlen = new short[1];
        char[] pRcpuBuffer = new char[255];
        
        char[] pShowBuf=new char[1024];

        umf_javacall rd = new umf_javacall();

        try {
            lDevice = umf_javacall.fw_init(100, 115200);
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

            status = umf_javacall.fw_card_hex(lDevice, (short) findCardMode, pSnrM1);//
            if (status != 0) {
                System.out.print("fw_card error!\n");
                System.out.print(status + "\n");

            } else {
                System.out.print("fw_card ok!\n");
                System.out.println(pSnrM1);
            }

            pSBuffer[0] = 0xFF;
            pSBuffer[1] = 0xFF;
            pSBuffer[2] = 0xFF;
            pSBuffer[3] = 0xFF;
            pSBuffer[4] = 0xFF;
            pSBuffer[5] = 0xFF;

            status = umf_javacall.fw_load_key(lDevice, (short) keyMode, (short) tsector, pSBuffer);
            if (status != 0) {
                System.out.print("fw_load_key error!\n");

            } else {
                System.out.print("fw_load_key ok!\n");
            }

            for (int i = 0; i < 16; i++) {
                pSBuffer[i] = 'N';
            }

 
            status = umf_javacall.fw_authentication(lDevice, (short) keyMode, (short) tsector);
            if (status != 0) {
                System.out.print("fw_authentication error!\n");
                System.out.print(status + "\n");
                umf_javacall.fw_exit(lDevice);
 
            } else {
                System.out.print("fw_authentication ok!\n");
            }
 
            //fw_initval
            status = umf_javacall.fw_initval(lDevice, (short)tblk , (short) 1000);
            if (status != 0) {
                System.out.print("fw_initval error!\n");
   
            } else {
                System.out.print("fw_initval ok!\n");
            }
            
            //fw_increment
            status = umf_javacall.fw_increment(lDevice, (short) tblk, (short) 100);

            if (status != 0) {
                System.out.print("fw_increment error!\n");

            } else {
                System.out.print("fw_increment ok!\n");
                umf_javacall.fw_transfer(lDevice, (short)tblk);//make valid
            }
            //fw_decrement
            status = umf_javacall.fw_decrement(lDevice, (short) tblk, (short) 50);
            if (status != 0) {
                System.out.print("fw_decrement error!\n");

            } else {
                System.out.print("fw_decrement ok!\n");
                umf_javacall.fw_transfer(lDevice, (short)tblk);//make valid
            }
            //fw_readval
            status = umf_javacall.fw_readval(lDevice, (short) tblk, pval);
            if (status != 0) {
                System.out.print("fw_readval error!\n");

            } else {
                System.out.print("fw_readval ok!\n");
                System.out.println(pval[0]);
            }

            status = umf_javacall.fw_write(lDevice, (short) tblk, pSBuffer);
            if (status != 0) {
                System.out.print("fw_write error!\n");
                umf_javacall.fw_exit(lDevice);

            } else {
                System.out.print("fw_write ok!\n");
            }

            status = umf_javacall.fw_read(lDevice, (short) tblk, pRBuffer);
            if (status != 0) {
                System.out.print("fw_read error!\n");
                umf_javacall.fw_exit(lDevice);

            } else {
                System.out.print("fw_read ok!\n");
                System.out.println(pRBuffer);
            }

            /*
            //fw_restore
            status = rd.fw_restore(lDevice, (short) 8);
            if (status != 0) {
                System.out.print("fw_restore error!\n");

            } else {
                System.out.print("fw_restore ok!\n");
                rd.fw_transfer(lDevice, (short)tblk);//make valid
            }


            //fw_srd_eeprom
            char[] loutchar = new char[255];

            status = rd.fw_srd_eeprom(lDevice, 12, 5, loutchar);
            if (status != 0) {
                System.out.print("fw_srd_eeprom error!\n");

            } else {
                System.out.print("fw_srd_eeprom ok!\n");
            }
            //-----------------------------------------------
            //*******Cpu SAM card****************************
             //-----------------------------------------------
             //fw_setcpu
             status = rd.fw_setcpu(lDevice, (short) (0x0d));
            if (status != 0) {
                System.out.print("fw_setcpu error!\n");

            } else {
                System.out.print("fw_setcpu ok!\n");
            }

            //fw_cpureset
            java.util.Arrays.fill(pShowBuf,'\0');
            
            status = rd.fw_cpureset(lDevice, pRlen, pRcpuBuffer);
            if (status != 0) {
                System.out.print("fw_cpureset error!\n");

            } else {
                System.out.print("fw_cpureset ok!\n");
           //     System.out.println(pRcpuBuffer);
            }

            //fw_cpuapdu
            lSlen = (short) pScupBuffer.length;
            status = rd.fw_cpuapdu(lDevice, lSlen, pScupBuffer,
                                   pRlen, pRcpuBuffer);
            if (status != 0) {
                System.out.print("fw_cpuapdu error!\n");

            } else {
                System.out.print("fw_cpuapdu ok!\n");
            //    System.out.println(pRcpuBuffer);
 
            }

            //-----------------------------------------------
            //*******  4442 card   *************************
             //-----------------------------------------------
             char[] rrbuf = new char[600];
            char[] rrbuffer = new char[255];
            char[] spbuf = new char[16];
            char[] rcnbuf = new char[4];
            spbuf[0] = 0xff;
            spbuf[1] = 0xff;
            spbuf[2] = 0xff;
            
            
            //fw_write_4442
            rrbuf[0]='A';
            rrbuf[1]='B';
            rrbuf[2]='C';
            rrbuf[3]='D';
            
            status = rd.fw_write_4442(lDevice, (short) 50, rrbuf, 4);
            if (status != 0) {
                System.out.print("fw_write_4442 error!\n");
            } else {
                System.out.print("fw_write_4442 ok!\n");
            }
            
            //fw_read_4442
            status = rd.fw_read_4442(lDevice, (short) 50, rrbuf, 4);
            if (status != 0) {
                System.out.print("fw_read_4442 error!\n");

            } else {
                System.out.print("fw_read_4442 ok!\n");
                System.out.print(rrbuf);
            }
            //fw_getProtectData_4442
            status = rd.fw_getProtectData_4442(lDevice, (short) 0, rrbuffer, 4);
            if (status != 0) {
                System.out.print("fw_getProtectData_4442 error!\n");
            } else {
                System.out.print("fw_getProtectData_4442 ok!\n");
            }

            //fw_setProtectData_4442
            status = rd.fw_setProtectData_4442(lDevice, (short) 31, spbuf, 3);
            if (status != 0) {
                System.out.print("fw_setProtectData_4442 error!\n");
            } else {
                System.out.print("fw_setProtectData_4442 ok!\n");
            }

            //fw_authentikey_4442
            status = rd.fw_authentikey_4442(lDevice, (short) 0, 3, spbuf);
            if (status != 0) {
                System.out.print("fw_authentikey_4442 error!\n");
            } else {
                System.out.print("fw_authentikey_4442 ok!\n");
            }

            //fw_changkey_4442
            status = rd.fw_changkey_4442(lDevice, (short) 0, 3, spbuf);
            if (status != 0) {
                System.out.print("fw_changkey_4442 error!\n");
            } else {
                System.out.print("fw_changkey_4442 ok!\n");
            }

            //fw_cntReadError_4442
            status = rd.fw_cntReadError_4442(lDevice, rcnbuf);
            if (status != 0) {
                System.out.print("fw_cntReadError_4442 error!\n");
            } else {
                System.out.print("fw_cntReadError_4442 ok!\n");
             //   System.out.println(rcnbuf);

            }
            //-----------------------------------------------
            //*******  FCPU card   *************************
             //-----------------------------------------------
             char[] rlenbuf = new char[4];
             char[] sdbuf={0x00,0xA4,0x04,0x00,0x07,0x52,0x73,0x61,0x20,0x41,0x70,0x70
             };
             char[] rbuffcpu = new char[255];
             char[] showrbuf_fcpu=new char[255];
             //fw_reset
             status = rd.fw_reset(lDevice,10);
             if(status != 0){
             	System.out.print("fw_reset Error!\n");
             }else{
             	System.out.print("fw_reset Ok!\n");
             }
             //fw_card
             status = rd.fw_card(lDevice, (short) 0, pSnr);
            if (status != 0) {
                System.out.print("fw_card error!\n");
                System.out.print(status + "\n");

            } else {
                System.out.print("fw_card ok!\n");
                System.out.println(pSnr[0]);
            }
             //fw_pro_reset
             status = rd.fw_pro_reset(lDevice,rlenbuf,rbuffcpu);
             if(status!=0){
             	System.out.print("fw_pro_reset error!\n");
             }else{
             	System.out.print("fw_pro_reset Ok!\n");
             	rd.hex_a(showrbuf_fcpu,rbuffcpu,2*rlenbuf[0]);
             	System.out.println(showrbuf_fcpu);
             }             
             //fw_pro_commandlink
             
             status=rd.fw_pro_commandlink(lDevice,(short)12,sdbuf,rlenbuf,
             rbuffcpu,
             (short)07,(short)60);
             if(status!=0){
             	System.out.print("fw_pro_commandlink error!\n");
             }else{
             	System.out.print("fw_pro_commandlink Ok!\n");
                for(int i=0;i<255;i++)showrbuf_fcpu[i]=0;
             	rd.hex_a(showrbuf_fcpu,rbuffcpu,2*rlenbuf[0]);
             	
             	System.out.println(showrbuf_fcpu);
             }
	    */
             
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
