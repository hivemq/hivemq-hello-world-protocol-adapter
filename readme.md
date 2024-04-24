# Hello World Protocol Adapter

This repository is meant to reduce the friction of implementing an own protocol adapter by providing a functioning frame. 

## How adapters are loaded

The adapters are loaded via Java's service loading mechanism. This means the exposed service class needs to be declared
in the file `src/main/resources/META-INF/services/com.hivemq.edge.modules.api.adapters.ProtocolAdapterFactory`. The file
name is the interface class and the content of file is the full-qualified class name of the implementation,
e.g. `com.hivemq.edge.adapters.http.HelloWorldProtocolAdapterFactory`. This is correctly set up in this repository and IDE should reflect renamings of the implementation correctly. Nevertheless a sanity check that the content of this file is correctly set to your implementation makes sense.

## How to compile an adapter jar

The shadow plugin to create a jar with all needed dependencies is set up in this repository. The jar can be created via the gradle task `shadowJar`. The task can be executed via an IDE with gradle integration or by executing the task from the command line from the root folder of this project: `./gradlew shadowJar`. The jar is located after sucessfull execution in `build/libs/`. 
Per default the jar file will be named `hivemq-hello-world-protocol-adapter-<version>-all.jar`. 

## Important classes

### ProtocolAdapterFactory (HelloWorldProtocolAdapterFactory)

This class is loaded by HiveMQ Edge via the service loader framework of Java. It supplies Edge with Information on the Adapter, Config and how the actual Adapter instance is to be constructed.
The `HelloWorldProtocolAdapterFactory` already includes the necessary wiring of classes and there shouldn't be much work needed there besides renaming the class.

### AdapterInformation (HelloWorldProtocolAdapterInformation)

This class contains all information on the adapter like name, protocol, description and much more. The java doc of each method explains its purpose.

### Protocol Adapter (HelloWorldProtocolAdapter)

This class contains the actual implementation of the protocol adapter. 
TODO: Explain methods or reference java doc. 


### AdapterConfig (HelloWorldAdapterConfig)

The config class uses the Jackson framework to define the fields of the config. This way the config will automatically parsed from the input from the ui. 



## How to change the adapter icon

In order to change the icon for the adapter in the ui, you need to place the new image
in `src/main/resources/httpd/images/`and adapt the icon path in the class that extends `AdapterInformation`, which
is `HelloWorldProtocolAdapterInformation` in this project:

```
public class HelloWorldProtocolAdapterInformation extends AbstractProtocolAdapterInformation {
    ...

    @Override
    public @NotNull String getLogoUrl() {
        return "/images/helloWorld.png";
    }
    
    ...
}
```
