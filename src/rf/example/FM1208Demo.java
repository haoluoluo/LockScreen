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

        char[] pExKey ={0xff,0xff,0xff,0xff,0xff,0xff,0xff,0xff,0xff,0xff,0xff,0xff,0xff,0xff,0xff,0xff};//外部认证密钥
        char[] pFileProKey={0xff,0xff,0xff,0xff,0xff,0xff,0xff,0xff,0xff,0xff,0xff,0xff,0xff,0xff,0xff,0xff};//文件保护密钥
        char[] pPurchaseKey={0x20,0x28,0x9d,0x08,0x52,0xc9,0x61,0x60,0x5b,0x92,0xb6,0x07,0xca,0xa3,0xcc,0xd9};//消费密钥
        char[] pCreditKey ={0xda,0x40,0x4f,0x59,0xe9,0xf7,0x30,0x05,0xa8,0xb9,0x79,0x35,0x73,0xa8,0xbd,0x92};//圈存密钥
        char[] pPin = {0x11,0x22,0x33,0x44,0x55,0x66};
        char[] pTerminal = {0x00,0x00,0x00,0x00,0x00,0x01};//6字节（固定）的终端机编号
        char[] pDirName = {'a',' ','T','e','s','t',' ','D','i','r'};


        FM1208 rd = new FM1208();
        umf_javacall reader = new umf_javacall();

        try {

            pWriteBuffer = strToWrite.toCharArray();
            lenToRW = (short)strToWrite.length();

            lDevice = reader.fw_init((short)100, 115200);//第一个参数设置：0(串口1），1（串口2）...,100(USB)
            if (lDevice <= 0) {
                System.out.print("fw_init error!\n");
                return;
            }
            System.out.print("fw_init ok!\n");

            //测试蜂鸣
            //  reader.fw_beep((int)lDevice,(short)10);
            reader.fw_reset((int)lDevice,(short)10);

            status = reader.fw_card((int)lDevice, (short)1, pval);
            if (status != 0) {
                System.out.print("find no card!\n");
                reader.fw_exit((int)lDevice);//断开连接
                return;
            }

            //reset
            status = reader.fw_pro_reset((int)lDevice,pChVal,  pRBuffer);
            if (status != 0) {
                System.out.print("fw_pro_reset error!\n");
                reader.fw_exit((int)lDevice);//断开连接
                return;
            } else {
                System.out.print("fw_pro_reset ok!\n");
            }

            //选择主应用
            status = rd.FWCosSelecteApp(lDevice);
            if (status != 0) {
                System.out.print("FWCosSelecteApp error!\n");
                reader.fw_exit((int)lDevice);//断开连接
                return;
            } else {
                System.out.print("FWCosSelecteApp ok!\n");
            }

            //建立一个密钥文件，用于存放各种密钥
            status = rd.FWCosCreateKeyFile(lDevice,
                    (short)0x0000,//所在目录文件标识,主应用为0
                    (short)0x0001,//密钥文件标识符
                    (short)0x0040,//密钥文件的长度
                    0);//在密钥中安装密钥的权限,为0表示可以任意安装密钥
            if (status != 0) {
                System.out.print("FWCosCreateKeyFile error!\n");
                System.out.print(status + "\n");
                rd.FWCosDeleteFileSys(lDevice);//测试结束前删除这个应用
                reader.fw_exit((int)lDevice);//断开连接
                return;

            } else {
                System.out.print("FWCosCreateKeyFile ok!\n");
            }

            //安装外部认证密钥
            status = rd.FWCosAddKey(lDevice,
                    (char)2,//外部认证密钥类型
                    pExKey,//密钥值
                    16);//密钥文件长度
            if (status != 0) {
                System.out.print("FWCosAddKey[ExKey] error!\n");
                System.out.print(status + "\n");
                rd.FWCosDeleteFileSys(lDevice);//测试结束前删除这个应用
                reader.fw_exit((int)lDevice);//断开连接
                return;

            } else {
                System.out.print("FWCosAddKey[ExKey] ok!\n");
            }

            //安装文件保护密钥
            status = rd.FWCosAddKey(lDevice,
                    (char)1,//文件保护密钥类型
                    pFileProKey,//密钥值
                    16);//密钥文件长度
            if (status != 0) {
                System.out.print("FWCosAddKey[FileProKey] error!\n");
                System.out.print(status + "\n");
                rd.FWCosSelecteApp(lDevice);
                rd.FWCosVerifyKey(lDevice, pExKey,16,(char)2);//验证外部认证密钥
                rd.FWCosDeleteFileSys(lDevice);//测试结束前删除这个应用
                reader.fw_exit((int)lDevice);//断开连接
                return;

            } else {
                System.out.print("FWCosAddKey[FileProKey] ok!\n");
            }

            //安装口令
            status = rd.FWCosAddKey(lDevice,
                    (char)3,//口令密钥类型
                    pPin,//口令值
                    6);//口令长度
            if (status != 0) {
                System.out.print("FWCosAddKey[Pin] error!\n");
                System.out.print(status + "\n");
                rd.FWCosSelecteApp(lDevice);
                rd.FWCosVerifyKey(lDevice, pExKey,16,(char)2);//验证外部认证密钥
                rd.FWCosDeleteFileSys(lDevice);//测试结束前删除这个应用
                reader.fw_exit((int)lDevice);//断开连接
                return;

            } else {
                System.out.print("FWCosAddKey[Pin] ok!\n");
            }

            //建立一个二进制一般文件
            status = rd.FWCosCreateBinaryFile(lDevice,
                    (short)0x0000,//当前所在目录标识
                    (short)0x0002,//要建的文件标识
                    (short)0x0080,//文件长度
                    (char)1,//数据加密类型：0-明文方式,1-明文+MAC校验，2-密文+MAC校验
                    0,//读不需密码
                    1,//更新需要口令验证 (1级)
                    0);//删除不需密钥(此参数一般无效)
            if (status != 0) {
                System.out.print("FWCosCreateBinaryFile error!\n");
                System.out.print(status + "\n");
                rd.FWCosSelecteApp(lDevice);
                rd.FWCosVerifyKey(lDevice, pExKey,16,(char)2);//外部认证
                rd.FWCosDeleteFileSys(lDevice);//测试结束前删除这个应用
                reader.fw_exit((int)lDevice);//断开连接
                return;

            } else {
                System.out.print("FWCosCreateBinaryFile ok!\n");
            }


            //验证口令
            status = rd.FWCosVerifyKey(lDevice,
                    pPin,//口令
                    6,//口令长度
                    (char)3);//口令类型
            if (status != 0) {
                System.out.print("FWCosVerifyKey error!\n");
                System.out.print(status + "\n");
                rd.FWCosSelecteApp(lDevice);
                rd.FWCosDeleteFileSys(lDevice);//测试结束前删除这个应用
                reader.fw_exit((int)lDevice);//断开连接
                return;

            } else {
                System.out.print("FWCosVerifyKey ok!\n");
            }

            //写(更新)二进制一般文件
            status = rd.FWCosUpdateBinaryFile(lDevice,
                    (short)0x0002,//文件标识
                    (short)0x00,//开始偏移地址
                    pWriteBuffer,//数据值
                    (short)lenToRW,//数据长度
                    (char)1,//数据加密类型：0-明文方式,1-明文+MAC校验，2-密文+MAC校验
                    pFileProKey,//文件保护密钥
                    16);//密钥长度
            if (status != 0) {
                System.out.print("FWCosUpdateBinaryFile error!\n");
                System.out.print(status + "\n");
                rd.FWCosSelecteApp(lDevice);
                rd.FWCosVerifyKey(lDevice, pExKey,16,(char)2);//外部认证
                rd.FWCosDeleteFileSys(lDevice);//测试结束前删除这个应用
                reader.fw_exit((int)lDevice);//断开连接
                return;

            } else {
                System.out.print("FWCosUpdateBinaryFile ok!\n");
            }

            //读二进制一般文件
            status = rd.FWCosReadBinaryFile(lDevice,
                    (short)0x0002,//文件标识
                    pRBuffer,
                    (short)0x00,//开始偏移地址
                    (short)lenToRW,//数据长度
                    (char)1,//数据加密类型：0-明文方式,1-明文+MAC校验，2-密文+MAC校验
                    pFileProKey,//文件保护密钥
                    16);//密钥长度
            if (status != 0) {
                System.out.print("FWCosReadBinaryFile error!\n");
                System.out.print(status + "\n");
                rd.FWCosSelecteApp(lDevice);
                rd.FWCosVerifyKey(lDevice, pExKey,16,(char)2);//外部认证
                rd.FWCosDeleteFileSys(lDevice);//测试结束前删除这个应用
                reader.fw_exit((int)lDevice);//断开连接
                return;

            } else {
                System.out.print("FWCosReadBinaryFile ok!\n");

                System.out.print(pRBuffer);
            }

            //***********************************************************************///
            //      值操作
            //**************************************************************************//
            //创建金融目录
            status = rd.FWCosCreateADF(lDevice,
                    (short)0xDF01,//文件标识
                    (long)0,//在该目录下建立文件的权限，不需验证密钥
                    (long)0,//在该目录下删除文件的权限，不需验证密钥
                    pDirName,//名称
                    (char)10);//长度
            if (status != 0) {
                System.out.print("FWCosCreateADF error!\n");
                System.out.print(status + "\n");
                rd.FWCosSelecteApp(lDevice);
                rd.FWCosVerifyKey(lDevice, pExKey,16,(char)2);//外部认证
                rd.FWCosDeleteFileSys(lDevice);//测试结束前删除这个应用
                reader.fw_exit((int)lDevice);//断开连接
                return;

            } else {
                System.out.print("\rFWCosCreateADF ok!\n");
            }

            //安装外部认证密钥
            status = rd.FWCosAddKey(lDevice,
                    (char)2,//外部认证密钥类型
                    pExKey,//密钥值
                    16);//密钥文件长度
            if (status != 0) {
                System.out.print("FWCosAddKey[ExKey] error!\n");
                System.out.print(status + "\n");
                rd.FWCosSelecteApp(lDevice);
                rd.FWCosVerifyKey(lDevice, pExKey,16,(char)2);//外部认证
                rd.FWCosDeleteFileSys(lDevice);//测试结束前删除这个应用
                reader.fw_exit((int)lDevice);//断开连接
                return;

            } else {
                System.out.print("FWCosAddKey[ExKey] ok!\n");
            }

            //安装口令
            status = rd.FWCosAddKey(lDevice,
                    (char)3,//口令密钥类型
                    pPin,//口令值
                    6);//口令长度
            if (status != 0) {
                System.out.print("FWCosAddKey[Pin] error!\n");
                System.out.print(status + "\n");
                rd.FWCosSelecteApp(lDevice);
                rd.FWCosVerifyKey(lDevice, pExKey,16,(char)2);//外部认证
                rd.FWCosDeleteFileSys(lDevice);//测试结束前删除这个应用
                reader.fw_exit((int)lDevice);//断开连接
                return;

            } else {
                System.out.print("FWCosAddKey[Pin] ok!\n");
            }

            //安装消费密钥
            status = rd.FWCosAddKey(lDevice,
                    (char)7,//消费密钥类型
                    pPurchaseKey,//密钥值
                    16);//密钥长度
            if (status != 0) {
                System.out.print("FWCosAddKey[Purchase] error!\n");
                System.out.print(status + "\n");
                rd.FWCosSelecteApp(lDevice);
                rd.FWCosVerifyKey(lDevice, pExKey,16,(char)2);//外部认证
                rd.FWCosDeleteFileSys(lDevice);//测试结束前删除这个应用
                reader.fw_exit((int)lDevice);//断开连接
                return;

            } else {
                System.out.print("FWCosAddKey[Purchase] ok!\n");
            }

            //安装圈存密钥
            status = rd.FWCosAddKey(lDevice,
                    (char)5,//圈存密钥类型
                    pCreditKey,//密钥值
                    16);//密钥长度
            if (status != 0) {
                System.out.print("FWCosAddKey[Credit] error!\n");
                System.out.print(status + "\n");
                rd.FWCosSelecteApp(lDevice);
                rd.FWCosVerifyKey(lDevice, pExKey,16,(char)2);//外部认证
                rd.FWCosDeleteFileSys(lDevice);//测试结束前删除这个应用
                reader.fw_exit((int)lDevice);//断开连接
                return;

            } else {
                System.out.print("FWCosAddKey[Credit] ok!\n");
            }

            //验证口令
            status = rd.FWCosVerifyKey(lDevice,
                    pPin,//口令
                    6,//口令长度
                    (char)3);//口令类型
            if (status != 0) {
                System.out.print("FWCosVerifyKey error!\n");
                System.out.print(status + "\n");
                rd.FWCosSelecteApp(lDevice);
                rd.FWCosVerifyKey(lDevice, pExKey,16,(char)2);//外部认证
                rd.FWCosDeleteFileSys(lDevice);//测试结束前删除这个应用
                reader.fw_exit((int)lDevice);//断开连接
                return;

            } else {
                System.out.print("FWCosVerifyKey ok!\n");
            }

            //圈存（存款）
            status = rd.FWCosCredit(lDevice,
                    (char)0x00,//圈存密钥标识，这里要设为0
                    100000,//金额
                    pTerminal,//终端号
                    pCreditKey,//圈存密钥
                    (long)16);//密钥长度
            if (status != 0) {
                System.out.print("FWCosCredit error!\n");
                System.out.print(status + "\n");
                rd.FWCosSelecteApp(lDevice);
                rd.FWCosVerifyKey(lDevice, pExKey,16,(char)2);//外部认证
                rd.FWCosDeleteFileSys(lDevice);//测试结束前删除这个应用
                reader.fw_exit((int)lDevice);//断开连接
                return;

            } else {
                System.out.print("FWCosCredit ok!\n");
            }

            //消费
            status = rd.FWCosPurchase(lDevice,
                    (char)0x01,//消费密钥标识，这里要设为1
                    50,//金额
                    pTerminal,//终端号
                    pPurchaseKey,//消费密钥
                    (long)16);//密钥长度
            if (status != 0) {
                System.out.print("FWCosPurchase error!\n");
                System.out.print(status + "\n");
                rd.FWCosSelecteApp(lDevice);
                rd.FWCosVerifyKey(lDevice, pExKey,16,(char)2);//外部认证
                rd.FWCosDeleteFileSys(lDevice);//测试结束前删除这个应用
                reader.fw_exit((int)lDevice);//断开连接
                return;

            } else {
                System.out.print("FWCosPurchase ok!\n");
            }

            //查询当前余额
            status = rd.FWCosGetBalance(lDevice,
                    pval);//密钥长度
            if (status != 0) {
                System.out.print("FWCosGetBalance error!\n");
                System.out.print(status + "\n");
                rd.FWCosSelecteApp(lDevice);
                rd.FWCosVerifyKey(lDevice, pExKey,16,(char)2);//外部认证
                rd.FWCosDeleteFileSys(lDevice);//测试结束前删除这个应用
                reader.fw_exit((int)lDevice);//断开连接
                return;

            } else {
                System.out.print("FWCosGetBalance ok!\n");
                System.out.println(pval[0]);
            }

            //选择主应用
            rd.FWCosSelecteApp(lDevice);

            //验证外部认证密钥
            status = rd.FWCosVerifyKey(lDevice,
                    pExKey,//外部认证密钥
                    16,//密钥文件长度
                    (char)2);
            if (status != 0) {
                System.out.print("FWCosVerifyKey error!\n");
                System.out.print(status + "\n");
                rd.FWCosDeleteFileSys(lDevice);//测试结束前删除这个应用
                reader.fw_exit((int)lDevice);//断开连接

            } else {
                System.out.print("FWCosVerifyKey ok!\n");
            }


            //测试结束前删除这个应用
            status = rd.FWCosDeleteFileSys(lDevice);
            if (status != 0) {
                System.out.print("FWCosDeleteFileSys error!\n");
                System.out.print(status + "\n");
                reader.fw_exit((int)lDevice);//断开连接

            } else {
                System.out.print("FWCosDeleteFileSys ok!\n");
            }

            reader.fw_exit((int)lDevice);//断开连接



        } catch (Exception e) {
            System.err.println("Exception caught: " + e.getMessage());
        }
    }
}
