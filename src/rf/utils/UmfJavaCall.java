package rf.utils;/**
*
* @author luoluo.hao
 * @create 2022-02-18 11:13
**/
public class UmfJavaCall {
    static
    {
        System.loadLibrary("umf");
    }

    public native static int fw_init_ex ( int portType, char[] szPathName, long baud);
    public native static int fw_init ( int port,long baud);
    public native static short fw_exit(int lDevice);
    public native static void  hex_a(char[] cHex,char[] a,int hex_length);
    public native static short a_hex(char[] a,char[] cHex,int a_length);
    public native static short fw_des(char[] key,char[] sour,char[] dest,int m);
    public native static short fw_getver(int lDevice, char[] strVer);

    public native static short fw_beep(int lDevice, short time);
    public native static short fw_reset(int lDevice,int mSec);
    public native static short fw_swr_eeprom(int lDevice,int lOffset,int lLen,char[] pBuffer);
    public native static short fw_srd_eeprom(int lDevice,int lOffset,int lLen,char[] pBuffer);
    public native static short fw_rdCardStatus(int lDevice, int[] pSt);

    public native static short fw_request(int lDevice,short iMode,int[] pTagType);
    public native static short fw_anticoll(int lDevice,short iBcnt,int[] pSnr);
    public native static short fw_select(int lDevice,int lSnr,short[] pSize);
    public native static short fw_card(int lDevice,short iMode,int[] pSnr);
    public native static short fw_card_hex(int lDevice,short iMode,char[] pSnr);
    public native static short fw_card_str(int lDevice,short iMode,char[] pSnr);
    public native static short fw_authentication(int lDevice,short iMod,short iSec);
    public native static short fw_read(int lDevice,short iAdr,char[] pData);
    public native static short fw_read_hex(int lDevice, short iAdr, char[] szData);
    public native static short fw_write(int lDevice,short iAdr,char[] pData);
    public native static short fw_write_hex(int lDevice, short iAdr, char[] szData);
    public native static short fw_changeKey(int lDevice,short secNr,char[] keyA,char[] ctrlW,char[] keyB);
    public native static short fw_changeKey_hex(int lDevice, short secNr, char[] szKeyA, char[] szCtrlW, char[] szKeyB);
    public native static short fw_increment(int lDevice,short iAdr,int lValue);
    public native static short fw_decrement(int lDevice,short iAdr,int lValue);
    public native static short fw_initval(int lDevice,short iAdr,int lValue);
    public native static short fw_readval(int lDevice,short iAdr,int[] pValue);
    public native static short fw_transfer(int lDevice,short iAdr);
    public native static short fw_restore(int lDevice,short iAdr);
    public native static short fw_load_key(int lDevice,short iMode,short iSecNr,char[] pKey);
    public native static short fw_halt(int lDevice);

    public native static short fw_read_S70(int lDevice,short _Adr,char[] _Data);
    public native static short fw_write_S70(int lDevice,short _Adr,char[] _Data);

    //ultralight /C
    public native static short fw_request_ultralt(int lDevice,short _Mode);
    public native static short fw_anticall_ultralt(int lDevice,long[] _Snr);
    public native static short fw_select_ultralt(int lDevice,long _Snr);
    public native static short fw_read_ultralt(int lDevice,short iPage,char[] redata);
    public native static short fw_write_ultralt(int lDevice,short iPage,char[] sdata);
    public native static short fw_halt_ultralt(int lDevice);
    public native static short fw_ultralt_C_authen(int lDevice, char[]  key);
    public native static short fw_ultralt_C_setSafePage(int lDevice, short ipage, char readSec);
    public native static short fw_ultralt_C_changePwd(int lDevice, char[] keyold,char[] keynew);
    public native static short fw_ultralt_C_lockPage(int lDevice, short flag);

    //mifare pro
    public native static short fw_reset_mifarepro(int lDevice,char[] rlen, char[] rbuff);
    public native static short fw_setcpu_mifarepro(int lDevice,short SAMID);
    public native static short fw_apdu_mifarepro(int lDevice,short slen,char[] sbuff,char[] rlen,char[] rbuff);

    //FCPU Card
    public native static short fw_pro_reset(int IDevice,char[] rlen,char[] rbuf);
    public native static short fw_pro_commandlink(int IDevice,short slen,char[] sbuff,char[] rlen,char[] rbuf,short tt,short fg);


    public native static short fw_read_4442(int lDevice,short iAdr,char[] pData,int length);
    public native static short fw_write_4442(int lDevice,short iAdr,char[] rData,int length);
    public native static short fw_getProtectData_4442(int lDevice,short iAdr,char[] rData,int length);
    public native static short fw_setProtectData_4442(int lDevice,short iAdr,char[] pData,int length);
    public native static short fw_authentikey_4442(int lDevice,short iAdr,int rlen,char[] pKey);
    public native static short fw_changkey_4442(int lDevice,short iAdr,int rlen,char[] pKey);
    public native static short fw_cntReadError_4442(int lDevice,char[] cntReadError);

    public native static short fw_read_4428(int lDevice,short iAdr,char[] pData,int length);
    public native static short fw_write_4428(int lDevice,short iAdr,char[] rData,int length);
    public native static short fw_getProtectData_4428(int lDevice,short iAdr,char[] rData,int length);
    public native static short fw_setProtectData_4428(int lDevice,short iAdr,char[] pData,int length);
    public native static short fw_authentikey_4428(int lDevice,char[] pKey);
    public native static short fw_changkey_4428(int lDevice,char[] pKey);
    public native static short fw_cntReadError_4428(int lDevice,char[] cntReadError);


    //password keyboard
    public native static short fw_PassIn(int IDevice, short ctime);
    public native static short fw_PassGet(int IDevice, char[] rlen, char[] cpass);
    public native static short fw_PassCancel(int IDevice);
    public native static short fw_CheckKeyValue(int IDevice,char[] rlen,char[] retdata);

    //magnetic card
    public native static short  fw_mgtReset(int IDevice);
    public native static short  fw_mgtCheckStatus(int IDevice);
    public native static short  fw_mgtReadCard(int IDevice, byte[] pData_out, int[] rLen);
    public native static short  fw_mgtClear(int IDevice);

    //LCD
    public native static short fw_lcd_dispclear(int IDevice);
    public native static short fw_lcd_dispstr(int IDevice, char[] str);

    //desfire
    public native static short fw_anticoll2(int lDevice,byte _Bcnt,long[] _Snr);
    public native static short fw_select2(int lDevice, long _Snr);
    public native static short fw_reset_desfire(int lDevice,byte[] rlen,byte[] rdata);
    public native static short fw_apdu_desfire(int lDevice,byte slen,byte[] sdata,byte[] rlen,byte[] rdata);
    public native static short fw_authen_desfire(int lDevice, byte keyNo, byte[] key,byte[] sessionKey);
    public native static short fw_authen_desfire_aes(int lDevice, byte keyNo, byte[] key,byte[] sessionKey);
    public native static short fw_getver_desfire(int lDevice,byte[] rlen,byte[] version);
    public native static short fw_getAIDs_desfire(int lDevice,byte[] rlen,byte[] AIDS);
    public native static short fw_selectApp_desfire(int lDevice, byte[] AID);
    public native static short fw_getKeySetting_desfire(int lDevice, byte[] rlen,byte[] setbuf);
    public native static short fw_getKeyver_desfire(int lDevice,byte keyNo,byte[] keyVer);
    public native static short fw_createApp_desfire(int lDevice,byte[] AID,byte KeySetting,byte NumOfKey);
    public native static short fw_delAID_desfire(int lDevice,byte[] AID);
    public native static short fw_changeKeySetting_desfire(int lDevice,byte newSet,byte[] sessionKey);
    public native static short fw_changeKey_desfire(int lDevice,byte[] sessionKey,byte[] curKey,byte keyNo,byte[] newkey);
    public native static short fw_changeKey_desfire_aes(int lDevice,byte[] sessionKey,byte[] curKey,byte keyNo,byte[] newkey,byte keyVersion);
    public native static short fw_getFileIDs_desfire(int lDevice, byte[] rlen, byte[] fileIDs);
    public native static short fw_getFileProper(int lDevice,byte fileNo,byte[] rlen,byte[] fileProper);
    public native static short fw_changeFileSetting(int lDevice,byte fileNo,byte comSet,byte[] accessRight,byte[] sessionKey);
    public native static short fw_createDataFile_desfire(int lDevice,byte fileNo,byte ComSet,byte[] AccessRight,byte[] FileSize);
    public native static short fw_createBackupDataFile_desfire(int lDevice,byte fileNo,byte ComSet,byte[] AccessRight,byte[] FileSize);
    public native static short fw_createValueFile_desfire(int lDevice,byte fileNo,byte ComSet,byte[] AccessRight,byte[] lowerLimit,byte[] upperLimit,byte[] value,byte creditEnabled);
    public native static short fw_createCsyRecord_desfire(int lDevice,byte fileNo,byte comSet,byte[] AccessRight,byte[] RecordSize,byte[] MaxNum);
    public native static short fw_delFile_desfire(int lDevice,byte fileNo);
    public native static short fw_write_desfire(int lDevice,byte fileNo,int offset,int length,byte[] data,byte[] sessionKey);
    public native static short fw_read_desfire(int lDevice,byte fileNo,int offset,int length,byte[] revData,byte[] sessionKey);
    public native static short fw_getvalue_desfire(int lDevice,byte fileNo,int[] value,byte[] sessionKey);
    public native static short fw_credit_desfire(int lDevice,byte fileNo,int value,byte[] sessionKey);
    public native static short fw_debit_desfire(int lDevice,byte fileNo,int value,byte[] sessionKey);
    public native static short fw_writeRecord_desfire(int lDevice,byte fileNo,int offset,int length,byte[] data,byte[] sessionKey);
    public native static short fw_readRecord_desfire(int lDevice,byte fileNo,int offset,int length,byte[] revData,int[] SgRecordlen,int[] rlen,byte[] sessionKey);
    public native static short fw_clearRecord_desfire(int lDevice,byte fileNo);
    public native static short fw_commitTransfer_desfire(int lDevice);
    public native static short fw_abortTransfer_desfire(int lDevice);
    public native static short fw_formatPICC_desfire(int lDevice);

    public native static short fw_cpureset(int lDevice,byte[] rlen, byte[] revData);
    public native static short fw_setcpu(int lDevice, byte iSeat);
    public native static short fw_cpuapdu(int lDevice, byte cmdLen, byte[] cmdBuf, byte[] rlen, byte[] revData);
    public native static short fw_setcpupara(int lDevice,byte cputype,byte cpupro, byte cpuetu);

    public native static short fw_config_card(int lDevice, byte flags);

    //iso15693(icode)
    public native static short fw_inventory(int lDevice,byte flags,byte AFI, byte masklen, byte[] rlen,byte[] rbuffer);
    public native static short fw_stay_quiet(int lDevice,byte flags,byte[] UID);
    public native static short fw_select_uid(int lDevice,byte flags,byte[] UID);
    public native static short fw_reset_to_ready(int lDevice,byte flags,byte[] UID);
    public native static short fw_readblock(int lDevice,byte flags,byte startblock,byte blocknum, byte[] UID, byte[] rlen,byte[] rbuffer);
    public native static short fw_writeblock(int lDevice,byte flags,byte startblock,byte blocknum, byte[] UID, byte wlen,byte[] rbuffer);
    public native static short fw_lock_block(int lDevice,byte flags,byte block,byte[] UID);
    public native static short fw_write_afi(int lDevice,byte flags,byte AFI,byte[] UID);
    public native static short fw_lock_afi(int lDevice,byte flags,byte AFI,byte[] UID);
    public native static short fw_write_dsfid(int lDevice,byte flags,byte DSFID,byte[] UID);
    public native static short fw_lock_dsfid(int lDevice,byte flags,byte DSFID,byte[] UID);
    public native static short fw_get_systeminfo(int lDevice,byte flags,byte[] UID, byte[] rlen,byte[] rbuffer);
    public native static short fw_get_securityinfo(int lDevice,byte flags,byte startblock,byte blocknum, byte[] UID, byte[] rlen,byte[] rbuffer);
    //parameter:
    //pwdType: password Identifier, 1-Read, 2-Write, 3-Privacy, 8-Destroy SL-S, 16-EAS
    //pwd: current password, (4 bytes)
    public native static short fw_set_password(int lDevice,byte[] UID, byte pwdType, byte[] pwd);
    //parameter:
    //pwdType: password Identifier, 1-Read, 2-Write, 3-Privacy, 8-Destroy SL-S, 16-EAS
    //pwd: new password, (4 bytes)
    public native static short fw_write_password(int lDevice,byte[] UID, byte pwdType, byte[] pwd);

    public native static short fw_icode_64bitProtect(int icdev,byte[] UID);
    //parameter:
    //iPage: 0 ~ 9
    //protectStatus:
    //32 bit mode:
    //0-public, 1-read and write protected by read password, 16-write protected by write password, 17-read by read pass, write by write pass
    //64 bit mode:
    //0-public, 1-read and write protected by read plus write password
    public native static short fw_protect_page(int lDevice,byte[] UID, byte iPage, byte protectStatus);
    //===============================================
    //  typeB at88rf020  card  Functions
    //===============================================
    public native static short fw_request_b(int lDevice,byte _Mode,byte AFI, byte N,byte[] ATQB);
    public native static short fw_attrib(int lDevice,byte[] PUPI, byte CID);
    public native static short fw_check_at(int lDevice,byte cid,byte[] key);
    public native static short fw_read_at(int lDevice,byte Adr,byte[] key,byte[] rbuffer);
    public native static short fw_write_at(int lDevice,byte Adr,byte[] sbuffer);
    public native static short fw_changekey_at(int lDevice,byte[] key);
    public native static short fw_lock_at(int lDevice,byte Adr,byte[] sbuffer);
    public native static short fw_CommandLink_at(int lDevice,byte[] sBuffer,int sLen,byte[] rBuffer,int[] rLen);
    public native static short fw_halt_at(int lDevice,byte cid,byte[] key);
    public native static short fw_count_at(int lDevice,byte cid,byte[] key);
}
