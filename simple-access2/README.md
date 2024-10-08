# spring-oracle-example

## build
```shell
export PROJECT_ID=$(gcloud config list --format 'value(core.project)')
gradle build
gcloud builds submit --tag gcr.io/$PROJECT_ID/api-oracle .
gcloud run deploy api-oracle \
--image gcr.io/$PROJECT_ID/api-oracle \
--platform managed \
--region asia-northeast1 \
--tag=test -q
```

## test data
```sql
CREATE TABLE store_info (
    store VARCHAR2(100) PRIMARY KEY,
    address VARCHAR2(200),
    category1 NUMBER,
    category2 NUMBER,
    category3 NUMBER,
    category4 NUMBER,
    lat_long VARCHAR2(100),
    city VARCHAR2(100)
);

-- Insert data
INSERT INTO store_info (store, address, category1, category2, category3, category4, lat_long, city) 
VALUES ('立石駅前店', '東京都葛飾区新小岩', 340000000, 360000000, 380000000, 390000000, '35.717041966067406, 139.85802335867652', 'Osaka');

INSERT INTO store_info (store, address, category1, category2, category3, category4, lat_long, city) 
VALUES ('方南町店', '東京都杉並区和泉', 220000000, 210000000, 240000000, 290000000, '35.675011778417826, 139.65271420380046', 'Yokohama');

INSERT INTO store_info (store, address, category1, category2, category3, category4, lat_long, city) 
VALUES ('所沢Part1店', '埼玉県所沢市日吉町', 180000000, 190000000, 180000000, 190000000, '35.78771811026823, 139.47138622962873', 'Shibuya City');

INSERT INTO store_info (store, address, category1, category2, category3, category4, lat_long, city) 
VALUES ('北千住店', '東京都足立区千住', 370000000, 390000000, 410000000, 360000000, '35.752158340288375, 139.80242113650192', 'Nagoya');

INSERT INTO store_info (store, address, category1, category2, category3, category4, lat_long, city) 
VALUES ('六本木店', '東京都港区六本木', 440000000, 480000000, 540000000, 580000000, '35.66179458689307, 139.7346378353036', 'Fukuoka');

INSERT INTO store_info (store, address, category1, category2, category3, category4, lat_long, city) 
VALUES ('池袋東口店', '東京都豊島区南池袋', 390000000, 390000000, 420000000, 410000000, '35.726543932820114, 139.7160068925317', 'Sapporo');

INSERT INTO store_info (store, address, category1, category2, category3, category4, lat_long, city) 
VALUES ('瑞江駅前店', '東京都江戸川区瑞江', 240000000, 340000000, 310000000, 290000000, '35.6970437514581, 139.89420224205693', 'Shinjuku City');

INSERT INTO store_info (store, address, category1, category2, category3, category4, lat_long, city) 
VALUES ('越谷赤山店', '埼玉県越谷市赤山町', 140000000, 150000000, 160000000, 180000000, '35.88287721618488, 139.78347984845539', 'Saitama');

INSERT INTO store_info (store, address, category1, category2, category3, category4, lat_long, city) 
VALUES ('神保町店', '東京都千代田区神田神保町', 210000000, 190000000, 200000000, 290000000, '35.69706132337424, 139.75627105738332', 'Kobe');

INSERT INTO store_info (store, address, category1, category2, category3, category4, lat_long, city) 
VALUES ('成田西口店', '千葉県成田市馬橋', 410000000, 430000000, 510000000, 415000000, '35.778572088799, 140.31239627468003', 'Minato City');

INSERT INTO store_info (store, address, category1, category2, category3, category4, lat_long, city) 
VALUES ('川越クレアモール店', '埼玉県川越市新富町', 120000000, 130000000, 120000000, 180000000, '35.91347275611276, 139.4828584400778', 'Kyoto');

INSERT INTO store_info (store, address, category1, category2, category3, category4, lat_long, city) 
VALUES ('練馬春日町店', '東京都練馬区春日町', 130000000, 120000000, 190000000, 170000000, '35.75325978086609, 139.6427175835712', 'Setagaya City');

INSERT INTO store_info (store, address, category1, category2, category3, category4, lat_long, city) 
VALUES ('柏旭町店', '千葉県柏市旭町', 290000000, 310000000, 390000000, 320000000, '35.85793039760093, 139.96341829457873', 'Hiroshima');

INSERT INTO store_info (store, address, category1, category2, category3, category4, lat_long, city) 
VALUES ('高幡不動店', '東京都日野市高幡', 140000000, 160000000, 170000000, 140000000, '35.662182014429725, 139.41318466503986', 'Kawasaki');

INSERT INTO store_info (store, address, category1, category2, category3, category4, lat_long, city) 
VALUES('溝ノ口店', '神奈川県川崎市高津区溝口', 270000000, 370000000, 370000000, 330000000, '35.60336337305878, 139.61192022693433', 'Chiba');


INSERT INTO store_info (store, address, category1, category2, category3, category4, lat_long, city) 
VALUES('新大久保駅前店', '東京都新宿区百人町', 190000000, 150000000, 150000000, 210000000, '35.703178403897056, 139.6975225522447', 'Yokohama');

COMMIT;
```

```
# .bash_profile

# Get the aliases and functions
if [ -f ~/.bashrc ]; then
        . ~/.bashrc
fi
PATH=$PATH:$HOME/bin

export ORACLE_HOME=/opt/oracle/product/21c/dbhomeXE

export PATH
export PATH=$PATH:/opt/oracle/product/21c/dbhomeXE/bin
# User specific environment and startup programs


export ORACLE_SID=XE

```

```
sqlplus "/as sysdba"

STARTUP
```

#Insert data curl example
```
        curl --location 'https://api-oracle-904838507507.asia-northeast1.run.app/api/v1/store-info' \
        --header 'Content-Type: application/json' \
        --data '{ 
            "store":"test-store-001", 
            "address": "test-address", 
            "category1": 100000, 
            "category2": 22222,  
            "category3": 333333, 
            "category4": 444444, 
            "lat_long": "35.1994, 129.0596" , 
            "city": "busan" 
        }'
```

