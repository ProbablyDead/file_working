## DevOps lab 2, выполнено на 10
* Релизовано приложение на Java, описание которого приставлено ниже
* Настроена сборка приложения в jar файл, используя 'actions/setup-java@v3'
* Создан пайплайн сборки в файле '.github/workflows/build.yml'
* Добавлена выгрузка артефактов 'actions/upload-artifact@v3.1.2'
* Выгрузка артефактов в Google Drive используя 'adityak74/google-drive-upload-git-action@main'
* Получение отформатированных файлов ('axel-op/googlejavaformat-action@v3'), создание архива и выгрузка в телеграм с помощью ('appleboy/telegram-action@master')
* Добавлена упаковка готового приложения в Docker-контейнер.
* Добавлена выгрузка готового Docker-контейнера после успешной сборки контейнера в Docker Hub.
* Добавлены шаги для развертывания приложения на пк, который был заведен в качестве local-host'a в Github Actions
* Пайплайн в GitHub Actions настроен для автоматической сборки, тестирования и выгрузки Docker-контейнера при каждом пуше в репозиторий или создании Pull Request.

## Вывод количества букв латинского алфавита в файле в другой файл

### Основное окно

![](./src/RMsrc/start.png)

### Запрос имен файлов

![](./src/RMsrc/ask_for_file_names.png)

### Процесс выполнения

![](./src/RMsrc/bar.gif)

### Завершение

![](./src/RMsrc/succsess.png)

### Результат

![](./src/RMsrc/result.png)

