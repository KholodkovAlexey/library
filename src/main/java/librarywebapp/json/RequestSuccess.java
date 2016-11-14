package librarywebapp.json;

public final class RequestSuccess implements JSONConvertible {
    
    private final boolean success;
    
    public RequestSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toJSON() {
        return "{\"success\":" + Boolean.toString(success) + "}";
    }
    
}
