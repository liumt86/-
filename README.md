# People Detect System/人が特定の場所にいるかどうかを照会するシステム
## 1. Setting セッティング
web server is conferenced by this website  
[**Setting up an NGINX web server on a Raspberry Pi**]https://www.raspberrypi.org/documentation/remote-access/web-server/nginx.md

## 2. Structure 構造
first, collect data from sensors and then send the data into database;   
next, get the data from database and encapsulate it in json format;  
finally, put the data on webpage and use Ajax to refresh data every second.
