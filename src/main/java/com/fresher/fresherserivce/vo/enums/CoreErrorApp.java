package com.fresher.fresherserivce.vo.enums;

public enum CoreErrorApp {
    SUCCESS(200, "Thành công"),
    ERR_LOGIN(2, "Lỗi login"),
    NOPERMITION(3, "Không có quyền truy cập hệ thống."),
    ERR_NOSESSION(4, "Lỗi Phiên đăng nhập"),
    DATAEMTY(5, "Không có dữ liệu thỏa mãn"),
    ERR_SIGN_IN(6, "Lỗi tạo mới tài khoản"),
    ERR_UPDATE_EVALUATE(7, "Lỗi! Thực tập sinh không tồn tại trong hệ thống."),
    ERR_EXIST_ACCOUNT(8, "Tài khoản đã tồn tại trên hệ thống.");

    private final int code;
    private final String description;

    private CoreErrorApp(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public int getCode() {
        return this.code;
    }
}
