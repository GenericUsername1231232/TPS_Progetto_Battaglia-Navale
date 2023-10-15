echo off

cd Server
START /B java -jar Server.jar
cd ..

cd Client
java -jar Client.jar