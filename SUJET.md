# Création d’un logiciel de gestion d’établissement scolaire V2
L’ENSUP souhaite créer un logiciel (application Web) sur mesure de gestion de l’école. Ce logiciel destiné à la direction permettra de mieux gérer la scolarité les étudiants, mais aussi les modules de formation, le réseau des enseignants et les cours.

Un étudiant est caractérisé par :
-	Un identifiant unique
-	Un nom
-	Un prénom
-	Une adresse email
-	Une adresse
-	Un numéro de téléphone
-	Une date de naissance

Un enseignant est caractérisé par :
-	Un identifiant unique
-	Un nom
-	Un prénom
-	Une adresse email
-	Une adresse
-	Un numéro de téléphone
-	Une matière enseignée

L’école ENSUP est caractérisée par :
-	Un nom
-	Une adresse email
-	Une adresse
-	Un numéro de téléphone
-	Un directeur

Un cours est caractérisé par :
-	Thème du cours
-	Nombre d’heures

## Version 1
Dans sa version 1, le logiciel permet au responsable des études ou au directeur de l’école de 
-	Créer un Etudiant, 
-	Associer un cours à un étudiant
-	Lire les informations d’un étudiant, 
-	Modifier une des informations sur l’étudiant, 
-	Supprimer un étudiant, 
-	Lister l’ensemble des étudiants de l’école

L’accès au logiciel est sécurisé. Seuls le directeur et le responsable des études pourrons utiliser le logiciel dans un premier temps.
Toutes les fonctionnalités seront accessibles une fois que chaque utilisateur se sera authentifié.
Le logiciel présente les fonctionnalités sous forme d’un menu. La fonctionnalité de listing des étudiants est accessible uniquement au Directeur.

## Contraintes :
1.	Mise en œuvre d’au moins 3 microservices basés sur Spring Boot, Spring Cloud, 
2.	Automatisation avec Docker, Terraform, Ansible
3.	85% de couverture de code sur la couche service
4.	Tests fonctionnels avec Selenium WebDriver
5.	Tests d’intégration 
6.	Test de performance avec Gatling
7.	Sécurité applicative basée sur VAULT
8.	Déploiement dans un cloud provider (au choix)

## Livraison : 
Envoyer par mail [douglasmbiandou@gmail.com](douglasmbiandou@gmail.com) avant la fin de l’examen un lien vers un repository Github contenant votre proposition. La livraison contiendra un fichier README.TXT qui précisera les étapes à réaliser pour installer l’application chez le client.