# INIreader
This reader makes working with ini-files easy.
It automatically keeps context and creates groups/keys so you only need to specify the group once.

An example to retrive a value to an optional could be:
```java
INIConfig config = new INIConfig();
config.loadFile(new File(".", "data.ini");

//This will get the value for Title from the group Application,
//  as that group was last used when calling .get(String key)
if (config.hasGroup("Application"))
  return Optional.of(config.get("Title"));
else
  return Optional.empty();
```

With `.drop()` and `.importFile(File other)` you can clear and merge ini-Files as well before writing them back with `.saveFile(File file)`.

Note that this will NOT read or write comments (they are skipped over when reading), so every comment will be lost after calling `.saveFile(File file)`!
