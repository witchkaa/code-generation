# code-generation

Автор проєкту: Вікторія Сабадан, ІМ-22.

# Опис проєкту

Цей проєкт демонструє створення власного процесора анотацій (Annotation Processor) 
для автоматичної генерації серіалізаторів користувацьких класів у формати JSON та XML 
на основі Java Annotation Processing API.

### Опис функціональності:
- Процесор анотацій:
Клас SerializationJsonProcessor автоматично генерує серіалізатори (JsonSerializer і XmlSerializer) під час компіляції для класів, позначених відповідними анотаціями.
Підтримуються формати JSON і XML.


- Анотації:

1. @SerializedToJson: Вказує, що клас має бути серіалізованим у формат JSON.
2. @SerializedToXml: Вказує, що клас має бути серіалізованим у формат XML.
3. @JsonField: Застосовується до полів, які потрібно включити до JSON. Можна вказати користувацьке ім'я для мапінгу.
4. @XmlField: Аналогічна до @JsonField, але для XML.

### Процес генерації:

Під час компіляції SerializationJsonProcessor аналізує класи, позначені анотаціями @SerializedToJson або @SerializedToXml, та генерує відповідні класи серіалізаторів.
Серіалізатори містять метод serialize, який формує JSON або XML-рядок для заданого об’єкта.

### Демонстрація роботи:

У головному класі демонструється серіалізація об'єктів у формати JSON та XML, використовуючи згенеровані серіалізатори.
Наприклад:
Об'єкт класу User серіалізується у JSON.
Об'єкти класів Dog та Tea серіалізуються у XML.

Вивід:
```
{"username": "John", "email": "john@example.com"}
{"age": 5, "color": "white", "weight": 7, "name": "Milo"}
<Tea>
    <type>black</type>
    <price>1.2</price>
    <size>large</size>
</Tea> 
```

# Інструкції по збірці і запуску

Для встановлення застосунку Вам необхідно попередньо встановити Java на свою машину.

Склонуйте цей репозиторій та відкрийте у середовищі розробки. Увімкніть annotation processing у налаштуваннях. 
Скомпілюйте проєкт (mvn compile). Згенеровані класи з'являться у директорії target/generated-sources.

За допомогою mvn install можна додати модуль з анотаційним процесором до локального Maven-репозиторію. Після цього можна використовувати анотації та серіалізатори у своїх проєктах.
