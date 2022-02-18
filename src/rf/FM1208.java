
package rf;

public class FM1208
{

    static
    {
       System.loadLibrary("umf");
    }
    

    public native static long FWCosSelecteApp(long lDevHandle);
    public native static long FWCosDeleteFileSys(long lDevHandle);
    public native static long FWCosCreateDirectory(long lDevHandle,
						 short usDirSize,
						 short usDirFileID,
						 long ulCreateSec,
						 long ulDeleteSec);
    public native static long FWCosSelectDirectory(long lDevHandle,
						   short usDirID);
    public native static long FWCosCreateBinaryFile(long lDevHandle,
						    short usDirFileID,
						    short usFileID,
						    short usFileLen,
						    char cryptyType,
						    long ulReadSec,
						    long ulUpdateSec,
						    long ulDeleteSec);
    public native static long FWCosActiveDirectory(long lDevHandle,
						   short usDirFileID);

    public native static long FWCosReadBinaryFile(long lDevHandle,
						  short usFileID,
						  char[] pbBinData,
						  short usOffset,
						  short usReadLen,
						  char cryptyType,
						  char[] pProKey,
						  long ulKeyLen);
    public native static long FWCosUpdateBinaryFile(long lDevHandle,
						    short  usFileID,
						    short usOffset,
						    char[] pbBinData,
						    short usUpdateLen,
						    char cryptyType,
						    char[] pProKey,
						    long ulKeyLen);
    public native static long FWCosVerifyKey(long lDevHandle,
					     char[] pbKey,
					     long ulKeyLen,
					     long ulKeyType);

    public native static long FWCosUpdateKey(long lDevHandle,
					     char[] pbOldKey,
					     long ulOldKeyLen,
					     char[] pbNewKey,
					     long ulNewKeyLen,
					     long bKeyType);

    public native static long FWCosCreateKeyFile(long lDevHandle,
						short	usDirFileID,
						short	usFileID,
						short  uFileLen,
						long	ulGenSec);
    public native static long FWCosAddKey(long lDevHandle,
					  char bKeyType,
					  char[] pbKeyValue,
					  long ulKeyValueLen);

    public native static long FWCosAddKeyEx(long lDevHandle,
					    char bKeyType,
					    char ucKeyID,
                             		    char[] pbKeyValue,
					    long ulKeyValueLen,
					    char ucSecUpdate,
					    char ucNextSt,
					    char cryptyType);

    public native static long FWCosUpdatePin(long lDevHandle,
					     char[] pbOldPin,
					     long ulOldPinLen,
					     char[] pbNewPin,
					     long ulNewPinLen);

    public native static long FWCosCreateADF(long lDevHandle,
					     short usDirFileID,
					     long ulCreateSec,
					     long ulDeleteSec,
					     char[] pbDirName,
					     char ucDirNameLength);

    public native static long FWCosGetBalance(long lDevHandle,
					      int[] balance);

    public native static long FWCosCredit(long lDevHandle,
					  char ucKeyID,
					  int value,
					  char[] terminalID,
					  char[] pbCreditKey,
					  long ulCreditKeyLen);

    public native static long FWCosPurchase(long lDevHandle,
					    char ucKeyID,
					    int value,
					    char[] terminalID,
					    char[] pbPurchaseKey,
					    long ulPurchaseKeyLen);

    public native static long FWCosBeep(long lDevHandle,
					short MSec);

    public native static short asc_hex(char[] asc,
					char[] hex,
					long length);

    public native static short hex_asc( char[] hex,
					char[] asc,
					long length);

    

}
