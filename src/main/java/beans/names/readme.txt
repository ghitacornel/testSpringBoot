A bean container acts as a Map of type <String,Object> where the key is represented by the bean name
and the associated value is provided on request by the bean container.
A bean container acts as a Map of type <Class,Object> where the key is represented by the bean class
and the associated value is provided on request by the bean container.

A bean container can be seen as a registry with named objects.
A Spring bean container is similar with JNDI repository.
A Spring bean container does not publish beans through a JNDI repository ( unlike as an EJB container ).