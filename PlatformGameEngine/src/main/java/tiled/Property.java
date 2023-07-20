package tiled;

import java.lang.reflect.Method;

public class Property {

    private PropertyType type;
    private String name;
    private Object value;


    public Property(PropertyType type, String name, Object value) {
        setType(type);
        setName(name);
        setValue(value);
    }

    public Property(PropertyType type, String name) {
        setType(type);
        setName(name);
        setValue(null);
    }

    public PropertyType getType() {
        return type;
    }

    public void setType(PropertyType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {

       return obj instanceof Property && ((Property) obj).getName() == this.getName()
               && ((Property) obj).getValue() == this.getValue() && ((Property) obj).getType() == this.getType();
    }
}
