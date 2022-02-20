package utils;

import lc.comproCall;

import java.util.Locale;
import java.util.Objects;

public class ICUtils {

   private static final short findCardMode = 1; //set to 1 if multiply card mode, set to 0 if single card mode

   private static final byte[] pSnrM1 = new byte[9];//M1 card serial number string
   private static final byte[] pSnrSize = new byte[2];
   private static final int[]  pTag = new int[2];
   private static final byte[] pSak = new byte[2];



   public static int init(){
       int lDevice = comproCall.lc_init_ex(2, " ".toCharArray(), 115200);
       if (lDevice == -1) {
           System.out.print("lc_init error!\n");
       }
       return lDevice;
   }
   public static void readIDCard(){
       int status;
       byte[] sendCmd ={0x00,0x36,0x00,0x00,0x08};
       byte [] revLen = new byte[1];
       byte [] revInfo = new byte[128];
       int lDevice = init();

       byte[] resetInfo = new byte[9];
       byte[] infoSize = new byte[1];
       status = comproCall.lc_findTypeB(lDevice, resetInfo, infoSize);
       System.out.println(status);
       if (status != 0) {
           System.out.println("lc_card error!");
           System.out.println(status );
       } else {
           System.out.println(pSnrSize[0]);
           for(int i =0; i < 9; i++){
               System.out.println(resetInfo[i]);
           }
       }

//       status = comproCall.lc_typeB_command(lDevice,(short) 5,sendCmd,revLen,revInfo);
//       if (status != 0) {
//           System.out.print("lc_card error!\n");
//           System.out.print(status + "\n");
//           comproCall.lc_exit(lDevice);
//       } else {
//           System.out.println(revLen[0]);
//           System.out.println(revInfo.length);
//           for(int i =0; i < revLen[0]; i++){
//               System.out.printf("%02X", revInfo[i]);
//           }
//           System.out.println();
//           for(int i =0; i < revInfo.length; i++){
////               System.out.println(pSnrM1[i]);
//               System.out.print(revInfo[i]);
//           }
//           System.out.println();
//       }
   }

   public static String readID(){
       int status;
       int lDevice = init();


       status = comproCall.lc_card(lDevice, (byte) findCardMode, pSnrM1,pSnrSize, pTag, pSak);
       if (status != 0) {
           System.out.println("lc_card error!");
           System.out.println(status );
       } else {
           char [] data = new char[pSnrSize[0]];
           System.out.println(pSnrSize[0]);
           System.out.print(comproCall.hex_asc(pSnrM1, pSnrM1, pSnrSize[0]));
           for(int i =0; i < pSnrSize[0]; i++){
               System.out.println(data[i]);
//               System.out.println(pSnrM1[i]);
           }
           return Objects.requireNonNull(StringUtil.bytesToHexString(pSnrM1, pSnrSize[0])).toUpperCase(Locale.ROOT);
       }
       comproCall.lc_exit(lDevice);
       return "";
   }

    public static void main(String[] args) {
//        String o = readID();
//        System.out.println(o);
        readIDCard();
    }
}
