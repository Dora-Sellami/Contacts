<?php
$bd="basecontacts";
$nom=$_POST["nom"];
$adresse=$_POST["adresse"];
$tel1=$_POST["tel1"];
$tel2=$_POST["tel2"];
$entreprise=$_POST["entreprise"];
$connexion=mysqli_connect("localhost","root","",$bd);
if($connexion){
    $sql="insert into contacts values('','$nom','$adresse','$tel1','$tel2','$entreprise')";
    if mysql_querry($connexion,$sql){
        echo "Succees Ajout";
    }
    else{
        echo "probleme d'ajout";
    }

    
}