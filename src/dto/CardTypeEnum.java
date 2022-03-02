package dto;

import org.apache.commons.lang3.StringUtils;

public enum CardTypeEnum {
    /**
     */
    NORMAL("N","普通用户"),

    /**
     */
    SUPER("S","管理员"),

   ;



    private final String code;

    private final String desc;

    CardTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    /**
     * 获取枚举码值
     * 序列化时采用改值
     *
     * @return 序号
     */
    public String code() {
        return this.code;
    }


    public String desc() {
        return this.desc;
    }

    public String getCode() {
        return code;
    }

    public static CardTypeEnum getItem(String code){
        for(CardTypeEnum item : values()){
            if(StringUtils.equals(item.getCode(), code)){
                return item;
            }
        }
        return null;
    }
}
