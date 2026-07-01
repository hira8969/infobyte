# UML Diagrams

## Use Case Diagram

```plantuml
@startuml
actor Student
Student --> (Login)
Student --> (Update Profile)
Student --> (Start Exam)
Student --> (Answer Questions)
Student --> (Navigate Questions)
Student --> (Submit Exam)
Student --> (View Result)
Student --> (Logout)
(Submit Exam) <.. (Auto Submit) : extends
@enduml
```

## Class Diagram

```plantuml
@startuml
class User
class Question
class Exam
class UserAnswer
class Result
class AuthenticationService
class ExamService
class ResultService
class UserRepository
class QuestionRepository
class SessionManager

Exam "1" o-- "*" Question
Result "1" o-- "*" AnswerBreakdown
AuthenticationService --> UserRepository
AuthenticationService --> SessionManager
ExamService --> QuestionRepository
ExamService --> SessionManager
ResultService --> SessionManager
@enduml
```

## Sequence Diagram

```plantuml
@startuml
actor Student
participant LoginFrame
participant LoginController
participant AuthenticationService
participant DashboardFrame
Student -> LoginFrame : enter credentials
LoginFrame -> LoginController : login event
LoginController -> AuthenticationService : login()
AuthenticationService --> LoginController : User
LoginController -> DashboardFrame : open dashboard
@enduml
```

## Activity Diagram

```plantuml
@startuml
start
:Login;
if (Valid credentials?) then (yes)
  :Open dashboard;
  :Start exam;
  :Answer questions;
  if (Submit or timer ends?) then (yes)
    :Calculate result;
    :Show result;
  endif
  :Logout;
else (no)
  :Show error;
endif
stop
@enduml
```

## Component Diagram

```plantuml
@startuml
component View
component Controller
component Service
component Repository
component Model
component Utility
View --> Controller
Controller --> Service
Service --> Repository
Service --> Model
Controller --> Utility
@enduml
```

## Deployment Diagram

```plantuml
@startuml
node "Student PC" {
  artifact "Online Examination System.jar"
  node "JVM"
}
database "Future DB\nMySQL/SQLite" as DB
"Online Examination System.jar" ..> DB : Phase 2 JDBC
@enduml
```
