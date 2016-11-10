package librarywebapp.json;

public final class JSONArray<T extends JSONConvertible> implements JSONConvertible {
    
    private final Iterable<T> array;

    public JSONArray(Iterable<T> array) {
        this.array = array;
    }

    @Override
    public String toJSON() {
        String res = "[";

        boolean add_comma = false;
        for (JSONConvertible jobj: array) {
            
            if (add_comma) {
                res += ',';
            } else {
                add_comma = true;
            }
            
            res += jobj.toJSON();
        }

        return res + "]";
    }

}
