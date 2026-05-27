# Comandos usados en el proyecto para exportar cambios al repo remoto de GitHub
* `git checkout -b name_new_branch`
Se uso para crear una rama descriptiva que defina los cambios que se suben al reporsitorio y no directamente a la rama Main
* `git add .`
Guardar los cambios realizados en el repo local, para añadirlos al commit
* `git commit -m "descriptive_message_commit"`
Crear el commit que incluye los cambios subidos al repo remoto
* `git push -u origin name_actual_branch`
Se usa para subir finalmente todos los cambios al repo remoto y crear una PR antes de hacer un Merge con la rama Main