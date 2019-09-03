# Photo Gallery -- Java Full-Stack

##Documentation 
https://drive.google.com/drive/u/0/folders/1rWj1To9joq7GnTEcjiYjaPWle6H1gMKB

## Required Libraries


### Each library should be added to build path as external .jar and also be copied to WebContent/WEB-INF/lib

1. (apache) commons-io-2.6.jar
2. (apache) commons-fileupload-1.4-sources.jar

## Configuration
Create the DBConstants.java file for your system:

    src/db/DBConstants.java
   
The file should contain this, modified for your system:

    package db;

	public class DBConstants {
	
	public static String url = "jdbc:mysql://localhost:3306/?serverTimezone=EST5EDT";
	public static String username = "root";
	public static String password = "root";
	public static String uploadDirectory = "C:/img/"; // or Mac: "/Users/.../img";
}