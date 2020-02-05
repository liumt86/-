import RPi.GPIO as GPIO
import time
import sqlite3

conn = sqlite3.connect('my_db')　#データベース'my_db'につなげる/connect database 'my_db' 
c = conn.cursor()

#ターブルがあるかどうかを判断する、なければターブルを作成する
#check if table exist, if not, create table 'sensors'
def isExist():
    c.execute(''' SELECT count(name) FROM sqlite_master WHERE type='table' AND name='sensors' ''')
    if c.fetchone()[0]==1:
        print('table exists')
        c.execute('SELECT * FROM sensors')
        print(c.fetchall())
    else:
        tableCreate()
        print('table created')
        
#ターブル'sensors'を作成する関数
#function of creating table 'sensors'
def tableCreate():
    c.execute('''CREATE TABLE sensors
                 (id text, floor integer, direction text, category text, statement integer)''')
    info = [('1et',1,'east','toilet',0),
            ('2et',2,'east','toilet',0),
            ('3et',3,'east','toilet',0),
            ('4et',4,'east','toilet',0),
            ('5et',5,'east','toilet',0),
            
            ('1es',1,'east','shower',0),
            ('2es',2,'east','shower',0),
            ('3es',3,'east','shower',0),
            ('4es',4,'east','shower',0),
            ('5es',5,'east','shower',0),
            
            ('1ek',1,'east','kitchen',0),
            ('2ek',2,'east','kitchen',0),
            ('3ek',3,'east','kitchen',0),
            ('4ek',4,'east','kitchen',0),
            ('5ek',5,'east','kitchen',0),
            
            ('1wt',1,'west','toilet',0),
            ('2wt',2,'west','toilet',0),
            ('3wt',3,'west','toilet',0),
            ('4wt',4,'west','toilet',0),
            ('5wt',5,'west','toilet',0),
            
            ('1ws',1,'west','shower',0),
            ('2ws',2,'west','shower',0),
            ('3ws',3,'west','shower',0),
            ('4ws',4,'west','shower',0),
            ('5ws',5,'west','shower',0),
            
            ('1wk',1,'west','kitchen',0),
            ('2wk',2,'west','kitchen',0),
            ('3wk',3,'west','kitchen',0),
            ('4wk',4,'west','kitchen',0),
            ('5wk',5,'west','kitchen',0),
            ]
    c.executemany('INSERT INTO sensors VALUES (?,?,?,?,?)', info)
    
    
def init():
    GPIO.setwarnings(False)
    GPIO.setmode(GPIO.BOARD)
    GPIO.setup(12,GPIO.IN)
    GPIO.setup(18,GPIO.IN)
    pass

#主関数
#funtion of detecting human
def detct():
    isExist()
    while True:
        #センサー１の状況を検出する
        #detect the statement of sensor1
        if GPIO.input(12)==True:
            print("sensor1_occupied")
            c.execute(''' UPDATE sensors SET statement = 1 WHERE id = '1et' ''')
            conn.commit()
        else:
            print("sensor1_free")
            c.execute(''' UPDATE sensors SET statement = 0 WHERE id = '1et' ''')
            conn.commit()
            
        #センサー２の状況を検出する   
        #detect the statement of sensor2
        if GPIO.input(18)==True:
            print("sensor2_occupied")
            c.execute(''' UPDATE sensors SET statement = 1 WHERE id = '2et' ''')
            conn.commit()
        else:
            print("sensor2_free")
            c.execute(''' UPDATE sensors SET statement = 0 WHERE id = '2et' ''')
            conn.commit()
            
        print("***********************")
        time.sleep(1)
        
        
time.sleep(1)
init()
detct()
conn.close()
GPIO.cleanup()
                
