package uz.ecms.madaniyat.models;


import uz.ecms.madaniyat.config.DataType;

public class ErrorModel {
    private String message;
    private DataType.Source type;
    private String error_debug_code;

    public ErrorModel(String message, DataType.Source type, String error_debug_code) {
        this.message = message;
        this.type = type;
        this.error_debug_code = error_debug_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataType.Source getType() {
        return type;
    }

    public void setType(DataType.Source type) {
        this.type = type;
    }

    public String getError_debug_code() {
        return error_debug_code;
    }

    public void setError_debug_code(String error_debug_code) {
        this.error_debug_code = error_debug_code;
    }
}
