package utils;

import java.util.HashMap;

/**
 * 解密接口
 * @Filename: com.septinary.common.util.IDecryptable.java of the project [com.septinary.common]
 *     @Type: IDecryptable
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月23日下午10:13:43
 * 
 * @param <T>
 */
public interface IDecryptable<T> {
	
	public T decrypt(T target, HashMap<Object, Object> options);
	
}
