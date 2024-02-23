# Начало работы
1. Следуйте этим шагам для запуска проекта:

    git clone git@github.com:N0vaT/JournalNotes.git
    
    cd JournalNotes


2. Соберите и запустите Docker-контейнеры:
   
    docker-compose up -d


3. Дождитесь развертывания приложения в контейнерах


4. После успешного развертывания, вы сможете получить доступ к приложению по следующим адресам:

    http://localhost:8090/...
### Users:

| URI                          | HTTP Method | Description              |
|------------------------------|-------------|--------------------------|
| /notes-api/v1/users          | GET         | Get all users            
| /notes-api/v1/users/{userId} | GET         | Get user by Id           
| /notes-api/v1/users          | POST        | Create new user          
| /notes-api/v1/users/{userId} | PATCH       | Edit not null filds user 
| /notes-api/v1/users/{userId} | PUT         | Edit user                
| /notes-api/v1/users/{userId} | DELETE      | Delete user              

---

## Notes:

| URI                                                  | HTTP Method | Description               |
|------------------------------------------------------|-------------|---------------------------|
| /notes-api/v1/users/{userId}/notes                   | GET         | Get all Notes in User     
| /notes-api/v1/users/{userId}/notes/{noteId}          | GET         | Get notes by Id           
| /notes-api/v1/users/{userId}/notes/                  | POST        | Create new note           
| /notes-api/v1/users/{userId}/notes/{noteId}          | PATCH       | Edit not null filds note  
| /notes-api/v1/users/{userId}/notes/{noteId}          | PUT         | Edit note                 
| /notes-api/v1/users/{userId}/notes/{noteId}          | DELETE      | Delete user               
| /notes-api/v1/users/{userId}/notes/{noteId}/comments | GET         | Get all comments  in note 

---

## Comments to the note:

| URI                                               | HTTP Method | Description                |
|---------------------------------------------------|-------------|----------------------------|
| /notes-api/v1/notes/{noteId}/comments             | GET         | Get all Comments in note   
| /notes-api/v1/notes/{noteId}/comments/{commentId} | GET         | Get comment by Id          
| /notes-api/v1/notes/{noteId}/comments             | POST        | Create new comment         
| /notes-api/v1/notes/{noteId}/comments/{commentId} | PATCH       | Edit not null filds coment 
| /notes-api/v1/notes/{noteId}/comments/{commentId} | PUT         | Edit comment               
| /notes-api/v1/notes/{noteId}/comments/{commentId} | DELETE      | Delete comment             

## Comments to comment:

| URI                                                           | HTTP Method | Description                |
|---------------------------------------------------------------|-------------|----------------------------|
| /notes-api/v1/comments/{commentBeforeId}/comments             | GET         | Get all Comments in note   
| /notes-api/v1/comments/{commentBeforeId}/comments/{commentId} | GET         | Get comment by Id          
| /notes-api/v1/comments/{commentBeforeId}/comments             | POST        | Create new comment         
| /notes-api/v1/comments/{commentBeforeId}/comments/{commentId} | PATCH       | Edit not null filds coment   
| /notes-api/v1/comments/{commentBeforeId}/comments/{commentId} | PUT         | Edit comment               
| /notes-api/v1/comments/{commentBeforeId}/comments/{commentId} | DELETE      | Delete comment             