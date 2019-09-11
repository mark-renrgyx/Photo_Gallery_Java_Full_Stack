# Photo Gallery -- Java Full-Stack

## Documentation 
https://drive.google.com/drive/u/0/folders/1rWj1To9joq7GnTEcjiYjaPWle6H1gMKB

## Required Libraries

### Add to build path external JARs:
1. Hibernate (all .jar files under the /required folder)
2. mysqlconnector
3. Apache Tomcat (8.5 is supported)


### These libraries should be added to build path as external .jar and also be copied to WebContent/WEB-INF/lib:

1. (apache) commons-io-2.6.jar
2. (apache) commons-fileupload-1.4-sources.jar

## Configuration
Modify the DBConstants.java file for your system (path to folder holding images)

    src/db/DBConstants.java

Modify hibernate XML config (username, password).  (It is set up for MySQL5, modify if needed.)
    
    src/hibernate.cfg.xml
   