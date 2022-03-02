package utils;

import lc.comproCall;

import java.math.BigInteger;
import java.util.Locale;
import java.util.Objects;

public class ICUtils {

    private static final byte sector = (byte) 0x01;
    private static final byte keyMode = 0x61;
    private static final short findCardMode = 1; //set to 1 if multiply card mode, set to 0 if single card mode

    private static final byte[] O_KEY = {(byte) 0xFF, (byte) 0xFF,(byte) 0xFF,(byte) 0xFF,(byte) 0xFF,(byte) 0xFF};

    private static final byte[] KeyA = {(byte)0x14,(byte)0xF2,(byte)0xB6,  (byte)0xA3, (byte)0xC7, (byte)0x59};

    //08778F69 ff 07 80 69
    private static final byte[] CtrlW = {(byte)0x08,(byte)0x77, (byte)0x8F, (byte)0x69};

    private static final byte[] KeyB = {(byte)0x14,(byte)0xF2,(byte)0xB6,  (byte)0xA3, (byte)0xC7, (byte)0x59};

    private static final byte[] pSnrM1 = new byte[9];//M1 card serial number string
    private static final byte[] pSnrSize = new byte[2];
    private static final int[]  pTag = new int[2];
    private static final byte[] pSak = new byte[2];



    public static int init(){
        int lDevice = comproCall.lc_init_ex(2, " ".toCharArray(), 115200);
        if (lDevice == -1) {
           int i = 0;
           while (i<5){
               lDevice = comproCall.lc_init_ex(2, " ".toCharArray(), 115200);
               if(lDevice != -1){
                   break;
               }
               i++;
           }
        }
        return lDevice;
    }
    public static void beep () {
        int lDevice = init();
        comproCall.lc_beep(lDevice, 10);
        comproCall.lc_exit(lDevice);
    }

    public static String readIDToNumber(){
        return readIDToNumber(false);
    }
    public static String readIDToNumber(Boolean isBeep){
        String s = readID(isBeep);
        if(s.length()<=0){
            return "";
        }
        return new BigInteger(s, 16).toString();
    }
    public static String readID(){
        return readID(false);
    }
    public static String readID(Boolean isBeep){
        int status;
        int lDevice = init();
        status = comproCall.lc_card(lDevice, (byte) findCardMode, pSnrM1,pSnrSize, pTag, pSak);
        if (status != 0) {
//            System.out.println("lc_card error!");
//            System.out.println(status );
        } else {
            if(isBeep){
                comproCall.lc_beep(lDevice, 10);
            }
            return Objects.requireNonNull(StringUtil.bytesToHexString(pSnrM1, pSnrSize[0])).toUpperCase(Locale.ROOT);
        }
        comproCall.lc_exit(lDevice);
        return "";
    }

    public static boolean authentication(){
        int lDevice = init();
        int status = comproCall.lc_authentication(lDevice, keyMode, sector, KeyB);
        if (status != 0) {
//            System.out.print("lc_authentication error!\n");
//            System.out.print(status + "\n");
            comproCall.lc_exit(lDevice);
            return false;
        } else {
//            System.out.print("lc_authentication ok!\n");
            return true;
        }

    }

    public static boolean updateKey(){

        int lDevice = init();
        int status = comproCall.lc_authentication(lDevice, keyMode, sector, KeyB);
        if (status != 0) {
            status = comproCall.lc_authentication(lDevice, keyMode, sector, O_KEY);
            if (status != 0) {
                status = comproCall.lc_updateKey(lDevice, sector, KeyA, CtrlW, KeyB);
                if (status != 0) {
                    comproCall.lc_exit(lDevice);
                    return false;
                }
                comproCall.lc_exit(lDevice);
                return true;
            } else {
                comproCall.lc_exit(lDevice);
                return true;
            }

        } else {
            return true;
        }

        

    }

    public static void main(String[] args) {
        int lDevice = init();
        int status = comproCall.lc_led(lDevice, 2, 0);
        if (status != 0) {
            System.out.print("Update key error!\n");
            comproCall.lc_exit(lDevice);
        } else {
            System.out.print("Update key ok!\n");
        }
//        String o = readID();
//        System.out.println(o);
//        BigInteger bigInteger = new BigInteger(o, 16);
//        System.out.println(bigInteger);
//        readIDCard();
    }
}
