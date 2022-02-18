package rf;

/**
 * @author luoluo.hao
 */
public class umf_javacall {
    public umf_javacall() {
    }

    public static native int fw_init_ex(int var0, char[] var1, long var2);

    public static native int fw_init(int var0, long var1);

    public static native short fw_exit(int var0);

    public static native void hex_a(char[] var0, char[] var1, int var2);

    public static native short a_hex(char[] var0, char[] var1, int var2);

    public static native short fw_des(char[] var0, char[] var1, char[] var2, int var3);

    public static native short fw_getver(int var0, char[] var1);

    public static native short fw_beep(int var0, short var1);

    public static native short fw_reset(int var0, int var1);

    public static native short fw_swr_eeprom(int var0, int var1, int var2, char[] var3);

    public static native short fw_srd_eeprom(int var0, int var1, int var2, char[] var3);

    public static native short fw_rdCardStatus(int var0, int[] var1);

    public static native short fw_request(int var0, short var1, int[] var2);

    public static native short fw_anticoll(int var0, short var1, int[] var2);

    public static native short fw_select(int var0, int var1, short[] var2);

    public static native short fw_card(int var0, short var1, int[] var2);

    public static native short fw_card_hex(int var0, short var1, char[] var2);

    public static native short fw_card_str(int var0, short var1, char[] var2);

    public static native short fw_authentication(int var0, short var1, short var2);

    public static native short fw_read(int var0, short var1, char[] var2);

    public static native short fw_read_hex(int var0, short var1, char[] var2);

    public static native short fw_write(int var0, short var1, char[] var2);

    public static native short fw_write_hex(int var0, short var1, char[] var2);

    public static native short fw_changeKey(int var0, short var1, char[] var2, char[] var3, char[] var4);

    public static native short fw_changeKey_hex(int var0, short var1, char[] var2, char[] var3, char[] var4);

    public static native short fw_increment(int var0, short var1, int var2);

    public static native short fw_decrement(int var0, short var1, int var2);

    public static native short fw_initval(int var0, short var1, int var2);

    public static native short fw_readval(int var0, short var1, int[] var2);

    public static native short fw_transfer(int var0, short var1);

    public static native short fw_restore(int var0, short var1);

    public static native short fw_load_key(int var0, short var1, short var2, char[] var3);

    public static native short fw_halt(int var0);

    public static native short fw_read_S70(int var0, short var1, char[] var2);

    public static native short fw_write_S70(int var0, short var1, char[] var2);

    public static native short fw_request_ultralt(int var0, short var1);

    public static native short fw_anticall_ultralt(int var0, long[] var1);

    public static native short fw_select_ultralt(int var0, long var1);

    public static native short fw_read_ultralt(int var0, short var1, char[] var2);

    public static native short fw_write_ultralt(int var0, short var1, char[] var2);

    public static native short fw_halt_ultralt(int var0);

    public static native short fw_ultralt_C_authen(int var0, char[] var1);

    public static native short fw_ultralt_C_setSafePage(int var0, short var1, char var2);

    public static native short fw_ultralt_C_changePwd(int var0, char[] var1, char[] var2);

    public static native short fw_ultralt_C_lockPage(int var0, short var1);

    public static native short fw_reset_mifarepro(int var0, char[] var1, char[] var2);

    public static native short fw_setcpu_mifarepro(int var0, short var1);

    public static native short fw_apdu_mifarepro(int var0, short var1, char[] var2, char[] var3, char[] var4);

    public static native short fw_pro_reset(int var0, char[] var1, char[] var2);

    public static native short fw_pro_commandlink(int var0, short var1, char[] var2, char[] var3, char[] var4, short var5, short var6);

    public static native short fw_read_4442(int var0, short var1, char[] var2, int var3);

    public static native short fw_write_4442(int var0, short var1, char[] var2, int var3);

    public static native short fw_getProtectData_4442(int var0, short var1, char[] var2, int var3);

    public static native short fw_setProtectData_4442(int var0, short var1, char[] var2, int var3);

    public static native short fw_authentikey_4442(int var0, short var1, int var2, char[] var3);

    public static native short fw_changkey_4442(int var0, short var1, int var2, char[] var3);

    public static native short fw_cntReadError_4442(int var0, char[] var1);

    public static native short fw_read_4428(int var0, short var1, char[] var2, int var3);

    public static native short fw_write_4428(int var0, short var1, char[] var2, int var3);

    public static native short fw_getProtectData_4428(int var0, short var1, char[] var2, int var3);

    public static native short fw_setProtectData_4428(int var0, short var1, char[] var2, int var3);

    public static native short fw_authentikey_4428(int var0, char[] var1);

    public static native short fw_changkey_4428(int var0, char[] var1);

    public static native short fw_cntReadError_4428(int var0, char[] var1);

    public static native short fw_PassIn(int var0, short var1);

    public static native short fw_PassGet(int var0, char[] var1, char[] var2);

    public static native short fw_PassCancel(int var0);

    public static native short fw_CheckKeyValue(int var0, char[] var1, char[] var2);

    public static native short fw_mgtReset(int var0);

    public static native short fw_mgtCheckStatus(int var0);

    public static native short fw_mgtReadCard(int var0, byte[] var1, int[] var2);

    public static native short fw_mgtClear(int var0);

    public static native short fw_lcd_dispclear(int var0);

    public static native short fw_lcd_dispstr(int var0, char[] var1);

    public static native short fw_anticoll2(int var0, byte var1, long[] var2);

    public static native short fw_select2(int var0, long var1);

    public static native short fw_reset_desfire(int var0, byte[] var1, byte[] var2);

    public static native short fw_apdu_desfire(int var0, byte var1, byte[] var2, byte[] var3, byte[] var4);

    public static native short fw_authen_desfire(int var0, byte var1, byte[] var2, byte[] var3);

    public static native short fw_authen_desfire_aes(int var0, byte var1, byte[] var2, byte[] var3);

    public static native short fw_getver_desfire(int var0, byte[] var1, byte[] var2);

    public static native short fw_getAIDs_desfire(int var0, byte[] var1, byte[] var2);

    public static native short fw_selectApp_desfire(int var0, byte[] var1);

    public static native short fw_getKeySetting_desfire(int var0, byte[] var1, byte[] var2);

    public static native short fw_getKeyver_desfire(int var0, byte var1, byte[] var2);

    public static native short fw_createApp_desfire(int var0, byte[] var1, byte var2, byte var3);

    public static native short fw_delAID_desfire(int var0, byte[] var1);

    public static native short fw_changeKeySetting_desfire(int var0, byte var1, byte[] var2);

    public static native short fw_changeKey_desfire(int var0, byte[] var1, byte[] var2, byte var3, byte[] var4);

    public static native short fw_changeKey_desfire_aes(int var0, byte[] var1, byte[] var2, byte var3, byte[] var4, byte var5);

    public static native short fw_getFileIDs_desfire(int var0, byte[] var1, byte[] var2);

    public static native short fw_getFileProper(int var0, byte var1, byte[] var2, byte[] var3);

    public static native short fw_changeFileSetting(int var0, byte var1, byte var2, byte[] var3, byte[] var4);

    public static native short fw_createDataFile_desfire(int var0, byte var1, byte var2, byte[] var3, byte[] var4);

    public static native short fw_createBackupDataFile_desfire(int var0, byte var1, byte var2, byte[] var3, byte[] var4);

    public static native short fw_createValueFile_desfire(int var0, byte var1, byte var2, byte[] var3, byte[] var4, byte[] var5, byte[] var6, byte var7);

    public static native short fw_createCsyRecord_desfire(int var0, byte var1, byte var2, byte[] var3, byte[] var4, byte[] var5);

    public static native short fw_delFile_desfire(int var0, byte var1);

    public static native short fw_write_desfire(int var0, byte var1, int var2, int var3, byte[] var4, byte[] var5);

    public static native short fw_read_desfire(int var0, byte var1, int var2, int var3, byte[] var4, byte[] var5);

    public static native short fw_getvalue_desfire(int var0, byte var1, int[] var2, byte[] var3);

    public static native short fw_credit_desfire(int var0, byte var1, int var2, byte[] var3);

    public static native short fw_debit_desfire(int var0, byte var1, int var2, byte[] var3);

    public static native short fw_writeRecord_desfire(int var0, byte var1, int var2, int var3, byte[] var4, byte[] var5);

    public static native short fw_readRecord_desfire(int var0, byte var1, int var2, int var3, byte[] var4, int[] var5, int[] var6, byte[] var7);

    public static native short fw_clearRecord_desfire(int var0, byte var1);

    public static native short fw_commitTransfer_desfire(int var0);

    public static native short fw_abortTransfer_desfire(int var0);

    public static native short fw_formatPICC_desfire(int var0);

    public static native short fw_cpureset(int var0, byte[] var1, byte[] var2);

    public static native short fw_setcpu(int var0, byte var1);

    public static native short fw_cpuapdu(int var0, byte var1, byte[] var2, byte[] var3, byte[] var4);

    public static native short fw_setcpupara(int var0, byte var1, byte var2, byte var3);

    public static native short fw_config_card(int var0, byte var1);

    public static native short fw_inventory(int var0, byte var1, byte var2, byte var3, byte[] var4, byte[] var5);

    public static native short fw_stay_quiet(int var0, byte var1, byte[] var2);

    public static native short fw_select_uid(int var0, byte var1, byte[] var2);

    public static native short fw_reset_to_ready(int var0, byte var1, byte[] var2);

    public static native short fw_readblock(int var0, byte var1, byte var2, byte var3, byte[] var4, byte[] var5, byte[] var6);

    public static native short fw_writeblock(int var0, byte var1, byte var2, byte var3, byte[] var4, byte var5, byte[] var6);

    public static native short fw_lock_block(int var0, byte var1, byte var2, byte[] var3);

    public static native short fw_write_afi(int var0, byte var1, byte var2, byte[] var3);

    public static native short fw_lock_afi(int var0, byte var1, byte var2, byte[] var3);

    public static native short fw_write_dsfid(int var0, byte var1, byte var2, byte[] var3);

    public static native short fw_lock_dsfid(int var0, byte var1, byte var2, byte[] var3);

    public static native short fw_get_systeminfo(int var0, byte var1, byte[] var2, byte[] var3, byte[] var4);

    public static native short fw_get_securityinfo(int var0, byte var1, byte var2, byte var3, byte[] var4, byte[] var5, byte[] var6);

    public static native short fw_set_password(int var0, byte[] var1, byte var2, byte[] var3);

    public static native short fw_write_password(int var0, byte[] var1, byte var2, byte[] var3);

    public static native short fw_icode_64bitProtect(int var0, byte[] var1);

    public static native short fw_protect_page(int var0, byte[] var1, byte var2, byte var3);

    public static native short fw_request_b(int var0, byte var1, byte var2, byte var3, byte[] var4);

    public static native short fw_attrib(int var0, byte[] var1, byte var2);

    public static native short fw_check_at(int var0, byte var1, byte[] var2);

    public static native short fw_read_at(int var0, byte var1, byte[] var2, byte[] var3);

    public static native short fw_write_at(int var0, byte var1, byte[] var2);

    public static native short fw_changekey_at(int var0, byte[] var1);

    public static native short fw_lock_at(int var0, byte var1, byte[] var2);

    public static native short fw_CommandLink_at(int var0, byte[] var1, int var2, byte[] var3, int[] var4);

    public static native short fw_halt_at(int var0, byte var1, byte[] var2);

    public static native short fw_count_at(int var0, byte var1, byte[] var2);

    static {
        System.loadLibrary("umf");
    }
}
