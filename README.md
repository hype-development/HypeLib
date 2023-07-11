# alumina library
alumina is a Java library for Minecraft Plugins built upon the Spigot API, which differs from the [minecraft-framework](https://github.com/Framework-Library/minecraft-framework) because this library is specifically made for the modern version of Minecraft compared to that one which aims to support all versions of Minecraft ranging from 1.8 to the latest.

## Features
* [Enhanced command system](#enhanced-command-system)
* [Enhanced message system](#enhanced-message-system)

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

## Enhanced Message System
This is a feature which can allow you to create messages more efficiently and easily, allowing you to put placeholders in your messages without making redundant translation systems to translate placeholders. With the added bonus of automatically translating color codes and hex codes!

### Creating a Message
To create a message, you just statically reference the `of` method and input your text!

```java
Message message = Message.of("&aHello world!");
```

### Adding Placeholders
To use placeholders in your message, you can just put them right in your message and replace them using the `replace` method!

```java
Message message = Message.of("&aHello, %player%!")
message.replace("%player%", player.getName());
```

### Sending or Broadcasting a Message
To send a message to a player, you can use the `send` method, or to broadcast a message to all players, you can use the `broadcast` method!

```java
message.send(player); // Send to a player!
message.broadcast(); // Broadcast to all online players!
```