The project is based on java 8. The dependencies used are: logback, jaxb and junit5.

As for unmarshaling the xml files I have used jaxb. The xml domain classes aregenerated using the maven xjc plugin. it 
is currently commented out, as if not, each build will result in the source changes.

For converting the xml elements into wiki string representation I have used state pattern with the help of the java SPI.
It is easily extensible. After the element are converted they are saved into a file in the output directory with the 
same name of the xml file but with wiki extension.

For monitoring to check if new files are added to the input dorectory for conversion, I have scheduled a cyclic task(5s)
in the SchedulerThreadPool. Also I make use of SchedulerThreadPool to convert multiple xml files in parallel.

Logback is used and configured for logging. A file with the name application.log is generated.

As for testing, it is done with junit5.

For building the artifact, please check out the project and run the following command:<br/>
mvn clean package<br/>
Then in the target directory you can find a jar file and lib folder. these two must be together for the project to work.

For running the project, after you have built it. navigate to the directory containing the jar file and lib folder. and 
execute the following command in the command propmpt:<br/>
java -jar wiki-1.0-SNAPSHOjar <input-dir> <output-dir><br/>
for example:<br/>
java -jar wiki-1.0-SNAPSHOjar /home/mohammad/Desktop/bh/wiki/src/test/resources /home/mohammad/Desktop/bh
