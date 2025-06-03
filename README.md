## MedScanner AI

### Objectif
Développer une application mobile intelligente qui permet d’importer une image d’un médicament (ex : ordonnance ou boîte), d’extraire automatiquement le nom du médicament via OCR, d’en consulter les détails médicaux depuis une base de données ou une API (OpenFDA), et d’interagir avec un assistant virtuel médical basé sur l'IA.

---

###  Contexte
Les notices médicales sont souvent longues, techniques, et difficiles à comprendre pour le grand public. Ce projet vise à offrir une solution numérique simple et rapide permettant de reconnaître un médicament à partir d’une image, et de fournir des informations fiables grâce à une base de données médicale enrichie et un chatbot.

---

###  Problématique
- Comment extraire avec précision le nom d’un médicament à partir d’une image sans caméra ?
- Comment garantir l’accès à des informations médicales fiables ?
- Comment proposer un assistant intelligent qui comprend les besoins de l’utilisateur ?

---

### Architecture du projet

Android App (OCR + Retrofit)
│
▼
Spring Boot API (auth + médicaments)
│
▼
MongoDB + OpenFDA API
│
▼
Flask Chatbot IA


---

###  Technologies & Outils

| Technologie      | Version       |
|------------------|---------------|
| Java             | 17+           |
| Spring Boot      | 3+            |
| Android SDK      | 33+           |
| Retrofit         | 2.9+          |
| Gradle           | 7+            |
| MongoDB          | Latest        |
| OCR              | Tesseract (Tess-Two) |
| Flask            | Latest        |
| API Médicale     | [OpenFDA API](https://open.fda.gov) |

---

###  Fonctionnement OCR

- L’utilisateur **importe une image depuis la galerie ou le stockage**.
- Le texte est extrait avec **Tesseract OCR (Tess-Two)**.
- Le nom du médicament est isolé puis envoyé via **Retrofit** à l’API Spring Boot.
- Si le médicament est dans MongoDB, les infos sont renvoyées.
- Sinon, une recherche est faite via **OpenFDA API**.

  ---

###  Architecture de projet 

  <img width="656" alt="Capture d'écran 2025-05-27 141344" src="https://github.com/user-attachments/assets/85e0fd0c-28b8-4df0-93e7-3c4e73204e34" />

### Demonstration

https://youtube.com/shorts/Qh2c8bZKhkA
```
val image = BitmapFactory.decodeFile(imagePath)
val tess = TessBaseAPI()
tess.init(datapath, "eng")
tess.setImage(image)
val extractedText = tess.utF8Text


 Exemple Retrofit
Dépendances build.gradle.kts

implementation("com.squareup.retrofit2:retrofit:2.9.0")
implementation("com.squareup.retrofit2:converter-gson:2.9.0")

interface ApiService {
    @POST("/api/auth/register")
    fun register(@Body user: User): Call<ApiResponse>

    @POST("/api/auth/login")
    fun login(@Body credentials: LoginRequest): Call<AuthResponse>

    @GET("/api/medicaments/{name}")
    fun getMedicament(@Path("name") name: String): Call<Medicament>
}



# #Fonctionnalités principales
 Authentification utilisateur (register/login)

 Scanner OCR à partir d’image (pas caméra)

 Recherche du médicament (MongoDB ou OpenFDA API)

 Chatbot médical IA (Flask)

 Gestion des données de médicaments

Exécution
Lancer l’API Spring Boot :
cd backend-spring
mvn spring-boot:run
Lancer le chatbot Flask :

cd chatbot-flask
pip install -r requirements.txt
python app.py
Lancer l’application Android :
Ouvrir le projet Android dans Android Studio

S'assurer d'utiliser SDK 33+

Ajouter les permissions dans le AndroidManifest.xml :


<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
🗂 Structure du projet

MedScanner/
├── android-app/           # App mobile Android (OCR, UI, Retrofit)
├── backend-spring/        # API Spring Boot (auth, données)
├── chatbot-flask/         # Assistant IA avec Flask
├── database/              # Scripts MongoDB
└── README.md
