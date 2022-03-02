package dto;

public class CardDto {

    private String id;

    private String name;

    private CardTypeEnum permission;

    public CardDto(String id, String name, String permission) {
        this.id = id;
        this.name = name;
        this.permission = CardTypeEnum.getItem(permission);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CardTypeEnum getPermission() {
        return permission;
    }

    public void setPermission(CardTypeEnum permission) {
        this.permission = permission;
    }
}
