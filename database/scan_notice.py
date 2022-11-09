import pymysql

db = pymysql.connect(
    host="127.0.0.1"
    port=3306
    user="hospitaldb"
    password="123456"
    charset="utf8mb4"
)
