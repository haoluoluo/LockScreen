package rf.example;
import jcumf.umf_javacall;

public class UltralightTest {

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

        char[] pShowBuf=new char[1024];
        int i, j;

        umf_javacall rd = new umf_javacall();

        try {
            lDevice = rd.fw_init((int)100, (long)115200);
            if (lDevice <= 0) {
                System.out.print("fw_init error!\n");
                return;
            }
            System.out.print("fw_init ok!\n");

            status = rd.fw_beep(lDevice, (short) 10);
            if (status != 0) {
                System.out.print("fw_beep error!\n");
            } else {
                System.out.print("fw_beep ok!\n");
            }

            status = rd.fw_card_hex(lDevice, (short) findCardMode, pSnrM1);
            if (status != 0) {
                System.out.print("fw_card error!\n");
                System.out.print(status + "\n");

            } else {
                System.out.print("fw_card ok!\n");
                System.out.println(pSnrM1);
            }
            
			//Read card
			//��д��֮ǰ����Ҫ��һ�µ�0ҳ
            status = rd.fw_read_ultralt(lDevice,(short)0,pRBuffer);//����0ҳ
            if (status != 0)
			{
            	System.out.print("fw_read_ultralt Error!\n");
				return;
			}
			
			
			//д��
			for(j=0;j<4;j++)
			{
				pSBuffer[j]='M';
			}
			
			status= rd.fw_write_ultralt(lDevice,(short)4,pSBuffer);//д��4ҳ
			
			if (status != 0)
			{
				System.out.print("fw_write_ultralt Error!");
				return;
			}
			
			System.out.print("fw_write_ultralt OK!");
			System.out.println(pSBuffer);
		
			//���Զ���

			status= rd.fw_read_ultralt(lDevice,(short)4,pRBuffer);//����4ҳ������
			if (status != 0)
			{
				System.out.print("fw_read_ultralt Error!");
				return;
			}
			
			System.out.print("fw_read_ultralt Ok!");
			System.out.println(pRBuffer);

             
            status = rd.fw_exit(lDevice);
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
