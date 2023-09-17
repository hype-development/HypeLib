![Sonatype Nexus (Releases)](https://img.shields.io/nexus/r/games.negative.alumina/alumina?server=https%3A%2F%2Frepo.negative.games&nexusVersion=3&logo=sonatype&label=version)
[![Javadoc](https://img.shields.io/badge/JavaDoc-Online-green)](https://docs.alumina.dev) ![Discord](https://img.shields.io/discord/822346437240815656?logo=discord&label=discord)

# Repository
## Maven

```xml
<repository>
    <id>Negative Games</id>
    <url>https://repo.negative.games/repository/development/</url>
</repository>
```

```xml
<dependency>
    <groupId>games.negative.alumina</groupId>
    <artifactId>alumina</artifactId>
    <version>{VERSION}</version>
    <scope>compile</scope>
</dependency>
```

## Gradle
```groovy
maven { url 'https://repo.negative.games/repository/development/' }
```

```groovy
implementation("games.negative.alumina:alumina:{VERSION}")
```

# Wiki

## Main Features
* [Enhanced command system](#enhanced-command-system)
* [Enhanced message system](#enhanced-message-system)
* [Enhanced menu system](#enhanced-menu-system)
## Other Utilities
* [Color Code Utility](#color-code-utility)

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

## Enhanced Menu System
This is a feature which can allow you to create intractable chest menus more efficiently and easily!

### Creating a Menu
When creating a menu, you need to make a dedicated class which holds all related information to the menu, such as the title, size, and items.

```java
public class ExampleMenu extends ChestMenu {
    
    public ExampleMenu() {
        super("Amazing Menu", 1);

        ItemStack diamond = new ItemStack(Material.DIAMOND);
        setItem(0, diamond, "diamond");
        
        ItemStack emerald = new ItemStack(Material.EMERALD);
        setItem(1, emerald, "emerald");
    }

    @Override
    public void onFunctionClick(@NotNull Player player, @NotNull MenuItem item, @NotNull InventoryClickEvent event) {
        event.setCancelled(true);

        String key = item.key();
        assert key != null;

        switch (key.toLowerCase()) {
            case "diamond" -> handleDiamond(player);
            case "emerald" -> handleEmerald(player);
        }
    }
    
    private void handleDiamond(@NotNull Player player) {
        player.sendMessage("You clicked on the diamond!");
        // other handling code
    }
    
    private void handleEmerald(@NotNull Player player) {
        player.sendMessage("You clicked on the emerald!");
        // other handling code
    }
    
}
```

### Opening a Menu
To open a menu, you can just use the `open` method when making a new instance of class menu you wish to open!

```java
new ExampleMenu().open(player);
```


## Color Code Utility
This class is a small utility class which can assist you with color code manipulation, such as translating color codes and hex codes!

### Translating Regular Color Codes
This method is here in case you wish to just translate regular color codes, such as `&a` or `&c`.
```java
String message = ColorUtil.basicTranslate("&aHello world!");
```

### Translating Hex Color Codes
This method is here in case you wish to translate hex color codes, such as `#00ff00` or `#ff0000`.
```java
String message = ColorUtil.hexTranslate("#00ff00Hello world!");
```
