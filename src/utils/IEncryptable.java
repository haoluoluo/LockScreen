package utils;

import java.util.HashMap;

/**
 * 加密接口
 * @Filename: com.septinary.common.util.IEncryptable.java of the project [com.septinary.common]
 *     @Type: IEncryptable
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月23日下午10:14:09
 * 
 * @param <T>
 */
public interface IEncryptable<T> {

	public T encrypt(T source, HashMap<Object, Object> options);

}
