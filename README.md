# Spring Boot CI/CD Demo — Jenkins Freestyle Project

A simple Spring Boot REST API designed for practising CI/CD with a **Jenkins Freestyle Project**.

---

## 📁 Project Structure

```
spring-boot-cicd-demo/
├── pom.xml
├── .gitignore
├── README.md
├── src/
│   ├── main/
│   │   ├── java/com/example/demo/
│   │   │   ├── DemoApplication.java
│   │   │   ├── controller/GreetingController.java
│   │   │   ├── model/Greeting.java
│   │   │   └── service/GreetingService.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/java/com/example/demo/
│       ├── DemoApplicationTests.java
│       ├── GreetingControllerTest.java
│       └── GreetingServiceTest.java
```

---

## 🚀 API Endpoints

| Method | Endpoint               | Description                 |
|--------|------------------------|-----------------------------|
| GET    | `/api/greeting`        | Returns a default greeting  |
| GET    | `/api/greeting/{name}` | Returns a personalised greeting |
| GET    | `/api/status`          | Returns `UP` (smoke test)   |
| GET    | `/actuator/health`     | Spring Boot health check    |
| GET    | `/actuator/info`       | App info metadata           |

---

## 🛠️ Prerequisites

- **Java 17+** (JDK)
- **Maven 3.8+**
- **Jenkins** (installed and running)
- **Git** (repo pushed to GitHub/GitLab/Bitbucket)

---

## ▶️ Run Locally

```bash
# Build
mvn clean package

# Run
java -jar target/spring-boot-cicd-demo.jar

# Test
mvn test
```

Visit: http://localhost:8080/api/greeting

---

## 🔧 Jenkins Freestyle Project Setup (Step-by-Step)

### Step 1 — Push to Git

```bash
cd spring-boot-cicd-demo
git init
git add .
git commit -m "Initial commit - Spring Boot CI/CD Demo"
git remote add origin <YOUR_REPO_URL>
git push -u origin main
```

### Step 2 — Create a Jenkins Freestyle Project

1. Open Jenkins → **New Item**
2. Enter name: `spring-boot-cicd-demo`
3. Select **Freestyle project** → Click **OK**

### Step 3 — Configure Source Code Management

1. Under **Source Code Management** → select **Git**
2. **Repository URL**: paste your Git repo URL
3. **Branch Specifier**: `*/main`
4. If private repo → Add **Credentials** (username/token)

### Step 4 — Configure Build Triggers (optional)

Pick one or more:

| Trigger                        | Setting                              |
|-------------------------------|--------------------------------------|
| **Poll SCM**                  | `H/5 * * * *` (every 5 min)         |
| **GitHub hook trigger**       | Check "GitHub hook trigger for GITScm polling" |
| **Build periodically**        | `H 0 * * *` (nightly)               |

### Step 5 — Add Build Steps

Click **Add build step** → **Execute shell** (Linux) or **Execute Windows batch command** (Windows).

#### 🐧 Linux / macOS:

```bash
echo "======== CLEAN & BUILD ========"
mvn clean package -DskipTests

echo "======== RUN TESTS ========"
mvn test

echo "======== BUILD SUCCESSFUL ========"
echo "Artifact: target/spring-boot-cicd-demo.jar"
```

#### 🪟 Windows:

```batch
echo ======== CLEAN ^& BUILD ========
mvn clean package -DskipTests

echo ======== RUN TESTS ========
mvn test

echo ======== BUILD SUCCESSFUL ========
echo Artifact: target\spring-boot-cicd-demo.jar
```

### Step 6 — Add Post-Build Actions

1. **Archive the artifacts**
   - Files to archive: `target/spring-boot-cicd-demo.jar`

2. **Publish JUnit test result report**
   - Test report XMLs: `target/surefire-reports/*.xml`

3. *(Optional)* **Email Notification**
   - Add recipients to get notified on build failure

### Step 7 — Save & Build

1. Click **Save**
2. Click **Build Now**
3. Check **Console Output** for results

---

## 📊 Expected Jenkins Console Output

```
[INFO] BUILD SUCCESS
[INFO] Tests run: 6, Failures: 0, Errors: 0, Skipped: 0
[INFO] Building jar: target/spring-boot-cicd-demo.jar
Finished: SUCCESS
```

---

## 🎯 Practice Exercises

Once you have the basic freestyle project working, try these:

1. **Break a test** — Change a greeting message and see Jenkins catch the failure
2. **Add a deploy step** — Add another build step to copy the JAR and run it:
   ```bash
   # Stop old instance
   pkill -f spring-boot-cicd-demo.jar || true
   # Start new instance in background
   nohup java -jar target/spring-boot-cicd-demo.jar &
   sleep 10
   # Smoke test
   curl http://localhost:8080/api/status
   ```
3. **Add build parameters** — Parameterise the build with environment name
4. **Chain jobs** — Create a separate deploy job triggered after build success
5. **Add webhooks** — Configure GitHub webhook for automatic trigger on push

---

## 📝 License

This project is for learning/practice purposes. Use freely.
