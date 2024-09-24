
# Tenisu

## Description

Tenisu est une application RESTful développée avec Spring Boot, permettant de gérer des informations sur des joueurs de tennis. Elle fournit des endpoints pour récupérer les joueurs, obtenir les détails d'un joueur spécifique, et afficher les statistiques des joueurs.

---

## Prérequis

- **Java 21** ou une version plus récente
- **Maven 3.9.9+**

---

## Endpoints

### 1. **Récupérer la liste de tous les joueurs**

- **URL** : `/api/v1/players`
- **Méthode HTTP** : `GET`
- **Description** : Récupère la liste de tous les joueurs trié par leur classement (du meilleur au plus bas).

  
### 2. **Récupérer un joueur par son ID**

- **URL** : `/api/v1/players/{id}`
- **Méthode HTTP** : `GET`
- **Description** : Récupère un joueur spécifique en fonction de son `id`.
- **Paramètres** :
  - `id` (path variable) : L'ID du joueur (ex. : `/api/v1/players/1`)

- **Réponse en cas d'ID invalide** : 
  - Status Code: `404 Not Found`

### 3. **Récupérer les statistiques des joueurs**

- **URL** : `/api/v1/players/statistics`
- **Méthode HTTP** : `GET`
- **Description** : Récupère les statistiques des joueurs: code pays ayant les joueurs avec le meilleur ratio de victoire, taille médiane et IMC moyen.
- **Réponse** :
  ```json
    {
      "countryCode": "SRB",
      "averageBMI": 23.34,
      "medianHeight": 185
    }
  ```

---

## Démarrer l'application

### Étapes pour exécuter l'application localement :

1. **Cloner le projet** :
   ```bash
   git clone https://github.com/tsrblt/tenisu.git
   cd tenisu
   ```

2. **Installer les dépendances** :
   Si vous utilisez Maven, installez les dépendances avec la commande suivante :
   ```bash
   mvn clean install
   ```

3. **Configurer le fichier JSON des joueurs** :
   Assurez-vous que le fichier `headtohead.json` contenant les données des joueurs se trouve dans le répertoire `src/main/resources/static/`.


4. **Lancer l'application** :
   Utilisez Maven pour démarrer l'application Spring Boot :
   ```bash
   mvn spring-boot:run
   ```

   Alternativement, si vous utilisez un IDE comme IntelliJ IDEA ou Eclipse, vous pouvez directement exécuter la classe `main` de l'application dans le fichier `TenisuApplication.java`.

5. **Accéder à l'application** :
   L'application sera accessible à l'adresse : `http://localhost:8080`.

---

## Tester l'application

### Test manuel via Postman ou cURL :

#### 1. **Tester la récupération de tous les joueurs** :
- **Via Postman** : 
  - Méthode : `GET`
  - URL : `http://localhost:8080/api/v1/players`
  
- **Via cURL** :
   ```bash
   curl -X GET http://localhost:8080/api/v1/players
   ```

#### 2. **Tester la récupération d'un joueur par son ID** :
- **Via Postman** :
  - Méthode : `GET`
  - URL : `http://localhost:8080/api/v1/players/1`
  
- **Via cURL** :
   ```bash
   curl -X GET http://localhost:8080/api/v1/players/1
   ```

#### 3. **Tester la récupération des statistiques des joueurs** :
- **Via Postman** :
  - Méthode : `GET`
  - URL : `http://localhost:8080/api/v1/players/statistics`
  
- **Via cURL** :
   ```bash
   curl -X GET http://localhost:8080/api/v1/players/statistics
   ```

### Test automatisé avec JUnit

1. **Exécuter les tests** :
   Vous pouvez exécuter les tests en utilisant Maven avec la commande :
   ```bash
   mvn test
   ```


---

## Technologies utilisées

- **Spring Boot** - Framework Java pour créer des applications web
- **Maven** - Outil de gestion de dépendances et de construction
- **Jackson** - Pour la sérialisation/désérialisation JSON
