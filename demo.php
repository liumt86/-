<?php
    $db = new \PDO('sqlite:database/my_db');//database'my_db'をつなげる/connect database 'my_db'
    $result = $db->query('SELECT * FROM sensors');//table 'sensors'からデータを取る/get data from table 'sensors'
    $recordlist = array();
    //dataは1行ごとにarrayに届く
    //put the data into the array
    foreach($result as $row)
    { 
        $recordlist[] =  $row;
    }
    $info = json_encode($recordlist);//JSON形式でデータをカプセル化する/Encapsulate the data in json format
    echo $json = '{"success":true,"info":'.$info.'}';
?>
