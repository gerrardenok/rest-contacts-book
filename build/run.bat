@echo off

set APP_NAME=rest-contacts-book-1.0-SNAPSHOT
set XMX=256m
set XMS=256m
set PORT=8080
set DB_URL=jdbc:mysql://localhost:3306/contacts_book
set DB_USER=root
set DB_PASSWORD=1111
                                                                                                                                                
start javaw -jar -Xms%XMS% -Xmx%XMX% %APP_NAME%.jar --server.port=%PORT% --spring.datasource.url=%DB_URL% --spring.datasource.username=%DB_USER% --spring.datasource.password=%DB_PASSWORD%

@echo on
