# mhider

## A Plugin that allows morphing into other Player

## General info

Just simple plugin.

## Building from source

1. Clone the repo

    ```shell
    $ git clone https://github.com/Zielin0/mhider.git
    ```

2. Install all maven dependencies.

    - If you want the jar to be compiled directly to your plugins
      directory add this to `pom.xml` in `maven-jar-plugin` section:

   ```xml
   <configuration>
     <outputDirectory>C:\path\to\plugins</outputDirectory>
   </configuration>
   ```

3. Run maven build commands:

    ```shell
    $ mvn clean package
    ```
   
4. Plugin should appear in the `target/` directory (only if you skipped `outputDirectory` configuration).

## License

The project is under the [MIT](./LICENSE) License.