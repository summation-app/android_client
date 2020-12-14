# Summation Android Client

## Install

### Gradle
```
implementation("app.summation.android:android-client:1.0")
```

## Use

### gateway query

```kotlin
val client = Client(
    gatewayUrl = "<your gateway url>",
    token = "<your token>",
    gatewayToken = "<your gateway token>",
    defaultDatabase = "<your database name>",
)
val params = HashMap<String, Any>()
params.put("id", 1)
val response = client.query("SELECT * FROM queries WHERE id=:id", params)
println(response)
```

### gateway read

```kotlin
val client = Client(
    gatewayUrl = "<your gateway url>",
    token = "<your token>",
    gatewayToken = "<your gateway token>",
    defaultDatabase = "<your database name>",
)
val params = HashMap<String, Any>()
params.put("id", 1)
val response = client.read("queries", params)
println(response)
```

### gateway get

```kotlin
val client = Client(
    gatewayUrl = "<your gateway url>",
    token = "<your token>",
    gatewayToken = "<your gateway token>",
    defaultDatabase = "<your database name>",
)
val response = client.get("http://api.ipapi.com/98.33.28.214", null, null)
println(response)
```

## Test

### 1.clone code to your computer
```
git clone https://github.com/summation-app/android_client.git
```

### 2.open the folder by IntelliJ IDEA

### 3.run test
![run test](https://github.com/summation-app/android_client/raw/master/screenshots/run_test.jpg)

## Author
Xiong Zhao, zhaoxiong15928976339@gmail.com