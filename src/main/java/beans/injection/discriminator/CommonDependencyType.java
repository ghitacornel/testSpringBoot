package beans.injection.discriminator;

public interface CommonDependencyType {

    default String execute() {
        return this + " executed";
    }

}
