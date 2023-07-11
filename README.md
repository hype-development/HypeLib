# alumina library
alumina is a Java library for Minecraft Plugins built upon the Spigot API, which differs from the [minecraft-framework](https://github.com/Framework-Library/minecraft-framework) because this library is specifically made for the modern version of Minecraft compared to that one which aims to support all versions of Minecraft ranging from 1.8 to the latest.

## Features
* [Enhanced command system](#enhanced-command-system)


## Enhanced Command System
This part of alumina is the same as [minecraft-framework](https://github.com/Framework-Library/minecraft-framework), as it is a pretty solid system already!

### Making a Command
To make a command you must implement `Command`, then add your implementation code.

```java
public class CommandExample implements Command {

    @Override
    public void execute(@NotNull Context context) {
        CommandSender sender = context.sender();
        sender.sendMessage("Some message");
    }

}
```

### Registering a Command
Your main class must extend `AluminaPlugin`, then you can register commands using the `registerCommand` method!

```java
public final class MyPlugin extends AluminaPlugin {

    @Override
    public void enable() {
        // Register the /example command!
        registerCommand(new CommandBuilder(new CommandExample())
                .name("example")
                .aliases("ex")
                .description("Example command")
                .permission("apexcore.example")
                .usage("/example")
                .playerOnly()
        );
    }

    @Override
    public void disable() {

    }
}
```