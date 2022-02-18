package rf.example;
import jRF.FM1208;
import jcumf.umf_javacall;
 
public class FM1208Demo {
    
    public static void main(String[] args) {
    	
    	// TODO, add your application code
       	long status;
        long lDevice = 0;
        
        int[] pval = new int[1];
        char[] pChVal = new char[2];
        char[] pRBuffer = new char[1024];
        
        String strToWrite = "this is a test!";
      	short lenToRW;
      	char[] pWriteBuffer;

        char[] pShowBuf=new char[1024];
        
        char[] pExKey ={0xff,0xff,0xff,0xff,0xff,0xff,0xff,0xff,0xff,0xff,0xff,0xff,0xff,0xff,0xff,0xff};//�ⲿ��֤��Կ
		char[] pFileProKey={0xff,0xff,0xff,0xff,0xff,0xff,0xff,0xff,0xff,0xff,0xff,0xff,0xff,0xff,0xff,0xff};//�ļ�������Կ
		char[] pPurchaseKey={0x20,0x28,0x9d,0x08,0x52,0xc9,0x61,0x60,0x5b,0x92,0xb6,0x07,0xca,0xa3,0xcc,0xd9};//������Կ
		char[] pCreditKey ={0xda,0x40,0x4f,0x59,0xe9,0xf7,0x30,0x05,0xa8,0xb9,0x79,0x35,0x73,0xa8,0xbd,0x92};//Ȧ����Կ
		char[] pPin = {0x11,0x22,0x33,0x44,0x55,0x66};
		char[] pTerminal = {0x00,0x00,0x00,0x00,0x00,0x01};//6�ֽڣ��̶������ն˻����
		char[] pDirName = {'a',' ','T','e','s','t',' ','D','i','r'};
		
		
        FM1208 rd = new FM1208();
        umf_javacall reader = new umf_javacall();

        try {
        	
        	pWriteBuffer = strToWrite.toCharArray();
        	lenToRW = (short)strToWrite.length();
        	
            lDevice = reader.fw_init((short)100, 115200);//��һ���������ã�0(����1����1������2��...,100(USB)
            if (lDevice <= 0) {
                System.out.print("fw_init error!\n");
                return;
            }
            System.out.print("fw_init ok!\n");
            
            //���Է���
          //  reader.fw_beep((int)lDevice,(short)10);
            reader.fw_reset((int)lDevice,(short)10);
            
            status = reader.fw_card((int)lDevice, (short)1, pval);
            if (status != 0) {
                System.out.print("find no card!\n");
                reader.fw_exit((int)lDevice);//�Ͽ�����
                return;
            }
            
            //reset
            status = reader.fw_pro_reset((int)lDevice,pChVal,  pRBuffer);
            if (status != 0) {
                System.out.print("fw_pro_reset error!\n");
                reader.fw_exit((int)lDevice);//�Ͽ�����
                return;
            } else {
                System.out.print("fw_pro_reset ok!\n");
            }

			//ѡ����Ӧ��
            status = rd.FWCosSelecteApp(lDevice);
            if (status != 0) {
                System.out.print("FWCosSelecteApp error!\n");
                reader.fw_exit((int)lDevice);//�Ͽ�����
                return;
            } else {
                System.out.print("FWCosSelecteApp ok!\n");
            }
            
			//����һ����Կ�ļ������ڴ�Ÿ�����Կ
            status = rd.FWCosCreateKeyFile(lDevice, 
            	(short)0x0000,//����Ŀ¼�ļ���ʶ,��Ӧ��Ϊ0
            	(short)0x0001,//��Կ�ļ���ʶ��
            	(short)0x0040,//��Կ�ļ��ĳ���
            	0);//����Կ�а�װ��Կ��Ȩ��,Ϊ0��ʾ�������ⰲװ��Կ
            if (status != 0) {
                System.out.print("FWCosCreateKeyFile error!\n");
                System.out.print(status + "\n");
                rd.FWCosDeleteFileSys(lDevice);//���Խ���ǰɾ�����Ӧ��
                reader.fw_exit((int)lDevice);//�Ͽ�����
                return;

            } else {
                System.out.print("FWCosCreateKeyFile ok!\n");
            }
            
           	//��װ�ⲿ��֤��Կ
            status = rd.FWCosAddKey(lDevice, 
            	(char)2,//�ⲿ��֤��Կ����
            	pExKey,//��Կֵ
            	16);//��Կ�ļ�����
            if (status != 0) {
                System.out.print("FWCosAddKey[ExKey] error!\n");
                System.out.print(status + "\n");
                rd.FWCosDeleteFileSys(lDevice);//���Խ���ǰɾ�����Ӧ��
                reader.fw_exit((int)lDevice);//�Ͽ�����
                return;

            } else {
                System.out.print("FWCosAddKey[ExKey] ok!\n");
            }
            
           	//��װ�ļ�������Կ
            status = rd.FWCosAddKey(lDevice, 
            	(char)1,//�ļ�������Կ����
            	pFileProKey,//��Կֵ
            	16);//��Կ�ļ�����
            if (status != 0) {
                System.out.print("FWCosAddKey[FileProKey] error!\n");
                System.out.print(status + "\n");
                rd.FWCosSelecteApp(lDevice);
                rd.FWCosVerifyKey(lDevice, pExKey,16,(char)2);//��֤�ⲿ��֤��Կ
                rd.FWCosDeleteFileSys(lDevice);//���Խ���ǰɾ�����Ӧ��
                reader.fw_exit((int)lDevice);//�Ͽ�����
                return;

            } else {
                System.out.print("FWCosAddKey[FileProKey] ok!\n");
            }
            
           	//��װ����
            status = rd.FWCosAddKey(lDevice, 
            	(char)3,//������Կ����
            	pPin,//����ֵ
            	6);//�����
            if (status != 0) {
                System.out.print("FWCosAddKey[Pin] error!\n");
                System.out.print(status + "\n");
                rd.FWCosSelecteApp(lDevice);
                rd.FWCosVerifyKey(lDevice, pExKey,16,(char)2);//��֤�ⲿ��֤��Կ
                rd.FWCosDeleteFileSys(lDevice);//���Խ���ǰɾ�����Ӧ��
                reader.fw_exit((int)lDevice);//�Ͽ�����
                return;

            } else {
                System.out.print("FWCosAddKey[Pin] ok!\n");
            }
            
           	//����һ��������һ���ļ�
            status = rd.FWCosCreateBinaryFile(lDevice, 
            	(short)0x0000,//��ǰ����Ŀ¼��ʶ
            	(short)0x0002,//Ҫ�����ļ���ʶ
            	(short)0x0080,//�ļ�����
            	(char)1,//���ݼ������ͣ�0-���ķ�ʽ,1-����+MACУ�飬2-����+MACУ��
            	0,//����������
            	1,//������Ҫ������֤ (1��)
            	0);//ɾ��������Կ(�˲���һ����Ч)
            if (status != 0) {
                System.out.print("FWCosCreateBinaryFile error!\n");
                System.out.print(status + "\n");
                rd.FWCosSelecteApp(lDevice);
                rd.FWCosVerifyKey(lDevice, pExKey,16,(char)2);//�ⲿ��֤
                rd.FWCosDeleteFileSys(lDevice);//���Խ���ǰɾ�����Ӧ��
                reader.fw_exit((int)lDevice);//�Ͽ�����
                return;

            } else {
                System.out.print("FWCosCreateBinaryFile ok!\n");
            }
            

           	//��֤����
            status = rd.FWCosVerifyKey(lDevice, 
            	pPin,//����
            	6,//�����
            	(char)3);//��������
            if (status != 0) {
                System.out.print("FWCosVerifyKey error!\n");
                System.out.print(status + "\n");
                rd.FWCosSelecteApp(lDevice);
                rd.FWCosDeleteFileSys(lDevice);//���Խ���ǰɾ�����Ӧ��
                reader.fw_exit((int)lDevice);//�Ͽ�����
                return;

            } else {
                System.out.print("FWCosVerifyKey ok!\n");
            }
            
           	//д(����)������һ���ļ�
            status = rd.FWCosUpdateBinaryFile(lDevice, 
            	(short)0x0002,//�ļ���ʶ
            	(short)0x00,//��ʼƫ�Ƶ�ַ
            	pWriteBuffer,//����ֵ
            	(short)lenToRW,//���ݳ���
            	(char)1,//���ݼ������ͣ�0-���ķ�ʽ,1-����+MACУ�飬2-����+MACУ��
            	pFileProKey,//�ļ�������Կ
            	16);//��Կ����
            if (status != 0) {
                System.out.print("FWCosUpdateBinaryFile error!\n");
                System.out.print(status + "\n");
                rd.FWCosSelecteApp(lDevice);
                rd.FWCosVerifyKey(lDevice, pExKey,16,(char)2);//�ⲿ��֤
                rd.FWCosDeleteFileSys(lDevice);//���Խ���ǰɾ�����Ӧ��
                reader.fw_exit((int)lDevice);//�Ͽ�����
                return;

            } else {
                System.out.print("FWCosUpdateBinaryFile ok!\n");
            }
            
           	//��������һ���ļ�
            status = rd.FWCosReadBinaryFile(lDevice, 
            	(short)0x0002,//�ļ���ʶ
            	pRBuffer,
            	(short)0x00,//��ʼƫ�Ƶ�ַ
            	(short)lenToRW,//���ݳ���
            	(char)1,//���ݼ������ͣ�0-���ķ�ʽ,1-����+MACУ�飬2-����+MACУ��
            	pFileProKey,//�ļ�������Կ
            	16);//��Կ����
            if (status != 0) {
                System.out.print("FWCosReadBinaryFile error!\n");
                System.out.print(status + "\n");
                rd.FWCosSelecteApp(lDevice);
                rd.FWCosVerifyKey(lDevice, pExKey,16,(char)2);//�ⲿ��֤
                rd.FWCosDeleteFileSys(lDevice);//���Խ���ǰɾ�����Ӧ��
                reader.fw_exit((int)lDevice);//�Ͽ�����
                return;

            } else {
                System.out.print("FWCosReadBinaryFile ok!\n");
                
                System.out.print(pRBuffer);
            }
            
            //***********************************************************************///
            //      ֵ���� 
            //**************************************************************************//
			//��������Ŀ¼
            status = rd.FWCosCreateADF(lDevice,
            	(short)0xDF01,//�ļ���ʶ
            	(long)0,//�ڸ�Ŀ¼�½����ļ���Ȩ�ޣ�������֤��Կ
            	(long)0,//�ڸ�Ŀ¼��ɾ���ļ���Ȩ�ޣ�������֤��Կ
            	pDirName,//����
            	(char)10);//����
            if (status != 0) {
                System.out.print("FWCosCreateADF error!\n");
                System.out.print(status + "\n");
                rd.FWCosSelecteApp(lDevice);
                rd.FWCosVerifyKey(lDevice, pExKey,16,(char)2);//�ⲿ��֤
                rd.FWCosDeleteFileSys(lDevice);//���Խ���ǰɾ�����Ӧ��
                reader.fw_exit((int)lDevice);//�Ͽ�����
                return;

            } else {
                System.out.print("\rFWCosCreateADF ok!\n");
            }
            
           	//��װ�ⲿ��֤��Կ
            status = rd.FWCosAddKey(lDevice, 
            	(char)2,//�ⲿ��֤��Կ����
            	pExKey,//��Կֵ
            	16);//��Կ�ļ�����
            if (status != 0) {
                System.out.print("FWCosAddKey[ExKey] error!\n");
                System.out.print(status + "\n");
                rd.FWCosSelecteApp(lDevice);
                rd.FWCosVerifyKey(lDevice, pExKey,16,(char)2);//�ⲿ��֤
                rd.FWCosDeleteFileSys(lDevice);//���Խ���ǰɾ�����Ӧ��
                reader.fw_exit((int)lDevice);//�Ͽ�����
                return;

            } else {
                System.out.print("FWCosAddKey[ExKey] ok!\n");
            }
            
           	//��װ����
            status = rd.FWCosAddKey(lDevice, 
            	(char)3,//������Կ����
            	pPin,//����ֵ
            	6);//�����
            if (status != 0) {
                System.out.print("FWCosAddKey[Pin] error!\n");
                System.out.print(status + "\n");
                rd.FWCosSelecteApp(lDevice);
                rd.FWCosVerifyKey(lDevice, pExKey,16,(char)2);//�ⲿ��֤
                rd.FWCosDeleteFileSys(lDevice);//���Խ���ǰɾ�����Ӧ��
                reader.fw_exit((int)lDevice);//�Ͽ�����
                return;

            } else {
                System.out.print("FWCosAddKey[Pin] ok!\n");
            }
            
           	//��װ������Կ
            status = rd.FWCosAddKey(lDevice, 
            	(char)7,//������Կ����
            	pPurchaseKey,//��Կֵ
            	16);//��Կ����
            if (status != 0) {
                System.out.print("FWCosAddKey[Purchase] error!\n");
                System.out.print(status + "\n");
                rd.FWCosSelecteApp(lDevice);
                rd.FWCosVerifyKey(lDevice, pExKey,16,(char)2);//�ⲿ��֤ 
                rd.FWCosDeleteFileSys(lDevice);//���Խ���ǰɾ�����Ӧ��
                reader.fw_exit((int)lDevice);//�Ͽ�����
                return;

            } else {
                System.out.print("FWCosAddKey[Purchase] ok!\n");
            }
            
           	//��װȦ����Կ
            status = rd.FWCosAddKey(lDevice, 
            	(char)5,//Ȧ����Կ����
            	pCreditKey,//��Կֵ
            	16);//��Կ����
            if (status != 0) {
                System.out.print("FWCosAddKey[Credit] error!\n");
                System.out.print(status + "\n");
                rd.FWCosSelecteApp(lDevice);
                rd.FWCosVerifyKey(lDevice, pExKey,16,(char)2);//�ⲿ��֤
                rd.FWCosDeleteFileSys(lDevice);//���Խ���ǰɾ�����Ӧ��
                reader.fw_exit((int)lDevice);//�Ͽ�����
                return;

            } else {
                System.out.print("FWCosAddKey[Credit] ok!\n");
            }
            
           	//��֤����
            status = rd.FWCosVerifyKey(lDevice, 
            	pPin,//����
            	6,//�����
            	(char)3);//��������
            if (status != 0) {
                System.out.print("FWCosVerifyKey error!\n");
                System.out.print(status + "\n");
                rd.FWCosSelecteApp(lDevice);
                rd.FWCosVerifyKey(lDevice, pExKey,16,(char)2);//�ⲿ��֤
                rd.FWCosDeleteFileSys(lDevice);//���Խ���ǰɾ�����Ӧ��
                reader.fw_exit((int)lDevice);//�Ͽ�����
                return;

            } else {
                System.out.print("FWCosVerifyKey ok!\n");
            }
            
           	//Ȧ�棨��
            status = rd.FWCosCredit(lDevice,
            	(char)0x00,//Ȧ����Կ��ʶ������Ҫ��Ϊ0
            	100000,//���
            	pTerminal,//�ն˺�
            	pCreditKey,//Ȧ����Կ
            	(long)16);//��Կ����
            if (status != 0) {
                System.out.print("FWCosCredit error!\n");
                System.out.print(status + "\n");
                rd.FWCosSelecteApp(lDevice);
                rd.FWCosVerifyKey(lDevice, pExKey,16,(char)2);//�ⲿ��֤
                rd.FWCosDeleteFileSys(lDevice);//���Խ���ǰɾ�����Ӧ��
                reader.fw_exit((int)lDevice);//�Ͽ�����
                return;

            } else {
                System.out.print("FWCosCredit ok!\n");
            }
            
           	//����
            status = rd.FWCosPurchase(lDevice,
            	(char)0x01,//������Կ��ʶ������Ҫ��Ϊ1
            	50,//���
            	pTerminal,//�ն˺�
            	pPurchaseKey,//������Կ
            	(long)16);//��Կ����
            if (status != 0) {
                System.out.print("FWCosPurchase error!\n");
                System.out.print(status + "\n");
                rd.FWCosSelecteApp(lDevice);
                rd.FWCosVerifyKey(lDevice, pExKey,16,(char)2);//�ⲿ��֤
                rd.FWCosDeleteFileSys(lDevice);//���Խ���ǰɾ�����Ӧ��
                reader.fw_exit((int)lDevice);//�Ͽ�����
                return;

            } else {
                System.out.print("FWCosPurchase ok!\n");
            }
            
           	//��ѯ��ǰ���
            status = rd.FWCosGetBalance(lDevice,
            	pval);//��Կ����
            if (status != 0) {
                System.out.print("FWCosGetBalance error!\n");
                System.out.print(status + "\n");
                rd.FWCosSelecteApp(lDevice);
                rd.FWCosVerifyKey(lDevice, pExKey,16,(char)2);//�ⲿ��֤
                rd.FWCosDeleteFileSys(lDevice);//���Խ���ǰɾ�����Ӧ��
                reader.fw_exit((int)lDevice);//�Ͽ�����
                return;

            } else {
                System.out.print("FWCosGetBalance ok!\n");
                System.out.println(pval[0]);
            }
            
            //ѡ����Ӧ��
            rd.FWCosSelecteApp(lDevice);
            
            //��֤�ⲿ��֤��Կ
            status = rd.FWCosVerifyKey(lDevice, 
            	pExKey,//�ⲿ��֤��Կ
            	16,//��Կ�ļ�����
            	(char)2);
            if (status != 0) {
                System.out.print("FWCosVerifyKey error!\n");
                System.out.print(status + "\n");
                rd.FWCosDeleteFileSys(lDevice);//���Խ���ǰɾ�����Ӧ��
                reader.fw_exit((int)lDevice);//�Ͽ�����

            } else {
                System.out.print("FWCosVerifyKey ok!\n");
            }

            
            //���Խ���ǰɾ�����Ӧ��
            status = rd.FWCosDeleteFileSys(lDevice);
            if (status != 0) {
                System.out.print("FWCosDeleteFileSys error!\n");
                System.out.print(status + "\n");
                reader.fw_exit((int)lDevice);//�Ͽ�����
 
            } else {
                System.out.print("FWCosDeleteFileSys ok!\n");
            }

            reader.fw_exit((int)lDevice);//�Ͽ�����
            


        } catch (Exception e) {
            System.err.println("Exception caught: " + e.getMessage());
        }
    }
}
