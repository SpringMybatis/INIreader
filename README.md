# Java INI reader
This is a very simple to use, single-class INI-configuration parser.

It is capable of loading and merging INI-files and gives you convenient access to configuration groups by keeping the current context, so you only need to specify the group you want to use once.

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

With `.drop()` and `.importFile(File other)` you can clear and merge INI-files as well before writing them back with `.saveFile(File file)`.

Note that this will NOT read or write comments (they are skipped over when reading), so every comment will be lost after calling `.saveFile(File file)`!

The additional INIUtils.java contains methods to verify values, but can be deleted from your project if you do not need it.
