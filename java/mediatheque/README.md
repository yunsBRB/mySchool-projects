# Media Library

A Java console application for managing media, members and loans. Data is stored in memory and resets on restart.

Requires JDK 17 or later. From this folder:

```sh
javac -encoding UTF-8 -d build "@sources.txt"
java -cp build Main
```

Demo data loads at startup. Choose `0` to quit. Update `sources.txt` when adding source files.

CI checks compilation and menu startup; loan rules still need dedicated tests.
