![logo](assets/alumina.png)


![Sonatype Nexus (Releases)](https://img.shields.io/nexus/r/games.negative.alumina/alumina?server=https%3A%2F%2Frepo.negative.games&nexusVersion=3&logo=sonatype&label=version)
[![Javadoc](https://img.shields.io/badge/JavaDoc-Online-green)](https://jd.alumina.dev) ![Discord](https://img.shields.io/discord/822346437240815656?logo=discord&label=discord)

***
![features](assets/Features.png)
***
![install](assets/Installation%20Details.png)
![maven](assets/Maven.png)
```xml
<repository>
    <id>Negative Games</id>
    <url>https://repo.negative.games/repository/maven-releases/</url>
</repository>
```
```xml
<dependency>
    <groupId>games.negative.alumina</groupId>
    <artifactId>alumina</artifactId>
    <version>1.5.1</version>
    <scope>compile</scope>
</dependency>
```

### Optional Shading
In case other software in your JVM is using this library, you should shade it into your jar and relocate it to your preferred namespace using the `maven-shade-plugin`.
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-shade-plugin</artifactId>
    <version>3.5.0</version>
    <executions>
        <execution>
            <phase>package</phase>
            <goals>
                <goal>shade</goal>
            </goals>
            <configuration>
                <createDependencyReducedPom>false</createDependencyReducedPom>
                <relocations>
                    <relocation>
                        <pattern>games.negative.alumina</pattern>
                        <shadedPattern>[YOUR NAMESPACE].alumina</shadedPattern>
                    </relocation>
                </relocations>
            </configuration>
        </execution>
    </executions>
</plugin>
```
***
![gradle](assets/Gradle.png)
```groovy
maven { url 'https://repo.negative.games/repository/maven-releases/' }
```
```groovy
implementation("games.negative.alumina:alumina:1.5.1")
```
***
![discord](assets/Discord.png)
[![Discord](https://discord.com/api/guilds/822346437240815656/widget.png?style=banner2)](https://discord.gg/XnHEn6BB2j)
