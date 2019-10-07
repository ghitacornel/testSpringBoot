package beans.properties.xml;

/**
 * bean with injected properties taken from XML<br>
 * the bean definition is provided via XML where the bean properties are also set<br>
 */
public class BeanWithPropertiesInjectedFromXML {

    // these field values will be overwritten with values taken from xml file
    final private String constructorProperty;
    private int simpleProperty;

    /**
     * a PUBLIC NO ARGUMENTS CONSTRUCTOR is required by @Component
     */
    public BeanWithPropertiesInjectedFromXML() {
        simpleProperty = -1;
        constructorProperty = "xxx";
    }

    /**
     * this constructor is used for injection a constructor provided property
     *
     * @param constructorProperty the constructor provided property
     */
    public BeanWithPropertiesInjectedFromXML(String constructorProperty) {
        this.constructorProperty = constructorProperty;
    }

    public String getConstructorProperty() {
        return constructorProperty;
    }

    public int getSimpleProperty() {
        return simpleProperty;
    }

    /**
     * properties of this type cannot be injected without a provided setter
     *
     * @param simpleProperty the simple property to set
     */
    public void setSimpleProperty(int simpleProperty) {
        this.simpleProperty = simpleProperty;
    }
}
