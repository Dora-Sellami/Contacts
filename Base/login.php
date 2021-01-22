<?php
$bd = "basecontacts";
$login = $_POST["Login"];
$password = $_POST["Password"];



$connexion = mysqli_connect(localhost,"root","",$bd);
if($connexion) {
    $sql ="select * from users where login like '$login' and password like '$password'" ;
    $resultat = mysqli_query($connexion,$sql);
    if(mysqli_num_rows($resultat)>0){
        echo"succes";
    }else{
        echo "echec";
    }mysqli_close($connexion);

}else{
    echo "prob de connexion";
}
?>